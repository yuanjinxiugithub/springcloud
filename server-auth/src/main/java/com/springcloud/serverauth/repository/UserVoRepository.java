package com.springcloud.serverauth.repository;


import com.springcloud.serverauth.entity.User;
import com.springcloud.serverauth.entity.UserVO;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/17 16:06
 * @Version: 1.0
 */
public interface UserVoRepository extends JpaRepository<UserVO,String> {
    UserVO findByUserName(String userName);
}
