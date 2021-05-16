package cn.edu.scau.ticket.application;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
/**
 * 注意：此项目并暂时并没有处理一些异常以及并发请求，也就是项目支持 基于正常非恶意行为下的请求
 * 后续将补充这部分内容
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableCaching
@EnableEncryptableProperties
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
