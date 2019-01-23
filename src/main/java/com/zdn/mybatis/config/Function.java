package com.zdn.mybatis.config;

public class Function {

    /**
    * sql类型
    */
    private String sqlType;

    /**
    * 方法名
    */
    private String funcName;

    /**
    * sql语句
    */
    private String sql;

    /**
    * 入参类型
    */
    private String parameterType;

    /**
     * 返回结果类型
     */
    private Object resultType;

    public String getSqlType() {
        return sqlType;
    }

    public void setSqlType(String sqlType) {
        this.sqlType = sqlType;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getParameterType() {
        return parameterType;
    }

    public void setParameterType(String parameterType) {
        this.parameterType = parameterType;
    }

    public Object getResultType() {
        return resultType;
    }

    public void setResultType(Object resultType) {
        this.resultType = resultType;
    }
}
