package com.springcloud.serverauth.repository;

import com.springcloud.serverauth.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/9 16:34
 * @Version: 1.0
 */
public interface UserRepository extends JpaRepository<User,String> {

    List<User> findByUserName(String userName);

    @Query(value = "select id,user_name,password,create_time,age from user where age>?1 and  age<?2", nativeQuery = true)
    List<Object> findByAgeRange(int lage, int mage);

    @Transactional
    @Modifying
    @Query(value = "update user set user_name = :#{#user.userName} , age=:#{#user.age},update_time = :#{#user.updateTime} where id=:#{#user.id}",nativeQuery = true)
    int update(User user);

    @Transactional
    @Modifying
    @Query(value = "update user set user_name = :userName , age=:age where id=:id",nativeQuery = true)
    int update(@Param("userName") String userName, @Param("age") int age, @Param("id") String id);

    @Transactional
    @Modifying
    @Query(value = "update user set del_flag=1 where id=?1",nativeQuery = true)
    void deleteById(String id);

    @Transactional
    @Modifying
    @Query(value = "update user set del_flag=1,update_time=:#{#user.updateTime} where id=:#{#user.id}",nativeQuery = true)
    void deleteById(User user);
}