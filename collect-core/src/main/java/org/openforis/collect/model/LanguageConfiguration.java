/**
 * 
 */
package org.openforis.collect.model;

import java.util.ArrayList;
import java.util.List;

import org.openforis.idm.metamodel.Configuration;
import org.openforis.idm.metamodel.Languages;
import org.openforis.idm.util.CollectionUtil;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Root;

/**
 * @author S. Ricci
 *
 */
@Order(elements = { "languageCode" })
@Root(name = "languages")
public class LanguageConfiguration implements Configuration {

	@ElementList(entry = "languageCode", inline = true)
	private List<String> languageCodes;

	public List<String> getLanguageCodes() {
		return CollectionUtil.unmodifiableList(languageCodes);
	}
	
	public void addLanguageCode(String code) {
		if ( Languages.contains(code) ) { 
			if ( languageCodes == null ) {
				languageCodes = new ArrayList<String>();
			}
			if ( ! languageCodes.contains(code) ) {
				languageCodes.add(code);
			}
		} else {
			throw new IllegalArgumentException("Unsupported language code: " + code);
		}
	}
	
	public void removeLanguageCode(String code) {
		languageCodes.remove(code);
	}

	public void addLanguageCodes(List<String> codes) {
		for (String code : codes) {
			addLanguageCode(code);
		}
	}
	
}
