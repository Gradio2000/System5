<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 19.05.2022
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<head>
    <title>Отчет</title>
</head>
<body>
<jsp:include page="../../includes/header.jsp"/>
<jsp:include page="../../includes/menu.jsp"/>

    <div class="main" >
        <div id="printableArea" style="text-align: center">
            <p>Форма оценки работников за ${month} ${year} года</p>
            <div>
                <table class="table">
                    <tbody>
                        <tr>
                            <th rowspan="2">ФИО</th>
                            <th rowspan="2">Должность</th>
                            <th colspan="5">Оценки по критериям</th>
                            <th rowspan="2">Итоговая оценка</th>
                        </tr>
                        <tr>
                            <th>Личная результативность</th>
                            <th>Инициативность</th>
                            <th>Совершенствование профессиональных знаний</th>
                            <th>Клиенто ориентированность</th>
                            <th>Работа в команде</th>
                        </tr>
                        <c:forEach var="system5" items="${system5List}">
                            <tr>
                                <td>${system5.user.name}</td>
                                <td>${system5.user.position.position}</td>
                                <td>${system5.system5empl.resempl1}</td>
                                <td>${system5.system5empl.resempl2}</td>
                                <td>${system5.system5empl.resempl3}</td>
                                <td>${system5.system5empl.resempl4}</td>
                                <td>${system5.system5empl.resempl5}</td>
                                <td>${system5.totalMark5.totalMarkEmpl}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <br/>
            </div>
        </div>
        <div>
            <button type="button" class="btn" onclick="document.location='/admin/prepareReport'">Назад</button>
            <input type="button" onclick="printDiv('printableArea')" value="Печать" class="btn"/>
        </div>
    </div>

</body>
</html>
<style>
    <%@include file="../../includes/myStyle.css"%>
</style>
<script>
    function printDiv(divName) {
        var printContents = document.getElementById(divName).innerHTML;
        var originalContents = document.body.innerHTML;

        document.body.innerHTML = printContents;

        window.print();

        document.body.innerHTML = originalContents;
    }
</script>