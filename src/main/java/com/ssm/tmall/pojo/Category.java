package com.ssm.tmall.pojo;

/**
 * 产品类别
 */
public class Category {
    private Integer id; // 类别 id
    private String name;    // 类别名称，如平板电脑，沙发等

    public Category() {
    }

    public Category(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertyDao{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
