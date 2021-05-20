package cn.edu.scau.ticket.application.config;

import cn.edu.scau.ticket.application.handler.DateFormatterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/20
 */
@Configuration
public class MyWebAppConfigurer implements WebMvcConfigurer {

    @Autowired
    private DateFormatterFactory dateFormatterFactory;

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatterForFieldAnnotation(dateFormatterFactory);
    }
}
