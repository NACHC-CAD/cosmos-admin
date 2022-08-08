<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>

	<head>
		<script>
			function getLots() {
				project = $("#project").children("option:selected").val();
				orgCode = $("#org").children("option:selected").val();
				forwardTo = "/WEB-INF/jsp/pages/cosmosprojects/tools/delete-lot/LotPickList.jsp";
				url = "${home}/GetDataLotsForOrgProject";
				url = url + "?project=" + project;
				url = url + "&orgCode=" + orgCode;
				url = url + "&forwardTo=" + forwardTo
				$("#lotPickList").load(url);
				$("#project").prop("disabled", true);
				$("#org").prop("disabled", true);
			}
			
			function getRawTableFileRecords() {
				project = $("#project").children("option:selected").val();
				orgCode = $("#org").children("option:selected").val();
				dataLot = $("#dataLot").children("option:selected").val();
				forwardTo = "/WEB-INF/jsp/pages/cosmosprojects/tools/delete-lot/RawTableFileRecordList.jsp";
				url = "${home}/GetRawTableFileRecords";
				url = url + "?project=" + project;
				url = url + "&orgCode=" + orgCode;
				url = url + "&dataLot=" + dataLot;
				url = url + "&forwardTo=" + forwardTo
				$("#rawTableFileRecords").load(url);
				$("#dataLot").prop("disabled", true);
			}
			
			function backToSelectLot() {
				$("#rawTableFileRecords").html("");
				$("#dataLot").prop("disabled", false);
			}
			
			function backToSelectProject() {
				$("#rawTableFileRecords").html("");
				$("#lotPickList").html("");
				$("#project").prop("disabled", false);
				$("#org").prop("disabled", false);
			}

			deleteLot = function() {
				// set the variable values
				project = $("#project").children("option:selected").val();
				orgCode = $("#org").children("option:selected").val();
				dataLot = $("#dataLot").children("option:selected").val();
			    // set the form values
			    $("#projectInput").val(project);
			    $("#orgInput").val(orgCode);
			    $("#dataLotInput").val(dataLot);
				// update the gui
				$("#deleteStatusLog").val("");
			    // submit the form
			    YES.postFormAndShowStatus("deleteForm", "deleteStatusLog", "${home}/DeleteLot");
			}
			
			$("#deleteStatusLog").change(
				function() {
					$('#deleteStatusLog').scrollTop($('#deleteStatusLog')[0].scrollHeight);
				}
			);

		</script>
	</head>

	<h2>Delete Lot Tool</h2>
	This tool will PERMANENTLY DELETE the data lot you select and choose to delete.
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
	<div id="rawTableFileRecords"></div>



	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</div>

