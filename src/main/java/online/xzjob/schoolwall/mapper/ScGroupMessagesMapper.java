package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.entity.ScGroupMessages;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
public interface ScGroupMessagesMapper extends BaseMapper<ScGroupMessages> {

    // 获取群组消息
    @Select("SELECT m.message_id, m.sender_id, u.user_name AS sender_name, m.message_text, m.message_sent_at FROM sc_group_messages m JOIN sc_users u ON m.sender_id = u.user_id WHERE m.group_id = #{groupId} ORDER BY m.message_sent_at")
    List<ScGroupMessages> getGroupMessages(@Param("groupId") Integer groupId);

}
