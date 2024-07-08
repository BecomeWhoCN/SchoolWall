package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.dto.GroupSearchResult;
import online.xzjob.schoolwall.entity.ScGroups;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface ScGroupsMapper extends BaseMapper<ScGroups> {

    // 根据用户id获取用户所在的群组
    @Select("SELECT * FROM sc_groups WHERE group_id IN (SELECT group_id FROM sc_group_members WHERE user_id = #{userId})")
    List<ScGroups> getUserGroups(@Param("userId") Integer userId);

    @Select("SELECT group_id, group_name, group_description FROM sc_groups WHERE group_name LIKE CONCAT('%', #{query}, '%')")
    List<GroupSearchResult> searchGroups(@Param("query") String query);
}
