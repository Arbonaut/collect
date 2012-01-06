/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (Check.as).
 */

package org.openforis.idm.metamodel {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import mx.collections.ListCollectionView;
    import org.granite.util.Enum;

    [Bindable]
    public class CheckBase implements IExternalizable {

        private var _condition:String;
        protected var _flag:Check$Flag;
        protected var _messages:ListCollectionView;

        [Bindable(event="unused")]
        public function get condition():String {
            return _condition;
        }

        [Bindable(event="unused")]
        public function get flag():Check$Flag {
            return _flag;
        }

        [Bindable(event="unused")]
        public function get messages():ListCollectionView {
            return _messages;
        }

        public function readExternal(input:IDataInput):void {
            _condition = input.readObject() as String;
            _flag = Enum.readEnum(input) as Check$Flag;
            _messages = input.readObject() as ListCollectionView;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_condition);
            output.writeObject(_flag);
            output.writeObject(_messages);
        }
    }
}