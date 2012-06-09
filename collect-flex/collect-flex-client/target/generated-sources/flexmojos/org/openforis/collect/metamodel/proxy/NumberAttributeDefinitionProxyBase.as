/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (NumberAttributeDefinitionProxy.as).
 */

package org.openforis.collect.metamodel.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import mx.collections.ListCollectionView;
    import org.granite.util.Enum;

    [Bindable]
    public class NumberAttributeDefinitionProxyBase extends AttributeDefinitionProxy {

        private var _integer:Boolean;
        private var _precisionDefinitions:ListCollectionView;
        private var _real:Boolean;
        private var _type:NumberAttributeDefinitionProxy$Type;

        [Bindable(event="unused")]
        public function get integer():Boolean {
            return _integer;
        }

        [Bindable(event="unused")]
        public function get precisionDefinitions():ListCollectionView {
            return _precisionDefinitions;
        }

        [Bindable(event="unused")]
        public function get real():Boolean {
            return _real;
        }

        [Bindable(event="unused")]
        public function get type():NumberAttributeDefinitionProxy$Type {
            return _type;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _integer = input.readObject() as Boolean;
            _precisionDefinitions = input.readObject() as ListCollectionView;
            _real = input.readObject() as Boolean;
            _type = Enum.readEnum(input) as NumberAttributeDefinitionProxy$Type;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_integer);
            output.writeObject(_precisionDefinitions);
            output.writeObject(_real);
            output.writeObject(_type);
        }
    }
}