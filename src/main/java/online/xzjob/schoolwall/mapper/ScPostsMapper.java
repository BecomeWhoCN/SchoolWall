package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.dto.ScPostsDTO;
import online.xzjob.schoolwall.entity.ScPosts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 熊峥
 * @since 2024-06-26
 */
@Mapper
public interface ScPostsMapper extends BaseMapper<ScPosts> {


    @Insert("INSERT INTO sc_posts(user_id, post_title, post_content_url, post_header_image_url, post_status, post_created_at, post_updated_at, group_id, post_summary) " +
            "VALUES (#{userId}, #{postTitle}, #{postContentUrl}, #{postHeaderImageUrl}, #{postStatus}, NOW(), NOW(), #{groupId}, #{postSummary})")
    int saveArticle(ScPosts scPosts);

    @Select("SELECT * FROM sc_posts WHERE user_id = #{userId} AND post_status='draft'")
    List<ScPosts> selectDraftAll(Integer userId);
    @Select("SELECT * FROM sc_posts WHERE user_id = #{userId} AND post_status<>'draft'")
    List<ScPosts> selectArticleAll(Integer userId);
    @Update("UPDATE sc_posts SET post_status='draft',post_updated_at=NoW() WHERE post_id=#{postId}")
    int backDraft(Integer postId);
     @Update("UPDATE sc_posts SET post_status='published',post_updated_at=NoW() WHERE post_id=#{postId} ")
    int releaseArticle(Integer postId);

    @Delete("DELETE FROM sc_posts WHERE post_id=#{postId}")
    int deleteArticle(Integer postId);
    @Update("UPDATE sc_posts SET post_title=#{postTitle},post_content_url=#{postContentUrl},post_header_image_url=#{postHeaderImageUrl},post_summary=#{postSummary},post_updated_at=NoW(),post_status=#{postStatus} WHERE post_id=#{postId}")
    int updatearticle(ScPosts scPosts);


    @Select("SELECT  " +
            "    scp.post_id, " +
            "    scp.post_header_image_url, " +
            "    scp.post_title, " +
            "    scp.post_summary, " +
            "    scp.post_content_url, " +
            "    scp.post_created_at, " +
            "    scu.user_name, " +
            "    sca.avatar_url AS sc_user_avatars " +
            "FROM  " +
            "    sc_posts AS scp " +
            "JOIN  " +
            "    sc_users AS scu ON scp.user_id = scu.user_id " +
            "JOIN  " +
            "    sc_user_avatars AS sca ON scp.user_id = sca.user_id " +
            "WHERE  " +
            "    scp.post_id = #{postId}")
    ScPostsDTO selectOne(Integer postId);
}
