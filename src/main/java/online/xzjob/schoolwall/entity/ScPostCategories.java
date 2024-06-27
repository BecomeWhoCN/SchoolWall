package online.xzjob.schoolwall.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("sc_post_categories")
public class ScPostCategories implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("post_id")
    private Integer postId;

    private Integer categoryId;
}
