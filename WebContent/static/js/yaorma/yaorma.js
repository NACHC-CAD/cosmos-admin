
/* * * *
 * 
 * YES: Yaorma Extended Scripting: core functions.
 * 
 * * * */

var YES = {};

// --------------------------------------------------------------------------*/
//
// GUI STUFF
//
// --------------------------------------------------------------------------*/

//
// ui block to prevent double clicks, etc.
//

YES.block = function() {
/*
	$.blockUI({ 
		message: null,
		overlayCSS: { backgroundColor: '#000000;', opacity: .4 }
	});
*/
};

YES.unblock = function() {
//	$.unblockUI();
};

//
// guid
//

YES.guid = function() {
	function s4() {
		return Math.floor((1 + Math.random()) * 0x10000).toString(16).substring(1);
	};
	return s4() + s4() + '-' + s4() + '-' + s4() + '-' + s4() + '-' + s4() + s4() + s4();
};	

//
// side tabs
//

YES.createYaormaSideTabs = function() {
	$(".yaormaSideTabs").find(" > table:eq(0)").addClass("yaormaSideTabsTable");
	$(".yaormaSideTabs").find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0)").addClass("yaormaSideTabsContainer");
	$(".yaormaSideTabs").find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0) div").addClass("yaormaSideTab");
	$(".yaormaSideTabs").find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > td:eq(0)").addClass("yaormaSideTabsContentContainer");
	$(".yaormaSideTabs").find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > td:eq(0) > div").addClass("yaormaSideTabContent");
};

YES.showYaormaSideTab = function(event) {
	// find the tabset and the selected tab
	var targ = YES.getEventTarget(event);
    var parent = targ.parents(".yaormaSideTabs").first();
    var contentDivName = targ.attr("id");
	// update the header
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0) div").removeClass("selected");
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0) div#" + contentDivName).addClass("selected");
	// update the content
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > td:eq(0) > div").removeClass("selected");
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > td:eq(0) > #" + contentDivName).addClass("selected");
};

//
// top tabs
//

YES.createYaormaTabs = function() {
	$(".yaormaTabs").find(" > table > tbody > tr:eq(1)").addClass("yaormaTabsContentRow");
	$(".yaormaTabs").find(" > table").addClass("yaormaTabsTable");
	$(".yaormaTabs").find(" > table > tbody > tr:eq(0) > th:eq(0)").addClass("yaormaTopTabsContainer");
	$(".yaormaTabs").find(" > table > tbody > tr:eq(0) > th:eq(0) div").addClass("yaormaTopTab");
	$(".yaormaTabs").find(" > table > tbody > tr:eq(1) > td:eq(0)").addClass("yaormaTabsContentContainer");
	$(".yaormaTabs").find(" > table > tbody > tr:eq(1) > td:eq(0) > div").addClass("yaormaTabsContent");
};

YES.showYaormaTab = function(event) {
	// find the tabset and the selected tab
	var targ = getEventTarget(event);
    var parent = targ.parents(".yaormaTabs").first();
    var contentDivName = targ.attr("id");
	// update the header
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0) div").removeClass("selected");
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0) div#" + contentDivName).addClass("selected");
	// update the content
	parent.find(" > table > tbody:eq(0) > tr:eq(1) > td:eq(0) > div").removeClass("selected");
	parent.find(" > table > tbody:eq(0) > tr:eq(1) > td:eq(0) > #" + contentDivName).addClass("selected");
};

//
// change tabs methods
//

YES.showYaormaTab = function(event) {
	// find the tabset and the selected tab
	var targ = YES.getEventTarget(event);
    var parent = targ.parents(".yaormaTabs").first();
    var contentDivName = targ.attr("id");
	// update the header
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0) div").removeClass("selected");
	parent.find(" > table:eq(0) > tbody:eq(0) > tr:eq(0) > th:eq(0) div#" + contentDivName).addClass("selected");
	// update the content
	parent.find(" > table > tbody:eq(0) > tr:eq(1) > td:eq(0) > div").removeClass("selected");
	parent.find(" > table > tbody:eq(0) > tr:eq(1) > td:eq(0) > #" + contentDivName).addClass("selected");
};

//
//event
//

YES.getEventTarget = function(event) {
	var targ;
	if (!event) var event = window.event;
	if (event.target) targ = event.target;
	else if (event.srcElement) targ = event.srcElement;
	if (targ.nodeType == 3) targ = targ.parentNode;
	return $(targ);
};


// 
// big box
//

YES.collapseBigBox = function() {
	var content = $(this).parent().parent().find(".yaormaBigBoxContent");
	var header = $(this).parent().parent().find(".yaormaBigBoxHeader");
	if(content.hasClass("hidden")) {
		content.removeClass("hidden");
		header.removeClass("collapsed");
		header.addClass("expanded");
	} else {
		content.addClass("hidden");
		header.removeClass("expanded");
		header.addClass("collapsed");
	}
};

YES.createYaormaBigBox = function() {
	$(".yaormaBigBox").find(" > tbody:eq(0) > tr:eq(0) > td:eq(0)").addClass("yaormaBigBoxHeader");
	$(".yaormaBigBox").find(" > tbody:eq(0) > tr:eq(1) > td:eq(0)").addClass("yaormaBigBoxContent");
	$(".yaormaBigBox.collapsible").find(" > tbody:eq(0) > tr:eq(0) > td:eq(0)").click(YES.collapseBigBox);
	$(".yaormaBigBox.collapsible").find(" > tbody:eq(0) > tr:eq(0) > td:eq(0)").addClass("expanded");
	$(".yaormaBigBox.collapsible.collapsed").find(" > tbody:eq(0) > tr:eq(0) > td:eq(0)").removeClass("expanded");
	$(".yaormaBigBox.collapsible.collapsed").find(" > tbody:eq(0) > tr:eq(0) > td:eq(0)").addClass("collapsed");
	$(".yaormaBigBox.collapsible.collapsed").find(".yaormaBigBoxContent").addClass("hidden");
};

//
// data table
//

YES.createDataTable = function() {
	var formatEvenRow = function(index, value) {
		var $value = $(value);
		$value.find(" > td").removeClass("evenRow");
		$value.find(" > td").removeClass("oddRow");
		$value.find(" > td").addClass("evenRow");
	};
	var formatOddRow = function(index, value) {
		var $value = $(value);
		$value.find(" > td").removeClass("evenRow");
		$value.find(" > td").removeClass("oddRow");
		$value.find(" > td").addClass("oddRow");
	};
	
	$(".yaormaDataTable > tbody > tr:even").each(formatEvenRow);
	$(".yaormaDataTable > tbody > tr:odd").each(formatOddRow);
};

YES.fixPageableDataDivHeightAndWidth = function() {
	var fixPageableDataDivHeightAndWidthImpl = function(index, value) {
		var $value = $(value);
		$value.css("min-height", 0);
		$value.css("min-width", 0);
	};
	$(".pageableDataDiv").each(fixPageableDataDivHeightAndWidthImpl);
};

//
// show yaorma content
//

YES.showYaormaContent = function() {
	$(".yaormaDynamicContent").removeClass("yaormaDynamicContent");
};

//
// document ready
//

YES.documentReadyFunction = function() {
	var continueLoading = true;
	if(typeof(beforePageDisplay) != "undefined") {
		continueLoading = beforePageDisplay();
	} if(continueLoading == true) {
		YES.createYaormaTabs();
		YES.createYaormaSideTabs();
		YES.createYaormaBigBox();
		YES.createDataTable();
		YES.showYaormaContent();
		YES.fixPageableDataDivHeightAndWidth();
	}
};

$(document).ready(YES.documentReadyFunction);

//--------------------------------------------------------------------------*/
//
// DYNAMIC CONTENT
//
// --------------------------------------------------------------------------*/

YES.showErrorMessage = function() {
	alert( "An error occured." );
	YES.unblock();
};

YES.get = function(divId, url, successFunction) {
	YES.block();
	jQuery.get(url, 
		function(data) {
			jQuery("#" + divId).html(data);
			if(successFunction != undefined) {
				successFunction();
			}
			YES.unblock();
		}
	).error(YES.ajaxError);
};

		$( function() {
		  $( "#jquery-tabs" ).tabs();
		} );


