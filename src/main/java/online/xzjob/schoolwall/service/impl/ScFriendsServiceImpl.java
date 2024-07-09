package online.xzjob.schoolwall.service.impl;

import online.xzjob.schoolwall.dto.FriendSearchResult;
import online.xzjob.schoolwall.dto.PendingFriendRequestDTO;
import online.xzjob.schoolwall.dto.ScFriendsDTO;
import online.xzjob.schoolwall.entity.ScFriends;
import online.xzjob.schoolwall.mapper.ScFriendsMapper;
import online.xzjob.schoolwall.service.IScFriendsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScFriendsServiceImpl extends ServiceImpl<ScFriendsMapper, ScFriends> implements IScFriendsService {

    private final ScFriendsMapper scFriendsMapper;

    @Autowired
    public ScFriendsServiceImpl(ScFriendsMapper scFriendsMapper) {
        this.scFriendsMapper = scFriendsMapper;
    }

    @Override
    public List<ScFriendsDTO> getAcceptedFriends(int userId) {
        return scFriendsMapper.getAcceptedFriends(userId);
    }

    @Override
    public List<PendingFriendRequestDTO> getPendingFriendRequests(Integer userId) {
        return scFriendsMapper.getPendingFriendRequests(userId);
    }

    @Override
    public void updateFriendRequestStatus(Integer userId, Integer friendId, String status) {
        if (userId == null || friendId == null || status == null) {
            return;
        }
        if (userId == friendId) {
            return;
        }

        // 更新原有的好友请求记录状态
        scFriendsMapper.updateFriendRequestStatus(userId, friendId, status);

        // 如果状态是 accepted，添加互为好友的记录
        if ("accepted".equals(status)) {
            // 生成新的记录
            ScFriends reciprocalFriend = new ScFriends();
            reciprocalFriend.setUserId(friendId);
            reciprocalFriend.setFriendId(userId);
            reciprocalFriend.setStatus("accepted");
            save(reciprocalFriend);
        }
    }

    @Override
    public void updateFriendNickname(Integer userId, Integer friendId, String nickname) {
        scFriendsMapper.updateFriendNickname(userId, friendId, nickname);
    }

    @Override
    public void deleteFriend(Integer userId, Integer friendId) {
        scFriendsMapper.deleteFriend(userId, friendId);
    }

    @Override
    public OperationResult<ScFriends> createFriendRequest(String friendId ,String userId) {
        ScFriends friend = new ScFriends();
        friend.setUserId(Integer.parseInt(friendId));
        friend.setFriendId(Integer.parseInt(userId));
        friend.setStatus("pending");
        save(friend);
        return new OperationResult<>(true, "好友请求已发送", friend);
    }

    @Override
    public OperationResult<List<FriendSearchResult>> searchFriends(String query, int userId) {
        List<FriendSearchResult> friends = baseMapper.searchFriends(query, userId);
        return new OperationResult<>(true, "查询成功", friends);
    }
}