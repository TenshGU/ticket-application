package cn.edu.scau.ticket.application.mapper;

import cn.edu.scau.ticket.application.beans.UserAuthority;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Set;

@Mapper
public interface AuthMapper {
    List<Integer> getGroupIdsByRoleName(Set<UserAuthority> authorities);
}
