package com.attencecheckin.javabackend.service.impl;

import com.attencecheckin.javabackend.common.page.util.PageList;
import com.attencecheckin.javabackend.dao.BaseDAO;
import com.attencecheckin.javabackend.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @program: javabackend
 * @description: 抽象Service实现类,已实现部分通用操作
 * @author: zxf
 * @create: 2019-10-11 18:20
 **/
public class AbstractBaseServiceImpl <E, PK extends Serializable> implements BaseService<E, PK> {

    @Autowired
    protected MessageSourceAccessor messageSourceAccessor;

    private BaseDAO<E, PK> baseDAO;

    public void setBaseDAO(BaseDAO<E, PK> baseDAO) {
        this.baseDAO = baseDAO;
    }
    //TODO 这里看是否需要改为抽象方法,必须由子类实现
    public BaseDAO<E, PK> getBaseDAO() {
        return baseDAO;
    }

    // /消息处理
    public String i18n(String msgName) {
        return messageSourceAccessor.getMessage(msgName);
    }

    public String i18n(String msgName, String value) {
        String msg = MessageFormat.format(messageSourceAccessor.getMessage(msgName), value);
        return msg;
    }

    public String i18n(String msgName, Object[] values) {
        String msg = MessageFormat.format(messageSourceAccessor.getMessage(msgName), values);
        return msg;
    }

    /**
     * 根据id获取对象
     * @param id
     * @return Entity
     */
    public E get(PK id) throws DataAccessException {
        return baseDAO.get(id);
    }

    /**
     * 取得所有Entity
     * @return List<E>
     */
    public List<E> getAllList() throws DataAccessException {
        return baseDAO.getAllList();
    }

    /**
     * 取得对象总数量
     * @return
     * @throws DataAccessException
     */
    public Integer getTotalCount() throws DataAccessException {
        return baseDAO.getTotalCount();
    }

    /**
     * 新增Entity
     * @param entity
     */
    public E save(E entity) throws DataAccessException {
        return baseDAO.save(entity);
    }

    /**
     * 保存修改Entity
     * @param entity
     */
    public int update(E entity) throws DataAccessException {
        baseDAO.update(entity);
        return 1;
    }

    /**
     * 新增或者保存修改Entity
     * @param entity
     */
    public E saveOrUpdate(E entity) throws DataAccessException {
        return baseDAO.saveOrUpdate(entity);
    }


    @Override
    public boolean existById(PK id) throws DataAccessException {
        return baseDAO.existById(id);
    }

    /*@Override
    public Page findPage(PageRequest query) {
        return baseDAO.findPage(query);
    }
    */
    @Override
    public PageList<E> findPage(Map<String, Object> parameter) {
        System.out.println("-==");
        return baseDAO.findPage(parameter);
    }

    @Override
    public Long findPageCount(Map<String, Object> parameter) {
        return baseDAO.findPageCount(parameter);
    }

    public int del(PK id) throws DataAccessException {
        return baseDAO.del(id);
    }
    public int delByIds(PK[] ids) throws DataAccessException {
        return baseDAO.delByIds(ids);
    }
}
