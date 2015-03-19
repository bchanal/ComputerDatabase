
<%@ include file="./templates/header.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


	<section id="main">
		<div class="container">
			<div class="row">
				<div class="col-xs-8 col-xs-offset-2 box">
					<h1><spring:message code="add.title" /></h1>
					<form:form modelAttribute="computer" action="add-computer" method="POST">
						<fieldset>
							<div class="form-group">
								<label for="computerName"><spring:message code="tab.name" /></label> 
								<spring:message code="tab.name" var="name"></spring:message>	
								<form:input required='required' type="text" class="form-control"
									id="computerName" name="computerName" path="name" placeholder="${name }"></form:input>
									<form:errors path="name" cssClass="error"></form:errors>
							</div>
							<div class="form-group">
								<label for="introduced"><spring:message code="tab.introduced" /></label> 
								<spring:message code="tab.introduced" var="introduced"></spring:message>	
								<form:input type="datetime-local" class="form-control" id="introduced"
									name="introduced" path="introduced" placeholder="${introduced }"></form:input>
									<span class="errorintroduced"><spring:message code="errorFormatIntro" /></span>
									<form:errors path="introduced" cssClass="introduced"></form:errors>
							</div>
							<div class="form-group">
								<label for="discontinued"><spring:message code="tab.discontinued" /></label> 
								<spring:message code="tab.discontinued" var="discontinued"></spring:message>	
								<form:input type="datetime-local" class="form-control" id="discontinued"
									name="discontinued" path="discontinued" placeholder="${discontinued}"></form:input>
									<span class="errordiscontinued"><spring:message code="errorFormatDisc" /></span>
									<form:errors path="discontinued" cssClass="discontinued"></form:errors>
							</div>
							<div class="form-group">
								<label for="companyId"><spring:message code="tab.company" /></label> 
								<select	class="form-control" id="companyId" name="companyId">
									<option value="0">--</option>

									<c:forEach items="${ listCompanies }" var="company"
										varStatus="boucle">
										<option value="${company.id }">${ company.name }</option>
									</c:forEach>
								</select>
							</div>
						</fieldset>
						<div class="actions pull-right">
							<spring:message code="button.add" var="add"></spring:message>	
							<input type="submit" id="btnSubmit" value="${add}"	class="btn btn-primary"> 
							or 
							<a href="dashboard"	class="btn btn-default"><spring:message code="button.cancel" /></a>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<%@ include file="./templates/footer.jsp"%>

</body>
</html>