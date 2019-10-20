package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LoginInfoLogExample implements BaseArg {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LoginInfoLogExample() {
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

    @Override
    public void checkArgs() {

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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLoginidIsNull() {
            addCriterion("loginId is null");
            return (Criteria) this;
        }

        public Criteria andLoginidIsNotNull() {
            addCriterion("loginId is not null");
            return (Criteria) this;
        }

        public Criteria andLoginidEqualTo(Integer value) {
            addCriterion("loginId =", value, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidNotEqualTo(Integer value) {
            addCriterion("loginId <>", value, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidGreaterThan(Integer value) {
            addCriterion("loginId >", value, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidGreaterThanOrEqualTo(Integer value) {
            addCriterion("loginId >=", value, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidLessThan(Integer value) {
            addCriterion("loginId <", value, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidLessThanOrEqualTo(Integer value) {
            addCriterion("loginId <=", value, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidIn(List<Integer> values) {
            addCriterion("loginId in", values, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidNotIn(List<Integer> values) {
            addCriterion("loginId not in", values, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidBetween(Integer value1, Integer value2) {
            addCriterion("loginId between", value1, value2, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginidNotBetween(Integer value1, Integer value2) {
            addCriterion("loginId not between", value1, value2, "loginid");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeIsNull() {
            addCriterion("loginUserType is null");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeIsNotNull() {
            addCriterion("loginUserType is not null");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeEqualTo(Integer value) {
            addCriterion("loginUserType =", value, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeNotEqualTo(Integer value) {
            addCriterion("loginUserType <>", value, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeGreaterThan(Integer value) {
            addCriterion("loginUserType >", value, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("loginUserType >=", value, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeLessThan(Integer value) {
            addCriterion("loginUserType <", value, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeLessThanOrEqualTo(Integer value) {
            addCriterion("loginUserType <=", value, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeIn(List<Integer> values) {
            addCriterion("loginUserType in", values, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeNotIn(List<Integer> values) {
            addCriterion("loginUserType not in", values, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeBetween(Integer value1, Integer value2) {
            addCriterion("loginUserType between", value1, value2, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLoginusertypeNotBetween(Integer value1, Integer value2) {
            addCriterion("loginUserType not between", value1, value2, "loginusertype");
            return (Criteria) this;
        }

        public Criteria andLogintimeIsNull() {
            addCriterion("loginTime is null");
            return (Criteria) this;
        }

        public Criteria andLogintimeIsNotNull() {
            addCriterion("loginTime is not null");
            return (Criteria) this;
        }

        public Criteria andLogintimeEqualTo(Date value) {
            addCriterion("loginTime =", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotEqualTo(Date value) {
            addCriterion("loginTime <>", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThan(Date value) {
            addCriterion("loginTime >", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeGreaterThanOrEqualTo(Date value) {
            addCriterion("loginTime >=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThan(Date value) {
            addCriterion("loginTime <", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeLessThanOrEqualTo(Date value) {
            addCriterion("loginTime <=", value, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeIn(List<Date> values) {
            addCriterion("loginTime in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotIn(List<Date> values) {
            addCriterion("loginTime not in", values, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeBetween(Date value1, Date value2) {
            addCriterion("loginTime between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andLogintimeNotBetween(Date value1, Date value2) {
            addCriterion("loginTime not between", value1, value2, "logintime");
            return (Criteria) this;
        }

        public Criteria andIpIsNull() {
            addCriterion("ip is null");
            return (Criteria) this;
        }

        public Criteria andIpIsNotNull() {
            addCriterion("ip is not null");
            return (Criteria) this;
        }

        public Criteria andIpEqualTo(String value) {
            addCriterion("ip =", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotEqualTo(String value) {
            addCriterion("ip <>", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThan(String value) {
            addCriterion("ip >", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpGreaterThanOrEqualTo(String value) {
            addCriterion("ip >=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThan(String value) {
            addCriterion("ip <", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLessThanOrEqualTo(String value) {
            addCriterion("ip <=", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpLike(String value) {
            addCriterion("ip like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotLike(String value) {
            addCriterion("ip not like", value, "ip");
            return (Criteria) this;
        }

        public Criteria andIpIn(List<String> values) {
            addCriterion("ip in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotIn(List<String> values) {
            addCriterion("ip not in", values, "ip");
            return (Criteria) this;
        }

        public Criteria andIpBetween(String value1, String value2) {
            addCriterion("ip between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andIpNotBetween(String value1, String value2) {
            addCriterion("ip not between", value1, value2, "ip");
            return (Criteria) this;
        }

        public Criteria andLocalIsNull() {
            addCriterion("local is null");
            return (Criteria) this;
        }

        public Criteria andLocalIsNotNull() {
            addCriterion("local is not null");
            return (Criteria) this;
        }

        public Criteria andLocalEqualTo(String value) {
            addCriterion("local =", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotEqualTo(String value) {
            addCriterion("local <>", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalGreaterThan(String value) {
            addCriterion("local >", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalGreaterThanOrEqualTo(String value) {
            addCriterion("local >=", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalLessThan(String value) {
            addCriterion("local <", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalLessThanOrEqualTo(String value) {
            addCriterion("local <=", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalLike(String value) {
            addCriterion("local like", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotLike(String value) {
            addCriterion("local not like", value, "local");
            return (Criteria) this;
        }

        public Criteria andLocalIn(List<String> values) {
            addCriterion("local in", values, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotIn(List<String> values) {
            addCriterion("local not in", values, "local");
            return (Criteria) this;
        }

        public Criteria andLocalBetween(String value1, String value2) {
            addCriterion("local between", value1, value2, "local");
            return (Criteria) this;
        }

        public Criteria andLocalNotBetween(String value1, String value2) {
            addCriterion("local not between", value1, value2, "local");
            return (Criteria) this;
        }

        public Criteria andMacaddressIsNull() {
            addCriterion("macAddress is null");
            return (Criteria) this;
        }

        public Criteria andMacaddressIsNotNull() {
            addCriterion("macAddress is not null");
            return (Criteria) this;
        }

        public Criteria andMacaddressEqualTo(String value) {
            addCriterion("macAddress =", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotEqualTo(String value) {
            addCriterion("macAddress <>", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressGreaterThan(String value) {
            addCriterion("macAddress >", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressGreaterThanOrEqualTo(String value) {
            addCriterion("macAddress >=", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressLessThan(String value) {
            addCriterion("macAddress <", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressLessThanOrEqualTo(String value) {
            addCriterion("macAddress <=", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressLike(String value) {
            addCriterion("macAddress like", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotLike(String value) {
            addCriterion("macAddress not like", value, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressIn(List<String> values) {
            addCriterion("macAddress in", values, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotIn(List<String> values) {
            addCriterion("macAddress not in", values, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressBetween(String value1, String value2) {
            addCriterion("macAddress between", value1, value2, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacaddressNotBetween(String value1, String value2) {
            addCriterion("macAddress not between", value1, value2, "macaddress");
            return (Criteria) this;
        }

        public Criteria andMacnameIsNull() {
            addCriterion("macName is null");
            return (Criteria) this;
        }

        public Criteria andMacnameIsNotNull() {
            addCriterion("macName is not null");
            return (Criteria) this;
        }

        public Criteria andMacnameEqualTo(String value) {
            addCriterion("macName =", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameNotEqualTo(String value) {
            addCriterion("macName <>", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameGreaterThan(String value) {
            addCriterion("macName >", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameGreaterThanOrEqualTo(String value) {
            addCriterion("macName >=", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameLessThan(String value) {
            addCriterion("macName <", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameLessThanOrEqualTo(String value) {
            addCriterion("macName <=", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameLike(String value) {
            addCriterion("macName like", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameNotLike(String value) {
            addCriterion("macName not like", value, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameIn(List<String> values) {
            addCriterion("macName in", values, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameNotIn(List<String> values) {
            addCriterion("macName not in", values, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameBetween(String value1, String value2) {
            addCriterion("macName between", value1, value2, "macname");
            return (Criteria) this;
        }

        public Criteria andMacnameNotBetween(String value1, String value2) {
            addCriterion("macName not between", value1, value2, "macname");
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