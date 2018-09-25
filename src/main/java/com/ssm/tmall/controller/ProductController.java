package com.ssm.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 产品类前端控制器
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/admin_product_edit/{cid}")
    public String toEditPage(@PathVariable("cid") Integer cid, Integer id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(cid));
        model.addAttribute("product", productService.getById(id));

        return "admin/editProduct";
    }

    // 使用Restful风格的URL
    @RequestMapping(value = "/admin_product_list/{cid}", method = RequestMethod.GET)
    public String get(@PathVariable("cid") Integer cid,
                      @RequestParam(required = false, defaultValue = "1") Integer start,
                      Model model) {
        // 调用分页插件
        PageHelper.startPage(start, 5);
        List<Product> products = productService.getListBycId(cid);
        PageInfo<Product> page = new PageInfo<>(products, 4);

        Category category = categoryService.getCategoryById(cid);

        model.addAttribute("page", page);
        model.addAttribute("products", products);
        model.addAttribute("category", category);
        model.addAttribute("url", "admin_product_list/"+cid);

        return "admin/listProduct";
    }

    @RequestMapping(value = "/admin_product_list/{cid}", method = RequestMethod.POST)
    public String add(@PathVariable("cid") Integer cid, Product product) {
        productService.add(product);

        return "redirect:/admin_product_list/" + cid;
    }

    @RequestMapping(value = "/admin_product_list/{cid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("cid") Integer cid, Integer id) {
        productService.delete(id);

        return "redirect:/admin_product_list/" + cid;
    }

    @RequestMapping(value = "/admin_product_list/{cid}", method = RequestMethod.PUT)
    public String update(@PathVariable("cid") Integer cid, Product product) {
        productService.update(product);

        return "redirect:/admin_product_list/" + cid;
    }
}
