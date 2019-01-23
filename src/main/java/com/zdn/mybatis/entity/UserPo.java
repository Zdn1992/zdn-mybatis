package com.zdn.mybatis.entity;

public class UserPo {
    
    /**
    * 用户Id
    */
    private Integer id; 
    
    /**
    * 用户名称
    */
    private String username; 
    
    /**
    * 用户密码
    */
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserPo{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
