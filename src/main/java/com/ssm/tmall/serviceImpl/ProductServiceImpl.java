package com.ssm.tmall.serviceImpl;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Override
    public void add(Product product) {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void update(Product product) {

    }

    @Override
    public Product getById(Integer id) {
        return null;
    }

    /**
     * 获取指定分类下的所有产品
     *
     * @param cId 类别 id
     * @return 产品列表
     */
    @Override
    public List<Product> getListBycId(Integer cId) {
        return null;
    }
}
