/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (TaxonVernacularNameProxy.as).
 */

package org.openforis.collect.model.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class TaxonVernacularNameProxyBase implements IExternalizable, Proxy {

        private var _id:Number;
        private var _languageCode:String;
        private var _languageVariety:String;
        private var _step:int;
        private var _taxonId:Number;
        private var _vernacularName:String;

        [Bindable(event="unused")]
        public function get id():Number {
            return _id;
        }

        [Bindable(event="unused")]
        public function get languageCode():String {
            return _languageCode;
        }

        [Bindable(event="unused")]
        public function get languageVariety():String {
            return _languageVariety;
        }

        [Bindable(event="unused")]
        public function get step():int {
            return _step;
        }

        [Bindable(event="unused")]
        public function get taxonId():Number {
            return _taxonId;
        }

        [Bindable(event="unused")]
        public function get vernacularName():String {
            return _vernacularName;
        }

        public function readExternal(input:IDataInput):void {
            _id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _languageCode = input.readObject() as String;
            _languageVariety = input.readObject() as String;
            _step = input.readObject() as int;
            _taxonId = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _vernacularName = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_id);
            output.writeObject(_languageCode);
            output.writeObject(_languageVariety);
            output.writeObject(_step);
            output.writeObject(_taxonId);
            output.writeObject(_vernacularName);
        }
    }
}