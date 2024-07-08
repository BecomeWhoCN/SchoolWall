package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScReportedDriftBottlesDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface IScDriftBottlesService extends IService<ScDriftBottles> {

    List<ScReportedDriftBottlesDTO> findAllReportedDriftBottles(int page, int pageSize);

    int countTotalReportedDriftBottles();

    boolean republish(Integer postId);

    boolean delete(Integer bottleId);
}
