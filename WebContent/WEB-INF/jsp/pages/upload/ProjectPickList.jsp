<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<div>
	Select Project
	<br/>
	<select id="projectPickList" name="projectPickList">
		<option value=""></option>
		<c:forEach items="${projectList}" var="project">
			<option value="${project.code}">${project.code}</option>
		</c:forEach>	
	</select>
	<br/>
</div>