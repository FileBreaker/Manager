/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filebreaker.manager.gui.frames.samples;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;

import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.frames.RefreshableFrame;
import com.filebreaker.manager.gui.i18n.Literals;
import com.filebreaker.manager.gui.utils.TimeUtils;
import com.filebreaker.manager.tasks.SampleExecutionTask;

public class SampleJFrame extends javax.swing.JFrame implements RefreshableFrame {

	private static final long serialVersionUID = 8162818230491268811L;
	
	private MainController mainController;
	
	private javax.swing.JButton editButton;
    
	private javax.swing.JLabel chronometerLabel;
    
    private javax.swing.JLabel oscillationNumberLabel;
    
    private javax.swing.JLabel stateLabel;
    
    private javax.swing.JPanel jPanel1;
    
    private javax.swing.JPanel jPanel2;
    
    private javax.swing.JScrollPane scrollPane;
    
    private javax.swing.JSlider switcherSlider;
    
    private javax.swing.JTabbedPane executionTabbedPane;
    
    private javax.swing.JTable propertiesTable;
    
    private Integer experimentId;
    
    private Integer sampleId;
    
    private SampleExecutionTask task;
	
    public SampleJFrame(MainController mainController, Integer experimentId, Integer sampleId) {
    	task = new SampleExecutionTask(this, mainController, experimentId, sampleId);
    	
    	this.mainController = mainController;
        this.experimentId = experimentId;
        this.sampleId = sampleId;
        
    	initComponents();        
    }

	private void initComponents() {
		this.setResizable(false);
		
		Sample sample = mainController.getSample(experimentId, sampleId);
		
        executionTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        oscillationNumberLabel = new javax.swing.JLabel();
        chronometerLabel = new javax.swing.JLabel();
        stateLabel = new javax.swing.JLabel();
        switcherSlider = new javax.swing.JSlider();
        jPanel2 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        propertiesTable = new javax.swing.JTable();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        oscillationNumberLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        oscillationNumberLabel.setText(sample.getOscillations() + " " + Literals.getInstance().getString("sample.oscillations"));

        chronometerLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        chronometerLabel.setText(TimeUtils.getDuration(sample.getDurationMillis()));

        stateLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        stateLabel.setText(Literals.getInstance().getString("sample.off"));

        switcherSlider.setValue(0);
        switcherSlider.setMinimum(0);
        switcherSlider.setMaximum(1);
        switcherSlider.setMajorTickSpacing(1);
        switcherSlider.setMinorTickSpacing(1);
        switcherSlider.setSnapToTicks(true);
        switcherSlider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent evt) {
				javax.swing.JSlider source = (javax.swing.JSlider)evt.getSource();
			    
				if (!source.getValueIsAdjusting()) {
					int value = source.getValue();
					
					if(value == 0){
						stateLabel.setText(Literals.getInstance().getString("sample.off"));
					} else if(value == 1){
						stateLabel.setText(Literals.getInstance().getString("sample.on"));
						task.execute();
					}
			    }
			}
		});
        
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap(43, Short.MAX_VALUE)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(chronometerLabel)
                        .add(95, 95, 95))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(oscillationNumberLabel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 180, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(52, 52, 52))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .add(switcherSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 82, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(stateLabel)
                        .add(41, 41, 41))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(oscillationNumberLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chronometerLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(switcherSlider, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(stateLabel))
                .addContainerGap(79, Short.MAX_VALUE))
        );

        executionTabbedPane.addTab(Literals.getInstance().getString("sample.execution"), jPanel1);

        propertiesTable.setModel(getTableModel());
        scrollPane.setViewportView(propertiesTable);

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interface_dialog.gif"))); // NOI18N
        editButton.setText(Literals.getInstance().getString("sample.edit"));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(scrollPane, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 292, Short.MAX_VALUE)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(0, 0, Short.MAX_VALUE)
                .add(editButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .add(editButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(scrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        executionTabbedPane.addTab(Literals.getInstance().getString("sample.data"), jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .add(executionTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 313, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, executionTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 239, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }
	
	protected void editButtonActionPerformed(ActionEvent evt) {
		JFrame sampleEditorJFrame = new SampleEditorJFrame(mainController, experimentId, sampleId, this);
		sampleEditorJFrame.setVisible(true);
	}

	private DefaultTableModel getTableModel() {
    	Sample sample = mainController.getSample(experimentId, sampleId);
		
    	return new DefaultTableModel(
            getModel(sample),
            new String [] {
                Literals.getInstance().getString("sample.property"), 
                Literals.getInstance().getString("sample.value")
            }
        );
	}
    
    private Object[][] getModel(Sample sample){
    	Object [][] result = null;
		
		if(sample != null){
			result = new Object[22][2];
		
			result[0][0] = Literals.getInstance().getString("sample.editor.id");
			result[0][1] = sample.getId();
			
			result[1][0] = Literals.getInstance().getString("sample.oscillations");
			result[1][1] = sample.getOscillations();
			
			result[2][0] = Literals.getInstance().getString("sample.editor.helix.angle");
			result[2][1] = sample.getHelixAngle();
			
			result[3][0] = Literals.getInstance().getString("sample.editor.distance.turns");
			result[3][1] = sample.getDistanceBetweenTurns();
			
			result[4][0] = Literals.getInstance().getString("sample.duration");
			result[4][1] = TimeUtils.getDuration(sample.getDurationMillis());
			
			result[5][0] = Literals.getInstance().getString("sample.editor.apical.diameter");
			result[5][1] = sample.getApicalDiameter();
			
			result[6][0] = Literals.getInstance().getString("sample.editor.curve.angle");
			result[6][1] = sample.getCurvatureAngle();
			
			result[7][0] = Literals.getInstance().getString("sample.editor.curve.radius");
			result[7][1] = sample.getCurvatureRadius();
			
			result[8][0] = Literals.getInstance().getString("sample.editor.duct.speed");
			result[8][1] = sample.getDuctSpeed();
			
			result[9][0] = Literals.getInstance().getString("sample.editor.engine.angular.speed");
			result[9][1] = sample.getEngineAngularSpeed();
			
			result[10][0] = Literals.getInstance().getString("sample.editor.engine.torque");
			result[10][1] = sample.getEngineTorque();
			
			result[11][0] = Literals.getInstance().getString("experiments.identifier");
			result[11][1] = sample.getExperimentId();
			
			result[12][0] = Literals.getInstance().getString("sample.editor.file.type");
			result[12][1] = sample.getFileType();
			
			result[13][0] = Literals.getInstance().getString("sample.editor.identifier");
			result[13][1] = sample.getId();
			
			result[14][0] = Literals.getInstance().getString("sample.editor.file.alloy");
			result[14][1] = sample.getMetalCompositionId();
			
			result[15][0] = Literals.getInstance().getString("sample.modification");
			result[15][1] = sample.getModificationDate();
			
			result[16][0] = Literals.getInstance().getString("sample.editor.movement.type");
			result[16][1] = sample.getMovementTypeId();
			
			result[17][0] = Literals.getInstance().getString("sample.editor.study.type");
			result[17][1] = sample.getStudyTypeId();
			
			result[18][0] = Literals.getInstance().getString("sample.editor.study.group");
			result[18][1] = sample.getStudyGroup();
			
			result[19][0] = Literals.getInstance().getString("sample.editor.use.number");
			result[19][1] = sample.getUses();
			
			result[20][0] = Literals.getInstance().getString("sample.editor.coning");
			result[20][1] = sample.getConicity();
			
			result[21][0] = Literals.getInstance().getString("sample.editor.section");
			result[21][1] = sample.getSection();
		}
		
		return result;
    }

	public void refresh() {
		propertiesTable.setModel(getTableModel());
	}

	public javax.swing.JSlider getSwitcherSlider() {
		return switcherSlider;
	}

	public void setSwitcherSlider(javax.swing.JSlider switcherSlider) {
		this.switcherSlider = switcherSlider;
	}
	
	public void setTimeToChronometerLabel(String time){
		chronometerLabel.setText(time);
	}
}