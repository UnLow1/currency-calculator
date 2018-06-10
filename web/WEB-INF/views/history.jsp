<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title><spring:message code="lbl.title"/></title>
</head>
<body>

<div class="container">
    <%@include file="header.jsp" %>

    <table class="table table-bordered table-hover">
        <thead class="bg-success">
        <tr>
            <th>Date</th>
            <th>Rate</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="day" items="${history}">
            <tr>
                <td>${day.key}</td>
                <td>${day.value}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="row">
        <div class="col-12">
            <a href="<c:url value="/" />" class="btn btn-lg btn-primary">Home page</a>
            <a href="<c:url value="list" />" class="btn btn-lg btn-primary">List of currencies</a>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</div>

</body>
</html>
