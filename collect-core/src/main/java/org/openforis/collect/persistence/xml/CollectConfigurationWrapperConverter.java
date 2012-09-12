/**
 * 
 */
package org.openforis.collect.persistence.xml;

import java.util.List;

import org.openforis.collect.model.LanguageConfiguration;
import org.openforis.collect.model.ui.UIConfiguration;
import org.openforis.idm.metamodel.Configuration;
import org.openforis.idm.metamodel.ConfigurationWrapper;
import org.openforis.idm.metamodel.xml.ConfigurationWrapperConverter;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

/**
 * @author S. Ricci
 *
 */
@Deprecated
public class CollectConfigurationWrapperConverter implements
		ConfigurationWrapperConverter<ConfigurationWrapper> {

	private static final String LANGUAGES_ELEMENT_NAME = "languages";
	private static final String FLEX_ELEMENT_NAME = "flex";

	@Override
	public ConfigurationWrapper read(InputNode node) throws Exception {
		ConfigurationWrapper result = new ConfigurationWrapper();
		Persister persister = new Persister();
		if ( node != null ) {
			InputNode childNode = node.getNext();
			while ( childNode != null ) {
				readChildNode(persister, result, childNode);
				childNode = node.getNext();
			}
		}
		return result;
	}

	private void readChildNode(Persister persister, ConfigurationWrapper result, InputNode node) throws Exception {
		if ( node != null ) {
			String nodeName = node.getName();
			if ( FLEX_ELEMENT_NAME.equals(nodeName) ) {
				UIConfiguration conf = persister.read(UIConfiguration.class, node);
				result.addConfiguration(conf);
			} else if ( LANGUAGES_ELEMENT_NAME.equals(nodeName) ) {
				LanguageConfiguration conf = persister.read(LanguageConfiguration.class, node);
				result.addConfiguration(conf);
			}
		}
	}
	
	@Override
	public void write(OutputNode node, ConfigurationWrapper value)
			throws Exception {
		Persister persister = new Persister();
		List<Configuration> configurations = value.getConfigurations();
		for (Configuration configuration : configurations) {
			if ( configuration instanceof UIConfiguration ) {
				OutputNode child = node.getChild(FLEX_ELEMENT_NAME);
				
			}
		}
		
	}
}
