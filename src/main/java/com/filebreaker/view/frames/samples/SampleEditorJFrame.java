package com.filebreaker.view.frames.samples;

import java.awt.event.ActionEvent;
import java.math.BigDecimal;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import com.filebreaker.samples.Sample;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.RefreshableFrame;
import com.filebreaker.view.frames.components.PositiveIntegerSpinnerNumberModel;
import com.filebreaker.view.frames.components.SampleEditorSpinner;
import com.filebreaker.view.i18n.I18n;

public class SampleEditorJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 6157009534536579567L;
	
	private JButton saveButton;
    
	private JButton cancelButton;
    
	private JComboBox<String> fileMetalCompositionComboBox;
    
	private JComboBox<String> studyTypeComboBox;
    
	private JComboBox<String> movementTypeComboBox;
    
	private JLabel helixAngleLabel;
	
	private JLabel distanceBetweenTurnsLabel;
	
	private JLabel angleCurveLabel;
    
	private JLabel movementTypeLabel;
    
	private JLabel rootCanalSpeedLabel;
    
	private JLabel fileMetalCompositionLabel;
    
	private JLabel studyTypeLabel;
	
	private JLabel studyGroupLabel;
    
	private JLabel radiusCurveLabel;
    
	private JLabel useNumberLabel;
    
	private JLabel esterilizationNumberLabel;
    
	private JLabel sectionLabel;
	
	private JLabel fileTypeLabel;
    
	private JLabel conicityLabel;
    
	private JLabel apicalDiameterLabel;
    
	private JLabel angularSpeedLabel;
    
	private JLabel engineTorqueLabel;
	
	private JSpinner helixAngleSpinner;
	
	private JSpinner distanceBetweenTurnsSpinner;
    
	private JSpinner angleCurveSpinner;
    
	private JSpinner rootCanalSpeedSpinner;
    
	private JSpinner radiusCurveSpinner;
    
	private JSpinner useNumberSpinner;
    
	private JSpinner esterilizationNumberSpinner;
    
	private JSpinner conicitySpinner;
    
	private JSpinner apicalDiameterSpinner;
    
	private JSpinner angularSpeedSpinner;
    
	private JSpinner engineTorqueSpinner;
	
	private JSpinner sectionSpinner;
    
	private JTextField fileTypeTextField;
	
	private JTextField studyGroupTextField;
	
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
		
		helixAngleLabel = new JLabel();
		distanceBetweenTurnsLabel = new JLabel();
        angleCurveLabel = new JLabel();
        radiusCurveLabel = new JLabel();
        useNumberLabel = new JLabel();
        esterilizationNumberLabel = new JLabel();
        sectionLabel = new JLabel();
        fileTypeLabel = new JLabel();
        conicityLabel = new JLabel();
        apicalDiameterLabel = new JLabel();
        angularSpeedLabel = new JLabel();
        engineTorqueLabel = new JLabel();
        movementTypeLabel = new JLabel();
        rootCanalSpeedLabel = new JLabel();
        fileMetalCompositionLabel = new JLabel();
        studyTypeLabel = new JLabel();
        studyGroupLabel = new JLabel();
        
        saveButton = new JButton();
        cancelButton = new JButton();
        helixAngleSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        distanceBetweenTurnsSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        angleCurveSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        radiusCurveSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        useNumberSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        esterilizationNumberSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        fileTypeTextField = new JTextField();
        studyGroupTextField = new JTextField();
        angularSpeedSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        rootCanalSpeedSpinner = new SampleEditorSpinner(new PositiveIntegerSpinnerNumberModel());
        movementTypeComboBox = new JComboBox<String>();
        fileMetalCompositionComboBox = new JComboBox<String>();
        studyTypeComboBox = new JComboBox<String>();
        
        // spinners with double values
        SpinnerNumberModel snmApicalDiameter = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        apicalDiameterSpinner = new SampleEditorSpinner(snmApicalDiameter);
        
        SpinnerNumberModel snmConicity = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        conicitySpinner = new SampleEditorSpinner(snmConicity);
        
        SpinnerNumberModel snmEngineTorque = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        engineTorqueSpinner = new SampleEditorSpinner(snmEngineTorque);
        
        SpinnerNumberModel snmSection = new SpinnerNumberModel(0.0,0.0,99999.99,0.01);
        sectionSpinner = new SampleEditorSpinner(snmSection);
                
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        helixAngleLabel.setText(I18n.getInstance().getString("sample.editor.helix.angle"));
        distanceBetweenTurnsLabel.setText(I18n.getInstance().getString("sample.editor.distance.turns"));
        angleCurveLabel.setText(I18n.getInstance().getString("sample.editor.curve.angle"));
        radiusCurveLabel.setText(I18n.getInstance().getString("sample.editor.curve.radius"));
        useNumberLabel.setText(I18n.getInstance().getString("sample.editor.use.number"));
        esterilizationNumberLabel.setText(I18n.getInstance().getString("sample.editor.sterilization.number"));
        fileTypeLabel.setText(I18n.getInstance().getString("sample.editor.file.type"));
        conicityLabel.setText(I18n.getInstance().getString("sample.editor.coning"));
        apicalDiameterLabel.setText(I18n.getInstance().getString("sample.editor.apical.diameter"));
        angularSpeedLabel.setText(I18n.getInstance().getString("sample.editor.engine.angular.speed"));
        engineTorqueLabel.setText(I18n.getInstance().getString("sample.editor.engine.torque"));
        movementTypeLabel.setText(I18n.getInstance().getString("sample.editor.movement.type"));
        rootCanalSpeedLabel.setText(I18n.getInstance().getString("sample.editor.root.canal.speed"));
        fileMetalCompositionLabel.setText(I18n.getInstance().getString("sample.editor.file.alloy"));
        studyTypeLabel.setText(I18n.getInstance().getString("sample.editor.study.type"));
        studyGroupLabel.setText(I18n.getInstance().getString("sample.editor.study.group"));
        sectionLabel.setText(I18n.getInstance().getString("sample.editor.section"));
        
        fileTypeTextField.setText("");
        studyGroupTextField.setText("");
       
        movementTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String [] {
        		I18n.getInstance().getString("sample.editor.movement.type.continuous.rotational"), 
        		I18n.getInstance().getString("sample.editor.movement.type.reciprocating"
        )}));
        fileMetalCompositionComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { 
        		I18n.getInstance().getString("sample.editor.file.alloy.niti")
        }));
        studyTypeComboBox.setModel(new DefaultComboBoxModel<String>(new String[] { 
        		I18n.getInstance().getString("sample.editor.study.type.static"), 
        		I18n.getInstance().getString("sample.editor.study.type.dynamic")
        }));

        saveButton.setIcon(new ImageIcon(getClass().getResource("/page_tick.gif"))); // NOI18N
        saveButton.setText(I18n.getInstance().getString("sample.editor.save"));
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new ImageIcon(getClass().getResource("/page_text_delete.gif"))); // NOI18N
        cancelButton.setText(I18n.getInstance().getString("sample.editor.cancel"));
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
                            .add(rootCanalSpeedLabel)
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
                            .add(rootCanalSpeedSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 90, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
                    .add(rootCanalSpeedLabel)
                    .add(rootCanalSpeedSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
    	
    	sample.setId(sampleId);
    	sample.setExperimentId(experimentId);
    	sample.setApicalDiameter(new BigDecimal(apicalDiameterSpinner.getValue().toString()));
    	sample.setHelixAngle((Integer) helixAngleSpinner.getValue());
    	sample.setDistanceBetweenTurns((Integer) distanceBetweenTurnsSpinner.getValue());
    	sample.setCurvatureAngle((Integer)angleCurveSpinner.getValue());
    	sample.setCurvatureRadius((Integer)radiusCurveSpinner.getValue());
    	sample.setRootCanalSpeed((Integer)rootCanalSpeedSpinner.getValue());
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
    	rootCanalSpeedSpinner.setValue(sample.getRootCanalSpeed());
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
    
    private void cancelButtonActionPerformed(ActionEvent evt) {
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