package online.xzjob.schoolwall.service.impl;

import online.xzjob.schoolwall.dto.ScPrivateMessagesDTO;
import online.xzjob.schoolwall.entity.ScPrivateMessages;
import online.xzjob.schoolwall.entity.ScPrivateMessageAttachments;
import online.xzjob.schoolwall.mapper.ScPrivateMessagesMapper;
import online.xzjob.schoolwall.mapper.ScPrivateMessageAttachmentsMapper;
import online.xzjob.schoolwall.service.IScPrivateMessagesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScPrivateMessagesServiceImpl extends ServiceImpl<ScPrivateMessagesMapper, ScPrivateMessages> implements IScPrivateMessagesService {

    private final ScPrivateMessagesMapper privateMessagesMapper;
    private final ScPrivateMessageAttachmentsMapper attachmentsMapper;

    @Autowired
    public ScPrivateMessagesServiceImpl(ScPrivateMessagesMapper privateMessagesMapper, ScPrivateMessageAttachmentsMapper attachmentsMapper) {
        this.privateMessagesMapper = privateMessagesMapper;
        this.attachmentsMapper = attachmentsMapper;
    }

    @Override
    public List<ScPrivateMessagesDTO> getPrivateMessages(Integer userId1, Integer userId2) {
        List<ScPrivateMessages> messages = privateMessagesMapper.getPrivateMessages(userId1, userId2);
        return messages.stream().map(message -> {
            ScPrivateMessagesDTO dto = new ScPrivateMessagesDTO();
            dto.setMessageId(message.getMessageId());
            dto.setSenderId(message.getSenderId());
            dto.setReceiverId(message.getReceiverId());
            dto.setMessageText(message.getMessageText());
            dto.setMessageSentAt(String.valueOf(message.getMessageSentAt()));
            dto.setAttachmentUrls(attachmentsMapper.getAttachmentsByMessageId(message.getMessageId()));
            return dto;
        }).collect(Collectors.toList());
    }

    @Override
    public void sendPrivateMessage(ScPrivateMessagesDTO privateMessageDTO) {
        ScPrivateMessages message = new ScPrivateMessages();
        message.setSenderId(privateMessageDTO.getSenderId());
        message.setReceiverId(privateMessageDTO.getReceiverId());
        message.setMessageText(privateMessageDTO.getMessageText());
        privateMessagesMapper.insertPrivateMessage(message);

        if (privateMessageDTO.getAttachmentUrls() != null) {
            for (String url : privateMessageDTO.getAttachmentUrls()) {
                ScPrivateMessageAttachments attachment = new ScPrivateMessageAttachments();
                attachment.setMessageId(message.getMessageId());
                attachment.setAttachmentUrl(url);
                attachmentsMapper.insertAttachment(attachment);
            }
        }
    }
}