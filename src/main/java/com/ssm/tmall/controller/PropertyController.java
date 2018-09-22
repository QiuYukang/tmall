package com.ssm.tmall.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ssm.tmall.pojo.Property;
import com.ssm.tmall.service.CategoryService;
import com.ssm.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 类别属性控制器
 */
@Controller
public class PropertyController {

    @Autowired
    private PropertyService propertyService;

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "admin_property_edit/{cid}")
    public String toEditPage(@PathVariable("cid") Integer cid, Integer id, Model model) {

        model.addAttribute("property", propertyService.get(id));
        model.addAttribute("category", categoryService.getCategoryById(cid));
        return "admin/editProperty";
    }

    @RequestMapping(value = "/admin_property_list/{cid}", method = RequestMethod.GET)
    public String toListPage(@PathVariable("cid") Integer cid,
                             Model model,
                             @RequestParam(required = false, defaultValue = "1") Integer start) {
        // 调用分页插件
        PageHelper.startPage(start, 7);
        List<Property> properties = propertyService.getListByCid(cid);
        PageInfo<Property> page = new PageInfo<>(properties, 5);

        model.addAttribute("url", "admin_property_list/" + cid);
        model.addAttribute("page", page);
        model.addAttribute("properties", properties);
        model.addAttribute("category", categoryService.getCategoryById(cid));

        return "admin/listProperty";
    }

    @RequestMapping(value = "/admin_property_list/{cid}", method = RequestMethod.POST)
    public String add(Property property, @PathVariable("cid") Integer cid) {
        propertyService.add(property);

        // 跳转前需要做数据校验
        return "redirect:/admin_property_list/" + cid;
    }

    @RequestMapping(value = "/admin_property_list/{cid}", method = RequestMethod.DELETE)
    public String delete(@PathVariable("cid") Integer cid, Integer id) {
        propertyService.delete(id);

        return "redirect:/admin_property_list/" + cid;
    }

    @RequestMapping(value = "/admin_property_list/{cid}", method = RequestMethod.PUT)
    public String update(@PathVariable("cid") Integer cid, Property property) {
        // 更新前需要在服务层做数据校验
        propertyService.update(property);

        return "redirect:/admin_property_list/" + cid;
    }
}
