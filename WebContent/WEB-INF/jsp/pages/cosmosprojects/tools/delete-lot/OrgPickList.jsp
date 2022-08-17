<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<span>
	<select id="org" name="org">
		<option value=""></option>
		<c:forEach items="${orgList}" var="org">
			<option value="${org.code}">${org.code} (${org.name})</option>
		</c:forEach>	
	</select>
</span>