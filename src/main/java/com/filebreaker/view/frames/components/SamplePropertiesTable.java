package com.filebreaker.view.frames.components;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.filebreaker.samples.Sample;
import com.filebreaker.view.i18n.I18n;
import com.filebreaker.view.utils.TimeUtils;

public class SamplePropertiesTable extends JTable {

	private static final long serialVersionUID = 1L;
	
	public void setTableModel(Sample sample) {
		this.setModel(new DefaultTableModel(getModel(sample), new String[] {
				I18n.getInstance().getString("sample.property"), 
				I18n.getInstance().getString("sample.value") 
		}));
	}
	
	public boolean isCellEditable(int row, int column){  
        return false;  
    }
	
	private Object[][] getModel(Sample sample){
    	Object [][] result = null;
		
		if(sample == null) return result;
		
		result = new Object[22][2];
	
		result[0][0] = I18n.getInstance().getString("sample.editor.id");
		result[0][1] = sample.getId();
		
		result[1][0] = I18n.getInstance().getString("sample.oscillations");
		result[1][1] = sample.getOscillations();
		
		result[2][0] = I18n.getInstance().getString("sample.editor.helix.angle");
		result[2][1] = sample.getHelixAngle();
		
		result[3][0] = I18n.getInstance().getString("sample.editor.distance.turns");
		result[3][1] = sample.getDistanceBetweenTurns();
		
		result[4][0] = I18n.getInstance().getString("sample.duration");
		result[4][1] = TimeUtils.getDuration(sample.getDurationMillis());
		
		result[5][0] = I18n.getInstance().getString("sample.editor.apical.diameter");
		result[5][1] = sample.getApicalDiameter();
		
		result[6][0] = I18n.getInstance().getString("sample.editor.curve.angle");
		result[6][1] = sample.getCurvatureAngle();
		
		result[7][0] = I18n.getInstance().getString("sample.editor.curve.radius");
		result[7][1] = sample.getCurvatureRadius();
		
		result[8][0] = I18n.getInstance().getString("sample.editor.root.canal.speed");
		result[8][1] = sample.getRootCanalSpeed();
		
		result[9][0] = I18n.getInstance().getString("sample.editor.engine.angular.speed");
		result[9][1] = sample.getEngineAngularSpeed();
		
		result[10][0] = I18n.getInstance().getString("sample.editor.engine.torque");
		result[10][1] = sample.getEngineTorque();
		
		result[11][0] = I18n.getInstance().getString("experiments.identifier");
		result[11][1] = sample.getExperimentId();
		
		result[12][0] = I18n.getInstance().getString("sample.editor.file.type");
		result[12][1] = sample.getFileType();
		
		result[13][0] = I18n.getInstance().getString("sample.editor.identifier");
		result[13][1] = sample.getId();
		
		result[14][0] = I18n.getInstance().getString("sample.editor.file.alloy");
		result[14][1] = sample.getMetalCompositionId();
		
		result[15][0] = I18n.getInstance().getString("sample.modification");
		result[15][1] = sample.getModificationDate();
		
		result[16][0] = I18n.getInstance().getString("sample.editor.movement.type");
		result[16][1] = sample.getMovementTypeId();
		
		result[17][0] = I18n.getInstance().getString("sample.editor.study.type");
		result[17][1] = sample.getStudyTypeId();
		
		result[18][0] = I18n.getInstance().getString("sample.editor.study.group");
		result[18][1] = sample.getStudyGroup();
		
		result[19][0] = I18n.getInstance().getString("sample.editor.use.number");
		result[19][1] = sample.getUses();
		
		result[20][0] = I18n.getInstance().getString("sample.editor.coning");
		result[20][1] = sample.getConicity();
		
		result[21][0] = I18n.getInstance().getString("sample.editor.section");
		result[21][1] = sample.getSection();
		
		return result;
    }

}