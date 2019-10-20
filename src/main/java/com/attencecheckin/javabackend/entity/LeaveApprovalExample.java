package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LeaveApprovalExample implements BaseArg {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LeaveApprovalExample() {
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

        public Criteria andStudentidIsNull() {
            addCriterion("studentId is null");
            return (Criteria) this;
        }

        public Criteria andStudentidIsNotNull() {
            addCriterion("studentId is not null");
            return (Criteria) this;
        }

        public Criteria andStudentidEqualTo(Integer value) {
            addCriterion("studentId =", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidNotEqualTo(Integer value) {
            addCriterion("studentId <>", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidGreaterThan(Integer value) {
            addCriterion("studentId >", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidGreaterThanOrEqualTo(Integer value) {
            addCriterion("studentId >=", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidLessThan(Integer value) {
            addCriterion("studentId <", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidLessThanOrEqualTo(Integer value) {
            addCriterion("studentId <=", value, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidIn(List<Integer> values) {
            addCriterion("studentId in", values, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidNotIn(List<Integer> values) {
            addCriterion("studentId not in", values, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidBetween(Integer value1, Integer value2) {
            addCriterion("studentId between", value1, value2, "studentid");
            return (Criteria) this;
        }

        public Criteria andStudentidNotBetween(Integer value1, Integer value2) {
            addCriterion("studentId not between", value1, value2, "studentid");
            return (Criteria) this;
        }

        public Criteria andLeavereasonIsNull() {
            addCriterion("leaveReason is null");
            return (Criteria) this;
        }

        public Criteria andLeavereasonIsNotNull() {
            addCriterion("leaveReason is not null");
            return (Criteria) this;
        }

        public Criteria andLeavereasonEqualTo(String value) {
            addCriterion("leaveReason =", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonNotEqualTo(String value) {
            addCriterion("leaveReason <>", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonGreaterThan(String value) {
            addCriterion("leaveReason >", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonGreaterThanOrEqualTo(String value) {
            addCriterion("leaveReason >=", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonLessThan(String value) {
            addCriterion("leaveReason <", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonLessThanOrEqualTo(String value) {
            addCriterion("leaveReason <=", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonLike(String value) {
            addCriterion("leaveReason like", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonNotLike(String value) {
            addCriterion("leaveReason not like", value, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonIn(List<String> values) {
            addCriterion("leaveReason in", values, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonNotIn(List<String> values) {
            addCriterion("leaveReason not in", values, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonBetween(String value1, String value2) {
            addCriterion("leaveReason between", value1, value2, "leavereason");
            return (Criteria) this;
        }

        public Criteria andLeavereasonNotBetween(String value1, String value2) {
            addCriterion("leaveReason not between", value1, value2, "leavereason");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCourseidIsNull() {
            addCriterion("courseId is null");
            return (Criteria) this;
        }

        public Criteria andCourseidIsNotNull() {
            addCriterion("courseId is not null");
            return (Criteria) this;
        }

        public Criteria andCourseidEqualTo(Integer value) {
            addCriterion("courseId =", value, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidNotEqualTo(Integer value) {
            addCriterion("courseId <>", value, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidGreaterThan(Integer value) {
            addCriterion("courseId >", value, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidGreaterThanOrEqualTo(Integer value) {
            addCriterion("courseId >=", value, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidLessThan(Integer value) {
            addCriterion("courseId <", value, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidLessThanOrEqualTo(Integer value) {
            addCriterion("courseId <=", value, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidIn(List<Integer> values) {
            addCriterion("courseId in", values, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidNotIn(List<Integer> values) {
            addCriterion("courseId not in", values, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidBetween(Integer value1, Integer value2) {
            addCriterion("courseId between", value1, value2, "courseid");
            return (Criteria) this;
        }

        public Criteria andCourseidNotBetween(Integer value1, Integer value2) {
            addCriterion("courseId not between", value1, value2, "courseid");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeIsNull() {
            addCriterion("leaveSubTime is null");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeIsNotNull() {
            addCriterion("leaveSubTime is not null");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeEqualTo(Date value) {
            addCriterion("leaveSubTime =", value, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeNotEqualTo(Date value) {
            addCriterion("leaveSubTime <>", value, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeGreaterThan(Date value) {
            addCriterion("leaveSubTime >", value, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("leaveSubTime >=", value, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeLessThan(Date value) {
            addCriterion("leaveSubTime <", value, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeLessThanOrEqualTo(Date value) {
            addCriterion("leaveSubTime <=", value, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeIn(List<Date> values) {
            addCriterion("leaveSubTime in", values, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeNotIn(List<Date> values) {
            addCriterion("leaveSubTime not in", values, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeBetween(Date value1, Date value2) {
            addCriterion("leaveSubTime between", value1, value2, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andLeavesubtimeNotBetween(Date value1, Date value2) {
            addCriterion("leaveSubTime not between", value1, value2, "leavesubtime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeIsNull() {
            addCriterion("approvalTime is null");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeIsNotNull() {
            addCriterion("approvalTime is not null");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeEqualTo(Date value) {
            addCriterion("approvalTime =", value, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeNotEqualTo(Date value) {
            addCriterion("approvalTime <>", value, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeGreaterThan(Date value) {
            addCriterion("approvalTime >", value, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeGreaterThanOrEqualTo(Date value) {
            addCriterion("approvalTime >=", value, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeLessThan(Date value) {
            addCriterion("approvalTime <", value, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeLessThanOrEqualTo(Date value) {
            addCriterion("approvalTime <=", value, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeIn(List<Date> values) {
            addCriterion("approvalTime in", values, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeNotIn(List<Date> values) {
            addCriterion("approvalTime not in", values, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeBetween(Date value1, Date value2) {
            addCriterion("approvalTime between", value1, value2, "approvaltime");
            return (Criteria) this;
        }

        public Criteria andApprovaltimeNotBetween(Date value1, Date value2) {
            addCriterion("approvalTime not between", value1, value2, "approvaltime");
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