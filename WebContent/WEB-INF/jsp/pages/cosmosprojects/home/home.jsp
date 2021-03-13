<%@ include file="/WEB-INF/jsp/headerfooter/header/componentHeader.jsp"%>

<div>
	<h2>Welcome to the NACHC Cosmos Browser</h2>
	<table class="yaormaBigBox collapsible fullWidth" style="table-layout:fixed;width:100%">
		<tr>
			<td>Goals and Overview</td>
		</tr>
		<tr>
			<td width="100%">
				<div width="100%">
					<div style="overflow:auto;">
						Cosmos provides a structured, common, consistent, reliable, mechanism for capturing and storing of information represented by files and other data sources provided to and available within NACHC.  This includes files of all types including raw datafiles, parsed and derived files, as well as images etc.  Incoming data will be from multiple sources, in multiple formats, and used for a variety of processes and tasks.  Files and information will be stored in a consistent manner that streamlines and optimizes current process and additionally allows for the archiving, indexing, tracking, searching, and generation of metrics and other information and metadata for enterprise data. 
						<br/><br/>
						Cosmos is here to enable data utilization.
						<br/><br/>
						<li>
							More information on Cosmos is available at the <a target="cosmos" href="https://confluence.nachc.org/display/COS/Cosmos">Cosmos Confluence Page</a>.  
						</li>
						<li>
							More information on our Informatics team is available at the <a target="informatics" href="https://confluence.nachc.org/display/INFO/Welcome+to+NACHC+Informatics">Informatics Confluence Page</a>.
						</li>
						<li>
							More information on NACHC is available at the <a target="nachc" href="https://www.nachc.org/">NACHC Web Page</a>.  
						</li>
					</div>
				</div>
			</td>
		</tr>
	</table>
	<br/>
	<table class="yaormaBigBox collapsible fullWidth" style="table-layout:fixed;width:100%">
		<tr>
			<td>Overview of Cosmos Data Model</td>
		</tr>
		<tr>
			<td width="100%">
				<div width="100%">
					<div style="overflow:auto;">
						Cosmos is built primarily on Databricks with some support for relational data (mostly metadata regarding data sources and relationships between tables, files, other data sources, etc.).  Data are uploaded into the system through an automated process.  
						<br/><br/>
						A Project is created in Cosmos that includes all of the information regarding the data sources for the project.  This includes information about what data files are/will be used by the project, the tables that are generated from these data sources, including aggregate and summary tables, etc.  
						<br/><br/>
						Each project is composed of Data Groups.  A Data Group is all of the data from all sources for a single entity (in the database sense of the word).  A Data Group is exposed to end users as a single table that represents all of the cleaned, validated, and verified data from all sources for the project.  A data group is the base table for things like Demographics, Encounters, Medications, Diagnoses, etc.  
						<br/><br/>
						All data uploaded into Cosmos is associated with the project that originally captured that data.  However, these data are available to all other projects and systems within Cosmos.  The processes and systems for Cosmos data capture are described in the "Cosmos Data Capture" PowerPoint document available <a href="https://confluence.nachc.org/display/COS/Documentation+About+Cosmos" target="confluence">here</a>.  
						<br/><br/>
						In short, raw data files are uploaded directly to Cosmos and are available from their as our source of truth or "gold copy" of this information.  A single table is crated for each file creating a one-to-on relationship between these raw data tables and raw data files.  Raw data tables are then aggregated into Data Group tables.  Each Data Group table represents all of the data from all sources for that Data Group (e.g. Demographics, Encounters, Rx, Dx, etc.).  
					</div>
				</div>
			</td>
		</tr>
	</table>
	<br/><br/><br/>
</div>
