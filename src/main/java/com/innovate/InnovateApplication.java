package com.innovate;

import com.innovate.datasources.DynamicDataSourceConfig;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@Import({DynamicDataSourceConfig.class})
public class InnovateApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(InnovateApplication.class, args);
		//指定jre系统属性，允许 特殊符号  | 做入参   详情见 tomcat  HttpParser类
		System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow","|{}[]_");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(InnovateApplication.class);
	}
}
