package online.xzjob.schoolwall.service.impl;

import online.xzjob.schoolwall.dto.PendingFriendRequestDTO;
import online.xzjob.schoolwall.dto.ScFriendsDTO;
import online.xzjob.schoolwall.entity.ScFriends;
import online.xzjob.schoolwall.mapper.ScFriendsMapper;
import online.xzjob.schoolwall.service.IScFriendsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import online.xzjob.schoolwall.util.OperationResult;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Timestamp;
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
        scFriendsMapper.updateFriendRequestStatus(userId, friendId, status);
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
    public OperationResult<ScFriends> createFriendRequest(ScFriends friend) {
        save(friend);
        return new OperationResult<>(true, "好友请求已发送", friend);
    }
}
