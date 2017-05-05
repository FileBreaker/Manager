package com.filebreaker.view.dialogs;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;

import com.filebreaker.samples.SamplesController;
import com.filebreaker.view.frames.components.PositiveIntegerSpinnerNumberModel;
import com.filebreaker.view.frames.samples.SamplesJFrame;
import com.filebreaker.view.i18n.I18n;

public class DuplicateSampleJDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = 8408082753995792194L;

	private JLabel titleLabel;
	
	private JButton duplicateSampleButton;
    
	private JButton cancelButton;
    
    private JLabel sampleNumberLabel;
    
    private JSpinner sampleNumberSpinner;
    
    private SamplesController samplesController;
    
    private SamplesJFrame samplesJFrame;
    
    private Integer sampleId;
    
    private Integer experimentId;
    
    /**
     * Creates new form NewExperimentJDialog
     * @param experimentsFrameView 
     */
    public DuplicateSampleJDialog(java.awt.Frame parent, boolean modal, SamplesController samplesController, Integer sampleId, Integer experimentId) {
        super(parent, modal);
        
        this.samplesController = samplesController;
        this.samplesJFrame = (SamplesJFrame)parent;
        
        this.sampleId = sampleId;
        this.experimentId = experimentId;
        
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */                         
    private void initComponents() {
    	
    	this.setResizable(false);
    	
    	titleLabel = new JLabel();
        sampleNumberLabel = new JLabel();
        sampleNumberSpinner = new JSpinner(new PositiveIntegerSpinnerNumberModel());
        duplicateSampleButton = new JButton();
        cancelButton = new JButton();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        titleLabel.setText(I18n.getInstance().getString("duplicate.sample.required.data"));

        sampleNumberLabel.setText(I18n.getInstance().getString("duplicate.sample.number"));
        
        duplicateSampleButton.setIcon(new ImageIcon(getClass().getResource("/note_new.gif")));
        duplicateSampleButton.setText(I18n.getInstance().getString("duplicate.sample.save"));
        duplicateSampleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicateSampleActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new ImageIcon(getClass().getResource("/note_delete.gif")));
        cancelButton.setText(I18n.getInstance().getString("duplicate.sample.cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	cancelExperimentCreationActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(23, 23, 23)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(titleLabel)
                        .add(0, 0, Short.MAX_VALUE))
                    .add(layout.createSequentialGroup()
                        .add(sampleNumberLabel)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 49, Short.MAX_VALUE)
                        .add(sampleNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .add(layout.createSequentialGroup()
                .add(72, 72, 72)
                .add(duplicateSampleButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cancelButton)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(15, 15, 15)
                .add(titleLabel)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(sampleNumberLabel)
                        .add(6, 6, 6))
                    .add(sampleNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(cancelButton)
                    .add(duplicateSampleButton))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }
    
    private void duplicateSampleActionPerformed(ActionEvent evt) {
		Integer samplesNumber = (Integer)sampleNumberSpinner.getValue();
		
		if(samplesNumber > 0){
			samplesController.duplicateSamples(sampleId, experimentId, samplesNumber);
			samplesJFrame.refresh();
		}
		
		dispose();
	}
    
    private void cancelExperimentCreationActionPerformed(ActionEvent evt) {
		dispose();
    }
}