package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.xzjob.schoolwall.util.QiniuUploadService;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
