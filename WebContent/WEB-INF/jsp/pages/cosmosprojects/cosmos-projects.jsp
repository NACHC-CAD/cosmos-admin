<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<html>
	<body>
		<div class="bodyContent">
			<h1>Cosmos Projects</h1>
			<div id="jquery-tabs">
				<ul>
					<li><a href="#tabs-1">Home</a></li>
					<li><a href="#tabs-2">Projects</a></li>
				</ul>
				<div id="tabs-1">
					<jsp:include page="/WEB-INF/jsp/pages/cosmosprojects/home/home.jsp" />
				</div>
				<div id="tabs-2">
					<jsp:include page="/WEB-INF/jsp/pages/cosmosprojects/projects/projects.jsp" />
				</div>
			</div>
		</div>
	</body>
</html>


