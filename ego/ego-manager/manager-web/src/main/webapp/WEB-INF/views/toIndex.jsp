<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 该页面的功能就是跳转到主页里面  ${ctx}/toIndex 访问controller-->
<jsp:forward page="${pageContext.request.contextPath}/toIndex"></jsp:forward>