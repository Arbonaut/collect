/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package org.openforis.collect.metamodel.proxy {
	import org.openforis.collect.Application;

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.metamodel.proxy.UITabProxy")]
    public class UITabProxy extends UITabProxyBase {
		
		public function get labelText():String {
			var langCode:String = Application.localeLanguageCode;
			var defaultLanguage:Boolean = Application.activeSurvey.defaultLanguageCode == langCode;
			var result:String = LanguageSpecificTextProxy.getLocalizedText(this.labels, langCode, defaultLanguage);
			return result;
		}
		
		public function hasChildTab(name:String):Boolean {
			if(this.tabs == null || this.tabs.length ==0){
				return false;
			} else {
				for each (var tab:UITabProxy in this.tabs) {
					if(tab.name == name){
						return true;
					}
				}
			}
			return false;
		}
    }
}