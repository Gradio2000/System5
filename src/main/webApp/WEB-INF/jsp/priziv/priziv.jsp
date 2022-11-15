<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 15.11.2022
  Time: 12:25
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

  <title>Призыв</title>
</head>
<body>
<div class="main">
  <table>
    <tr>
      <th>№п/п</th>
      <th>Команда</th>
      <th>Дата прибытия</th>
      <th>Количество человек</th>
      <th>Паспорта получены</th>
      <th>Информация введена в САБС</th>
      <th>Выдано, количество</th>
      <th>Оформлено, но не выдано, количество</th>
      <th>Дата убытия</th>
    </tr>
    <c:forEach var="priziv" items="${prizivList}" varStatus="count">
      <form id="prizivForm${priziv.prizivId}">
        <input type="hidden" name="prizivId" value="${priziv.prizivId}">
        <input type="hidden" name="commandName" value="${priziv.commandName}">
        <input type="hidden" name="dateArrival" value="${priziv.dateArrival}">
        <input type="hidden" name="dateDeparture" value="${priziv.dateDeparture}">
        <input type="hidden" name="peopleAmmount" value="${priziv.peopleAmmount}">
        <tr>
          <td>${count.count}</td>
          <td>${priziv.commandName}</td>
          <td><fmt:formatDate value="${priziv.dateArrival}" pattern="dd.MM.yyyy"/></td>
          <td>${priziv.peopleAmmount}</td>
          <td>
            <c:if test="${priziv.getPassports}">
              <input type="checkbox" name="getPassports" checked onchange="editPriziv(${priziv.prizivId})">
            </c:if>
            <c:if test="${!priziv.getPassports}">
              <input type="checkbox" name="getPassports" onchange="editPriziv(${priziv.prizivId})">
            </c:if>
          </td>
          <td>
            <c:if test="${priziv.processed}">
              <input type="checkbox" name="processed" checked onchange="editPriziv(${priziv.prizivId})">
            </c:if>
            <c:if test="${!priziv.processed}">
              <input type="checkbox" name="processed" onchange="editPriziv(${priziv.prizivId})">
            </c:if>
          </td>
          <td><input type="number" class="myinput" name="issued" value="${priziv.issued}" onchange="editPriziv(${priziv.prizivId})"></td>
          <td><input type="number" class="myinput" name="preparedAndNotIssued" value="${priziv.preparedAndNotIssued}" onchange="editPriziv(${priziv.prizivId})"></td>
          <td><fmt:formatDate value="${priziv.dateDeparture}" pattern="dd.MM.yyyy"/></td>
        </tr>
      </form>
    </c:forEach>
  </table>
</div>
</body>
</html>
<script>
  function editPriziv(id){
    const msg = document.getElementById("prizivForm" + id);
    console.log(msg);
    let d = $(msg).serializeArray();
    $.ajax({
      type: 'POST',
      url: '/priziv/change',
      data: d,
      success: function (data) {
      },
      error: function () {
        alert('Ошибка изменения записи! Обратитесь к администратору! function editPriziv(id)');
        console.log(d);
      }
    });
  }
</script>

