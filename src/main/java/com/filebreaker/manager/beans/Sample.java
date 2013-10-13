package com.filebreaker.manager.beans;

import java.math.BigDecimal;
import java.util.Date;

public class Sample {

	private Integer id;
	
	private Integer curvatureAngle;
	
	private Integer curvatureRadius;
	
	private Integer uses;
	
	private Integer sterilizations;

	private String fileType;
	
	private BigDecimal apicalDiameter;
	
	private Integer engineAngularSpeed;
	
	private BigDecimal engineTorque;
	
	private Integer ductSpeed;
	
	private Integer movementTypeId;
	
	private Integer studyTypeId;
	
	private String studyGroup;
	
	private Integer experimentId;
	
	private Integer metalCompositionId;
	
	private Date creationDate;
	
	private Date modificationDate;
	
	private BigDecimal conicity;
	
	private BigDecimal section;
	
	private int oscillations;
	
	private long durationMillis;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurvatureAngle() {
		return curvatureAngle;
	}

	public void setCurvatureAngle(Integer curvatureAngle) {
		this.curvatureAngle = curvatureAngle;
	}

	public Integer getCurvatureRadius() {
		return curvatureRadius;
	}

	public void setCurvatureRadius(Integer curvatureRadius) {
		this.curvatureRadius = curvatureRadius;
	}

	public Integer getUses() {
		return uses;
	}

	public void setUses(Integer uses) {
		this.uses = uses;
	}
	
	public Integer getSterilizations() {
		return sterilizations;
	}

	public void setSterilizations(Integer sterilizations) {
		this.sterilizations = sterilizations;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public BigDecimal getApicalDiameter() {
		return apicalDiameter;
	}

	public void setApicalDiameter(BigDecimal apicalDiameter) {
		this.apicalDiameter = apicalDiameter;
	}

	public Integer getEngineAngularSpeed() {
		return engineAngularSpeed;
	}

	public void setEngineAngularSpeed(Integer engineAngularSpeed) {
		this.engineAngularSpeed = engineAngularSpeed;
	}

	public BigDecimal getEngineTorque() {
		return engineTorque;
	}

	public void setEngineTorque(BigDecimal engineTorque) {
		this.engineTorque = engineTorque;
	}

	public Integer getMovementTypeId() {
		return movementTypeId;
	}

	public void setMovementTypeId(Integer movementTypeId) {
		this.movementTypeId = movementTypeId;
	}

	public Integer getStudyTypeId() {
		return studyTypeId;
	}

	public void setStudyTypeId(Integer studyTypeId) {
		this.studyTypeId = studyTypeId;
	}
	
	public String getStudyGroup() {
		return studyGroup;
	}

	public void setStudyGroup(String studyGroup) {
		this.studyGroup = studyGroup;
	}

	public Integer getDuctSpeed() {
		return ductSpeed;
	}

	public void setDuctSpeed(Integer ductSpeed) {
		this.ductSpeed = ductSpeed;
	}

	public Integer getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(Integer experimentId) {
		this.experimentId = experimentId;
	}

	public Integer getMetalCompositionId() {
		return metalCompositionId;
	}

	public void setMetalCompositionId(Integer metalCompositionId) {
		this.metalCompositionId = metalCompositionId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public BigDecimal getConicity() {
		return conicity;
	}

	public void setConicity(BigDecimal conicity) {
		this.conicity = conicity;
	}

	public int getOscillations() {
		return oscillations;
	}

	public BigDecimal getSection() {
		return section;
	}

	public void setSection(BigDecimal section) {
		this.section = section;
	}

	public void setOscillations(int oscillations) {
		this.oscillations = oscillations;
	}

	public long getDurationMillis() {
		return durationMillis;
	}

	public void setDurationMillis(long durationMillis) {
		this.durationMillis = durationMillis;
	}
}