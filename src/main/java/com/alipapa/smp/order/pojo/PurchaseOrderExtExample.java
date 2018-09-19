package com.alipapa.smp.order.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PurchaseOrderExtExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PurchaseOrderExtExample() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
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

        public Criteria andPurchaseFrontAmountIsNull() {
            addCriterion("purchase_front_amount is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountIsNotNull() {
            addCriterion("purchase_front_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountEqualTo(Long value) {
            addCriterion("purchase_front_amount =", value, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountNotEqualTo(Long value) {
            addCriterion("purchase_front_amount <>", value, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountGreaterThan(Long value) {
            addCriterion("purchase_front_amount >", value, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("purchase_front_amount >=", value, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountLessThan(Long value) {
            addCriterion("purchase_front_amount <", value, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountLessThanOrEqualTo(Long value) {
            addCriterion("purchase_front_amount <=", value, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountIn(List<Long> values) {
            addCriterion("purchase_front_amount in", values, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountNotIn(List<Long> values) {
            addCriterion("purchase_front_amount not in", values, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountBetween(Long value1, Long value2) {
            addCriterion("purchase_front_amount between", value1, value2, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseFrontAmountNotBetween(Long value1, Long value2) {
            addCriterion("purchase_front_amount not between", value1, value2, "purchaseFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNull() {
            addCriterion("pay_channel is null");
            return (Criteria) this;
        }

        public Criteria andPayChannelIsNotNull() {
            addCriterion("pay_channel is not null");
            return (Criteria) this;
        }

        public Criteria andPayChannelEqualTo(String value) {
            addCriterion("pay_channel =", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotEqualTo(String value) {
            addCriterion("pay_channel <>", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThan(String value) {
            addCriterion("pay_channel >", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelGreaterThanOrEqualTo(String value) {
            addCriterion("pay_channel >=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThan(String value) {
            addCriterion("pay_channel <", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLessThanOrEqualTo(String value) {
            addCriterion("pay_channel <=", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelLike(String value) {
            addCriterion("pay_channel like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotLike(String value) {
            addCriterion("pay_channel not like", value, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelIn(List<String> values) {
            addCriterion("pay_channel in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotIn(List<String> values) {
            addCriterion("pay_channel not in", values, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelBetween(String value1, String value2) {
            addCriterion("pay_channel between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayChannelNotBetween(String value1, String value2) {
            addCriterion("pay_channel not between", value1, value2, "payChannel");
            return (Criteria) this;
        }

        public Criteria andPayNoIsNull() {
            addCriterion("pay_no is null");
            return (Criteria) this;
        }

        public Criteria andPayNoIsNotNull() {
            addCriterion("pay_no is not null");
            return (Criteria) this;
        }

        public Criteria andPayNoEqualTo(String value) {
            addCriterion("pay_no =", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotEqualTo(String value) {
            addCriterion("pay_no <>", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoGreaterThan(String value) {
            addCriterion("pay_no >", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoGreaterThanOrEqualTo(String value) {
            addCriterion("pay_no >=", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoLessThan(String value) {
            addCriterion("pay_no <", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoLessThanOrEqualTo(String value) {
            addCriterion("pay_no <=", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoLike(String value) {
            addCriterion("pay_no like", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotLike(String value) {
            addCriterion("pay_no not like", value, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoIn(List<String> values) {
            addCriterion("pay_no in", values, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotIn(List<String> values) {
            addCriterion("pay_no not in", values, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoBetween(String value1, String value2) {
            addCriterion("pay_no between", value1, value2, "payNo");
            return (Criteria) this;
        }

        public Criteria andPayNoNotBetween(String value1, String value2) {
            addCriterion("pay_no not between", value1, value2, "payNo");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNull() {
            addCriterion("submit_time is null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIsNotNull() {
            addCriterion("submit_time is not null");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeEqualTo(Date value) {
            addCriterion("submit_time =", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotEqualTo(Date value) {
            addCriterion("submit_time <>", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThan(Date value) {
            addCriterion("submit_time >", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("submit_time >=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThan(Date value) {
            addCriterion("submit_time <", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeLessThanOrEqualTo(Date value) {
            addCriterion("submit_time <=", value, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeIn(List<Date> values) {
            addCriterion("submit_time in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotIn(List<Date> values) {
            addCriterion("submit_time not in", values, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeBetween(Date value1, Date value2) {
            addCriterion("submit_time between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSubmitTimeNotBetween(Date value1, Date value2) {
            addCriterion("submit_time not between", value1, value2, "submitTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeIsNull() {
            addCriterion("super_apv_time is null");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeIsNotNull() {
            addCriterion("super_apv_time is not null");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeEqualTo(Date value) {
            addCriterion("super_apv_time =", value, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeNotEqualTo(Date value) {
            addCriterion("super_apv_time <>", value, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeGreaterThan(Date value) {
            addCriterion("super_apv_time >", value, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("super_apv_time >=", value, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeLessThan(Date value) {
            addCriterion("super_apv_time <", value, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeLessThanOrEqualTo(Date value) {
            addCriterion("super_apv_time <=", value, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeIn(List<Date> values) {
            addCriterion("super_apv_time in", values, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeNotIn(List<Date> values) {
            addCriterion("super_apv_time not in", values, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeBetween(Date value1, Date value2) {
            addCriterion("super_apv_time between", value1, value2, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andSuperApvTimeNotBetween(Date value1, Date value2) {
            addCriterion("super_apv_time not between", value1, value2, "superApvTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeIsNull() {
            addCriterion("fin_front_time is null");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeIsNotNull() {
            addCriterion("fin_front_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeEqualTo(Date value) {
            addCriterion("fin_front_time =", value, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeNotEqualTo(Date value) {
            addCriterion("fin_front_time <>", value, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeGreaterThan(Date value) {
            addCriterion("fin_front_time >", value, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fin_front_time >=", value, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeLessThan(Date value) {
            addCriterion("fin_front_time <", value, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeLessThanOrEqualTo(Date value) {
            addCriterion("fin_front_time <=", value, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeIn(List<Date> values) {
            addCriterion("fin_front_time in", values, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeNotIn(List<Date> values) {
            addCriterion("fin_front_time not in", values, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeBetween(Date value1, Date value2) {
            addCriterion("fin_front_time between", value1, value2, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinFrontTimeNotBetween(Date value1, Date value2) {
            addCriterion("fin_front_time not between", value1, value2, "finFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeIsNull() {
            addCriterion("cash_front_time is null");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeIsNotNull() {
            addCriterion("cash_front_time is not null");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeEqualTo(Date value) {
            addCriterion("cash_front_time =", value, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeNotEqualTo(Date value) {
            addCriterion("cash_front_time <>", value, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeGreaterThan(Date value) {
            addCriterion("cash_front_time >", value, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cash_front_time >=", value, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeLessThan(Date value) {
            addCriterion("cash_front_time <", value, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeLessThanOrEqualTo(Date value) {
            addCriterion("cash_front_time <=", value, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeIn(List<Date> values) {
            addCriterion("cash_front_time in", values, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeNotIn(List<Date> values) {
            addCriterion("cash_front_time not in", values, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeBetween(Date value1, Date value2) {
            addCriterion("cash_front_time between", value1, value2, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andCashFrontTimeNotBetween(Date value1, Date value2) {
            addCriterion("cash_front_time not between", value1, value2, "cashFrontTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeIsNull() {
            addCriterion("fin_tail_time is null");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeIsNotNull() {
            addCriterion("fin_tail_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeEqualTo(Date value) {
            addCriterion("fin_tail_time =", value, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeNotEqualTo(Date value) {
            addCriterion("fin_tail_time <>", value, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeGreaterThan(Date value) {
            addCriterion("fin_tail_time >", value, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fin_tail_time >=", value, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeLessThan(Date value) {
            addCriterion("fin_tail_time <", value, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeLessThanOrEqualTo(Date value) {
            addCriterion("fin_tail_time <=", value, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeIn(List<Date> values) {
            addCriterion("fin_tail_time in", values, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeNotIn(List<Date> values) {
            addCriterion("fin_tail_time not in", values, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeBetween(Date value1, Date value2) {
            addCriterion("fin_tail_time between", value1, value2, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andFinTailTimeNotBetween(Date value1, Date value2) {
            addCriterion("fin_tail_time not between", value1, value2, "finTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeIsNull() {
            addCriterion("cash_tail_time is null");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeIsNotNull() {
            addCriterion("cash_tail_time is not null");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeEqualTo(Date value) {
            addCriterion("cash_tail_time =", value, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeNotEqualTo(Date value) {
            addCriterion("cash_tail_time <>", value, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeGreaterThan(Date value) {
            addCriterion("cash_tail_time >", value, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cash_tail_time >=", value, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeLessThan(Date value) {
            addCriterion("cash_tail_time <", value, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeLessThanOrEqualTo(Date value) {
            addCriterion("cash_tail_time <=", value, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeIn(List<Date> values) {
            addCriterion("cash_tail_time in", values, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeNotIn(List<Date> values) {
            addCriterion("cash_tail_time not in", values, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeBetween(Date value1, Date value2) {
            addCriterion("cash_tail_time between", value1, value2, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCashTailTimeNotBetween(Date value1, Date value2) {
            addCriterion("cash_tail_time not between", value1, value2, "cashTailTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNull() {
            addCriterion("complete_time is null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNotNull() {
            addCriterion("complete_time is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeEqualTo(Date value) {
            addCriterion("complete_time =", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotEqualTo(Date value) {
            addCriterion("complete_time <>", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThan(Date value) {
            addCriterion("complete_time >", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_time >=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThan(Date value) {
            addCriterion("complete_time <", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("complete_time <=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIn(List<Date> values) {
            addCriterion("complete_time in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotIn(List<Date> values) {
            addCriterion("complete_time not in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeBetween(Date value1, Date value2) {
            addCriterion("complete_time between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("complete_time not between", value1, value2, "completeTime");
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

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Integer value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Integer value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Integer value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Integer value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Integer value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Integer> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Integer> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Integer value1, Integer value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Integer value1, Integer value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
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