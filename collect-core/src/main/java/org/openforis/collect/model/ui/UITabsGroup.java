package org.openforis.collect.model.ui;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.openforis.idm.util.CollectionUtil;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Transient;

/**
 * 
 * @author S. Ricci
 *
 */
@Transient
public abstract class UITabsGroup implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Attribute(name = "name")
	protected String name;

	@ElementList(name = "tabs", entry = "tab", type = UITab.class, required = false)
	protected List<UITab> tabs;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<UITab> getTabs() {
		return CollectionUtil.unmodifiableList(tabs);
	}
	
	public UITab getTab(String name) {
		if ( tabs != null ) {
			for (UITab tab : tabs) {
				if ( tab.getName().equals(name) ) {
					return tab;
				}
			}
		}
		return null;
	}

	public void addTab(UITab tab) {
		if ( tabs == null ) {
			tabs = new ArrayList<UITab>();
		}
		tabs.add(tab);
	}
	
	public void setTab(int index, UITab tab) {
		tabs.set(index, tab);
	}
	
	public void removeTab(UITab tab) {
		tabs.remove(tab);
	}

	public UITab updateTab(String tabName, String newName, String newLabel) {
		UITab oldTab = getTab(tabName);
		oldTab.setName(newName);
		oldTab.setLabel(newLabel);
		return oldTab;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((tabs == null) ? 0 : tabs.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UITabsGroup other = (UITabsGroup) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (tabs == null) {
			if (other.tabs != null)
				return false;
		} else if (!tabs.equals(other.tabs))
			return false;
		return true;
	}

}
