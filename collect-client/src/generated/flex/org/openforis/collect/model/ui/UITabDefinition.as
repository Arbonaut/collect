/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package org.openforis.collect.model.ui {
	import mx.collections.IList;
	
	import org.openforis.collect.util.CollectionUtil;

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.model.ui.UITabDefinition")]
    public class UITabDefinition extends UITabDefinitionBase {
		
		public function getTab(name:String):UITab {
			var stack:Array = new Array();
			stack.push(tabs);
			while (stack.length > 0) {
				var tabs:IList = stack.pop();
				for each(var tab:UITab in tabs) {
					if(tab.name == name) {
						return tab;
					}
					if ( CollectionUtil.isNotEmpty(tab.tabs) ) {
						stack.push(tab.tabs);
					}
				}
			}
			return null;
		}
    }
}