package com.ssm.tmall.dao;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.util.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 产品类别 DAO
 */
@Repository
public interface CategoryDao {

    /**
     * 获取所有产品类别 (使用分页插件)
     *
     * @return 产品类别对象列表
     */
    List<Category> getCategoryList();

//    手动实现分页功能
//    /**
//     * 获取分页后的产品类别
//     *
//     * @param page 分页对象
//     * @return 产品类别列表
//     */
//    List<PropertyDao> getCategoryList(Page page);

    /**
     * 获取指定 id 的类别对象
     *
     * @param id 类别 id
     * @return 类别对象
     */
    Category getCategoryById(Integer id);

    /**
     * 获取总记录条数
     *
     * @return 总记录条数
     */
    int getTotal();

    /**
     * 插入一个新的类别
     *
     * @param category 新的分类对象
     */
    void addCategory(Category category);

    /**
     * 删除指定 id 的分类
     * @param id 类别 id
     */
    void deleteCategoryById(Integer id);

    /**
     * 修改分类信息
     *
     * @param category 新的分类信息
     */
    void updateCategory(Category category);
}
