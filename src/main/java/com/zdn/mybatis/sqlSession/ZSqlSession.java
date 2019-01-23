package com.zdn.mybatis.sqlSession;

import java.lang.reflect.Proxy;

public class ZSqlSession {

    private Excutor excutor = new ZExcutor();

    private ZConfiguration config = new ZConfiguration();

    public <T> T selectOne(String statement,Object parameter){
        return excutor.query(statement,parameter);
    }

    public <T> T getMapper(Class<T> clazz){
        return (T) Proxy.newProxyInstance(clazz.getClassLoader(),new Class[]{clazz},
                new ZMapperProxy(config,this));
    }
}
