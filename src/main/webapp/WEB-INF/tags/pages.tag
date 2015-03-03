<%@ tag language="java" pageEncoding="UTF-8" description="Page template"%>
<%@ attribute name="page" required="true" type="com.excilys.cdb.model.Page"%>
<%@ attribute name="search" required="true"%>
<%@ attribute name="numPage" required="true"%>
<%@ attribute name="order" required="true"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="tag"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul class="pagination">
	<li <c:if test="${numPage == 1}">style="display:none;"</c:if>>
		<a	href="<tag:link target="dashboard" page="${numPage-1}" nbPerPage="${page.nbPerPage}" search="${search}" order="${order}" />"	aria-label="Previous"> 
			<span aria-hidden="true">&laquo;</span>
		</a>
	</li>
	
<%-- 	<c:forEach begin="1" end="${page.nbTotalPages}" var="numPage"> --%>
		<c:forEach begin="${page.range[0] }" end="${page.range[1]}" var="index">
	
 		<li <c:if test="${numPage == index}">class="active"</c:if> >
			<a href="<tag:link target="dashboard" page="${index}" nbPerPage="${page.nbPerPage}" search="${search}" order="${order}" />">${index}</a>
		</li>
	</c:forEach>
	
	<li  <c:if test="${numPage == page.nbTotalPages}">style="display:none;"</c:if> >
		<a href="<tag:link target="dashboard" page="${numPage+1}" nbPerPage="${page.nbPerPage}" search="${search}"  order="${order}" />" aria-label="Next"> 
			<span aria-hidden="true">&raquo;</span>
		</a>
	</li>
</ul>
<div class="btn-group btn-group-sm pull-right" role="group">
	<a type="button" href="<tag:link target="dashboard" page="1" nbPerPage="10" search="${search}" order="${order}" />" ${page.nbPerPage == 10 ? 'class="btn btn-primary"' : 'class="btn btn-default"'}>10</a>
	<a type="button" href="<tag:link target="dashboard" page="1" nbPerPage="50" search="${search}" order="${order}" />" ${page.nbPerPage == 50 ? 'class="btn btn-primary"' : 'class="btn btn-default"'}>50</a>
	<a type="button" href="<tag:link target="dashboard" page="1" nbPerPage="100" search="${search}" order="${order}" />" ${page.nbPerPage == 100 ? 'class="btn btn-primary"' : 'class="btn btn-default"'}>100</a>
</div>

