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

    <div id="wrapper">
      <div id="left">
        <table id="color_table" style="table-layout: auto">
          <tr>
            <th class="tblsht">${business.businessName}</th>
          </tr>
          <c:forEach var="docs" items="${business.docsList}">
            <tr>
              <td class="tblsht">
                <a onclick="getTextFromFile(${docs.docId})">${docs.docName}</a>
            </tr>
          </c:forEach>
        </table>
        <input form="loadFile" type="file" name="file"/>
        <button form="loadFile" name="addDiv" id="mybtn" class="btn" type="submit">Загрузить файл</button>
      </div>
      <div id="textFromFile"></div>
    </div>

    <form id="loadFile" method="POST" action="/docs/fileUpload" enctype="multipart/form-data">
      <input type="hidden" value="${business.businessId}" name="businessId"/>
    </form>


  </div>
</body>
<script>
  function getTextFromFile(id){
    $.ajax({
      type: 'GET',
      url: '/docs/getDoc/' + id,
      dataType: 'html',
      success: function (data) {
        $('#textFromFile').html(data);
      },
      error: function () {
        alert('Ошибка получения фвйла!');
      }
    });
  }
</script>


<style>
  #left {
    float: left;
    width: 40%;
    overflow: hidden;
  }

  #textFromFile {
    overflow: hidden;
    border: thin solid gray;
    min-height: 100%;
  }



</style>
</html>
