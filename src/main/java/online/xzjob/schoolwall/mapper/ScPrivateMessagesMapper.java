package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.entity.ScPrivateMessages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScPrivateMessagesMapper extends BaseMapper<ScPrivateMessages> {

    @Select("SELECT * FROM sc_private_messages WHERE (sender_id = #{userId1} AND receiver_id = #{userId2}) " +
            "OR (sender_id = #{userId2} AND receiver_id = #{userId1}) ORDER BY message_sent_at")
    List<ScPrivateMessages> getPrivateMessages(@Param("userId1") Integer userId1, @Param("userId2") Integer userId2);

    @Insert("INSERT INTO sc_private_messages (sender_id, receiver_id, message_text) VALUES (#{senderId}, #{receiverId}, #{messageText})")
    void insertPrivateMessage(ScPrivateMessages privateMessage);
}