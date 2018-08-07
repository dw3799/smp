package com.alipapa.smp.order.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MaterielOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MaterielOrderExample() {
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

        public Criteria andMaterielOrderStatusIsNull() {
            addCriterion("materiel_order_status is null");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusIsNotNull() {
            addCriterion("materiel_order_status is not null");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusEqualTo(Integer value) {
            addCriterion("materiel_order_status =", value, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusNotEqualTo(Integer value) {
            addCriterion("materiel_order_status <>", value, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusGreaterThan(Integer value) {
            addCriterion("materiel_order_status >", value, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("materiel_order_status >=", value, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusLessThan(Integer value) {
            addCriterion("materiel_order_status <", value, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("materiel_order_status <=", value, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusIn(List<Integer> values) {
            addCriterion("materiel_order_status in", values, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusNotIn(List<Integer> values) {
            addCriterion("materiel_order_status not in", values, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("materiel_order_status between", value1, value2, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andMaterielOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("materiel_order_status not between", value1, value2, "materielOrderStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNull() {
            addCriterion("pay_status is null");
            return (Criteria) this;
        }

        public Criteria andPayStatusIsNotNull() {
            addCriterion("pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andPayStatusEqualTo(Integer value) {
            addCriterion("pay_status =", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotEqualTo(Integer value) {
            addCriterion("pay_status <>", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThan(Integer value) {
            addCriterion("pay_status >", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("pay_status >=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThan(Integer value) {
            addCriterion("pay_status <", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusLessThanOrEqualTo(Integer value) {
            addCriterion("pay_status <=", value, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusIn(List<Integer> values) {
            addCriterion("pay_status in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotIn(List<Integer> values) {
            addCriterion("pay_status not in", values, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusBetween(Integer value1, Integer value2) {
            addCriterion("pay_status between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andPayStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("pay_status not between", value1, value2, "payStatus");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNull() {
            addCriterion("supplier_id is null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIsNotNull() {
            addCriterion("supplier_id is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierIdEqualTo(Long value) {
            addCriterion("supplier_id =", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotEqualTo(Long value) {
            addCriterion("supplier_id <>", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThan(Long value) {
            addCriterion("supplier_id >", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdGreaterThanOrEqualTo(Long value) {
            addCriterion("supplier_id >=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThan(Long value) {
            addCriterion("supplier_id <", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdLessThanOrEqualTo(Long value) {
            addCriterion("supplier_id <=", value, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdIn(List<Long> values) {
            addCriterion("supplier_id in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotIn(List<Long> values) {
            addCriterion("supplier_id not in", values, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdBetween(Long value1, Long value2) {
            addCriterion("supplier_id between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierIdNotBetween(Long value1, Long value2) {
            addCriterion("supplier_id not between", value1, value2, "supplierId");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNull() {
            addCriterion("supplier_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIsNotNull() {
            addCriterion("supplier_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierNameEqualTo(String value) {
            addCriterion("supplier_name =", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotEqualTo(String value) {
            addCriterion("supplier_name <>", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThan(String value) {
            addCriterion("supplier_name >", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_name >=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThan(String value) {
            addCriterion("supplier_name <", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLessThanOrEqualTo(String value) {
            addCriterion("supplier_name <=", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameLike(String value) {
            addCriterion("supplier_name like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotLike(String value) {
            addCriterion("supplier_name not like", value, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameIn(List<String> values) {
            addCriterion("supplier_name in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotIn(List<String> values) {
            addCriterion("supplier_name not in", values, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameBetween(String value1, String value2) {
            addCriterion("supplier_name between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierNameNotBetween(String value1, String value2) {
            addCriterion("supplier_name not between", value1, value2, "supplierName");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeIsNull() {
            addCriterion("supplier_charge is null");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeIsNotNull() {
            addCriterion("supplier_charge is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeEqualTo(String value) {
            addCriterion("supplier_charge =", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeNotEqualTo(String value) {
            addCriterion("supplier_charge <>", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeGreaterThan(String value) {
            addCriterion("supplier_charge >", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_charge >=", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeLessThan(String value) {
            addCriterion("supplier_charge <", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeLessThanOrEqualTo(String value) {
            addCriterion("supplier_charge <=", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeLike(String value) {
            addCriterion("supplier_charge like", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeNotLike(String value) {
            addCriterion("supplier_charge not like", value, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeIn(List<String> values) {
            addCriterion("supplier_charge in", values, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeNotIn(List<String> values) {
            addCriterion("supplier_charge not in", values, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeBetween(String value1, String value2) {
            addCriterion("supplier_charge between", value1, value2, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierChargeNotBetween(String value1, String value2) {
            addCriterion("supplier_charge not between", value1, value2, "supplierCharge");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileIsNull() {
            addCriterion("supplier_mobile is null");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileIsNotNull() {
            addCriterion("supplier_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileEqualTo(String value) {
            addCriterion("supplier_mobile =", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileNotEqualTo(String value) {
            addCriterion("supplier_mobile <>", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileGreaterThan(String value) {
            addCriterion("supplier_mobile >", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_mobile >=", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileLessThan(String value) {
            addCriterion("supplier_mobile <", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileLessThanOrEqualTo(String value) {
            addCriterion("supplier_mobile <=", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileLike(String value) {
            addCriterion("supplier_mobile like", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileNotLike(String value) {
            addCriterion("supplier_mobile not like", value, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileIn(List<String> values) {
            addCriterion("supplier_mobile in", values, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileNotIn(List<String> values) {
            addCriterion("supplier_mobile not in", values, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileBetween(String value1, String value2) {
            addCriterion("supplier_mobile between", value1, value2, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierMobileNotBetween(String value1, String value2) {
            addCriterion("supplier_mobile not between", value1, value2, "supplierMobile");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameIsNull() {
            addCriterion("supplier_bank_name is null");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameIsNotNull() {
            addCriterion("supplier_bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameEqualTo(String value) {
            addCriterion("supplier_bank_name =", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameNotEqualTo(String value) {
            addCriterion("supplier_bank_name <>", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameGreaterThan(String value) {
            addCriterion("supplier_bank_name >", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_bank_name >=", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameLessThan(String value) {
            addCriterion("supplier_bank_name <", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameLessThanOrEqualTo(String value) {
            addCriterion("supplier_bank_name <=", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameLike(String value) {
            addCriterion("supplier_bank_name like", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameNotLike(String value) {
            addCriterion("supplier_bank_name not like", value, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameIn(List<String> values) {
            addCriterion("supplier_bank_name in", values, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameNotIn(List<String> values) {
            addCriterion("supplier_bank_name not in", values, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameBetween(String value1, String value2) {
            addCriterion("supplier_bank_name between", value1, value2, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNameNotBetween(String value1, String value2) {
            addCriterion("supplier_bank_name not between", value1, value2, "supplierBankName");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountIsNull() {
            addCriterion("supplier_bank_account is null");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountIsNotNull() {
            addCriterion("supplier_bank_account is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountEqualTo(String value) {
            addCriterion("supplier_bank_account =", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountNotEqualTo(String value) {
            addCriterion("supplier_bank_account <>", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountGreaterThan(String value) {
            addCriterion("supplier_bank_account >", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_bank_account >=", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountLessThan(String value) {
            addCriterion("supplier_bank_account <", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountLessThanOrEqualTo(String value) {
            addCriterion("supplier_bank_account <=", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountLike(String value) {
            addCriterion("supplier_bank_account like", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountNotLike(String value) {
            addCriterion("supplier_bank_account not like", value, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountIn(List<String> values) {
            addCriterion("supplier_bank_account in", values, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountNotIn(List<String> values) {
            addCriterion("supplier_bank_account not in", values, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountBetween(String value1, String value2) {
            addCriterion("supplier_bank_account between", value1, value2, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankAccountNotBetween(String value1, String value2) {
            addCriterion("supplier_bank_account not between", value1, value2, "supplierBankAccount");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoIsNull() {
            addCriterion("supplier_bank_no is null");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoIsNotNull() {
            addCriterion("supplier_bank_no is not null");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoEqualTo(String value) {
            addCriterion("supplier_bank_no =", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoNotEqualTo(String value) {
            addCriterion("supplier_bank_no <>", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoGreaterThan(String value) {
            addCriterion("supplier_bank_no >", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoGreaterThanOrEqualTo(String value) {
            addCriterion("supplier_bank_no >=", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoLessThan(String value) {
            addCriterion("supplier_bank_no <", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoLessThanOrEqualTo(String value) {
            addCriterion("supplier_bank_no <=", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoLike(String value) {
            addCriterion("supplier_bank_no like", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoNotLike(String value) {
            addCriterion("supplier_bank_no not like", value, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoIn(List<String> values) {
            addCriterion("supplier_bank_no in", values, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoNotIn(List<String> values) {
            addCriterion("supplier_bank_no not in", values, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoBetween(String value1, String value2) {
            addCriterion("supplier_bank_no between", value1, value2, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andSupplierBankNoNotBetween(String value1, String value2) {
            addCriterion("supplier_bank_no not between", value1, value2, "supplierBankNo");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountIsNull() {
            addCriterion("purchase_amount is null");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountIsNotNull() {
            addCriterion("purchase_amount is not null");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountEqualTo(Long value) {
            addCriterion("purchase_amount =", value, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountNotEqualTo(Long value) {
            addCriterion("purchase_amount <>", value, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountGreaterThan(Long value) {
            addCriterion("purchase_amount >", value, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("purchase_amount >=", value, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountLessThan(Long value) {
            addCriterion("purchase_amount <", value, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountLessThanOrEqualTo(Long value) {
            addCriterion("purchase_amount <=", value, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountIn(List<Long> values) {
            addCriterion("purchase_amount in", values, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountNotIn(List<Long> values) {
            addCriterion("purchase_amount not in", values, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountBetween(Long value1, Long value2) {
            addCriterion("purchase_amount between", value1, value2, "purchaseAmount");
            return (Criteria) this;
        }

        public Criteria andPurchaseAmountNotBetween(Long value1, Long value2) {
            addCriterion("purchase_amount not between", value1, value2, "purchaseAmount");
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