package cn.edu.scau.ticket.application.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("user")
public class User implements UserDetails {
    @NotNull(message = "用户名不能为空")
    @Length(min = 6, max = 10, message = "用户名长度不正确")
    @Pattern(regexp = "^[0-9a-zA-Z]{6,10}$", message = "用户名格式不正确")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 12, message = "密码长度不正确")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,12}$", message = "密码格式不正确")
    private String password;

    @NotNull(message = "手机号码不能为空")
    @Length(min = 11, max = 11, message = "手机号码长度不正确")
    private String phone;

    @NotNull(message = "名不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]{1,10}$", message = "名格式不对")
    private String firstName;

    @NotNull(message = "姓不能为空")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9]{1,10}$", message = "姓格式不对")
    private String lastName;

    @NotNull(message = "性别不能为空")
    @Pattern(regexp = "^[MF]{1}$", message = "性别格式错误")
    private String gender;

    @NotNull(message = "年龄不能为空")
    @Min(value = 10, message = "年龄范围不对")
    @Max(value = 150, message = "年龄范围不对")
    private Integer age;

    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确")
    private String email;

    private String image;
    private List<Integer> groupIds;
    private Set<UserAuthority> authorities;

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
