<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>


<div>
	<table class="yaormaBigBox collapsible fullWidth" style="table-layout:fixed;width:100%">
		<tr>
			<td>Project Overview:&nbsp;${projectOverviewProxy.projectDvo.name}</td>
		</tr>
		<tr>
			<td width="100%">
				<div width="100%">
					<div style="overflow:auto;">
						<b>Description</b>
						<br/>
						${projectOverviewProxy.projectDvo.description}
						<br/><br/>
						<b>More Information</b>
						<c:forEach items="${projectOverviewProxy.projUrlDvoList}" var="url" varStatus="urlCnt">
							<li>
								<a target="target_${urlCnt.index}" href="${url.url}">${url.linkText}</a>
							</li>
						</c:forEach>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<br/><br/><br/>
</div>




