//package com.example.stest.common.repository;
//
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.engine.spi.SessionFactoryImplementor;
//import org.hibernate.hql.internal.ast.QueryTranslatorImpl;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageImpl;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.persistence.Query;
//import javax.persistence.TypedQuery;
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.TypeVariable;
//import java.math.BigDecimal;
//import java.math.BigInteger;
//import java.util.*;
//import java.util.Map.Entry;
//
///**
// * BaseHibernate4QueryDaoImpl
// * @date 2018.11.8
// * @author ccx
// */
//public class BaseHibernate4QueryDaoImpl<E extends Serializable> implements BaseHibernate4QueryDao<E> {
//
//    @PersistenceContext
//    private EntityManager em;
//    private static SessionFactory sf;
//    public Class<E> entityClass = null;
//
//    public BaseHibernate4QueryDaoImpl() {
//        if (this.getClass().getGenericSuperclass() instanceof ParameterizedType && !(((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0] instanceof TypeVariable)) {
//            this.entityClass = (Class) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
//        }
//
//    }
//
//    @Override
//    public void hibernateFlush() {
//        this.em.flush();
//    }
//
//    @Override
//    public void hibernateClear() {
//        this.em.flush();
//        this.em.clear();
//    }
//
//    @Override
//    public void hibernateDetach(E entity) {
//        this.em.detach(entity);
//    }
//
//    @Override
//    public void detachAll(Collection<E> entities) {
//        Iterator var2 = entities.iterator();
//
//        while (var2.hasNext()) {
//            E entity = (E) var2.next();
//            this.hibernateDetach(entity);
//        }
//
//    }
//
//    @Override
//    public boolean hibernateContains(E entity) {
//        return this.em.contains(entity);
//    }
//
//    @Override
//    public void hibernateSave(E entity) {
//        if (!this.em.contains(entity)) {
//            this.em.merge(entity);
//        }
//
//    }
//
//    @Override
//    public void hibernateSave(Collection<E> entities) {
//        Iterator var2 = entities.iterator();
//
//        while (var2.hasNext()) {
//            E entity = (E) var2.next();
//            this.hibernateSave(entity);
//        }
//
//    }
//
//    @Override
//    public void hibernateInsert(E entity) {
//        this.em.persist(entity);
//    }
//
//    @Override
//    public void hibernateInsert(Collection<E> entities) {
//        Iterator var2 = entities.iterator();
//
//        while (var2.hasNext()) {
//            E entity = (E) var2.next();
//            this.hibernateInsert(entity);
//        }
//
//    }
//
//    @Override
//    public void hibernateDelete(E entity) {
//        this.em.remove(this.em.contains(entity) ? entity : (Serializable) this.em.merge(entity));
//    }
//
//    @Override
//    public void hibernateDelete(Object id) {
//        this.hibernateDelete(this.hibernateLoad(id));
//    }
//
//    @Override
//    public E hibernateLoad(Object id) {
//        return (E) this.em.find(this.entityClass, id);
//    }
//
//    @Override
//    public E findOneEntityObject(String hql, Object[] values) {
//        return (E) this.findOneEntityObject(hql, (Map) null, values);
//    }
//
//    @Override
//    public E findOneEntityObject(String hql, Map<String, Object> conditionMap) {
//        return this.findOneEntityObject(hql, conditionMap, (Object[]) null);
//    }
//
//    private E findOneEntityObject(String hql, Map<String, Object> map, Object[] values) {
//        if (hql != null) {
//            TypedQuery query = this.em.createQuery(hql, this.entityClass);
//            this.setParameters(query, map, values);
//            return (E) this.getSingleResult(query);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<E> findEntityObjects(String hql, Object[] values) {
//        return this.findAllEntityObjects(hql, (Map) null, values);
//    }
//
//    @Override
//    public List<E> findEntityObjects(String hql, Map<String, Object> conditionMap) {
//        return this.findAllEntityObjects(hql, conditionMap, (Object[]) null);
//    }
//
//    private List<E> findAllEntityObjects(String hql, Map<String, Object> conditionMap, Object[] values) {
//        if (hql != null) {
//            TypedQuery query = this.em.createQuery(hql, this.entityClass);
//            this.setParameters(query, conditionMap, values);
//            return query.getResultList();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Object findOneResultObject(String hql, Object[] values) {
//        return this.findOneResultObject(hql, (Map) null, values);
//    }
//
//    @Override
//    public Object findOneResultObject(String hql, Map<String, Object> conditionMap) {
//        return this.findOneResultObject(hql, conditionMap, (Object[]) null);
//    }
//
//    private Object findOneResultObject(String hql, Map<String, Object> conditionMap, Object[] values) {
//        if (hql != null) {
//            Query query = this.em.createQuery(hql);
//            this.setParameters(query, conditionMap, values);
//            return this.getSingleResult(query);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<?> findResultObjects(String hql, Object[] values) {
//        return this.findAllResultObjects(hql, (Map) null, values);
//    }
//
//    @Override
//    public List<?> findResultObjects(String hql, Map<String, Object> conditionMap) {
//        return this.findAllResultObjects(hql, conditionMap, (Object[]) null);
//    }
//
//    private List<?> findAllResultObjects(String hql, Map<String, Object> conditionMap, Object[] values) {
//        if (hql != null) {
//            Query query = this.em.createQuery(hql);
//            this.setParameters(query, conditionMap, values);
//            return query.getResultList();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Page<E> findEntityObjects(String hql, Object[] values, Pageable pageRequest) {
//        return this.findEntityObjects(hql, (Map) null, values, pageRequest);
//    }
//
//    @Override
//    public Page<E> findEntityObjects(String hql, Map<String, Object> conditionMap, Pageable pageRequest) {
//        return this.findEntityObjects(hql, conditionMap, (Object[]) null, pageRequest);
//    }
//
//    private Page<E> findEntityObjects(String hql, Map<String, Object> conditionMap, Object[] values, Pageable pageRequest) {
//        if (hql != null && pageRequest.getOffset() >= 0 && pageRequest.getPageSize() > 0) {
//            String hql2 = hql;
//            if (hql.toLowerCase().indexOf("order by") == -1) {
//                Sort sort = pageRequest.getSort();
//                if (sort != null && !"UNSORTED".equals(sort.toString())) {
//                    hql2 = hql + " order by " + sort.toString().replace(":", "");
//                }
//            }
//
//            long total1 = this.findCount(hql, conditionMap, values);
//            TypedQuery query = this.em.createQuery(hql2, this.entityClass);
//            this.setParameters(query, conditionMap, values);
//            query.setFirstResult((int)pageRequest.getOffset());
//            query.setMaxResults(pageRequest.getPageSize());
//            List content = query.getResultList();
//            return new PageImpl(content, pageRequest, total1);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Page<Object[]> findResultObjects(String hql, Object[] values, Pageable pageRequest) {
//        return this.findResultObjects(hql, (Map) null, values, pageRequest);
//    }
//
//    @Override
//    public Page<Object[]> findResultObjects(String hql, Map<String, Object> conditionMap, Pageable pageRequest) {
//        return this.findResultObjects(hql, conditionMap, (Object[]) null, pageRequest);
//    }
//
//    private Page<Object[]> findResultObjects(String hql, Map<String, Object> conditionMap, Object[] values, Pageable pageRequest) {
//        if (hql != null && pageRequest.getOffset() >= 0 && pageRequest.getPageSize() > 0) {
//            long total = this.findCount(hql, conditionMap, values);
//            Query query = this.em.createQuery(hql);
//            this.setParameters(query, conditionMap, values);
//            query.setFirstResult((int)pageRequest.getOffset());
//            query.setMaxResults(pageRequest.getPageSize());
//            List content = query.getResultList();
//            return new PageImpl(content, pageRequest, total);
//        } else {
//            return null;
//        }
//    }
//
//    private long findCount(String hql, Map<String, Object> conditionMap, Object[] values) {
//        String sql = this.hqlToSql(hql);
//        if (conditionMap != null && !conditionMap.isEmpty()) {
//            Object[] paras = this.mapToArray(hql, conditionMap);
//            sql = this.improveSql(sql);
//            return this.findCountBySql(sql, (Map) null, paras);
//        } else {
//            return this.findCountBySql(sql, (Map) null, values);
//        }
//    }
//
//    @Override
//    public E findOneEntityObjectBySql(String sql, Object[] values) {
//        return (E) this.findOneEntityObjectBySql(sql, (Map) null, values);
//    }
//
//    @Override
//    public E findOneEntityObjectBySql(String sql, Map<String, Object> conditionMap) {
//        return this.findOneEntityObjectBySql(sql, conditionMap, (Object[]) null);
//    }
//
//    private E findOneEntityObjectBySql(String sql, Map<String, Object> conditionMap, Object[] values) {
//        if (sql != null) {
//            Query query = this.em.createNativeQuery(sql, this.entityClass);
//            this.setParameters(query, conditionMap, values);
//            return (E) this.getSingleResult(query);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<E> findEntityObjectsBySql(String sql, Object[] values) {
//        return this.findAllEntityObjectsBySql(sql, (Map) null, values);
//    }
//
//    @Override
//    public List<E> findEntityObjectsBySql(String sql, Map<String, Object> conditionMap) {
//        return this.findAllEntityObjectsBySql(sql, conditionMap, (Object[]) null);
//    }
//
//    private List<E> findAllEntityObjectsBySql(String sql, Map<String, Object> conditionMap, Object[] values) {
//        if (sql != null) {
//            Query query = this.em.createNativeQuery(sql, this.entityClass);
//            this.setParameters(query, conditionMap, values);
//            return query.getResultList();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Object findOneResutlObjectBySql(String sql, Object[] values) {
//        return this.findOneResutlObjectBySql(sql, (Map) null, values);
//    }
//
//    @Override
//    public Object findOneResutlObjectBySql(String sql, Map<String, Object> conditionMap) {
//        return this.findOneResutlObjectBySql(sql, conditionMap, (Object[]) null);
//    }
//
//    private Object findOneResutlObjectBySql(String sql, Map<String, Object> conditionMap, Object[] values) {
//        if (sql != null) {
//            Query query = this.em.createNativeQuery(sql);
//            this.setParameters(query, conditionMap, values);
//            return this.getSingleResult(query);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public List<?> findResutlObjectsBySql(String sql, Object[] values) {
//        return this.findAllResutlObjectsBySql(sql, (Map) null, values);
//    }
//
//    @Override
//    public List<?> findResutlObjectsBySql(String sql, Map<String, Object> conditionMap) {
//        return this.findAllResutlObjectsBySql(sql, conditionMap, (Object[]) null);
//    }
//
//    private List<?> findAllResutlObjectsBySql(String sql, Map<String, Object> conditionMap, Object[] values) {
//        if (sql != null) {
//            Query query = this.em.createNativeQuery(sql);
//            this.setParameters(query, conditionMap, values);
//            return query.getResultList();
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Page<E> findEntityObjectsBySql(String sql, Object[] values, Pageable pageRequest) {
//        return this.findEntityObjectsBySql(sql, (Map) null, values, pageRequest);
//    }
//
//    @Override
//    public Page<E> findEntityObjectsBySql(String sql, Map<String, Object> conditionMap, Pageable pageRequest) {
//        return this.findEntityObjectsBySql(sql, conditionMap, (Object[]) null, pageRequest);
//    }
//
//    private Page<E> findEntityObjectsBySql(String sql, Map<String, Object> conditionMap, Object[] values, Pageable pageRequest) {
//        if (pageRequest.getOffset() >= 0 && pageRequest.getPageSize() > 0 && sql != null) {
//            String sql2 = sql;
//            if (sql.toLowerCase().indexOf("order by") == -1) {
//                Sort total = pageRequest.getSort();
//                sql2 = sql + "order by " + total.toString().replace(":", "");
//            }
//
//            long total1 = this.findCountBySql(sql, conditionMap, values);
//            Query query = this.em.createNativeQuery(sql2, this.entityClass);
//            this.setParameters(query, conditionMap, values);
//            query.setFirstResult((int)pageRequest.getOffset());
//            query.setMaxResults(pageRequest.getPageSize());
//            List content = query.getResultList();
//            return new PageImpl(content, pageRequest, total1);
//        } else {
//            return null;
//        }
//    }
//
//    @Override
//    public Page<Object[]> findResutlObjectsBySql(String sql, Object[] values, Pageable pageRequest) {
//        return this.findResutlObjectsBySql(sql, (Map) null, values, pageRequest);
//    }
//
//    @Override
//    public Page<Object[]> findResutlObjectsBySql(String sql, Map<String, Object> conditionMap, Pageable pageRequest) {
//        return this.findResutlObjectsBySql(sql, conditionMap, (Object[]) null, pageRequest);
//    }
//
//    private Page<Object[]> findResutlObjectsBySql(String sql, Map<String, Object> conditionMap, Object[] values, Pageable pageRequest) {
//        if (pageRequest.getOffset() >= 0 && pageRequest.getPageSize() > 0 && sql != null) {
//            long total = this.findCountBySql(sql, conditionMap, values);
//            Query query = this.em.createNativeQuery(sql);
//            this.setParameters(query, conditionMap, values);
//            query.setFirstResult((int)pageRequest.getOffset());
//            query.setMaxResults(pageRequest.getPageSize());
//            List content = query.getResultList();
//            return new PageImpl(content, pageRequest, total);
//        } else {
//            return null;
//        }
//    }
//
//    private long findCountBySql(String sql, Map<String, Object> conditionMap, Object[] values) {
//        if (sql != null) {
//            Object o = this.findOneResutlObjectBySql("select count(*) from ( " + sql + " ) count_sql", conditionMap, values);
//            if(o instanceof Long) {
//            	return ((BigInteger) o).longValue();
//            } else if(o instanceof BigDecimal) {
//            	return ((BigDecimal) o).longValue();
//            } else {
//            	return 0;
//            }
//        } else {
//            return 0L;
//        }
//
//    }
//
//    @Override
//    public int bulkUpdate(String hql, Object[] values) {
//        return this.bulkUpdate(hql, (Map) null, values);
//    }
//
//    @Override
//    public int bulkUpdate(String hql, Map<String, Object> conditionMap) {
//        return this.bulkUpdate(hql, conditionMap, (Object[]) null);
//    }
//
//    private int bulkUpdate(String hql, Map<String, Object> conditionMap, Object[] values) {
//        if (hql != null) {
//            Query query = this.em.createQuery(hql);
//            this.setParameters(query, conditionMap, values);
//            return query.executeUpdate();
//        } else {
//            return 0;
//        }
//    }
//
//    @Override
//    public int bulkUpdateSql(String sql, Object[] values) {
//        return this.bulkUpdateSql(sql, (Map) null, values);
//    }
//
//    @Override
//    public int bulkUpdateSql(String sql, Map<String, Object> conditionMap) {
//        return this.bulkUpdateSql(sql, conditionMap, (Object[]) null);
//    }
//
//    private int bulkUpdateSql(String sql, Map<String, Object> conditionMap, Object[] values) {
//        if (sql != null) {
//            Query query = this.em.createNativeQuery(sql);
//            this.setParameters(query, conditionMap, values);
//            return query.executeUpdate();
//        } else {
//            return 0;
//        }
//    }
//
//    private void setParameters(Query query, Map<String, Object> map, Object[] values) {
//        if (map != null && map.size() > 0) {
//            Iterator var6 = map.entrySet().iterator();
//
//            while (var6.hasNext()) {
//                Entry entry = (Entry) var6.next();
//                query.setParameter((String) entry.getKey(), entry.getValue());
//            }
//        } else if (null != values && values.length > 0) {
//            for (int i = 0; i < values.length; ++i) {
//                query.setParameter(i + 1, values[i]);
//            }
//        }
//
//    }
//
//    private String hqlToSql(String hql) {
//        if (hql == null || "".equals(hql.trim())) {
//            return hql;
//        } else {
//            QueryTranslatorImpl queryTranslator = new QueryTranslatorImpl(hql, hql, Collections.EMPTY_MAP, (SessionFactoryImplementor) this.getSessionFactory());
//            queryTranslator.compile(Collections.EMPTY_MAP, true);
//            String sql = queryTranslator.getSQLString();
//            return sql;
//        }
//    }
//
//    private SessionFactory getSessionFactory() {
//        Session session = (Session) this.em.getDelegate();
//        sf = session.getSessionFactory();
//        if (sf == null) {
//            sf = session.getSessionFactory();
//        }
//
//        return sf;
//    }
//
//    private Object[] mapToArray(String hql, Map<String, Object> conditionMap) {
//        String tmp = " " + hql + " ";
//        Object[] param = new Object[conditionMap.size()];
//        int i = 0;
//
//        while (true) {
//            int start = tmp.indexOf(58);
//            if (start < 0) {
//                return param;
//            }
//
//            tmp = tmp.substring(start + 1);
//            int end = tmp.indexOf(32);
//            String key;
//            if (end < 0) {
//                key = tmp.substring(0, tmp.length());
//            } else {
//                key = tmp.substring(0, end);
//            }
//
//            int m = key.indexOf(41);
//            if (m > 0) {
//                key = key.substring(0, m);
//            }
//
//            param[i++] = conditionMap.get(key);
//        }
//    }
//
//    private String improveSql(String sql) {
//        sql = " " + sql + " ";
//        String[] split = sql.split("[?]");
//
//        for (int i = 0; i < split.length; ++i) {
//            if (i != split.length - 1) {
//                split[i] = split[i] + " ?" + (i + 1) + " ";
//            }
//        }
//
//        return this.toStr(split);
//    }
//
//    private String toStr(Object[] param) {
//        StringBuilder sb = new StringBuilder();
//
//        for (int i = 0; i < param.length; ++i) {
//            sb.append(param[i].toString());
//        }
//
//        return sb.toString();
//    }
//
//    private Object getSingleResult(Query query) {
//        try {
//            return query.getSingleResult();
//        } catch (Exception var3) {
//            return null;
//        }
//    }
//}
