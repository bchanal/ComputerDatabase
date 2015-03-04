<%@ include file="./templates/header.jsp"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

	<section id="main">
		<div class="container">
			<h1 id="homeTitle">${ page.nbTotalComputer} <spring:message code="nbComputers" /></h1>
			<div id="actions" class="form-horizontal">
				<div class="pull-left">
					<form id="searchForm" action="#" method="GET" class="form-inline">
						<spring:message code="search" var="searchfield"></spring:message>
						<input type="search" id="searchbox" name="search" class="form-control" placeholder="${searchfield}" /> 
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
						
						<th><a class="navbar-brand" href="<tag:link target="dashboard" page="${numPage}" nbPerPage="${page.nbPerPage}" search="${search}"  order="name" />"><spring:message code="tab.name" /></a></th>
						<th><a class="navbar-brand" href="<tag:link target="dashboard" page="${numPage}" nbPerPage="${page.nbPerPage}" search="${search}"  order="intro" />"><spring:message code="tab.introduced" /></a></th>
						<!-- Table header for Discontinued Date -->
						<th><a class="navbar-brand" href="<tag:link target="dashboard" page="${numPage}" nbPerPage="${page.nbPerPage}" search="${search}"  order="disc" />"><spring:message code="tab.discontinued" /></a></th>
						<!-- Table header for Company -->
						<th><a class="navbar-brand" href="<tag:link target="dashboard" page="${numPage}" nbPerPage="${page.nbPerPage}" search="${search}"  order="company" />"><spring:message code="tab.company" /></a></th>

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
			<tag:pages page="${page}" numPage="${numPage}" search="${search}" order="${order}"></tag:pages>	
		</div>


<script type="text/javascript">
	var strings = new Array();
	strings['suredelete'] = "<spring:message code='suredelete' javaScriptEscape='true' />";
	strings['view_button'] = "<spring:message code='button.view' javaScriptEscape='true' />";
	strings['edit_button'] = "<spring:message code='button.edit' javaScriptEscape='true' />";
</script>
	</footer>
	<%@ include file="./templates/footer.jsp"%>
</body>
</html>

