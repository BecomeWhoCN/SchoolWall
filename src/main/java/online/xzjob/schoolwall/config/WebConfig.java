package online.xzjob.schoolwall.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 允许所有路径
                .allowedOriginPatterns("http://*","https://*")  // 使用patterns替代origins
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 允许的请求方法
                .allowedHeaders("*")  // 允许的请求头
                .allowCredentials(true)  // 允许携带凭证
                .exposedHeaders("Authorization", "Link", "X-Total-Count")  // 显式地暴露特定的头部
                .maxAge(3600);  // 预检请求的缓存时间
    }
}