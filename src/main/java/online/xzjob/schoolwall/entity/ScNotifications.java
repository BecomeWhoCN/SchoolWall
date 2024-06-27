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
@TableName("sc_notifications")
public class ScNotifications implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "notification_id", type = IdType.AUTO)
    private Integer notificationId;

    private Integer userId;

    private String notificationType;

    private String notificationText;

    private String notificationReadStatus;

    private LocalDateTime notificationCreatedAt;
}
