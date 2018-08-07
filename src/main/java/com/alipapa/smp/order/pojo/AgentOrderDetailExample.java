package com.alipapa.smp.order.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AgentOrderDetailExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AgentOrderDetailExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoIsNull() {
            addCriterion("sub_order_no is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoIsNotNull() {
            addCriterion("sub_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoEqualTo(String value) {
            addCriterion("sub_order_no =", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotEqualTo(String value) {
            addCriterion("sub_order_no <>", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoGreaterThan(String value) {
            addCriterion("sub_order_no >", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("sub_order_no >=", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoLessThan(String value) {
            addCriterion("sub_order_no <", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoLessThanOrEqualTo(String value) {
            addCriterion("sub_order_no <=", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoLike(String value) {
            addCriterion("sub_order_no like", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotLike(String value) {
            addCriterion("sub_order_no not like", value, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoIn(List<String> values) {
            addCriterion("sub_order_no in", values, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotIn(List<String> values) {
            addCriterion("sub_order_no not in", values, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoBetween(String value1, String value2) {
            addCriterion("sub_order_no between", value1, value2, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSubOrderNoNotBetween(String value1, String value2) {
            addCriterion("sub_order_no not between", value1, value2, "subOrderNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoIsNull() {
            addCriterion("sale_no is null");
            return (Criteria) this;
        }

        public Criteria andSaleNoIsNotNull() {
            addCriterion("sale_no is not null");
            return (Criteria) this;
        }

        public Criteria andSaleNoEqualTo(String value) {
            addCriterion("sale_no =", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoNotEqualTo(String value) {
            addCriterion("sale_no <>", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoGreaterThan(String value) {
            addCriterion("sale_no >", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoGreaterThanOrEqualTo(String value) {
            addCriterion("sale_no >=", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoLessThan(String value) {
            addCriterion("sale_no <", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoLessThanOrEqualTo(String value) {
            addCriterion("sale_no <=", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoLike(String value) {
            addCriterion("sale_no like", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoNotLike(String value) {
            addCriterion("sale_no not like", value, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoIn(List<String> values) {
            addCriterion("sale_no in", values, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoNotIn(List<String> values) {
            addCriterion("sale_no not in", values, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoBetween(String value1, String value2) {
            addCriterion("sale_no between", value1, value2, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleNoNotBetween(String value1, String value2) {
            addCriterion("sale_no not between", value1, value2, "saleNo");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNull() {
            addCriterion("sale_amount is null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIsNotNull() {
            addCriterion("sale_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSaleAmountEqualTo(Long value) {
            addCriterion("sale_amount =", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotEqualTo(Long value) {
            addCriterion("sale_amount <>", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThan(Long value) {
            addCriterion("sale_amount >", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("sale_amount >=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThan(Long value) {
            addCriterion("sale_amount <", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountLessThanOrEqualTo(Long value) {
            addCriterion("sale_amount <=", value, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountIn(List<Long> values) {
            addCriterion("sale_amount in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotIn(List<Long> values) {
            addCriterion("sale_amount not in", values, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountBetween(Long value1, Long value2) {
            addCriterion("sale_amount between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andSaleAmountNotBetween(Long value1, Long value2) {
            addCriterion("sale_amount not between", value1, value2, "saleAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountIsNull() {
            addCriterion("factory_amount is null");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountIsNotNull() {
            addCriterion("factory_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountEqualTo(Long value) {
            addCriterion("factory_amount =", value, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountNotEqualTo(Long value) {
            addCriterion("factory_amount <>", value, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountGreaterThan(Long value) {
            addCriterion("factory_amount >", value, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("factory_amount >=", value, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountLessThan(Long value) {
            addCriterion("factory_amount <", value, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountLessThanOrEqualTo(Long value) {
            addCriterion("factory_amount <=", value, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountIn(List<Long> values) {
            addCriterion("factory_amount in", values, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountNotIn(List<Long> values) {
            addCriterion("factory_amount not in", values, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountBetween(Long value1, Long value2) {
            addCriterion("factory_amount between", value1, value2, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andFactoryAmountNotBetween(Long value1, Long value2) {
            addCriterion("factory_amount not between", value1, value2, "factoryAmount");
            return (Criteria) this;
        }

        public Criteria andUnitIsNull() {
            addCriterion("unit is null");
            return (Criteria) this;
        }

        public Criteria andUnitIsNotNull() {
            addCriterion("unit is not null");
            return (Criteria) this;
        }

        public Criteria andUnitEqualTo(String value) {
            addCriterion("unit =", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotEqualTo(String value) {
            addCriterion("unit <>", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThan(String value) {
            addCriterion("unit >", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitGreaterThanOrEqualTo(String value) {
            addCriterion("unit >=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThan(String value) {
            addCriterion("unit <", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLessThanOrEqualTo(String value) {
            addCriterion("unit <=", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitLike(String value) {
            addCriterion("unit like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotLike(String value) {
            addCriterion("unit not like", value, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitIn(List<String> values) {
            addCriterion("unit in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotIn(List<String> values) {
            addCriterion("unit not in", values, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitBetween(String value1, String value2) {
            addCriterion("unit between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andUnitNotBetween(String value1, String value2) {
            addCriterion("unit not between", value1, value2, "unit");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountIsNull() {
            addCriterion("single_package_count is null");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountIsNotNull() {
            addCriterion("single_package_count is not null");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountEqualTo(Integer value) {
            addCriterion("single_package_count =", value, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountNotEqualTo(Integer value) {
            addCriterion("single_package_count <>", value, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountGreaterThan(Integer value) {
            addCriterion("single_package_count >", value, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("single_package_count >=", value, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountLessThan(Integer value) {
            addCriterion("single_package_count <", value, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountLessThanOrEqualTo(Integer value) {
            addCriterion("single_package_count <=", value, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountIn(List<Integer> values) {
            addCriterion("single_package_count in", values, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountNotIn(List<Integer> values) {
            addCriterion("single_package_count not in", values, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountBetween(Integer value1, Integer value2) {
            addCriterion("single_package_count between", value1, value2, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andSinglePackageCountNotBetween(Integer value1, Integer value2) {
            addCriterion("single_package_count not between", value1, value2, "singlePackageCount");
            return (Criteria) this;
        }

        public Criteria andPackageNumberIsNull() {
            addCriterion("package_number is null");
            return (Criteria) this;
        }

        public Criteria andPackageNumberIsNotNull() {
            addCriterion("package_number is not null");
            return (Criteria) this;
        }

        public Criteria andPackageNumberEqualTo(Integer value) {
            addCriterion("package_number =", value, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberNotEqualTo(Integer value) {
            addCriterion("package_number <>", value, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberGreaterThan(Integer value) {
            addCriterion("package_number >", value, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("package_number >=", value, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberLessThan(Integer value) {
            addCriterion("package_number <", value, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberLessThanOrEqualTo(Integer value) {
            addCriterion("package_number <=", value, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberIn(List<Integer> values) {
            addCriterion("package_number in", values, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberNotIn(List<Integer> values) {
            addCriterion("package_number not in", values, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberBetween(Integer value1, Integer value2) {
            addCriterion("package_number between", value1, value2, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andPackageNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("package_number not between", value1, value2, "packageNumber");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeIsNull() {
            addCriterion("single_volume is null");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeIsNotNull() {
            addCriterion("single_volume is not null");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeEqualTo(String value) {
            addCriterion("single_volume =", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeNotEqualTo(String value) {
            addCriterion("single_volume <>", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeGreaterThan(String value) {
            addCriterion("single_volume >", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("single_volume >=", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeLessThan(String value) {
            addCriterion("single_volume <", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeLessThanOrEqualTo(String value) {
            addCriterion("single_volume <=", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeLike(String value) {
            addCriterion("single_volume like", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeNotLike(String value) {
            addCriterion("single_volume not like", value, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeIn(List<String> values) {
            addCriterion("single_volume in", values, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeNotIn(List<String> values) {
            addCriterion("single_volume not in", values, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeBetween(String value1, String value2) {
            addCriterion("single_volume between", value1, value2, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleVolumeNotBetween(String value1, String value2) {
            addCriterion("single_volume not between", value1, value2, "singleVolume");
            return (Criteria) this;
        }

        public Criteria andSingleWeightIsNull() {
            addCriterion("single_weight is null");
            return (Criteria) this;
        }

        public Criteria andSingleWeightIsNotNull() {
            addCriterion("single_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSingleWeightEqualTo(String value) {
            addCriterion("single_weight =", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightNotEqualTo(String value) {
            addCriterion("single_weight <>", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightGreaterThan(String value) {
            addCriterion("single_weight >", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightGreaterThanOrEqualTo(String value) {
            addCriterion("single_weight >=", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightLessThan(String value) {
            addCriterion("single_weight <", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightLessThanOrEqualTo(String value) {
            addCriterion("single_weight <=", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightLike(String value) {
            addCriterion("single_weight like", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightNotLike(String value) {
            addCriterion("single_weight not like", value, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightIn(List<String> values) {
            addCriterion("single_weight in", values, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightNotIn(List<String> values) {
            addCriterion("single_weight not in", values, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightBetween(String value1, String value2) {
            addCriterion("single_weight between", value1, value2, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andSingleWeightNotBetween(String value1, String value2) {
            addCriterion("single_weight not between", value1, value2, "singleWeight");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNull() {
            addCriterion("total_volume is null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNotNull() {
            addCriterion("total_volume is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeEqualTo(String value) {
            addCriterion("total_volume =", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotEqualTo(String value) {
            addCriterion("total_volume <>", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThan(String value) {
            addCriterion("total_volume >", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("total_volume >=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThan(String value) {
            addCriterion("total_volume <", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThanOrEqualTo(String value) {
            addCriterion("total_volume <=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLike(String value) {
            addCriterion("total_volume like", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotLike(String value) {
            addCriterion("total_volume not like", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIn(List<String> values) {
            addCriterion("total_volume in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotIn(List<String> values) {
            addCriterion("total_volume not in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeBetween(String value1, String value2) {
            addCriterion("total_volume between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotBetween(String value1, String value2) {
            addCriterion("total_volume not between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNull() {
            addCriterion("total_weight is null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNotNull() {
            addCriterion("total_weight is not null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightEqualTo(String value) {
            addCriterion("total_weight =", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotEqualTo(String value) {
            addCriterion("total_weight <>", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThan(String value) {
            addCriterion("total_weight >", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThanOrEqualTo(String value) {
            addCriterion("total_weight >=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThan(String value) {
            addCriterion("total_weight <", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThanOrEqualTo(String value) {
            addCriterion("total_weight <=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLike(String value) {
            addCriterion("total_weight like", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotLike(String value) {
            addCriterion("total_weight not like", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIn(List<String> values) {
            addCriterion("total_weight in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotIn(List<String> values) {
            addCriterion("total_weight not in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightBetween(String value1, String value2) {
            addCriterion("total_weight between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotBetween(String value1, String value2) {
            addCriterion("total_weight not between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}