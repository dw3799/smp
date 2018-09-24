package com.alipapa.smp.order.service;

import com.alipapa.smp.common.enums.MaterielOrderStatusEnum;
import com.alipapa.smp.order.mapper.MaterielOrderMapper;
import com.alipapa.smp.order.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterielOrderService {

    @Autowired
    private MaterielOrderMapper materielOrderMapper;


    /**
     * 获取物料订单
     *
     * @param subOrderNo
     * @return
     */
    public List<MaterielOrder> listMaterielOrderBySubOrderNo(String subOrderNo) {
        MaterielOrderExample example = new MaterielOrderExample();
        MaterielOrderExample.Criteria criteria = example.createCriteria();
        criteria.andSubOrderNoEqualTo(subOrderNo);
        criteria.andMaterielOrderStatusNotEqualTo(MaterielOrderStatusEnum.DISCARDED.getCode());

        List<MaterielOrder> materielOrderList = materielOrderMapper.selectByExample(example);
        return materielOrderList;
    }


    /**
     * 获取所有物料订单（包含已作废）
     *
     * @param subOrderNo
     * @return
     */
    public List<MaterielOrder> listAllMaterielOrderBySubOrderNo(String subOrderNo) {
        MaterielOrderExample example = new MaterielOrderExample();
        MaterielOrderExample.Criteria criteria = example.createCriteria();
        criteria.andSubOrderNoEqualTo(subOrderNo);
        example.setOrderByClause("updated_time desc");

        List<MaterielOrder> materielOrderList = materielOrderMapper.selectByExample(example);
        return materielOrderList;
    }


    /**
     * 更新物料订单
     *
     * @param materielOrder
     * @return
     */
    public boolean updateMaterielOrder(MaterielOrder materielOrder) {
        materielOrderMapper.updateByPrimaryKey(materielOrder);
        return true;
    }


    /**
     * 保存物料订单
     *
     * @param materielOrder
     * @return
     */
    public boolean saveMaterielOrder(MaterielOrder materielOrder) {
        materielOrderMapper.insert(materielOrder);
        return true;
    }


    /**
     * 删除物料订单
     *
     * @param id
     * @return
     */
    public boolean delMaterielOrder(Long id) {
        materielOrderMapper.deleteByPrimaryKey(id);
        return true;
    }


    /**
     * 查询
     *
     * @param id
     * @return
     */
    public MaterielOrder selectMaterielOrderById(Long id) {
        return materielOrderMapper.selectByPrimaryKey(id);
    }


    /**
     * @param materielOrder
     * @return
     */
    public boolean isCanEdit(MaterielOrder materielOrder) {

        if (materielOrder.getId() == null) {
            return true;
        }

        MaterielOrderStatusEnum materielOrderStatusEnum = MaterielOrderStatusEnum.valueOf(materielOrder.getMaterielOrderStatus());

        if (materielOrderStatusEnum == null) {
            return true;
        }

        if (materielOrderStatusEnum == MaterielOrderStatusEnum.CREATE || materielOrderStatusEnum == MaterielOrderStatusEnum.BUYER_ORDER) {
            return true;

        }
        return false;
    }

}
