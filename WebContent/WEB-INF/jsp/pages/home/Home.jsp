<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<html>
	<body>
		<div class="bodyContent">
			<%@ include file="/WEB-INF/jsp/headerfooter/header/pageHeader.jsp"%>
			<h1>Cosmos Administration</h1>
			<div id="jquery-tabs">
				<ul>
					<li><a href="#tabs-1">Home</a></li>
					<li><a href="#tabs-2">Upload</a></li>
					<li><a href="#tabs-3">Tools</a></li>
					<li><a href="#tabs-4">Projects</a></li>
				</ul>
				<div id="tabs-1">
					<jsp:include page="/WEB-INF/jsp/pages/cosmosprojects/home/home.jsp" />
				</div>
				<div id="tabs-2">
					<jsp:include page="/WEB-INF/jsp/pages/upload/Upload.jsp" />
				</div>
				<div id="tabs-3">
					<jsp:include page="/WEB-INF/jsp/pages/cosmosprojects/tools/tools.jsp" />
				</div>
				<div id="tabs-4">
					<jsp:include page="/WEB-INF/jsp/pages/cosmosprojects/projects/projects.jsp" />
				</div>
			</div>
		</div>
	</body>
</html>


