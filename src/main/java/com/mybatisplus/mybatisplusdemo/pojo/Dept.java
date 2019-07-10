package com.mybatisplus.mybatisplusdemo.pojo;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author HQ
 * @since 2019-07-10
 */
public class Dept implements Serializable {

private static final long serialVersionUID=1L;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 部门地址
     */
    private String pleace;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPleace() {
        return pleace;
    }

    public void setPleace(String pleace) {
        this.pleace = pleace;
    }

    @Override
    public String toString() {
        return "Dept{" +
        "name=" + name +
        ", pleace=" + pleace +
        "}";
    }
}
