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
@TableName("sc_group_invitations")
public class ScGroupInvitations implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "invitation_id", type = IdType.AUTO)
    private Integer invitationId;

    private Integer groupId;

    private Integer inviterId;

    private Integer inviteeId;

    private String status;

    private LocalDateTime invitedAt;
}
