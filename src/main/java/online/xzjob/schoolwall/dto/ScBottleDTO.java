package online.xzjob.schoolwall.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ScBottleDTO {
    @JsonIgnore
    private MultipartFile file;
    private Integer bottle_id;
    private Integer user_id;
    private String user_gender;
    private String bottle_title;
    private String bottle_content_url;
    private Date bottle_created_at;
    private String bottle_attachment_url;
    private String bottle_content;// 新增字段，用于存储从url获取的内容
    private String user_name;
}
