
<%@ include file="header.jsp"%>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application -
				ComputerDatabase </a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${ idEdit }</div>
					<h1>Edit Computer</h1>

					<form action="editComputer" method="POST">
						<input type="hidden" value="0" />
						<fieldset>
							<div class="form-group">
								<label for="computerName">Computer name</label> <input required
									type="text" class="form-control" id="computerName"
									name="computerName" placeholder="Computer name"
									value=${ computer.name }>
								<div>
									<div class="form-group">
										<label for="introduced">Introduced date</label> <input
											type="datetime-local" class="form-control" id="introduced"
											name="introduced" placeholder="Introduced date"
											value=${ computer.dateIntro }>
									</div>
									<div class="form-group">
										<label for="discontinued">Discontinued date</label> <input
											type="datetime-local" class="form-control" id="discontinued"
											name="discontinued" placeholder="Discontinued date"
											value=${ computer.dateDiscontinued }>
									</div>
									<div class="form-group">
										<label for="companyId">Company</label> <select
											class="form-control" id="companyId" name="companyId">
											<option value=${ computer.manufacturer.id }>${ computer.manufacturer.name }</option>

											<c:forEach items="${ listCompanies }" var="company"
												varStatus="boucle">
												<option value="${company.id }">${ company.name }</option>
											</c:forEach>
										</select>
									</div>
						</fieldset>
						<div class="actions pull-right">
							<input type="submit" value="Edit" class="btn btn-primary">
							or <a href="dashboard" class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
</body>
</html>