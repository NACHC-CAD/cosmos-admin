<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<html>
	<body>
		<div class="bodyContent" align="center">
			<p>
				<b>Upload Files (as a single zip)</b>
			</p>
			<form action="${home}/UploadFiles" method="post">
				<table border="0">
					<tr>
						<td>
							<input type="file" id="file" name="file" />
						</td>
					</tr>
					<tr>
						<td>
							<button width="100%" style="width:100%">Submit</button>
						</td>
					</tr>
				</table>
			</form>
			<br/><br/><br/><br/>
		</div>
	<%@ include file="/WEB-INF/jsp/headerfooter/footer/footer.jsp"%>
	</body>
</html>
