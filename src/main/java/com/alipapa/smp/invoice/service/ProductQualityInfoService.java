package com.alipapa.smp.invoice.service;

import com.alipapa.smp.invoice.mapper.ProductQualityInfoMapper;
import com.alipapa.smp.invoice.pojo.ProductQualityInfo;
import com.alipapa.smp.invoice.pojo.ProductQualityInfoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ProductQualityInfoService {


    @Autowired
    private ProductQualityInfoMapper productQualityInfoMapper;


    public boolean saveProductQualityInfo(ProductQualityInfo record) {
        if (record.getId() == null) {
            productQualityInfoMapper.insert(record);
            return true;
        } else {
            productQualityInfoMapper.updateByPrimaryKey(record);
            return true;
        }
    }


    /**
     * 保存质检信息
     *
     * @param record
     * @return
     */
    public boolean insert(ProductQualityInfo record) {
        productQualityInfoMapper.insert(record);
        return true;
    }

    /**
     * 更新质检信息
     *
     * @param record
     * @return
     */
    public boolean update(ProductQualityInfo record) {
        productQualityInfoMapper.updateByPrimaryKey(record);
        return true;
    }


    /**
     * 获取质检信息
     *
     * @param subOrderNo
     * @return
     */
    public ProductQualityInfo getProductQualityInfoBySubOrderNo(String subOrderNo) {
        ProductQualityInfoExample example = new ProductQualityInfoExample();
        ProductQualityInfoExample.Criteria criteria = example.createCriteria();
        criteria.andSubOrderNoEqualTo(subOrderNo);
        List<ProductQualityInfo> productQualityInfoList = productQualityInfoMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(productQualityInfoList)) {
            return null;
        }
        return productQualityInfoList.get(0);
    }


}
