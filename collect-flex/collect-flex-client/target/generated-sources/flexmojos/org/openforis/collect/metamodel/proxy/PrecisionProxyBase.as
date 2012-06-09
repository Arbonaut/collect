/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (PrecisionProxy.as).
 */

package org.openforis.collect.metamodel.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class PrecisionProxyBase implements IExternalizable, Proxy {

        private var _decimalDigits:Number;
        private var _defaultPrecision:Boolean;
        private var _unit:UnitProxy;
        private var _unitName:String;

        [Bindable(event="unused")]
        public function get decimalDigits():Number {
            return _decimalDigits;
        }

        [Bindable(event="unused")]
        public function get defaultPrecision():Boolean {
            return _defaultPrecision;
        }

        [Bindable(event="unused")]
        public function get unit():UnitProxy {
            return _unit;
        }

        [Bindable(event="unused")]
        public function get unitName():String {
            return _unitName;
        }

        public function readExternal(input:IDataInput):void {
            _decimalDigits = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _defaultPrecision = input.readObject() as Boolean;
            _unit = input.readObject() as UnitProxy;
            _unitName = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_decimalDigits);
            output.writeObject(_defaultPrecision);
            output.writeObject(_unit);
            output.writeObject(_unitName);
        }
    }
}