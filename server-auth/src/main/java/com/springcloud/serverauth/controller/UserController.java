package com.springcloud.serverauth.controller;

import com.springcloud.serverauth.entity.JsonResult;
import com.springcloud.serverauth.entity.User;
import com.springcloud.serverauth.repository.UserRepository;
import com.springcloud.serverauth.util.ResponseMessageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author: YuanJinXiu
 * @Description: ${description}
 * @Date: 2019/1/9 16:51
 * @Version: 1.0
 */
@RestController
@RequestMapping(value = "user")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;

    @GetMapping(value = "findByUserName")
    @PreAuthorize("hasPermission('user', 'read') or hasRole('ROLE_ADMINISTRATOR')")
    public ResponseEntity<JsonResult> findByUserName(@RequestParam String userName){
         User user = userRepository.findByUserName(userName);
       return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"查询数据成功",user);
    }

    @RequestMapping(value = "save")
    public ResponseEntity<JsonResult> addUser(@RequestBody  User user){
         user.setCreateTime(new Date());
         userRepository.saveAndFlush(user);
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"新增数据成功",null);
    }

    @GetMapping(value = "findByAgeRange")
    public ResponseEntity<JsonResult> findByAgeRange(@RequestParam String lage,@RequestParam String mage){
        List<Object> user = userRepository.findByAgeRange(Integer.parseInt(lage),Integer.parseInt(mage));
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"查询数据成功",user);
    }

    @RequestMapping(value = "update")
    public ResponseEntity<JsonResult> update(@RequestBody  User user){
        user.setUpdateTime(new Date());
        userRepository.update(user);
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"修改数据成功",null);
    }

    @RequestMapping(value = "updateByParam")
    public ResponseEntity<JsonResult> updateByParam(@RequestParam(value = "userName",required = false) String userName,
                                                    @RequestParam(value = "age",required = false) Integer age,
                                                    String id){
       System.out.println(age);
       System.out.println(userName);
       System.out.println(id);
        userRepository.update(userName,age,id);
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"修改数据成功",null);
    }

    @RequestMapping(value = "delete")
    public ResponseEntity<JsonResult> delete(@RequestBody  User user){
        userRepository.deleteById(user.getId());
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"删除数据成功",null);
    }

    @RequestMapping(value = "deleteAddUpT")
    public ResponseEntity<JsonResult> deleteAddUpT(@RequestBody  User user){
        user.setUpdateTime(new Date());
        userRepository.deleteById(user);
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"删除数据成功",null);
    }

    @PostMapping(value = "findAll")
    public ResponseEntity<JsonResult> findAll(){
        List<User> user = userRepository.findAll();
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"查询数据成功",user);
    }

    @RequestMapping(value = "findByPage")
    public ResponseEntity<JsonResult> findByPage(@RequestParam(value = "pagesize",defaultValue = "10",required = false) int pageSize,
               @RequestParam(value = "pagenum",defaultValue = "1",required = false) int pageNum){
        Pageable pageRequest =  PageRequest.of(pageNum,pageSize, Sort.Direction.DESC,"age");
        Page<User> pages = userRepository.findAll(pageRequest);
        return ResponseMessageUtil.success(String.valueOf(HttpStatus.OK.value()),"查询数据成功",pages);
    }


}
