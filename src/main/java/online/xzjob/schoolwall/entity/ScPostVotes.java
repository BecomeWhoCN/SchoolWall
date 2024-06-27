package online.xzjob.schoolwall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Getter
@Setter
@TableName("sc_post_votes")
public class ScPostVotes implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "vote_id", type = IdType.AUTO)
    private Integer voteId;

    private Integer postId;

    private Integer userId;

    private String voteType;

    private LocalDateTime voteCreatedAt;
}
