package online.xzjob.schoolwall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@TableName("sc_user_avatars")
public class ScUserAvatars implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "avatar_id", type = IdType.AUTO)
    private Integer avatarId;

    private Integer userId;

    private String avatarUrl;

}
