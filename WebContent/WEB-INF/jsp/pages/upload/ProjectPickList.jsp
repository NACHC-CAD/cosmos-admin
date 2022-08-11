<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<div>

	<form id="createGroupTablesForm">
		Select Project
		<br/>
		<select id="projectPickList" name="projectPickList">
			<option value=""></option>
			<c:forEach items="${projectList}" var="project">
				<option value="${project.code}">${project.code}</option>
			</c:forEach>	
		</select>
		&nbsp;<input id="createGroupTablesButton" type="button" value="Create Group Tables For Selected Project" />
	</form>
	<br/>

	<script>
		function createGroupTables() {
			project = $("#projectPickList").children("option:selected").val();
		    // update the gui
			$("#deleteStatusLog").val("");
		    // submit the form
		    url = "";
		    url = url + "${home}/CreateProjectGroupTables";
		    url = url + "?project=" + project;
		    YES.postFormAndShowStatus("createGroupTablesForm", "status", url);
		}
	
		$("#status").change(
			function() {
				$('#status').scrollTop($('#status')[0].scrollHeight);
			}
		);

		document.getElementById("createGroupTablesButton").addEventListener("click", createGroupTables, false);

	</script>

</div>

