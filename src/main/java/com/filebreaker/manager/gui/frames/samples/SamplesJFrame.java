/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filebreaker.manager.gui.frames.samples;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.dialogs.DuplicateSampleJDialog;
import com.filebreaker.manager.gui.frames.RefreshableFrame;
import com.filebreaker.manager.gui.tables.IdentifiedTableModel;
import com.filebreaker.manager.gui.utils.ExportFileChooserFactory;

/**
 *
 * @author danielortegaufano
 */
public class SamplesJFrame extends javax.swing.JFrame implements RefreshableFrame {

	private static final long serialVersionUID = 5223108555053502278L;
	
	private MainController mainController;

    private javax.swing.JButton exportButton;
    
    private javax.swing.JButton newButton;
    
    private javax.swing.JButton deleteButton;
    
    private javax.swing.JButton duplicateButton;
    
    private javax.swing.JLabel sampleLabel;
    
    private javax.swing.JScrollPane scrollPane;
    
    private javax.swing.JTable samplesTable; 
    
    private Integer experimentId;
    
    private JDialog duplicateSampleDialog;

    public SamplesJFrame(MainController mainController, Integer experimentId) {
    	this.experimentId = experimentId;
    	this.mainController = mainController;
        
    	initComponents();   
    }
                   
    private void initComponents() {
    	
    	this.setResizable(false);
    	
    	Experiment experiment = mainController.getExperiment(this.experimentId);
    	this.setTitle("Muestras del experimento: " + experiment.getName()); // NOI18N
    	
        sampleLabel = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        samplesTable = new javax.swing.JTable();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        duplicateButton = new javax.swing.JButton();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        sampleLabel.setText("Muestras");

        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_down.gif"))); // NOI18N
        exportButton.setText("Exportar");
        exportButton.setEnabled(false);
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        samplesTable.setModel(getTableModel());
        samplesTable.addMouseListener(new MouseAdapter() {
        	@SuppressWarnings("unchecked")
			public void mouseClicked(MouseEvent e) {
        		JTable target = (JTable) e.getSource();
        		int row = target.getSelectedRow();
                
        		if(row != -1){
        			exportButton.setEnabled(true);
        			deleteButton.setEnabled(true);
        			duplicateButton.setEnabled(true);
        			
        			if(e.getClickCount() > 1){
        				IdentifiedTableModel tableModel = (IdentifiedTableModel)target.getModel();
        				Map<String, Object> composedSampleId = (Map<String, Object>) tableModel.getModelId(row);
                	
        				JFrame sampleJFrame = new SampleJFrame(mainController, experimentId, (Integer)composedSampleId.get("sampleId"));
        				sampleJFrame.setVisible(true);
        			}
        		}
        	}
		});
        
        scrollPane.setViewportView(samplesTable);

        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note_new.gif"))); // NOI18N
        newButton.setText("Nuevo");
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note_delete.gif"))); // NOI18N
        deleteButton.setText("Eliminar");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        
        duplicateButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note_new.gif"))); // NOI18N
        duplicateButton.setText("Duplicar");
        duplicateButton.setEnabled(false);
        duplicateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duplicateButtonActionPerformed(evt);
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
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(scrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(sampleLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(exportButton)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(newButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deleteButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(duplicateButton)
                        .add(0, 239, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(exportButton)
                    .add(layout.createSequentialGroup()
                        .add(5, 5, 5)
                        .add(sampleLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(newButton)
                    .add(deleteButton)
                    .add(duplicateButton))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }
    
    private DefaultTableModel getTableModel() {
    	List<Sample> samples = mainController.getSamples(this.experimentId);
		
    	return new IdentifiedTableModel(
            getModel(samples),
            new String [] {
                "Identificador", "Fecha de creación", "Fecha de modificación"
            },
            getModelIds(samples)
        );
	}
    
	private List<Object> getModelIds(List<Sample> samples){
		List<Object> result = new ArrayList<Object>();
		
		for(Sample sample : samples){
			Map<String, Object> composedId = new HashMap<String, Object>();
			
			composedId.put("sampleId", sample.getId());
			composedId.put("experimentId", sample.getExperimentId());
			
			result.add(composedId);
		}
		
		return result;
	}
    
    private Object[][] getModel(List<Sample> samples){
    	Object [][] result = null;
		
		if(samples != null && !samples.isEmpty()){
			result = new Object[samples.size()][3];
		
			for(int i = 0; i < samples.size(); i++){
				Sample sample = samples.get(i);
			
				result[i][0] = sample.getId();
				result[i][1] = sample.getCreationDate();
				result[i][2] = sample.getModificationDate();
			}
		}
		
		return result;
    }

    @SuppressWarnings("unchecked")
	private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	Set<Integer> selectedSamplesId = new HashSet<Integer>();
    	int [] rows = samplesTable.getSelectedRows();
    	IdentifiedTableModel tableModel = (IdentifiedTableModel) samplesTable.getModel();
    	Integer experimentId = null;
    	
    	for(int row : rows){
    		Map<String, Object> composedId = (Map<String, Object>)tableModel.getModelId(row);
    		selectedSamplesId.add((Integer) composedId.get("sampleId"));
    		
    		if(experimentId == null){
    			experimentId = (Integer)composedId.get("experimentId");
    		}
    	}
    	
    	JFileChooser fileChooser = ExportFileChooserFactory.createFileChooser();
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File saveFile = fileChooser.getSelectedFile();
			
			try {
				mainController.exportSamples(selectedSamplesId, experimentId, saveFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
    }                                        

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) { 
    	JFrame sampleEditorJFrame = new SampleEditorJFrame(mainController, this.experimentId, this);
    	sampleEditorJFrame.setVisible(true);
    }
    
    public void refresh(){
    	samplesTable.setModel(getTableModel());
    	
    	if(samplesTable.getSelectedRow() == -1){
    		deleteButton.setEnabled(false);
    		exportButton.setEnabled(false);
    		duplicateButton.setEnabled(false);
    	}
    }

    @SuppressWarnings("unchecked")
	private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	Set<Integer> selectedSamplesId = new HashSet<Integer>();
    	int [] rows = samplesTable.getSelectedRows();
    	IdentifiedTableModel tableModel = (IdentifiedTableModel) samplesTable.getModel();
    	Integer experimentId = null;
    	
    	for(int row : rows){
    		Map<String, Object> composedId = (Map<String, Object>)tableModel.getModelId(row);
    		selectedSamplesId.add((Integer) composedId.get("sampleId"));
    		
    		if(experimentId == null){
    			experimentId = (Integer)composedId.get("experimentId");
    		}
    	}
    	
    	mainController.deleteSamples(experimentId, selectedSamplesId);
    	refresh();
    }
    
    @SuppressWarnings("unchecked")
	private void duplicateButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	int row = samplesTable.getSelectedRow();
    	
    	if(row != -1){
	    	IdentifiedTableModel tableModel = (IdentifiedTableModel) samplesTable.getModel();
	    	
	    	Map<String, Object> composedId = (Map<String, Object>)tableModel.getModelId(row);
			
	    	Integer sampleId = (Integer) composedId.get("sampleId");
			Integer experimentId = (Integer)composedId.get("experimentId");
			
			duplicateSampleDialog = new DuplicateSampleJDialog(this, true, mainController, sampleId, experimentId);
	    	duplicateSampleDialog.setVisible(true);
    	}
	}
}