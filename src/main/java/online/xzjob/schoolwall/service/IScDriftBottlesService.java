package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.util.OperationResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lpl
 * @since 2024-01-03
 */
public interface IScDriftBottlesService extends IService<ScDriftBottles> {
    /**
     * 根据传进来的漂流瓶信息存入数据库
     * @param scBottleDTO
     * @return
     */
    OperationResult<ScBottleDTO> createDriftBottles(ScBottleDTO scBottleDTO);
}
