/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package org.openforis.collect.model.proxy {
	import org.openforis.collect.util.CollectionUtil;

	/**
	 * @author S. Ricci
	 * */
    [Bindable]
    [RemoteClass(alias="org.openforis.collect.model.proxy.AttributeProxy")]
    public class AttributeProxy extends AttributeProxyBase {
		
		public function getField(index:int):FieldProxy {
			if(index < 0) {
				index = 0;
			}
			return fields.getItemAt(index) as FieldProxy;
		}
		
		override public function hasErrors():Boolean {
			return validationResults != null && CollectionUtil.isNotEmpty(validationResults.errors);
		}
		
		public function hasWarnings():Boolean {
			return validationResults != null && CollectionUtil.isNotEmpty(validationResults.warnings);
		}
		
		override public function get empty():Boolean {
			for each (var field:FieldProxy in fields) {
				if ( field.hasValue() ) {
					return false;
				}
			}
			return true;
		}
		
		/**
		 * Returns true if and only if each field contains a value
		 */
		public function get filled():Boolean {
			for each (var field:FieldProxy in fields) {
				if ( ! field.hasValue() ) {
					return false;
				}
			}
			return true;
		}
		
		/**
		 * Returns true if there is a field with no value or reason blank specified.
		 */
		public function hasBlankField():Boolean {
			for each (var field:FieldProxy in fields) {
				if ( ! ( field.hasValue() || field.hasReasonBlankSpecified() ) ) {
					return false;
				}
			}
			return true;
		}
		
    }
}