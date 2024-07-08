package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.entity.ScGroupMembers;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
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
public interface ScGroupMembersMapper extends BaseMapper<ScGroupMembers> {

    // 根据群组ID获取群组成员列表
    @Select("SELECT m.user_id, u.user_name, m.member_joined_at FROM sc_group_members m JOIN sc_users u ON m.user_id = u.user_id WHERE m.group_id = #{groupId}")
    List<ScGroupMembers> getGroupMembers(@Param("groupId") Integer groupId);


    // 根据群组ID和用户ID移除群组成员
    @Delete("DELETE FROM sc_group_members WHERE group_id = #{groupId} AND user_id = #{userId}")
    void removeMember(@Param("groupId") Integer groupId, @Param("userId") Integer userId);
}
