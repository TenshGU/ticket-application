package cn.edu.scau.ticket.application.service.impl;

import cn.edu.scau.ticket.application.beans.UserAuthority;
import cn.edu.scau.ticket.application.mapper.AuthMapper;
import cn.edu.scau.ticket.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.boot.autoconfigure.context.PropertyPlaceholderAutoConfiguration;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/5/9
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    /**
     * 获取权限组id
     * @param authorities 角色名
     * @return 组id的集合
     */
    @Cacheable(value = "cache", key = "'groupIds'")
    @Override
    public List<Integer> getGroupIdsByRoleName(Set<UserAuthority> authorities) {
        return authMapper.getGroupIdsByRoleName(authorities);
    }
}
