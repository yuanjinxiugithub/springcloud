package com.springcloud.serverauth.config;

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

/**
 * @Author: YuanJinXiu
 * @Description:  客户端认证
 * @Date: 2019/1/17 10:31
 * @Version: 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Autowired
    private FilterIgnorePropertiesConfig filterIgnorePropertiesConfig;
    @Bean

    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    UserDetailsService CustomUserService(){ //注册UserDetailsService 的bean
        return new UserDetailServiceImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(CustomUserService()); //user Details Service验证
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry =
                http.formLogin().loginPage(SecurityConstants.DEFAULT_LOGIN_PAGE_URL)
                        .loginProcessingUrl("/authentication/form")
                        .and()
                        .authorizeRequests();
        filterIgnorePropertiesConfig.getUrls().forEach(url -> registry.antMatchers(url).permitAll());
        registry.anyRequest().authenticated()
                .and()
                .csrf().disable();
    }


}
