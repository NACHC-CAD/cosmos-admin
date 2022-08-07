<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<html>

	<div class="yaormaSideTabs yaormaDynamicContent">
		<table style="table-layout:fixed;">
			<tr>
				<th>
					<nobr>
						<div id="delete-lot" onclick="javascript:YES.showYaormaSideTab(event);" class="selected">Delete Lot</div>
						<div id="about" onclick="javascript:YES.showYaormaSideTab(event);">About Tools</div>
					</nobr>
				</th>
				<td width="100%">
					<div id="delete-lot" class="selected">
						<jsp:include page="/WEB-INF/jsp/pages/cosmosprojects/tools/delete-lot/DeleteLotHome.jsp" />
					</div>
					<div id="about">
						<jsp:include page="/WEB-INF/jsp/pages/cosmosprojects/tools/about/AboutTools.jsp" />
					</div>
				</td>
			</tr>
		</table>
		<br/><br/><br/>
	</div>
	<br/><br/>
</html>


