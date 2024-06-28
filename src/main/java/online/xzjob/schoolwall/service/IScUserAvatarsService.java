package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.entity.ScUserAvatars;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface IScUserAvatarsService extends IService<ScUserAvatars> {

    OperationResult<ScUserDTO> updateUsesrTx(Integer id, MultipartFile file);

}
