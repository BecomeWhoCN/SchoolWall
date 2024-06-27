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
@TableName("sc_group_admins")
public class ScGroupAdmins implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("group_id")
    private Integer groupId;

    private Integer userId;

    private String adminRole;

    private LocalDateTime adminCreatedAt;
}
