package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.SupplierMapper;
import com.alipapa.smp.product.mapper.SupplierProductMapper;
import com.alipapa.smp.product.pojo.*;
import com.alipapa.smp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SupplierService {

    private static Logger logger = LoggerFactory.getLogger(SupplierService.class);

    @Autowired
    private SupplierMapper supplierMapper;


    @Autowired
    private SupplierProductMapper supplierProductMapper;


    /**
     * @param supplierProduct
     * @return
     */
    public boolean saveSupplierProduct(SupplierProduct supplierProduct) {
        supplierProductMapper.insert(supplierProduct);
        return true;
    }


    /**
     * @param supplier
     * @return
     */
    public boolean saveSupplier(Supplier supplier) {
        supplierMapper.insert(supplier);
        return true;
    }


    /**
     * @param supplierId
     * @return
     */
    public boolean delSupplier(Long supplierId) {
        supplierMapper.deleteByPrimaryKey(supplierId);
        return true;
    }


    /**
     * @param supplier
     * @return
     */
    public boolean updateSupplier(Supplier supplier) {
        supplierMapper.updateByPrimaryKey(supplier);
        return true;
    }


    /**
     * @param supplierId
     * @return
     */
    public Supplier getSupplierById(Long supplierId) {
        return supplierMapper.selectByPrimaryKey(supplierId);
    }


    /**
     * @param name
     * @return
     */
    public Supplier getSupplierByName(String name) {

        if (StringUtil.isEmptyString(name)) {
            return null;
        }
        SupplierExample example = new SupplierExample();
        SupplierExample.Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(name);

        List<Supplier> supplierList = supplierMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(supplierList)) {
            return supplierList.get(0);
        }
        return null;
    }


    /**
     * @param supplierId
     * @return
     */
    public List<SupplierProduct> listSupplierProductBySupplierId(Long supplierId) {
        if (supplierId == null) {
            return null;
        }
        SupplierProductExample example = new SupplierProductExample();
        SupplierProductExample.Criteria criteria = example.createCriteria();
        criteria.andSupplierIdEqualTo(supplierId);

        List<SupplierProduct> supplierList = supplierProductMapper.selectByExample(example);
        if (!CollectionUtils.isEmpty(supplierList)) {
            return supplierList;
        }
        return null;
    }

}


