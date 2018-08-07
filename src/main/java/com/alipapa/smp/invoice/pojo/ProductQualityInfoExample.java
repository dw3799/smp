package com.alipapa.smp.invoice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductQualityInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProductQualityInfoExample() {
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

        public Criteria andCheckStatusIsNull() {
            addCriterion("check_status is null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIsNotNull() {
            addCriterion("check_status is not null");
            return (Criteria) this;
        }

        public Criteria andCheckStatusEqualTo(Integer value) {
            addCriterion("check_status =", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotEqualTo(Integer value) {
            addCriterion("check_status <>", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThan(Integer value) {
            addCriterion("check_status >", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_status >=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThan(Integer value) {
            addCriterion("check_status <", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusLessThanOrEqualTo(Integer value) {
            addCriterion("check_status <=", value, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusIn(List<Integer> values) {
            addCriterion("check_status in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotIn(List<Integer> values) {
            addCriterion("check_status not in", values, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusBetween(Integer value1, Integer value2) {
            addCriterion("check_status between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andCheckStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("check_status not between", value1, value2, "checkStatus");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeIsNull() {
            addCriterion("arrival_time is null");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeIsNotNull() {
            addCriterion("arrival_time is not null");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeEqualTo(Date value) {
            addCriterion("arrival_time =", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeNotEqualTo(Date value) {
            addCriterion("arrival_time <>", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeGreaterThan(Date value) {
            addCriterion("arrival_time >", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("arrival_time >=", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeLessThan(Date value) {
            addCriterion("arrival_time <", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeLessThanOrEqualTo(Date value) {
            addCriterion("arrival_time <=", value, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeIn(List<Date> values) {
            addCriterion("arrival_time in", values, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeNotIn(List<Date> values) {
            addCriterion("arrival_time not in", values, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeBetween(Date value1, Date value2) {
            addCriterion("arrival_time between", value1, value2, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andArrivalTimeNotBetween(Date value1, Date value2) {
            addCriterion("arrival_time not between", value1, value2, "arrivalTime");
            return (Criteria) this;
        }

        public Criteria andCheckNumberIsNull() {
            addCriterion("check_number is null");
            return (Criteria) this;
        }

        public Criteria andCheckNumberIsNotNull() {
            addCriterion("check_number is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNumberEqualTo(Integer value) {
            addCriterion("check_number =", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberNotEqualTo(Integer value) {
            addCriterion("check_number <>", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberGreaterThan(Integer value) {
            addCriterion("check_number >", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_number >=", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberLessThan(Integer value) {
            addCriterion("check_number <", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberLessThanOrEqualTo(Integer value) {
            addCriterion("check_number <=", value, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberIn(List<Integer> values) {
            addCriterion("check_number in", values, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberNotIn(List<Integer> values) {
            addCriterion("check_number not in", values, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberBetween(Integer value1, Integer value2) {
            addCriterion("check_number between", value1, value2, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andCheckNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("check_number not between", value1, value2, "checkNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberIsNull() {
            addCriterion("bad_number is null");
            return (Criteria) this;
        }

        public Criteria andBadNumberIsNotNull() {
            addCriterion("bad_number is not null");
            return (Criteria) this;
        }

        public Criteria andBadNumberEqualTo(Integer value) {
            addCriterion("bad_number =", value, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberNotEqualTo(Integer value) {
            addCriterion("bad_number <>", value, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberGreaterThan(Integer value) {
            addCriterion("bad_number >", value, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("bad_number >=", value, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberLessThan(Integer value) {
            addCriterion("bad_number <", value, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberLessThanOrEqualTo(Integer value) {
            addCriterion("bad_number <=", value, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberIn(List<Integer> values) {
            addCriterion("bad_number in", values, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberNotIn(List<Integer> values) {
            addCriterion("bad_number not in", values, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberBetween(Integer value1, Integer value2) {
            addCriterion("bad_number between", value1, value2, "badNumber");
            return (Criteria) this;
        }

        public Criteria andBadNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("bad_number not between", value1, value2, "badNumber");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityIsNull() {
            addCriterion("printing_quality is null");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityIsNotNull() {
            addCriterion("printing_quality is not null");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityEqualTo(String value) {
            addCriterion("printing_quality =", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityNotEqualTo(String value) {
            addCriterion("printing_quality <>", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityGreaterThan(String value) {
            addCriterion("printing_quality >", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityGreaterThanOrEqualTo(String value) {
            addCriterion("printing_quality >=", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityLessThan(String value) {
            addCriterion("printing_quality <", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityLessThanOrEqualTo(String value) {
            addCriterion("printing_quality <=", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityLike(String value) {
            addCriterion("printing_quality like", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityNotLike(String value) {
            addCriterion("printing_quality not like", value, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityIn(List<String> values) {
            addCriterion("printing_quality in", values, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityNotIn(List<String> values) {
            addCriterion("printing_quality not in", values, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityBetween(String value1, String value2) {
            addCriterion("printing_quality between", value1, value2, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPrintingQualityNotBetween(String value1, String value2) {
            addCriterion("printing_quality not between", value1, value2, "printingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityIsNull() {
            addCriterion("packaging_quality is null");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityIsNotNull() {
            addCriterion("packaging_quality is not null");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityEqualTo(String value) {
            addCriterion("packaging_quality =", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityNotEqualTo(String value) {
            addCriterion("packaging_quality <>", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityGreaterThan(String value) {
            addCriterion("packaging_quality >", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityGreaterThanOrEqualTo(String value) {
            addCriterion("packaging_quality >=", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityLessThan(String value) {
            addCriterion("packaging_quality <", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityLessThanOrEqualTo(String value) {
            addCriterion("packaging_quality <=", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityLike(String value) {
            addCriterion("packaging_quality like", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityNotLike(String value) {
            addCriterion("packaging_quality not like", value, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityIn(List<String> values) {
            addCriterion("packaging_quality in", values, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityNotIn(List<String> values) {
            addCriterion("packaging_quality not in", values, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityBetween(String value1, String value2) {
            addCriterion("packaging_quality between", value1, value2, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andPackagingQualityNotBetween(String value1, String value2) {
            addCriterion("packaging_quality not between", value1, value2, "packagingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityIsNull() {
            addCriterion("suturing_quality is null");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityIsNotNull() {
            addCriterion("suturing_quality is not null");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityEqualTo(String value) {
            addCriterion("suturing_quality =", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityNotEqualTo(String value) {
            addCriterion("suturing_quality <>", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityGreaterThan(String value) {
            addCriterion("suturing_quality >", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityGreaterThanOrEqualTo(String value) {
            addCriterion("suturing_quality >=", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityLessThan(String value) {
            addCriterion("suturing_quality <", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityLessThanOrEqualTo(String value) {
            addCriterion("suturing_quality <=", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityLike(String value) {
            addCriterion("suturing_quality like", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityNotLike(String value) {
            addCriterion("suturing_quality not like", value, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityIn(List<String> values) {
            addCriterion("suturing_quality in", values, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityNotIn(List<String> values) {
            addCriterion("suturing_quality not in", values, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityBetween(String value1, String value2) {
            addCriterion("suturing_quality between", value1, value2, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andSuturingQualityNotBetween(String value1, String value2) {
            addCriterion("suturing_quality not between", value1, value2, "suturingQuality");
            return (Criteria) this;
        }

        public Criteria andOpUserNoIsNull() {
            addCriterion("op_user_no is null");
            return (Criteria) this;
        }

        public Criteria andOpUserNoIsNotNull() {
            addCriterion("op_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserNoEqualTo(String value) {
            addCriterion("op_user_no =", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoNotEqualTo(String value) {
            addCriterion("op_user_no <>", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoGreaterThan(String value) {
            addCriterion("op_user_no >", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("op_user_no >=", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoLessThan(String value) {
            addCriterion("op_user_no <", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoLessThanOrEqualTo(String value) {
            addCriterion("op_user_no <=", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoLike(String value) {
            addCriterion("op_user_no like", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoNotLike(String value) {
            addCriterion("op_user_no not like", value, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoIn(List<String> values) {
            addCriterion("op_user_no in", values, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoNotIn(List<String> values) {
            addCriterion("op_user_no not in", values, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoBetween(String value1, String value2) {
            addCriterion("op_user_no between", value1, value2, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNoNotBetween(String value1, String value2) {
            addCriterion("op_user_no not between", value1, value2, "opUserNo");
            return (Criteria) this;
        }

        public Criteria andOpUserNameIsNull() {
            addCriterion("op_user_name is null");
            return (Criteria) this;
        }

        public Criteria andOpUserNameIsNotNull() {
            addCriterion("op_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserNameEqualTo(String value) {
            addCriterion("op_user_name =", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotEqualTo(String value) {
            addCriterion("op_user_name <>", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameGreaterThan(String value) {
            addCriterion("op_user_name >", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("op_user_name >=", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameLessThan(String value) {
            addCriterion("op_user_name <", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameLessThanOrEqualTo(String value) {
            addCriterion("op_user_name <=", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameLike(String value) {
            addCriterion("op_user_name like", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotLike(String value) {
            addCriterion("op_user_name not like", value, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameIn(List<String> values) {
            addCriterion("op_user_name in", values, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotIn(List<String> values) {
            addCriterion("op_user_name not in", values, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameBetween(String value1, String value2) {
            addCriterion("op_user_name between", value1, value2, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserNameNotBetween(String value1, String value2) {
            addCriterion("op_user_name not between", value1, value2, "opUserName");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleIsNull() {
            addCriterion("op_user_role is null");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleIsNotNull() {
            addCriterion("op_user_role is not null");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleEqualTo(String value) {
            addCriterion("op_user_role =", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleNotEqualTo(String value) {
            addCriterion("op_user_role <>", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleGreaterThan(String value) {
            addCriterion("op_user_role >", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleGreaterThanOrEqualTo(String value) {
            addCriterion("op_user_role >=", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleLessThan(String value) {
            addCriterion("op_user_role <", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleLessThanOrEqualTo(String value) {
            addCriterion("op_user_role <=", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleLike(String value) {
            addCriterion("op_user_role like", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleNotLike(String value) {
            addCriterion("op_user_role not like", value, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleIn(List<String> values) {
            addCriterion("op_user_role in", values, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleNotIn(List<String> values) {
            addCriterion("op_user_role not in", values, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleBetween(String value1, String value2) {
            addCriterion("op_user_role between", value1, value2, "opUserRole");
            return (Criteria) this;
        }

        public Criteria andOpUserRoleNotBetween(String value1, String value2) {
            addCriterion("op_user_role not between", value1, value2, "opUserRole");
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