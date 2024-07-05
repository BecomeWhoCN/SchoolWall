package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.dto.ScFriendsDTO;
import online.xzjob.schoolwall.entity.ScFriends;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import online.xzjob.schoolwall.dto.PendingFriendRequestDTO;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface ScFriendsMapper extends BaseMapper<ScFriends> {
    @Select("SELECT f.user_id, f.friend_id, f.friend_nickname, a.avatar_url\n" +
            "FROM sc_friends f\n" +
            "LEFT JOIN sc_user_avatars a\n" +
            "ON f.friend_id = a.user_id\n" +
            "WHERE f.user_id = #{userId} AND f.status = 'accepted';")
    List<ScFriendsDTO> getAcceptedFriends(@Param("userId") int userId);

    @Select("SELECT f.user_id, f.friend_id, u.user_name AS friend_name, f.created_at " +
            "FROM sc_friends f " +
            "JOIN sc_users u ON f.friend_id = u.user_id " +
            "WHERE f.user_id = #{userId} AND f.status = 'pending'")
    List<PendingFriendRequestDTO> getPendingFriendRequests(@Param("userId") Integer userId);

    // 更新好友关系状态
    @Update("UPDATE sc_friends SET status = #{status} WHERE user_id = #{userId} AND friend_id = #{friendId}")
    void updateFriendRequestStatus(@Param("userId") Integer userId, @Param("friendId") Integer friendId, @Param("status") String status);

    @Update("UPDATE sc_friends SET friend_nickname = #{nickname} WHERE user_id = #{userId} AND friend_id = #{friendId}")
    void updateFriendNickname(@Param("userId") Integer userId, @Param("friendId") Integer friendId, @Param("nickname") String nickname);

    @Delete("DELETE FROM sc_friends WHERE user_id = #{userId} AND friend_id = #{friendId}")
    void deleteFriend(@Param("userId") Integer userId, @Param("friendId") Integer friendId);

}