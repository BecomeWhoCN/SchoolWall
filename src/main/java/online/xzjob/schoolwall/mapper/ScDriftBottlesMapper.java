package online.xzjob.schoolwall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.xzjob.schoolwall.dto.ScReportedDriftBottlesDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Mapper
public interface ScDriftBottlesMapper extends BaseMapper<ScDriftBottles> {
    @Select("SELECT u.user_name, d.bottle_title,d.bottle_id FROM sc_users u JOIN sc_drift_bottles d ON u.user_id = d.user_id WHERE d.bottle_status = 'inactive'ORDER BY d.bottle_id ASC LIMIT #{pageSize} OFFSET #{offset}")
    List<ScReportedDriftBottlesDTO> findAllReportedPosts(int offset, int pageSize);

    @Select("SELECT COUNT(*) FROM sc_drift_bottles WHERE bottle_status = 'inactive'")
    int countTotalReportedPosts();
    @Update("UPDATE sc_drift_bottles SET bottle_status = 'published' WHERE bottle_id = #{bottleId} AND bottle_status = 'inactive'")
    int republish(Integer bottleId);
    @Delete("DELETE FROM sc_drift_bottles WHERE bottle_id = #{bottleId};")
    int delete(Integer bottleId);
}
