/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (RecordSummarySortField.as).
 */

package org.openforis.collect.model {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.granite.util.Enum;

    [Bindable]
    public class RecordSummarySortFieldBase implements IExternalizable {

        private var _descending:Boolean;
        private var _field:RecordSummarySortField$Sortable;

        public function set descending(value:Boolean):void {
            _descending = value;
        }
        public function get descending():Boolean {
            return _descending;
        }

        public function set field(value:RecordSummarySortField$Sortable):void {
            _field = value;
        }
        public function get field():RecordSummarySortField$Sortable {
            return _field;
        }

        public function readExternal(input:IDataInput):void {
            _descending = input.readObject() as Boolean;
            _field = Enum.readEnum(input) as RecordSummarySortField$Sortable;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_descending);
            output.writeObject(_field);
        }
    }
}