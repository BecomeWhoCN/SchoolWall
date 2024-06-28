package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScUserDTO {
    private Integer userId;
    private String userEmail;
    private String userRole;

    private String userName;
    private String userBio;
    private String userPhone;
    private String userGender;
    private String userClass;

    private String userOnlineStatus;

    private String userAvatar;

    private String scUserAvatars;
}
