<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Computer Database</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Bootstrap -->
<link href="<c:url value="/static/css/bootstrap.min.css"/>"	rel="stylesheet" media="screen">
<link href="<c:url value="/static/css/font-awesome.css"/>"	rel="stylesheet" media="screen">
<link href="<c:url value="/static/css/main.css"/>" rel="stylesheet"	media="screen">
</head>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application <spring:message code="title" /></a>
			<a class='btn btn-primary' type=button href="?lang=en"><spring:message code="english" /></a>
			<a class='btn btn-primary' type=button href="?lang=fr"><spring:message code="french" /></a>

		</div>
	</header>

