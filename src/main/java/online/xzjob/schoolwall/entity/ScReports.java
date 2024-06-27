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
@TableName("sc_reports")
public class ScReports implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "report_id", type = IdType.AUTO)
    private Integer reportId;

    private Integer reporterId;

    private Integer reportedUserId;

    private String reportReason;

    private LocalDateTime reportCreatedAt;

    private String reportStatus;
}
