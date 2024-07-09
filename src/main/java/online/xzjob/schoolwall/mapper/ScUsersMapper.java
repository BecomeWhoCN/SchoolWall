package online.xzjob.schoolwall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.xzjob.schoolwall.dto.ScUserDTO;
import online.xzjob.schoolwall.dto.ScUserSetting;
import online.xzjob.schoolwall.entity.ScUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/*  Mapper 它主要用于定义与数据库交互的方法，如查询、插入、更新和删除操作。
Mapper 接口通常与 XML 文件或注解一起使用，以提供 SQL 语句和映射规则。 */

@Mapper   //标记为Mybatis的 Mapper 接口
public interface ScUsersMapper extends BaseMapper<ScUsers> {

    @Select("SELECT id, username, email FROM sc_users WHERE id = #{id} ")
    ScUserDTO selectUserByIdWithoutPassword(Integer id);

    @Select("select user_id,user_name,user_role from sc_users")
    List<ScUserSetting> selectAllUser();

    @Select("SELECT COUNT(*) FROM sc_users ")
    int countTotalUsers();

    @Select("SELECT user_id, user_name, user_role FROM sc_users LIMIT #{pageSize} OFFSET #{offset}")
    List<ScUserSetting> findAllUsers(int offset, int pageSize);

    @Update("UPDATE sc_users SET user_role = 'admin' WHERE user_id = #{id} AND user_role = 'user';\n")
    int increasePermission(Integer id);
    @Update("UPDATE sc_users SET user_role = 'user' WHERE user_id = #{id} AND user_role = 'admin';\n")
    int decreasePermission(Integer id);
}
