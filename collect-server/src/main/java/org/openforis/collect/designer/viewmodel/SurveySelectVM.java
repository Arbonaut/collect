/**
 * 
 */
package org.openforis.collect.designer.viewmodel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.openforis.collect.designer.model.SurveyManagerUtil;
import org.openforis.collect.designer.model.SurveyWorkSummary;
import org.openforis.collect.designer.session.SessionStatus;
import org.openforis.collect.designer.util.PageUtil;
import org.openforis.collect.designer.util.Resources;
import org.openforis.collect.designer.util.Resources.Page;
import org.openforis.collect.manager.SurveyManager;
import org.openforis.collect.model.CollectSurvey;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Window;

/**
 * 
 * @author S. Ricci
 *
 */
public class SurveySelectVM extends BaseVM {
	
	private static final String TEXT_XML = "text/xml";

	public static final String CLOSE_SURVEY_IMPORT_POP_UP_GLOBAL_COMMNAD = "closeSurveyImportPopUp";
	
	@WireVariable
	private SurveyManager surveyManager;
	
	private SurveyWorkSummary selectedSurvey;

	private Window surveyImportPopUp;
	
	@Init()
	public void init() {
		PageUtil.clearConfirmClose();
	}
	
	@Command
	public void editSelectedSurvey() throws IOException {
		CollectSurvey surveyWork = loadSelectedSurvey();
		SessionStatus sessionStatus = getSessionStatus();
		if ( selectedSurvey.isPublished() && ! selectedSurvey.isWorking() ) {
			sessionStatus.setPublishedSurveyId(selectedSurvey.getId());
		} else {
			sessionStatus.setPublishedSurveyId(null);
		}
		sessionStatus.setSurvey(surveyWork);
		sessionStatus.setCurrentLanguageCode(null);
		Executions.sendRedirect(Page.SURVEY_EDIT.getLocation());
	}

	@Command
	public void newSurvey() throws IOException {
		CollectSurvey survey = surveyManager.createSurveyWork();
		SessionStatus sessionStatus = getSessionStatus();
		sessionStatus.setSurvey(survey);
		sessionStatus.setCurrentLanguageCode(null);
		Executions.sendRedirect(Page.SURVEY_EDIT.getLocation());
	}
	
	@Command
	public void exportSelectedSurvey() throws IOException {
		CollectSurvey survey = loadSelectedSurvey();
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		surveyManager.marshalSurvey(survey, os);
		byte[] content = os.toByteArray();
		String fileName = survey.getName() + ".xml";
		Filedownload.save(content, TEXT_XML, fileName);
	}
	
	@Command
	public void goToIndex() {
		Executions.sendRedirect(Page.INDEX.getLocation());
	}
	
	@Command
	public void openSurveyImportPopUp() {
		surveyImportPopUp = openPopUp(Resources.Component.SURVEY_IMPORT_POPUP.getLocation(), true);
	}
	
	@GlobalCommand
	public void closeSurveyImportPopUp() {
		closePopUp(surveyImportPopUp);
		surveyImportPopUp = null;
	}
	
	protected CollectSurvey loadSelectedSurvey() {
		String uri = selectedSurvey.getUri();
		CollectSurvey surveyWork;
		if ( selectedSurvey.isPublished() ) {
			surveyWork = surveyManager.loadPublishedSurveyForEdit(uri);
		} else {
			surveyWork = surveyManager.loadSurveyWork(selectedSurvey.getId());
		}
		return surveyWork;
	}
	
	public ListModel<SurveyWorkSummary> getSurveySummaries() {
		List<SurveyWorkSummary> summaries = SurveyManagerUtil.getSurveySummaries(surveyManager);
		return new ListModelList<SurveyWorkSummary>(summaries);
	}

	public SurveyWorkSummary getSelectedSurvey() {
		return selectedSurvey;
	}

	public void setSelectedSurvey(SurveyWorkSummary selectedSurvey) {
		this.selectedSurvey = selectedSurvey;
	}
	
	@DependsOn("selectedSurvey")
	public boolean isSurveySelected() {
		return this.selectedSurvey != null;
	}
	
	@DependsOn("selectedSurvey")
	public boolean isEditingDisabled() {
		return this.selectedSurvey == null;
	}
	
	@DependsOn("selectedSurvey")
	public boolean isExportDisabled() {
		return this.selectedSurvey == null;
	}
	
}
