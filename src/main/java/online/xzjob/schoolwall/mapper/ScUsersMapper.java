package online.xzjob.schoolwall.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import online.xzjob.schoolwall.entity.ScUsers;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/*  Mapper 它主要用于定义与数据库交互的方法，如查询、插入、更新和删除操作。
Mapper 接口通常与 XML 文件或注解一起使用，以提供 SQL 语句和映射规则。 */

@Mapper   //标记为Mybatis的 Mapper 接口
public interface ScUsersMapper extends BaseMapper<ScUsers> {

}
