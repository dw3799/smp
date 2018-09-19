package com.alipapa.smp.order.service;

import com.alipapa.smp.order.mapper.PurchaseOrderExtMapper;
import com.alipapa.smp.order.pojo.PurchaseOrderExt;
import com.alipapa.smp.order.pojo.PurchaseOrderExtExample;
import com.alipapa.smp.order.pojo.SubOrder;
import com.alipapa.smp.order.pojo.SubOrderExample;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PurchaseOrderExtService {

    @Resource
    private PurchaseOrderExtMapper purchaseOrderExtMapper;


    public boolean savePurchaseOrderExt(PurchaseOrderExt purchaseOrderExt) {
        purchaseOrderExtMapper.insert(purchaseOrderExt);
        return true;
    }


    public boolean updatePurchaseOrderExt(PurchaseOrderExt purchaseOrderExt) {
        purchaseOrderExtMapper.updateByPrimaryKey(purchaseOrderExt);
        return true;
    }


    /**
     * 获取采购订单扩展信息
     *
     * @param subOrderNo
     * @return
     */
    public PurchaseOrderExt getPurchaseOrderExtBySubOrderNo(String subOrderNo) {
        PurchaseOrderExtExample example = new PurchaseOrderExtExample();
        PurchaseOrderExtExample.Criteria criteria = example.createCriteria();
        criteria.andSubOrderNoEqualTo(subOrderNo);
        criteria.andIsDelEqualTo(0);
        example.setOrderByClause("created_time desc");

        List<PurchaseOrderExt> purchaseOrderExtList = purchaseOrderExtMapper.selectByExample(example);

        if (CollectionUtils.isEmpty(purchaseOrderExtList)) {
            return null;
        }
        return purchaseOrderExtList.get(0);
    }


}
