
<%@ include file="header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>


	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${ idEdit }</div>
					<h1><spring:message code="edit.title" /></h1>

					<form action="edit-computer" method="POST">
						<input type="hidden" value="0" />
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="tab.name" /></label> 
								<spring:message code="tab.name" var="name"></spring:message>
								<input required	type="text" class="form-control" id="computerName"
									name="computerName" placeholder="${name }"	value="${ computer.name }">
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="tab.introduced" /></label> 
								<spring:message code="tab.introduced" var="introduced"></spring:message>
								<input type="datetime-local" class="form-control" id="introduced"
									name="introduced" placeholder="${introduced }"	value=${ computer.introduced }> 
								<span class="errorintroduced"><spring:message code="errorFormat" /></span>

							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="tab.discontinued" /></label> 
								<spring:message code="tab.discontinued" var="discontinued"></spring:message>
								<input	type="datetime-local" class="form-control" id="discontinued"
									name="discontinued" placeholder="${discontinued }"	value=${ computer.discontinued }>
								<span class="errordiscontinued"><spring:message code="errorFormat" /></span>

							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="tab.company" /></label> 
								<select	class="form-control" id="companyId" name="companyId">
									<option value=${ computer.company.id }>${ computer.company.name }</option>
									<c:forEach items="${ listCompanies }" var="company"	varStatus="boucle">
										<option value="${ company.id }">${ company.name }</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<input id="btnSubmit" type="submit" value="Edit" class="btn btn-primary">
							 or 
							 <a href="dashboard"	class="btn btn-default">Cancel</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="footer.jsp"%>
</body>
</html>