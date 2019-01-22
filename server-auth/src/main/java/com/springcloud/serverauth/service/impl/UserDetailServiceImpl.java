package com.springcloud.serverauth.service.impl;

import com.springcloud.serverauth.entity.UserVO;
import com.springcloud.serverauth.repository.UserVoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @Author: YuanJinXiu
 * @Description:  实现UserDetailsService  用于处理登陆信息
 * @Date: 2019/1/17 16:00
 * @Version: 1.0
 */
@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserVoRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; //对密码进行加密解密


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserVO userVo = userRepository.findByUserName(s);
        if (userVo == null) {
            throw new UsernameNotFoundException("用户名不存在或者密码错误");
        }
        logger.info("用户的用户名: {}", s);
        String password = passwordEncoder.encode(userVo.getPassword());
        logger.info("password: {}", password);
        // 参数分别是：用户名，密码，用户权限
        User user = new User(s, password, AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMI"));
        return user;
    }
}
