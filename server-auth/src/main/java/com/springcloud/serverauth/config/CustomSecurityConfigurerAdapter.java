package com.springcloud.serverauth.config;

import com.springcloud.serverauth.authentication.MyAuthenctiationFailureHandler;
import com.springcloud.serverauth.authentication.MyAuthenctiationSuccessHandler;
import com.springcloud.serverauth.properties.SecurityConstants;
import com.springcloud.serverauth.service.impl.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

/**
 * @Author: YuanJinXiu
 * @Description:  客户端认证
 * @Date: 2019/1/17 10:31
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class CustomSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private MyAuthenctiationFailureHandler myAuthenctiationFailureHandler;

    @Autowired
    private MyAuthenctiationSuccessHandler myAuthenctiationSuccessHandler;

    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService); //user Details Service验证
    }



    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.formLogin()   // 定义当需要用户登录时候，转到的登录页面
                        .loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)//设置登录页面
                        .loginProcessingUrl("/authentication/form") //自定义登录路径
                        .successHandler(myAuthenctiationSuccessHandler)
                        .failureHandler(myAuthenctiationFailureHandler)
                        .and()
                        .authorizeRequests();
              // http.sessionManagement().invalidSessionUrl(SecurityConstants.DEFAULT_SESSION_INVALID_URL);
               //设置session 失效
        filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable(); //禁止跨域攻击
    }


}
