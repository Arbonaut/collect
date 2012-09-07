/**
 * 
 */
package org.openforis.collect.designer.viewmodel;

import org.openforis.collect.designer.form.ItemFormObject;
import org.openforis.collect.designer.form.ModelVersionFormObject;
import org.openforis.idm.metamodel.ModelVersion;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.databind.BindingListModelList;

/**
 * 
 * @author S. Ricci
 *
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SurveyVersioningEditVM extends SurveyItemEditVM<ModelVersion> {
	
	private static final String VERSIONS_UPDATED_GLOBAL_COMMAND = "versionsUpdated";

	@Override
	public BindingListModelList<ModelVersion> getItems() {
		return new BindingListModelList<ModelVersion>(survey.getVersions(), false);
	}
	
	@Override
	protected void addNewItemToSurvey() {
		survey.addVersion(editedItem);
	}
	
	@Override
	protected void deleteItemFromSurvey(ModelVersion item) {
		survey.removeVersion(item);
	}

	@Override
	protected ItemFormObject<ModelVersion> createFormObject() {
		return new ModelVersionFormObject();
	}
	
	@Override
	@Command
	@NotifyChange({"editedItem","selectedItem"})
	public void applyChanges() {
		super.applyChanges();
		BindUtils.postGlobalCommand(null, null, VERSIONS_UPDATED_GLOBAL_COMMAND, null);
	}

}
