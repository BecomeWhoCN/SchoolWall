package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.entity.ScGroupInvitations;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.util.OperationResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface IScGroupInvitationsService extends IService<ScGroupInvitations> {
    void inviteUser(ScGroupInvitations invitation);
    void respondToInvitation(Integer invitationId, String status);

    OperationResult<ScGroupInvitations> createInvitation(ScGroupInvitations invitation);
}
