package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScBottleReplyDTO {
    private Integer bottle_id;
    private Integer user_id;
    private String reply_content;
    private String user_avatar;
    private String user_name;
    private Date reply_created_at;


}
