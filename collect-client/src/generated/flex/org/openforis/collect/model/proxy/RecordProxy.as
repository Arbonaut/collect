/**
 * Generated by Gas3 v2.2.0 (Granite Data Services).
 *
 * NOTE: this file is only generated if it does not exist. You may safely put
 * your custom code here.
 */

package org.openforis.collect.model.proxy {
	import flash.utils.Dictionary;
	
	import mx.collections.ArrayCollection;
	import mx.collections.IList;
	import mx.collections.ListCollectionView;
	
	import org.openforis.collect.event.ApplicationEvent;
	import org.openforis.collect.event.EventDispatcherFactory;
	import org.openforis.collect.metamodel.proxy.NodeDefinitionProxy;
	import org.openforis.collect.metamodel.proxy.SurveyProxy;
	import org.openforis.collect.remoting.service.UpdateRequest;
	import org.openforis.collect.remoting.service.UpdateRequestOperation;
	import org.openforis.collect.remoting.service.UpdateRequestOperation$Method;
	import org.openforis.collect.remoting.service.UpdateResponse;
	import org.openforis.collect.util.ArrayUtil;

    [Bindable]
    [RemoteClass(alias="org.openforis.collect.model.proxy.RecordProxy")]
    public class RecordProxy extends RecordProxyBase {
		
		private var _survey:SurveyProxy;
		private var _nodesMap:Dictionary;
		private var _updated:Boolean = false;
		
		private var validationResults:ValidationResultsProxy;

		public function RecordProxy():void {
			super();
		}
		
		public function init():void {
			_nodesMap = new Dictionary();
			traverse(associateDefinition);
			traverse(initNode);
		}
		
		/**
		 * Traverse all the record's nodes and execute the argument function passing
		 * the visited node to it
		 * */
		public function traverse(funct:Function):void {
			if ( rootEntity != null ) {
				funct(rootEntity);
				rootEntity.traverse(funct);
			}
		}
		
		protected function associateDefinition(node:NodeProxy):void {
			var defn:NodeDefinitionProxy = survey.schema.getDefinitionById(node.definitionId);
			node.definition = defn;
		}
		
		protected function initNode(node:NodeProxy):void {
			_nodesMap[node.id] = node;
			node.init();
		}
		
		public function getNode(id:int):NodeProxy {
			return _nodesMap[id];
		}
		
		public function update(responses:IList, req:UpdateRequest):void {
			updateNodes(req);
			
			for each (var response:UpdateResponse in responses)	{
				processResponse(response);
			}
			
			if ( responses != null && responses.length > 0 ) {
				var firstResp:UpdateResponse = UpdateResponse(responses.getItemAt(0));
				this.errors = firstResp.errors;
				this.skipped = firstResp.skipped;
				this.missing = firstResp.missing;
				this.missingErrors = firstResp.missingErrors;
				this.missingWarnings = firstResp.missingWarnings;
				this.warnings = firstResp.warnings;
			}
			
			_updated = true;
			
			var appEvt:ApplicationEvent = new ApplicationEvent(ApplicationEvent.UPDATE_RESPONSE_RECEIVED);
			appEvt.result = responses;
			EventDispatcherFactory.getEventDispatcher().dispatchEvent(appEvt);
		}
		
		private function updateNodes(req:UpdateRequest):void {
			var reqOperations:ListCollectionView = req.operations;
			var attr:AttributeProxy;
			var field:FieldProxy;
			for each (var reqOp:UpdateRequestOperation in reqOperations) {
				switch(reqOp.method) {
					case UpdateRequestOperation$Method.UPDATE_REMARKS:
						attr = getNode(reqOp.nodeId) as AttributeProxy;
						field = attr.getField(reqOp.fieldIndex);
						field.remarks = reqOp.remarks;
						break;
					case UpdateRequestOperation$Method.UPDATE:
						attr = getNode(reqOp.nodeId) as AttributeProxy;
						if(reqOp.fieldIndex >= 0) {
							field = attr.getField(reqOp.fieldIndex);
							field.symbol = reqOp.symbol;
							field.remarks = reqOp.remarks;
						} else {
							for each (field in attr.fields) {
								field.symbol = reqOp.symbol;
								field.remarks = reqOp.remarks;
							}
						}
						break;
					case UpdateRequestOperation$Method.APPLY_DEFAULT_VALUE:
						//nullify the symbol (if any)
						attr = getNode(reqOp.nodeId) as AttributeProxy;
						for each (field in attr.fields) {
							field.symbol = null;
						}
						break;
					case UpdateRequestOperation$Method.CONFIRM_ERROR:
						attr = getNode(reqOp.nodeId) as AttributeProxy;
						if ( attr != null ) {
							attr.errorConfirmed = true;
						}
						break;
				}
			}
		}
		
		private function processResponse(response:UpdateResponse):void {
			var node:NodeProxy, oldNode:NodeProxy, parent:EntityProxy;
			if(response.createdNode != null) {
				node = response.createdNode;
				associateDefinition(node);
				if ( node is EntityProxy ) {
					EntityProxy(node).traverse(associateDefinition);
				}
				parent = getNode(node.parentId) as EntityProxy;
				parent.addChild(node);
				initNode(node);
				if(node is EntityProxy) {
					EntityProxy(node).traverse(initNode);
				}
			}
			if(response.deletedNodeId > 0) {
				node = getNode(response.deletedNodeId);
				if(node != null) {
					parent = getNode(node.parentId) as EntityProxy;
					parent.removeChild(node);
					_nodesMap[node.id] = null;
				}
			} else {
				node = getNode(response.nodeId);
				if(node is AttributeProxy) {
					var a:AttributeProxy = AttributeProxy(node);
					if(response.validationResults != null) {
						a.validationResults = response.validationResults;
					}
					if(response.updatedFieldValues != null) {
						var fieldIdxs:ArrayCollection = response.updatedFieldValues.keySet;
						for each (var i:int in fieldIdxs) {
							var f:FieldProxy = a.getField(i);
							f.value = response.updatedFieldValues.get(i);
						}
						a.errorConfirmed = false;
						parent = getNode(node.parentId) as EntityProxy;
						parent.updateKeyText();
					}
				} else if(node is EntityProxy) {
					var e:EntityProxy = EntityProxy(node);
					if(response.maxCountValidation != null && response.maxCountValidation.length > 0) {
						e.updateChildrenMaxCountValiditationMap(response.maxCountValidation);
					}
					if(response.minCountValidation != null && response.minCountValidation.length > 0) {
						e.updateChildrenMinCountValiditationMap(response.minCountValidation);
					}
					if(response.relevant != null && response.relevant.length > 0) {
						e.updateChildrenRelevanceMap(response.relevant);
					}
					if(response.required != null && response.required.length > 0) {
						e.updateChildrenRequiredMap(response.required);
					}
				}
			}
		}
		
		public function showErrors():void {
			var stack:Array = new Array();
			stack.push(rootEntity);
			while ( stack.length > 0 ) {
				var entity:EntityProxy = stack.pop();
				var childDefinitionNames:IList = entity.childDefinitionNames;
				for each (var name:String in childDefinitionNames) {
					entity.showChildrenErrorsMap.put(name, true);
				}
				var childrenEntities:IList = entity.getChildEntities();
				ArrayUtil.addAll(stack, childrenEntities.toArray());
			}
		}
		
		public function get survey():SurveyProxy {
			return _survey;
		}
		
		public function set survey(value:SurveyProxy):void {
			_survey = value;
		}
		
		public function get updated():Boolean {
			return _updated;
		}
		
		public function set updated(value:Boolean):void {
			 _updated = value;
		}
    }
}