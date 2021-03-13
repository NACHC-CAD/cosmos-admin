<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<html>

	<div class="yaormaSideTabs yaormaDynamicContent">
		<table style="table-layout:fixed;">
			<tr>
				<th>
					<nobr>
						<div id="raw-data" onclick="javascript:YES.showYaormaSideTab(event);" class="selected">Raw Data</div>
						<div id="published-data" onclick="javascript:YES.showYaormaSideTab(event);">Published Data</div>
						<div id="custom-queries" onclick="javascript:YES.showYaormaSideTab(event);">Custom Queries</div>
					</nobr>
				</th>
				<td width="100%">
					<div id="raw-data" class="selected">
						<jsp:include page="/WEB-INF/jsp/pages/project/data/rawdata/rawdata.jsp" />
					</div>
					<div id="published-data">
						Published data goes here
					</div>
					<div id="custom-queries">
						Custom Queries go here
					</div>
				</td>
			</tr>
		</table>
		<br/><br/><br/>
	</div>
	<br/><br/>
</html>


