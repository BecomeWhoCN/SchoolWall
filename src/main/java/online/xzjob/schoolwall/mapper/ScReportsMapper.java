package online.xzjob.schoolwall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.xzjob.schoolwall.entity.ScReports;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Mapper
public interface ScReportsMapper extends BaseMapper<ScReports> {
    @Insert("INSERT INTO sc_reports (reporter_id, reported_post_id, report_reason) VALUES (#{userId}, #{postId}, #{value})")
    int addReportedData(String userId, String postId, String value);
}

