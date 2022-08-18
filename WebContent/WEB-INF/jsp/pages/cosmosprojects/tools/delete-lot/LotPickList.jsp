<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>
	<h3>Select Lot</h3>

	<select id="dataLot" name="dataLot">
		<option value=""></option>
		<c:forEach items="${dataLots}" var="dataLot">
			<option value="${dataLot.dataLot}">(${dataLot.createdDate}) ${dataLot.dataLot}</option>
		</c:forEach>	
	</select>
	<br/><br/>
	<button id="back1" onclick="javascript:backToSelectProject();">Back</button>
	&nbsp;
	<button id="next2" onclick="javascript:getRawTableFileRecords();">Next</button>
	
</div>