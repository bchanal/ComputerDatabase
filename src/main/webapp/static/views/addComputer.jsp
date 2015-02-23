
<%@ include file="header.jsp"%>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application - Computer
				Database </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1>Add Computer</h1>
					<form action="add-computer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input
									required='required' type="text" class="form-control"
									id="computerName" name="computerName"
									placeholder="Computer name">
							</div>
							<div class="form-group">
								<label for="introduced">Introduced date</label> <input
									type="datetime-local" class="form-control" id="introduced"
									name="introduced" placeholder="Introduced date"> <span
									class="errorintroduced">Wrong format !</span>
							</div>
							<div class="form-group">
								<label for="discontinued">Discontinued date</label> <input
									type="datetime-local" class="form-control" id="discontinued"
									name="discontinued" placeholder="Discontinued date"> <span
									class="errordiscontinued">Wrong format, mwhahahaha !!</span>
							</div>
							<div class="form-group">
								<label for="companyId">Company</label> <select
									class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>

									<c:forEach items="${ listCompanies }" var="company"
										varStatus="boucle">
										<option value="${company.id }">${ company.name }</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" id="btnSubmit" value="Add"
								class="btn btn-primary"> or <a href="dashboard"
								class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>

</body>
</html>