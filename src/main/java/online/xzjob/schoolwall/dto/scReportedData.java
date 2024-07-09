package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class scReportedData {
    private String userId;
    private String postId;
    private String value;
    private long time;
}
