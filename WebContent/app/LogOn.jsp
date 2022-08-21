<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>
<html>
	<body>
		<div class="bodyContent" align="center">
			<br/>
			<img src="${home}/static/img/icon/chickadee.gif" width="300" />
			<br/>
			<img src="${home}/static/img/icon/cosmos-logo-green.png" width="200"/>
			<p>
				<b>Welcome to NACHC Cosmos</b>
				<br/>
				The Integrated Informatics System for the National Association of Community Health Centers
			</p>
			<form action="" method="post">
				<input type="hidden" id="attempt" name="attempt" value="${attempt + 1}" />
				<table class="yaormaBigBox">
					<tr>
						<td>Logon...</td>
					</tr>
					<tr>
						<td>
							<table border="0">
								<tr>
									<td align="right">
										Name:
									</td>
									<td>
										<!-- 
										<input type="text" id="uid" name="uid" value="${uid}"/>
										-->
										<input type="text" id="uid" name="uid" value="greshje"/>
									</td>
								</tr>
								<tr>
									<td align="right">
										Password:
									</td>
									<td>
										<!-- 
										<input type="password" id="pwd" name="pwd" value="${pwd}"/>
										-->
										<input type="password" id="pwd" name="pwd" value="dev"/>
									</td>
								</tr>
								<tr>
									<td>
									</td>
									<td align="center">
										<button width="100%" style="width:100%">Submit</button>
									</td>
								</tr>
								<tr>
									<td></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<br/>
				<div style="color:red">${msg}</div>
			</form>
			<br/><br/><br/><br/>
		</div>
	<%@ include file="/WEB-INF/jsp/headerfooter/footer/footer.jsp"%>
	</body>
</html>
