package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.dto.ScBottleDTO;
import online.xzjob.schoolwall.dto.ScBottleReplyDTO;
import online.xzjob.schoolwall.entity.ScDriftBottleReply;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import online.xzjob.schoolwall.dto.ScReportedDriftBottlesDTO;
import online.xzjob.schoolwall.entity.ScDriftBottles;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * Mapper 接口
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

    @Select("SELECT bottle_id, user_id, user_gender, bottle_title, bottle_content_url, bottle_created_at, bottle_attachment_url " +
            "FROM sc_drift_bottles "  +
            "WHERE user_id = #{userId} OR bottle_id IN (SELECT bottle_id FROM sc_drift_bottle_taken WHERE user_id = #{userId}) "+
            "LIMIT #{offset}, #{pageSize}")
    List<ScDriftBottles> findPublishedBottles(@Param("userId") int userId, @Param("offset") int offset, @Param("pageSize") int pageSize);

    @Select("SELECT COUNT(*) " +
            "FROM sc_drift_bottles " +
            "WHERE user_id = #{userId} OR bottle_id IN (SELECT bottle_id FROM sc_drift_bottle_taken WHERE user_id = #{userId})")
    int countPublishedBottles(@Param("userId") int userId);

    @Delete("DELETE FROM sc_drift_bottles WHERE bottle_id = #{bottleId}")
    int deleteBottleById(@Param("bottleId") int bottleId);

    @Insert("INSERT INTO sc_drift_bottle_replies (bottle_id, user_id, reply_content, reply_created_at) VALUES (#{bottle_id}, #{user_id}, #{reply_content}, #{reply_created_at})")
    int insert(ScDriftBottleReply reply);

    @Select("SELECT * FROM sc_drift_bottle_replies WHERE bottle_id = #{bottleId}")
    @Results({
            @Result(property = "reply_id", column = "reply_id"),
            @Result(property = "bottle_id", column = "bottle_id"),
            @Result(property = "user_id", column = "user_id"),
            @Result(property = "reply_content", column = "reply_content"),
            @Result(property = "reply_created_at", column = "reply_created_at")
    })
    List<ScDriftBottleReply> selectByBottleId(@Param("bottleId") int bottleId);
    @Select("SELECT u.user_name, d.bottle_title,d.bottle_id FROM sc_users u JOIN sc_drift_bottles d ON u.user_id = d.user_id WHERE d.bottle_status = 'inactive'ORDER BY d.bottle_id ASC LIMIT #{pageSize} OFFSET #{offset}")
    List<ScReportedDriftBottlesDTO> findAllReportedPosts(int offset, int pageSize);

    @Select("SELECT COUNT(*) FROM sc_drift_bottles WHERE bottle_status = 'inactive'")
    int countTotalReportedPosts();
    @Update("UPDATE sc_drift_bottles SET bottle_status = 'published' WHERE bottle_id = #{bottleId} AND bottle_status = 'inactive'")
    int republish(Integer bottleId);
    @Delete("DELETE FROM sc_drift_bottles WHERE bottle_id = #{bottleId};")
    int delete(Integer bottleId);
}
