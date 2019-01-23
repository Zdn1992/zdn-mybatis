package com.zdn.mybatis;


import com.zdn.mybatis.entity.UserPo;
import com.zdn.mybatis.mapper.UserMapper;
import com.zdn.mybatis.sqlSession.ZSqlSession;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class MybatisTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void test() {
        ZSqlSession sqlSession = new ZSqlSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserPo user = userMapper.getUser(1);
        System.out.println(user);
    }
}
