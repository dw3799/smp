package com.alipapa.smp.invoice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceCostInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceCostInfoExample() {
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

        public Criteria andTransportChannelIsNull() {
            addCriterion("transport_channel is null");
            return (Criteria) this;
        }

        public Criteria andTransportChannelIsNotNull() {
            addCriterion("transport_channel is not null");
            return (Criteria) this;
        }

        public Criteria andTransportChannelEqualTo(String value) {
            addCriterion("transport_channel =", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelNotEqualTo(String value) {
            addCriterion("transport_channel <>", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelGreaterThan(String value) {
            addCriterion("transport_channel >", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelGreaterThanOrEqualTo(String value) {
            addCriterion("transport_channel >=", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelLessThan(String value) {
            addCriterion("transport_channel <", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelLessThanOrEqualTo(String value) {
            addCriterion("transport_channel <=", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelLike(String value) {
            addCriterion("transport_channel like", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelNotLike(String value) {
            addCriterion("transport_channel not like", value, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelIn(List<String> values) {
            addCriterion("transport_channel in", values, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelNotIn(List<String> values) {
            addCriterion("transport_channel not in", values, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelBetween(String value1, String value2) {
            addCriterion("transport_channel between", value1, value2, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportChannelNotBetween(String value1, String value2) {
            addCriterion("transport_channel not between", value1, value2, "transportChannel");
            return (Criteria) this;
        }

        public Criteria andTransportCostIsNull() {
            addCriterion("transport_cost is null");
            return (Criteria) this;
        }

        public Criteria andTransportCostIsNotNull() {
            addCriterion("transport_cost is not null");
            return (Criteria) this;
        }

        public Criteria andTransportCostEqualTo(String value) {
            addCriterion("transport_cost =", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostNotEqualTo(String value) {
            addCriterion("transport_cost <>", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostGreaterThan(String value) {
            addCriterion("transport_cost >", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostGreaterThanOrEqualTo(String value) {
            addCriterion("transport_cost >=", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostLessThan(String value) {
            addCriterion("transport_cost <", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostLessThanOrEqualTo(String value) {
            addCriterion("transport_cost <=", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostLike(String value) {
            addCriterion("transport_cost like", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostNotLike(String value) {
            addCriterion("transport_cost not like", value, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostIn(List<String> values) {
            addCriterion("transport_cost in", values, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostNotIn(List<String> values) {
            addCriterion("transport_cost not in", values, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostBetween(String value1, String value2) {
            addCriterion("transport_cost between", value1, value2, "transportCost");
            return (Criteria) this;
        }

        public Criteria andTransportCostNotBetween(String value1, String value2) {
            addCriterion("transport_cost not between", value1, value2, "transportCost");
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