package com.filebreaker.view.frames.samples;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import javax.swing.JFrame;
import javax.swing.SpinnerNumberModel;

import com.filebreaker.samples.Sample;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.RefreshableFrame;
import com.filebreaker.view.i18n.Literals;

public class SampleEditorJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 6157009534536579567L;
	
	private javax.swing.JButton saveButton;
    
	private javax.swing.JButton cancelButton;
    
	private javax.swing.JComboBox<String> fileMetalCompositionComboBox;
    
	private javax.swing.JComboBox<String> studyTypeComboBox;
    
	private javax.swing.JComboBox<String> movementTypeComboBox;
    
	private javax.swing.JLabel helixAngleLabel;
	
	private javax.swing.JLabel distanceBetweenTurnsLabel;
	
	private javax.swing.JLabel angleCurveLabel;
    
	private javax.swing.JLabel movementTypeLabel;
    
	private javax.swing.JLabel ductSpeedLabel;
    
	private javax.swing.JLabel fileMetalCompositionLabel;
    
	private javax.swing.JLabel studyTypeLabel;
	
	private javax.swing.JLabel studyGroupLabel;
    
	private javax.swing.JLabel radiusCurveLabel;
    
	private javax.swing.JLabel useNumberLabel;
    
	private javax.swing.JLabel esterilizationNumberLabel;
    
	private javax.swing.JLabel sectionLabel;
	
	private javax.swing.JLabel fileTypeLabel;
    
	private javax.swing.JLabel conicityLabel;
    
	private javax.swing.JLabel apicalDiameterLabel;
    
	private javax.swing.JLabel angularSpeedLabel;
    
	private javax.swing.JLabel engineTorqueLabel;
	
	private javax.swing.JSpinner helixAngleSpinner;
	
	private javax.swing.JSpinner distanceBetweenTurnsSpinner;
    
	private javax.swing.JSpinner angleCurveSpinner;
    
	private javax.swing.JSpinner ductSpeedSpinner;
    
	private javax.swing.JSpinner radiusCurveSpinner;
    
	private javax.swing.JSpinner useNumberSpinner;
    
	private javax.swing.JSpinner esterilizationNumberSpinner;
    
	private javax.swing.JSpinner conicitySpinner;
    
	private javax.swing.JSpinner apicalDiameterSpinner;
    
	private javax.swing.JSpinner angularSpeedSpinner;
    
	private javax.swing.JSpinner engineTorqueSpinner;
	
	private javax.swing.JSpinner sectionSpinner;
    
	private javax.swing.JTextField fileTypeTextField;
	
	private javax.swing.JTextField studyGroupTextField;
	
	private SamplesController samplesController;
	
	private Integer experimentId;
	
	private Integer sampleId;
	
	private RefreshableFrame parentFrame;
	
	public SampleEditorJFrame(SamplesController samplesController, Integer experimentId, RefreshableFrame parentFrame) {
        this.samplesController = samplesController;
		this.experimentId = experimentId;
        this.parentFrame = parentFrame;
        
		initComponents();
    }

    public SampleEditorJFrame(SamplesController samplesController, Integer experimentId, Integer selectedSampleId, RefreshableFrame parentFrame) {
		this(samplesController, experimentId, parentFrame);
		this.sampleId = selectedSampleId;
		
		Sample sample = samplesController.getSample(experimentId, selectedSampleId);
		fillFormWithSample(sample);
	}

	private void initComponents() {

		this.setResizable(false);
		
		helixAngleLabel = new javax.swing.JLabel();
		distanceBetweenTurnsLabel = new javax.swing.JLabel();
        angleCurveLabel = new javax.swing.JLabel();
        radiusCurveLabel = new javax.swing.JLabel();
        useNumberLabel = new javax.swing.JLabel();
        esterilizationNumberLabel = new javax.swing.JLabel();
        sectionLabel = new javax.swing.JLabel();
        fileTypeLabel = new javax.swing.JLabel();
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
        helixAngleSpinner = new javax.swing.JSpinner();
        distanceBetweenTurnsSpinner = new javax.swing.JSpinner();
        angleCurveSpinner = new javax.swing.JSpinner();
        radiusCurveSpinner = new javax.swing.JSpinner();
        useNumberSpinner = new javax.swing.JSpinner();
        esterilizationNumberSpinner = new javax.swing.JSpinner();
        fileTypeTextField = new javax.swing.JTextField();
        studyGroupTextField = new javax.swing.JTextField();
        angularSpeedSpinner = new javax.swing.JSpinner();
        ductSpeedSpinner = new javax.swing.JSpinner();
        movementTypeComboBox = new javax.swing.JComboBox<String>();
        fileMetalCompositionComboBox = new javax.swing.JComboBox<String>();
        studyTypeComboBox = new javax.swing.JComboBox<String>();
        
        // spinners with double values
        SpinnerNumberModel snmApicalDiameter = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        apicalDiameterSpinner = new javax.swing.JSpinner(snmApicalDiameter);
        
        SpinnerNumberModel snmConicity = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        conicitySpinner = new javax.swing.JSpinner(snmConicity);
        
        SpinnerNumberModel snmEngineTorque = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        engineTorqueSpinner = new javax.swing.JSpinner(snmEngineTorque);
        
        SpinnerNumberModel snmSection = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        sectionSpinner = new javax.swing.JSpinner(snmSection);
                
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        helixAngleLabel.setText(Literals.getInstance().getString("sample.editor.helix.angle"));
        distanceBetweenTurnsLabel.setText(Literals.getInstance().getString("sample.editor.distance.turns"));
        angleCurveLabel.setText(Literals.getInstance().getString("sample.editor.curve.angle"));
        radiusCurveLabel.setText(Literals.getInstance().getString("sample.editor.curve.radius"));
        useNumberLabel.setText(Literals.getInstance().getString("sample.editor.use.number"));
        esterilizationNumberLabel.setText(Literals.getInstance().getString("sample.editor.sterilization.number"));
        fileTypeLabel.setText(Literals.getInstance().getString("sample.editor.file.type"));
        conicityLabel.setText(Literals.getInstance().getString("sample.editor.coning"));
        apicalDiameterLabel.setText(Literals.getInstance().getString("sample.editor.apical.diameter"));
        angularSpeedLabel.setText(Literals.getInstance().getString("sample.editor.engine.angular.speed"));
        engineTorqueLabel.setText(Literals.getInstance().getString("sample.editor.engine.torque"));
        movementTypeLabel.setText(Literals.getInstance().getString("sample.editor.movement.type"));
        ductSpeedLabel.setText(Literals.getInstance().getString("sample.editor.duct.speed"));
        fileMetalCompositionLabel.setText(Literals.getInstance().getString("sample.editor.file.alloy"));
        studyTypeLabel.setText(Literals.getInstance().getString("sample.editor.study.type"));
        studyGroupLabel.setText(Literals.getInstance().getString("sample.editor.study.group"));
        sectionLabel.setText(Literals.getInstance().getString("sample.editor.section"));
        
        fileTypeTextField.setText("");
        studyGroupTextField.setText("");
       
        movementTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String [] {
        		Literals.getInstance().getString("sample.editor.movement.type.continuous.rotational"), 
        		Literals.getInstance().getString("sample.editor.movement.type.reciprocating"
        )}));
        fileMetalCompositionComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { 
        		Literals.getInstance().getString("sample.editor.file.alloy.niti")
        }));
        studyTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<String>(new String[] { 
        		Literals.getInstance().getString("sample.editor.study.type.static"), 
        		Literals.getInstance().getString("sample.editor.study.type.dynamic")
        }));

        saveButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_tick.gif"))); // NOI18N
        saveButton.setText(Literals.getInstance().getString("sample.editor.save"));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_text_delete.gif"))); // NOI18N
        cancelButton.setText(Literals.getInstance().getString("sample.editor.cancel"));
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
                            .add(helixAngleLabel)
                            .add(distanceBetweenTurnsLabel)
                            .add(conicityLabel)
                            .add(sectionLabel)
                            .add(engineTorqueLabel)
                            .add(movementTypeLabel)
                            .add(studyTypeLabel)
                            .add(studyGroupLabel)
                            .add(ductSpeedLabel)
                            .add(fileTypeLabel)
                            .add(angularSpeedLabel)
                            .add(apicalDiameterLabel)
                            .add(fileMetalCompositionLabel)
                            .add(saveButton))
                        .add(25, 25, 25)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(esterilizationNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(useNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(radiusCurveSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(helixAngleSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(distanceBetweenTurnsSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(angleCurveSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(conicitySpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(sectionSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
                    .add(helixAngleLabel)
                    .add(helixAngleSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(distanceBetweenTurnsLabel)
                    .add(distanceBetweenTurnsSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)    
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
                    .add(sectionLabel)
                    .add(sectionSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
                    .add(fileTypeLabel)
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
    	sample.setApicalDiameter(new BigDecimal(apicalDiameterSpinner.getValue().toString()));
    	sample.setHelixAngle((Integer) helixAngleSpinner.getValue());
    	sample.setDistanceBetweenTurns((Integer) distanceBetweenTurnsSpinner.getValue());
    	sample.setCurvatureAngle((Integer)angleCurveSpinner.getValue());
    	sample.setCurvatureRadius((Integer)radiusCurveSpinner.getValue());
    	sample.setDuctSpeed((Integer)ductSpeedSpinner.getValue());
    	sample.setEngineAngularSpeed((Integer)angularSpeedSpinner.getValue());
    	sample.setEngineTorque(new BigDecimal(engineTorqueSpinner.getValue().toString()));
    	sample.setFileType(fileTypeTextField.getText());
    	sample.setUses((Integer)useNumberSpinner.getValue());
    	sample.setSterilizations((Integer)esterilizationNumberSpinner.getValue());
    	sample.setConicity(new BigDecimal(conicitySpinner.getValue().toString()));
    	sample.setSection(new BigDecimal(sectionSpinner.getValue().toString()));
    	sample.setMovementTypeId(movementTypeComboBox.getSelectedIndex());
    	sample.setStudyTypeId(studyTypeComboBox.getSelectedIndex());
    	sample.setStudyGroup(studyGroupTextField.getText());
    	sample.setMetalCompositionId(fileMetalCompositionComboBox.getSelectedIndex());
    	
    	return sample;
    }
	
	private void fillFormWithSample(Sample sample){
		apicalDiameterSpinner.setValue(sample.getApicalDiameter());
		helixAngleSpinner.setValue(sample.getHelixAngle());
		distanceBetweenTurnsSpinner.setValue(sample.getDistanceBetweenTurns());
		angleCurveSpinner.setValue(sample.getCurvatureAngle());
		radiusCurveSpinner.setValue(sample.getCurvatureRadius());
    	ductSpeedSpinner.setValue(sample.getDuctSpeed());
    	angularSpeedSpinner.setValue(sample.getEngineAngularSpeed());
    	engineTorqueSpinner.setValue(sample.getEngineTorque());
    	sectionSpinner.setValue(sample.getSection());
    	conicitySpinner.setValue(sample.getConicity());
    	fileTypeTextField.setText(sample.getFileType());
    	useNumberSpinner.setValue(sample.getUses());
    	esterilizationNumberSpinner.setValue(sample.getSterilizations());
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
			samplesController.createSample(sample);
		} else {
			samplesController.updateSample(sample);
		}
		
		parentFrame.refresh();
		this.dispose();
	}
}