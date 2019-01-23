package com.zdn.mybatis.sqlSession;

import com.zdn.mybatis.entity.UserPo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ZExcutor implements Excutor {

    private ZConfiguration xmlConfig = new ZConfiguration();

    @Override
    public <T> T query(String statement, Object parameter) {
        Connection connection = getConnection();
        PreparedStatement pre = null;
        ResultSet result = null;
        try {
            pre = connection.prepareStatement(statement);
            // 设置参数
            pre.setInt(1,Integer.parseInt(parameter.toString()));
            result = pre.executeQuery();
            UserPo user = new UserPo();
            while (result.next()){
                user.setId(result.getInt(1));
                user.setUsername(result.getString(2));
                user.setPassword(result.getString(3));
            }
            return (T) user;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (result != null){
                try {
                    result.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (pre != null){
                try {
                    pre.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private Connection getConnection(){
        try {
            return xmlConfig.build("SqlMapperConfig.xml");
        } catch (Exception e) {
            System.out.println("getConnection error");
        }
        return null;
    }
}
