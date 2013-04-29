package org.openforis.collect.designer.form.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.openforis.collect.manager.SurveyManager;
import org.openforis.collect.model.CollectSurvey;
import org.openforis.commons.collection.CollectionUtils;
import org.openforis.idm.metamodel.EntityDefinition;
import org.openforis.idm.metamodel.NodeDefinition;
import org.openforis.idm.metamodel.NodeDefinitionVisitor;
import org.openforis.idm.metamodel.NumericAttributeDefinition;
import org.openforis.idm.metamodel.Schema;
import org.zkoss.util.resource.Labels;

/**
 * 
 * @author S. Ricci
 *
 */
public class SurveyValidator {

	@SuppressWarnings("unused")
	private SurveyManager surveyManager;

	public SurveyValidator(SurveyManager surveyManager) {
		this.surveyManager = surveyManager;
	}
	
	public List<SurveyValidationResult> validateSurveyForPublishing(CollectSurvey oldPublishedSurvey, CollectSurvey newSurvey) {
		List<SurveyValidationResult> results = validateSurvey(newSurvey);
		if ( oldPublishedSurvey != null ) {
			results.addAll(validateChanges(oldPublishedSurvey, newSurvey));
		}
		return results;
	}
	
	public List<SurveyValidationResult> validateSurvey(CollectSurvey survey) {
		List<SurveyValidationResult> results = new ArrayList<SurveyValidator.SurveyValidationResult>();
		List<SurveyValidationResult> partialResults = validateEnties(survey);
		results.addAll(partialResults);
		partialResults = validateEnties(survey);
//		partialResults = validateExpressions(survey);
//		results.addAll(partialResults);
		return results;
	}
	
	public List<SurveyValidationResult> validateChanges(CollectSurvey oldPublishedSurvey, CollectSurvey newSurvey) {
		List<SurveyValidationResult> results = new ArrayList<SurveyValidator.SurveyValidationResult>();
		List<SurveyValidationResult> partialResults;
		partialResults = validateParentRelationship(oldPublishedSurvey, newSurvey);
		results.addAll(partialResults);
		partialResults = validateDataTypeNotChanged(oldPublishedSurvey, newSurvey);
		results.addAll(partialResults);
		return results;
	}
	
	/**
	 * Checks for the existence of empty entities
	 * 
	 * @param survey
	 * @return
	 */
	protected List<SurveyValidationResult> validateEnties(CollectSurvey survey) {
		List<SurveyValidationResult> results = new ArrayList<SurveyValidator.SurveyValidationResult>();
		Schema schema = survey.getSchema();
		Stack<EntityDefinition> entitiesStack = new Stack<EntityDefinition>();
		List<EntityDefinition> rootEntities = schema.getRootEntityDefinitions();
		entitiesStack.addAll(rootEntities);
		while ( ! entitiesStack.isEmpty() ) {
			EntityDefinition entity = entitiesStack.pop();
			List<NodeDefinition> childDefinitions = entity.getChildDefinitions();
			if ( childDefinitions.size() == 0 ) {
				String message = Labels.getLabel("survey.validation.error.empty_entity");
				String path = entity.getPath();
				SurveyValidationResult validationResult = new SurveyValidationResult(path, message);
				results.add(validationResult);
			} else {
				for (NodeDefinition childDefn : childDefinitions) {
					if ( childDefn instanceof EntityDefinition ) {
						entitiesStack.push((EntityDefinition) childDefn);
					}
				}
			}
		}
		return results;
	}
	
	/*
	protected List<SurveyValidationResult> validateExpressions(CollectSurvey survey) {
		List<SurveyValidationResult> results = new ArrayList<SurveyValidator.SurveyValidationResult>();
		Schema schema = survey.getSchema();
		Stack<NodeDefinition> nodesStack = new Stack<NodeDefinition>();
		List<EntityDefinition> rootEntities = schema.getRootEntityDefinitions();
		nodesStack.addAll(rootEntities);
		while ( ! nodesStack.isEmpty() ) {
			NodeDefinition node = nodesStack.pop();
			List<SurveyValidationResult> nodeValidationResults = validateExpressions(node);
			if ( ! nodeValidationResults.isEmpty() ) {
				results.addAll(nodeValidationResults);
			}
			if ( node instanceof EntityDefinition ) {
				List<NodeDefinition> childDefns = ((EntityDefinition) node).getChildDefinitions();
				if ( ! childDefns.isEmpty() ) {
					nodesStack.addAll(childDefns);
				}
			}
		}
		return results;
	}
	
	private List<SurveyValidationResult> validateExpressions(NodeDefinition node) {
		List<SurveyValidationResult> results = new ArrayList<SurveyValidator.SurveyValidationResult>();
		NodeDefinition parentDefn = node.getParentDefinition();
		String path = node.getPath();
		String expression = node.getRelevantExpression();
		if ( StringUtils.isNotBlank(expression) ) {
		}
		if ( node.getMinCount() > 0 ) {
			expression = node.getRequiredExpression();
			if ( StringUtils.isNotBlank(expression) ) {
			}
		}
		if ( node instanceof CodeAttributeDefinition ) {
			CodeAttributeDefinition codeDefn = (CodeAttributeDefinition) node;
			String expr = codeDefn.getParentExpression();
			if ( StringUtils.isNotBlank(expr) && ! surveyManager.validatePathExpression(parentDefn, expr) ) {
				String message = Labels.getLabel("survey.schema.attribute.code.validation.error.invalid_parent_expression");
				SurveyValidationResult surveyValidationResult = new SurveyValidationResult(path, message);
				results.add(surveyValidationResult);
			}
		} else if ( node instanceof TaxonAttributeDefinition ) {
			List<String> qualifiers = ((TaxonAttributeDefinition) node).getQualifiers();
			if ( qualifiers != null ) {
				for (String expr : qualifiers) {
					if ( StringUtils.isNotBlank(expr) && ! surveyManager.validatePathExpression(parentDefn, expr) ) {
						String message = Labels.getLabel("survey.schema.attribute.taxon.validation.error.invalid_qualifier_expression");
						SurveyValidationResult surveyValidationResult = new SurveyValidationResult(path, message);
						results.add(surveyValidationResult);
						break;
					}
				}
			}
		}
		return results;
	}
	*/
	
	protected List<SurveyValidationResult> validateParentRelationship(CollectSurvey oldPublishedSurvey, CollectSurvey newSurvey) {
		final Schema oldSchema = oldPublishedSurvey.getSchema();
		SurveyValidationNodeDefinitionVisitor visitor = new SurveyValidationNodeDefinitionVisitor() {
			@Override
			public void visit(NodeDefinition nodeDefn) {
				NodeDefinition oldDefn = oldSchema.getDefinitionById(nodeDefn.getId());
				if ( oldDefn != null ) {
					NodeDefinition parentDefn = nodeDefn.getParentDefinition();
					NodeDefinition oldParentDefn = oldDefn.getParentDefinition();
					Integer parentDefnId = parentDefn == null ? null: parentDefn.getId();
					Integer oldParentDefnId = oldParentDefn == null ? null: oldParentDefn.getId();
					if ( parentDefnId == null && oldParentDefnId != null || 
							parentDefnId != null && oldParentDefnId == null ||
							parentDefnId != null && ! parentDefnId.equals(oldParentDefnId) ) {
						String message = Labels.getLabel("survey.validation.error.parent_changed");
						String path = nodeDefn.getPath();
						SurveyValidationResult validationResult = new SurveyValidationResult(path, message);
						addValidationError(validationResult);
					}
				}
			}
		};
		visitNodeDefinitions(newSurvey, visitor);
		return visitor.getResults();
	}
	
	protected List<SurveyValidationResult> validateDataTypeNotChanged(CollectSurvey oldPublishedSurvey, CollectSurvey newSurvey) {
		final Schema oldSchema = oldPublishedSurvey.getSchema();
		SurveyValidationNodeDefinitionVisitor visitor = new SurveyValidationNodeDefinitionVisitor() {
			@Override
			public void visit(NodeDefinition nodeDefn) {
				NodeDefinition oldDefn = oldSchema.getDefinitionById(nodeDefn.getId());
				if ( oldDefn != null && oldDefn.getClass() != nodeDefn.getClass() || 
						oldDefn.getClass().isAssignableFrom(NumericAttributeDefinition.class) && 
						((NumericAttributeDefinition) oldDefn).getType() != ((NumericAttributeDefinition) nodeDefn).getType()) {
					String message = Labels.getLabel("survey.validation.error.data_type_changed");
					String path = nodeDefn.getPath();
					SurveyValidationResult validationError = new SurveyValidationResult(path, message);
					addValidationError(validationError);
				} else if ( oldDefn.isMultiple() && ! nodeDefn.isMultiple() ) {
					String message = Labels.getLabel("survey.validation.error.cardinality_changed_from_multiple_to_single");
					String path = nodeDefn.getPath();
					SurveyValidationResult validationError = new SurveyValidationResult(path, message);
					addValidationError(validationError);
				}
			}
		};
		visitNodeDefinitions(newSurvey, visitor);
		return visitor.getResults();
	}

	protected void visitNodeDefinitions(CollectSurvey survey, NodeDefinitionVisitor nodeDefnVisitor) {
		Schema schema = survey.getSchema();
		List<EntityDefinition> rootEntityDefns = schema.getRootEntityDefinitions();
		for (EntityDefinition entityDefn : rootEntityDefns) {
			entityDefn.traverse(nodeDefnVisitor);
		}
	}
	
	public static class SurveyValidationResult {
		
		private String path;
		private String message;

		public SurveyValidationResult(String path, String message) {
			super();
			this.path = path;
			this.message = message;
		}

		public String getPath() {
			return path;
		}

		public String getMessage() {
			return message;
		}

	}
	
	public static abstract class SurveyValidationNodeDefinitionVisitor implements NodeDefinitionVisitor {
		
		private List<SurveyValidationResult> results;
		
		public void addValidationError(SurveyValidationResult result) {
			if ( results == null ) {
				results = new ArrayList<SurveyValidator.SurveyValidationResult>();
			}
			results.add(result);
		}
		
		public List<SurveyValidationResult> getResults() {
			return CollectionUtils.unmodifiableList(results);
		}
		
	}
		
}
