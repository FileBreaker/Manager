package com.filebreaker.view.dialogs;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import com.filebreaker.experiments.Experiment;
import com.filebreaker.experiments.ExperimentsController;
import com.filebreaker.view.frames.components.PositiveIntegerSpinnerNumberModel;
import com.filebreaker.view.frames.experiments.ExperimentsFrameView;
import com.filebreaker.view.i18n.I18n;

public class ExperimentJDialog extends javax.swing.JDialog {

	private static final long serialVersionUID = 8408082753995792194L;
	
	private Experiment experiment;

	private JButton saveExperimentButton;
    
	private JButton cancelButton;
    
    private JLabel titleLabel;
    
    private JLabel experimentLabel;
    
    private JLabel sampleNumberLabel;
    
    private JSpinner sampleNumberSpinner;
    
    private JTextField experimentNameTextField;
    
    private ExperimentsController experimentsController;
    
    private ExperimentsFrameView experimentsFrameView;
    
    /**
     * Creates new form NewExperimentJDialog
     * @param experimentsFrameView 
     */
    public ExperimentJDialog(java.awt.Frame parent, boolean modal, ExperimentsController experimentsController, ExperimentsFrameView experimentsFrameView) {
        super(parent, modal);
        
        this.experimentsController = experimentsController;
        this.experimentsFrameView = experimentsFrameView;
        
        initComponents();
    }
    
    /**
     * Creates new form NewExperimentJDialog
     * @param experimentsFrameView 
     */
    public ExperimentJDialog(java.awt.Frame parent, boolean modal, ExperimentsController experimentsController, ExperimentsFrameView experimentsFrameView, Experiment experiment) {
        super(parent, modal);
        
        this.experimentsController = experimentsController;
        this.experimentsFrameView = experimentsFrameView;
        this.experiment = experiment;
        
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
        experimentLabel = new JLabel();
        sampleNumberLabel = new JLabel();
        experimentNameTextField = new JTextField();
        sampleNumberSpinner = new JSpinner(new PositiveIntegerSpinnerNumberModel());
        saveExperimentButton = new JButton();
        cancelButton = new JButton();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        titleLabel.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        
        titleLabel.setText(I18n.getInstance().getString("new.experiment.required.data"));
        experimentLabel.setText(I18n.getInstance().getString("new.experiment.name"));
        sampleNumberLabel.setText(I18n.getInstance().getString("new.experiment.initial.sample.number"));
        
        experimentNameTextField.setText("");
        
        saveExperimentButton.setIcon(new ImageIcon(getClass().getResource("/note_new.gif")));
        saveExperimentButton.setText(I18n.getInstance().getString("new.experiment.save"));
        saveExperimentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createExperimentActionPerformed(evt);
            }
        });

        cancelButton.setIcon(new ImageIcon(getClass().getResource("/note_delete.gif")));
        cancelButton.setText(I18n.getInstance().getString("new.experiment.cancel"));
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	cancelExperimentCreationActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        if(experiment == null){
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	            .add(layout.createSequentialGroup()
	                .add(23, 23, 23)
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
	                    .add(layout.createSequentialGroup()
	                        .add(experimentLabel)
	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .add(experimentNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
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
	                .add(saveExperimentButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
	                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                            .add(experimentLabel)
	                            .add(experimentNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
	                        .add(sampleNumberLabel)
	                        .add(6, 6, 6))
	                    .add(sampleNumberSpinner, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
	                    .add(cancelButton)
	                    .add(saveExperimentButton))
	                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	        );
        } else {
        	experimentNameTextField.setText(experiment.getName());
        	
        	layout.setHorizontalGroup(
    	            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
    	            .add(layout.createSequentialGroup()
    	                .add(23, 23, 23)
    	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
    	                    .add(layout.createSequentialGroup()
    	                        .add(experimentLabel)
    	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    	                        .add(experimentNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 84, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    	                    .add(layout.createSequentialGroup()
    	                        .add(titleLabel)
    	                        .add(0, 0, Short.MAX_VALUE)))
    	                .addContainerGap())
    	            .add(layout.createSequentialGroup()
    	                .add(72, 72, 72)
    	                .add(saveExperimentButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 101, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
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
    	                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
    	                            .add(experimentLabel)
    	                            .add(experimentNameTextField, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
    	                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)))
    	                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
    	                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
    	                    .add(cancelButton)
    	                    .add(saveExperimentButton))
    	                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    	        );
        }

        pack();
    }
    
    private void createExperimentActionPerformed(ActionEvent evt) {
		String experimentName = experimentNameTextField.getText();
		Integer samplesNumber = (Integer)sampleNumberSpinner.getValue();
		
		if(experiment == null){
			experimentsController.createExperiment(experimentName, samplesNumber);
		} else {
			experiment.setName(experimentName);
			experimentsController.editExperiment(experiment);
		}
		
		experimentsFrameView.refreshTable();
		dispose();
	}
    
    private void cancelExperimentCreationActionPerformed(ActionEvent evt) {
		dispose();
    }
}