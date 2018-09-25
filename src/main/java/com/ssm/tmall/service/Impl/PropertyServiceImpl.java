package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.PropertyDao;
import com.ssm.tmall.pojo.Property;
import com.ssm.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "propertyService")
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    private PropertyDao propertyDao;

    @Override
    public Property get(Integer id) {
        return propertyDao.get(id);
    }

    /**
     * 获取指定产品类别的所有属性
     *
     * @param cId 产品类别 id
     * @return 属性对象列表
     */
    @Override
    public List<Property> getListByCid(Integer cId) {
        return propertyDao.getAll(cId);
    }

    /**
     * 增加一个新的属性
     *
     * @param property 新的属性对象 (只要包含名字 name 和所属类别 cId 即可)
     */
    @Override
    public void add(Property property) {
        // 后台需要自己做数据校验，不能信任前台数据
        if (property.getCid() != null && property.getName() != null) {
            propertyDao.add(property);
        }
    }

    @Override
    public void delete(Integer id) {
        if (id != null)
            propertyDao.delete(id);
    }

    /**
     * 修改属性名字
     *
     * @param property 新的属性信息
     */
    @Override
    public void update(Property property) {
        // ------做数据校验后再修改--------
        propertyDao.update(property);
    }
}
