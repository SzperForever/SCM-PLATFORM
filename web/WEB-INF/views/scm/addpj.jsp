<%--
  Created by IntelliJ IDEA.
  User: szper
  Date: 2018/3/29
  Time: 22:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>addpj</title>
</head>
<body>
<script>
    $.ajax({

    })
</script>
<form action="/addLib.form" method="get">
    id:<input type="text" name="id"/>
    FileName:<input type="text" name="FileName"/>
    FilePath:<input type="text" name="FilePath"/>
    Typeid:<input type="text" name="Typeid"/>
    Userid:<input type="text" name="Userid"/>
    Status:<input type="text" name="Status"/>
    UploadTime:<input type="date" name="UploadTime"/>
    <input type="submit" name="submit" value="提交"/>

</form>
</body>
</html>
