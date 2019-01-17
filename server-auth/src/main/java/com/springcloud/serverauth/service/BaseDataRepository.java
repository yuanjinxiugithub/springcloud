package com.springcloud.serverauth.service;

import com.mysql.cj.QueryResult;
import com.springcloud.serverauth.entity.JPABaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * <p>Description: 扩展spring data接口，包含基本的持久化操作 </p>
 * Created by wusong on 2018-07-19 17:02.
 */
// 使用NoRepositoryBean表示 Spring Data JPA 不会创建这个接口的实例
@NoRepositoryBean
public interface BaseDataRepository<T extends JPABaseEntity, ID extends Serializable> extends JpaRepository<T, ID> {

    /**
     * 使用原生sql查询数据
     *
     * @param sql 原生sql e.g. select * from table t where t.id = ? and t.name = ?
     * @return
     */
    public abstract List<Object[]> listByNativeQuerySQL(String sql);

    /**
     * 保存实体
     *
     * @param entity 实体id
     */
    public abstract void add(T entity);

    /**
     * 更新实体
     *
     * @param entity 实体id
     */
    public abstract void update(T entity);

    /**
     * 删除实体
     *
     * @param entityClass 实体类
     * @param entityid    实体id
     */
    public abstract <T> void delete(Class<T> entityClass, Object entityid);

    /**
     * 删除实体
     *
     * @param entityClass 实体类
     * @param entityids   实体id数组
     */
    public abstract <T> void delete(Class<T> entityClass, Object[] entityids);

    /**
     * 获取实体
     *
     * @param <T>
     * @param entityClass 实体类
     * @param entityId    实体id
     * @return
     */
    public abstract <T> T find(Class<T> entityClass, Object entityId);

    /**
     * 用hql语句查询所有记录
     *
     * @param hql
     * @return
     */
    public abstract List getHqlQuery(String hql);

    /**
     * <p>Description: 根据查询语句查询数据, 带条件 </p>
     *
     * @param hql    查询语句
     * @param params 条件
     * @return
     */
    public abstract Object findByHql(String hql, Map<String, Object> params);

    /**
     * 用hql语句分页查询
     *
     * @param hql
     * @param start 起始数
     * @param count 页大小
     * @return
     */
    public abstract List getHqlQuery(String hql, int start, int count);

    /**
     * 查询总记录数
     *
     * @param hql
     * @return int @
     */
    public abstract Long getHqlQuerySize(String hql);


    /**
     * 按条件删除对象
     *
     * @param entityName 对象名
     * @param params     删除条件集合(Param ArrayList) @
     */
    public void deleteAll(String entityName, List<Param> params);

    /**
     * 根据sql语句批量删除数据
     *
     * @param sql sql语句 e.g. update table set deleted = 0 where table.id = ?
     */
    public void deleteBySql(String sql);

    /**
     * 根据条件删除对象
     *
     * @param hql
     * @param params
     */
    public void deleteAll(String hql, Map<String, Object> params);


    /**
     * 通过id查询获得特定某些字段的值
     *
     * @param entity
     * @param id
     * @param fields
     */
    public Object getUniqueResultById(String entity, Integer id, String... fields);

    /**
     * 获得唯一值
     *
     * @param hql
     */
    public Object getUniqueResultByHql(StringBuffer hql);

    /**
     * 根据Hql,查询参数 和分页参数, 查询对象列表
     *
     * @param hql
     * @param params   hql 命名参数
     * @param start
     * @param pagesize
     * @return
     */
    public QueryResult getPagingData(String hql, Map<String, Object> params,
                                     int start, int pagesize);

    /**
     * 根据Hql,查询参数 和分页参数, 查询对象列表
     *
     * @param hql      hql 查询语句 中的对象别名要设置为 o
     * @param params   hql 命名参数
     * @param start
     * @param pagesize
     * @param orderby
     * @return
     */
    public QueryResult getPagingData(String hql, Map<String, Object> params,
                                     int start, int pagesize, Map<String, String> orderby);

    /**
     * 根据Hql 分页参数, 查询对象列表
     *
     * @param hql
     * @param start
     * @param pagesize
     * @return
     */
    public QueryResult getPagingData(String hql, int start, int pagesize);

    /**
     * 根据Hql,查询参数 和分页参数, 查询对象列表
     *
     * @param hql      hql 查询语句 中的对象别名要设置为 o
     * @param start
     * @param pagesize
     * @param orderby
     * @return
     */
    public QueryResult getPagingData(String hql, int start, int pagesize,
                                     Map<String, String> orderby);


    /**
     * 根据hql 查询总数
     *
     * @param hql
     * @return
     */
    public Long count(String hql);

    /**
     * 根据hql 和参数 查询总数
     *
     * @param hql
     * @return
     */
    public Long count(String hql, Map<String, Object> params);

    /**
     * 根据Hql 和分页参数查询对象列表
     *
     * @param hql
     * @param start
     * @param pagesize
     * @return
     */
    public List find(String hql, int start, int pagesize);

    /**
     * 根据Hql 和分页参数,排序属性, 查询对象列表
     *
     * @param hql      注意: hql中的对象别名一律命名为o. 否则orderby 语句不能正常构建 会报错
     * @param start    开始页
     * @param pagesize 页大小
     * @param orderby  排序属性 例如 {key:"name", value:"desc"} 属性名:升序/降序
     * @return
     */
    public List find(String hql, int start, int pagesize,
                     Map<String, String> orderby);

    /**
     * 根据Hql,查询参数 和分页参数, 查询对象列表
     *
     * @param hql      "from CorpInfo o where o.name=:name "
     * @param params   命名查询参数
     * @param start    开始页
     * @param pagesize 页大小
     * @return
     */
    public List find(String hql, Map<String, Object> params, int start,
                     int pagesize);

    /**
     * 根据Hql, 查询参数 和分页参数 排序字段, 查询对象列表
     *
     * @param hql      hql 查询语句 中的对象别名要设置为 o
     * @param params   hql 命名参数
     * @param start
     * @param pagesize
     * @param orderby
     * @return
     */
    public List find(String hql, Map<String, Object> params, int start,
                     int pagesize, Map<String, String> orderby);


    /**
     * <p>Description: 校验是否是正确的操作符  </p>
     *
     * @param operator 操作符 e.g. lt eq and nq
     * @return true 是正确的操作符, false 不是正确的操作符
     */
   /* default public boolean isOperator(String operator) {
        return StringUtils.equals("gt", operator.trim())
                || StringUtils.equals("lt", operator.trim())
                || StringUtils.equals("eq", operator.trim())
                || StringUtils.equals("nq", operator.trim())
                || StringUtils.equals("like", operator.trim())
                || StringUtils.equals("rlike", operator.trim())
                || StringUtils.equals("llike", operator.trim())
                || StringUtils.equals("in", operator.trim())
                || StringUtils.equals("nin", operator.trim())
                || StringUtils.equals("=", operator.trim());
    }
*/
    /**
     * <p>Description: 根据条件删除数据 </p>
     *
     * @param entityName 实体
     * @param clazz      被删除的对象
     * @param params     条件
     * @return void
     */
    public void deleteByCondition(String entityName, Object clazz, List<Param> params);

    /**
     * <p>Description: 根据条件更新数据 </p>
     *
     * @param entityName 实体
     * @param clazz      待更新的对象
     * @param params     条件
     * @return
     */
    public void updateByCondition(String entityName, Object clazz, List<Param> params);

    /**
     * <p>Description: 根据条件查询数据 </p>
     *
     * @param clazz   带查询的对象
     * @param params  条件
     * @param orderBy 排序 e.g. orderBy.put("name", "desc") orderBy.put("age", "asc")
     * @return
     */
    public List<T> selectByConditon(Object clazz, List<Param> params, Map<String, String> orderBy);

}
