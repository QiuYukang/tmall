package com.ssm.tmall.service;

import com.ssm.tmall.pojo.ProductImage;

import java.util.List;

public interface ProductImageService {
    // 接口中的成员变量默认是 static final的
    // 单个图片
    String type_single = "type_single";
    // 详情图片
    String type_detail = "type_detail";

    void add(ProductImage productImage);

    void delete(int id);

    void update(ProductImage productImage);

    ProductImage get(int id);

    /**
     * 获取指定产品的所有图片
     *
     * @param pid  产品 id
     * @param type 图片类型
     * @return 产品图片列表
     */
    List<ProductImage> getList(int pid, String type);
}
