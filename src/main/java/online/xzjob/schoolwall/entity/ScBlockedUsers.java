package online.xzjob.schoolwall.entity;

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
@TableName("sc_blocked_users")
public class ScBlockedUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("blocker_id")
    private Integer blockerId;

    private Integer blockedUserId;

    private LocalDateTime blockCreatedAt;
}
