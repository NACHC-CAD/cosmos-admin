<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>
	<table class="yaormaDataTable">
		<tr>
			<th><nobr>Project Name</nobr></th>
			<th>Project Description</th>
			<th><nobr>View Project Details</nobr></th>
		</tr>
		<c:forEach items="${projectList}" var="project">
			<tr>
				<td>${project.name}</td>
				<td>${project.description}</td>
				<td><a href="${home}/ProjectOverview?guid=${project.guid}"><nobr>View Project Details</nobr></a></td>
			</tr>
		</c:forEach>
	</table>
</div>
