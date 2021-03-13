<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:if test="${componentHeaderLoaded != true}">
	
	<!--  request variables -->
	<c:set var="home" value="${pageContext.request.contextPath}" scope="request"/>

	<!-- global resources -->
	<link rel="shortcut icon" href="${home}/static/img/icon/nachc-favico.png">
	<link rel="icon" href="${home}/static/img/icon/nachc-favico.png">

	<!-- jquery -->	
	<link rel="stylesheet" type="text/css" href="${home}/static/jquery/jquery-1.10.4.custom/css/redmond/jquery-ui-1.10.4.custom.min.css" />
	<script type='text/javascript' src='${home}/static/jquery/jquery-1.10.4.custom/js/jquery-1.10.2.js'></script>
	<script type='text/javascript' src='${home}/static/jquery/jquery-1.10.4.custom/js/jquery-ui-1.10.4.custom.min.js'></script>

	<!-- yaorma -->	
	<link rel="stylesheet" type="text/css" href="${home}/static/css/yaorma/yaorma.css" />
	<script type='text/javascript' src='${home}/static/js/yaorma/yaorma.js'></script>

	<!-- set this variable so this is only loaded once per request -->
	<c:set var="componentHeaderLoaded" value="true" scope="request"/>

</c:if>

