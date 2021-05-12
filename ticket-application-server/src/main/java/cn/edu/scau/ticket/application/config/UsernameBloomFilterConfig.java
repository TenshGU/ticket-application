package cn.edu.scau.ticket.application.config;

import cn.edu.scau.ticket.application.mapper.UserMapper;
import org.redisson.Redisson;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/11
 */
@Configuration
@ConditionalOnClass(Redisson.class)
public class UsernameBloomFilterConfig {

    @Value("${bloomFilter.username.expectedInsertions}")
    private Long expectedInsertions;

    @Value("${bloomFilter.username.falseProbability}")
    private Double falseProbability;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private UserMapper userMapper;

    @Bean
    public RBloomFilter<String> usernameBloomFilter() {
        RBloomFilter<String> usernameBloomFilter = redissonClient.getBloomFilter("usernameList");
        usernameBloomFilter.tryInit(expectedInsertions,falseProbability);
        List<String> allUsernames = userMapper.getAllUsernames();
        for (String username : allUsernames) {
            usernameBloomFilter.add(username);
        }
        return usernameBloomFilter;
    }
}
