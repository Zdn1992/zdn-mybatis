package com.zdn.mybatis.sqlSession;

import com.zdn.mybatis.config.Function;
import com.zdn.mybatis.config.MapperBean;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ZConfiguration {

    private static ClassLoader loader = ClassLoader.getSystemClassLoader();

    public Connection build(String resource) {
        try {
            InputStream in = loader.getResourceAsStream(resource);
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            return evalDataSource(root);
        } catch (Exception e) {
            throw new RuntimeException("error occured while evaling xml " + resource);
        }
    }

    private Connection evalDataSource(Element node) throws ClassNotFoundException {
        if (!"database".equals(node.getName())) {
            throw new RuntimeException("root should be <database>");
        }

        String driverClassName = null;
        String url = null;
        String username = null;
        String password = null;
        for (Object item : node.elements("property")) {
            Element element = (Element) item;
            String value = getValue(element);
            String name = element.attributeValue("name");
            if (name == null || value == null) {
                throw new RuntimeException("[database]: <property> unknown name");
            }
            // 赋值
            switch (name) {
                case "url":
                    url = value;
                    break;
                case "username":
                    username = value;
                    break;
                case "password":
                    password = value;
                    break;
                case "driverClassName":
                    driverClassName = value;
                    break;
                default:
                    throw new RuntimeException("[database]: <property> unknown name");
            }
        }
        Class.forName(driverClassName);
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    //获取property属性的值,如果有value值,则读取 没有设置value,则读取内容
    private String getValue(Element node) {
        return node.hasContent() ? node.getText() : node.attributeValue("value");
    }

    /**
     * 获取Mapper.xml中的相关信息
     * @param path
     * @return
     */
    public MapperBean readMapper(String path){
        MapperBean mapper = new MapperBean();
        try {
            InputStream in = loader.getResourceAsStream(path);
            SAXReader reader = new SAXReader();
            Document document = reader.read(in);
            Element root = document.getRootElement();
            List<Function> functionList = new ArrayList<>();
            for(Iterator iterator = root.elementIterator();iterator.hasNext();){
                Function function = new Function();
                Element e = (Element) iterator.next();
                String sqlType = e.getName().trim();
                String funcName = e.attributeValue("id");
                String sql = e.getText().trim();
                String resultType = e.attributeValue("resultType").trim();
                function.setSqlType(sqlType);
                function.setFuncName(funcName);
                function.setSql(sql);
                Object newInstance = Class.forName(resultType).newInstance();
                function.setResultType(newInstance);
                functionList.add(function);
            }
            // 将Mapper的配置存入MapperBean
            mapper.setInterfaceName(root.attributeValue("namespace").trim());
            mapper.setFunctions(functionList);
        }catch (Exception e){
            System.out.println("readMapper error " + e);
        }
        return mapper;
    }
}
