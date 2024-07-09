package online.xzjob.schoolwall.mapper;

import online.xzjob.schoolwall.dto.ScPostsDTO;
import online.xzjob.schoolwall.entity.ScPosts;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import online.xzjob.schoolwall.dto.ScNewsDataDTO;
import online.xzjob.schoolwall.dto.ScReportedPostDTO;
import online.xzjob.schoolwall.entity.ScPosts;
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
 * @author 熊峥
 * @since 2024-06-26
 */
@Mapper
public interface ScPostsMapper extends BaseMapper<ScPosts> {
    @Select("SELECT u.user_name, p.post_title,p.post_id FROM sc_users u JOIN sc_posts p ON u.user_id = p.user_id WHERE p.post_status = 'inactive'ORDER BY p.post_id ASC LIMIT #{pageSize} OFFSET #{offset}")
    List<ScReportedPostDTO> findAllReportedPosts(int offset, int pageSize);

    @Select("SELECT COUNT(*) FROM sc_posts WHERE post_status = 'inactive'")
    int countTotalReportedPosts();

    @Update("UPDATE sc_posts SET post_status = 'draft' WHERE post_id = #{postId} AND post_status = 'inactive'")
    int sendToDraft(Integer postId);

    @Update("UPDATE sc_posts SET post_status = 'published' WHERE post_id = #{postId} AND post_status = 'inactive'")
    int republish(Integer postId);

    @Delete("DELETE FROM sc_posts WHERE post_id = #{postId};")
    int delete(Integer postId);

    @Select("SELECT \n" +
            "    p.post_id AS postId,\n" +
            "    p.post_header_image_url AS imageURL,\n" +
            "    p.post_title AS title,\n" +
            "    p.post_content_url AS contentURL,\n" +
            "    p.post_summary AS summary,\n" +
            "    u.user_name AS author,\n" +
            "    ua.avatar_url AS authorAvatarURL,\n" +
            "    p.post_updated_at AS date,\n" +
            "    (SELECT COUNT(*) FROM sc_post_comments c WHERE c.post_id = p.post_id) AS comments,\n" +
            "    (SELECT COUNT(*) FROM sc_post_votes v WHERE v.post_id = p.post_id) AS likes\n" +
            "FROM \n" +
            "    sc_posts p\n" +
            "JOIN \n" +
            "    sc_users u ON p.user_id = u.user_id\n" +
            "JOIN \n" +
            "    sc_user_avatars ua ON u.user_id = ua.user_id\n" +
            "WHERE \n" +
            "    u.user_role = 'admin'\n" +
            "ORDER BY \n" +
            "    p.post_updated_at DESC\n" +
            "LIMIT 2;\n")
    List<ScNewsDataDTO> findNewsData();

    @Select("SELECT \n" +
            "    p.post_id AS postId,\n" +
            "    p.post_header_image_url AS imageURL,\n" +
            "    p.post_title AS title,\n" +
            "    p.post_content_url AS contentURL,\n" +
            "    p.post_summary AS summary,\n" +
            "    u.user_name AS author,\n" +
            "    ua.avatar_url AS authorAvatarURL,\n" +
            "    p.post_updated_at AS date,\n" +
            "    (SELECT COUNT(*) FROM sc_post_comments c WHERE c.post_id = p.post_id) AS comments,\n" +
            "    (SELECT COUNT(*) FROM sc_post_votes v WHERE v.post_id = p.post_id) AS likes\n" +
            "FROM \n" +
            "    sc_posts p\n" +
            "JOIN \n" +
            "    sc_users u ON p.user_id = u.user_id\n" +
            "JOIN \n" +
            "    sc_user_avatars ua ON u.user_id = ua.user_id\n" +
            "WHERE \n" +
            "    u.user_role = 'user'\n" +
            "ORDER BY \n" +
            "    p.post_updated_at DESC\n" +
            "LIMIT 2;\n")
    List<ScNewsDataDTO> findArticlesData();

    @Select("SELECT \n" +
            "    p.post_id AS postId,\n" +
            "    p.post_header_image_url AS imageURL,\n" +
            "    p.post_title AS title,\n" +
            "    p.post_content_url AS contentURL,\n" +
            "    p.post_summary AS summary,\n" +
            "    u.user_name AS author,\n" +
            "    ua.avatar_url AS authorAvatarURL,\n" +
            "    p.post_updated_at AS date,\n" +
            "    (SELECT COUNT(*) FROM sc_post_comments c WHERE c.post_id = p.post_id) AS comments,\n" +
            "    (SELECT COUNT(*) FROM sc_post_votes v WHERE v.post_id = p.post_id) AS likes\n" +
            "FROM \n" +
            "    sc_posts p\n" +
            "JOIN \n" +
            "    sc_users u ON p.user_id = u.user_id\n" +
            "JOIN \n" +
            "    sc_user_avatars ua ON u.user_id = ua.user_id\n" +
            "WHERE \n" +
            "    u.user_role = 'user'\n" +
            "ORDER BY \n" +
            "    p.post_updated_at DESC\n")
    List<ScNewsDataDTO> findAllArticlesData(int offset, int pageSize);

    @Select("SELECT COUNT(*) AS result_count\n" +
            "FROM (\n" +
            "    SELECT \n" +
            "        p.post_id AS postId,\n" +
            "        p.post_header_image_url AS imageURL,\n" +
            "        p.post_title AS title,\n" +
            "        p.post_content_url AS contentURL,\n" +
            "        p.post_summary AS summary,\n" +
            "        u.user_name AS author,\n" +
            "        ua.avatar_url AS authorAvatarURL,\n" +
            "        p.post_updated_at AS date,\n" +
            "        (SELECT COUNT(*) FROM sc_post_comments c WHERE c.post_id = p.post_id) AS comments,\n" +
            "        (SELECT COUNT(*) FROM sc_post_votes v WHERE v.post_id = p.post_id) AS likes\n" +
            "    FROM \n" +
            "        sc_posts p\n" +
            "    JOIN \n" +
            "        sc_users u ON p.user_id = u.user_id\n" +
            "    JOIN \n" +
            "        sc_user_avatars ua ON u.user_id = ua.user_id\n" +
            "    WHERE \n" +
            "        u.user_role = 'user'\n" +
            ") AS result;\n")
    int countTotalAllArticlesData();
    @Select("SELECT \n" +
            "    p.post_id AS postId,\n" +
            "    p.post_header_image_url AS imageURL,\n" +
            "    p.post_title AS title,\n" +
            "    p.post_content_url AS contentURL,\n" +
            "    p.post_summary AS summary,\n" +
            "    u.user_name AS author,\n" +
            "    ua.avatar_url AS authorAvatarURL,\n" +
            "    p.post_updated_at AS date,\n" +
            "    (SELECT COUNT(*) FROM sc_post_comments c WHERE c.post_id = p.post_id) AS comments,\n" +
            "    (SELECT COUNT(*) FROM sc_post_votes v WHERE v.post_id = p.post_id) AS likes\n" +
            "FROM \n" +
            "    sc_posts p\n" +
            "JOIN \n" +
            "    sc_users u ON p.user_id = u.user_id\n" +
            "JOIN \n" +
            "    sc_user_avatars ua ON u.user_id = ua.user_id\n" +
            "WHERE \n" +
            "    u.user_role = 'admin'\n" +
            "ORDER BY \n" +
            "    p.post_updated_at DESC\n")
    List<ScNewsDataDTO> findAllNewsData(int offset, int pageSize);
    @Select("SELECT COUNT(*) AS result_count\n" +
            "FROM (\n" +
            "    SELECT \n" +
            "        p.post_id AS postId,\n" +
            "        p.post_header_image_url AS imageURL,\n" +
            "        p.post_title AS title,\n" +
            "        p.post_content_url AS contentURL,\n" +
            "        p.post_summary AS summary,\n" +
            "        u.user_name AS author,\n" +
            "        ua.avatar_url AS authorAvatarURL,\n" +
            "        p.post_updated_at AS date,\n" +
            "        (SELECT COUNT(*) FROM sc_post_comments c WHERE c.post_id = p.post_id) AS comments,\n" +
            "        (SELECT COUNT(*) FROM sc_post_votes v WHERE v.post_id = p.post_id) AS likes\n" +
            "    FROM \n" +
            "        sc_posts p\n" +
            "    JOIN \n" +
            "        sc_users u ON p.user_id = u.user_id\n" +
            "    JOIN \n" +
            "        sc_user_avatars ua ON u.user_id = ua.user_id\n" +
            "    WHERE \n" +
            "        u.user_role = 'admin'\n" +
            ") AS result;\n")
    int countTotalAllNewsData();


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
