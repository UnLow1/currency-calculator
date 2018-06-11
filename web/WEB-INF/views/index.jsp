<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="lbl.title"/>r</title>
</head>
<body>
<div class="container">
    <%@ include file="header.jsp" %>

    <div class="row">
        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
            <div class="jumbotron">
                <h1 class="display-4">Welcome to currency calculator</h1>
                <p class="lead">
                    In this application you can calculate one currency for another
                </p>
                <a href="calculate" class="btn btn-lg btn-success">Calculator</a>
                <a href="list" class="btn btn-lg btn-success">List of currencies</a>
                <a href="new" class="btn btn-lg btn-success">Add a new currency</a>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
            <div class="card">
                <img src="<spring:url value="/resource/images/calculator.jpg"/>" class="card-img-top"/>
            </div>
        </div>

        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
            <div class="card">
                <img src="<spring:url value="/resource/images/dollars.jpg"/>" class="card-img-top"/>
            </div>
        </div>

        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
            <div class="card">
                <img src="<spring:url value="/resource/images/money.jpg"/>" class="card-img-top"/>
            </div>
        </div>

        <div class="col-xl-3 col-lg-3 col-md-3 col-sm-6 col-12">
            <div class="card">
                <img src="<spring:url value="/resource/images/chart.jpg"/>" class="card-img-top"/>
            </div>
        </div>
    </div>
    <%@ include file="footer.jsp" %>
</div>
</body>
</html>
