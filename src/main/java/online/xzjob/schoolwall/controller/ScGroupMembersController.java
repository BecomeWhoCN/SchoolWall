package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.entity.ScGroupMembers;
import online.xzjob.schoolwall.service.IScGroupMembersService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@RestController
@RequestMapping("/api/scGroups")
public class ScGroupMembersController {
    private final IScGroupMembersService scGroupMembersService;

    @Autowired
    public ScGroupMembersController(IScGroupMembersService scGroupMembersService) {
        this.scGroupMembersService = scGroupMembersService;
    }

    // 获取群组成员列表
    @GetMapping("/members")
    public OperationResult<List<ScGroupMembers>> getGroupMembers(@RequestParam Integer groupId) {
        List<ScGroupMembers> members = scGroupMembersService.getGroupMembers(groupId);
        return new OperationResult<>(true, "查询成功", members);
    }

    // 移除群组成员
    @PostMapping("/removeMember")
    public OperationResult<Void> removeMember(@RequestParam Integer groupId, @RequestParam Integer userId) {
        scGroupMembersService.removeMember(groupId, userId);
        return new OperationResult<>(true, "成员已移除", null);
    }
}
