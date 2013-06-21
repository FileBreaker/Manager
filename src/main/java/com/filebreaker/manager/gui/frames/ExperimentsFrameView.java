/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filebreaker.manager.gui.frames;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

import com.filebreaker.manager.beans.Experiment;
import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.dialogs.NewExperimentJDialog;
import com.filebreaker.manager.gui.tables.IdentifiedTableModel;

/**
 *
 * @author danielortegaufano
 */
public class ExperimentsFrameView extends FrameView {

	private MainController mainController;
	
	private javax.swing.JButton exportButton;
    
    private javax.swing.JButton newButton;
    
    private javax.swing.JButton deleteButton;
    
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
    	mainPanel = new JPanel();
    	
        experimentsLabel = new javax.swing.JLabel();
        exportButton = new javax.swing.JButton();
        scrollPane = new javax.swing.JScrollPane();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        
        newExperimentJDialog = new NewExperimentJDialog(this.getFrame(), true, mainController, this);
        
        experimentsLabel.setText("Experimentos");

        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_down.gif"))); // NOI18N
        exportButton.setText("Exportar");
        exportButton.setEnabled(false);
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        experimentsTable = new javax.swing.JTable(){
			private static final long serialVersionUID = -2151838777943597951L;
        	
			@Override
			public boolean isCellEditable(int row, int column){
				// click on first column is for experiment name edition
				return column < 1;
			}
        };
        
        experimentsTable.setModel(getTableModel());
        experimentsTable.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent e) {
        		JTable target = (JTable) e.getSource();
                
        		int row = target.getSelectedRow();
                int column = target.getSelectedColumn();
                
                if(row != -1 && column != -1){
                	deleteButton.setEnabled(true);
            		exportButton.setEnabled(true);
            		
            		IdentifiedTableModel tableModel = (IdentifiedTableModel)target.getModel();
                    Integer selectedExperimentId = (Integer) tableModel.getModelId(row);
                    
                    // click on first column is for experiment name edition
                    if(column > 0){
                    	JFrame samplesJFrame = new SamplesJFrame(mainController, selectedExperimentId);
                    	samplesJFrame.setVisible(true);
                    }
                }
        	}
		});
        
        scrollPane.setViewportView(experimentsTable);

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
                    .add(deleteButton))
                .addContainerGap(8, Short.MAX_VALUE))
        );
        
        setComponent(mainPanel);
    }
    
    public void refreshTable(){
    	experimentsTable.setModel(getTableModel());
    	
    	if(experimentsTable.getSelectedRow() == -1 || experimentsTable.getSelectedRow() == -1){
    		deleteButton.setEnabled(false);
    		exportButton.setEnabled(false);
    	}
    }
    
	private DefaultTableModel getTableModel() {
		List<Experiment> experiments = mainController.getExperiments();
		
		return new IdentifiedTableModel(
            getModel(experiments),
            new String [] {
                "Nombre", "Fecha de creación", "Fecha de modificación"
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

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	mainController.exportExperiment();
    }                                        

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {   
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
}