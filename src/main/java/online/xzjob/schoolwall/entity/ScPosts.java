package online.xzjob.schoolwall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;

/**
 * <p>
 * 
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("sc_posts")
public class ScPosts implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;

    private Integer userId;

    private String postTitle;

    private String postSummary;

    private String postContentUrl;

    private String postHeaderImageUrl;

    private String postStatus;

    private LocalDateTime postCreatedAt;

    private LocalDateTime postUpdatedAt;

    private Integer groupId;
}
