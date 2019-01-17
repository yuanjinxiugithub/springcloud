package com.springcloud.serverauth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.util.Date;


/**
 * @Author: YuanJinXiu
 * @Description: jpa 使用
 * @Date: 2019/1/9 15:43
 * @Version: 1.0
 */
@Entity /*标识为实体类*/
@Table(name = "user")
@Data /* 所有属性自动添加getter setter方法 */
@NoArgsConstructor /* 提供一个无参的构造方法*/
@AllArgsConstructor /*提供全参的构造方法*/
@DynamicInsert
@DynamicUpdate
@ToString
public class User {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id",length = 50)
    private String id;

    @Column(name = "user_name",length = 32)
    private String userName;

    @Column(name = "password",length = 32)
    private String password;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @CreatedDate
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @LastModifiedDate
    @Column(name = "update_time")
    private Date updateTime;

    @Column(name = "remark")
    @Null
    private String  remark;


    @Column(name = "age",length = 32)
    private  int age;

    /**
     * spring data jpa 乐观锁
     */
    @Version
    private Long version;

    /**
     * 删除标志
     * 0--未删除
     * 1--已删除
     */
    @Column(name = "del_flag")
    private String delFlag = "0";

    @Transient
    private static String DEL_FLAG_NORMAL = "0";
    @Transient
    private static String DEL_FLAG_DELETE = "1";
}
