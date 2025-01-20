<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 13.10.2022
  Time: 16:16
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

  <title>Конвертер</title>
</head>
<body>
<div class="main">
  <div style="margin-top: 80px;">
    <a style=" color: blue">Конвертация прошла успешно!</a>
    <a style=" color: blue">Обработано: ${mapSize} файл(-а, -ов).</a>
  </div>
  <div>
    <button class="btn" type="button" onclick="document.location='/converter/download'">Скачать</button>
  </div>
</div>
</body>
</html>

