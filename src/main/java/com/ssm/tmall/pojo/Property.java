package com.ssm.tmall.pojo;


/**
 * 属性类
 */
public class Property {
    // 属性 id
    private Integer id;
    // 所属产品类别 id
    private Integer cid;
    // 属性名
    private String name;

    public Property() {
    }

    public Property(Integer id, Integer cid, String name) {
        this.id = id;
        this.cid = cid;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
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
                ", cid=" + cid +
                ", name='" + name + '\'' +
                '}';
    }
}
