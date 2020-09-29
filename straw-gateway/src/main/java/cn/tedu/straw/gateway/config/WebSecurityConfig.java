package cn.tedu.straw.gateway.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.annotation.Resource;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 登录页面的URL
        String loginPageUrl = "/login.html";
        // 处理登录的URL，也就是提交用户名、密码到哪个位置
        // 默认情况下Spring Security的 /login 路径，当请求方式是GET时，表示访问登录页面，当请求方式是POST时，是处理登录请求
        String loginProcessingUrl = "/login";
        // 登录失败后重定向到的URL
        String loginFailureUrl = "/login.html?error";
        // 登录成功后重定向到的URL
        String loginSccessUrl = "/index.html";
        // 退出登录的URL
        String logoutUrl = "/logout";
        // 退出登录成功后重定向到的URL
        String logoutSuccessUrl = "/login.html?logout";
        // ----------------------------------
        // ----------------------------------
        // ----------------------------------
        // 对所有请求进行授权
        http.authorizeRequests().anyRequest().permitAll();
        // 关闭跨域攻击
        http.csrf().disable();
        // 使用表单验证登录
        http.formLogin()
                .loginPage(loginPageUrl)
                .loginProcessingUrl(loginProcessingUrl)
                .failureUrl(loginFailureUrl)
                .defaultSuccessUrl(loginSccessUrl);
        // 配置退出登录
        http.logout()
                .logoutUrl(logoutUrl)
                .logoutSuccessUrl(logoutSuccessUrl);
    }

}
