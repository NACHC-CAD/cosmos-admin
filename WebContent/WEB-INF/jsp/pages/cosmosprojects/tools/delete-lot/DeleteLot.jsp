<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>

	<head>
		<script>
			function getLots() {
				projectGuid = $("#project").children("option:selected").val();
				orgCode = $("#org").children("option:selected").val();
				forwardTo = "/WEB-INF/jsp/pages/cosmosprojects/tools/delete-lot/LotPickList.jsp";
				alert(orgCode);
				url = "${home}/GetDataLotsForOrgProject";
				url = url + "?projectGuid=" + projectGuid;
				url = url + "&orgCode=" + orgCode;
				url = url + "&forwardTo=" + forwardTo
				$("#lotPickList").load(url);
			}
		</script>
	</head>

	<h2>Delete Lot Tool</h2>
	This tool will PERMENANTLY DELETE the data lot you select and choose to delete.
	<br/>
	Please use cautiously.  With great power comes great responsibility.  
	<br/>
	
	<h3>Select Project and Organization</h3>
	<select id="project" name="project">
		<option value=""></option>
		<c:forEach items="${projectList}" var="project">
			<option value="${project.code}">${project.code}</option>
		</c:forEach>	
	</select>
	&nbsp;&nbsp;
	<select id="org" name="org">
		<option value=""></option>
		<c:forEach items="${orgList}" var="org">
			<option value="${org.code}">${org.code} (${org.name})</option>
		</c:forEach>	
	</select>
	<br/>
	<br/>
	<button onclick="javascript:getLots();">Next</button>
	<br/>
	<br/>
	<div id="lotPickList"></div>



	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</div>

