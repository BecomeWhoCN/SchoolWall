package online.xzjob.schoolwall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qiniu.common.QiniuException;
import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.entity.ScUserAvatars;
import online.xzjob.schoolwall.entity.ScUsers;
import online.xzjob.schoolwall.mapper.ScUserAvatarsMapper;
import online.xzjob.schoolwall.mapper.ScUsersMapper;
import online.xzjob.schoolwall.service.IScUserAvatarsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.ImageCompressor;
import online.xzjob.schoolwall.util.OperationResult;
import online.xzjob.schoolwall.util.QiniuUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;

@Service
public class ScUserAvatarsServiceImpl extends ServiceImpl<ScUserAvatarsMapper, ScUserAvatars> implements IScUserAvatarsService {

    @Autowired
    private ScUsersMapper scUsersMapper;

    @Autowired
    private ScUserAvatarsMapper scUserAvatarsMapper;

    @Autowired
    private QiniuUploadService qiniuUploadService;

    @Override
    public OperationResult<ScUserDTO> updateUsesrTx(Integer id, MultipartFile file) {
        OperationResult<ScUserDTO> result = new OperationResult<>();

        // 检查用户是否存在
        ScUsers scUsers = scUsersMapper.selectById(id);
        if (scUsers == null) {
            result.setSuccess(false);
            result.setMessage("上传头像文件失败，因为用户不存在");
            return result;
        }

        // 检查用户是否已经上传头像
        QueryWrapper<ScUserAvatars> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", id);
        ScUserAvatars scUserAvatar = scUserAvatarsMapper.selectOne(queryWrapper);
        if (scUserAvatar != null) {
            // 如果用户已经上传头像，则删除七牛云中的旧头像
            try {
                qiniuUploadService.deleteFile(scUserAvatar.getAvatarUrl());
            } catch (QiniuException e) {
                throw new RuntimeException(e);
            }

            // 删除数据库中的旧头像记录
            scUserAvatarsMapper.delete(queryWrapper);
        }

        // 检查文件格式
        String fileName = file.getOriginalFilename();
        String fileType = fileName.substring(fileName.lastIndexOf('.'));
        if (!Arrays.asList(".jpg", ".png", ".jpeg").contains(fileType.toLowerCase())) {
            result.setSuccess(false);
            result.setMessage("上传头像失败，不支持的文件格式");
            return result;
        }

        try {
            // 压缩文件大小并转换为 JPG 格式
            byte[] compressedBytes = ImageCompressor.compressImage(file, 100, 100, "jpg", 10);

            // 保存文件到七牛云对象存储中
            String newFileName = "userTx" + id + ".jpg";
            String fileUrl = qiniuUploadService.uploadFile(compressedBytes, newFileName, "useravatars");

            // 更新数据库中的头像地址
            ScUserAvatars newScUserAvatar = new ScUserAvatars();
            newScUserAvatar.setUserId(id);
            newScUserAvatar.setAvatarUrl(fileUrl);
            scUserAvatarsMapper.insert(newScUserAvatar);

            result.setSuccess(true);
            result.setMessage("头像上传成功");
        } catch (IOException e) {
            result.setSuccess(false);
            result.setMessage("上传头像失败: " + e.getMessage());
        }

        return result;
    }
}
