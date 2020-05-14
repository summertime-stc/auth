//package com.example.stest.common.repository;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.repository.NoRepositoryBean;
//
//import java.io.Serializable;
//import java.util.Collection;
//import java.util.List;
//import java.util.Map;
//
///**
// * BaseHibernate4QueryDao
// * @date 2018.11.8
// * @author ccx
// */
//@NoRepositoryBean
//public interface BaseHibernate4QueryDao<E extends Serializable> {
//    void hibernateFlush();
//
//    void hibernateClear();
//
//    void hibernateDetach(E entity);
//
//    void detachAll(Collection<E> entities);
//
//    boolean hibernateContains(E entity);
//
//    void hibernateSave(E entity);
//
//    void hibernateSave(Collection<E> entities);
//
//    void hibernateInsert(E entity);
//
//    void hibernateInsert(Collection<E> entities);
//
//    void hibernateDelete(E entity);
//
//    void hibernateDelete(Object id);
//
//    E hibernateLoad(Object id);
//
//    // protect
//    E findOneEntityObject(String hql, Object[] values);
//
//    E findOneEntityObject(String hql, Map<String, Object> conditionMap);
//
//
//    List<E> findEntityObjects(String hql, Object[] values);
//
//    List<E> findEntityObjects(String hql, Map<String, Object> conditionMap);
//
//    Object findOneResultObject(String hql, Object[] values);
//
//    Object findOneResultObject(String hql, Map<String, Object> conditionMap);
//
//
//    List<?> findResultObjects(String hql, Object[] values);
//
//    List<?> findResultObjects(String hql, Map<String, Object> conditionMap);
//
//
//    Page<E> findEntityObjects(String hql, Object[] values, Pageable pageRequest);
//
//    Page<E> findEntityObjects(String hql, Map<String, Object> conditionMap, Pageable pageRequest);
//
//
//    Page<Object[]> findResultObjects(String hql, Object[] values, Pageable pageRequest);
//
//    Page<Object[]> findResultObjects(String hql, Map<String, Object> conditionMap, Pageable pageRequest);
//
//    E findOneEntityObjectBySql(String sql, Object[] values);
//
//    E findOneEntityObjectBySql(String sql, Map<String, Object> conditionMap);
//
//    List<E> findEntityObjectsBySql(String sql, Object[] values);
//
//    List<E> findEntityObjectsBySql(String sql, Map<String, Object> conditionMap);
//
//
//    Object findOneResutlObjectBySql(String sql, Object[] values);
//
//    Object findOneResutlObjectBySql(String sql, Map<String, Object> conditionMap);
//
//
//    List<?> findResutlObjectsBySql(String sql, Object[] values);
//
//    List<?> findResutlObjectsBySql(String sql, Map<String, Object> conditionMap);
//
//
//    Page<E> findEntityObjectsBySql(String sql, Object[] values, Pageable pageRequest);
//
//    Page<E> findEntityObjectsBySql(String sql, Map<String, Object> conditionMap, Pageable pageRequest);
//
//
//    Page<Object[]> findResutlObjectsBySql(String sql, Object[] values, Pageable pageRequest);
//
//    Page<Object[]> findResutlObjectsBySql(String sql, Map<String, Object> conditionMap, Pageable pageRequest);
//
//
//    int bulkUpdate(String hql, Object[] values);
//
//    int bulkUpdate(String hql, Map<String, Object> conditionMap);
//
//
//    int bulkUpdateSql(String sql, Object[] values);
//
//    int bulkUpdateSql(String sql, Map<String, Object> conditionMap);
//
//}
