<%@ include file="header.jsp" %>

	<section id="main">
	<div class="container">
		<div class="alert alert-danger">
			<spring:message code="403"/> <br />
			<!-- stacktrace -->
		</div>
	</div>
	</section>

	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>

</body>
</html>