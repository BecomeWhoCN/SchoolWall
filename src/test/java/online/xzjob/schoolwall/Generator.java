package online.xzjob.schoolwall;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class Generator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/scwall?characterEncoding=utf-8",
                        "root", "123456")
                .globalConfig(builder -> builder
                        .author("熊峥")
                        .outputDir("G:\\SchoolWall_demo\\schoolwall\\src\\main\\java")
                )
                .packageConfig(builder -> builder
                        .parent("online.xzjob.schoolwall")
                        .entity("entity")
                        .mapper("mapper")
                        .service("service")
                        .serviceImpl("service.impl")
                        .xml("mapper.xml")
                )
                .strategyConfig(builder -> builder
                        .entityBuilder()
                        .enableLombok()
                )
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}