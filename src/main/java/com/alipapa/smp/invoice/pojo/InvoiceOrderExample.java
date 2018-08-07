package com.alipapa.smp.invoice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceOrderExample() {
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

        public Criteria andInvoiceNoIsNull() {
            addCriterion("invoice_no is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIsNotNull() {
            addCriterion("invoice_no is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoEqualTo(String value) {
            addCriterion("invoice_no =", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotEqualTo(String value) {
            addCriterion("invoice_no <>", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThan(String value) {
            addCriterion("invoice_no >", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_no >=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThan(String value) {
            addCriterion("invoice_no <", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLessThanOrEqualTo(String value) {
            addCriterion("invoice_no <=", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoLike(String value) {
            addCriterion("invoice_no like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotLike(String value) {
            addCriterion("invoice_no not like", value, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoIn(List<String> values) {
            addCriterion("invoice_no in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotIn(List<String> values) {
            addCriterion("invoice_no not in", values, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoBetween(String value1, String value2) {
            addCriterion("invoice_no between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceNoNotBetween(String value1, String value2) {
            addCriterion("invoice_no not between", value1, value2, "invoiceNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNull() {
            addCriterion("invoice_status is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIsNotNull() {
            addCriterion("invoice_status is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusEqualTo(Integer value) {
            addCriterion("invoice_status =", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotEqualTo(Integer value) {
            addCriterion("invoice_status <>", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThan(Integer value) {
            addCriterion("invoice_status >", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("invoice_status >=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThan(Integer value) {
            addCriterion("invoice_status <", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusLessThanOrEqualTo(Integer value) {
            addCriterion("invoice_status <=", value, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusIn(List<Integer> values) {
            addCriterion("invoice_status in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotIn(List<Integer> values) {
            addCriterion("invoice_status not in", values, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status between", value1, value2, "invoiceStatus");
            return (Criteria) this;
        }

        public Criteria andInvoiceStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("invoice_status not between", value1, value2, "invoiceStatus");
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

        public Criteria andConsigneeIsNull() {
            addCriterion("consignee is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNotNull() {
            addCriterion("consignee is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeEqualTo(String value) {
            addCriterion("consignee =", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotEqualTo(String value) {
            addCriterion("consignee <>", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThan(String value) {
            addCriterion("consignee >", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThanOrEqualTo(String value) {
            addCriterion("consignee >=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThan(String value) {
            addCriterion("consignee <", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThanOrEqualTo(String value) {
            addCriterion("consignee <=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLike(String value) {
            addCriterion("consignee like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotLike(String value) {
            addCriterion("consignee not like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeIn(List<String> values) {
            addCriterion("consignee in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotIn(List<String> values) {
            addCriterion("consignee not in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeBetween(String value1, String value2) {
            addCriterion("consignee between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotBetween(String value1, String value2) {
            addCriterion("consignee not between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andMobileIsNull() {
            addCriterion("mobile is null");
            return (Criteria) this;
        }

        public Criteria andMobileIsNotNull() {
            addCriterion("mobile is not null");
            return (Criteria) this;
        }

        public Criteria andMobileEqualTo(String value) {
            addCriterion("mobile =", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotEqualTo(String value) {
            addCriterion("mobile <>", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThan(String value) {
            addCriterion("mobile >", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("mobile >=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThan(String value) {
            addCriterion("mobile <", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("mobile <=", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileLike(String value) {
            addCriterion("mobile like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotLike(String value) {
            addCriterion("mobile not like", value, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileIn(List<String> values) {
            addCriterion("mobile in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotIn(List<String> values) {
            addCriterion("mobile not in", values, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileBetween(String value1, String value2) {
            addCriterion("mobile between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andMobileNotBetween(String value1, String value2) {
            addCriterion("mobile not between", value1, value2, "mobile");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNull() {
            addCriterion("postal_code is null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIsNotNull() {
            addCriterion("postal_code is not null");
            return (Criteria) this;
        }

        public Criteria andPostalCodeEqualTo(String value) {
            addCriterion("postal_code =", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotEqualTo(String value) {
            addCriterion("postal_code <>", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThan(String value) {
            addCriterion("postal_code >", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeGreaterThanOrEqualTo(String value) {
            addCriterion("postal_code >=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThan(String value) {
            addCriterion("postal_code <", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLessThanOrEqualTo(String value) {
            addCriterion("postal_code <=", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeLike(String value) {
            addCriterion("postal_code like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotLike(String value) {
            addCriterion("postal_code not like", value, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeIn(List<String> values) {
            addCriterion("postal_code in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotIn(List<String> values) {
            addCriterion("postal_code not in", values, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeBetween(String value1, String value2) {
            addCriterion("postal_code between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andPostalCodeNotBetween(String value1, String value2) {
            addCriterion("postal_code not between", value1, value2, "postalCode");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeIsNull() {
            addCriterion("deliver_type is null");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeIsNotNull() {
            addCriterion("deliver_type is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeEqualTo(Integer value) {
            addCriterion("deliver_type =", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeNotEqualTo(Integer value) {
            addCriterion("deliver_type <>", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeGreaterThan(Integer value) {
            addCriterion("deliver_type >", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("deliver_type >=", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeLessThan(Integer value) {
            addCriterion("deliver_type <", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeLessThanOrEqualTo(Integer value) {
            addCriterion("deliver_type <=", value, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeIn(List<Integer> values) {
            addCriterion("deliver_type in", values, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeNotIn(List<Integer> values) {
            addCriterion("deliver_type not in", values, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeBetween(Integer value1, Integer value2) {
            addCriterion("deliver_type between", value1, value2, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("deliver_type not between", value1, value2, "deliverType");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNull() {
            addCriterion("deliver_time is null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIsNotNull() {
            addCriterion("deliver_time is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeEqualTo(Date value) {
            addCriterion("deliver_time =", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotEqualTo(Date value) {
            addCriterion("deliver_time <>", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThan(Date value) {
            addCriterion("deliver_time >", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("deliver_time >=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThan(Date value) {
            addCriterion("deliver_time <", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeLessThanOrEqualTo(Date value) {
            addCriterion("deliver_time <=", value, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeIn(List<Date> values) {
            addCriterion("deliver_time in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotIn(List<Date> values) {
            addCriterion("deliver_time not in", values, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeBetween(Date value1, Date value2) {
            addCriterion("deliver_time between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andDeliverTimeNotBetween(Date value1, Date value2) {
            addCriterion("deliver_time not between", value1, value2, "deliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeIsNull() {
            addCriterion("actaul_deliver_time is null");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeIsNotNull() {
            addCriterion("actaul_deliver_time is not null");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeEqualTo(Date value) {
            addCriterion("actaul_deliver_time =", value, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeNotEqualTo(Date value) {
            addCriterion("actaul_deliver_time <>", value, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeGreaterThan(Date value) {
            addCriterion("actaul_deliver_time >", value, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("actaul_deliver_time >=", value, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeLessThan(Date value) {
            addCriterion("actaul_deliver_time <", value, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeLessThanOrEqualTo(Date value) {
            addCriterion("actaul_deliver_time <=", value, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeIn(List<Date> values) {
            addCriterion("actaul_deliver_time in", values, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeNotIn(List<Date> values) {
            addCriterion("actaul_deliver_time not in", values, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeBetween(Date value1, Date value2) {
            addCriterion("actaul_deliver_time between", value1, value2, "actaulDeliverTime");
            return (Criteria) this;
        }

        public Criteria andActaulDeliverTimeNotBetween(Date value1, Date value2) {
            addCriterion("actaul_deliver_time not between", value1, value2, "actaulDeliverTime");
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