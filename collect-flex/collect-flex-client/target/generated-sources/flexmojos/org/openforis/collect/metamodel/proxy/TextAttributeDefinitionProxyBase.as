/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (TextAttributeDefinitionProxy.as).
 */

package org.openforis.collect.metamodel.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import org.granite.util.Enum;

    [Bindable]
    public class TextAttributeDefinitionProxyBase extends AttributeDefinitionProxy {

        private var _type:TextAttributeDefinitionProxy$Type;

        [Bindable(event="unused")]
        public function get type():TextAttributeDefinitionProxy$Type {
            return _type;
        }

        public override function readExternal(input:IDataInput):void {
            super.readExternal(input);
            _type = Enum.readEnum(input) as TextAttributeDefinitionProxy$Type;
        }

        public override function writeExternal(output:IDataOutput):void {
            super.writeExternal(output);
            output.writeObject(_type);
        }
    }
}