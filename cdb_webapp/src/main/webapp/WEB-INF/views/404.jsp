<%@ include file="./templates/header.jsp"%>

	<section id="main">
		<div class="container">
			<div class="alert alert-danger">
				<spring:message code="404"/> <br />
			</div>

		</div>
	</section>

	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>

</body>
</html>