package online.xzjob.schoolwall;

import online.xzjob.schoolwall.entity.ScUsers;
import online.xzjob.schoolwall.mapper.ScUsersMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@MapperScan(value = "online.xzjob.schoolwall.mapper")
class SchoolwallApplicationTests {

	@Autowired
	private ScUsersMapper scUsersMapper;

	@Test
	void testInsert() {
		ScUsers sc_users = ScUsers.builder()
				.userName("admin2")
				.userEmail("123456@163.com")
				.userPasswordHash("123456")
				.userRole("user")
				.userBio("Hello, I am a test user")
				.userClass("class2")
				.userPhone("1234567890")
				.userGender("male")
				.userOnlineStatus("online")
				.userStatus("active")
				.build();
		scUsersMapper.insert(sc_users);
	}

	@Test
	void testSelect1() {
		ScUsers scUsers = scUsersMapper.selectById(1); // 使用注入的实例调用方法
		System.out.println(scUsers.getUserName());
	}

}
