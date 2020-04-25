package com.lagou.sqlSession;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;

import java.util.List;

public interface Executor {

    /**
     * 获取数据
     *
     * @param configuration
     * @param mappedStatement
     * @param params
     * @param <E>
     * @return
     * @throws Exception
     */
    public <E> List<E> query(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    public void insert(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    public void update(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;

    public void delete(Configuration configuration, MappedStatement mappedStatement, Object... params) throws Exception;


}
