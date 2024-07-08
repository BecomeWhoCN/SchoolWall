package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserSearchResult {
    private Integer userId;
    private String userName;
    private String avatarUrl;

    // Getters and Setters
}