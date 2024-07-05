package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.entity.ScPrivateMessageAttachments;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ScPrivateMessageAttachmentsMapper extends BaseMapper<ScPrivateMessageAttachments> {

    @Select("SELECT attachment_url FROM sc_private_message_attachments WHERE message_id = #{messageId}")
    List<String> getAttachmentsByMessageId(@Param("messageId") Integer messageId);

    @Insert("INSERT INTO sc_private_message_attachments (message_id, attachment_url) VALUES (#{messageId}, #{attachmentUrl})")
    void insertAttachment(ScPrivateMessageAttachments attachment);
}