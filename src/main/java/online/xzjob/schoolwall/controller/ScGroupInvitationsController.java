package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.entity.ScGroupInvitations;
import online.xzjob.schoolwall.service.IScGroupInvitationsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/scGroupInvitations")
public class ScGroupInvitationsController {

    private final IScGroupInvitationsService scGroupInvitationsService;

    @Autowired
    public ScGroupInvitationsController(IScGroupInvitationsService scGroupInvitationService) {
        this.scGroupInvitationsService = scGroupInvitationService;
    }

    @PostMapping("/invite")
    public OperationResult<Void> inviteUser(@RequestBody ScGroupInvitations invitation) {
        scGroupInvitationsService.inviteUser(invitation);
        return new OperationResult<>(true, "邀请已发送", null);
    }

    @PostMapping("/respond")
    public OperationResult<Void> respondToInvitation(@RequestParam Integer invitationId, @RequestParam String status) {
        scGroupInvitationsService.respondToInvitation(invitationId, status);
        return new OperationResult<>(true, "操作成功", null);
    }

    @PostMapping("/inviteToGroup")
    public OperationResult<ScGroupInvitations> inviteToGroup(@RequestBody ScGroupInvitations invitation) {
        return scGroupInvitationsService.createInvitation(invitation);
    }
}
