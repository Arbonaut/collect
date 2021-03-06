/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (DateProxy.as).
 */

package org.openforis.collect.model.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class DateProxyBase implements IExternalizable, Proxy {

        private var _day:Number;
        private var _month:Number;
        private var _year:Number;

        [Bindable(event="unused")]
        public function get day():Number {
            return _day;
        }

        [Bindable(event="unused")]
        public function get month():Number {
            return _month;
        }

        [Bindable(event="unused")]
        public function get year():Number {
            return _year;
        }

        public function readExternal(input:IDataInput):void {
            _day = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _month = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _year = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_day);
            output.writeObject(_month);
            output.writeObject(_year);
        }
    }
}