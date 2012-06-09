/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (RecordProxy.as).
 */

package org.openforis.collect.model.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import mx.collections.ListCollectionView;
    import org.granite.util.Enum;
    import org.openforis.collect.Proxy;
    import org.openforis.collect.metamodel.proxy.ModelVersionProxy;
    import org.openforis.collect.model.CollectRecord$State;
    import org.openforis.collect.model.CollectRecord$Step;

    [Bindable]
    public class RecordProxyBase implements IExternalizable, Proxy {

        private var _cleansingComplete:Boolean;
        private var _createdBy:UserProxy;
        private var _creationDate:Date;
        private var _entityCounts:ListCollectionView;
        private var _entryComplete:Boolean;
        private var _errors:Number;
        private var _id:Number;
        private var _missing:Number;
        private var _missingErrors:Number;
        private var _missingWarnings:Number;
        private var _modifiedBy:UserProxy;
        private var _modifiedDate:Date;
        private var _rootEntity:EntityProxy;
        private var _rootEntityKeys:ListCollectionView;
        private var _skipped:Number;
        private var _state:CollectRecord$State;
        private var _step:CollectRecord$Step;
        private var _version:ModelVersionProxy;
        private var _warnings:Number;

        [Bindable(event="unused")]
        public function get cleansingComplete():Boolean {
            return _cleansingComplete;
        }

        [Bindable(event="unused")]
        public function get createdBy():UserProxy {
            return _createdBy;
        }

        [Bindable(event="unused")]
        public function get creationDate():Date {
            return _creationDate;
        }

        [Bindable(event="unused")]
        public function get entityCounts():ListCollectionView {
            return _entityCounts;
        }

        [Bindable(event="unused")]
        public function get entryComplete():Boolean {
            return _entryComplete;
        }

        public function set errors(value:Number):void {
            _errors = value;
        }
        public function get errors():Number {
            return _errors;
        }

        [Bindable(event="unused")]
        public function get id():Number {
            return _id;
        }

        public function set missing(value:Number):void {
            _missing = value;
        }
        public function get missing():Number {
            return _missing;
        }

        public function set missingErrors(value:Number):void {
            _missingErrors = value;
        }
        public function get missingErrors():Number {
            return _missingErrors;
        }

        public function set missingWarnings(value:Number):void {
            _missingWarnings = value;
        }
        public function get missingWarnings():Number {
            return _missingWarnings;
        }

        [Bindable(event="unused")]
        public function get modifiedBy():UserProxy {
            return _modifiedBy;
        }

        [Bindable(event="unused")]
        public function get modifiedDate():Date {
            return _modifiedDate;
        }

        [Bindable(event="unused")]
        public function get rootEntity():EntityProxy {
            return _rootEntity;
        }

        [Bindable(event="unused")]
        public function get rootEntityKeys():ListCollectionView {
            return _rootEntityKeys;
        }

        public function set skipped(value:Number):void {
            _skipped = value;
        }
        public function get skipped():Number {
            return _skipped;
        }

        [Bindable(event="unused")]
        public function get state():CollectRecord$State {
            return _state;
        }

        [Bindable(event="unused")]
        public function get step():CollectRecord$Step {
            return _step;
        }

        [Bindable(event="unused")]
        public function get version():ModelVersionProxy {
            return _version;
        }

        public function set warnings(value:Number):void {
            _warnings = value;
        }
        public function get warnings():Number {
            return _warnings;
        }

        public function readExternal(input:IDataInput):void {
            _cleansingComplete = input.readObject() as Boolean;
            _createdBy = input.readObject() as UserProxy;
            _creationDate = input.readObject() as Date;
            _entityCounts = input.readObject() as ListCollectionView;
            _entryComplete = input.readObject() as Boolean;
            _errors = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _id = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _missing = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _missingErrors = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _missingWarnings = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _modifiedBy = input.readObject() as UserProxy;
            _modifiedDate = input.readObject() as Date;
            _rootEntity = input.readObject() as EntityProxy;
            _rootEntityKeys = input.readObject() as ListCollectionView;
            _skipped = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _state = Enum.readEnum(input) as CollectRecord$State;
            _step = Enum.readEnum(input) as CollectRecord$Step;
            _version = input.readObject() as ModelVersionProxy;
            _warnings = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_cleansingComplete);
            output.writeObject(_createdBy);
            output.writeObject(_creationDate);
            output.writeObject(_entityCounts);
            output.writeObject(_entryComplete);
            output.writeObject(_errors);
            output.writeObject(_id);
            output.writeObject(_missing);
            output.writeObject(_missingErrors);
            output.writeObject(_missingWarnings);
            output.writeObject(_modifiedBy);
            output.writeObject(_modifiedDate);
            output.writeObject(_rootEntity);
            output.writeObject(_rootEntityKeys);
            output.writeObject(_skipped);
            output.writeObject(_state);
            output.writeObject(_step);
            output.writeObject(_version);
            output.writeObject(_warnings);
        }
    }
}