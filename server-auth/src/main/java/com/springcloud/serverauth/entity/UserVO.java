/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.springcloud.serverauth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author yjx
 * @date 2019/1/16
 */
@Entity /*标识为实体类*/
@Table(name = "sys_user")
@Data /* 所有属性自动添加getter setter方法 */
@NoArgsConstructor /* 提供一个无参的构造方法*/
@AllArgsConstructor /*提供全参的构造方法*/
@DynamicInsert
@DynamicUpdate
@ToString
public class UserVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "user_id",length = 50)
    private String userId;
    /**
     * 用户名
     */
    @Column(name = "user_name",length = 50)
    private String userName;
    /**
     * 密码
     */
    @Column(name = "password",length = 50)
    private String password;
    /**
     * 随机盐
     */
    @Column(name = "salt",length = 50)
    private String salt;
    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;
    /**
     * 0-正常，1-删除
     */
    @Column(name="del_flag",length = 2)
    private String delFlag;
    /**
     * 简介
     */
    private String phone;
    /**
     * 头像
     */
    private String avatar;

    /**
     * 部门ID
     */
    @Column(name = "dept_id",length = 32)
    private Integer deptId;
    /**
     * 部门名称
     */
    @Column(name = "dept_name",length = 32)
    private String deptName;

    /**
     * 角色列表
     */

   /* @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="detailId",referencedColumnName = "id")*/
   /* @ManyToMany(cascade = CascadeType.REMOVE,fetch = FetchType.LAZY)
    @JoinTable(name = "sys_user_role",joinColumns = @JoinColumn(name="user_id"),inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<SysRole> roleList;*/


}
