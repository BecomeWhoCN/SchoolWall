package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.entity.ScGroupInvitations;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface ScGroupInvitationsMapper extends BaseMapper<ScGroupInvitations> {

    // 修改邀请状态
    @Update("UPDATE sc_group_invitations SET status = #{status} WHERE invitation_id = #{invitationId}")
    void respondToInvitation(@Param("invitationId") Integer invitationId, @Param("status") String status);

}
