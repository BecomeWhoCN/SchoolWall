package online.xzjob.schoolwall.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName(value = "sc_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScUsers implements Serializable {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    @TableField(value = "user_name")
    private String userName;

    @TableField(value = "user_email")
    private String userEmail;

    @TableField(value = "user_password_hash")
    private String userPasswordHash;

    @TableField(value = "user_role")
    private String userRole;

    @TableField(value = "user_bio")
    private String userBio;

    @TableField(value = "user_class")
    private String userClass;

    @TableField(value = "user_phone")
    private String userPhone;

    @TableField(value = "user_gender")
    private String userGender;

    @TableField(value = "user_online_status")
    private String userOnlineStatus;

    @TableField(fill = FieldFill.INSERT, value = "user_last_active")
    private Timestamp userLastActive;

    @TableField(fill = FieldFill.INSERT_UPDATE, value = "user_created_at")
    private Timestamp userCreatedAt;

    @TableField(value = "user_status")
    private String userStatus;
}
