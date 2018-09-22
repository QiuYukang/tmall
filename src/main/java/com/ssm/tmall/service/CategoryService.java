package com.ssm.tmall.service;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.util.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;

public interface CategoryService {
    /**
     * 获取所有产品类别 (使用分页插件)
     *
     * @return 产品类别对象列表
     */
    List<Category> getCategoryList();

//    不使用分页插件，自己实现分页功能
//    /**
//     * 获取总记录条数  (不使用分页插件)
//     *
//     * @return 总记录条数
//     */
//    int getTotal();
//
//    /**
//     * 按照分页要求获取产品类别 (不使用分页插件)
//     *
//     * @param page 分页对象
//     * @return 产品类别对象列表
//     */
//    List<PropertyDao> getCategoryList(Page page);

    /**
     * 新增分类信息
     *
     * @param category 新的分类对象（包含分类·名字即可）
     * @param imageFolder 存放分类图片的文件夹路径
     * @param imageFile 网页端上传的分类文件
     */
    void addCategory(Category category, File imageFolder, MultipartFile imageFile);

    /**
     * 删除指定 id 的分类信息(包括图片)
     * @param id
     * @param imageFolder
     */
    void deleteCategoryById(Integer id, File imageFolder);

    /**
     * 获取指定 id 的分类对象
     *
     * @param id 类别 id
     * @return 类别对象
     */
    Category getCategoryById(Integer id);

    /**
     * 更新指定 id 的类别信息
     *
     * @param category 新的类别信息
     * @param imageFolder 要更新的图片文件夹路径
     * @param imageFile 网页端上传的图片文件
     */
    void updateCategory(Category category, File imageFolder, MultipartFile imageFile);
}
