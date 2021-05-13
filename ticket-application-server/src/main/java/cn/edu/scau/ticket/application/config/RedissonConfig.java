package cn.edu.scau.ticket.application.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/11
 */
@Configuration
public class RedissonConfig {
    @Value("${spring.redisson.address}")
    private String address;

    @Value("${spring.redisson.password}")
    private String password;

    @Bean
    public Redisson redissonClient() {
        Config config = new Config();
        config.useSingleServer().setAddress(address);
        config.useSingleServer().setPassword(password);
        return (Redisson) Redisson.create(config);
    }
}