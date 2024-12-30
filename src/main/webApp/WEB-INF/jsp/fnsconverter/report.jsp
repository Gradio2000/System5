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

  <title>UNO</title>
</head>
<body>

  <div class="main">
    <div id="printableArea">
    <table>
      <tbody>
      <tr>
        <th>Атрибут</th>
        <th>Значение</th>
      </tr>
      <c:forEach var="item" items="${resultMap}">
        <tr>
          <td style="padding: 2; text-align: left">${item.key}</td>
          <td style="padding: 2">${item.value}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
  <input type="button" onclick="printDiv('printableArea')" value="Печать" class="btn"/>
</div>


</body>

<script>
  function printDiv(divName) {
    const printContents = document.getElementById(divName).innerHTML;
    const originalContents = document.body.innerHTML;

    document.body.innerHTML = printContents;

    window.print();

    document.body.innerHTML = originalContents;
  }
</script>

</html>

