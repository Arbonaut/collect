package org.openforis.collect.persistence.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.openforis.collect.model.LanguageConfiguration;
import org.openforis.collect.model.ui.UIConfiguration;
import org.openforis.collect.model.ui.UITabDefinition;
import org.openforis.idm.metamodel.Configuration;
import org.openforis.idm.metamodel.ConfigurationWrapper;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

/**
 * @author S. Ricci
 */
public class ConfigurationWrapperConverterTest {
	//private final Log log = LogFactory.getLog(ConfigurationDaoIntegrationTest.class);
	
	//@Test
	public void test() throws Exception  {
		Registry registry = new Registry();
		Strategy strategy = new RegistryStrategy(registry);
		registry.bind(ConfigurationWrapper.class, CollectConfigurationWrapperConverter.class);
		Persister persister = new Persister(strategy);
		
		URL testXmlFileUrl = ClassLoader.getSystemResource("test-configuration-wrapper.xml");
		InputStream is = testXmlFileUrl.openStream();
		SimpleObject simpleObject = persister.read(SimpleObject.class, is, false);
		assertNotNull(simpleObject);
		ConfigurationWrapper configurationWrapper = simpleObject.getConfiguration();
		assertNotNull(configurationWrapper);
		List<Configuration> configurations = configurationWrapper.getConfigurations();
		assertEquals(2, configurations.size());
		for (Configuration configuration : configurations) {
			if ( configuration instanceof UIConfiguration ) {
				UITabDefinition tabDefinition = ((UIConfiguration) configuration).getTabDefinition("cluster");
				assertNotNull(tabDefinition);
			} else if ( configuration instanceof LanguageConfiguration ) {
				List<String> languageCodes = ((LanguageConfiguration) configuration).getLanguageCodes();
				assertNotNull(languageCodes);
				assertEquals(3, languageCodes.size());
				assertEquals("eng", languageCodes.get(0));
				assertEquals("spa", languageCodes.get(1));
				assertEquals("fra", languageCodes.get(2));
			} else {
				fail("Unexpected configuration type: " + configuration.getClass().getSimpleName());
			}
		}
	}
	
	public static class SimpleObject {
		
		@Element
		@Convert(CollectConfigurationWrapperConverter.class)
		private ConfigurationWrapper configuration;
		
		public ConfigurationWrapper getConfiguration() {
			return configuration;
		}
	}

}
