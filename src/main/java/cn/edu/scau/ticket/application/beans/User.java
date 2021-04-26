package cn.edu.scau.ticket.application.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements UserDetails {

    private Integer id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private String email;
    private String username;
    private String password;
    private String image;
    private List<UserAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
