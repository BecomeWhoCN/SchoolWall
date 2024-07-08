package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScFriendsDTO;
import com.baomidou.mybatisplus.extension.service.IService;
import online.xzjob.schoolwall.entity.ScFriends;
import online.xzjob.schoolwall.util.OperationResult;
import org.apache.logging.log4j.message.Message;
import online.xzjob.schoolwall.dto.PendingFriendRequestDTO;

import java.util.List;

public interface IScFriendsService extends IService<ScFriends> {
    List<ScFriendsDTO> getAcceptedFriends(int userId);
    List<PendingFriendRequestDTO> getPendingFriendRequests(Integer userId);
    void updateFriendRequestStatus(Integer userId, Integer friendId, String status);
    void updateFriendNickname(Integer userId, Integer friendId, String nickname);
    void deleteFriend(Integer userId, Integer friendId);

    OperationResult<ScFriends> createFriendRequest(ScFriends friend);
}