package online.xzjob.schoolwall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
