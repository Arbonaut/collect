package org.openforis.collect.persistence.xml;

import org.openforis.collect.model.CollectSurvey;
import org.openforis.idm.metamodel.Configuration;
import org.openforis.idm.metamodel.SurveyContext;
import org.openforis.idm.metamodel.xml.ConfigurationAdapter;
import org.openforis.idm.metamodel.xml.IdmlBindingContext;

/**
 * @author G. Miceli
 * @author K. Waga
 */

public class CollectIdmlBindingContext extends IdmlBindingContext {
	
	public CollectIdmlBindingContext(SurveyContext surveyContext) {
		super(CollectSurvey.class, surveyContext);
		ConfigurationAdapter<Configuration> configurationAdapter = new CollectConfigurationAdapter();
		super.setConfigurationAdapter(configurationAdapter);
	}

//	@Override
//	public void setConfigurationAdapter(ConfigurationAdapter<? extends Configuration> configurationAdapter) {
//		throw new UnsupportedOperationException();
//	}

}
