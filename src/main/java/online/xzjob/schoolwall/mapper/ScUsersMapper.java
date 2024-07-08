package online.xzjob.schoolwall.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.dto.UserSearchResult;
import online.xzjob.schoolwall.entity.ScUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/*  Mapper 它主要用于定义与数据库交互的方法，如查询、插入、更新和删除操作。
Mapper 接口通常与 XML 文件或注解一起使用，以提供 SQL 语句和映射规则。 */

@Mapper   //标记为Mybatis的 Mapper 接口
public interface ScUsersMapper extends BaseMapper<ScUsers> {

    @Select("SELECT id, username, email FROM sc_users WHERE id = #{id}")
    ScUserDTO selectUserByIdWithoutPassword(Integer id);

    @Select("SELECT u.user_id, u.user_name, a.avatar_url " +
            "FROM sc_users u LEFT JOIN sc_user_avatars a ON u.user_id = a.user_id " +
            "WHERE u.user_name LIKE CONCAT('%', #{query}, '%') OR u.user_email LIKE CONCAT('%', #{query}, '%')")
    List<UserSearchResult> selectUserSearchResults(@Param("query") String query);
}
