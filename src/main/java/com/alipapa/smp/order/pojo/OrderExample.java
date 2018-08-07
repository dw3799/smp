package com.alipapa.smp.order.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderExample() {
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

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Integer value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Integer value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Integer value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Integer value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Integer> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Integer> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNull() {
            addCriterion("order_status is null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIsNotNull() {
            addCriterion("order_status is not null");
            return (Criteria) this;
        }

        public Criteria andOrderStatusEqualTo(Integer value) {
            addCriterion("order_status =", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotEqualTo(Integer value) {
            addCriterion("order_status <>", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThan(Integer value) {
            addCriterion("order_status >", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_status >=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThan(Integer value) {
            addCriterion("order_status <", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusLessThanOrEqualTo(Integer value) {
            addCriterion("order_status <=", value, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusIn(List<Integer> values) {
            addCriterion("order_status in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotIn(List<Integer> values) {
            addCriterion("order_status not in", values, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusBetween(Integer value1, Integer value2) {
            addCriterion("order_status between", value1, value2, "orderStatus");
            return (Criteria) this;
        }

        public Criteria andOrderStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("order_status not between", value1, value2, "orderStatus");
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

        public Criteria andConsumerNoIsNull() {
            addCriterion("consumer_no is null");
            return (Criteria) this;
        }

        public Criteria andConsumerNoIsNotNull() {
            addCriterion("consumer_no is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerNoEqualTo(String value) {
            addCriterion("consumer_no =", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoNotEqualTo(String value) {
            addCriterion("consumer_no <>", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoGreaterThan(String value) {
            addCriterion("consumer_no >", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_no >=", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoLessThan(String value) {
            addCriterion("consumer_no <", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoLessThanOrEqualTo(String value) {
            addCriterion("consumer_no <=", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoLike(String value) {
            addCriterion("consumer_no like", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoNotLike(String value) {
            addCriterion("consumer_no not like", value, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoIn(List<String> values) {
            addCriterion("consumer_no in", values, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoNotIn(List<String> values) {
            addCriterion("consumer_no not in", values, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoBetween(String value1, String value2) {
            addCriterion("consumer_no between", value1, value2, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNoNotBetween(String value1, String value2) {
            addCriterion("consumer_no not between", value1, value2, "consumerNo");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNull() {
            addCriterion("consumer_name is null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIsNotNull() {
            addCriterion("consumer_name is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerNameEqualTo(String value) {
            addCriterion("consumer_name =", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotEqualTo(String value) {
            addCriterion("consumer_name <>", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThan(String value) {
            addCriterion("consumer_name >", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_name >=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThan(String value) {
            addCriterion("consumer_name <", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLessThanOrEqualTo(String value) {
            addCriterion("consumer_name <=", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameLike(String value) {
            addCriterion("consumer_name like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotLike(String value) {
            addCriterion("consumer_name not like", value, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameIn(List<String> values) {
            addCriterion("consumer_name in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotIn(List<String> values) {
            addCriterion("consumer_name not in", values, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameBetween(String value1, String value2) {
            addCriterion("consumer_name between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerNameNotBetween(String value1, String value2) {
            addCriterion("consumer_name not between", value1, value2, "consumerName");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryIsNull() {
            addCriterion("consumer_country is null");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryIsNotNull() {
            addCriterion("consumer_country is not null");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryEqualTo(String value) {
            addCriterion("consumer_country =", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryNotEqualTo(String value) {
            addCriterion("consumer_country <>", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryGreaterThan(String value) {
            addCriterion("consumer_country >", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryGreaterThanOrEqualTo(String value) {
            addCriterion("consumer_country >=", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryLessThan(String value) {
            addCriterion("consumer_country <", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryLessThanOrEqualTo(String value) {
            addCriterion("consumer_country <=", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryLike(String value) {
            addCriterion("consumer_country like", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryNotLike(String value) {
            addCriterion("consumer_country not like", value, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryIn(List<String> values) {
            addCriterion("consumer_country in", values, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryNotIn(List<String> values) {
            addCriterion("consumer_country not in", values, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryBetween(String value1, String value2) {
            addCriterion("consumer_country between", value1, value2, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andConsumerCountryNotBetween(String value1, String value2) {
            addCriterion("consumer_country not between", value1, value2, "consumerCountry");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoIsNull() {
            addCriterion("saler_user_no is null");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoIsNotNull() {
            addCriterion("saler_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoEqualTo(String value) {
            addCriterion("saler_user_no =", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoNotEqualTo(String value) {
            addCriterion("saler_user_no <>", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoGreaterThan(String value) {
            addCriterion("saler_user_no >", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("saler_user_no >=", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoLessThan(String value) {
            addCriterion("saler_user_no <", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoLessThanOrEqualTo(String value) {
            addCriterion("saler_user_no <=", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoLike(String value) {
            addCriterion("saler_user_no like", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoNotLike(String value) {
            addCriterion("saler_user_no not like", value, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoIn(List<String> values) {
            addCriterion("saler_user_no in", values, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoNotIn(List<String> values) {
            addCriterion("saler_user_no not in", values, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoBetween(String value1, String value2) {
            addCriterion("saler_user_no between", value1, value2, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNoNotBetween(String value1, String value2) {
            addCriterion("saler_user_no not between", value1, value2, "salerUserNo");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameIsNull() {
            addCriterion("saler_user_name is null");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameIsNotNull() {
            addCriterion("saler_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameEqualTo(String value) {
            addCriterion("saler_user_name =", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameNotEqualTo(String value) {
            addCriterion("saler_user_name <>", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameGreaterThan(String value) {
            addCriterion("saler_user_name >", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("saler_user_name >=", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameLessThan(String value) {
            addCriterion("saler_user_name <", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameLessThanOrEqualTo(String value) {
            addCriterion("saler_user_name <=", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameLike(String value) {
            addCriterion("saler_user_name like", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameNotLike(String value) {
            addCriterion("saler_user_name not like", value, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameIn(List<String> values) {
            addCriterion("saler_user_name in", values, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameNotIn(List<String> values) {
            addCriterion("saler_user_name not in", values, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameBetween(String value1, String value2) {
            addCriterion("saler_user_name between", value1, value2, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andSalerUserNameNotBetween(String value1, String value2) {
            addCriterion("saler_user_name not between", value1, value2, "salerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoIsNull() {
            addCriterion("buyer_user_no is null");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoIsNotNull() {
            addCriterion("buyer_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoEqualTo(String value) {
            addCriterion("buyer_user_no =", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoNotEqualTo(String value) {
            addCriterion("buyer_user_no <>", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoGreaterThan(String value) {
            addCriterion("buyer_user_no >", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_user_no >=", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoLessThan(String value) {
            addCriterion("buyer_user_no <", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoLessThanOrEqualTo(String value) {
            addCriterion("buyer_user_no <=", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoLike(String value) {
            addCriterion("buyer_user_no like", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoNotLike(String value) {
            addCriterion("buyer_user_no not like", value, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoIn(List<String> values) {
            addCriterion("buyer_user_no in", values, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoNotIn(List<String> values) {
            addCriterion("buyer_user_no not in", values, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoBetween(String value1, String value2) {
            addCriterion("buyer_user_no between", value1, value2, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNoNotBetween(String value1, String value2) {
            addCriterion("buyer_user_no not between", value1, value2, "buyerUserNo");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameIsNull() {
            addCriterion("buyer_user_name is null");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameIsNotNull() {
            addCriterion("buyer_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameEqualTo(String value) {
            addCriterion("buyer_user_name =", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameNotEqualTo(String value) {
            addCriterion("buyer_user_name <>", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameGreaterThan(String value) {
            addCriterion("buyer_user_name >", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("buyer_user_name >=", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameLessThan(String value) {
            addCriterion("buyer_user_name <", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameLessThanOrEqualTo(String value) {
            addCriterion("buyer_user_name <=", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameLike(String value) {
            addCriterion("buyer_user_name like", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameNotLike(String value) {
            addCriterion("buyer_user_name not like", value, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameIn(List<String> values) {
            addCriterion("buyer_user_name in", values, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameNotIn(List<String> values) {
            addCriterion("buyer_user_name not in", values, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameBetween(String value1, String value2) {
            addCriterion("buyer_user_name between", value1, value2, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andBuyerUserNameNotBetween(String value1, String value2) {
            addCriterion("buyer_user_name not between", value1, value2, "buyerUserName");
            return (Criteria) this;
        }

        public Criteria andProductionCycleIsNull() {
            addCriterion("production_cycle is null");
            return (Criteria) this;
        }

        public Criteria andProductionCycleIsNotNull() {
            addCriterion("production_cycle is not null");
            return (Criteria) this;
        }

        public Criteria andProductionCycleEqualTo(String value) {
            addCriterion("production_cycle =", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleNotEqualTo(String value) {
            addCriterion("production_cycle <>", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleGreaterThan(String value) {
            addCriterion("production_cycle >", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleGreaterThanOrEqualTo(String value) {
            addCriterion("production_cycle >=", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleLessThan(String value) {
            addCriterion("production_cycle <", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleLessThanOrEqualTo(String value) {
            addCriterion("production_cycle <=", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleLike(String value) {
            addCriterion("production_cycle like", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleNotLike(String value) {
            addCriterion("production_cycle not like", value, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleIn(List<String> values) {
            addCriterion("production_cycle in", values, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleNotIn(List<String> values) {
            addCriterion("production_cycle not in", values, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleBetween(String value1, String value2) {
            addCriterion("production_cycle between", value1, value2, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andProductionCycleNotBetween(String value1, String value2) {
            addCriterion("production_cycle not between", value1, value2, "productionCycle");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNull() {
            addCriterion("currency is null");
            return (Criteria) this;
        }

        public Criteria andCurrencyIsNotNull() {
            addCriterion("currency is not null");
            return (Criteria) this;
        }

        public Criteria andCurrencyEqualTo(String value) {
            addCriterion("currency =", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotEqualTo(String value) {
            addCriterion("currency <>", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThan(String value) {
            addCriterion("currency >", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyGreaterThanOrEqualTo(String value) {
            addCriterion("currency >=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThan(String value) {
            addCriterion("currency <", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLessThanOrEqualTo(String value) {
            addCriterion("currency <=", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyLike(String value) {
            addCriterion("currency like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotLike(String value) {
            addCriterion("currency not like", value, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyIn(List<String> values) {
            addCriterion("currency in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotIn(List<String> values) {
            addCriterion("currency not in", values, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyBetween(String value1, String value2) {
            addCriterion("currency between", value1, value2, "currency");
            return (Criteria) this;
        }

        public Criteria andCurrencyNotBetween(String value1, String value2) {
            addCriterion("currency not between", value1, value2, "currency");
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

        public Criteria andOrderAmountIsNull() {
            addCriterion("order_amount is null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIsNotNull() {
            addCriterion("order_amount is not null");
            return (Criteria) this;
        }

        public Criteria andOrderAmountEqualTo(Long value) {
            addCriterion("order_amount =", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotEqualTo(Long value) {
            addCriterion("order_amount <>", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThan(Long value) {
            addCriterion("order_amount >", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("order_amount >=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThan(Long value) {
            addCriterion("order_amount <", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountLessThanOrEqualTo(Long value) {
            addCriterion("order_amount <=", value, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountIn(List<Long> values) {
            addCriterion("order_amount in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotIn(List<Long> values) {
            addCriterion("order_amount not in", values, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountBetween(Long value1, Long value2) {
            addCriterion("order_amount between", value1, value2, "orderAmount");
            return (Criteria) this;
        }

        public Criteria andOrderAmountNotBetween(Long value1, Long value2) {
            addCriterion("order_amount not between", value1, value2, "orderAmount");
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

        public Criteria andReceiptAmountIsNull() {
            addCriterion("receipt_amount is null");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountIsNotNull() {
            addCriterion("receipt_amount is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountEqualTo(Long value) {
            addCriterion("receipt_amount =", value, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountNotEqualTo(Long value) {
            addCriterion("receipt_amount <>", value, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountGreaterThan(Long value) {
            addCriterion("receipt_amount >", value, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("receipt_amount >=", value, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountLessThan(Long value) {
            addCriterion("receipt_amount <", value, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountLessThanOrEqualTo(Long value) {
            addCriterion("receipt_amount <=", value, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountIn(List<Long> values) {
            addCriterion("receipt_amount in", values, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountNotIn(List<Long> values) {
            addCriterion("receipt_amount not in", values, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountBetween(Long value1, Long value2) {
            addCriterion("receipt_amount between", value1, value2, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andReceiptAmountNotBetween(Long value1, Long value2) {
            addCriterion("receipt_amount not between", value1, value2, "receiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountIsNull() {
            addCriterion("cn_receipt_amount is null");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountIsNotNull() {
            addCriterion("cn_receipt_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountEqualTo(Long value) {
            addCriterion("cn_receipt_amount =", value, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountNotEqualTo(Long value) {
            addCriterion("cn_receipt_amount <>", value, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountGreaterThan(Long value) {
            addCriterion("cn_receipt_amount >", value, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("cn_receipt_amount >=", value, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountLessThan(Long value) {
            addCriterion("cn_receipt_amount <", value, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountLessThanOrEqualTo(Long value) {
            addCriterion("cn_receipt_amount <=", value, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountIn(List<Long> values) {
            addCriterion("cn_receipt_amount in", values, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountNotIn(List<Long> values) {
            addCriterion("cn_receipt_amount not in", values, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountBetween(Long value1, Long value2) {
            addCriterion("cn_receipt_amount between", value1, value2, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andCnReceiptAmountNotBetween(Long value1, Long value2) {
            addCriterion("cn_receipt_amount not between", value1, value2, "cnReceiptAmount");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeIsNull() {
            addCriterion("order_volume is null");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeIsNotNull() {
            addCriterion("order_volume is not null");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeEqualTo(String value) {
            addCriterion("order_volume =", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeNotEqualTo(String value) {
            addCriterion("order_volume <>", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeGreaterThan(String value) {
            addCriterion("order_volume >", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeGreaterThanOrEqualTo(String value) {
            addCriterion("order_volume >=", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeLessThan(String value) {
            addCriterion("order_volume <", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeLessThanOrEqualTo(String value) {
            addCriterion("order_volume <=", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeLike(String value) {
            addCriterion("order_volume like", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeNotLike(String value) {
            addCriterion("order_volume not like", value, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeIn(List<String> values) {
            addCriterion("order_volume in", values, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeNotIn(List<String> values) {
            addCriterion("order_volume not in", values, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeBetween(String value1, String value2) {
            addCriterion("order_volume between", value1, value2, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderVolumeNotBetween(String value1, String value2) {
            addCriterion("order_volume not between", value1, value2, "orderVolume");
            return (Criteria) this;
        }

        public Criteria andOrderWeightIsNull() {
            addCriterion("order_weight is null");
            return (Criteria) this;
        }

        public Criteria andOrderWeightIsNotNull() {
            addCriterion("order_weight is not null");
            return (Criteria) this;
        }

        public Criteria andOrderWeightEqualTo(String value) {
            addCriterion("order_weight =", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightNotEqualTo(String value) {
            addCriterion("order_weight <>", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightGreaterThan(String value) {
            addCriterion("order_weight >", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightGreaterThanOrEqualTo(String value) {
            addCriterion("order_weight >=", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightLessThan(String value) {
            addCriterion("order_weight <", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightLessThanOrEqualTo(String value) {
            addCriterion("order_weight <=", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightLike(String value) {
            addCriterion("order_weight like", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightNotLike(String value) {
            addCriterion("order_weight not like", value, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightIn(List<String> values) {
            addCriterion("order_weight in", values, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightNotIn(List<String> values) {
            addCriterion("order_weight not in", values, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightBetween(String value1, String value2) {
            addCriterion("order_weight between", value1, value2, "orderWeight");
            return (Criteria) this;
        }

        public Criteria andOrderWeightNotBetween(String value1, String value2) {
            addCriterion("order_weight not between", value1, value2, "orderWeight");
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