<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
	<table class="yaormaBigBox collapsible fullWidth" style="table-layout:fixed;width:100%">
		<tr>
			<td>Raw Data Files and Tables by Table</td>
		</tr>
		<tr>
			<td>
				These are the Raw Data Tables that are defined in the system and the data files that have populated those tables.  
				<br/><br/>
				This table answers the question: What files have been uploaded for each table.  
				<br/><br/>
				<div width="100%">
					<div style="overflow:auto;">
						<table class="yaormaDataTable">
							<tr>
								<th><nobr>Entity</nobr></th>
								<th><nobr>Table</nobr></th>
								<th><nobr>Org</nobr></th>
								<th><nobr>File</nobr></th>
								<th><nobr>File Size</nobr></th>
								<th><nobr>Units</nobr></th>
								<th><nobr>Raw Table Name For This File</nobr></th>
								<th><nobr>Raw Table Schema</nobr></th>
								<th><nobr>Group Table Name For This File</nobr></th>
								<th><nobr>Group Table Schema</nobr></th>
							</tr>
							<c:forEach items="${rawTableDetailListByTable}" var="rawTableDetailListByTable">
								<tr>
									<td>${rawTableDetailListByTable.groupTableName}</td>
									<td>${rawTableDetailListByTable.groupCode}</td>
									<td>${rawTableDetailListByTable.orgCode}</td>
									<td>${rawTableDetailListByTable.fileName}</td>
									<td>${rawTableDetailListByTable.fileSize}</td>
									<td>${rawTableDetailListByTable.fileSizeUnits}</td>
									<td>${rawTableDetailListByTable.rawTableSchema}</td>
									<td>${rawTableDetailListByTable.rawTableName}</td>
									<td>${rawTableDetailListByTable.groupTableSchema}</td>
									<td>${rawTableDetailListByTable.groupTableName}</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</div>
