package online.xzjob.schoolwall.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ScDriftBottleReply {
    private Integer reply_id;
    private Integer bottle_id;
    private Integer user_id;
    private String reply_content;
    private Date reply_created_at;
}
