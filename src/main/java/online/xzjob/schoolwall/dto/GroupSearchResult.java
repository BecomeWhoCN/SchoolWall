package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.security.Timestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupSearchResult {
    private Integer groupId;
    private String groupName;
    private Timestamp groupCreatedAt;

    // Getters and Setters
}