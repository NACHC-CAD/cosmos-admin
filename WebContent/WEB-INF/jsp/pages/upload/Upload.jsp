<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<html>
	<head>
	</head>
	<body>
		<script>
		
			disableButtons = function() {
				$("#uploadButton").prop("disabled", true);
				$("#file").prop("disabled", true);
				$("#createGroupTablesButton").prop("disabled", true);
			}
		
			enableButtons = function() {
				$("#uploadButton").prop("disabled", false);
				$("#file").prop("disabled", false);
				$("#createGroupTablesButton").prop("disabled", false);
			}
			
			postUploadFile = function() {
				if($("#file").val() == "") {
					alert("Please select file to upload.");
					enableButtons();
				} else {
				    $("#status").val("");
				    createGroupTables = $("#createGroupTables").is(":checked");
				    if(createGroupTables == "true") {
				    	$("createGroupTablesFormInput").val("true");
				    } else {
				    	$("createGroupTablesFormInput").val("false");
				    }
				    url = "";
				    url = url + "${home}/Upload";
				    url = url + "?createGroupTables=" + createGroupTables;
					YES.getAndShowStatus("uploadForm", "status", url, enableButtons, enableButtons, disableButtons);
					disableButtons();
				}
			}
		</script>
		<div align="center">
			<p>
				<h1>Upload Files (as a single zip)</h1>
			</p>
			<form name="uploadForm" id="uploadForm" action="${home}/Upload" method="post" enctype="multipart/form-data">
				<input id="createGroupTablesFormInput" type="hidden" value="" />
				<table border="1" width="800px">
					<tr><td>
						<table border="0">
							<tr>
								<td><h2>Upload Zip File</h2></td>
							</tr>
							<tr>
								<td>
									<input type="file" id="file" name="file" style="width: 800px"/>
								</td>
							</tr>
							<tr>
								<td>
									<input id="createGroupTables" type="checkbox" checked />
									<label for="createGroupTables">&nbsp;Create&nbsp;Group&nbsp;Tables</label>
								</td>
							</tr>
							<tr>
								<td>
									<br/>
									Status:
								</td>
							</tr>
							<tr>
								<td>
									<textarea wrap="off" readonly id="status" name="status" style="height: 330px;width: 100%;color: gray;">Select file and press submit...</textarea>
								</td>
							</tr>
							<tr>
								<td>
									<input id="uploadButton" type="button" value="Upload file to Cosmos" onclick="postUploadFile();" />
								</td>
							</tr>
						</table>
					</td></tr>
					<tr><td>
						<table width="800px">
							<tr>
								<td><h2>Create Group Tables</h2></td>
							</tr>
							<tr>
								<td><div id="projectPickList"></div></td>
							</tr>
						</table>
					</td></tr>
				</table>
			</form>
			<br/><br/><br/><br/>
		</div>
		
		<script>
			forwardTo = "/WEB-INF/jsp/pages/upload/ProjectPickList.jsp";
			url = "${home}/GetProjectList";
			url = url + "?forwardTo=" + forwardTo
			$("#projectPickList").load(url);
		</script>
		
	</body>
	
	
</html>
