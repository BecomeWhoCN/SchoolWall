package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScFriendsDTO {
    private Integer userId;
    private Integer friendId;
    private String friendNickname;
    private String avatarUrl;
}