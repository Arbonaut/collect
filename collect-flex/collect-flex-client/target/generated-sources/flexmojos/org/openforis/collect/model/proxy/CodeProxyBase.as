/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (CodeProxy.as).
 */

package org.openforis.collect.model.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class CodeProxyBase implements IExternalizable, Proxy {

        private var _code:String;
        private var _qualifier:String;

        [Bindable(event="unused")]
        public function get code():String {
            return _code;
        }

        [Bindable(event="unused")]
        public function get qualifier():String {
            return _qualifier;
        }

        public function readExternal(input:IDataInput):void {
            _code = input.readObject() as String;
            _qualifier = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_code);
            output.writeObject(_qualifier);
        }
    }
}