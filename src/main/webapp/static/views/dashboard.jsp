<%@ include file="header.jsp" %>

<body>
	<header class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<a class="navbar-brand" href="dashboard?page=${page}&search=${search}&nbPerPage=${nbPerPage}"> Application - Computer Database </a>
	</div>
	</header>

	<section id="main">
	<div class="container">
		<h1 id="homeTitle">${ nbTotalComputer} Computers found</h1>
		<div id="actions" class="form-horizontal">
			<div class="pull-left">
				<form id="searchForm" action="#" method="GET" class="form-inline">

					<input type="search" id="searchbox" name="search"
						class="form-control" placeholder="Search name" /> <input
						type="submit" id="searchsubmit" value="Filter by name"
						class="btn btn-primary" />
				</form>
			</div>
			<div class="pull-right">
				<a class="btn btn-success" id="addComputer" href="addComputer">Add
					Computer</a> 
				<a class="btn btn-default" id="editComputer" href="#"
					onclick="$.fn.toggleEditMode();">Edit</a>
			</div>
		</div>
	</div>

	<form id="deleteForm" action="#" method="POST">
		<input type="hidden" name="selection" value="">
	</form>

	<div class="container" style="margin-top: 10px;">
		<table class="table table-striped table-bordered">
			<thead>
				<tr>
					<!-- Variable declarations for passing labels as parameters -->
					<!-- Table header for Computer Name -->

					<th class="editMode" style="width: 60px; height: 22px;">
						<input type="checkbox" id="selectall" /> 
						<span style="vertical-align: top;">
					 		- 
					 		<a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();"> 
					 			<i	class="fa fa-trash-o fa-lg"></i>
							</a>
						</span>
					</th>
					<th>Computer name</th>
					<th>Introduced date</th>
					<!-- Table header for Discontinued Date -->
					<th>Discontinued date</th>
					<!-- Table header for Company -->
					<th>Company</th>

				</tr>
			</thead>
			<!-- Browse attribute computers -->
			<tbody id="results">

				<c:forEach items="${ listComputers }" var="computer" varStatus="boucle">
					<tr>
						<td class="editMode"><input type="checkbox" name="cb"
							class="cb" value= ${ computer.id }></td>
						<td><a href="editComputer?id=${ computer.id }" onclick="">${ computer.name }</a>
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
		<ul class="pagination">
			<li><a href="dashboard?page=-&search=${search}" aria-label="Previous"> 
			<span aria-hidden="true">&laquo;</span>
			</a></li>
			<li><a href="dashboard?page=1&search=${search}&nbPerPage=${nbPerPage}">1</a></li>
			<li><a href="dashboard?page=2&search=${search}&nbPerPage=${nbPerPage}">2</a></li>
			<li><a href="dashboard?page=3&search=${search}&nbPerPage=${nbPerPage}">3</a></li>
			<li><a href="dashboard?page=4&search=${search}&nbPerPage=${nbPerPage}">4</a></li>
			<li><a href="dashboard?page=5&search=${search}&nbPerPage=${nbPerPage}">5</a></li>
			<li><a href="dashboard?page=+&search=${search}&nbPerPage=${nbPerPage}" aria-label="Next"> <span aria-hidden="true">&raquo;</span>
			</a></li>
		</ul>

		<div class="btn-group btn-group-sm pull-right" role="group">
			<a type="button" class="btn btn-default" href="dashboard?page=1&nbPerPage=10&search=${search}" >10</a>
			<a type="button" class="btn btn-default" href="dashboard?page=1&nbPerPage=50&search=${search}" >50</a>
			<a type="button" class="btn btn-default" href="dashboard?page=1&nbPerPage=100&search=${search}" >100</a>
		</div>
	</div>
	
	</footer>
	<script src="<c:url value="/static/js/jquery.min.js"/>"></script>
	<script src="<c:url value="/static/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/static/js/dashboard.js"/>"></script>
</body>
</html>