package org.openforis.collect.model.ui;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Order;
import org.simpleframework.xml.Transient;

/**
 * 
 * @author S. Ricci
 * 
 */

//@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(name = "", propOrder = { "name", "label", "tabs" })
@Order(attributes = {"name"}, elements = {"label","tabs"})
public class UITab extends UITabsGroup {

	private static final long serialVersionUID = 1L;

	@Element(name = "label")
	private String label;

	@Transient
	private UITabDefinition tabDefinition;
	
	public String getLabel() {
		return label;
	}
	
	public void setLabel(String label) {
		this.label = label;
	}
	
	public UITabDefinition getTabDefinition() {
		return tabDefinition;
	}

	public void setTabDefinition(UITabDefinition tabDefinition) {
		this.tabDefinition = tabDefinition;
		if ( tabs != null ) {
			for (UITab tab : tabs ) {
				tab.setTabDefinition(tabDefinition);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result
				+ ((tabDefinition == null) ? 0 : tabDefinition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		UITab other = (UITab) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (tabDefinition == null) {
			if (other.tabDefinition != null)
				return false;
		} else if (!tabDefinition.equals(other.tabDefinition))
			return false;
		return true;
	}

}
