<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>消息提示</title>
</head>
<body>
${requestScope.message}

<a href="listFileServlet">下载列表</a>
<a href="upload.jsp">上传列表</a>
</body>
</html>
