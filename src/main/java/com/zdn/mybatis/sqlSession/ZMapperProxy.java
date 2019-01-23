package com.zdn.mybatis.sqlSession;

import com.zdn.mybatis.config.Function;
import com.zdn.mybatis.config.MapperBean;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

public class ZMapperProxy implements InvocationHandler {

    private ZConfiguration config;

    private ZSqlSession sqlSession;

    public ZMapperProxy(ZConfiguration config, ZSqlSession sqlSession) {
        this.config = config;
        this.sqlSession = sqlSession;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MapperBean readMapper = config.readMapper("UserMapper.xml");
        // 判断是否是xml文件对应的接口
        if (!method.getDeclaringClass().getName().equals(readMapper.getInterfaceName())){
            return null;
        }
        List<Function> functions = readMapper.getFunctions();
        if (null != functions && 0 != functions.size()){
            for (Function function : functions) {
                // id是否和接口方法名一致
                if (method.getName().equals(function.getFuncName())){
                    return sqlSession.selectOne(function.getSql(),String.valueOf(args[0]));
                }
                
            }
        }
        return null;
    }
}
