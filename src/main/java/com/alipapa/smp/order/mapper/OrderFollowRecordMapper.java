package com.alipapa.smp.order.mapper;

import com.alipapa.smp.order.pojo.OrderFollowRecord;
import com.alipapa.smp.order.pojo.OrderFollowRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderFollowRecordMapper {
    long countByExample(OrderFollowRecordExample example);

    int deleteByExample(OrderFollowRecordExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderFollowRecord record);

    int insertSelective(OrderFollowRecord record);

    List<OrderFollowRecord> selectByExample(OrderFollowRecordExample example);

    OrderFollowRecord selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderFollowRecord record, @Param("example") OrderFollowRecordExample example);

    int updateByExample(@Param("record") OrderFollowRecord record, @Param("example") OrderFollowRecordExample example);

    int updateByPrimaryKeySelective(OrderFollowRecord record);

    int updateByPrimaryKey(OrderFollowRecord record);
}