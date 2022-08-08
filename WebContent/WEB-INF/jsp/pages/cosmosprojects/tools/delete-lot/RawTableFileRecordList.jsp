<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div style="overflow:auto;">
	<br/><br/>
	<table class="yaormaDataTable">
		<tr>
			<th><nobr>Project</nobr></th>
			<th><nobr>Org</nobr></th>
			<th><nobr>Data Lot</nobr></th>
			<th><nobr>File Name</nobr></th>
			<th><nobr>File Size</nobr></th>
			<th><nobr>Units</nobr></th>
			<th><nobr>Provided By</nobr></th>
			<th><nobr>Provided Date</nobr></th>
		</tr>
		<c:forEach items="${rawTableFileRecords}" var="rawTableFileRecord">
			<tr>
				<td>${rawTableFileRecord.project}</td>
				<td>${rawTableFileRecord.orgCode}</td>
				<td>${rawTableFileRecord.dataLot}</td>
				<td>${rawTableFileRecord.fileName}</td>
				<td>${rawTableFileRecord.fileSize}</td>
				<td>${rawTableFileRecord.fileSizeUnits}</td>
				<td>${rawTableFileRecord.providedBy}</td>
				<td>${rawTableFileRecord.providedDate}</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<button onclick="javascript:backToSelectLot();">Back</button>
	&nbsp;
	<button>Delete These Files</button>
</div>
