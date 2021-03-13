<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>
	<table class="yaormaBigBox collapsible fullWidth" style="table-layout:fixed;width:100%">
		<tr>
			<td>Raw Data Files by Organization</td>
		</tr>
		<tr>
			<td>
				These are all of the data files currently captured for this project and the associated Organization that provided the file.  The table that was populated by the file is also shown.  
				<br/><br/>
				This table answers the question: What data files have been uploaded for each organization.  
				<br/><br/>
				<div width="100%">
					<div style="overflow:auto;">
						<table class="yaormaDataTable">
							<tr>
								<th><nobr>Org</nobr></th>
								<th><nobr>Entity</nobr></th>
								<th><nobr>Table Name</nobr></th>
								<th><nobr>File Name</nobr></th>
								<th><nobr>File Size</nobr></th>
								<th>Units</th>
							</tr>
							<c:forEach items="${rawTableDetailListByOrg}" var="rawTableDetailListByOrg">
								<tr>
									<td>${rawTableDetailListByOrg.orgCode}</td>
									<td>${rawTableDetailListByOrg.groupTableName}</td>
									<td>${rawTableDetailListByOrg.groupCode}</td>
									<td>${rawTableDetailListByOrg.fileName}</td>
									<td>${rawTableDetailListByOrg.fileSize}</td>
									<td>${rawTableDetailListByOrg.fileSizeUnits}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</div>
