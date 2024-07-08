package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScReportedDriftBottlesDTO {
    private Integer BottleId;
    private String userName;
    private String BottleTitle;
}
