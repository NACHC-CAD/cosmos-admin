<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<html>
	<div class="headerBar footer yaormaDynamicContent" align="center">
		<nobr>
			<br/><br/>
			<img src="${home}/static/img/icon/nachc-logo.png" height="75px"/>
			<br/>
			<br/>
			&copy;&nbsp;2020 The National Association of Community Health Centers (NACHC), Bethesda, MD
			<br/>
			<c:if test = "${versionInfo != null}" >
				Databricks Instance: ${versionInfo.databricksDbInstance}
				<br/>
				Databricks File Store: ${versionInfo.databricksFileStoreInstance}
				<br/>
				MySql Instance: ${versionInfo.mySqlDbInstance}
				<br/>
				Java Version: ${versionInfo.javaVersion}
				<br/>
			</c:if> 
		</nobr>
	</div>
</html>


