package online.xzjob.schoolwall.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PendingFriendRequestDTO {
    private Integer userId;
    private Integer friendId;
    private String friendName;
    private LocalDateTime createdAt;
}