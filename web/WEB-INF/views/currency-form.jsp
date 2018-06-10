<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title><spring:message code="lbl.title"/></title>
</head>
<body>
<div class="container">
    <%@include file="header.jsp" %>

    <div class="row">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
            <h2 class="page-header">Please type currency (example: PLN, USD, EUR)</h2>
        </div>
    </div>

    <div class="row">
        <div class="col-6">
            <form:form action="saveCurrency" modelAttribute="currency" cssClass="form-horizontal" role="form">

                <div class="form-group row">
                    <Label for="name" class="col-6 col-form-label">Currency name: </Label>
                    <div class="col-6">
                        <form:input path="name" cssClass="form-control"/>
                        <form:errors path="name" cssClass="alert-danger"/>
                        <b><font color="red">${message}</font></b>
                    </div>
                </div>

                <div class="form-group row">
                    <div class="offset-6 col-6">
                        <input type="submit" value="Add currency" name="btnSubmit" class="btn btn-primary"/>
                    </div>
                </div>
            </form:form>
        </div>
    </div>

    <a href="<c:url value="/" />" class="btn btn-lg btn-primary">Home page</a>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>
