/**
 * Generated by Gas3 v2.3.0 (Granite Data Services).
 *
 * WARNING: DO NOT CHANGE THIS FILE. IT MAY BE OVERWRITTEN EACH TIME YOU USE
 * THE GENERATOR. INSTEAD, EDIT THE INHERITED CLASS (ValidationResultsProxy.as).
 */

package org.openforis.collect.model.proxy {

    import flash.utils.IDataInput;
    import flash.utils.IDataOutput;
    import flash.utils.IExternalizable;
    import mx.collections.ListCollectionView;
    import org.openforis.collect.Proxy;

    [Bindable]
    public class ValidationResultsProxyBase implements IExternalizable, Proxy {

        private var _errors:ListCollectionView;
        private var _warnings:ListCollectionView;

        [Bindable(event="unused")]
        public function get errors():ListCollectionView {
            return _errors;
        }

        [Bindable(event="unused")]
        public function get warnings():ListCollectionView {
            return _warnings;
        }

        public function readExternal(input:IDataInput):void {
            _errors = input.readObject() as ListCollectionView;
            _warnings = input.readObject() as ListCollectionView;
        }

        public function writeExternal(output:IDataOutput):void {
            output.writeObject(_errors);
            output.writeObject(_warnings);
        }
    }
}