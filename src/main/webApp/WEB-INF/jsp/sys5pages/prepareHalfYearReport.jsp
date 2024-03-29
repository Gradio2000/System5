<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 04.06.2022
  Time: 09:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<script type="text/javascript" src="../../../js/jquery-3.6.0.js"></script>
<html>
<style>
  <%@include file="../../includes/myStyle.css"%>
</style>
<head>
  <jsp:include page="../../includes/header.jsp"/>
  <jsp:include page="../../includes/menu.jsp"/>
  <title>Отчет</title>
</head>
<body>
<div class="main">
  <form method="get" action="/admin/halfYearReport">
    <div style="margin-top: 80px">
      <select class="select-css" name="year" onchange="setYear(this.value)"
              style="width: max-content;">
        <option disabled selected>Год</option>
        <c:forEach var="year" items="${years}">
          <option value="${year}">${year}</option>
        </c:forEach>
      </select>
    </div>
    <select name="half" class="select-css" style="width: max-content">
      <option value="1">За 1 полугодие</option>
      <option value="2">За 2 полугодие</option>
    </select>
    <button type="submit" class="btn">Сформировать</button>
  </form>

</div>
</body>
</html>
