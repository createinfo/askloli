<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
    String contextPath = request.getContextPath();
%>
${contextPath}
<form action="<%=contextPath%>/user/manage/survey/new" method="post">
<input type="text" name="title" placeholder="标题">
<input type="text" name="description" placeholder="描述">
<input type="submit" value="提交">
</form>