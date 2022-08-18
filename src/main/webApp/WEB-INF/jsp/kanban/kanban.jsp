<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 17.08.2022
  Time: 16:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"      prefix="c"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"       prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql"       prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml"       prefix="x"   %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"  %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"  %>
<script type="text/javascript" src="../../../js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="../../../js/sortable.js"></script>
<html>
<head>
    <jsp:include page="../../includes/header.jsp"/>
    <jsp:include page="../../includes/menu.jsp"/>
    <title>Канбан</title>
</head>
<body>
<div class="main">
    <div class="container">
        <div id="col1" class="column">
            <h1>Надо сделать</h1>
            <c:forEach items="${kanbanList}" var="kanban">
                <c:if test="${kanban.started == true}">
                    <div id="${kanban.id}" class="list-group-item started span-shadow" draggable="true">${kanban.kanbanName}</div>
                </c:if>
            </c:forEach>
        </div>
        <div id="col2" class="column">
            <h1>Уже делаем</h1>
            <c:forEach items="${kanbanList}" var="kanban">
                <c:if test="${kanban.continues == true}">
                    <div id="${kanban.id}" class="list-group-item continues span-shadow-yellow" draggable="true">${kanban.kanbanName}</div>
                </c:if>
            </c:forEach>
        </div>
        <div id="col3" class="column">
            <h1>Мы сделали</h1>
            <c:forEach items="${kanbanList}" var="kanban">
                <c:if test="${kanban.finished == true}">
                    <div id="${kanban.id}" class="list-group-item finished span-shadow-green" draggable="true">${kanban.kanbanName}</div>
                </c:if>
            </c:forEach>
        </div>
        <div style="width: 40%">
            <button class="collapsible">Новая задача</button>
            <div class="content">
                <div class="container-form">
                    <form:form action="/kanban/addNewCanban" method="post" modelAttribute="kanban" >
                        <label for="kanbanName">Название</label>
                        <input type="text" id="kanbanName" name="kanbanName" placeholder="Что делаем..">
                        <label for="describe">Описание</label>
                        <textarea id="describe" name="describe" placeholder="Напишите, что ожидаете.." style="height:200px"></textarea>
                        <input type="hidden" name="started" value="true"/>
                        <input type="hidden" name="continues" value="false"/>
                        <input type="hidden" name="finished" value="false"/>
                        <input type="hidden" name="user"/>

                        <button class="btn" type="submit">Сохранить</button>
                    </form:form>
                </div>
            </div>
        </div>

    </div>
</div>
</body>

<script>
      const columns = document.querySelectorAll('.column');
      columns.forEach((column) => {
        new Sortable(column, {
          group: "shared",
          animation: 150,
          ghostClass: "blue-background-class",

            onAdd: function (evt) {
              if(column.id === "col1"){
                  evt.item.className = "list-group-item started span-shadow";
              }
              else if(column.id === "col2"){
                  evt.item.className = "list-group-item continues span-shadow-yellow";
              }
              else if(column.id === "col3"){
                  evt.item.className = "list-group-item finished span-shadow-green";
              }


                $.ajax({
                    type: 'POST',
                    url: '/kanban/move',
                    data: { columnId: column.id, kanbanId: evt.item.id },
                    success: function (data) {
                        // запустится при успешном выполнении запроса и в data будет ответ скрипта
                    },
                    error: function () {
                        alert('Ошибка сохранения статуса задачи!');
                        console.log(evt);
                    }
                });
            }
        });
      });


      var coll = document.getElementsByClassName("collapsible");
      var i;

      for (i = 0; i < coll.length; i++) {
          coll[i].addEventListener("click", function() {
              this.classList.toggle("active");
              var content = this.nextElementSibling;
              if (content.style.maxHeight){
                  content.style.maxHeight = null;
              } else {
                  content.style.maxHeight = content.scrollHeight + "px";
              }
          });
      }
</script>
<style>
    <%@include file="../../includes/myStyle.css"%>


    input[type=text], select, textarea {
        width: 100%;
        padding: 12px;
        border: 1px solid #ccc;
        border-radius: 4px;
        box-sizing: border-box;
        margin-top: 6px;
        margin-bottom: 16px;
        resize: vertical;
    }

    input[type=submit] {
        background-color: #4CAF50;
        color: white;
        padding: 12px 20px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    input[type=submit]:hover {
        background-color: #45a049;
    }

    .container-form {
        border-radius: 5px;
        background-color: #f2f2f2;
        padding: 20px;
    }


    .span-shadow {
        background: #1fb5bf
    }
    .span-shadow:hover {
        box-shadow: 0 0 0 2px #1fb5bf inset, 0 0 0 4px white inset;
    }


    .span-shadow-yellow {
        background: #faff5b
    }
    .span-shadow-yellow:hover {
        box-shadow: 0 0 0 2px #faff5b inset, 0 0 0 4px white inset;
    }

    .span-shadow-green {
        background: #76ff80
    }
    .span-shadow-green:hover {
        box-shadow: 0 0 0 2px #76ff80 inset, 0 0 0 4px white inset;
    }

    .container {
        font-family: "Trebuchet MS", sans-serif;
        display: flex;
        gap: 10px;
        padding: 5px;
  }
  .column {
    flex-basis: 20%;
    background: #f1f1f1;
    min-height: 90vh;
    /*padding: 5px;*/
    border-radius: 10px;
  }
  .column h1 {
    text-align: center;
    font-size: 22px;
  }
  .list-group-item {
    background: #fff;
    margin: 5px;
    padding: 20px;
    border-radius: 5px;
    cursor: move;
  }

  .started{
      background: #fff;
  }
  .continues{
      background: #faff5b;
  }
  .finished{
      background: #76ff80;
  }


    .collapsible {
        background-color: #939393;
        color: white;
        cursor: pointer;
        padding: 18px;
        width: 100%;
        border: none;
        text-align: left;
        outline: none;
        font-size: 15px;
        border-radius: 10px;
    }

    .active, .collapsible:hover {
        background-color: #737373;
    }

    .collapsible:after {
        content: '\002B';
        color: white;
        font-weight: bold;
        float: right;
        margin-left: 5px;
    }

    .active:after {
        content: "\2212";
    }

    .content {
        padding: 0 18px;
        max-height: 0;
        overflow: hidden;
        transition: max-height 0.2s ease-out;
        background-color: #f1f1f1;
    }
</style>
</html>
