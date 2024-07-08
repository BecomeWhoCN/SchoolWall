package online.xzjob.schoolwall.service.impl;

import online.xzjob.schoolwall.entity.ScGroupInvitations;
import online.xzjob.schoolwall.mapper.ScGroupInvitationsMapper;
import online.xzjob.schoolwall.service.IScGroupInvitationsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Service
public class ScGroupInvitationsServiceImpl extends ServiceImpl<ScGroupInvitationsMapper, ScGroupInvitations> implements IScGroupInvitationsService {
    private final ScGroupInvitationsMapper scGroupInvitationsMapper;

    @Autowired
    public ScGroupInvitationsServiceImpl(ScGroupInvitationsMapper scGroupInvitationMapper) {
        this.scGroupInvitationsMapper = scGroupInvitationMapper;
    }

    @Override
    public void inviteUser(ScGroupInvitations invitation) {
        this.save(invitation);
    }

    @Override
    public void respondToInvitation(Integer invitationId, String status) {
        scGroupInvitationsMapper.respondToInvitation(invitationId, status);
    }

    @Override
    public OperationResult<ScGroupInvitations> createInvitation(ScGroupInvitations invitation) {
        save(invitation);
        return new OperationResult<>(true, "邀请已发送", invitation);
    }
}
