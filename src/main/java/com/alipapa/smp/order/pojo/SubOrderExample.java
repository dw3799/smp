package com.alipapa.smp.order.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SubOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SubOrderExample() {
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

        public Criteria andSubOrderStatusIsNull() {
            addCriterion("sub_order_status is null");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusIsNotNull() {
            addCriterion("sub_order_status is not null");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusEqualTo(Integer value) {
            addCriterion("sub_order_status =", value, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusNotEqualTo(Integer value) {
            addCriterion("sub_order_status <>", value, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusGreaterThan(Integer value) {
            addCriterion("sub_order_status >", value, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_order_status >=", value, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusLessThan(Integer value) {
            addCriterion("sub_order_status <", value, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("sub_order_status <=", value, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusIn(List<Integer> values) {
            addCriterion("sub_order_status in", values, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusNotIn(List<Integer> values) {
            addCriterion("sub_order_status not in", values, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_status between", value1, value2, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_order_status not between", value1, value2, "subOrderStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusIsNull() {
            addCriterion("sub_pay_status is null");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusIsNotNull() {
            addCriterion("sub_pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusEqualTo(Integer value) {
            addCriterion("sub_pay_status =", value, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusNotEqualTo(Integer value) {
            addCriterion("sub_pay_status <>", value, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusGreaterThan(Integer value) {
            addCriterion("sub_pay_status >", value, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("sub_pay_status >=", value, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusLessThan(Integer value) {
            addCriterion("sub_pay_status <", value, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusLessThanOrEqualTo(Integer value) {
            addCriterion("sub_pay_status <=", value, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusIn(List<Integer> values) {
            addCriterion("sub_pay_status in", values, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusNotIn(List<Integer> values) {
            addCriterion("sub_pay_status not in", values, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusBetween(Integer value1, Integer value2) {
            addCriterion("sub_pay_status between", value1, value2, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andSubPayStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("sub_pay_status not between", value1, value2, "subPayStatus");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdIsNull() {
            addCriterion("product_category_id is null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdIsNotNull() {
            addCriterion("product_category_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdEqualTo(Long value) {
            addCriterion("product_category_id =", value, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdNotEqualTo(Long value) {
            addCriterion("product_category_id <>", value, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdGreaterThan(Long value) {
            addCriterion("product_category_id >", value, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_category_id >=", value, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdLessThan(Long value) {
            addCriterion("product_category_id <", value, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdLessThanOrEqualTo(Long value) {
            addCriterion("product_category_id <=", value, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdIn(List<Long> values) {
            addCriterion("product_category_id in", values, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdNotIn(List<Long> values) {
            addCriterion("product_category_id not in", values, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdBetween(Long value1, Long value2) {
            addCriterion("product_category_id between", value1, value2, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIdNotBetween(Long value1, Long value2) {
            addCriterion("product_category_id not between", value1, value2, "productCategoryId");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIsNull() {
            addCriterion("product_category is null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIsNotNull() {
            addCriterion("product_category is not null");
            return (Criteria) this;
        }

        public Criteria andProductCategoryEqualTo(String value) {
            addCriterion("product_category =", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotEqualTo(String value) {
            addCriterion("product_category <>", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryGreaterThan(String value) {
            addCriterion("product_category >", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryGreaterThanOrEqualTo(String value) {
            addCriterion("product_category >=", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryLessThan(String value) {
            addCriterion("product_category <", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryLessThanOrEqualTo(String value) {
            addCriterion("product_category <=", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryLike(String value) {
            addCriterion("product_category like", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotLike(String value) {
            addCriterion("product_category not like", value, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryIn(List<String> values) {
            addCriterion("product_category in", values, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotIn(List<String> values) {
            addCriterion("product_category not in", values, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryBetween(String value1, String value2) {
            addCriterion("product_category between", value1, value2, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductCategoryNotBetween(String value1, String value2) {
            addCriterion("product_category not between", value1, value2, "productCategory");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNull() {
            addCriterion("product_id is null");
            return (Criteria) this;
        }

        public Criteria andProductIdIsNotNull() {
            addCriterion("product_id is not null");
            return (Criteria) this;
        }

        public Criteria andProductIdEqualTo(Long value) {
            addCriterion("product_id =", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotEqualTo(Long value) {
            addCriterion("product_id <>", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThan(Long value) {
            addCriterion("product_id >", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdGreaterThanOrEqualTo(Long value) {
            addCriterion("product_id >=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThan(Long value) {
            addCriterion("product_id <", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdLessThanOrEqualTo(Long value) {
            addCriterion("product_id <=", value, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdIn(List<Long> values) {
            addCriterion("product_id in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotIn(List<Long> values) {
            addCriterion("product_id not in", values, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdBetween(Long value1, Long value2) {
            addCriterion("product_id between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductIdNotBetween(Long value1, Long value2) {
            addCriterion("product_id not between", value1, value2, "productId");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNull() {
            addCriterion("product_name is null");
            return (Criteria) this;
        }

        public Criteria andProductNameIsNotNull() {
            addCriterion("product_name is not null");
            return (Criteria) this;
        }

        public Criteria andProductNameEqualTo(String value) {
            addCriterion("product_name =", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotEqualTo(String value) {
            addCriterion("product_name <>", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThan(String value) {
            addCriterion("product_name >", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameGreaterThanOrEqualTo(String value) {
            addCriterion("product_name >=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThan(String value) {
            addCriterion("product_name <", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLessThanOrEqualTo(String value) {
            addCriterion("product_name <=", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameLike(String value) {
            addCriterion("product_name like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotLike(String value) {
            addCriterion("product_name not like", value, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameIn(List<String> values) {
            addCriterion("product_name in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotIn(List<String> values) {
            addCriterion("product_name not in", values, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameBetween(String value1, String value2) {
            addCriterion("product_name between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andProductNameNotBetween(String value1, String value2) {
            addCriterion("product_name not between", value1, value2, "productName");
            return (Criteria) this;
        }

        public Criteria andPicIsNull() {
            addCriterion("pic is null");
            return (Criteria) this;
        }

        public Criteria andPicIsNotNull() {
            addCriterion("pic is not null");
            return (Criteria) this;
        }

        public Criteria andPicEqualTo(String value) {
            addCriterion("pic =", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotEqualTo(String value) {
            addCriterion("pic <>", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThan(String value) {
            addCriterion("pic >", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicGreaterThanOrEqualTo(String value) {
            addCriterion("pic >=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThan(String value) {
            addCriterion("pic <", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLessThanOrEqualTo(String value) {
            addCriterion("pic <=", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicLike(String value) {
            addCriterion("pic like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotLike(String value) {
            addCriterion("pic not like", value, "pic");
            return (Criteria) this;
        }

        public Criteria andPicIn(List<String> values) {
            addCriterion("pic in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotIn(List<String> values) {
            addCriterion("pic not in", values, "pic");
            return (Criteria) this;
        }

        public Criteria andPicBetween(String value1, String value2) {
            addCriterion("pic between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andPicNotBetween(String value1, String value2) {
            addCriterion("pic not between", value1, value2, "pic");
            return (Criteria) this;
        }

        public Criteria andMiniPicIsNull() {
            addCriterion("mini_pic is null");
            return (Criteria) this;
        }

        public Criteria andMiniPicIsNotNull() {
            addCriterion("mini_pic is not null");
            return (Criteria) this;
        }

        public Criteria andMiniPicEqualTo(String value) {
            addCriterion("mini_pic =", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicNotEqualTo(String value) {
            addCriterion("mini_pic <>", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicGreaterThan(String value) {
            addCriterion("mini_pic >", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicGreaterThanOrEqualTo(String value) {
            addCriterion("mini_pic >=", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicLessThan(String value) {
            addCriterion("mini_pic <", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicLessThanOrEqualTo(String value) {
            addCriterion("mini_pic <=", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicLike(String value) {
            addCriterion("mini_pic like", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicNotLike(String value) {
            addCriterion("mini_pic not like", value, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicIn(List<String> values) {
            addCriterion("mini_pic in", values, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicNotIn(List<String> values) {
            addCriterion("mini_pic not in", values, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicBetween(String value1, String value2) {
            addCriterion("mini_pic between", value1, value2, "miniPic");
            return (Criteria) this;
        }

        public Criteria andMiniPicNotBetween(String value1, String value2) {
            addCriterion("mini_pic not between", value1, value2, "miniPic");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNull() {
            addCriterion("product_amount is null");
            return (Criteria) this;
        }

        public Criteria andProductAmountIsNotNull() {
            addCriterion("product_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProductAmountEqualTo(Long value) {
            addCriterion("product_amount =", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotEqualTo(Long value) {
            addCriterion("product_amount <>", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThan(Long value) {
            addCriterion("product_amount >", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("product_amount >=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThan(Long value) {
            addCriterion("product_amount <", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountLessThanOrEqualTo(Long value) {
            addCriterion("product_amount <=", value, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountIn(List<Long> values) {
            addCriterion("product_amount in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotIn(List<Long> values) {
            addCriterion("product_amount not in", values, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountBetween(Long value1, Long value2) {
            addCriterion("product_amount between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andProductAmountNotBetween(Long value1, Long value2) {
            addCriterion("product_amount not between", value1, value2, "productAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountIsNull() {
            addCriterion("expect_purchase_amount is null");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountIsNotNull() {
            addCriterion("expect_purchase_amount is not null");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountEqualTo(Long value) {
            addCriterion("expect_purchase_amount =", value, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountNotEqualTo(Long value) {
            addCriterion("expect_purchase_amount <>", value, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountGreaterThan(Long value) {
            addCriterion("expect_purchase_amount >", value, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("expect_purchase_amount >=", value, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountLessThan(Long value) {
            addCriterion("expect_purchase_amount <", value, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountLessThanOrEqualTo(Long value) {
            addCriterion("expect_purchase_amount <=", value, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountIn(List<Long> values) {
            addCriterion("expect_purchase_amount in", values, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountNotIn(List<Long> values) {
            addCriterion("expect_purchase_amount not in", values, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountBetween(Long value1, Long value2) {
            addCriterion("expect_purchase_amount between", value1, value2, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andExpectPurchaseAmountNotBetween(Long value1, Long value2) {
            addCriterion("expect_purchase_amount not between", value1, value2, "expectPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountIsNull() {
            addCriterion("actual_purchase_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountIsNotNull() {
            addCriterion("actual_purchase_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountEqualTo(Long value) {
            addCriterion("actual_purchase_amount =", value, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountNotEqualTo(Long value) {
            addCriterion("actual_purchase_amount <>", value, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountGreaterThan(Long value) {
            addCriterion("actual_purchase_amount >", value, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("actual_purchase_amount >=", value, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountLessThan(Long value) {
            addCriterion("actual_purchase_amount <", value, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountLessThanOrEqualTo(Long value) {
            addCriterion("actual_purchase_amount <=", value, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountIn(List<Long> values) {
            addCriterion("actual_purchase_amount in", values, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountNotIn(List<Long> values) {
            addCriterion("actual_purchase_amount not in", values, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountBetween(Long value1, Long value2) {
            addCriterion("actual_purchase_amount between", value1, value2, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andActualPurchaseAmountNotBetween(Long value1, Long value2) {
            addCriterion("actual_purchase_amount not between", value1, value2, "actualPurchaseAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountIsNull() {
            addCriterion("product_front_amount is null");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountIsNotNull() {
            addCriterion("product_front_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountEqualTo(Long value) {
            addCriterion("product_front_amount =", value, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountNotEqualTo(Long value) {
            addCriterion("product_front_amount <>", value, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountGreaterThan(Long value) {
            addCriterion("product_front_amount >", value, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("product_front_amount >=", value, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountLessThan(Long value) {
            addCriterion("product_front_amount <", value, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountLessThanOrEqualTo(Long value) {
            addCriterion("product_front_amount <=", value, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountIn(List<Long> values) {
            addCriterion("product_front_amount in", values, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountNotIn(List<Long> values) {
            addCriterion("product_front_amount not in", values, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountBetween(Long value1, Long value2) {
            addCriterion("product_front_amount between", value1, value2, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andProductFrontAmountNotBetween(Long value1, Long value2) {
            addCriterion("product_front_amount not between", value1, value2, "productFrontAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIsNull() {
            addCriterion("payed_amount is null");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIsNotNull() {
            addCriterion("payed_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPayedAmountEqualTo(Long value) {
            addCriterion("payed_amount =", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotEqualTo(Long value) {
            addCriterion("payed_amount <>", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountGreaterThan(Long value) {
            addCriterion("payed_amount >", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("payed_amount >=", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountLessThan(Long value) {
            addCriterion("payed_amount <", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountLessThanOrEqualTo(Long value) {
            addCriterion("payed_amount <=", value, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountIn(List<Long> values) {
            addCriterion("payed_amount in", values, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotIn(List<Long> values) {
            addCriterion("payed_amount not in", values, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountBetween(Long value1, Long value2) {
            addCriterion("payed_amount between", value1, value2, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andPayedAmountNotBetween(Long value1, Long value2) {
            addCriterion("payed_amount not between", value1, value2, "payedAmount");
            return (Criteria) this;
        }

        public Criteria andProductRemarkIsNull() {
            addCriterion("product_remark is null");
            return (Criteria) this;
        }

        public Criteria andProductRemarkIsNotNull() {
            addCriterion("product_remark is not null");
            return (Criteria) this;
        }

        public Criteria andProductRemarkEqualTo(String value) {
            addCriterion("product_remark =", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkNotEqualTo(String value) {
            addCriterion("product_remark <>", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkGreaterThan(String value) {
            addCriterion("product_remark >", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("product_remark >=", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkLessThan(String value) {
            addCriterion("product_remark <", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkLessThanOrEqualTo(String value) {
            addCriterion("product_remark <=", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkLike(String value) {
            addCriterion("product_remark like", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkNotLike(String value) {
            addCriterion("product_remark not like", value, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkIn(List<String> values) {
            addCriterion("product_remark in", values, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkNotIn(List<String> values) {
            addCriterion("product_remark not in", values, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkBetween(String value1, String value2) {
            addCriterion("product_remark between", value1, value2, "productRemark");
            return (Criteria) this;
        }

        public Criteria andProductRemarkNotBetween(String value1, String value2) {
            addCriterion("product_remark not between", value1, value2, "productRemark");
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