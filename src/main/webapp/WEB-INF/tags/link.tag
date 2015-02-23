<%@ tag language="java" pageEncoding="UTF-8" description="Page template"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ attribute name="target" required="true"%>
<%@ attribute name="page" required="true"%>
<%@ attribute name="nbPerPage" required="true"%>
<%@ attribute name="search" required="true"%>

<c:url value="${target}?page=${page}&nbPerPage=${nbPerPage}&search=${search}" />