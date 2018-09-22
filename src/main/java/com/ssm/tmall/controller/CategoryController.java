package com.ssm.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 产品类别控制器
 */
@Controller
public class CategoryController {

    // 自动注入 Service 层
    @Autowired
    private CategoryService categoryService;

    // 获得不分页的分类列表(后台管理页面，自动实现分页)
//    @RequestMapping(value = "/admin_category_list", method = RequestMethod.GET)
//    public String list(Map<String, Object> map, Page page) {
//        // 调用分页插件
//        PageHelper.offsetPage(page.getStart(), page.getCount());
//        List<PropertyDao> categories = categoryService.getCategoryList();
//
//        // 获取总页数及页数信息
//        PageInfo<PropertyDao> pageInfo = new PageInfo<>(categories);
//        int total = (int) pageInfo.getTotal();
//        page.setTotal(total);
//
//        map.put("page", page);
//        map.put("categories", categories);
//
//        return "admin/listCategory";
//    }

    // 改进版分页，单纯使用分页插件
    @RequestMapping(value = "/admin_category_list", method = RequestMethod.GET)
    public String list(Map<String, Object> map,
                       @RequestParam(required = false, defaultValue = "1")Integer start) {
        // 调用分页插件
        PageHelper.startPage(start, 4);
        List<Category> categories = categoryService.getCategoryList();
        PageInfo<Category> page = new PageInfo<>(categories, 4);

        map.put("url", "admin_category_list");
        map.put("page", page);
        map.put("categories", categories);

        return "admin/listCategory";
    }

    // 获取分页的分类列表(后台管理界面，手动实现分页)
//    @RequestMapping(value = "/admin_category_list", method = RequestMethod.GET)
//    public String getCategoryList(Map<String, Object> map, Page page) {
//        // 参数start会自动填充到page的成员变量中
//        // System.out.println(page);
//        List<PropertyDao> categories = categoryService.getCategoryList(page);
//        int total = categoryService.getTotal();
//        page.setTotal(total);
//
//        map.put("page", page);
//        map.put("categories", categories);
//
//        return "admin/listCategory";
//    }

    // 新增产品分类信息
    @RequestMapping(value = "/admin_category_list", method = RequestMethod.POST)
    public String add(MultipartFile image, Category category, HttpSession session) {
        // 建立保存图片文件的文件夹路径
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        categoryService.addCategory(category, imageFolder, image);

        return "redirect:/admin_category_list";
    }

    // 删除指定 id 的分类
    @RequestMapping(value = "/admin_category_list/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("id") Integer id, HttpSession session) {
        categoryService.deleteCategoryById(id, new File(session.getServletContext().getRealPath("img/category")));

        return "redirect:/admin_category_list";
    }

    // 跳转到编辑页面
    @RequestMapping(value = "/admin_category_edit/{id}")
    public String toEditPage(Model model, @PathVariable("id")Integer id) {
        model.addAttribute("category", categoryService.getCategoryById(id));

        return "admin/editCategory";
    }

    // 修改分类信息
    @RequestMapping(value = "/admin_category_list", method = RequestMethod.PUT)
    public String update(MultipartFile newImage, HttpSession session, Category category) {
        // 建立保存图片文件的文件夹路径
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        categoryService.updateCategory(category, imageFolder, newImage);
        // ---待完善---自动跳转到含有修改项的页面
        return "redirect:/admin_category_list";
    }
}
