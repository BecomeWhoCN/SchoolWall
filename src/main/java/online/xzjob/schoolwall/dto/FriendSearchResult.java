package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FriendSearchResult {
    private Integer friendId;
    private String friendName;
    private String avatarUrl;
}