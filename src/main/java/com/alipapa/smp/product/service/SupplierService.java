package com.alipapa.smp.product.service;

import com.alipapa.smp.product.mapper.SupplierMapper;
import com.alipapa.smp.product.mapper.SupplierProductMapper;
import com.alipapa.smp.product.pojo.Supplier;
import com.alipapa.smp.product.pojo.SupplierExample;
import com.alipapa.smp.product.pojo.SupplierProduct;
import com.alipapa.smp.product.pojo.SupplierProductExample;
import com.alipapa.smp.product.vo.SupplierVo;
import com.alipapa.smp.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
     * @param supplierProductId
     * @return
     */
    public boolean delSupplierProduct(Long supplierProductId) {
        supplierProductMapper.deleteByPrimaryKey(supplierProductId);
        return true;
    }

    /**
     * @param supplierId
     * @return
     */
    @Transactional
    public boolean delSupplierById(Long supplierId) {
        List<SupplierProduct> supplierProductList = this.listSupplierProductBySupplierId(supplierId);
        if (!CollectionUtils.isEmpty(supplierProductList)) {
            for (SupplierProduct supplierProduct : supplierProductList) {
                this.delSupplierProduct(supplierProduct.getId());
            }
        }
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
            logger.info("supplierList:" + supplierList.size());
            return supplierList;
        }
        return null;
    }


    /**
     * 供应商管理查询
     *
     * @param params
     * @return
     */
    public List<SupplierVo> listSupplierByParams(Map<String, Object> params, Integer start, Integer size) {
        Long totalCount = supplierMapper.listSupplierByParamsCount(params);

        if (totalCount <= 0) {
            return null;
        }

        params.put("start", start);
        params.put("size", size);

        List<Supplier> supplierList = supplierMapper.listSupplierByParams(params);
        List<SupplierVo> supplierVoList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(supplierList)) {
            for (Supplier supplier : supplierList) {
                SupplierVo supplierVo = new SupplierVo();
                supplierVo.setId(supplier.getId());
                supplierVo.setCharge(supplier.getCharge());
                supplierVo.setCity(supplier.getCity());
                supplierVo.setMobile(supplier.getMobile1());
                supplierVo.setTotalCount(totalCount);
                supplierVoList.add(supplierVo);
            }
        }

        return supplierVoList;
    }


}


