package org.openforis.collect.persistence.xml;

import javax.xml.bind.annotation.XmlRootElement;

import org.openforis.collect.model.CollectSurvey;
import org.openforis.idm.metamodel.SurveyContext;
import org.openforis.idm.metamodel.xml.IdmlBindingContext;

/**
 * @author G. Miceli
 */
@XmlRootElement(name = "survey")
public class CollectIdmlBindingContext extends IdmlBindingContext {
	
	public CollectIdmlBindingContext(SurveyContext surveyContext) {
		super(CollectSurvey.class, surveyContext, CollectConfigurationWrapperConverter.class);
//		UIConfigurationAdapter configurationAdapter = new UIConfigurationAdapter();
//		super.setConfigurationAdapter(configurationAdapter);
	}

//	@Override
//	public void setConfigurationAdapter(ConfigurationAdapter<? extends Configuration> configurationAdapter) {
//		throw new UnsupportedOperationException();
//	}

}
