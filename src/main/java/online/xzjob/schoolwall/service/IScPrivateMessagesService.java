package online.xzjob.schoolwall.service;

import online.xzjob.schoolwall.dto.ScPrivateMessagesDTO;

import java.util.List;

public interface IScPrivateMessagesService {
    List<ScPrivateMessagesDTO> getPrivateMessages(Integer userId1, Integer userId2);
    void sendPrivateMessage(ScPrivateMessagesDTO privateMessageDTO);
}
