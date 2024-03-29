<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.03.2024
  Time: 20:33
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

  <title>Бизнесс процессы</title>
</head>
<body>


  <div class="main">
    <table id="color_table" style="width: 100%; table-layout: auto" >
      <tbody>
      <tr>
        <th colspan="3" class="tblsht">Участки работы (бизнес-процессы)</th>
      </tr>
      <tr>
        <th>Удалить</th>
        <th>Список документов</th>
        <th>Название группы</th>
      </tr>
      <c:forEach var="businessDto" items="${businessDtoList}">
        <form id="editGroup${businessDto.dtoBusinessId}">
          <input  type="hidden" name="businessId" value="${businessDto.dtoBusinessId}">
          <tr>
            <td style="width: 10%;">
              <input form="del" value="${businessDto.dtoBusinessId}" type="checkbox" name="check"/>
            </td>
            <td class="tblsht" style="width: 10%;">
              <a href="getBusinessById/${businessDto.dtoBusinessId}">Список документов</a>
            </td>
            <td>
              <input type="text" class="myinput" name="businessName" value="${businessDto.dtoBusinessName}" onchange="changeGroupName(${businessDto.dtoBusinessId})" style="margin-top: 0; padding: 0"/>
            </td>
          </tr>
        </form>
      </c:forEach>
      <div>
        <a style="color: crimson; font: bold italic 110% serif">
          <c:if test="${param.get('error') == 100}">Не выбрана группа документов!</c:if>
          <c:if test="${param.get('error') == 110}">Группа содержит документы. Удаление невозможно!</c:if>
          <c:if test="${param.get('error') == 200}">Введите название участка работы (бизнес-процесса)!</c:if>
        </a>
      </div>

      </tbody>
    </table>

    <form id="add" action="/docs/addbusiness" method="post">
      <input name="businessName" class="myinput" type="text" placeholder="Введите название бизнес-процесса"/>
    </form>
    <form id="del" action="/docs/deleteBusiness" method="post"></form>
    <button form="add" name="addDiv" id="mybtn" type="submit" class="btn">Добавить</button>
    <button form="del" name="delete" type="submit" class="btncancel">Удалить</button>
  </div>


</body>

<script>
  function changeGroupName(id){
    const msg = document.getElementById("editGroup" + id);
    let d = $(msg).serializeArray();


    $.ajax({
      type: 'POST',
      url: '/docs/changeBusinessName',
      data: d,
      success: function (data) {
      },
      error: function () {
        alert('Ошибка изменения теста! Обратитесь к администратору!');
      }
    });
  }
</script>

</html>

