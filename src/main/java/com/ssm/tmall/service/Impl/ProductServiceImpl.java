package com.ssm.tmall.service.Impl;

import com.ssm.tmall.pojo.Category;
import com.ssm.tmall.pojo.Product;
import com.ssm.tmall.dao.ProductDao;
import com.ssm.tmall.pojo.ProductExample;
import com.ssm.tmall.pojo.ProductImage;
import com.ssm.tmall.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductImageService productImageService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private OrderItemService orderItemService;

    @Autowired
    private CategoryService categoryService;

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
        List<ProductImage> singleImages = productImageService.getList(id, ProductImageService.type_single);
        List<ProductImage> detailImages = productImageService.getList(id, ProductImageService.type_detail);

        // 设置分类信息
        this.setCategory(product);

        // 设置图片信息
        this.setFirstProductIamge(product);
        product.setProductSingleImages(singleImages);
        product.setProductDetailImages(detailImages);

        // 设置销量和评价数量信息
        this.setSaleAndReviewNumber(product);

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
        // 设置分类信息
        this.setCategory(products);
        // 给产品设置一张图片
        this.setFirstProductImage(products);
        // 给产品填充评价数量和销量
        this.setSaleAndReviewNumber(products);

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

    /**
     * 给分类对象填充相应的产品内容
     *
     * @param category 分类对象
     */
    @Override
    public void fill(Category category) {
        List<Product> products = this.getListBycId(category.getId());
        category.setProducts(products);
    }

    /**
     * 为多个分类对象填充产品集合
     *
     * @param categories
     */
    @Override
    public void fill(List<Category> categories) {
        for (Category category : categories) {
            this.fill(category);
        }
    }

    /**
     * 为多个分类填充推荐产品集合，按照 8 个为 1 行，拆成多行，以便后续页面进行显示
     *
     * @param categories
     */
    @Override
    public void fillByRow(List<Category> categories) {
        final int numberOfEachRow = 8;
        for (Category category : categories) {
            List<Product> products = category.getProducts();
            List<List<Product>> productsByRow = new ArrayList<>();
            for (int i = 0; i < products.size(); i += numberOfEachRow) {
                int size = i + numberOfEachRow;
                size = size > products.size() ? products.size() : size;
                List<Product> productsOfEachRow = products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
            category.setProductsByRow(productsByRow);
        }
    }

    /**
     * 给产品填充销量和评价总数信息
     *
     * @param product
     */
    @Override
    public void setSaleAndReviewNumber(Product product) {
        product.setSaleCount(orderItemService.getSaleCount(product.getId()));
        product.setReviewCount(reviewService.getCount(product.getId()));
    }

    @Override
    public void setSaleAndReviewNumber(List<Product> products) {
        for (Product product : products) {
            this.setSaleAndReviewNumber(product);
        }
    }

    // 搜索，模糊查询
    @Override
    public List<Product> search(String keyword) {
        ProductExample example = new ProductExample();
        example.createCriteria().andNameLike("%" + keyword + "%");
        example.setOrderByClause("id desc");
        List<Product> products = productDao.selectByExample(example);

        this.setCategory(products);
        this.setSaleAndReviewNumber(products);
        this.setFirstProductImage(products);

        return products;
    }

    public void setFirstProductImage(List<Product> products) {
        for (Product product : products) {
            this.setFirstProductIamge(product);
        }
    }

    // 给产品填充分类信息
    private void setCategory(Product product) {
        Category category = categoryService.getCategoryById(product.getCid());
        product.setCategory(category);
    }

    private void setCategory(List<Product> products) {
        for (Product product : products) {
            this.setCategory(product);
        }
    }
}
