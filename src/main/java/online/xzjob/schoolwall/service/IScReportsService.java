package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.scReportedData;
import online.xzjob.schoolwall.entity.ScReports;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface IScReportsService extends IService<ScReports> {

    Boolean addReportedData(scReportedData scReportedData);
}
