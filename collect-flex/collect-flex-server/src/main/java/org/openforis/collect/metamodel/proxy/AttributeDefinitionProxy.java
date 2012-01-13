/**
 * 
 */
package org.openforis.collect.metamodel.proxy;

import java.util.List;

import org.granite.messaging.amf.io.util.externalizer.annotation.ExternalizedProperty;
import org.openforis.idm.metamodel.AttributeDefinition;
import org.openforis.idm.metamodel.KeyAttributeDefinition;

/**
 * @author M. Togna
 * 
 */
public class AttributeDefinitionProxy extends NodeDefinitionProxy implements ProxyBase {

	private transient AttributeDefinition attributeDefinition;

	public AttributeDefinitionProxy(AttributeDefinition attributeDefinition) {
		super(attributeDefinition);
		this.attributeDefinition = attributeDefinition;
	}

	public List<AttributeDefaultProxy> getAttributeDefaults() {
		return AttributeDefaultProxy.fromList(attributeDefinition.getAttributeDefaults());
	}
	
	@ExternalizedProperty
	public boolean isKey() {
		if(this.attributeDefinition instanceof KeyAttributeDefinition) {
			return ((KeyAttributeDefinition) this.attributeDefinition).isKey(); 
		} else {
			return false;
		}
	}

}
