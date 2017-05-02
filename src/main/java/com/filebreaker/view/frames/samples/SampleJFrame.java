package com.filebreaker.view.frames.samples;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import com.filebreaker.controllers.MainController;
import com.filebreaker.samples.Sample;
import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.RefreshableFrame;
import com.filebreaker.view.frames.components.ActualSpeedLabel;
import com.filebreaker.view.frames.components.ChronometerLabel;
import com.filebreaker.view.frames.components.EngineState;
import com.filebreaker.view.frames.components.ExecutionTimeState;
import com.filebreaker.view.frames.components.LdrState;
import com.filebreaker.view.frames.components.LdrValueLabel;
import com.filebreaker.view.frames.components.OscillationNumberLabel;
import com.filebreaker.view.frames.components.SamplePropertiesTable;
import com.filebreaker.view.frames.components.SampleState;
import com.filebreaker.view.frames.components.SpeedDownButton;
import com.filebreaker.view.frames.components.SpeedUpButton;
import com.filebreaker.view.frames.components.StateLabel;
import com.filebreaker.view.frames.components.SwitcherSlider;
import com.filebreaker.view.i18n.I18n;
import com.filebreaker.view.tasks.SampleExecutionTask;

public class SampleJFrame extends javax.swing.JFrame implements RefreshableFrame {

	private static final long serialVersionUID = 8162818230491268811L;
	
	private SampleState sampleState;
	
	private SamplesController samplesController;
	
	private javax.swing.JButton speedUpButton;
	
	private javax.swing.JButton speedDownButton;
	
	private javax.swing.JLabel actualSpeedLabel;
	
	private javax.swing.JButton editButton;
    
	private javax.swing.JLabel chronometerLabel;
	
	private javax.swing.JLabel ldrValueLabel;
    
    private javax.swing.JLabel oscillationNumberLabel;
    
    private StateLabel stateLabel;
    
    private javax.swing.JPanel executionPanel;
    
    private javax.swing.JPanel sampleDataPanel;
    
    private javax.swing.JScrollPane scrollPane;
    
    private SwitcherSlider switcherSlider;
    
    private javax.swing.JTabbedPane executionTabbedPane;
    
    private SamplePropertiesTable propertiesTable;
    
    private SampleExecutionTask task;

	private Sample sample;

	private ExecutionTimeState executionTimeState;

	private LdrState ldrState;

	private EngineState engineState;
	
    public SampleJFrame(MainController mainController, SamplesController samplesController, Integer experimentId, Integer sampleId) {
    	this.samplesController = samplesController;
        this.sample = samplesController.getSample(experimentId, sampleId);
        
        this.executionTimeState = new ExecutionTimeState(sample.getEngineAngularSpeed());
        this.ldrState = new LdrState();
        this.engineState = new EngineState(mainController, ldrState);
        
        this.sampleState = new SampleState(sample, mainController, executionTimeState, ldrState, engineState);
        this.task = new SampleExecutionTask(samplesController, mainController, sampleState);
        
    	initComponents();
    }

	private void initComponents() {
		this.setResizable(false);
			
		propertiesTable = new SamplePropertiesTable();
		propertiesTable.setTableModel(sample);
		
		executionPanel = new javax.swing.JPanel();
        executionTabbedPane = new javax.swing.JTabbedPane();
        
        oscillationNumberLabel = new OscillationNumberLabel(executionTimeState);
        chronometerLabel = new ChronometerLabel(executionTimeState);
        ldrValueLabel = new LdrValueLabel(ldrState);
        stateLabel = new StateLabel(engineState);
        switcherSlider = new SwitcherSlider(engineState);
        speedUpButton = new SpeedUpButton(engineState);
        speedDownButton = new SpeedDownButton(engineState);
        actualSpeedLabel = new ActualSpeedLabel(engineState);
        sampleDataPanel = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        
        editButton = new javax.swing.JButton(); 

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(executionPanel);
        executionPanel.setLayout(jPanel1Layout);
        
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
                        .add(41, 41, 41))
            		.add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
	                    .add(speedUpButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 120, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                    .add(speedDownButton)
	                    .add(41, 41, 41))
            		.add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
            			.add(actualSpeedLabel)
            			.add(41, 41, 41))
            		.add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
	        			.add(ldrValueLabel)
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
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        .add(speedUpButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(speedDownButton))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(actualSpeedLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(ldrValueLabel)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        executionTabbedPane.addTab(I18n.getInstance().getString("sample.execution"), executionPanel);

        scrollPane.setViewportView(propertiesTable);

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interface_dialog.gif"))); // NOI18N
        editButton.setText(I18n.getInstance().getString("sample.edit"));
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(sampleDataPanel);
        sampleDataPanel.setLayout(jPanel2Layout);
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
                .add(scrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 218, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        executionTabbedPane.addTab(I18n.getInstance().getString("sample.data"), sampleDataPanel);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .add(executionTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 350, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, executionTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 305, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        task.execute();
        pack();
    }
	
	protected void editButtonActionPerformed(ActionEvent evt) {
		JFrame sampleEditorJFrame = new SampleEditorJFrame(samplesController, sample.getExperimentId(), sample.getId(), this);
		sampleEditorJFrame.setVisible(true);
	}

	public void refresh() {
		propertiesTable.setTableModel(samplesController.getSample(sample.getExperimentId(), sample.getId()));
	}

	public SwitcherSlider getSwitcherSlider() {
		return switcherSlider;
	}
}