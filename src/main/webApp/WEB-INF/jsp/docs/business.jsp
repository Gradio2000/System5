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
<%@ taglib prefix="enctype" uri="http://www.springframework.org/tags/form" %>
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

    <div id="wrapper" style="margin-right: 5px">
      <div id="left">
        <p class="heading">${business.businessName}</p>
        <table id="color_table" style="margin-top: 50px">
          <c:forEach var="docs" items="${business.docsList}">
            <tr>
              <td class="tblsht">
                <a onclick="getTextFromFile(${docs.docId})">${docs.docName}</a>
            </tr>
          </c:forEach>
        </table>
      </div>

      <div class="sticky" style="margin-top: 10px">
        <input form="loadFile" type="file" id="mybtn" class="btn" name="file" accept=".docx" onchange="upLoadFile(${business.businessId})"/>
        <label class="btn" for="mybtn" style="margin-top: 2px; height: auto">Загрузить</label>
      </div>

      <div id="textFromFile"  ></div>
    </div>




    <form id="loadFile" enctype="multipart/form-data">
      <input type="hidden" value="${business.businessId}" name="businessId"/>
    </form>


  </div>
</body>
<script>

  function upLoadFile(businessId){
    var $input = $("#mybtn");
    var fd = new FormData;

    fd.append('file', $input.prop('files')[0]);
    fd.append('businessId', businessId);

    console.log($input);

    $.ajax({
      url: '/docs/fileUpload',
      data: fd,
      processData: false,
      contentType: false,
      type: 'POST',
      success: function (data) {
        $('#textFromFile').html(data);
      },
      error: function () {
        alert('Ошибка получения файла!');
      }
    });
  }

  function getTextFromFile(id){
    $.ajax({
      type: 'GET',
      url: '/docs/getDoc/' + id,
      dataType: 'html',
      success: function (data) {
        $('#textFromFile').html(data);
      },
      error: function () {
        alert('Ошибка получения файла!');
      }
    });
  }
</script>


<style>
  #left {
    height: 200px;
    width: 100%;
    overflow: scroll;
  }

  #textFromFile {
    overflow: scroll;
    /*border: thin solid gray;*/
    height: 50vw;
    margin-top: 10px;
    resize: both;
  }

  #mybtn {
    opacity: 0;
    position: absolute;
    z-index: -1;
  }

  .heading{
      padding-top: 12px;
      padding-bottom: 12px;
      background-color: #1fb5bf;
      color: white;
      padding-left: 4px;
      margin: auto;
      position: fixed;
      width: 100%;
  }

  .sticky {
    background-color: white;
    position: -webkit-sticky;
    position: sticky;
    top: 110px;
  }
</style>
</html>
