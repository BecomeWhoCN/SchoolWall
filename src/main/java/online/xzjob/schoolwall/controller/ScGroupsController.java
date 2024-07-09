package online.xzjob.schoolwall.controller;


import online.xzjob.schoolwall.dto.GroupSearchResult;
import online.xzjob.schoolwall.entity.ScGroups;
import online.xzjob.schoolwall.service.IScGroupsService;
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
public class ScGroupsController {

    private final IScGroupsService scGroupService;

    @Autowired
    public ScGroupsController(IScGroupsService scGroupService) {
        this.scGroupService = scGroupService;
    }

    // 创建群组
    @PostMapping("/create")
    public OperationResult<ScGroups> createGroup(@RequestBody ScGroups group) {
        scGroupService.createGroup(group);
        return new OperationResult<>(true, "群组创建成功", group);
    }

    // 获取用户加入的群组列表
    @GetMapping("/userGroups")
    public OperationResult<List<ScGroups>> getUserGroups(@RequestParam Integer userId) {
        List<ScGroups> groups = scGroupService.getUserGroups(userId);
        return new OperationResult<>(true, "查询成功", groups);
    }

    // 更新群组信息
    @PostMapping("/update")
    public OperationResult<Void> updateGroupInfo(@RequestBody ScGroups group) {
        scGroupService.updateGroupInfo(group);
        return new OperationResult<>(true, "群组信息更新成功", null);
    }

    // 删除群组
    @PostMapping("/delete")
    public OperationResult<Void> deleteGroup(@RequestParam Integer groupId) {
        scGroupService.deleteGroup(groupId);
        return new OperationResult<>(true, "群组已删除", null);
    }

    // 模糊搜索群组
    @GetMapping("/groups")
    public OperationResult<List<GroupSearchResult>> searchGroups(@RequestParam String query) {
        return scGroupService.searchGroups(query);
    }
}