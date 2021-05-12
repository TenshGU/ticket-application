package cn.edu.scau.ticket.application.config;

import cn.edu.scau.ticket.application.config.properties.ReleaseProperties;
import cn.edu.scau.ticket.application.handler.security.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @description:
 * @author: Tensh
 * @createDate: 2021/4/2
 */
@Configuration
@EnableConfigurationProperties(ReleaseProperties.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ReleaseProperties releaseProperties;

    /**
     * 权限不足处理器
     */
    @Autowired
    private AccessDeniedHandler webAccessDeniedHandler;

    /**
     * 未登录处理器
     */
    @Autowired
    private AuthenticationEntryPoint webAuthenticationEntryPoint;

    /**
     * 登录成功处理器
     */
    @Autowired
    private AuthenticationSuccessHandler webAuthenticationSuccessHandler;

    /**
     * 登录失败处理器
     */
    @Autowired
    private AuthenticationFailureHandler webAuthenticationFailureHandler;

    /**
     * 登出成功处理器
     */
    @Autowired
    private LogoutSuccessHandler webLogoutSuccessHandler;

    @Autowired
    private UserDetailsService userDetailsServiceImpl;

    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        http
                .csrf().disable()
                //不再生成session，而是使用token
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                .and()
                .authorizeRequests()
                /*放行某些url访问
                注解负责权限
                配置文件负责匿名访问
                 */
                .antMatchers(releaseProperties.getReleasePath()).permitAll()
                .anyRequest().authenticated()

                //开启登录
                .and()
                .formLogin()
                .loginProcessingUrl("/login")
                .successHandler(webAuthenticationSuccessHandler)
                .failureHandler(webAuthenticationFailureHandler)
                .permitAll()

                //登出
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler(webLogoutSuccessHandler)
                .permitAll()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(webAuthenticationEntryPoint)
                .accessDeniedHandler(webAccessDeniedHandler);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    /**
     * @return 密码编码器
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
