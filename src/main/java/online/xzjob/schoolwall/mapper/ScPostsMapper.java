package online.xzjob.schoolwall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
}
