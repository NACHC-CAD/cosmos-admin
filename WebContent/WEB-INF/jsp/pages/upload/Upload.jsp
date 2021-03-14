<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<html>
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
									<button width="100%" style="width:100%">Submit</button>
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
