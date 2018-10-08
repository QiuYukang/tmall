package com.ssm.tmall.service.Impl;

import com.ssm.tmall.dao.PropertyValueDao;
import com.ssm.tmall.pojo.PropertyValue;
import com.ssm.tmall.pojo.PropertyValueExample;
import com.ssm.tmall.service.PropertyService;
import com.ssm.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("propertyValueService")
public class PropertyValueServiceImpl implements PropertyValueService {
    @Autowired
    private PropertyValueDao propertyValueDao;

    @Autowired
    private PropertyService propertyService;

    @Override
    public void add(PropertyValue propertyValue) {
        propertyValueDao.insert(propertyValue);
    }

    @Override
    public void delete(Integer id) {
        propertyValueDao.deleteByPrimaryKey(id);
    }

    @Override
    public void update(PropertyValue propertyValue) {
        propertyValueDao.updateByPrimaryKeySelective(propertyValue);
    }

    /**
     * 获取指定产品的指定属性
     *
     * @param pid  产品 id
     * @param ptid 产品属性 id
     * @return
     */
    @Override
    public PropertyValue get(Integer pid, Integer ptid) {
        PropertyValueExample example = new PropertyValueExample();
        example.createCriteria().andPidEqualTo(pid).andPtidEqualTo(ptid);

        List<PropertyValue> values =  propertyValueDao.selectByExample(example);
        if(values.isEmpty()){
            return null;
        }

        return values.get(0);
    }

    /**
     * 获取指定产品的所有属性值
     *
     * @param pid 产品 id
     * @return
     */
    @Override
    public List<PropertyValue> getList(Integer pid) {
        PropertyValueExample example = new PropertyValueExample();
        example.setOrderByClause("id desc");
        example.createCriteria().andPidEqualTo(pid);
        List<PropertyValue> values = propertyValueDao.selectByExample(example);
        // 给属性值设置属性内容(方便展示属性值对应的属性名字)
        for(PropertyValue value: values) {
            Integer ptid = value.getPtid();
            value.setProperty(propertyService.get(ptid));
        }

        return values;
    }
}
