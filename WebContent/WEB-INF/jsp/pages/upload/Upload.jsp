<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<html>
	<head>
		<script>
			postUploadFile = function() {
			    $("#status").val("");
			    YES.getAndShowStatus("uploadForm", "status", "${home}/Upload");
			}
		</script>
	</head>
	<body>
		<div align="center">
			<p>
				<b>Upload Files (as a single zip)</b>
			</p>
			<form name="uploadForm" id="uploadForm" action="${home}/Upload" method="post" enctype="multipart/form-data">
				<table border="1">
					<tr><td>
						<table border="0">
							<tr>
								<td>
									<br/>
									<input type="file" id="file" name="file" style="width: 600px"/>
								</td>
							</tr>
							<tr>
								<td>
									<br/>
									Status:
									<textarea wrap="off" readonly id="status" name="status" style="height: 200px;width: 100%;color: gray;">Select file and press submit...</textarea>
								</td>
							</tr>
							<tr>
								<td>
									<input type="button" value="Upload file to Cosmos" style="width:100%" onclick="javascript:postUploadFile()" />
								</td>
							</tr>
						</table>
					</td></tr>
				</table>
			</form>
			<br/><br/><br/><br/>
		</div>
	</body>
	
	
</html>
