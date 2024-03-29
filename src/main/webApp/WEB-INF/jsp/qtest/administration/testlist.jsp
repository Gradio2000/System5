<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 14.06.2022
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<script type="text/javascript" src="../../../../js/jquery-3.6.0.js"></script>
<html>
<style>
    <%@include file="../../../includes/myStyle.css"%>

</style>
<head>
    <jsp:include page="../../../includes/header.jsp"/>
    <jsp:include page="../../../includes/menu.jsp"/>

    <title>Список тестов</title>
</head>
<body>
<div class="main">
    <table id="color_table" style="width: 100%; table-layout: auto" >
        <tbody>
            <tr>
                <th colspan="4" class="tblsht">Список тестов: ${groupTestName}</th>
            </tr>
            <tr>
                <th>Удалить</th>
                <th>Посмотреть вопросы</th>
                <th>Название теста</th>
                <th>Количество вопросов в тесте</th>
            </tr>
            <c:forEach var="test" items="${testList}">
                <c:if test="${test.deleted}">
                    <tr>
                        <td style="width: 10%;">
                            <form id="undelForm${test.testId}" action="/tests/undelete" method="post">
                                <input type="hidden" name="testGroupId" value="${groupTestId}">
                                <input type="hidden" name="testId" value="${test.testId}">
                                <a href=# onclick="document.getElementById('undelForm${test.testId}').submit()">Разархивировать</a>
                            </form>
                        </td>
                        <td style="width: 10%; color: #b1b0b0">
                            <a>Вопросы теста</a>
                        </td>
                        <td class="tblsht">
                            <a style="color: #b1b0b0">${test.testName}</a>
                        </td>
                        <td></td>
                        <td></td>
                    </tr>
                </c:if>
                <c:if test="${!test.deleted}">
                    <form id="safetest${test.testId}">
                        <input type="hidden" name="testId" value="${test.testId}">
                        <input type="hidden" name="testId" value="${groupTestId}">
                        <tr>
                            <td style="width: 10%;">
                                <input form="del" value="${test.testId}" type="checkbox" name="check"/>
                            </td>
                            <td style="width: 10%;">
                                <a href="/tests/${test.testId}/questions">Вопросы теста</a>
                            </td>
                            <td class="tblsht">
                                <input type="text" class="myinput" name="testName" value="${test.testName}" onchange="saveTest(${test.testId})" style="margin-top: 0; padding: 0">
                            </td>
                            <td>
                                <a>${test.quesAmount}</a>
                            </td>
                        </tr>
                    </form>
                </c:if>
                

            </c:forEach>
            <div>
                <a style="color: crimson; font: bold italic 110% serif">
                    <c:if test="${param.get('error') == 100}">Не выбран тест!</c:if>
                    <c:if test="${param.get('error') == 200}">Введите название теста!</c:if>
                </a>
            </div>
        </tbody>
    </table>

    <form id="add" action="/tests/add" method="post">
        <input name="groupId" type="hidden" value="${groupTestId}"/>
        <input name="testName" class="myinput" type="text" placeholder="Введите название теста"/>
    </form>

    <form id="del" action="/tests/delete" method="post" onsubmit="alert('Внимание! Тесты, которые не использовались для тестирования ' +
     'будут удалены. Тесты, которые использовались для тестирования будут заархивированы!')">
        <input type="hidden" name="testGroupId" value="${groupTestId}">
    </form>

    <button form="add" name="addDiv" id="mybtn" type="submit" class="btn">Добавить</button>
    <button form="del" name="delete" type="submit" class="btncancel">Удалить</button>
</div>
</body>
<script>
    function changeData(id){
      let el =  document.getElementById('btnch' + id);
      $(el).show();
    }

    function saveTest(id){
        const msg = document.getElementById("safetest" + id);
        let d = $(msg).serializeArray();
        $.ajax({
            type: 'POST',
            url: '/tests/change/',
            data: d,
            success: function (data) {
            },
            error: function () {
                alert('Ошибка изменения теста! Обратитесь к администратору!');
                console.log(d);
            }
        });
    }
</script>
</html>

