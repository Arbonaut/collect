package org.openforis.collect.persistence.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.openforis.collect.model.LanguageConfiguration;
import org.openforis.collect.model.ui.UIConfiguration;
import org.openforis.idm.metamodel.Configuration;
import org.openforis.idm.metamodel.xml.ConfigurationAdapter;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.TreeStrategy;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author S. Ricci
 *
 */
public class CollectConfigurationAdapter implements ConfigurationAdapter<Configuration> {

	private Persister persister;

	public CollectConfigurationAdapter() {
		TreeStrategy strategy = new TreeStrategy();
		persister = new Persister(strategy);
	}
	
	@Override
	public Configuration unmarshal(Element elem) {
		Configuration configuration = null;
		String nodeName = elem.getNodeName();
		InputStream is = toInputStream(elem);
		Class<? extends Configuration> configurationClass = null;
		if ( "flex".equals(nodeName) ) {
			configurationClass = UIConfiguration.class;
		} else if ( "languages".equals(nodeName) ) {
			configurationClass = LanguageConfiguration.class;
		}
		if ( configurationClass != null ) {
			try {
				configuration = persister.read(configurationClass, is);
			} catch (Exception e) {
				throw new RuntimeException("Cannot parse configuration: ");
			}
		}
		return configuration;
	}

	@Override
	public Element marshal(Configuration config) {
		Element result = null;
		StringWriter writer = new StringWriter();
		try {
			persister.write(config, writer);
			result = writerToElement(writer);
		} catch (Exception e) {
			throw new RuntimeException("Cannot marshall configuration", e);
		}
		return result;
	}

	private static Element writerToElement(Writer writer) {
		Element result;
		try {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder documentBuilder = dbf.newDocumentBuilder();
			String string = writer.toString();
			byte[] byteArray;
			byteArray = string.getBytes("UTF-8");
			ByteArrayInputStream is = new ByteArrayInputStream(byteArray);
			Document doc = documentBuilder.parse(is);
			result = doc.getDocumentElement();
		} catch (Exception e) {
			throw new RuntimeException("Cannot convert to Element", e);
		}  
		return result;
	}

	private static InputStream toInputStream(Element elem) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		Source xmlSource = new DOMSource(elem);
		Result outputTarget = new StreamResult(outputStream);
		try {
			TransformerFactory tranformerFactory = TransformerFactory.newInstance();
			Transformer transformer = tranformerFactory.newTransformer();
			transformer.transform(xmlSource, outputTarget);
		} catch (Exception e) {
			throw new RuntimeException("Cannot parse element", e);
		}
		byte[] byteArray = outputStream.toByteArray();
		InputStream is = new ByteArrayInputStream(byteArray);
		return is;
	}
	
}
