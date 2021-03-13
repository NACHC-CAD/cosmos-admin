package org.nachc.cosmos.web.proxy;

import java.util.List;

import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjUrlDvo;
import org.nachc.cad.cosmos.dvo.mysql.cosmos.ProjectDvo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectOverviewProxy {

	private ProjectDvo projectDvo;

	private List<ProjUrlDvo> projUrlDvoList;

}
