package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.dto.UserSearchResult;
import online.xzjob.schoolwall.entity.ScUsers;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface IScUsersService extends IService<ScUsers> {

    // 注册用户
    OperationResult<ScUserDTO> createUser(ScUsers user);

    // 根据传入的用户id获取用户信息
    OperationResult<ScUserDTO> getUserById(Integer id);

    // 根据传入的用户email获取用户信息
    OperationResult<ScUserDTO> getUserByEmail(String email);

    // 登录查询邮箱和密码是否匹配
    OperationResult<ScUserDTO> getLonginValue(String email, String password);

    // 更新用户密码
    OperationResult<ScUserDTO> updateUserPassword(Integer id, String oldPassword, String newPassword);

    // 用户忘记密码的
    OperationResult<ScUserDTO> updateLostPasswd(String phone, String email, String newPasswd);

    // 根据用户id修改用户为away状态，删除用户
    OperationResult<ScUserDTO> updateUserStatus(Integer id);

    // 根据用户id修改用户的账号状态  /api/scUsers/update_userStatus
    OperationResult<ScUserDTO> updateUserStatus(Integer id, String userPasswd);

    OperationResult<ScUserDTO> updateUserInfo(ScUsers scUsers);

    OperationResult<List<UserSearchResult>> searchUsers(String query);
}
