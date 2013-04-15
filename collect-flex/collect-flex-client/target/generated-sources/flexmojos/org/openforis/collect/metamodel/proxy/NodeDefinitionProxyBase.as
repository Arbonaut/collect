/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (NodeDefinitionProxy.as).
 */

package org.openforis.collect.metamodel.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import mx.collections.ListCollectionView;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class NodeDefinitionProxyBase implements IExternalizable, Proxy {

        private var _deprecatedVersion:ModelVersionProxy;
        private var _descriptions:ListCollectionView;
        private var _id:Number;
        private var _labels:ListCollectionView;
        private var _maxCount:Number;
        private var _minCount:Number;
        private var _multiple:Boolean;
        private var _name:String;
        protected var _parent:EntityDefinitionProxy;
        private var _path:String;
        private var _prompts:ListCollectionView;
        private var _relevantExpression:String;
        private var _requiredExpression:String;
        private var _sinceVersion:ModelVersionProxy;
        private var _uiTabName:String;

        [Bindable(event="unused")]
        public function get deprecatedVersion():ModelVersionProxy {
            return _deprecatedVersion;
        }

        [Bindable(event="unused")]
        public function get descriptions():ListCollectionView {
            return _descriptions;
        }

        [Bindable(event="unused")]
        public function get id():Number {
            return _id;
        }

        [Bindable(event="unused")]
        public function get labels():ListCollectionView {
            return _labels;
        }

        [Bindable(event="unused")]
        public function get maxCount():Number {
            return _maxCount;
        }

        [Bindable(event="unused")]
        public function get minCount():Number {
            return _minCount;
        }

        [Bindable(event="unused")]
        public function get multiple():Boolean {
            return _multiple;
        }

        [Bindable(event="unused")]
        public function get name():String {
            return _name;
        }

        [Bindable(event="unused")]
        public function get parent():EntityDefinitionProxy {
            return _parent;
        }

        [Bindable(event="unused")]
        public function get path():String {
            return _path;
        }

        [Bindable(event="unused")]
        public function get prompts():ListCollectionView {
            return _prompts;
        }

        [Bindable(event="unused")]
        public function get relevantExpression():String {
            return _relevantExpression;
        }

        [Bindable(event="unused")]
        public function get requiredExpression():String {
            return _requiredExpression;
        }

        [Bindable(event="unused")]
        public function get sinceVersion():ModelVersionProxy {
            return _sinceVersion;
        }

        [Bindable(event="unused")]
        public function get uiTabName():String {
            return _uiTabName;
        }

        public function readExternal(input:IDataInput):void {
            _deprecatedVersion = input.readObject() as ModelVersionProxy;
            _descriptions = input.readObject() as ListCollectionView;
            _id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _labels = input.readObject() as ListCollectionView;
            _maxCount = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _minCount = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _multiple = input.readObject() as Boolean;
            _name = input.readObject() as String;
            _parent = input.readObject() as EntityDefinitionProxy;
            _path = input.readObject() as String;
            _prompts = input.readObject() as ListCollectionView;
            _relevantExpression = input.readObject() as String;
            _requiredExpression = input.readObject() as String;
            _sinceVersion = input.readObject() as ModelVersionProxy;
            _uiTabName = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_deprecatedVersion);
            output.writeObject(_descriptions);
            output.writeObject(_id);
            output.writeObject(_labels);
            output.writeObject(_maxCount);
            output.writeObject(_minCount);
            output.writeObject(_multiple);
            output.writeObject(_name);
            output.writeObject(_parent);
            output.writeObject(_path);
            output.writeObject(_prompts);
            output.writeObject(_relevantExpression);
            output.writeObject(_requiredExpression);
            output.writeObject(_sinceVersion);
            output.writeObject(_uiTabName);
        }
    }
}