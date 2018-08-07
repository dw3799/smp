package com.alipapa.smp.order.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumerFrontPayExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConsumerFrontPayExample() {
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

        public Criteria andRoyaltyAmountIsNull() {
            addCriterion("royalty_amount is null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountIsNotNull() {
            addCriterion("royalty_amount is not null");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountEqualTo(Long value) {
            addCriterion("royalty_amount =", value, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountNotEqualTo(Long value) {
            addCriterion("royalty_amount <>", value, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountGreaterThan(Long value) {
            addCriterion("royalty_amount >", value, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("royalty_amount >=", value, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountLessThan(Long value) {
            addCriterion("royalty_amount <", value, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountLessThanOrEqualTo(Long value) {
            addCriterion("royalty_amount <=", value, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountIn(List<Long> values) {
            addCriterion("royalty_amount in", values, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountNotIn(List<Long> values) {
            addCriterion("royalty_amount not in", values, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountBetween(Long value1, Long value2) {
            addCriterion("royalty_amount between", value1, value2, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andRoyaltyAmountNotBetween(Long value1, Long value2) {
            addCriterion("royalty_amount not between", value1, value2, "royaltyAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountIsNull() {
            addCriterion("freight_amount is null");
            return (Criteria) this;
        }

        public Criteria andFreightAmountIsNotNull() {
            addCriterion("freight_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFreightAmountEqualTo(Long value) {
            addCriterion("freight_amount =", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotEqualTo(Long value) {
            addCriterion("freight_amount <>", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountGreaterThan(Long value) {
            addCriterion("freight_amount >", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("freight_amount >=", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountLessThan(Long value) {
            addCriterion("freight_amount <", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountLessThanOrEqualTo(Long value) {
            addCriterion("freight_amount <=", value, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountIn(List<Long> values) {
            addCriterion("freight_amount in", values, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotIn(List<Long> values) {
            addCriterion("freight_amount not in", values, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountBetween(Long value1, Long value2) {
            addCriterion("freight_amount between", value1, value2, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andFreightAmountNotBetween(Long value1, Long value2) {
            addCriterion("freight_amount not between", value1, value2, "freightAmount");
            return (Criteria) this;
        }

        public Criteria andBankFeeIsNull() {
            addCriterion("bank_fee is null");
            return (Criteria) this;
        }

        public Criteria andBankFeeIsNotNull() {
            addCriterion("bank_fee is not null");
            return (Criteria) this;
        }

        public Criteria andBankFeeEqualTo(Long value) {
            addCriterion("bank_fee =", value, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeNotEqualTo(Long value) {
            addCriterion("bank_fee <>", value, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeGreaterThan(Long value) {
            addCriterion("bank_fee >", value, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("bank_fee >=", value, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeLessThan(Long value) {
            addCriterion("bank_fee <", value, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeLessThanOrEqualTo(Long value) {
            addCriterion("bank_fee <=", value, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeIn(List<Long> values) {
            addCriterion("bank_fee in", values, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeNotIn(List<Long> values) {
            addCriterion("bank_fee not in", values, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeBetween(Long value1, Long value2) {
            addCriterion("bank_fee between", value1, value2, "bankFee");
            return (Criteria) this;
        }

        public Criteria andBankFeeNotBetween(Long value1, Long value2) {
            addCriterion("bank_fee not between", value1, value2, "bankFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeIsNull() {
            addCriterion("others_fee is null");
            return (Criteria) this;
        }

        public Criteria andOthersFeeIsNotNull() {
            addCriterion("others_fee is not null");
            return (Criteria) this;
        }

        public Criteria andOthersFeeEqualTo(Long value) {
            addCriterion("others_fee =", value, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeNotEqualTo(Long value) {
            addCriterion("others_fee <>", value, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeGreaterThan(Long value) {
            addCriterion("others_fee >", value, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeGreaterThanOrEqualTo(Long value) {
            addCriterion("others_fee >=", value, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeLessThan(Long value) {
            addCriterion("others_fee <", value, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeLessThanOrEqualTo(Long value) {
            addCriterion("others_fee <=", value, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeIn(List<Long> values) {
            addCriterion("others_fee in", values, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeNotIn(List<Long> values) {
            addCriterion("others_fee not in", values, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeBetween(Long value1, Long value2) {
            addCriterion("others_fee between", value1, value2, "othersFee");
            return (Criteria) this;
        }

        public Criteria andOthersFeeNotBetween(Long value1, Long value2) {
            addCriterion("others_fee not between", value1, value2, "othersFee");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelIsNull() {
            addCriterion("receipt_channel is null");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelIsNotNull() {
            addCriterion("receipt_channel is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelEqualTo(String value) {
            addCriterion("receipt_channel =", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelNotEqualTo(String value) {
            addCriterion("receipt_channel <>", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelGreaterThan(String value) {
            addCriterion("receipt_channel >", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelGreaterThanOrEqualTo(String value) {
            addCriterion("receipt_channel >=", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelLessThan(String value) {
            addCriterion("receipt_channel <", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelLessThanOrEqualTo(String value) {
            addCriterion("receipt_channel <=", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelLike(String value) {
            addCriterion("receipt_channel like", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelNotLike(String value) {
            addCriterion("receipt_channel not like", value, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelIn(List<String> values) {
            addCriterion("receipt_channel in", values, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelNotIn(List<String> values) {
            addCriterion("receipt_channel not in", values, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelBetween(String value1, String value2) {
            addCriterion("receipt_channel between", value1, value2, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptChannelNotBetween(String value1, String value2) {
            addCriterion("receipt_channel not between", value1, value2, "receiptChannel");
            return (Criteria) this;
        }

        public Criteria andReceiptNoIsNull() {
            addCriterion("receipt_no is null");
            return (Criteria) this;
        }

        public Criteria andReceiptNoIsNotNull() {
            addCriterion("receipt_no is not null");
            return (Criteria) this;
        }

        public Criteria andReceiptNoEqualTo(String value) {
            addCriterion("receipt_no =", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotEqualTo(String value) {
            addCriterion("receipt_no <>", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoGreaterThan(String value) {
            addCriterion("receipt_no >", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoGreaterThanOrEqualTo(String value) {
            addCriterion("receipt_no >=", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoLessThan(String value) {
            addCriterion("receipt_no <", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoLessThanOrEqualTo(String value) {
            addCriterion("receipt_no <=", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoLike(String value) {
            addCriterion("receipt_no like", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotLike(String value) {
            addCriterion("receipt_no not like", value, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoIn(List<String> values) {
            addCriterion("receipt_no in", values, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotIn(List<String> values) {
            addCriterion("receipt_no not in", values, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoBetween(String value1, String value2) {
            addCriterion("receipt_no between", value1, value2, "receiptNo");
            return (Criteria) this;
        }

        public Criteria andReceiptNoNotBetween(String value1, String value2) {
            addCriterion("receipt_no not between", value1, value2, "receiptNo");
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

        public Criteria andFrontAmountIsNull() {
            addCriterion("front_amount is null");
            return (Criteria) this;
        }

        public Criteria andFrontAmountIsNotNull() {
            addCriterion("front_amount is not null");
            return (Criteria) this;
        }

        public Criteria andFrontAmountEqualTo(Long value) {
            addCriterion("front_amount =", value, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountNotEqualTo(Long value) {
            addCriterion("front_amount <>", value, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountGreaterThan(Long value) {
            addCriterion("front_amount >", value, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("front_amount >=", value, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountLessThan(Long value) {
            addCriterion("front_amount <", value, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountLessThanOrEqualTo(Long value) {
            addCriterion("front_amount <=", value, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountIn(List<Long> values) {
            addCriterion("front_amount in", values, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountNotIn(List<Long> values) {
            addCriterion("front_amount not in", values, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountBetween(Long value1, Long value2) {
            addCriterion("front_amount between", value1, value2, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andFrontAmountNotBetween(Long value1, Long value2) {
            addCriterion("front_amount not between", value1, value2, "frontAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNull() {
            addCriterion("actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andActualAmountIsNotNull() {
            addCriterion("actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andActualAmountEqualTo(Long value) {
            addCriterion("actual_amount =", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotEqualTo(Long value) {
            addCriterion("actual_amount <>", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThan(Long value) {
            addCriterion("actual_amount >", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("actual_amount >=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThan(Long value) {
            addCriterion("actual_amount <", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountLessThanOrEqualTo(Long value) {
            addCriterion("actual_amount <=", value, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountIn(List<Long> values) {
            addCriterion("actual_amount in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotIn(List<Long> values) {
            addCriterion("actual_amount not in", values, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountBetween(Long value1, Long value2) {
            addCriterion("actual_amount between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andActualAmountNotBetween(Long value1, Long value2) {
            addCriterion("actual_amount not between", value1, value2, "actualAmount");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIsNull() {
            addCriterion("exchange_rate is null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIsNotNull() {
            addCriterion("exchange_rate is not null");
            return (Criteria) this;
        }

        public Criteria andExchangeRateEqualTo(String value) {
            addCriterion("exchange_rate =", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotEqualTo(String value) {
            addCriterion("exchange_rate <>", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThan(String value) {
            addCriterion("exchange_rate >", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateGreaterThanOrEqualTo(String value) {
            addCriterion("exchange_rate >=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThan(String value) {
            addCriterion("exchange_rate <", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLessThanOrEqualTo(String value) {
            addCriterion("exchange_rate <=", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateLike(String value) {
            addCriterion("exchange_rate like", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotLike(String value) {
            addCriterion("exchange_rate not like", value, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateIn(List<String> values) {
            addCriterion("exchange_rate in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotIn(List<String> values) {
            addCriterion("exchange_rate not in", values, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateBetween(String value1, String value2) {
            addCriterion("exchange_rate between", value1, value2, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andExchangeRateNotBetween(String value1, String value2) {
            addCriterion("exchange_rate not between", value1, value2, "exchangeRate");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountIsNull() {
            addCriterion("cn_actual_amount is null");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountIsNotNull() {
            addCriterion("cn_actual_amount is not null");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountEqualTo(Long value) {
            addCriterion("cn_actual_amount =", value, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountNotEqualTo(Long value) {
            addCriterion("cn_actual_amount <>", value, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountGreaterThan(Long value) {
            addCriterion("cn_actual_amount >", value, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("cn_actual_amount >=", value, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountLessThan(Long value) {
            addCriterion("cn_actual_amount <", value, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountLessThanOrEqualTo(Long value) {
            addCriterion("cn_actual_amount <=", value, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountIn(List<Long> values) {
            addCriterion("cn_actual_amount in", values, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountNotIn(List<Long> values) {
            addCriterion("cn_actual_amount not in", values, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountBetween(Long value1, Long value2) {
            addCriterion("cn_actual_amount between", value1, value2, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andCnActualAmountNotBetween(Long value1, Long value2) {
            addCriterion("cn_actual_amount not between", value1, value2, "cnActualAmount");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
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