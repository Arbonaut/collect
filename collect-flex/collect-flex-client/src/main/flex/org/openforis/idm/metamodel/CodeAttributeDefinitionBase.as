/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (CodeAttributeDefinition.as).
 */

package org.openforis.idm.metamodel {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;

    [Bindable]
    public class CodeAttributeDefinitionBase extends AttributeDefinition {

        private var _allowUnlisted:Boolean;
        private var _key:Boolean;
        private var _list:CodeList;
        private var _parentExpression:String;

        [Bindable(event="unused")]
        public function get allowUnlisted():Boolean {
            return _allowUnlisted;
        }

        [Bindable(event="unused")]
        public function get key():Boolean {
            return _key;
        }

        [Bindable(event="unused")]
        public function get list():CodeList {
            return _list;
        }

        [Bindable(event="unused")]
        public function get parentExpression():String {
            return _parentExpression;
        }

        override public function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _allowUnlisted = input.readObject() as Boolean;
            _key = input.readObject() as Boolean;
            _list = input.readObject() as CodeList;
            _parentExpression = input.readObject() as String;
        }

        override public function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_allowUnlisted);
            output.writeObject(_key);
            output.writeObject(_list);
            output.writeObject(_parentExpression);
        }
    }
}