package com.happy.ssmdemo;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
/* 自定义Spring Boot内嵌的Tomcat配置
 */
@Configuration
public class MyServerConfig {
    @Bean
    public ConfigurableServletWebServerFactory configurableServletWebServerFactory(){
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.setPort(8866);                              // 修改端口
        factory.setUriEncoding(Charset.forName("UTF-8"));   // 指定字符编码
        return factory;
    }
}
