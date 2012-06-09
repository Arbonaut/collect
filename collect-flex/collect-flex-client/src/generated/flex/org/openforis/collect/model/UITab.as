/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package org.openforis.collect.model {

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.model.UITab")]
    public class UITab extends UITabBase {
		
		public function hasChildTab(name:String):Boolean {
			if(this.tabs == null || this.tabs.length ==0){
				return false;
			} else {
				for each (var tab:UITab in this.tabs) {
					if(tab.name == name){
						return true;
					}
				}
				
			}
			return false;
		}
    }
	
}