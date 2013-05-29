package org.openforis.collect.remoting.service.dataimport;

import java.util.List;
import java.util.Map;

import org.granite.messaging.amf.io.util.externalizer.annotation.ExternalizedProperty;
import org.openforis.collect.Proxy;
import org.openforis.collect.model.CollectRecord.Step;
import org.openforis.collect.remoting.service.dataimport.DataImportSummary.FileErrorItem;
import org.openforis.collect.spring.SpringMessageSource;

/**
 * 
 * @author S. Ricci
 *
 */
public class DataImportSummaryProxy implements Proxy {

	private transient DataImportSummary summary;
	private transient SpringMessageSource messageContextHolder;

	public DataImportSummaryProxy(SpringMessageSource messageContextHolder, DataImportSummary summary) {
		super();
		this.summary = summary;
	}

	@ExternalizedProperty
	public List<FileUnmarshallingErrorProxy> getSkippedFileErrors() {
		List<FileErrorItem> skippedFileErrors = summary.getSkippedFileErrors();
		List<FileUnmarshallingErrorProxy> proxies = FileUnmarshallingErrorProxy.fromList(skippedFileErrors);
		return proxies;
	}

	@ExternalizedProperty
	public String getSurveyName() {
		return summary.getSurveyName();
	}

	@ExternalizedProperty
	public Map<Step, Integer> getTotalPerStep() {
		return summary.getTotalPerStep();
	}

	@ExternalizedProperty
	public List<DataImportSummaryItemProxy> getRecordsToImport() {
		return DataImportSummaryItemProxy.fromList(messageContextHolder, summary.getRecordsToImport());
	}

	@ExternalizedProperty
	public List<DataImportSummaryItemProxy> getConflictingRecords() {
		return DataImportSummaryItemProxy.fromList(messageContextHolder, summary.getConflictingRecords());
	}

	
}
