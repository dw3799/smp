package com.alipapa.smp.consumer.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConsumerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConsumerExample() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andCountryIsNull() {
            addCriterion("country is null");
            return (Criteria) this;
        }

        public Criteria andCountryIsNotNull() {
            addCriterion("country is not null");
            return (Criteria) this;
        }

        public Criteria andCountryEqualTo(String value) {
            addCriterion("country =", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotEqualTo(String value) {
            addCriterion("country <>", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThan(String value) {
            addCriterion("country >", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryGreaterThanOrEqualTo(String value) {
            addCriterion("country >=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThan(String value) {
            addCriterion("country <", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLessThanOrEqualTo(String value) {
            addCriterion("country <=", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryLike(String value) {
            addCriterion("country like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotLike(String value) {
            addCriterion("country not like", value, "country");
            return (Criteria) this;
        }

        public Criteria andCountryIn(List<String> values) {
            addCriterion("country in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotIn(List<String> values) {
            addCriterion("country not in", values, "country");
            return (Criteria) this;
        }

        public Criteria andCountryBetween(String value1, String value2) {
            addCriterion("country between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andCountryNotBetween(String value1, String value2) {
            addCriterion("country not between", value1, value2, "country");
            return (Criteria) this;
        }

        public Criteria andMainBusinessIsNull() {
            addCriterion("main_business is null");
            return (Criteria) this;
        }

        public Criteria andMainBusinessIsNotNull() {
            addCriterion("main_business is not null");
            return (Criteria) this;
        }

        public Criteria andMainBusinessEqualTo(String value) {
            addCriterion("main_business =", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessNotEqualTo(String value) {
            addCriterion("main_business <>", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessGreaterThan(String value) {
            addCriterion("main_business >", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessGreaterThanOrEqualTo(String value) {
            addCriterion("main_business >=", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessLessThan(String value) {
            addCriterion("main_business <", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessLessThanOrEqualTo(String value) {
            addCriterion("main_business <=", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessLike(String value) {
            addCriterion("main_business like", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessNotLike(String value) {
            addCriterion("main_business not like", value, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessIn(List<String> values) {
            addCriterion("main_business in", values, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessNotIn(List<String> values) {
            addCriterion("main_business not in", values, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessBetween(String value1, String value2) {
            addCriterion("main_business between", value1, value2, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andMainBusinessNotBetween(String value1, String value2) {
            addCriterion("main_business not between", value1, value2, "mainBusiness");
            return (Criteria) this;
        }

        public Criteria andSourceIsNull() {
            addCriterion("source is null");
            return (Criteria) this;
        }

        public Criteria andSourceIsNotNull() {
            addCriterion("source is not null");
            return (Criteria) this;
        }

        public Criteria andSourceEqualTo(String value) {
            addCriterion("source =", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotEqualTo(String value) {
            addCriterion("source <>", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThan(String value) {
            addCriterion("source >", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceGreaterThanOrEqualTo(String value) {
            addCriterion("source >=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThan(String value) {
            addCriterion("source <", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLessThanOrEqualTo(String value) {
            addCriterion("source <=", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceLike(String value) {
            addCriterion("source like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotLike(String value) {
            addCriterion("source not like", value, "source");
            return (Criteria) this;
        }

        public Criteria andSourceIn(List<String> values) {
            addCriterion("source in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotIn(List<String> values) {
            addCriterion("source not in", values, "source");
            return (Criteria) this;
        }

        public Criteria andSourceBetween(String value1, String value2) {
            addCriterion("source between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andSourceNotBetween(String value1, String value2) {
            addCriterion("source not between", value1, value2, "source");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andFacebookIsNull() {
            addCriterion("facebook is null");
            return (Criteria) this;
        }

        public Criteria andFacebookIsNotNull() {
            addCriterion("facebook is not null");
            return (Criteria) this;
        }

        public Criteria andFacebookEqualTo(String value) {
            addCriterion("facebook =", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookNotEqualTo(String value) {
            addCriterion("facebook <>", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookGreaterThan(String value) {
            addCriterion("facebook >", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookGreaterThanOrEqualTo(String value) {
            addCriterion("facebook >=", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookLessThan(String value) {
            addCriterion("facebook <", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookLessThanOrEqualTo(String value) {
            addCriterion("facebook <=", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookLike(String value) {
            addCriterion("facebook like", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookNotLike(String value) {
            addCriterion("facebook not like", value, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookIn(List<String> values) {
            addCriterion("facebook in", values, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookNotIn(List<String> values) {
            addCriterion("facebook not in", values, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookBetween(String value1, String value2) {
            addCriterion("facebook between", value1, value2, "facebook");
            return (Criteria) this;
        }

        public Criteria andFacebookNotBetween(String value1, String value2) {
            addCriterion("facebook not between", value1, value2, "facebook");
            return (Criteria) this;
        }

        public Criteria andWhatsappIsNull() {
            addCriterion("whatsapp is null");
            return (Criteria) this;
        }

        public Criteria andWhatsappIsNotNull() {
            addCriterion("whatsapp is not null");
            return (Criteria) this;
        }

        public Criteria andWhatsappEqualTo(String value) {
            addCriterion("whatsapp =", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappNotEqualTo(String value) {
            addCriterion("whatsapp <>", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappGreaterThan(String value) {
            addCriterion("whatsapp >", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappGreaterThanOrEqualTo(String value) {
            addCriterion("whatsapp >=", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappLessThan(String value) {
            addCriterion("whatsapp <", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappLessThanOrEqualTo(String value) {
            addCriterion("whatsapp <=", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappLike(String value) {
            addCriterion("whatsapp like", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappNotLike(String value) {
            addCriterion("whatsapp not like", value, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappIn(List<String> values) {
            addCriterion("whatsapp in", values, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappNotIn(List<String> values) {
            addCriterion("whatsapp not in", values, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappBetween(String value1, String value2) {
            addCriterion("whatsapp between", value1, value2, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andWhatsappNotBetween(String value1, String value2) {
            addCriterion("whatsapp not between", value1, value2, "whatsapp");
            return (Criteria) this;
        }

        public Criteria andLinkedinIsNull() {
            addCriterion("linkedin is null");
            return (Criteria) this;
        }

        public Criteria andLinkedinIsNotNull() {
            addCriterion("linkedin is not null");
            return (Criteria) this;
        }

        public Criteria andLinkedinEqualTo(String value) {
            addCriterion("linkedin =", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinNotEqualTo(String value) {
            addCriterion("linkedin <>", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinGreaterThan(String value) {
            addCriterion("linkedin >", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinGreaterThanOrEqualTo(String value) {
            addCriterion("linkedin >=", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinLessThan(String value) {
            addCriterion("linkedin <", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinLessThanOrEqualTo(String value) {
            addCriterion("linkedin <=", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinLike(String value) {
            addCriterion("linkedin like", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinNotLike(String value) {
            addCriterion("linkedin not like", value, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinIn(List<String> values) {
            addCriterion("linkedin in", values, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinNotIn(List<String> values) {
            addCriterion("linkedin not in", values, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinBetween(String value1, String value2) {
            addCriterion("linkedin between", value1, value2, "linkedin");
            return (Criteria) this;
        }

        public Criteria andLinkedinNotBetween(String value1, String value2) {
            addCriterion("linkedin not between", value1, value2, "linkedin");
            return (Criteria) this;
        }

        public Criteria andWechatIsNull() {
            addCriterion("wechat is null");
            return (Criteria) this;
        }

        public Criteria andWechatIsNotNull() {
            addCriterion("wechat is not null");
            return (Criteria) this;
        }

        public Criteria andWechatEqualTo(String value) {
            addCriterion("wechat =", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotEqualTo(String value) {
            addCriterion("wechat <>", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThan(String value) {
            addCriterion("wechat >", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatGreaterThanOrEqualTo(String value) {
            addCriterion("wechat >=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThan(String value) {
            addCriterion("wechat <", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLessThanOrEqualTo(String value) {
            addCriterion("wechat <=", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatLike(String value) {
            addCriterion("wechat like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotLike(String value) {
            addCriterion("wechat not like", value, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatIn(List<String> values) {
            addCriterion("wechat in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotIn(List<String> values) {
            addCriterion("wechat not in", values, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatBetween(String value1, String value2) {
            addCriterion("wechat between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andWechatNotBetween(String value1, String value2) {
            addCriterion("wechat not between", value1, value2, "wechat");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(String value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(String value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(String value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(String value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(String value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(String value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLike(String value) {
            addCriterion("qq like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotLike(String value) {
            addCriterion("qq not like", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<String> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<String> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(String value1, String value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(String value1, String value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andContactsIsNull() {
            addCriterion("contacts is null");
            return (Criteria) this;
        }

        public Criteria andContactsIsNotNull() {
            addCriterion("contacts is not null");
            return (Criteria) this;
        }

        public Criteria andContactsEqualTo(String value) {
            addCriterion("contacts =", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotEqualTo(String value) {
            addCriterion("contacts <>", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThan(String value) {
            addCriterion("contacts >", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsGreaterThanOrEqualTo(String value) {
            addCriterion("contacts >=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThan(String value) {
            addCriterion("contacts <", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLessThanOrEqualTo(String value) {
            addCriterion("contacts <=", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsLike(String value) {
            addCriterion("contacts like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotLike(String value) {
            addCriterion("contacts not like", value, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsIn(List<String> values) {
            addCriterion("contacts in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotIn(List<String> values) {
            addCriterion("contacts not in", values, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsBetween(String value1, String value2) {
            addCriterion("contacts between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andContactsNotBetween(String value1, String value2) {
            addCriterion("contacts not between", value1, value2, "contacts");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNull() {
            addCriterion("company_address is null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIsNotNull() {
            addCriterion("company_address is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressEqualTo(String value) {
            addCriterion("company_address =", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotEqualTo(String value) {
            addCriterion("company_address <>", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThan(String value) {
            addCriterion("company_address >", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressGreaterThanOrEqualTo(String value) {
            addCriterion("company_address >=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThan(String value) {
            addCriterion("company_address <", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLessThanOrEqualTo(String value) {
            addCriterion("company_address <=", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressLike(String value) {
            addCriterion("company_address like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotLike(String value) {
            addCriterion("company_address not like", value, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressIn(List<String> values) {
            addCriterion("company_address in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotIn(List<String> values) {
            addCriterion("company_address not in", values, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressBetween(String value1, String value2) {
            addCriterion("company_address between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyAddressNotBetween(String value1, String value2) {
            addCriterion("company_address not between", value1, value2, "companyAddress");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteIsNull() {
            addCriterion("company_website is null");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteIsNotNull() {
            addCriterion("company_website is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteEqualTo(String value) {
            addCriterion("company_website =", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteNotEqualTo(String value) {
            addCriterion("company_website <>", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteGreaterThan(String value) {
            addCriterion("company_website >", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteGreaterThanOrEqualTo(String value) {
            addCriterion("company_website >=", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteLessThan(String value) {
            addCriterion("company_website <", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteLessThanOrEqualTo(String value) {
            addCriterion("company_website <=", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteLike(String value) {
            addCriterion("company_website like", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteNotLike(String value) {
            addCriterion("company_website not like", value, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteIn(List<String> values) {
            addCriterion("company_website in", values, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteNotIn(List<String> values) {
            addCriterion("company_website not in", values, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteBetween(String value1, String value2) {
            addCriterion("company_website between", value1, value2, "companyWebsite");
            return (Criteria) this;
        }

        public Criteria andCompanyWebsiteNotBetween(String value1, String value2) {
            addCriterion("company_website not between", value1, value2, "companyWebsite");
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

        public Criteria andTelMobileIsNull() {
            addCriterion("tel_mobile is null");
            return (Criteria) this;
        }

        public Criteria andTelMobileIsNotNull() {
            addCriterion("tel_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andTelMobileEqualTo(String value) {
            addCriterion("tel_mobile =", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileNotEqualTo(String value) {
            addCriterion("tel_mobile <>", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileGreaterThan(String value) {
            addCriterion("tel_mobile >", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileGreaterThanOrEqualTo(String value) {
            addCriterion("tel_mobile >=", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileLessThan(String value) {
            addCriterion("tel_mobile <", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileLessThanOrEqualTo(String value) {
            addCriterion("tel_mobile <=", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileLike(String value) {
            addCriterion("tel_mobile like", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileNotLike(String value) {
            addCriterion("tel_mobile not like", value, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileIn(List<String> values) {
            addCriterion("tel_mobile in", values, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileNotIn(List<String> values) {
            addCriterion("tel_mobile not in", values, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileBetween(String value1, String value2) {
            addCriterion("tel_mobile between", value1, value2, "telMobile");
            return (Criteria) this;
        }

        public Criteria andTelMobileNotBetween(String value1, String value2) {
            addCriterion("tel_mobile not between", value1, value2, "telMobile");
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

        public Criteria andReceivingAddressIsNull() {
            addCriterion("receiving_address is null");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressIsNotNull() {
            addCriterion("receiving_address is not null");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressEqualTo(String value) {
            addCriterion("receiving_address =", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressNotEqualTo(String value) {
            addCriterion("receiving_address <>", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressGreaterThan(String value) {
            addCriterion("receiving_address >", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressGreaterThanOrEqualTo(String value) {
            addCriterion("receiving_address >=", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressLessThan(String value) {
            addCriterion("receiving_address <", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressLessThanOrEqualTo(String value) {
            addCriterion("receiving_address <=", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressLike(String value) {
            addCriterion("receiving_address like", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressNotLike(String value) {
            addCriterion("receiving_address not like", value, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressIn(List<String> values) {
            addCriterion("receiving_address in", values, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressNotIn(List<String> values) {
            addCriterion("receiving_address not in", values, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressBetween(String value1, String value2) {
            addCriterion("receiving_address between", value1, value2, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andReceivingAddressNotBetween(String value1, String value2) {
            addCriterion("receiving_address not between", value1, value2, "receivingAddress");
            return (Criteria) this;
        }

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(String value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(String value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(String value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(String value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(String value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(String value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLike(String value) {
            addCriterion("level like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotLike(String value) {
            addCriterion("level not like", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<String> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<String> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(String value1, String value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(String value1, String value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andHasOrderIsNull() {
            addCriterion("has_order is null");
            return (Criteria) this;
        }

        public Criteria andHasOrderIsNotNull() {
            addCriterion("has_order is not null");
            return (Criteria) this;
        }

        public Criteria andHasOrderEqualTo(String value) {
            addCriterion("has_order =", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderNotEqualTo(String value) {
            addCriterion("has_order <>", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderGreaterThan(String value) {
            addCriterion("has_order >", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderGreaterThanOrEqualTo(String value) {
            addCriterion("has_order >=", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderLessThan(String value) {
            addCriterion("has_order <", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderLessThanOrEqualTo(String value) {
            addCriterion("has_order <=", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderLike(String value) {
            addCriterion("has_order like", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderNotLike(String value) {
            addCriterion("has_order not like", value, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderIn(List<String> values) {
            addCriterion("has_order in", values, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderNotIn(List<String> values) {
            addCriterion("has_order not in", values, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderBetween(String value1, String value2) {
            addCriterion("has_order between", value1, value2, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andHasOrderNotBetween(String value1, String value2) {
            addCriterion("has_order not between", value1, value2, "hasOrder");
            return (Criteria) this;
        }

        public Criteria andBelongUserIsNull() {
            addCriterion("belong_user is null");
            return (Criteria) this;
        }

        public Criteria andBelongUserIsNotNull() {
            addCriterion("belong_user is not null");
            return (Criteria) this;
        }

        public Criteria andBelongUserEqualTo(String value) {
            addCriterion("belong_user =", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotEqualTo(String value) {
            addCriterion("belong_user <>", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserGreaterThan(String value) {
            addCriterion("belong_user >", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserGreaterThanOrEqualTo(String value) {
            addCriterion("belong_user >=", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserLessThan(String value) {
            addCriterion("belong_user <", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserLessThanOrEqualTo(String value) {
            addCriterion("belong_user <=", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserLike(String value) {
            addCriterion("belong_user like", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotLike(String value) {
            addCriterion("belong_user not like", value, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserIn(List<String> values) {
            addCriterion("belong_user in", values, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotIn(List<String> values) {
            addCriterion("belong_user not in", values, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserBetween(String value1, String value2) {
            addCriterion("belong_user between", value1, value2, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongUserNotBetween(String value1, String value2) {
            addCriterion("belong_user not between", value1, value2, "belongUser");
            return (Criteria) this;
        }

        public Criteria andBelongGroupIsNull() {
            addCriterion("belong_group is null");
            return (Criteria) this;
        }

        public Criteria andBelongGroupIsNotNull() {
            addCriterion("belong_group is not null");
            return (Criteria) this;
        }

        public Criteria andBelongGroupEqualTo(String value) {
            addCriterion("belong_group =", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupNotEqualTo(String value) {
            addCriterion("belong_group <>", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupGreaterThan(String value) {
            addCriterion("belong_group >", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupGreaterThanOrEqualTo(String value) {
            addCriterion("belong_group >=", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupLessThan(String value) {
            addCriterion("belong_group <", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupLessThanOrEqualTo(String value) {
            addCriterion("belong_group <=", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupLike(String value) {
            addCriterion("belong_group like", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupNotLike(String value) {
            addCriterion("belong_group not like", value, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupIn(List<String> values) {
            addCriterion("belong_group in", values, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupNotIn(List<String> values) {
            addCriterion("belong_group not in", values, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupBetween(String value1, String value2) {
            addCriterion("belong_group between", value1, value2, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andBelongGroupNotBetween(String value1, String value2) {
            addCriterion("belong_group not between", value1, value2, "belongGroup");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNull() {
            addCriterion("create_user is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIsNotNull() {
            addCriterion("create_user is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserEqualTo(String value) {
            addCriterion("create_user =", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotEqualTo(String value) {
            addCriterion("create_user <>", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThan(String value) {
            addCriterion("create_user >", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserGreaterThanOrEqualTo(String value) {
            addCriterion("create_user >=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThan(String value) {
            addCriterion("create_user <", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLessThanOrEqualTo(String value) {
            addCriterion("create_user <=", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserLike(String value) {
            addCriterion("create_user like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotLike(String value) {
            addCriterion("create_user not like", value, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserIn(List<String> values) {
            addCriterion("create_user in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotIn(List<String> values) {
            addCriterion("create_user not in", values, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserBetween(String value1, String value2) {
            addCriterion("create_user between", value1, value2, "createUser");
            return (Criteria) this;
        }

        public Criteria andCreateUserNotBetween(String value1, String value2) {
            addCriterion("create_user not between", value1, value2, "createUser");
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