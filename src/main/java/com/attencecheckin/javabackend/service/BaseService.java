package com.attencecheckin.javabackend.service;

import com.attencecheckin.javabackend.common.page.util.PageList;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Service基础接口,抽闲出通用操作
 *
 * @param <E> domain类型
 * @param <PK> 主键类型
 */
public interface BaseService <E, PK extends Serializable>{
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
    public int update(E entity) throws DataAccessException;

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public E saveOrUpdate(E entity) throws DataAccessException;

    /**
     * 根据属性值判断对象是否存在
     * @param id
     * @return 存在-true, 不存在-false
     * @throws DataAccessException
     */
    public boolean existById(PK id) throws DataAccessException;

    //public Page findPage(PageRequest query);

    public PageList<E> findPage(Map<String, Object> parameter);
    public Long findPageCount(Map<String, Object> parameter);


    public int del(PK id) throws DataAccessException;
    public int delByIds(PK[] ids) throws DataAccessException;
}
