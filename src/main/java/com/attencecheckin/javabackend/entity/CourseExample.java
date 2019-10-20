package com.attencecheckin.javabackend.entity;

import com.attencecheckin.javabackend.common.arg.BaseArg;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CourseExample implements BaseArg {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CourseExample() {
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

        public Criteria andCoursenameIsNull() {
            addCriterion("courseName is null");
            return (Criteria) this;
        }

        public Criteria andCoursenameIsNotNull() {
            addCriterion("courseName is not null");
            return (Criteria) this;
        }

        public Criteria andCoursenameEqualTo(String value) {
            addCriterion("courseName =", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameNotEqualTo(String value) {
            addCriterion("courseName <>", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameGreaterThan(String value) {
            addCriterion("courseName >", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameGreaterThanOrEqualTo(String value) {
            addCriterion("courseName >=", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameLessThan(String value) {
            addCriterion("courseName <", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameLessThanOrEqualTo(String value) {
            addCriterion("courseName <=", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameLike(String value) {
            addCriterion("courseName like", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameNotLike(String value) {
            addCriterion("courseName not like", value, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameIn(List<String> values) {
            addCriterion("courseName in", values, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameNotIn(List<String> values) {
            addCriterion("courseName not in", values, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameBetween(String value1, String value2) {
            addCriterion("courseName between", value1, value2, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursenameNotBetween(String value1, String value2) {
            addCriterion("courseName not between", value1, value2, "coursename");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeIsNull() {
            addCriterion("courseStartTime is null");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeIsNotNull() {
            addCriterion("courseStartTime is not null");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeEqualTo(Date value) {
            addCriterion("courseStartTime =", value, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeNotEqualTo(Date value) {
            addCriterion("courseStartTime <>", value, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeGreaterThan(Date value) {
            addCriterion("courseStartTime >", value, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("courseStartTime >=", value, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeLessThan(Date value) {
            addCriterion("courseStartTime <", value, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeLessThanOrEqualTo(Date value) {
            addCriterion("courseStartTime <=", value, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeIn(List<Date> values) {
            addCriterion("courseStartTime in", values, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeNotIn(List<Date> values) {
            addCriterion("courseStartTime not in", values, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeBetween(Date value1, Date value2) {
            addCriterion("courseStartTime between", value1, value2, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCoursestarttimeNotBetween(Date value1, Date value2) {
            addCriterion("courseStartTime not between", value1, value2, "coursestarttime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeIsNull() {
            addCriterion("courseEndTime is null");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeIsNotNull() {
            addCriterion("courseEndTime is not null");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeEqualTo(Date value) {
            addCriterion("courseEndTime =", value, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeNotEqualTo(Date value) {
            addCriterion("courseEndTime <>", value, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeGreaterThan(Date value) {
            addCriterion("courseEndTime >", value, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("courseEndTime >=", value, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeLessThan(Date value) {
            addCriterion("courseEndTime <", value, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeLessThanOrEqualTo(Date value) {
            addCriterion("courseEndTime <=", value, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeIn(List<Date> values) {
            addCriterion("courseEndTime in", values, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeNotIn(List<Date> values) {
            addCriterion("courseEndTime not in", values, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeBetween(Date value1, Date value2) {
            addCriterion("courseEndTime between", value1, value2, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andCourseendtimeNotBetween(Date value1, Date value2) {
            addCriterion("courseEndTime not between", value1, value2, "courseendtime");
            return (Criteria) this;
        }

        public Criteria andWeekIsNull() {
            addCriterion("week is null");
            return (Criteria) this;
        }

        public Criteria andWeekIsNotNull() {
            addCriterion("week is not null");
            return (Criteria) this;
        }

        public Criteria andWeekEqualTo(Integer value) {
            addCriterion("week =", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotEqualTo(Integer value) {
            addCriterion("week <>", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThan(Integer value) {
            addCriterion("week >", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekGreaterThanOrEqualTo(Integer value) {
            addCriterion("week >=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThan(Integer value) {
            addCriterion("week <", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekLessThanOrEqualTo(Integer value) {
            addCriterion("week <=", value, "week");
            return (Criteria) this;
        }

        public Criteria andWeekIn(List<Integer> values) {
            addCriterion("week in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotIn(List<Integer> values) {
            addCriterion("week not in", values, "week");
            return (Criteria) this;
        }

        public Criteria andWeekBetween(Integer value1, Integer value2) {
            addCriterion("week between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andWeekNotBetween(Integer value1, Integer value2) {
            addCriterion("week not between", value1, value2, "week");
            return (Criteria) this;
        }

        public Criteria andJieciIsNull() {
            addCriterion("jieci is null");
            return (Criteria) this;
        }

        public Criteria andJieciIsNotNull() {
            addCriterion("jieci is not null");
            return (Criteria) this;
        }

        public Criteria andJieciEqualTo(Integer value) {
            addCriterion("jieci =", value, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciNotEqualTo(Integer value) {
            addCriterion("jieci <>", value, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciGreaterThan(Integer value) {
            addCriterion("jieci >", value, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciGreaterThanOrEqualTo(Integer value) {
            addCriterion("jieci >=", value, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciLessThan(Integer value) {
            addCriterion("jieci <", value, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciLessThanOrEqualTo(Integer value) {
            addCriterion("jieci <=", value, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciIn(List<Integer> values) {
            addCriterion("jieci in", values, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciNotIn(List<Integer> values) {
            addCriterion("jieci not in", values, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciBetween(Integer value1, Integer value2) {
            addCriterion("jieci between", value1, value2, "jieci");
            return (Criteria) this;
        }

        public Criteria andJieciNotBetween(Integer value1, Integer value2) {
            addCriterion("jieci not between", value1, value2, "jieci");
            return (Criteria) this;
        }

        public Criteria andClassidIsNull() {
            addCriterion("classId is null");
            return (Criteria) this;
        }

        public Criteria andClassidIsNotNull() {
            addCriterion("classId is not null");
            return (Criteria) this;
        }

        public Criteria andClassidEqualTo(Integer value) {
            addCriterion("classId =", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotEqualTo(Integer value) {
            addCriterion("classId <>", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThan(Integer value) {
            addCriterion("classId >", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidGreaterThanOrEqualTo(Integer value) {
            addCriterion("classId >=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThan(Integer value) {
            addCriterion("classId <", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidLessThanOrEqualTo(Integer value) {
            addCriterion("classId <=", value, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidIn(List<Integer> values) {
            addCriterion("classId in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotIn(List<Integer> values) {
            addCriterion("classId not in", values, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidBetween(Integer value1, Integer value2) {
            addCriterion("classId between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassidNotBetween(Integer value1, Integer value2) {
            addCriterion("classId not between", value1, value2, "classid");
            return (Criteria) this;
        }

        public Criteria andClassroomidIsNull() {
            addCriterion("classRoomId is null");
            return (Criteria) this;
        }

        public Criteria andClassroomidIsNotNull() {
            addCriterion("classRoomId is not null");
            return (Criteria) this;
        }

        public Criteria andClassroomidEqualTo(Integer value) {
            addCriterion("classRoomId =", value, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidNotEqualTo(Integer value) {
            addCriterion("classRoomId <>", value, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidGreaterThan(Integer value) {
            addCriterion("classRoomId >", value, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidGreaterThanOrEqualTo(Integer value) {
            addCriterion("classRoomId >=", value, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidLessThan(Integer value) {
            addCriterion("classRoomId <", value, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidLessThanOrEqualTo(Integer value) {
            addCriterion("classRoomId <=", value, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidIn(List<Integer> values) {
            addCriterion("classRoomId in", values, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidNotIn(List<Integer> values) {
            addCriterion("classRoomId not in", values, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidBetween(Integer value1, Integer value2) {
            addCriterion("classRoomId between", value1, value2, "classroomid");
            return (Criteria) this;
        }

        public Criteria andClassroomidNotBetween(Integer value1, Integer value2) {
            addCriterion("classRoomId not between", value1, value2, "classroomid");
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