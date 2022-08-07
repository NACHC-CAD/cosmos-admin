<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>

	<head>
		<script>
			function getLots() {
				projectGuid = $("#project").children("option:selected").val();
				alert(projectGuid);
			}
		</script>
	</head>

	<h2>Delete Lot Tool</h2>
	This tool will PERMENANTLY DELETE the data lot you select and choose to delete.
	<br/>
	Please use cautiously.  With great power comes great responsibility.  
	<br/>
	
	<h3>Select Project</h3>
	<select id="project" name="project">
		<option value=""></option>
		<c:forEach items="${projectList}" var="project">
			<option value="${project.guid}">${project.code}</option>
		</c:forEach>	
	</select>
	<br/>
	<br/>
	<button onclick="javascript:getLots();">Next</button>



	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</div>

