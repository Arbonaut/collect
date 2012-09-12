package org.openforis.collect.persistence.xml;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openforis.idm.metamodel.ConfigurationWrapper;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Registry;
import org.simpleframework.xml.convert.RegistryStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author S. Ricci
 */
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( locations = {"classpath:test-context.xml"} )
@TransactionConfiguration(defaultRollback=true)
@Transactional
public class ConfigurationWrapperConverterTest {
	//private final Log log = LogFactory.getLog(ConfigurationDaoIntegrationTest.class);
	
	@Before
	public void before() throws Exception {
	}
	
	@Test
	public void test() throws Exception  {
		Registry registry = new Registry();
		Strategy strategy = new RegistryStrategy(registry);
		registry.bind(ConfigurationWrapper.class, CollectConfigurationWrapperConverter.class);
		Persister persister = new Persister(strategy);
		
		URL testXmlFileUrl = ClassLoader.getSystemResource("test-configuration-wrapper.xml");
		InputStream is = testXmlFileUrl.openStream();
		SimpleObject simpleObject = persister.read(SimpleObject.class, is, false);
		assertNotNull(simpleObject);
	}
	
	@After
	public void after() throws Exception {
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
