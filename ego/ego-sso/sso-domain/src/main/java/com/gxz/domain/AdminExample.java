package com.gxz.domain;

import java.util.ArrayList;
import java.util.List;

public class AdminExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public AdminExample() {
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

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
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

        public Criteria andIdEqualTo(Short value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Short value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Short value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Short value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Short value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Short value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Short> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Short> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Short value1, Short value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Short value1, Short value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
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

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andEcSaltIsNull() {
            addCriterion("ec_salt is null");
            return (Criteria) this;
        }

        public Criteria andEcSaltIsNotNull() {
            addCriterion("ec_salt is not null");
            return (Criteria) this;
        }

        public Criteria andEcSaltEqualTo(String value) {
            addCriterion("ec_salt =", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltNotEqualTo(String value) {
            addCriterion("ec_salt <>", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltGreaterThan(String value) {
            addCriterion("ec_salt >", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltGreaterThanOrEqualTo(String value) {
            addCriterion("ec_salt >=", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltLessThan(String value) {
            addCriterion("ec_salt <", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltLessThanOrEqualTo(String value) {
            addCriterion("ec_salt <=", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltLike(String value) {
            addCriterion("ec_salt like", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltNotLike(String value) {
            addCriterion("ec_salt not like", value, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltIn(List<String> values) {
            addCriterion("ec_salt in", values, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltNotIn(List<String> values) {
            addCriterion("ec_salt not in", values, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltBetween(String value1, String value2) {
            addCriterion("ec_salt between", value1, value2, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andEcSaltNotBetween(String value1, String value2) {
            addCriterion("ec_salt not between", value1, value2, "ecSalt");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(Integer value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(Integer value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(Integer value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(Integer value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(Integer value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<Integer> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<Integer> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(Integer value1, Integer value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNull() {
            addCriterion("last_login is null");
            return (Criteria) this;
        }

        public Criteria andLastLoginIsNotNull() {
            addCriterion("last_login is not null");
            return (Criteria) this;
        }

        public Criteria andLastLoginEqualTo(Integer value) {
            addCriterion("last_login =", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotEqualTo(Integer value) {
            addCriterion("last_login <>", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThan(Integer value) {
            addCriterion("last_login >", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginGreaterThanOrEqualTo(Integer value) {
            addCriterion("last_login >=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThan(Integer value) {
            addCriterion("last_login <", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginLessThanOrEqualTo(Integer value) {
            addCriterion("last_login <=", value, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginIn(List<Integer> values) {
            addCriterion("last_login in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotIn(List<Integer> values) {
            addCriterion("last_login not in", values, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginBetween(Integer value1, Integer value2) {
            addCriterion("last_login between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastLoginNotBetween(Integer value1, Integer value2) {
            addCriterion("last_login not between", value1, value2, "lastLogin");
            return (Criteria) this;
        }

        public Criteria andLastIpIsNull() {
            addCriterion("last_ip is null");
            return (Criteria) this;
        }

        public Criteria andLastIpIsNotNull() {
            addCriterion("last_ip is not null");
            return (Criteria) this;
        }

        public Criteria andLastIpEqualTo(String value) {
            addCriterion("last_ip =", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotEqualTo(String value) {
            addCriterion("last_ip <>", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpGreaterThan(String value) {
            addCriterion("last_ip >", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpGreaterThanOrEqualTo(String value) {
            addCriterion("last_ip >=", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpLessThan(String value) {
            addCriterion("last_ip <", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpLessThanOrEqualTo(String value) {
            addCriterion("last_ip <=", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpLike(String value) {
            addCriterion("last_ip like", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotLike(String value) {
            addCriterion("last_ip not like", value, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpIn(List<String> values) {
            addCriterion("last_ip in", values, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotIn(List<String> values) {
            addCriterion("last_ip not in", values, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpBetween(String value1, String value2) {
            addCriterion("last_ip between", value1, value2, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLastIpNotBetween(String value1, String value2) {
            addCriterion("last_ip not between", value1, value2, "lastIp");
            return (Criteria) this;
        }

        public Criteria andLangTypeIsNull() {
            addCriterion("lang_type is null");
            return (Criteria) this;
        }

        public Criteria andLangTypeIsNotNull() {
            addCriterion("lang_type is not null");
            return (Criteria) this;
        }

        public Criteria andLangTypeEqualTo(String value) {
            addCriterion("lang_type =", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeNotEqualTo(String value) {
            addCriterion("lang_type <>", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeGreaterThan(String value) {
            addCriterion("lang_type >", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeGreaterThanOrEqualTo(String value) {
            addCriterion("lang_type >=", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeLessThan(String value) {
            addCriterion("lang_type <", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeLessThanOrEqualTo(String value) {
            addCriterion("lang_type <=", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeLike(String value) {
            addCriterion("lang_type like", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeNotLike(String value) {
            addCriterion("lang_type not like", value, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeIn(List<String> values) {
            addCriterion("lang_type in", values, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeNotIn(List<String> values) {
            addCriterion("lang_type not in", values, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeBetween(String value1, String value2) {
            addCriterion("lang_type between", value1, value2, "langType");
            return (Criteria) this;
        }

        public Criteria andLangTypeNotBetween(String value1, String value2) {
            addCriterion("lang_type not between", value1, value2, "langType");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIsNull() {
            addCriterion("agency_id is null");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIsNotNull() {
            addCriterion("agency_id is not null");
            return (Criteria) this;
        }

        public Criteria andAgencyIdEqualTo(Short value) {
            addCriterion("agency_id =", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotEqualTo(Short value) {
            addCriterion("agency_id <>", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdGreaterThan(Short value) {
            addCriterion("agency_id >", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdGreaterThanOrEqualTo(Short value) {
            addCriterion("agency_id >=", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdLessThan(Short value) {
            addCriterion("agency_id <", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdLessThanOrEqualTo(Short value) {
            addCriterion("agency_id <=", value, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdIn(List<Short> values) {
            addCriterion("agency_id in", values, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotIn(List<Short> values) {
            addCriterion("agency_id not in", values, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdBetween(Short value1, Short value2) {
            addCriterion("agency_id between", value1, value2, "agencyId");
            return (Criteria) this;
        }

        public Criteria andAgencyIdNotBetween(Short value1, Short value2) {
            addCriterion("agency_id not between", value1, value2, "agencyId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdIsNull() {
            addCriterion("suppliers_id is null");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdIsNotNull() {
            addCriterion("suppliers_id is not null");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdEqualTo(Short value) {
            addCriterion("suppliers_id =", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdNotEqualTo(Short value) {
            addCriterion("suppliers_id <>", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdGreaterThan(Short value) {
            addCriterion("suppliers_id >", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdGreaterThanOrEqualTo(Short value) {
            addCriterion("suppliers_id >=", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdLessThan(Short value) {
            addCriterion("suppliers_id <", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdLessThanOrEqualTo(Short value) {
            addCriterion("suppliers_id <=", value, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdIn(List<Short> values) {
            addCriterion("suppliers_id in", values, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdNotIn(List<Short> values) {
            addCriterion("suppliers_id not in", values, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdBetween(Short value1, Short value2) {
            addCriterion("suppliers_id between", value1, value2, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andSuppliersIdNotBetween(Short value1, Short value2) {
            addCriterion("suppliers_id not between", value1, value2, "suppliersId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNull() {
            addCriterion("role_id is null");
            return (Criteria) this;
        }

        public Criteria andRoleIdIsNotNull() {
            addCriterion("role_id is not null");
            return (Criteria) this;
        }

        public Criteria andRoleIdEqualTo(Short value) {
            addCriterion("role_id =", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotEqualTo(Short value) {
            addCriterion("role_id <>", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThan(Short value) {
            addCriterion("role_id >", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdGreaterThanOrEqualTo(Short value) {
            addCriterion("role_id >=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThan(Short value) {
            addCriterion("role_id <", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdLessThanOrEqualTo(Short value) {
            addCriterion("role_id <=", value, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdIn(List<Short> values) {
            addCriterion("role_id in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotIn(List<Short> values) {
            addCriterion("role_id not in", values, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdBetween(Short value1, Short value2) {
            addCriterion("role_id between", value1, value2, "roleId");
            return (Criteria) this;
        }

        public Criteria andRoleIdNotBetween(Short value1, Short value2) {
            addCriterion("role_id not between", value1, value2, "roleId");
            return (Criteria) this;
        }
    }

    /**
     */
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