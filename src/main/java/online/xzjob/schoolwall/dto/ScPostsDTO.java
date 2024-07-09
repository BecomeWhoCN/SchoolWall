package online.xzjob.schoolwall.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class ScPostsDTO {

    @JsonIgnore
    private MultipartFile editorContent;
    @JsonIgnore
    private MultipartFile imageFile;
    @TableId(value = "post_id", type = IdType.AUTO)
    private Integer postId;
    private String postSummary;
    private String userName;
    private String postTitle;
    private String postStatus;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date postCreatedAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date postUpdateTime;
    private String scUserAvatars;
    private String postContentUrl;

    private String postHeaderImageUrl;


}
