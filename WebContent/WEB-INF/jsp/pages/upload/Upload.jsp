<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<html>
	<head>
		<script>
			postUploadFile = function() {
				// YES.post("uploadFilesForm", "status", "${home}/Upload");
				alert("DOING GET");
				$("#status").val("");
				YES.getAndShowStatus("status", "${home}/Upload");
			}
		</script>
	</head>
	<body>
		<div align="center">
			<p>
				<b>Upload Files (as a single zip)</b>
			</p>
			<form action="${home}/Upload" method="post" enctype="multipart/form-data">
				<table border="1">
					<tr><td>
						<table border="0">
							<tr>
								<td>
									<input type="file" id="file" name="file" style="width: 600px"/>
								</td>
							</tr>
							<tr>
								<td>
									  
									<button style="width:100%">Submit Form</button>
									
								</td>
							</tr>
							<tr>
								<td>
									<br/>
									<div id="foo"></div>
									Status:
									<textarea id="status" name="status" style="height: 200px;width: 100%">Select file and press submit...</textarea>
								</td>
							</tr>
						</table>
					</td></tr>
				</table>
			</form>
			<br/><br/><br/><br/>
		</div>
	</body>
	<!--  
									<button style="width:100%" onclick="javascript:postUploadFile()">Submit</button>
	-->
</html>
