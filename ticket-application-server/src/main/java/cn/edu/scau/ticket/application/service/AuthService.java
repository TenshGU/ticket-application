package cn.edu.scau.ticket.application.service;

import cn.edu.scau.ticket.application.beans.UserAuthority;
import java.util.List;
import java.util.Set;

public interface AuthService {
    List<Integer> getGroupIdsByRoleName(Set<UserAuthority> authorities);
}
