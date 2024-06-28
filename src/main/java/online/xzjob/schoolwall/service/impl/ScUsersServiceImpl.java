package online.xzjob.schoolwall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.entity.ScUsers;
import online.xzjob.schoolwall.mapper.ScUsersMapper;
import online.xzjob.schoolwall.service.IScUsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import online.xzjob.schoolwall.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

/**
 *  服务实现类
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Service
public class ScUsersServiceImpl extends ServiceImpl<ScUsersMapper, ScUsers> implements IScUsersService {

    @Autowired
    private ScUsersMapper scUsersMapper;

    // 创建用户 /api/scUsers/create_scuser
    public OperationResult<ScUserDTO> createUser(ScUsers user) {

        // 检查用户名是否已存在 select * from sc_users where user_email=?(user.getUserEmail())
        QueryWrapper<ScUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_email", user.getUserEmail());
        ScUsers existingUser = scUsersMapper.selectOne(queryWrapper);

        // 如果存在，返回错误信息
        OperationResult<ScUserDTO> result = new OperationResult<>();
        if (existingUser != null) {
            result.setSuccess(false);
            result.setMessage("邮箱已被注册");
            return result;
        }

        // 密码格式验证
        String PasswdRegex = "^(?=.*[^0-9])[A-Za-z\\d!@#$%^&*()_+]{7,}$";
        Pattern pattern = Pattern.compile(PasswdRegex);
        if (!pattern.matcher(user.getUserPasswordHash()).matches()) {
            result.setSuccess(false);
            result.setMessage("密码格式不正确，密码大于6为并且至少要有一个非数字字符");
            return result;
        }

        // 加密密码
        String hashedPassword = PasswordUtil.get_jmPasswd(user.getUserPasswordHash(),PasswordUtil.SaltValue.getBytes());
        user.setUserPasswordHash(hashedPassword);

        // 如果不存在，创建新用户
        user.setUserRole("USER");
        // 设置其他属性...
        scUsersMapper.insert(user);

        // 构建返回的 DTO
        ScUserDTO scUserDTO = new ScUserDTO();
        scUserDTO.setUserId(user.getUserId());
        scUserDTO.setUserEmail(user.getUserEmail());
        scUserDTO.setUserRole(user.getUserRole());

        // 返回成功信息
        result.setSuccess(true);
        result.setMessage("用户创建成功");
        result.setData(scUserDTO);

        return result;
    }

    // 查询用户(根据用户id) /api/scUsers/get_scuserbyid/{userId}
    @Override
    public OperationResult<ScUserDTO> getUserById(Integer userId) {

        // 检查用户是否存在
        ScUsers user = scUsersMapper.selectById(userId);
        //
        OperationResult<ScUserDTO> result = new OperationResult<>();

        if (user == null) {
            result.setSuccess(false);
            result.setMessage("用户不存在或未找到");
        } else {

            if (user.getUserOnlineStatus() == "away"){
                result.setSuccess(false);
                result.setMessage("用户已被注销");
            }

            ScUserDTO scUserDTO = new ScUserDTO();
            scUserDTO.setUserId(user.getUserId());
            scUserDTO.setUserEmail(user.getUserEmail());
            scUserDTO.setUserRole(user.getUserRole());
            scUserDTO.setUserBio(user.getUserBio());
            scUserDTO.setUserClass(user.getUserClass());
            scUserDTO.setUserGender(user.getUserGender());
            scUserDTO.setUserName(user.getUserName());
            scUserDTO.setUserPhone(user.getUserPhone());
            scUserDTO.setUserOnlineStatus(user.getUserOnlineStatus());

            result.setSuccess(true);
            result.setMessage("用户存在，正在登陆");
            result.setData(scUserDTO);
        }
        return result;
    }

    // 查询用户(根据用户Email) /api/scUsers/get_scuserbyemail/{userEmail}
    @Override
    public OperationResult<ScUserDTO> getUserByEmail(String userEmail) {

        // 检查用户是否存在 select * from sc_users where user_email=?(userEmail)
        ScUsers user = scUsersMapper.selectOne(new QueryWrapper<ScUsers>().eq("user_email", userEmail));
        OperationResult<ScUserDTO> result = new OperationResult<>();

        if (user != null) {
            ScUserDTO scUserDTO = new ScUserDTO();
            scUserDTO.setUserId(user.getUserId());
            scUserDTO.setUserEmail(user.getUserEmail());
            scUserDTO.setUserRole(user.getUserRole());

            result.setSuccess(true);
            result.setMessage("用户存在，正在登陆");
            result.setData(scUserDTO);
        } else {
            result.setSuccess(false);
            result.setMessage("用户不存在或未找到");
        }

        return result;
    }

    // 用户登录 /api/scUsers/getLonginValue
    @Override
    public OperationResult<ScUserDTO> getLonginValue(String email, String password) {
        OperationResult<ScUserDTO> result = new OperationResult<>();

        // 检查用户是否存在
        ScUsers user = scUsersMapper.selectOne(new QueryWrapper<ScUsers>().eq("user_email", email));

        if (user == null) {
            result.setSuccess(false);
            result.setMessage("用户不存在或未找到");
            return result;
        }

        // 检查密码是否正确
        String hashedPassword = PasswordUtil.get_jmPasswd(password, PasswordUtil.SaltValue.getBytes());
        if (!user.getUserPasswordHash().equals(hashedPassword)) {
            result.setSuccess(false);
            result.setMessage("密码错误，请检查你的密码");
            return result;
        }

        // 构建返回的 DTO
        ScUserDTO scUserDTO = new ScUserDTO();
        scUserDTO.setUserId(user.getUserId());
        scUserDTO.setUserEmail(user.getUserEmail());
        scUserDTO.setUserRole(user.getUserRole());

        result.setSuccess(true);
        result.setMessage("用户登录成功");
        result.setData(scUserDTO);

        return result;
    }

    // 用户更新密码 /api/scUsers/update_userpassword
    @Override
    public OperationResult<ScUserDTO> updateUserPassword(Integer id, String oldPassword, String newPassword) {
        OperationResult<ScUserDTO> result = new OperationResult<>();

        // 旧密码传进来 加密，和数据库查询id和密码的配置
        // select * from sc_users where user_id=?(id) and user_password_hash=?(oldPasswordHash)
        String oldPasswordHash = PasswordUtil.get_jmPasswd(oldPassword, PasswordUtil.SaltValue.getBytes());
        QueryWrapper<ScUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        queryWrapper.eq("user_password_hash", oldPasswordHash);
        if (scUsersMapper.selectOne(queryWrapper) == null) {
            result.setMessage("旧密码错误，请检查你的密码");
            return result;
        }

        // 密码格式验证
        String PasswdRegex = "^(?=.*[^0-9])[A-Za-z\\d!@#$%^&*()_+]{7,}$";
        Pattern pattern = Pattern.compile(PasswdRegex);
        if (!pattern.matcher(newPassword).matches()) {
            result.setSuccess(false);
            result.setMessage("密码格式不正确，密码大于6为并且至少要有一个非数字字符");
            return result;
        }

        // 加密新密码，更新数据库
        String newPasswordHash = PasswordUtil.get_jmPasswd(newPassword, PasswordUtil.SaltValue.getBytes());

        ScUsers scUsers = new ScUsers();
        scUsers.setUserId(id);
        scUsers.setUserPasswordHash(newPasswordHash);
        int updateCount = scUsersMapper.updateById(scUsers);

        if (updateCount == 1) {
            result.setSuccess(true);
            result.setMessage("密码更新成功");
        } else {
            result.setSuccess(false);
            result.setMessage("密码更新失败");
        }

        return result;
    }

    // 用户忘记密码 /api/scUsers/update_lostPasswd
    @Override
    public OperationResult<ScUserDTO> updateLostPasswd(String phone, String email, String newPasswd) {
        OperationResult<ScUserDTO> result = new OperationResult<>();

        // 检查手机和邮箱是否存在
        QueryWrapper<ScUsers> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_phone", phone);
        queryWrapper.eq("user_email", email);

        ScUsers user = scUsersMapper.selectOne(queryWrapper);
        if (user == null) {
            result.setSuccess(false);
            result.setMessage("手机或邮箱不存在");
            return result;
        }

        // 检查新密码格式
        String PasswdRegex = "^(?=.*[^0-9])[A-Za-z\\d!@#$%^&*()_+]{7,}$";
        Pattern pattern = Pattern.compile(PasswdRegex);

        if (!pattern.matcher(newPasswd).matches()) {
            result.setSuccess(false);
            result.setMessage("密码格式不正确，密码大于6位并且至少要有一个非数字字符");
            return result;
        }

        // 加密新密码
        String newPasswordHash = PasswordUtil.get_jmPasswd(newPasswd, PasswordUtil.SaltValue.getBytes());

        // 更新数据库
        user.setUserPasswordHash(newPasswordHash);
        int updateResult = scUsersMapper.updateById(user);

        if (updateResult > 0) {
            result.setSuccess(true);
            result.setMessage("密码更新成功");

            // 构建返回的 DTO
            ScUserDTO scUserDTO = new ScUserDTO();
            scUserDTO.setUserId(user.getUserId());
            scUserDTO.setUserEmail(user.getUserEmail());
            scUserDTO.setUserRole(user.getUserRole());

            result.setData(scUserDTO);
        } else {
            result.setSuccess(false);
            result.setMessage("密码更新失败，请稍后再试");
        }

        return result;
    }


}
