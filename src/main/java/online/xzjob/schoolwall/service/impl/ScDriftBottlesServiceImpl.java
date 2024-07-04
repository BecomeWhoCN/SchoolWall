package online.xzjob.schoolwall.service.impl;

import jakarta.annotation.Resource;
import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import online.xzjob.schoolwall.mapper.ScDriftBottlesMapper;
import online.xzjob.schoolwall.service.IScDriftBottlesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import online.xzjob.schoolwall.util.QiniuUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lpl
 * @since 2024-06-26
 */
@Service
public class ScDriftBottlesServiceImpl extends ServiceImpl<ScDriftBottlesMapper, ScDriftBottles> implements IScDriftBottlesService {

    @Autowired
    private QiniuUploadService qiniuUploadService = null;

    @Autowired
    private ScDriftBottlesMapper scDriftBottlesMapper;

    /**
     * 根据传进来的漂流瓶信息存入数据库
     * @param scBottleDTO
     * @return
     */
    @Override
    @Transactional
    public OperationResult<ScBottleDTO> createDriftBottles(ScBottleDTO scBottleDTO) {

        try {
            // 上传文件到七牛云
            MultipartFile file = scBottleDTO.getFile();
            String fileUrl = null;
            if (file != null) {
                byte[] fileBytes = file.getBytes();
                String fileName = file.getOriginalFilename();
                fileUrl = qiniuUploadService.uploadFile(fileBytes, fileName, "bottleContents");
                // 设置文件URL
                scBottleDTO.setBottle_content_url(fileUrl);
                System.out.println(fileUrl);
            }


            // 设置创建时间
            scBottleDTO.setBottle_created_at(new Date());

            // 保存漂流瓶信息到数据库,返回受影响的行数，如果大于0，那么说明成功，反之失败
            int row = scDriftBottlesMapper.saveDriftBottles(scBottleDTO);

            if (row > 0){
                // 返回成功结果
                return new OperationResult<>(true, "漂流瓶发布成功", scBottleDTO);
            }else {
                // 返回失败结果
                return new OperationResult<>(false, "漂流瓶发布失败", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            // 返回失败结果
            return new OperationResult<>(false, "漂流瓶发布失败", null);
        }
    }
}
