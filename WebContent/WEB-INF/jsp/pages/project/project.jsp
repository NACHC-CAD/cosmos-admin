<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<html>
	<body>
		<div class="bodyContent">
			<h1>Project: ${projectDvo.name}</h1>
			<div id="jquery-tabs">
				<ul>
					<li><a href="#tabs-1">Overview</a></li>
					<li><a href="#tabs-2">Data</a></li>
				</ul>
				<div id="tabs-1">
					<jsp:include page="/WEB-INF/jsp/pages/project/overview/overview.jsp" />
				</div>
				<div id="tabs-2" class="jQueryTab">
					<jsp:include page="/WEB-INF/jsp/pages/project/data/data.jsp" />
				</div>
			</div>
		</div>
	</body>
</html>


