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
  <a style="margin-top: 10px; color: crimson; font: bold italic 110% serif">
    <c:if test="${param.get('error') == 100}">Произошла ошибка при загрузке файла. Проверьте его структуру!</c:if>
  </a>

  <p>Внимание! Поддерживается загрузка файлов</p>
  <p>- из ПК ОИФЛ ПУ</p>
  <p>- файлы UNO ФНС России</p>
  <p>- квитанции из налоговой 2zh</p>

  <form id="load" method="POST" action="/fileConverter/fileUpload" enctype="multipart/form-data">
    <input type="file" name="file" style="margin-top: 10px"><br />
    <button class="btn" type="submit" value="Upload">Загрузить</button>
  </form>
</div>
</body>
</html>

