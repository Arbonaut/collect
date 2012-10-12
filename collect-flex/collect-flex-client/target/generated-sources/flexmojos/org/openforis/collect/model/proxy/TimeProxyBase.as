/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (TimeProxy.as).
 */

package org.openforis.collect.model.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class TimeProxyBase implements IExternalizable, Proxy {

        private var _hour:Number;
        private var _minute:Number;

        [Bindable(event="unused")]
        public function get hour():Number {
            return _hour;
        }

        [Bindable(event="unused")]
        public function get minute():Number {
            return _minute;
        }

        public function readExternal(input:IDataInput):void {
            _hour = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _minute = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_hour);
            output.writeObject(_minute);
        }
    }
}