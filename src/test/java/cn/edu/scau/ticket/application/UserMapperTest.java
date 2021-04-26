package cn.edu.scau.ticket.application;

import cn.edu.scau.ticket.application.beans.UserAuthority;
import cn.edu.scau.ticket.application.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/26
 */
@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetUserById() {
        UserDetails user = userMapper.getUserByUsername("admin");
        System.out.println(user);
    }
}
