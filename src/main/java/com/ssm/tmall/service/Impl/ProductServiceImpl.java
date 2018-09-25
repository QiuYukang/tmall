package com.ssm.tmall.service.Impl;

import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.dao.ProductDao;
import com.ssm.tmall.pojo.ProductExample;
import com.ssm.tmall.pojo.ProductImage;
import com.ssm.tmall.service.ProductImageService;
import com.ssm.tmall.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImageService productImageService;

    @Override
    public void add(Product product) {
        // 添加时间???
        product.setCreateDate(new Date());
        productDao.insert(product);
    }

    @Override
    public void delete(Integer id) {
        productDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(Product product) {
        // 写入新的更新时间
        product.setCreateDate(new Date());
        productDao.updateByPrimaryKeySelective(product);
    }

    @Override
    public Product getById(Integer id) {
        Product product = productDao.selectByPrimaryKey(id);
        this.setFirstProductIamge(product);

        return product;
    }

    /**
     * 获取指定分类下的所有产品
     *
     * @param cid 类别 id
     * @return 产品列表
     */
    @Override
    public List<Product> getListBycId(Integer cid) {
        ProductExample productExample = new ProductExample();
        // 设置条件
        productExample.createCriteria().andCidEqualTo(cid);
//        productExample.setOrderByClause("id desc");
        // 按照更新日期先后排序
        productExample.setOrderByClause("createDate desc");
        List<Product> products = productDao.selectByExample(productExample);
        // 给产品设置一张图片
        this.setFirstProductIamge(products);

        return products;
    }

    /**
     * 给产品设置第一张图片
     *
     * @param product 产品对象
     */
    @Override
    public void setFirstProductIamge(Product product) {
        List<ProductImage> images = productImageService.getList(product.getId(),
                ProductImageService.type_single);
        if (!images.isEmpty()) {
            ProductImage image = images.get(0);
            product.setFirstProductImage(image);
        }
    }

    public void setFirstProductIamge(List<Product> products) {
        for(Product product: products){
            this.setFirstProductIamge(product);
        }
    }
}
