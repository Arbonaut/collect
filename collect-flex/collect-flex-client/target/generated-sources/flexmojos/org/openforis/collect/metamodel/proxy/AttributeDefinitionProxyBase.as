/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (AttributeDefinitionProxy.as).
 */

package org.openforis.collect.metamodel.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class AttributeDefinitionProxyBase extends NodeDefinitionProxy implements Proxy {

        private var _defaultValueApplicable:Boolean;
        private var _key:Boolean;

        [Bindable(event="unused")]
        public function get defaultValueApplicable():Boolean {
            return _defaultValueApplicable;
        }

        [Bindable(event="unused")]
        public function get key():Boolean {
            return _key;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _defaultValueApplicable = input.readObject() as Boolean;
            _key = input.readObject() as Boolean;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_defaultValueApplicable);
            output.writeObject(_key);
        }
    }
}