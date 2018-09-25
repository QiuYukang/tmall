package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.ProductImageDao;
import com.ssm.tmall.pojo.ProductImage;
import com.ssm.tmall.pojo.ProductImageExample;
import com.ssm.tmall.service.ProductImageService;
import com.ssm.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService {
    @Autowired
    private ProductImageDao productImageDao;

    @Override
    public void add(ProductImage productImage) {
        productImageDao.insert(productImage);
    }

    @Override
    public void delete(int id) {
        productImageDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(ProductImage productImage) {
        productImageDao.updateByPrimaryKeySelective(productImage);
    }

    @Override
    public ProductImage get(int id) {
        return productImageDao.selectByPrimaryKey(id);
    }

    /**
     * 获取指定产品的所有图片
     *
     * @param pid  产品 id
     * @param type 图片类型
     * @return 产品图片列表
     */
    @Override
    public List<ProductImage> getList(int pid, String type) {
        ProductImageExample example = new ProductImageExample();
        example.setOrderByClause("id desc");
        // 设置查找条件
        example.createCriteria().andPidEqualTo(pid).andTypeEqualTo(type);

        return productImageDao.selectByExample(example);
    }
}
