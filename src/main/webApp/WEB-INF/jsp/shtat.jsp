<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 10.05.2022
  Time: 19:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>


<html>
<head>
    <title>Title</title>
</head>
<body>


<table  id="color_table">
    <tbody>
    <tr>
        <th class="tblsht">Структурное подразделение</th>
    </tr>
    <c:forEach var="division" items="${divisionList}">
    <tr>
        <td class="tblsht">
            <a id=${division.divisionId - 1}>${division.division}</a>
        </td>
    </tr
    </c:forEach>

        <label style="color: crimson; font: bold italic 110% serif">
            <c:if test="${param.get('errordivision') == true}">Введите название структурного подразделения!</c:if>
            <c:if test="${param.get('errorposition') == true}">Введите название должности!</c:if>
        </label>
    </tbody>
</table>

<button name="addDiv" id="mybtn" type="button" class="btn" onclick="insertInputText()">Добавить</button>
<button name="addDiv" id="mybtnDel" type="button" class="btncancel" onclick="deleteDivision()">Удалить</button>
<div id="ins"></div>

<br/>
<br/>
<table class="iksweb" id="myTable">
    <tbody >
    <tr>
        <th class="tblsht">Должности</th>
        <th class="tblsht">Работники</th>
    </tr>
    </tbody>
</table>
<div id="insbtn"></div>

<form id="addDivision" name="addDivision" method="post" action="/admin/division"></form>
<form id="addPosition" name="addPosition" method="post" ></form>



</body>

<script>

    highlight_Table_Rows("color_table", "hover_Row", "clicked_Row");
    let el = document.getElementById("color_table");
    el.addEventListener("click", getPositions);

    function getPositions(){
        let el = document.getElementsByClassName("clicked_Row").item(0).children.item(0).children.item(0);
        let id = el.id;

        $('.insert').remove();

        let elem = $('#myTable > tbody:last-child');

        $.ajax({
            url: "/admin/positions/" + id
       }).then(function(data) {
           console.log(data)
            if (data.myer === true){
                insertButton(id);
            } else {
                const data1 = data._embedded.positions;
                let userName;
                for (let i = 0; i < data1.length; i++) {
                    let position = data1[i].position;
                    if (data1[i].user == null) {
                        userName = "";
                    } else userName = data1[i].user.name;
                    elem.append(`
                            <tr class="insert">
                                <td class="tblsht">` + data1[i].position + `</a> </td>
                                <td class="tblsht">` + userName + `</td>
                            </tr>

                        `);
                }
            }
        });

        insertButton(id);

        }

        function insertButton(id){
        $('.mybtnPos').remove();
        let el = document.getElementById("insbtn");
        let but = document.createElement("button");
        but.setAttribute("type", "button");
        but.setAttribute("onclick", "insertInputTextForPositions(" + id + ")");
        but.setAttribute("class", "btn mybtnPos");
        but.innerText = "Добавить";
        el.append(but);
        }

    function deleteDivision(){
        let el = document.getElementsByClassName("clicked_Row").item(0).children.item(0).children.item(0);
        let id = Number(el.id) + 1;
        document.location.href = '/admin/division/' + id;
    }

    function insertInputText(){
        $('#mybtn').hide();
        $('#ins')
            .append('<input class="myinput rem" form="addDivision" name="division" placeholder="Введите подразделение"/>')
            .append('<button type="submit" id="sendButton" class="btn rem" form="addDivision">OK</button>')
            .append('<button type="button" class="btncancel rem" onclick=getShtat()>Отмена</button>');
        }

    function insertInputTextForPositions(id){
        const posId = Number(id) + 1;
        $('.mybtnPos').hide();
        $('#insbtnPos')
            .prepend('<input class="myinput remPos" form="addPosition" name="position" placeholder="Введите должность"/>')
            .append('<button type="submit" id="sendButtonPos" class="btn remPos" form="addPosition">OK</button>')
            .append('<button type="button" class="btncancel remPos" onclick=getShtatPos()>Отмена</button>');
        $('#addPosition').attr("action", "/admin/position/" + posId);

    }

    function getShtat() {
        $('.rem').remove();
        $('#mybtn').show();
    }

    function getShtatPos() {
        $('.remPos').remove();
        $('#mybtnPos').show();
    }

    function highlight_Table_Rows(table_Id, hover_Class, click_Class, multiple) {
        var table = document.getElementById(table_Id);
        if (typeof multiple == 'undefined') multiple = false;

        if (hover_Class) {
            var hover_Class_Reg = new RegExp("\\b"+hover_Class+"\\b");
            table.onmouseover = table.onmouseout = function(e) {
                if (!e) e = window.event;
                var elem = e.target || e.srcElement;
                while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
                    elem = elem.parentNode;

                if (elem.parentNode.tagName === 'TR' &&
                    elem.parentNode.parentNode.tagName === 'TBODY') {
                    var row = elem.parentNode;
                    if (!row.getAttribute('clicked_Row'))
                        row.className = e.type==="mouseover"?row.className +
                            " " + hover_Class:row.className.replace(hover_Class_Reg," ");
                }
            };
        }

        if (click_Class) table.onclick = function(e) {
            if (!e) e = window.event;
            var elem = e.target || e.srcElement;
            while (!elem.tagName || !elem.tagName.match(/td|th|table/i))
                elem = elem.parentNode;

            if (elem.parentNode.tagName === 'TR' &&
                elem.parentNode.parentNode.tagName === 'TBODY') {
                var click_Class_Reg = new RegExp("\\b"+click_Class+"\\b");
                var row = elem.parentNode;

                if (row.getAttribute('clicked_Row')) {
                    row.removeAttribute('clicked_Row');
                    row.className = row.className.replace(click_Class_Reg, "");
                    row.className += " "+hover_Class;
                }
                else {
                    if (hover_Class) row.className = row.className.replace(hover_Class_Reg, "");
                    row.className += " "+click_Class;
                    row.setAttribute('clicked_Row', true);

                    if (!multiple) {
                        var lastRowI = table.getAttribute("last_Clicked_Row");
                        if (lastRowI!==null && lastRowI!=='' && row.sectionRowIndex!==lastRowI) {
                            var lastRow = table.tBodies[0].rows[lastRowI];
                            lastRow.className = lastRow.className.replace(click_Class_Reg, "");
                            lastRow.removeAttribute('clicked_Row');
                        }
                    }
                    table.setAttribute("last_Clicked_Row", row.sectionRowIndex);
                }
            }
        };
    }





</script>
<style>
    <%@include file="myStyle.css"%>
    .hover_Row { background-color: #e0dfdf; }
    .clicked_Row { background-color: #b1b0b0; }
</style>
</html>
