package online.xzjob.schoolwall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // 启动类定义
@MapperScan(value = "online.xzjob.schoolwall.mapper")  //指定扫描的包路径
public class SchoolwallApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolwallApplication.class, args);
	}

}
