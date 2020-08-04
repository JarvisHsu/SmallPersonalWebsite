<%@ page import="tk.hellojarvis.db.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false"%>
<html>
<head>
    <title>Upload</title>
</head>
<%
    User user = (User) session.getAttribute("User");
    String userName = user.getUserName();
    pageContext.setAttribute("userName",userName);
%>
<body>

<form action="uploadHandleServlet" enctype="multipart/form-data" method="post">
    <label for="username"></label>
    <input type="text" id="username" name="username" placeholder="上传用户" value="${pageScope.userName}" autocomplete="off"><br/>
    <input type="file" name="file1" id="file1" placeholder="上传文件1"><br/>
    <input type="file" name="file2" id="file2" placeholder="上传文件2"><br/>
    <input type="submit" value="上传">
</form>
</body>
</html>
