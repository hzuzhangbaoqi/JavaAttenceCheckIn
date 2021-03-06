package com.attencecheckin.javabackend.dao;

import com.attencecheckin.javabackend.common.page.PageRequest;
import com.attencecheckin.javabackend.common.page.Page;
import com.attencecheckin.javabackend.common.page.PageRequest;
import com.attencecheckin.javabackend.common.page.util.PageList;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * DAO基础接口, 抽象出常用的数据操作方法, E是domain类型,PK是主键类型
 * @author Joe
 *
 * @param <E>
 * @param <PK>
 */
public interface BaseDAO <E, PK extends Serializable> {
    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public E get(PK id) throws DataAccessException;

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<E> getAllList() throws DataAccessException;

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException;

    /**
     * 新增Entity
     * @param entity
     */
    public E save(E entity) throws DataAccessException;

    /**
     * 保存修改Entity
     * @param entity
     */
    public void update(E entity) throws DataAccessException;

    /**
     * 根据属性值判断对象是否存在
     * @param
     * @return 存在-true, 不存在-false
     */
    public boolean existById(PK id) throws DataAccessException;

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public E saveOrUpdate(E entity) throws DataAccessException;

    /**
     * 根据id信息删除对象
     * @param id
     * @throws DataAccessException
     */
    public void delete(PK id) throws DataAccessException;

    /**
     * 根据多个id删除
     * @param ids
     * @throws DataAccessException
     */
    public void deleteByIds(PK[] ids) throws DataAccessException;

    public Page findPage(PageRequest query);

    public PageList<E> findPage(Map<String, Object> parameter);
    public PageList<E> findPage(Map<String, Object> parameter, String selectSubId);

    public Long findPageCount(Map<String, Object> parameter);

    public int del(PK id) throws DataAccessException;
    public int delByIds(PK[] ids) throws DataAccessException;

    Connection getConnection();

    void releaseConnection(Connection conn);

}
