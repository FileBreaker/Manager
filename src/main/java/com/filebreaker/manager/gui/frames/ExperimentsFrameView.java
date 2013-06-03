/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filebreaker.manager.gui.frames;

import javax.swing.JDialog;
import javax.swing.JPanel;

import org.jdesktop.application.Application;
import org.jdesktop.application.FrameView;

import com.filebreaker.manager.controllers.MainController;
import com.filebreaker.manager.gui.dialogs.NewExperimentJDialog;

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
        experimentsTable = new javax.swing.JTable();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        
        // TODO: chusta, crear con parent como dios manda
        newExperimentJDialog = new NewExperimentJDialog((java.awt.Frame) null, true, mainController);

        experimentsLabel.setText("Experimentos");

        exportButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/page_down.gif"))); // NOI18N
        exportButton.setText("Exportar");
        exportButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportButtonActionPerformed(evt);
            }
        });

        experimentsTable.setModel(new javax.swing.table.DefaultTableModel(
            mainController.getExperiments(),
            new String [] {
                "Nombre", "Fecha de creación", "Fecha de modificación"
            }
        ));
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

    private void exportButtonActionPerformed(java.awt.event.ActionEvent evt) {  
    	mainController.exportExperiment();
    }                                        

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    	newExperimentJDialog.setVisible(true);
    }                                        

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {  
    	mainController.deleteExperiment();
    }        
}