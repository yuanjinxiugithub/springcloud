package com.springcloud.eurakeserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

/**
 * <p>Description: 在spring-security-core:5.0+ 中, 由于CSRF的某些原因， spring无法对配置application.properties中的用户进行身份验证 </p>
 * <p>所以我不得不重写WebSecurityConfigurerAdapter中的configure方法来禁用csrf并创建inMemory用户。还需要将application.properties中的spring.security.user和spring.security.password属性给删除掉。</p>
 * Created by wusong on 2018-08-30 10:11.
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            /**
             * 注意：spring-security-core:5.0+中NoOpPasswordEncoder被标记为@Deprecated
             * 官方建议使用DelegatingPasswordEncoder
             * 但是使用DelegatingPasswordEncoder有一个问题就是启动工程访问localhost:8761输入用户名和密码时会抛出一个异常, 异常类型为
             * java.lang.IllegalArgumentException: There is no PasswordEncoder mapped for the id "null"
             * 通过查资料发现：
             * 在spring-security-core:5.0+中, 默认情况下PasswordEncoder构建为DelegatingPasswordEncoder.
             * 当我们将用户存储在内存中时, 我们将以纯文本形式提供密码, 并且在尝试从DelegatingPasswordEncoder验证密码查找编码器时
             * 无法找到与这些密码存储方式相匹配的密码.
             * 所以我们可以使用以下的方式来创建用户。
             * .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
             * .withUser("admin").password("{noop}admin")
             * 我们可以简单地加一个{noop}为密码前缀, 以便DelegatingPasswordEncoder使用它NoOpPasswordEncoder来验证这些密码。
             * 请注意，NoOpPasswordEncoder不推荐使用，因为以纯文本格式存储密码不是一个好习惯。
             * @link https://github.com/spring-projects/spring-security/issues/5086
             * @link https://spring.io/blog/2017/11/01/spring-security-5-0-0-rc1-released#password-encoding
             * PasswordEncoderFactories#createDelegatingPasswordEncoder();
             * encoders.put("noop", NoOpPasswordEncoder.getInstance());
             * @link https://stackoverflow.com/questions/46999940/spring-boot-passwordencoder-error
             * @link https://stackoverflow.com/questions/50971891/how-to-secure-spring-cloud-eureka-service-with-basic-auth
             * @link https://cloud.spring.io/spring-cloud-netflix/multi/multi__service_discovery_eureka_clients.html
             */
            //.passwordEncoder(NoOpPasswordEncoder.getInstance())
            .passwordEncoder(PasswordEncoderFactories.createDelegatingPasswordEncoder())
            .withUser("admin").password("{noop}admin")
            .authorities("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .authorizeRequests()   //定义那些url 被保护
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }
}
