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
			<th><nobr>Date Provided</nobr></th>
			<th><nobr>Date Uploaded</nobr></th>
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
				<td>${rawTableFileRecord.createdDate}</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	<button onclick="javascript:backToSelectLot();">Back</button>
	&nbsp;
	<button onclick="javascript:deleteLot();">Delete These Files</button>
	<br/><br/>
	<form id="deleteForm" name="deleteForm">
		<input id="projectInput" type="hidden" name="project" />
		<input id="orgInput" type="hidden" name="org" />
		<input id="dataLotInput" type="hidden" name="dataLot" />
		<table border="1">
			<tr>
				<td><b>Status:<b/></td>
			</tr>
			<tr>
				<td>
					<textarea 
						wrap="off" 
						readonly 
						id="deleteStatusLog" 
						name="deleteStatusLog" 
						style="height: 200px;width: 800px;color: gray;">Press the "Delete These Files" button to PERMANENTLY DELETE the above files...</textarea>
				</td>
			</tr>
		</table>
	</form>
</div>
