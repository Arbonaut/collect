/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (BooleanAttributeDefinitionProxy.as).
 */

package org.openforis.collect.metamodel.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class BooleanAttributeDefinitionProxyBase extends AttributeDefinitionProxy {

        private var _affirmativeOnly:Boolean;

        [Bindable(event="unused")]
        public function get affirmativeOnly():Boolean {
            return _affirmativeOnly;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _affirmativeOnly = input.readObject() as Boolean;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_affirmativeOnly);
        }
    }
}