package com.ssm.tmall.serviceImpl;

import com.ssm.tmall.dao.CategoryDao;
import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.util.ImageUtil;
import com.ssm.tmall.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service(value = "categoryService")
public class CategoryServiceImpl implements CategoryService {

    // 自动注入 DAO 层
    @Autowired
    private CategoryDao categoryDao;

    /**
     * 获取所有产品类别
     *
     * @return 产品类别对象列表
     */
    @Override
    public List<Category> getCategoryList() {
        return categoryDao.getCategoryList();
    }


//    不使用分页插件，自己实现分页功能
//    /**
//     * 获取总记录条数
//     *
//     * @return 总记录条数
//     */
//    @Override
//    public int getTotal() {
//        return categoryDao.getTotal();
//    }
//
//    /**
//     * 按照分页要求获取产品类别
//     *
//     * @param page 分页对象
//     * @return 产品类别对象列表
//     */
//    @Override
//    public List<PropertyDao> getCategoryList(Page page) {
//        return categoryDao.getCategoryList(page);
//    }

    /**
     * 新增分类信息
     *
     * @param category    新的分类对象（包含分类·名字即可）
     * @param imageFolder 存放分类图片的文件夹路径
     * @param imageFile   网页端上传的分类图片文件
     */
    @Override
    public void addCategory(Category category, File imageFolder, MultipartFile imageFile) {
        // 添加分类数据到数据库中(只有先添加到数据库中，才能获取到自动生成的id值，否则为null)
        categoryDao.addCategory(category);
        // 存储图片
        this.saveImage(imageFolder, imageFile, category.getId());
    }

    /**
     * 删除指定 id 的分类信息(包括图片)
     *
     * @param id
     * @param imageFolder
     */
    @Override
    public void deleteCategoryById(Integer id, File imageFolder) {
        // 删除数据库信息
        categoryDao.deleteCategoryById(id);
        // 删除图片信息
        File file = new File(imageFolder, id + ".jpg");
        file.delete();
    }

    /**
     * 获取指定 id 的分类对象
     *
     * @param id 类别 id
     * @return 类别对象
     */
    @Override
    public Category getCategoryById(Integer id) {
        return categoryDao.getCategoryById(id);
    }

    /**
     * 更新指定 id 的类别信息
     *
     * @param category    新的类别信息
     * @param imageFolder 要更新的图片文件(为空则表示不更新)
     */
    @Override
    public void updateCategory(Category category, File imageFolder, MultipartFile imageFile) {
        categoryDao.updateCategory(category);
        // 若要更新图片则存储
        if(imageFile.getSize() != 0){
            this.saveImage(imageFolder, imageFile, category.getId());
        }
    }

    public void saveImage(File imageFolder, MultipartFile imageFile, Integer id){
        // 存放文件到对应的文件夹中
        // 按照类别 id 命名图片文件
        File file = new File(imageFolder, id + ".jpg");
        // 判定文件夹是否存在
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        try {
            imageFile.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            // 异常处理？？？？？？上传非jpg文件
            e.printStackTrace();
        }
    }
}
