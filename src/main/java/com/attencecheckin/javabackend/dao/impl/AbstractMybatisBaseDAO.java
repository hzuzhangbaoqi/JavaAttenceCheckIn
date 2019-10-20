package com.attencecheckin.javabackend.dao.impl;

import com.attencecheckin.javabackend.dao.BaseDAO;
import com.attencecheckin.javabackend.common.page.MybatisPageUtils;
import com.attencecheckin.javabackend.common.page.Page;
import com.attencecheckin.javabackend.common.page.PageRequest;
import com.attencecheckin.javabackend.common.page.util.MybatisPageQueryUtils;
import com.attencecheckin.javabackend.common.page.util.PageList;
import com.attencecheckin.javabackend.common.util.TextUtil;
import com.attencecheckin.javabackend.dao.BaseDAO;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.SqlSessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.dao.DataAccessException;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

/**
 * @program: javabackend
 * @description:
 * @author: zxf
 * @create: 2019-05-04 03:56
 **/
public abstract class AbstractMybatisBaseDAO<E, PK extends Serializable> implements BaseDAO<E, PK> {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected MessageSourceAccessor messageSourceAccessor;

    private final static int DEFAULT_PAGE_SIZE = 20;
//	private SqlSession sqlSession_;

//	@Autowired
//	private SqlSessionFactory sqlSessionFactory;
    /**
     private SqlSession sqlSession;

     public void SetSqlSession(SqlSession sqlSession) {
     this.sqlSession = sqlSession;
     }
     */
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

    public abstract SqlSession getSqlSession();

    public abstract String getMybatisMapperNamespace();

    public AbstractMybatisBaseDAO() {
        /** 也可以通过泛型直接获取E的子类class类型
         this.entityClass = null;
         Class c = getClass();
         Type type = c.getGenericSuperclass();
         if (type instanceof ParameterizedType) {
         Type[] parameterizedType = ((ParameterizedType) type).getActualTypeArguments();
         this.entityClass = (Class<E>) parameterizedType[0];
         }
         */
    }

    /**
     * 用于子类覆盖,在insert,update之前调用
     * @param e
     */
    protected void prepareObjectForSaveOrUpdate(E e) {
    }

    public String getFindByPrimaryKeyStatement() {
        return getMybatisMapperNamespace() + ".get";
    }

    public String getInsertStatement() {
        return getMybatisMapperNamespace() + ".insert";
    }
    public String getUpdateStatement() {
        return getMybatisMapperNamespace() + ".update";
    }
    public String getDeleteStatement() {
        return getMybatisMapperNamespace() + ".delete";
    }
    public String getTotalCountStatement() {
        return getMybatisMapperNamespace() + ".getTotalCount";
    }
    public String getAllListStatement() {
        return getMybatisMapperNamespace() + ".getAllList";
    }
    public String getExistByIdStatement() {
        return getMybatisMapperNamespace() + ".existById";
    }
    public String getDeleteByIdsStatement() {
        return getMybatisMapperNamespace() + ".deleteByIds";
    }

    @SuppressWarnings("unchecked")
    @Override
    public E get(PK id) {
        Object object = getSqlSession().selectOne(getFindByPrimaryKeyStatement(), id);
        return (E) object;
    }

    @Override
    public E save(E entity) {
        prepareObjectForSaveOrUpdate(entity);
        getSqlSession().insert(getInsertStatement(), entity);
        return entity;
    }

    @Override
    public void update(E entity) {
        prepareObjectForSaveOrUpdate(entity);
        getSqlSession().update(getUpdateStatement(), entity);
    }

    @Override
    public E saveOrUpdate(E entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(PK id) {
        getSqlSession().delete(getDeleteStatement(), id);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> getAllList() {
        return getSqlSession().selectList(getAllListStatement());
    }

    @Override
    public Integer getTotalCount() throws DataAccessException {
        return (Integer) getSqlSession().selectOne(getTotalCountStatement());
    }

    @Override
    public void deleteByIds(PK[] ids) throws DataAccessException {
        getSqlSession().delete(getDeleteByIdsStatement(), ids);
    }

    @Override
    public boolean existById(PK id)
            throws DataAccessException {
        int count =
                (Integer) getSqlSession().selectOne(getExistByIdStatement(), id);
        return count > 0;
    }

    @Override
    public Page findPage(PageRequest query) {
        return MybatisPageQueryUtils.pageQuery(getSqlSession(),
                getMybatisMapperNamespace() + ".findPage", query);
    }

    @Override
    public PageList<E> findPage(Map<String, Object> parameter, String selectSubId) {
        int pageNo = 1;
        int pageSize = DEFAULT_PAGE_SIZE;
        if(parameter.size() > 0) {
            try {
                Object pageNoObj = parameter.get("pageNo");
                if(pageNoObj==null) {
                    pageNoObj = parameter.get("page");
                }
                if(pageNoObj!=null) {
                    if(pageNoObj instanceof String) {
                        pageNo = Integer.parseInt((String) pageNoObj);
                    } else {
                        pageNo = (Integer) pageNoObj;
                    }
                }
                Object pageSizeObj = parameter.get("pageSize");
                if(pageSizeObj==null) {
                    pageSizeObj = parameter.get("rows");
                }
                if(pageSizeObj != null) {
                    if(pageSizeObj instanceof String) {
                        pageSize = Integer.parseInt((String) pageSizeObj);
                    } else {
                        pageSize = (Integer) pageSizeObj;
                    }
                }
            } catch (Exception e) {
//				log.error("pageNo or pageSize cast exception.", e);
            }
        }
        return MybatisPageUtils.pageQuery(getSqlSession(),
                getMybatisMapperNamespace() + ".findPage" +
                        (TextUtil.isNotNull(selectSubId)?("_"+selectSubId):""),
                parameter, pageNo, pageSize);
    }

    @Override
    public PageList<E> findPage(Map<String, Object> parameter) {
        return this.findPage(parameter, null);
    }

    @Override
    public Long findPageCount(Map<String, Object> parameter) throws DataAccessException {
        return (Long) getSqlSession().selectOne(getMybatisMapperNamespace() + ".findPage.count",parameter);
    }

    @Override
    public int del(PK id) {
        return getSqlSession().delete(getDeleteStatement(), id);
    }

    @Override
    public int delByIds(PK[] ids) throws DataAccessException {
        return getSqlSession().delete(getDeleteByIdsStatement(), ids);
    }
    @Override
    public Connection getConnection() {
//		DataSource dataSource = jdbcTemplate.getDataSource();
//		Connection conn = DataSourceUtils.getConnection(dataSource);
//		sqlSession_ = sqlSessionFactory.openSession();
//		Connection conn = getSqlSession().getConnection();
//		System.out.println("getConnection|"+conn.isClosed()+"|"+conn.hashCode());
        Connection conn = null;
        try {
            conn = getSqlSession().getConfiguration().getEnvironment().getDataSource().getConnection();
//			System.out.println("getConnection|"+conn.isClosed()+"|"+conn.hashCode());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//		Connection conn = sqlSessionFactory.openSession().getConnection();
//		Connection conn = jdbcTemplate.getDataSource().getConnection();
//		System.out.println("getConnection|"+conn.isClosed()+"|"+conn.hashCode());
        return conn;

    }
    // 在事物中的
    protected Connection getConnectionIn() {
        SqlSessionTemplate st = (SqlSessionTemplate) getSqlSession();
        Connection conn = SqlSessionUtils.getSqlSession(
                st.getSqlSessionFactory(), st.getExecutorType(),
                st.getPersistenceExceptionTranslator()).getConnection();
        return conn;
    }

    @Override
    public  void releaseConnection(Connection conn) {
//		DataSource dataSource = jdbcTemplate.getDataSource();
//		System.out.println(dataSource+"|"+conn+"|releaseConnection");
//		DataSourceUtils.releaseConnection(conn, dataSource);
        try {
//			System.out.println("releaseConnection|"+conn.isClosed()+"|"+conn.hashCode());
            if(conn!=null&&!conn.isClosed()){
                conn.close();
            }
//			if(sqlSession_!=null) {
//				sqlSession_.close();
//			}
//			System.out.println("releaseConnection|"+conn.isClosed()+"|"+conn.hashCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
