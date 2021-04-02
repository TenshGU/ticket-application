package cn.edu.scau.ticket.application.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/2
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * 从数据库中查找用户
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return User.builder().build();
    }
}
