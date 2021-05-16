<%--
  Created by IntelliJ IDEA.
  User: 박수민
  Date: 2021-05-16
  Time: 오후 1:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<html>
<head>
    <title>회원 정보 전달 view</title>
</head>
<body>
    id = <%= request.getAttribute("id") %><br>
    name = <%= request.getAttribute("name") %><br>
    pw = <%= request.getAttribute("pw") %><br>
    birthday = <%= request.getAttribute("birthday") %><br>
    age = <%= request.getAttribute("age") %><br>
</body>
</html>
