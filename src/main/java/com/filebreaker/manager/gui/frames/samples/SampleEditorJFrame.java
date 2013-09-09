package com.filebreaker.manager.gui.frames.samples;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.frames.RefreshableFrame;

/**
 *
 * @author danielortegaufano
 */
public class SampleEditorJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 6157009534536579567L;
	
	private javax.swing.JButton saveButton;
    
	private javax.swing.JButton cancelButton;
    
	private javax.swing.JComboBox fileMetalCompositionComboBox;
    
	private javax.swing.JComboBox studyTypeComboBox;
    
	private javax.swing.JComboBox movementTypeComboBox;
    
	private javax.swing.JLabel angleCurveLabel;
    
	private javax.swing.JLabel movementTypeLabel;
    
	private javax.swing.JLabel ductSpeedLabel;
    
	private javax.swing.JLabel fileMetalCompositionLabel;
    
	private javax.swing.JLabel studyTypeLabel;
	
	private javax.swing.JLabel studyGroupLabel;
    
	private javax.swing.JLabel radiusCurveLabel;
    
	private javax.swing.JLabel useNumberLabel;
    
	private javax.swing.JLabel esterilizationNumberLabel;
    
	private javax.swing.JLabel limeTypeLabel;
    
	private javax.swing.JLabel conicityLabel;
    
	private javax.swing.JLabel apicalDiameterLabel;
    
	private javax.swing.JLabel angularSpeedLabel;
    
	private javax.swing.JLabel engineTorqueLabel;
    
	private javax.swing.JSpinner angleCurveSpinner;
    
	private javax.swing.JSpinner ductSpeedSpinner;
    
	private javax.swing.JSpinner radiusCurveSpinner;
    
	private javax.swing.JSpinner useNumberSpinner;
    
	private javax.swing.JSpinner esterilizationNumberSpinner;
    
	private javax.swing.JSpinner conicitySpinner;
    
	private javax.swing.JSpinner apicalDiameterSpinner;
    
	private javax.swing.JSpinner angularSpeedSpinner;
    
	private javax.swing.JSpinner engineTorqueSpinner;
    
	private javax.swing.JTextField fileTypeTextField;
	
	private javax.swing.JTextField studyGroupTextField;
	
	private MainController mainController;
	
	private Integer experimentId;
	
	private Integer sampleId;
	
	private RefreshableFrame parentFrame;
	
	public SampleEditorJFrame(MainController mainController, Integer experimentId, RefreshableFrame parentFrame) {
		this.mainController = mainController;
        this.experimentId = experimentId;
        this.parentFrame = parentFrame;
        
		initComponents();
    }

    public SampleEditorJFrame(MainController mainController, Integer experimentId, Integer selectedSampleId, RefreshableFrame parentFrame) {
		this(mainController, experimentId, parentFrame);
		this.sampleId = selectedSampleId;
		
		Sample sample = mainController.getSample(experimentId, selectedSampleId);
		fillFormWithSample(sample);
	}

	private void initComponents() {

		this.setResizable(false);
		
        angleCurveLabel = new javax.swing.JLabel();
        radiusCurveLabel = new javax.swing.JLabel();
        useNumberLabel = new javax.swing.JLabel();
        esterilizationNumberLabel = new javax.swing.JLabel();
        limeTypeLabel = new javax.swing.JLabel();
        conicityLabel = new javax.swing.JLabel();
        apicalDiameterLabel = new javax.swing.JLabel();
        angularSpeedLabel = new javax.swing.JLabel();
        engineTorqueLabel = new javax.swing.JLabel();
        movementTypeLabel = new javax.swing.JLabel();
        ductSpeedLabel = new javax.swing.JLabel();
        fileMetalCompositionLabel = new javax.swing.JLabel();
        studyTypeLabel = new javax.swing.JLabel();
        studyGroupLabel = new javax.swing.JLabel();
        
        saveButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        angleCurveSpinner = new javax.swing.JSpinner();
        radiusCurveSpinner = new javax.swing.JSpinner();
        useNumberSpinner = new javax.swing.JSpinner();
        esterilizationNumberSpinner = new javax.swing.JSpinner();
        fileTypeTextField = new javax.swing.JTextField();
        studyGroupTextField = new javax.swing.JTextField();
        conicitySpinner = new javax.swing.JSpinner();
        apicalDiameterSpinner = new javax.swing.JSpinner();
        angularSpeedSpinner = new javax.swing.JSpinner();
        engineTorqueSpinner = new javax.swing.JSpinner();
        ductSpeedSpinner = new javax.swing.JSpinner();
        movementTypeComboBox = new javax.swing.JComboBox();
        fileMetalCompositionComboBox = new javax.swing.JComboBox();
        studyTypeComboBox = new javax.swing.JComboBox();
        
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        angleCurveLabel.setText("Ángulo de curvatura");
        radiusCurveLabel.setText("Radio de curvatura");
        useNumberLabel.setText("Número de usos");
        esterilizationNumberLabel.setText("Número de esterilizaciones");
        limeTypeLabel.setText("Tipo de lima");
        conicityLabel.setText("Conicidad");
        apicalDiameterLabel.setText("Diámetro apical");
        angularSpeedLabel.setText("Velocidad angular del motor");
        engineTorqueLabel.setText("Torqué del motor");
        movementTypeLabel.setText("Tipo de movimiento");
        ductSpeedLabel.setText("Velocidad del conducto");
        fileMetalCompositionLabel.setText("Aleación de la lima");
        studyTypeLabel.setText("Tipo de estudio");
        studyGroupLabel.setText("Grupo de estudio");
        
        fileTypeTextField.setText("");
        studyGroupTextField.setText("");
       
        movementTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String [] {"Rotacional Continuo", "Reciprocante"}));
        fileMetalCompositionComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "NiTi" }));
        studyTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Estático", "Dinámico" }));

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_tick.gif"))); // NOI18N
        saveButton.setText("Guardar");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_text_delete.gif"))); // NOI18N
        cancelButton.setText("Cancelar");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(radiusCurveLabel)
                            .add(useNumberLabel)
                            .add(esterilizationNumberLabel)
                            .add(angleCurveLabel)
                            .add(conicityLabel)
                            .add(engineTorqueLabel)
                            .add(movementTypeLabel)
                            .add(studyTypeLabel)
                            .add(studyGroupLabel)
                            .add(ductSpeedLabel)
                            .add(limeTypeLabel)
                            .add(angularSpeedLabel)
                            .add(apicalDiameterLabel)
                            .add(fileMetalCompositionLabel)
                            .add(saveButton))
                        .add(25, 25, 25)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(esterilizationNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(useNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(radiusCurveSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(angleCurveSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(conicitySpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(apicalDiameterSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(angularSpeedSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(ductSpeedSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(studyTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 198, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(studyGroupTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 198, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(fileMetalCompositionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 198, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(fileTypeTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 198, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(movementTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 198, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(engineTorqueSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(cancelButton))
                         ))));
        
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(angleCurveLabel)
                    .add(angleCurveSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(radiusCurveLabel)
                    .add(radiusCurveSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(useNumberLabel)
                    .add(useNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(esterilizationNumberLabel)
                    .add(esterilizationNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(conicityLabel)
                    .add(conicitySpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(apicalDiameterLabel)
                    .add(apicalDiameterSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(angularSpeedLabel)
                    .add(angularSpeedSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(engineTorqueLabel)
                    .add(engineTorqueSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(ductSpeedLabel)
                    .add(ductSpeedSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(limeTypeLabel)
                    .add(fileTypeTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(studyTypeLabel)
                    .add(studyTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(studyGroupLabel)
                    .add(studyGroupTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(fileMetalCompositionLabel)
                    .add(fileMetalCompositionComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(movementTypeLabel)
                    .add(movementTypeComboBox, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(saveButton)
                    .add(cancelButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }

	private Sample getSampleFromEditorForm(){
    	Sample sample = new Sample();
    	
    	sample.setId(this.sampleId);
    	sample.setExperimentId(this.experimentId);
    	sample.setApicalDiameter((Integer)apicalDiameterSpinner.getValue());
    	sample.setCurvatureAngle((Integer)angleCurveSpinner.getValue());
    	sample.setCurvatureRadius((Integer)radiusCurveSpinner.getValue());
    	sample.setDuctSpeed((Integer)ductSpeedSpinner.getValue());
    	sample.setEngineAngularSpeed((Integer)angularSpeedSpinner.getValue());
    	sample.setEngineTorque((Integer)engineTorqueSpinner.getValue());
    	sample.setFileType(fileTypeTextField.getText());
    	sample.setUses((Integer)useNumberSpinner.getValue());
    	sample.setMovementTypeId(movementTypeComboBox.getSelectedIndex());
    	sample.setStudyTypeId(studyTypeComboBox.getSelectedIndex());
    	sample.setStudyGroup(studyGroupTextField.getText());
    	sample.setMetalCompositionId(fileMetalCompositionComboBox.getSelectedIndex());
    	
    	return sample;
    }
	
	private void fillFormWithSample(Sample sample){
		apicalDiameterSpinner.setValue(sample.getApicalDiameter());
		angleCurveSpinner.setValue(sample.getCurvatureAngle());
		radiusCurveSpinner.setValue(sample.getCurvatureRadius());
    	ductSpeedSpinner.setValue(sample.getDuctSpeed());
    	angularSpeedSpinner.setValue(sample.getEngineAngularSpeed());
    	engineTorqueSpinner.setValue(sample.getEngineTorque());
    	fileTypeTextField.setText(sample.getFileType());
    	useNumberSpinner.setValue(sample.getUses());
    	movementTypeComboBox.setSelectedIndex(sample.getMovementTypeId());
    	studyTypeComboBox.setSelectedIndex(sample.getStudyTypeId());
    	studyGroupTextField.setText(sample.getStudyGroup());
    	fileMetalCompositionComboBox.setSelectedIndex(sample.getMetalCompositionId());
	}
    
    protected void cancelButtonActionPerformed(ActionEvent evt) {
		this.dispose();
	}

	protected void saveButtonActionPerformed(ActionEvent evt) {
		Sample sample = getSampleFromEditorForm();
		
		if(this.sampleId == null){
			mainController.createSample(sample);
		} else {
			mainController.updateSample(sample);
		}
		
		parentFrame.refresh();
		this.dispose();
	}
}