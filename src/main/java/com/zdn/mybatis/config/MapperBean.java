package com.zdn.mybatis.config;

import java.util.List;

public class MapperBean {

    /**
    * 接口名
    */
    private String interfaceName;

    /**
     * 接口下所有的方法
     */
    private List<Function> functions;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }
}
