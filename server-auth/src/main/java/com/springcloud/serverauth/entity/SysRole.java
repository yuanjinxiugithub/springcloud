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
import com.springcloud.serverauth.repository.UserVoRepository;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author lengleng
 * @since 2017-10-29
 */
@Entity /*标识为实体类*/
@Table(name = "sys_role")
@Data /* 所有属性自动添加getter setter方法 */
@NoArgsConstructor /* 提供一个无参的构造方法*/
@AllArgsConstructor /*提供全参的构造方法*/
@DynamicInsert
@DynamicUpdate
@ToString
public class SysRole implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(name = "role_id",length = 32)
    private String roleId;
    @Column(name = "role_name",length = 32)
    private String roleName;
    @Column(name = "role_code",length = 32)
    private String roleCode;
    @Column(name = "role_desc",length = 32)
    private String roleDesc;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name="del_flag",length = 2)
    private String delFlag;

    /*@ManyToMany(mappedBy="courses")
    List<UserVO> users = new ArrayList<UserVO>();*/
}
