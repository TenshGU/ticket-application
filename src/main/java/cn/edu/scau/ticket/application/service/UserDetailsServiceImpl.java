package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/2
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 从数据库中查找用户
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    //@CachePut
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userMapper.getUserByUsername(username);
    }
}
