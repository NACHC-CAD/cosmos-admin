<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>

	<table class="yaormaBigBox collapsible fullWidth" style="table-layout:fixed;width:100%">
		<tr>
			<td>Raw Data Tables</td>
		</tr>
		<tr>
			<td>
				The following tables represent the raw data tables for all data submitted to this project.  
				These tables are the aggregate tables that include data from multiple organizations.  
				All of these tables include columns that indicate the organization associated with the data (i.e. data can be queried by organization).  
				Information regarding raw data tables for this project is here (Coming soon... link to raw data details).  
				All original raw data files are retained by this system.  Information regarding raw data files for this project is available here.  (Coming soon... link to data files);
				<br/><br/>
				These tables are generally not accessed directly.  Data in these tables are not fully cleaned (column names have been normalized and column aliases have been applied).
				Most queries should be done using the Core Project Schema data tables (Coming soon... link).  
				<br/><br/>
				<div width="100%">
					<div style="overflow:auto;">
						<table class="yaormaDataTable">
							<tr>
								<th><nobr>Entity Name</nobr></th>
								<th><nobr>Table Schema</nobr></th>
								<th><nobr>Table Description</nobr></th>
								<th><nobr>View Table Details</nobr></th>
							</tr>
							<c:forEach items="${rawTableGroupList}" var="group">
								<tr>
									<td>${group.groupTableName}</td>
									<td>${group.groupTableSchema}</td>
									<td>${group.description}</td>
									<td>Comming Soon...</td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</div>
