package com.zhangmiao.networktest10_2;

/**
 * Author: zhangmiao
 * Date: 2017/8/1
 */
public class App {

    private String id;
    private String name;
    private String version;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "id : " + id + ",name : " + name + ",version : " + version;
    }
}
