package com.springcloud.serverauth.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>Description: </p>
 * Created by wusong on 2018-08-08 10:37.
 */
@Data
//@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
//@EntityListeners(AuditingEntityListener.class)
public abstract class JPABaseEntity implements BaseEntity<String>, Serializable {

    private static final long serialVersionUID = 4531649046263494237L;
    @Id
    // 使用hibernate自定义主键策略 uuid 生成id
    /**
     * Hibernate拓展id策略
     * 当然，很多时候，这么几种策略并不够用，这里hibernate也拓展了JPA的id策略，我们可以在org.hibernate.id.IdentifierGeneratorFactory中看到，主要提供了这么些策略：
     * 1. native: 对于oracle采用Sequence方式，对于MySQL和SQL Server采用identity(自增主键生成机制)，native就是将主键的生成工作交由数据库完成，hibernate不管(很常用)。
     * 2. uuid: 采用128位的uuid算法生成主键，uuid被编码为一个32位16进制数字的字符串。占用空间大(字符串类型)。
     * 3. hilo: 使用hilo生成策略，要在数据库中建立一张额外的表，默认表名为hibernate_unique_key,默认字段为Integer类型，名称是next_hi(比较少用)。
     * 4. assigned: 在插入数据的时候主键由程序处理(很常用)，这是generator元素没有指定时的默认生成策略。等同于JPA中的AUTO。
     * 5. identity: 使用SQL Server和MySQL的自增字段，这个方法不能放到Oracle中，Oracle不支持自增字段，要设定sequence(MySQL和SQL Server中很常用)。等同于JPA中的IDENTITY。
     * 6. select: 使用触发器生成主键(主要用于早期的数据库主键生成机制，少用)。
     * 7. sequence: 调用底层数据库的序列来生成主键，要设定序列名，不然hibernate无法找到。
     * 8. seqhilo: 通过hilo算法实现，但是主键历史保存在Sequence中，适用于支持Sequence的数据库，如Oracle(比较少用)。
     * 9. increment: 插入数据的时候hibernate会给主键添加一个自增的主键，但是一个hibernate实例就维护一个计数器，所以在多个实例运行的时候不能使用这个方法。
     * 10. foreign: 使用另外一个相关联的对象的主键。通常和联合起来使用。
     * 11. guid: 采用数据库底层的guid算法机制，对应MYSQL的uuid()函数，SQL Server的newid()函数，ORACLE的rawtohex(sys_guid())函数等。
     * 12. uuid.hex: 看uuid，建议用uuid替换。
     * 13. sequence-identity: sequence策略的扩展，采用立即检索策略来获取sequence值，需要JDBC3.0和JDK4以上（含1.4）版本 。
     */
    /**
     * {@link https://stackoverflow.com/questions/18622716/how-entity-use-id-with-string-type-in-jpa-hibernate}
     */
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id")
    private String id;

    /**
     * 创建者Id
     */
    private String createdId;

    /**
     * 创建时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @CreatedDate
    private Date createdDate;

    /**
     * 创建人
     */
    protected String createdBy;

    /**
     * 创建人拼音
     */
    @CreatedBy
    private String createdByPinyin;


    /**
     * 最后更新时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @LastModifiedDate
    private Date lastModifiedDate;

    /**
     * 最后更新人Id
     */
    @LastModifiedBy
    private String lastModifydId;

    /**
     * 最后更新人
     */
    private String lastModifiedBy;

    /**
     * 最后更新人拼音
     */
    private String lastModifiedByPinyin;


    /**
     * spring data jpa 乐观锁
     */
    @Version
    private Long version;

    /**
     * 是否删除标识
     * 0：未删除
     * 1: 已删除
     */
    private String deleted = "0";
}
