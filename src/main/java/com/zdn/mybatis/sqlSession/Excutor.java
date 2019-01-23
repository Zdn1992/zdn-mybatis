package com.zdn.mybatis.sqlSession;

public interface Excutor {

    <T> T query(String statement,Object parameter);
}
