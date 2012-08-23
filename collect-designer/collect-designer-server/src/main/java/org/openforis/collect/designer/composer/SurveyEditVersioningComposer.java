package org.openforis.collect.designer.composer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openforis.collect.designer.viewmodel.SurveyEditVM;
import org.openforis.collect.manager.SurveyManager;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.idm.metamodel.ModelVersion;
import org.zkoss.bind.BindComposer;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.Textbox;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class SurveyEditVersioningComposer extends BindComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	@Wire
	Textbox versionLabelTextBox;
	
	@Listen("onChange=#versionLabelTextBox")
    public void onChangeVersionLabel() {
		ModelVersion each = (ModelVersion) versionLabelTextBox.getAttribute("each");
		each.setLabel(((SurveyEditVM) getViewModel()).getSelectedLanguageCode(), versionLabelTextBox.getValue());
//        output.setValue(input.getValue());
    }
	
}
