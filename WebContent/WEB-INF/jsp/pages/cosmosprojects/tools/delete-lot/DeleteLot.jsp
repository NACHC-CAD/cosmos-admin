<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>

	<head>
		<script>

			function getOrgs() {
				project = $("#project").children("option:selected").val();
				forwardTo = "/WEB-INF/jsp/pages/cosmosprojects/tools/delete-lot/OrgPickList.jsp";
				url = "${home}/GetOrgsForProject";
				url = url + "?project=" + project;
				url = url + "&forwardTo=" + forwardTo
				$("#orgPickList").load(url);
			}

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

			function enableDeleteLotButtons() {
				$("#createGroupTablesForDelete").prop("disabled", false);
				$("#next1").prop("disabled", false);
				$("#next2").prop("disabled", false);
				$("#back1").prop("disabled", false);
				$("#back2").prop("disabled", false);
				$("#deleteTheseFiles").prop("disabled", false);
			}
			
			function disableDeleteLotButtons() {
				$("#next1").prop("disabled", true);
				$("#next2").prop("disabled", true);
				$("#back1").prop("disabled", true);
				$("#back2").prop("disabled", true);
				$("#createGroupTablesForDelete").prop("disabled", true);
				$("#deleteTheseFiles").prop("disabled", true);
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
				// figure out group tables
			    createGroupTables = $("#createGroupTablesForDelete").is(":checked");
			    if(createGroupTables == "true") {
			    	$("createGroupTablesDeleteFormInput").val("true");
			    } else {
			    	$("createGroupTablesDeleteFormInput").val("false");
			    }
			    // update the gui
				$("#deleteStatusLog").val("");
			    // submit the form
			    url = "";
			    url = url + "${home}/DeleteLot";
			    url = url + "?createGroupTablesDeleteFormInput=" + createGroupTables;
			    YES.postFormAndShowStatus("deleteForm", "deleteStatusLog", url, enableDeleteLotButtons, enableDeleteLotButtons, disableDeleteLotButtons);
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
	<select id="project" name="project" onchange="getOrgs();">
		<option value=""></option>
		<c:forEach items="${projectList}" var="project">
			<option value="${project.code}">${project.code}</option>
		</c:forEach>	
	</select>
	&nbsp;&nbsp;
	<span id="orgPickList">
		<select id="org" name="org">
			<option disabled="true" value=""></option>
			<option disabled="true" value="">-- Please Select Project First --</option>
		</select>
	</span>
	<br/>
	<br/>
	<button id="next1" onclick="javascript:getLots();">Next</button>
	<br/>
	<br/>
	<div id="lotPickList"></div>
	<div id="rawTableFileRecords"></div>



	<br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
</div>

