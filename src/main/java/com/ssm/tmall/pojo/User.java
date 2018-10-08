package com.ssm.tmall.pojo;

import java.util.Date;

public class User {
    private Integer id;

    private String name;

    private String password;

    private Date createDate;

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
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     * 获取匿名名字 真实名字中间带有 * 号
     *
     * @return
     */
    public String getAnonymousName() {
        // ---如果是中文名字如何处理？？？？？
        if (null == this.name)
            return null;
        if (this.name.length() <= 1)
            return "*";
        if (this.name.length() == 2)
            return name.substring(0, 1) + "*";

        char[] nameArray = this.name.toCharArray();
        for (int i = 1; i < nameArray.length - 1; i++) {
            nameArray[i] = '*';
        }

        return new String(nameArray);
    }
}