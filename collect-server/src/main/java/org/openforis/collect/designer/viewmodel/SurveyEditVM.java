/**
 * 
 */
package org.openforis.collect.designer.viewmodel;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.compress.utils.CharsetNames;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.openforis.collect.designer.model.LabelKeys;
import org.openforis.collect.designer.model.SurveyManagerUtil;
import org.openforis.collect.designer.model.SurveyWorkSummary;
import org.openforis.collect.designer.session.SessionStatus;
import org.openforis.collect.designer.util.MessageUtil;
import org.openforis.collect.designer.util.PageUtil;
import org.openforis.collect.designer.util.Resources;
import org.openforis.collect.manager.SurveyManager;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.collect.persistence.SurveyImportException;
import org.openforis.idm.metamodel.CodeList;
import org.openforis.idm.metamodel.EntityDefinition;
import org.openforis.idm.metamodel.ModelVersion;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.Binder;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.DependsOn;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.bind.annotation.QueryParam;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.IdSpace;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zkplus.databind.BindingListModelList;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Window;

/**
 * @author S. Ricci
 *
 */
public class SurveyEditVM extends SurveyBaseVM {

	private static final String COLLECT_SWF_LOCATION = "/collect.swf";
	private static final String TEXT_XML = "text/xml";
	private static final String PREVIEW_WINDOW_ID = "collect_survey_preview";
	public static final String SHOW_PREVIEW_POP_UP_GLOBAL_COMMAND = "showPreview";
	private static final String SURVEY_SUCCESSFULLY_SAVED_MESSAGE_KEY = "survey.successfully_saved";
//	private static final String SURVEY_SUCCESSFULLY_PUBLISHED_MESSAGE_KEY = "survey.successfully_published";
	
	private Window selectLanguagePopUp;
	private Window previewPreferencesPopUp;
	private Window srsPopUp;
	private Window codeListsPopUp;
	private Window unitsPopUp;
	private Window versioningPopUp;

	@WireVariable
	private SurveyManager surveyManager;
	
	private boolean changed;

	@Init(superclass=false)
	public void init(@QueryParam("temp_id") Integer tempId) {
		super.init();
		if ( survey == null ) {
			backToSurveysList();
		} else {
			changed = false;
			currentLanguageCode = survey.getDefaultLanguage();
			if ( currentLanguageCode == null ) {
				openLanguageManagerPopUp();
			} else {
				SessionStatus sessionStatus = getSessionStatus();
				sessionStatus.setCurrentLanguageCode(currentLanguageCode);
			}
			String confirmCloseMessage = Labels.getLabel("survey.edit.leave_page");
			PageUtil.confirmClose(confirmCloseMessage);
		}
	}
	
	@Command
	public void openLanguageManagerPopUp() {
		if ( checkCanLeaveForm() ) {
			selectLanguagePopUp = openPopUp(Resources.Component.SELECT_LANGUAGE_POP_UP.getLocation(), true);
		}
	}
	
	@GlobalCommand
	public void exportSurvey() {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		surveyManager.marshalSurvey(survey, os);
		byte[] content = os.toByteArray();
		String fileName = survey.getName() + ".xml";
		Filedownload.save(content, TEXT_XML, fileName);
	}
	
	@GlobalCommand
	public void openSRSManagerPopUp() {
		if ( checkCanLeaveForm() ) {
			srsPopUp = openPopUp(Resources.Component.SRS_MANAGER_POP_UP.getLocation(), true);
		}
	}
	
	@GlobalCommand
	public void closeSRSManagerPopUp() {
		checkCanLeaveForm(new CanLeaveFormConfirmHandler() {
			@Override
			public void onOk(boolean confirmed) {
				closePopUp(srsPopUp);
				srsPopUp = null;
				dispatchCurrentFormValidatedCommand(true);
			}
		});
	}
	
	@GlobalCommand
	public void openCodeListsManagerPopUp(@BindingParam("selectedCodeList") CodeList selectedCodeList) {
		if ( codeListsPopUp == null ) { 
			dispatchCurrentFormValidatedCommand(true);
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("selectedCodeList", selectedCodeList);
			codeListsPopUp = openPopUp(Resources.Component.CODE_LISTS_POPUP.getLocation(), true, args);
		}
	}

	@GlobalCommand
	public void closeCodeListsManagerPopUp(@ContextParam(ContextType.BINDER) Binder binder) {
		if ( codeListsPopUp != null ) {
			checkCanLeaveForm(new CanLeaveFormConfirmHandler() {
				@Override
				public void onOk(boolean confirmed) {
					closePopUp(codeListsPopUp);
					codeListsPopUp = null;
					dispatchCurrentFormValidatedCommand(true);
				}
			});
		}
	}
	
	@GlobalCommand
	public void openUnitsManagerPopUp() {
		if ( unitsPopUp == null ) {
			dispatchCurrentFormValidatedCommand(true);
			unitsPopUp = openPopUp(Resources.Component.UNITS_MANAGER_POP_UP.getLocation(), true);
		}
	}
	
	@GlobalCommand
	public void closeUnitsManagerPopUp(@ContextParam(ContextType.BINDER) Binder binder) {
		if ( unitsPopUp != null ) {
			checkCanLeaveForm(new CanLeaveFormConfirmHandler() {
				@Override
				public void onOk(boolean confirmed) {
					closePopUp(unitsPopUp);
					unitsPopUp = null;
					dispatchCurrentFormValidatedCommand(true);
				}
			});
		}
	}	
	
	@GlobalCommand
	public void openVersioningManagerPopUp() {
		if ( versioningPopUp == null ) {
			dispatchCurrentFormValidatedCommand(true);
			versioningPopUp = openPopUp(Resources.Component.VERSIONING_POPUP.getLocation(), true);
		}
	}

	@GlobalCommand
	public void closeVersioningManagerPopUp() {
		if ( versioningPopUp != null ) {
			checkCanLeaveForm(new CanLeaveFormConfirmHandler() {
				@Override
				public void onOk(boolean confirmed) {
					closePopUp(versioningPopUp);
					versioningPopUp = null;
					dispatchCurrentFormValidatedCommand(true);
				}
			});
		}
	}
	
	@Command
	public void backToSurveysList() {
		if ( changed ) {
			MessageUtil.showConfirm(new MessageUtil.ConfirmHandler() {
				@Override
				public void onOk() {
					performBackToSurveysList();
				}
			}, "survey.edit.leave_page");
		} else {
			performBackToSurveysList();
		}
	}
	
	protected void performBackToSurveysList() {
		PageUtil.clearConfirmClose();
		resetSessionStatus();
		showMainPage();
	}

	protected void showMainPage() {
		Executions.sendRedirect(Resources.Page.DESIGNER.getLocation());
	}

	protected void resetSessionStatus() {
		SessionStatus sessionStatus = getSessionStatus();
		sessionStatus.reset();
	}
	
	@Command
	@NotifyChange({"currentLanguageCode"})
	public void languageCodeSelected(@BindingParam("code") final String selectedLanguageCode) {
		final SessionStatus sessionStatus = getSessionStatus();
		checkCanLeaveForm(new CanLeaveFormConfirmHandler() {
			@Override
			public void onOk(boolean confirmed) {
				sessionStatus.setCurrentLanguageCode(selectedLanguageCode);
				BindUtils.postGlobalCommand(null, null, SurveyLocaleVM.CURRENT_LANGUAGE_CHANGED_COMMAND, null);
				currentLanguageCode = sessionStatus.getCurrentLanguageCode();
			}
		});
	}
	
	@Command
	public void save(@ContextParam(ContextType.BINDER) Binder binder) throws SurveyImportException {
		dispatchValidateAllCommand();
//		validateMainForm(binder);
		if ( checkCanSave(false) ) {
			surveyManager.saveSurveyWork(survey);
			MessageUtil.showInfo(SURVEY_SUCCESSFULLY_SAVED_MESSAGE_KEY);
			BindUtils.postNotifyChange(null, null, survey, "id");
			BindUtils.postNotifyChange(null, null, survey, "published");
			notifyChange("surveyId","surveyPublished");
			changed = false;
		}
	}
	
	protected void validateMainForm(Binder binder) {
		Component view = binder.getView();
		IdSpace spaceOwner = view.getSpaceOwner();
		Component mainInfoFormContainer = Path.getComponent(spaceOwner, "mainInfoInclude/formContainer");
		Binder mainFormBinder = (Binder) mainInfoFormContainer.getAttribute("binder");
		SurveyMainInfoVM mainFormVM = (SurveyMainInfoVM) mainFormBinder.getViewModel();
		mainFormVM.validateForm(mainFormBinder);
	}

	protected boolean checkCanSave(boolean publishing) {
		if ( checkCanLeaveForm() ) {
			List<SurveyWorkSummary> surveySummaries = SurveyManagerUtil.getSurveySummaries(surveyManager);
			for (SurveyWorkSummary surveySummary : surveySummaries) {
				boolean notDuplicate = checkIsNotDuplicate(surveySummary, publishing);
				if ( ! notDuplicate ) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	protected boolean checkIsNotDuplicate(SurveyWorkSummary summary, boolean publishing) {
		Integer surveyId = survey.getId();
		Integer publishedSurveyId = getSessionStatus().getPublishedSurveyId();
		Integer summaryId = summary.getId();
		boolean skip = false;
		if ( surveyId == null ) {
			if ( publishedSurveyId != null && summary.isPublished() && publishedSurveyId.equals(summaryId) ) {
				skip = true;
			}
		} else if ( summaryId.equals(surveyId)) {
			skip = true;
		}
		if ( ! skip ) {
			if ( summary.getName().equals(survey.getName()) ) {
				String messageKey = publishing ? LabelKeys.SURVEY_PUBLISH_ERROR_DUPLICATE_NAME: LabelKeys.SURVEY_SAVE_ERROR_DUPLICATE_NAME;
				MessageUtil.showWarning(messageKey);
				return false;
			}
			if ( summary.getUri().equals(survey.getUri()) ) {
				String messageKey = publishing ? LabelKeys.SURVEY_PUBLISH_ERROR_DUPLICATE_URI: LabelKeys.SURVEY_SAVE_ERROR_DUPLICATE_URI;
				MessageUtil.showWarning(messageKey);
				return false;
			}
		}
		return true;
	}

	@GlobalCommand
	public void showPreview(@BindingParam("formVersion") ModelVersion formVersion, @BindingParam("rootEntity") EntityDefinition rootEntity) {
		if ( validateShowPreview(rootEntity, formVersion) ) {
			Execution current = Executions.getCurrent();
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("preview", "true"));
			params.add(new BasicNameValuePair("surveyId", Integer.toString(survey.getId())));
			params.add(new BasicNameValuePair("rootEntityId", Integer.toString(rootEntity.getId())));
			if ( formVersion != null ) {
				params.add(new BasicNameValuePair("versionId", Integer.toString(formVersion.getId())));
			}
			String uri = Resources.PREVIEW_PATH + "?" + URLEncodedUtils.format(params, "UTF-8");
			current.sendRedirect(uri, PREVIEW_WINDOW_ID);
			
			closePreviewPreferencesPopUp();
		}
	}
	
	protected boolean validateShowPreview(EntityDefinition rootEntityDefn, ModelVersion version) {
		if ( rootEntityDefn == null ) {
			MessageUtil.showWarning(LabelKeys.PREVIEW_ROOT_ENTITY_NOT_SPECIFIED);
			return false;
		} else if (survey.getId() == null || changed)  {
			MessageUtil.showWarning(LabelKeys.PREVIEW_ERROR_SAVE_SURVEY_FIRST);
			return false;
		} else if (version == null && survey.getVersions() != null && ! survey.getVersions().isEmpty() ) {
			MessageUtil.showWarning(LabelKeys.PREVIEW_ERROR_VERSION_NOT_SPECIFIED);
			return false;
		} else {
			return true;
		}
	}

	@Command
	public void openPreviewPreferencesPopUp() {
		previewPreferencesPopUp = openPopUp(Resources.Component.PREVIEW_PREFERENCES_POP_UP.getLocation(), true);
	}
	
	@GlobalCommand
	public void closePreviewPreferencesPopUp() {
		closePopUp(previewPreferencesPopUp);
		previewPreferencesPopUp = null;
	}
	
	@GlobalCommand
	public void surveyChanged() {
		changed = true;
	}

	@GlobalCommand
	@NotifyChange({"availableLanguages"})
	public void surveyLanguagesChanged() {
		closePopUp(selectLanguagePopUp);
		selectLanguagePopUp = null;
	}
	
	public List<String> getAvailableLanguages() {
		CollectSurvey survey = getSurvey();
		if ( survey == null ) {
			//TODO session expired?
			return null;
		} else {
			List<String> languages = survey.getLanguages();
			return new BindingListModelList<String>(languages, false);
		}
	}

	public boolean isChanged() {
		return changed;
	}
	
	@DependsOn({"surveyId","surveyPublished"})
	public String getSamplingDesignImportModuleUrl() {
		Map<String, String> queryParams = createBasicModuleParameters();
		queryParams.put("sampling_design_import", "true");
		String url = createUrl(COLLECT_SWF_LOCATION, queryParams);
		return url;
	}

	@DependsOn({"surveyId","surveyPublished"})
	public String getSpeciesImportModuleUrl() {
		Map<String, String> queryParams = createBasicModuleParameters();
		queryParams.put("species_import", "true");
		String url = createUrl(COLLECT_SWF_LOCATION, queryParams);
		return url;
	}

	private String createUrl(String base, Map<String, String> queryParams) {
		List<BasicNameValuePair> convertedParams = new ArrayList<BasicNameValuePair>();
		Set<Entry<String, String>> paramsEntrySet = queryParams.entrySet();
		for (Entry<String, String> param : paramsEntrySet) {
			BasicNameValuePair valuePair = new BasicNameValuePair(param.getKey(), param.getValue());
			convertedParams.add(valuePair);
		}
		String queryString = URLEncodedUtils.format(convertedParams, CharsetNames.UTF_8);
		String result = base + "?" + queryString;
		return result;
	}

	protected Map<String, String> createBasicModuleParameters() {
		Integer surveyId = getSurveyId();
		SessionStatus sessionStatus = getSessionStatus();
		Integer publishedSurveyId = sessionStatus.getPublishedSurveyId();
		if ( surveyId == null ) {
			//not yet saved
			surveyId = publishedSurveyId;
		}
		boolean work = surveyId != null && ! surveyId.equals(publishedSurveyId);
		String surveyIdStr = surveyId == null ? "": surveyId.toString();
		String localeStr = "en_US";
		Map<String, String> result = new HashMap<String, String>();
		result.put("lang", localeStr);
		result.put("work", "" + work);
		result.put("surveyId", surveyIdStr);
		return result;
	}

	
}
