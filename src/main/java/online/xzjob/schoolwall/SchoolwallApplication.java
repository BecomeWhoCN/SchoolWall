package online.xzjob.schoolwall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 启动类定义
@MapperScan("online.xzjob.schoolwall.mapper")
public class SchoolwallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolwallApplication.class, args);
	}

}
