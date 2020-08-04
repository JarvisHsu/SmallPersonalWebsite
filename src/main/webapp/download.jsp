<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
<head>
    <title>Download</title>
</head>
<body>
<c:forEach var="me" items="${requestScope.fileNameMap}">
    <c:url value="downLoadServlet" var="downUrl" scope="page">
        <c:param name="filename" value="${me.key}"/>
    </c:url>
    ${me.value}<a href="${pageScope.downUrl}">下载</a>
    <br/>
</c:forEach>
</body>
</html>
