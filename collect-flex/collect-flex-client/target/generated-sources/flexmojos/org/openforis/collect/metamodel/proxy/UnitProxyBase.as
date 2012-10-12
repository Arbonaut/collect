/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (UnitProxy.as).
 */

package org.openforis.collect.metamodel.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import mx.collections.ListCollectionView;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class UnitProxyBase implements IExternalizable, Proxy {

        private var _abbreviations:ListCollectionView;
        private var _conversionFactor:Number;
        private var _dimension:String;
        private var _labels:ListCollectionView;
        private var _name:String;

        [Bindable(event="unused")]
        public function get abbreviations():ListCollectionView {
            return _abbreviations;
        }

        [Bindable(event="unused")]
        public function get conversionFactor():Number {
            return _conversionFactor;
        }

        [Bindable(event="unused")]
        public function get dimension():String {
            return _dimension;
        }

        [Bindable(event="unused")]
        public function get labels():ListCollectionView {
            return _labels;
        }

        [Bindable(event="unused")]
        public function get name():String {
            return _name;
        }

        public function readExternal(input:IDataInput):void {
            _abbreviations = input.readObject() as ListCollectionView;
            _conversionFactor = function(o:*):Number { return (o is Number ? o as Number : Number.NaN) } (input.readObject());
            _dimension = input.readObject() as String;
            _labels = input.readObject() as ListCollectionView;
            _name = input.readObject() as String;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_abbreviations);
            output.writeObject(_conversionFactor);
            output.writeObject(_dimension);
            output.writeObject(_labels);
            output.writeObject(_name);
        }
    }
}