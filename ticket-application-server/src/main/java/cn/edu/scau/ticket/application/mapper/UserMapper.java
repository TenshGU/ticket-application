package cn.edu.scau.ticket.application.mapper;

import cn.edu.scau.ticket.application.beans.User;
import cn.edu.scau.ticket.application.beans.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

@Mapper
public interface UserMapper {
    UserDetails getUserByUsername(String username);

    int saveUser(User user);

    Set<UserAuthority> getUserAuthoritiesByUsername(String username);

    List<String> getAllUsernames();
}
