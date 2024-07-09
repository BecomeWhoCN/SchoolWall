package online.xzjob.schoolwall.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScNewsDataDTO {
    private Integer postId;//帖子表
    private String imageURL;//帖子表
    private String title;//帖子表
    private String contentURL;//帖子表
    private String author;//用户表
    private String authorAvatarURL;//用户头像表
    private String date;//帖子表
    private Integer comments;//帖子评论表
    private Integer likes;//帖子点赞表
    private String summary;//帖子表
}