<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.03.2024
  Time: 21:15
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

  <title>Бизнесс процесс</title>
</head>
<body>
  <div class="main">
    <table id="color_table" style="width: 100%; table-layout: auto">
      <tr>
        <th class="tblsht">${business.businessName}</th>
      </tr>
      <c:forEach var="docs" items="${business.docsList}">
        <tr>
          <td class="tblsht">
            <a href="/docs/getDoc/${docs.docId}">${docs.docName}</a>
        </tr>
      </c:forEach>
    </table>
    <form id="loadFile" method="POST" action="/docs/fileUpload" enctype="multipart/form-data">
      <input type="hidden" value="${business.businessId}" name="businessId"/>
      <input type="file" name="file"/>
      <button name="addDiv" id="mybtn" class="btn" type="submit">Загрузить файл</button>
    </form>


  </div>
</body>
</html>
