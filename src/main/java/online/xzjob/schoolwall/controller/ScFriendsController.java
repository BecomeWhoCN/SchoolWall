package online.xzjob.schoolwall.controller;

import online.xzjob.schoolwall.dto.PendingFriendRequestDTO;
import online.xzjob.schoolwall.dto.ScFriendsDTO;
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

    @GetMapping("/acceptedFriends")
    public OperationResult<List<ScFriendsDTO>> getAcceptedFriends(@RequestParam int userId) {
        List<ScFriendsDTO> friends = scFriendsService.getAcceptedFriends(userId);
        return new OperationResult<>(true, "查询成功", friends);
    }

    @GetMapping("/pendingRequests")
    public OperationResult<List<PendingFriendRequestDTO>> getPendingFriendRequests(@RequestParam Integer userId) {
        List<PendingFriendRequestDTO> pendingRequests = scFriendsService.getPendingFriendRequests(userId);
        return new OperationResult<>(true, "查询成功", pendingRequests);
    }

    @PostMapping("/updateRequestStatus")
    public OperationResult<Void> updateFriendRequestStatus(@RequestParam Integer userId, @RequestParam Integer friendId, @RequestParam String status) {
        scFriendsService.updateFriendRequestStatus(userId, friendId, status);
        return new OperationResult<>(true, "更新成功", null);
    }

    @PostMapping("/updateNickname")
    public OperationResult<Void> updateFriendNickname(@RequestParam Integer userId, @RequestParam Integer friendId, @RequestParam String nickname) {
        scFriendsService.updateFriendNickname(userId, friendId, nickname);
        return new OperationResult<>(true, "备注更新成功", null);
    }

    @PostMapping("/deleteFriend")
    public OperationResult<Void> deleteFriend(@RequestParam Integer userId, @RequestParam Integer friendId) {
        scFriendsService.deleteFriend(userId, friendId);
        return new OperationResult<>(true, "好友删除成功", null);
    }

}