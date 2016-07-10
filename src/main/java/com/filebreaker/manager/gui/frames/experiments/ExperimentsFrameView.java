/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filebreaker.manager.gui.frames.experiments;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.dialogs.ExperimentJDialog;
import com.filebreaker.manager.gui.frames.samples.SamplesJFrame;
import com.filebreaker.manager.gui.i18n.Literals;
import com.filebreaker.manager.gui.tables.IdentifiedTableModel;
import com.filebreaker.manager.gui.utils.ExportFileChooserFactory;

/**
 *
 * @author danielortegaufano
 */
public class ExperimentsFrameView extends FrameView {

	private MainController mainController;
	
	private javax.swing.JButton exportButton;
    
    private javax.swing.JButton newButton;
    
    private javax.swing.JButton deleteButton;
    
    private javax.swing.JButton editButton;
    
    private javax.swing.JLabel experimentsLabel;
    
    private javax.swing.JScrollPane scrollPane;
    
    private javax.swing.JTable experimentsTable;
    
    private javax.swing.JPanel mainPanel;

	private JDialog newExperimentJDialog;
	
    public ExperimentsFrameView(Application app, MainController mainController) {
		super(app);
		this.mainController = mainController;
		
		initComponents();
	}
                       
    private void initComponents() {
    	this.getFrame().setResizable(false);
    	
    	mainPanel = new JPanel();
    	this.getFrame().setTitle(Literals.getInstance().getString("filebreaker.title"));
    	
        experimentsLabel = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        
        experimentsLabel.setText(Literals.getInstance().getString("experiments.title"));

        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_down.gif"))); // NOI18N
        exportButton.setText(Literals.getInstance().getString("experiments.export"));
        exportButton.setEnabled(false);
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
					exportButtonActionPerformed(evt);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
        
        experimentsTable = new javax.swing.JTable(){
			private static final long serialVersionUID = 8174336416229179263L;

			@Override
        	public boolean isCellEditable(int row, int column) {
        		return false;
        	}
        };
        experimentsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        experimentsTable.setModel(getTableModel());
        experimentsTable.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		JTable target = (JTable) e.getSource();
        		int row = target.getSelectedRow();
                
                if(row != -1){
                	deleteButton.setEnabled(true);
            		exportButton.setEnabled(true);
            		editButton.setEnabled(true);
            		
            		if(e.getClickCount() > 1){
            			IdentifiedTableModel tableModel = (IdentifiedTableModel)target.getModel();
            			Integer selectedExperimentId = (Integer) tableModel.getModelId(row);

            			JFrame samplesJFrame = new SamplesJFrame(mainController, selectedExperimentId);
            			samplesJFrame.setVisible(true);
            		}
                }
        	}
		});
        
        scrollPane.setViewportView(experimentsTable);

        newButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note_new.gif"))); // NOI18N
        newButton.setText(Literals.getInstance().getString("experiments.new"));
        newButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newButtonActionPerformed(evt);
            }
        });

        deleteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note_delete.gif"))); // NOI18N
        deleteButton.setText(Literals.getInstance().getString("experiments.delete"));
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });
        
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/note.gif"))); // NOI18N
        editButton.setText(Literals.getInstance().getString("experiments.name.edit"));
        editButton.setEnabled(false);
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	editButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(mainPanel);
        
        mainPanel.setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(scrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .add(layout.createSequentialGroup()
                                .add(experimentsLabel)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .add(exportButton)))
                        .addContainerGap())
                    .add(layout.createSequentialGroup()
                        .add(newButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deleteButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(editButton)
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
                        .add(experimentsLabel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(scrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 246, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(newButton)
                    .add(deleteButton)
                    .add(editButton))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        
        setComponent(mainPanel);
    }
    
    public void refreshTable(){
    	experimentsTable.setModel(getTableModel());
    	
    	if(experimentsTable.getSelectedRow() == -1){
    		deleteButton.setEnabled(false);
    		exportButton.setEnabled(false);
    		editButton.setEnabled(false);
    	}
    }
    
	private DefaultTableModel getTableModel() {
		List<Experiment> experiments = mainController.getExperiments();
		
		return new IdentifiedTableModel(
            getModel(experiments),
            new String [] {
                Literals.getInstance().getString("experiments.name"), 
                Literals.getInstance().getString("experiments.creation.date"), 
                Literals.getInstance().getString("experiments.modification.date")
            },
            getModelIds(experiments)
        );
	}
	
	private List<Object> getModelIds(List<Experiment> experiments){
		List<Object> result = new ArrayList<Object>();
		
		for(Experiment experiment : experiments){
			result.add(experiment.getId());
		}
		
		return result;
	}
	
	private Object [][] getModel(List<Experiment> experiments){
		Object [][] result = null;
		
		if(experiments != null && !experiments.isEmpty()){
			result = new Object[experiments.size()][3];
		
			for(int i = 0; i < experiments.size(); i++){
				Experiment experiment = experiments.get(i);
			
				result[i][0] = experiment.getName();
				result[i][1] = experiment.getCreationDate();
				result[i][2] = experiment.getModificationDate();
			}
		}
		
		return result;
	}

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) throws FileNotFoundException, IOException {
    	int row = experimentsTable.getSelectedRow();
    	IdentifiedTableModel tableModel = (IdentifiedTableModel) experimentsTable.getModel();
		Integer selectedExperimentId = (Integer) tableModel.getModelId(row);
		
    	JFileChooser fileChooser = ExportFileChooserFactory.createFileChooser();
		int returnValue = fileChooser.showSaveDialog(null);

		if (returnValue == JFileChooser.APPROVE_OPTION) {
			File saveFile = fileChooser.getSelectedFile();
			mainController.exportExperiment(selectedExperimentId, saveFile);
		}
    }                                        

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	newExperimentJDialog = new ExperimentJDialog(this.getFrame(), true, mainController, this);
    	newExperimentJDialog.setVisible(true);
    }                                        

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	int row = experimentsTable.getSelectedRow();
    	
    	if(row != -1){
    		IdentifiedTableModel tableModel = (IdentifiedTableModel) experimentsTable.getModel();
    		Integer selectedExperimentId = (Integer) tableModel.getModelId(row);
    	
    		mainController.deleteExperiment(selectedExperimentId);
    		
    		this.refreshTable();
    	}
    }    
    
    private void editButtonActionPerformed(ActionEvent evt) {
    	int row = experimentsTable.getSelectedRow();
    	
    	if(row != -1){
    		IdentifiedTableModel tableModel = (IdentifiedTableModel) experimentsTable.getModel();
    		Integer selectedExperimentId = (Integer) tableModel.getModelId(row);
    		
    		Experiment experimentToEdit = mainController.getExperiment(selectedExperimentId);
    		
    		newExperimentJDialog = new ExperimentJDialog(this.getFrame(), true, mainController, this, experimentToEdit);
        	newExperimentJDialog.setVisible(true);
    	}
	}
}