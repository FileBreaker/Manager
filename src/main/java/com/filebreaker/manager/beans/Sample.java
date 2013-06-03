package com.filebreaker.manager.beans;

import java.util.Date;

public class Sample {

	private int id;
	
	private int curvatureAngle;
	
	private int curvatureRadius;
	
	private int uses;
	
	private String fileType;
	
	private int apicalDiameter;
	
	private int engineAngularSpeed;
	
	private int engineTorque;
	
	private int ductSpeed;
	
	private int movementTypeId;
	
	private int studyTypeId;
	
	private int experimentId;
	
	private Date creationDate;
	
	private Date modificationDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCurvatureAngle() {
		return curvatureAngle;
	}

	public void setCurvatureAngle(int curvatureAngle) {
		this.curvatureAngle = curvatureAngle;
	}

	public int getCurvatureRadius() {
		return curvatureRadius;
	}

	public void setCurvatureRadius(int curvatureRadius) {
		this.curvatureRadius = curvatureRadius;
	}

	public int getUses() {
		return uses;
	}

	public void setUses(int uses) {
		this.uses = uses;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getApicalDiameter() {
		return apicalDiameter;
	}

	public void setApicalDiameter(int apicalDiameter) {
		this.apicalDiameter = apicalDiameter;
	}

	public int getEngineAngularSpeed() {
		return engineAngularSpeed;
	}

	public void setEngineAngularSpeed(int engineAngularSpeed) {
		this.engineAngularSpeed = engineAngularSpeed;
	}

	public int getEngineTorque() {
		return engineTorque;
	}

	public void setEngineTorque(int engineTorque) {
		this.engineTorque = engineTorque;
	}

	public int getMovementTypeId() {
		return movementTypeId;
	}

	public void setMovementTypeId(int movementTypeId) {
		this.movementTypeId = movementTypeId;
	}

	public int getStudyTypeId() {
		return studyTypeId;
	}

	public void setStudyTypeId(int studyTypeId) {
		this.studyTypeId = studyTypeId;
	}

	public int getDuctSpeed() {
		return ductSpeed;
	}

	public void setDuctSpeed(int ductSpeed) {
		this.ductSpeed = ductSpeed;
	}

	public int getExperimentId() {
		return experimentId;
	}

	public void setExperimentId(int experimentId) {
		this.experimentId = experimentId;
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
}