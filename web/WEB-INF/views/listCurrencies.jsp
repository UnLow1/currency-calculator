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
            <th><spring:message code="lbl.name"/></th>
            <th><spring:message code="lbl.rate"/></th>
            <th><spring:message code="lbl.lastUpdate"/></th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
            <th>&nbsp;</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="currency" items="${currencies}">
            <c:url var="updateLink" value="/update">
                <c:param name="name" value="${currency.name}"/>
            </c:url>
            <c:url var="historyLink" value="/history">
                <c:param name="name" value="${currency.name}"/>
            </c:url>
            <c:url var="deleteLink" value="/delete">
                <c:param name="name" value="${currency.name}"/>
            </c:url>
            <tr>
                <td>${currency.name}</td>
                <td><fmt:formatNumber type="number" pattern="0.00000" value="${currency.rate}"/></td>
                <td>${currency.lastUpdate}</td>
                <td><a href="${updateLink}" class="btn btn-warning">Update</a></td>
                <td><a href="${historyLink}" class="btn btn-primary">Last month</a></td>
                <td><a href="${deleteLink}" class="btn btn-danger"
                       onclick="if(!(confirm('Are you sure to delete record?'))) return false">Delete</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <div class="row">
        <div class="col-12">
            <a href="<c:url value="/" />" class="btn btn-lg btn-primary">Home page</a>
            <a href="<c:url value="/new" />" class="btn btn-lg btn-primary">Add new currency</a>
        </div>
    </div>

    <%@include file="footer.jsp" %>
</div>

</body>
</html>
