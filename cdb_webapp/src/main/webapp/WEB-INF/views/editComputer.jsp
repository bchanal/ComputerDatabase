
<%@ include file="./templates/header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<div class="label label-default pull-right">id: ${ computer.id }</div>
					<h1><spring:message code="edit.title" /></h1>

					<form:form modelAttribute="computer" action="edit-computer" method="POST">
						<input type="hidden" value="0" />
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="tab.name" /></label> 
								<spring:message code="tab.name" var="name"></spring:message>
								<form:input type="text" class="form-control" id="computerName"
									name="computerName" placeholder="${name }"	path="name" value="${ computer.name }"></form:input>
									<form:errors path="name" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="tab.introduced" /></label> 
								<spring:message code="tab.introduced" var="introduced"></spring:message>
								<form:input type="datetime-local" class="form-control" id="introduced"
									name="introduced" placeholder="${introduced }"	path="introduced" value="${ computer.introduced }"></form:input> 
								<span class="errorintroduced"><spring:message code="errorFormatIntro" /></span>
								<form:errors path="introduced" cssClass="error"></form:errors>

							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="tab.discontinued" /></label> 
								<spring:message code="tab.discontinued" var="discontinued"></spring:message>
								<form:input	type="datetime-local" class="form-control" id="discontinued"
									name="discontinued" placeholder="${discontinued }"	path="discontinued" value="${ computer.discontinued }"></form:input>
								<span class="errordiscontinued"><spring:message code="errorFormatDisc" /></span>
								<form:errors path="discontinued" cssClass="error"></form:errors>

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
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="./templates/footer.jsp"%>
</body>
</html>