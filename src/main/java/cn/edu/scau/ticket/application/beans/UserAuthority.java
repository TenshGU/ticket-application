package cn.edu.scau.ticket.application.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("userAuthority")
public class UserAuthority implements GrantedAuthority {

    private String authority;

    @Override
    public String getAuthority() {
        return authority;
    }
}
