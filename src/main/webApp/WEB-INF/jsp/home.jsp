<%--
  Created by IntelliJ IDEA.
  User: aleksejlaskin
  Date: 23.04.2022
  Time: 23:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="/list">Посмотреть все оценки</a>
    <br/>
    <a href="/getUser">Сведения о пользователе</a>
    <br/>
    <a href="/admin/shtat">Получить штатное расписание</a>
    <br/>
    <button type="button" onclick="get_employers()">Посмотреть подчиненных сотрудников</button>
    <br/>
    <p class="zzz"></p>
    <br/>
    <a href="/logout">Выйти</a>
</body>

<script>
   async function get_employers()
    {
        $.ajax({
            url: "/my_employers"
        }).then(function(data) {
            const data1 = data._embedded.positions;
            console.log(data1);
            for (let i = 0; i < data1.length; i++){
                let position = data1[i].position;
                $(".zzz").append(data1[i].position + ` ` + data1[i].user.name + `<br/>`);
            }
        });
    };

</script>
</html>
