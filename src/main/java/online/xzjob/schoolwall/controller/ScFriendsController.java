package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.FriendSearchResult;
import online.xzjob.schoolwall.dto.PendingFriendRequestDTO;
import online.xzjob.schoolwall.dto.ScFriendsDTO;
import online.xzjob.schoolwall.entity.ScFriends;
import online.xzjob.schoolwall.service.IScFriendsService;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/scFriends")
public class ScFriendsController {

    private final IScFriendsService scFriendsService;

    @Autowired
    public ScFriendsController(IScFriendsService scFriendsService) {
        this.scFriendsService = scFriendsService;
    }

    // 根据用户ID查询用户的好友列表并返回
    @GetMapping("/acceptedFriends")
    public OperationResult<List<ScFriendsDTO>> getAcceptedFriends(@RequestParam int userId) {
        List<ScFriendsDTO> friends = scFriendsService.getAcceptedFriends(userId);
        return new OperationResult<>(true, "查询成功", friends);
    }

    // 根据用户ID查询用户的好友列表并返回
    @GetMapping("/pendingRequests")
    public OperationResult<List<PendingFriendRequestDTO>> getPendingFriendRequests(@RequestParam Integer userId) {
        List<PendingFriendRequestDTO> pendingRequests = scFriendsService.getPendingFriendRequests(userId);
        return new OperationResult<>(true, "查询成功", pendingRequests);
    }

    // 更新好友请求的状态（接受或拒绝）
    @PostMapping("/updateRequestStatus")
    public OperationResult<Void> updateFriendRequestStatus(@RequestParam Integer userId, @RequestParam Integer friendId, @RequestParam String status) {
        scFriendsService.updateFriendRequestStatus(userId, friendId, status);
        return new OperationResult<>(true, "更新成功", null);
    }

    // 更新好友的备注名称
    @PostMapping("/updateNickname")
    public OperationResult<Void> updateFriendNickname(@RequestParam Integer userId, @RequestParam Integer friendId, @RequestParam String nickname) {
        scFriendsService.updateFriendNickname(userId, friendId, nickname);
        return new OperationResult<>(true, "备注更新成功", null);
    }

    // 删除好友
    @PostMapping("/deleteFriend")
    public OperationResult<Void> deleteFriend(@RequestParam Integer userId, @RequestParam Integer friendId) {
        scFriendsService.deleteFriend(userId, friendId);
        return new OperationResult<>(true, "好友删除成功", null);
    }

      // 添加发送好友请求
    @PostMapping("/add")
    public OperationResult<ScFriends> addFriend(@RequestParam String friendId ,@RequestParam String userId) {
        return scFriendsService.createFriendRequest(friendId,userId);
    }

    // 模糊搜索好友
    @GetMapping("/friends")
    public OperationResult<List<FriendSearchResult>> searchFriends(@RequestParam String query, @RequestParam int userId) {
        return scFriendsService.searchFriends(query, userId);
    }



}