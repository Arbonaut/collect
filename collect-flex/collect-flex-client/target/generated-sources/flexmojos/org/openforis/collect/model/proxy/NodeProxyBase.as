/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (NodeProxy.as).
 */

package org.openforis.collect.model.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class NodeProxyBase implements IExternalizable, Proxy {

        private var _definitionId:Number;
        private var _id:Number;
        private var _name:String;
        private var _parent:EntityProxy;
        private var _parentId:Number;

        [Bindable(event="unused")]
        public function get definitionId():Number {
            return _definitionId;
        }

        [Bindable(event="unused")]
        public function get id():Number {
            return _id;
        }

        [Bindable(event="unused")]
        public function get name():String {
            return _name;
        }

        public function set parent(value:EntityProxy):void {
            _parent = value;
        }
        public function get parent():EntityProxy {
            return _parent;
        }

        [Bindable(event="unused")]
        public function get parentId():Number {
            return _parentId;
        }

        public function readExternal(input:IDataInput):void {
            _definitionId = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _name = input.readObject() as String;
            _parent = input.readObject() as EntityProxy;
            _parentId = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_definitionId);
            output.writeObject(_id);
            output.writeObject(_name);
            output.writeObject(_parent);
            output.writeObject(_parentId);
        }
    }
}