package com.alipapa.smp.invoice.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InvoiceOrderExtExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public InvoiceOrderExtExample() {
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

        public Criteria andInvoiceOrderNoIsNull() {
            addCriterion("invoice_order_no is null");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoIsNotNull() {
            addCriterion("invoice_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoEqualTo(String value) {
            addCriterion("invoice_order_no =", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoNotEqualTo(String value) {
            addCriterion("invoice_order_no <>", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoGreaterThan(String value) {
            addCriterion("invoice_order_no >", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("invoice_order_no >=", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoLessThan(String value) {
            addCriterion("invoice_order_no <", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoLessThanOrEqualTo(String value) {
            addCriterion("invoice_order_no <=", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoLike(String value) {
            addCriterion("invoice_order_no like", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoNotLike(String value) {
            addCriterion("invoice_order_no not like", value, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoIn(List<String> values) {
            addCriterion("invoice_order_no in", values, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoNotIn(List<String> values) {
            addCriterion("invoice_order_no not in", values, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoBetween(String value1, String value2) {
            addCriterion("invoice_order_no between", value1, value2, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andInvoiceOrderNoNotBetween(String value1, String value2) {
            addCriterion("invoice_order_no not between", value1, value2, "invoiceOrderNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoIsNull() {
            addCriterion("storage_user_no is null");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoIsNotNull() {
            addCriterion("storage_user_no is not null");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoEqualTo(String value) {
            addCriterion("storage_user_no =", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoNotEqualTo(String value) {
            addCriterion("storage_user_no <>", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoGreaterThan(String value) {
            addCriterion("storage_user_no >", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoGreaterThanOrEqualTo(String value) {
            addCriterion("storage_user_no >=", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoLessThan(String value) {
            addCriterion("storage_user_no <", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoLessThanOrEqualTo(String value) {
            addCriterion("storage_user_no <=", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoLike(String value) {
            addCriterion("storage_user_no like", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoNotLike(String value) {
            addCriterion("storage_user_no not like", value, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoIn(List<String> values) {
            addCriterion("storage_user_no in", values, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoNotIn(List<String> values) {
            addCriterion("storage_user_no not in", values, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoBetween(String value1, String value2) {
            addCriterion("storage_user_no between", value1, value2, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNoNotBetween(String value1, String value2) {
            addCriterion("storage_user_no not between", value1, value2, "storageUserNo");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameIsNull() {
            addCriterion("storage_user_name is null");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameIsNotNull() {
            addCriterion("storage_user_name is not null");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameEqualTo(String value) {
            addCriterion("storage_user_name =", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameNotEqualTo(String value) {
            addCriterion("storage_user_name <>", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameGreaterThan(String value) {
            addCriterion("storage_user_name >", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("storage_user_name >=", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameLessThan(String value) {
            addCriterion("storage_user_name <", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameLessThanOrEqualTo(String value) {
            addCriterion("storage_user_name <=", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameLike(String value) {
            addCriterion("storage_user_name like", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameNotLike(String value) {
            addCriterion("storage_user_name not like", value, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameIn(List<String> values) {
            addCriterion("storage_user_name in", values, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameNotIn(List<String> values) {
            addCriterion("storage_user_name not in", values, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameBetween(String value1, String value2) {
            addCriterion("storage_user_name between", value1, value2, "storageUserName");
            return (Criteria) this;
        }

        public Criteria andStorageUserNameNotBetween(String value1, String value2) {
            addCriterion("storage_user_name not between", value1, value2, "storageUserName");
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

        public Criteria andFinApvTimeIsNull() {
            addCriterion("fin_apv_time is null");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeIsNotNull() {
            addCriterion("fin_apv_time is not null");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeEqualTo(Date value) {
            addCriterion("fin_apv_time =", value, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeNotEqualTo(Date value) {
            addCriterion("fin_apv_time <>", value, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeGreaterThan(Date value) {
            addCriterion("fin_apv_time >", value, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("fin_apv_time >=", value, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeLessThan(Date value) {
            addCriterion("fin_apv_time <", value, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeLessThanOrEqualTo(Date value) {
            addCriterion("fin_apv_time <=", value, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeIn(List<Date> values) {
            addCriterion("fin_apv_time in", values, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeNotIn(List<Date> values) {
            addCriterion("fin_apv_time not in", values, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeBetween(Date value1, Date value2) {
            addCriterion("fin_apv_time between", value1, value2, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andFinApvTimeNotBetween(Date value1, Date value2) {
            addCriterion("fin_apv_time not between", value1, value2, "finApvTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeIsNull() {
            addCriterion("doc_time is null");
            return (Criteria) this;
        }

        public Criteria andDocTimeIsNotNull() {
            addCriterion("doc_time is not null");
            return (Criteria) this;
        }

        public Criteria andDocTimeEqualTo(Date value) {
            addCriterion("doc_time =", value, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeNotEqualTo(Date value) {
            addCriterion("doc_time <>", value, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeGreaterThan(Date value) {
            addCriterion("doc_time >", value, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("doc_time >=", value, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeLessThan(Date value) {
            addCriterion("doc_time <", value, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeLessThanOrEqualTo(Date value) {
            addCriterion("doc_time <=", value, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeIn(List<Date> values) {
            addCriterion("doc_time in", values, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeNotIn(List<Date> values) {
            addCriterion("doc_time not in", values, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeBetween(Date value1, Date value2) {
            addCriterion("doc_time between", value1, value2, "docTime");
            return (Criteria) this;
        }

        public Criteria andDocTimeNotBetween(Date value1, Date value2) {
            addCriterion("doc_time not between", value1, value2, "docTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeIsNull() {
            addCriterion("check_out_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeIsNotNull() {
            addCriterion("check_out_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeEqualTo(Date value) {
            addCriterion("check_out_time =", value, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeNotEqualTo(Date value) {
            addCriterion("check_out_time <>", value, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeGreaterThan(Date value) {
            addCriterion("check_out_time >", value, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_out_time >=", value, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeLessThan(Date value) {
            addCriterion("check_out_time <", value, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_out_time <=", value, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeIn(List<Date> values) {
            addCriterion("check_out_time in", values, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeNotIn(List<Date> values) {
            addCriterion("check_out_time not in", values, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeBetween(Date value1, Date value2) {
            addCriterion("check_out_time between", value1, value2, "checkOutTime");
            return (Criteria) this;
        }

        public Criteria andCheckOutTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_out_time not between", value1, value2, "checkOutTime");
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