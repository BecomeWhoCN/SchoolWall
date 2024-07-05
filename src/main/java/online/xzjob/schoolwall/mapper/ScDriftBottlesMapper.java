package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.xzjob.schoolwall.util.QiniuUploadService;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lpl
 * @since 2024-06-26
 */
@Repository
public interface ScDriftBottlesMapper extends BaseMapper<ScDriftBottles> {
    @Insert("INSERT INTO sc_drift_bottles" +
            "        (user_id,user_gender,bottle_title,bottle_content_url,bottle_created_at)" +
            "        VALUES" +
            "        (#{user_id},#{user_gender},#{bottle_title},#{bottle_content_url},#{bottle_created_at})")
    abstract int saveDriftBottles(ScBottleDTO scBottleDTO);

    @Select("SELECT * FROM sc_drift_bottles ORDER BY RAND() LIMIT 3")
    @Results({
            @Result(column = "bottle_id", property = "bottleId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "user_gender", property = "userGender"),
            @Result(column = "bottle_title", property = "bottleTitle"),
            @Result(column = "bottle_content_url", property = "bottleContentUrl"),
            @Result(column = "bottle_attachment_url", property = "bottleAttachmentUrl"),
            @Result(column = "bottle_created_at", property = "bottleCreatedAt")
    })
    List<ScDriftBottles> findRandomDriftBottles();
}
