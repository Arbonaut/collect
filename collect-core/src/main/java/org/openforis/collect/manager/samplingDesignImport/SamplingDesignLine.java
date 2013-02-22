package org.openforis.collect.manager.samplingDesignImport;

import java.util.List;

import org.openforis.collect.manager.referenceDataImport.Line;
import org.openforis.idm.util.CollectionUtil;

/**
 * 
 * @author S. Ricci
 *
 */
public class SamplingDesignLine extends Line {
	
	private List<String> levelCodes;
	private String x;
	private String y;
	
	public List<String> getLevelCodes() {
		return CollectionUtil.unmodifiableList(levelCodes);
	}
	
	public void setLevelCodes(List<String> levelCodes) {
		this.levelCodes = levelCodes;
	}

	public String getX() {
		return x;
	}

	public void setX(String x) {
		this.x = x;
	}

	public String getY() {
		return y;
	}

	public void setY(String y) {
		this.y = y;
	}

}