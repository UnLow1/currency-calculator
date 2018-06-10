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
        <div class="col-6">
            <form:form action="calculateRates" modelAttribute="exchange" cssClass="form-horizontal" role="form">

                <div class="form-group row">
                    <Label for="amount" class="col-6 col-form-label">Amount: </Label>
                    <div class="col-6">
                        <form:input path="amount" cssClass="form-control"/>
                        <form:errors path="amount" cssClass="alert-danger"/>
                    </div>
                </div>

                <div class="form-group row">
                    <Label for="from" class="col-6 col-form-label">From currency: </Label>
                    <div class="col-6">
                        <form:input path="from" cssClass="form-control"/>
                        <form:errors path="from" cssClass="alert-danger"/>
                    </div>
                </div>

                <div class="form-group row">
                    <Label for="to" class="col-6 col-form-label">To currency: </Label>
                    <div class="col-6">
                        <form:input path="to" cssClass="form-control"/>
                        <form:errors path="to" cssClass="alert-danger"/>
                    </div>
                </div>

                <b><font color="red">${message}</font></b>

                <div class="form-group row">
                    <div class="offset-6 col-6">
                        <input type="submit" value="Count" name="btnSubmit" class="btn btn-primary"/>
                    </div>
                </div>
            </form:form>
            <h2>${result}</h2>
        </div>
    </div>

    <a href="<c:url value="/" />" class="btn btn-lg btn-primary">Home page</a>

    <%@include file="footer.jsp" %>
</div>
</body>
</html>
