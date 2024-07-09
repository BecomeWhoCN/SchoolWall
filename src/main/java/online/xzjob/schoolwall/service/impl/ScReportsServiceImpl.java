package online.xzjob.schoolwall.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.dto.scReportedData;
import online.xzjob.schoolwall.entity.ScReports;
import online.xzjob.schoolwall.mapper.ScReportsMapper;
import online.xzjob.schoolwall.service.IScReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Service
public class ScReportsServiceImpl extends ServiceImpl<ScReportsMapper, ScReports> implements IScReportsService {
    @Autowired
    private ScReportsMapper scReportsMapper;

    @Override
    public Boolean addReportedData(scReportedData scReportedData) {
        String userId = scReportedData.getUserId();
        String postId = scReportedData.getPostId();
        String value = scReportedData.getValue();
        if ( scReportsMapper.addReportedData(userId,postId,value) == 1) {
            return true;
        } else {
            return false;
        }

    }
}
