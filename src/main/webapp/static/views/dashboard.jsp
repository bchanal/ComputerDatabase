<%@ include file="header.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<a class="navbar-brand" href="dashboard"> Application <spring:message code="title" /></a>
		</div>
	</header>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${ page.nbTotalComputer} <spring:message code="nbComputers" /></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<spring:message code="search" var="search"></spring:message>
						<input type="search" id="searchbox" name="search" class="form-control" placeholder="${search}" /> 
						<spring:message code="button.filter" var="filter"></spring:message>	
						<input type="submit" id="searchsubmit" value="${filter}" class="btn btn-primary" />
					</form>
				</div>
				<div class="pull-right">
					<a class="btn btn-success" id="addComputer" href="add-computer"><spring:message code="button.add" /></a> 
					<a class="btn btn-default" id="editComputer" href="#" onclick="$.fn.toggleEditMode();"><spring:message code="button.edit" /></a>
				</div>
			</div>
		</div>

		<form id="deleteForm" action="delete-computer" method="POST">
			<input type="hidden" name="selection" value="">
		</form>

		<div class="container" style="margin-top: 10px;">
			<table class="table table-striped table-bordered">
				<thead>
					<tr>
						<!-- Variable declarations for passing labels as parameters -->
						<!-- Table header for Computer Name -->

						<th class="editMode" style="width: 60px; height: 22px;"><input
							type="checkbox" id="selectall" /> <span
							style="vertical-align: top;"> - <a href="#"
								id="deleteSelected" onclick="$.fn.deleteSelected();"> <i
									class="fa fa-trash-o fa-lg"></i>
							</a>
						</span></th>
						<th><spring:message code="tab.name" /></th>
						<th><spring:message code="tab.introduced" /></th>
						<!-- Table header for Discontinued Date -->
						<th><spring:message code="tab.discontinued" /></th>
						<!-- Table header for Company -->
						<th><spring:message code="tab.company" /></th>

					</tr>
				</thead>
				<!-- Browse attribute computers -->
				<tbody id="results">

					<c:forEach items="${ page.list }" var="computer" varStatus="boucle">
						<tr>
							<td class="editMode"><input type="checkbox" name="cb"
								class="cb" value=${ computer.id }></td>
							<td><a href="edit-computer?id=${ computer.id }" onclick="">${ computer.name }</a>
							</td>
							<td>${ computer.introduced}</td>
							<td>${ computer.discontinued }</td>
							<td>${ computer.company.name }</td>

						</tr>
					</c:forEach>


				</tbody>
			</table>
		</div>
	</section>

	<footer class="navbar-fixed-bottom">
		<div class="container text-center">
		
			<tag:pages page="${page}" numPage="${numPage}" search="${search}"></tag:pages>
			
		</div>

	</footer>
	<%@ include file="footer.jsp"%>
</body>
</html>

