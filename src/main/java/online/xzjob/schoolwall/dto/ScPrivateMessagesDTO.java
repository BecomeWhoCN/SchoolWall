package online.xzjob.schoolwall.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScPrivateMessagesDTO {
    private Integer messageId;
    private Integer senderId;
    private Integer receiverId;
    private String messageText;
    private String messageSentAt;
    private List<String> attachmentUrls;
}