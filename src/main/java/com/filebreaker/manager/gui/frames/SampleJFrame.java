/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.filebreaker.manager.gui.frames;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;

import com.filebreaker.manager.beans.Sample;
import com.filebreaker.manager.controllers.MainController;

public class SampleJFrame extends javax.swing.JFrame {

	private static final long serialVersionUID = 8162818230491268811L;
	
	private MainController mainController;
	
	private javax.swing.JButton editButton;
    
	private javax.swing.JLabel chronometerLabel;
    
    private javax.swing.JLabel oscillationNumberLabel;
    
    private javax.swing.JLabel stateLabel;
    
    private javax.swing.JPanel jPanel1;
    
    private javax.swing.JPanel jPanel2;
    
    private javax.swing.JScrollPane scrollPane;
    
    private javax.swing.JSlider switcherSlider;
    
    private javax.swing.JTabbedPane executionTabbedPane;
    
    private javax.swing.JTable propertiesTable;
    
    private Integer experimentId;
    
    private Integer sampleId;
	
    public SampleJFrame(MainController mainController, Integer experimentId, Integer sampleId) {
    	this.mainController = mainController;
        this.experimentId = experimentId;
        this.sampleId = sampleId;
        
    	initComponents();        
    }

	private void initComponents() {
        executionTabbedPane = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        oscillationNumberLabel = new javax.swing.JLabel();
        chronometerLabel = new javax.swing.JLabel();
        stateLabel = new javax.swing.JLabel();
        switcherSlider = new javax.swing.JSlider();
        jPanel2 = new javax.swing.JPanel();
        scrollPane = new javax.swing.JScrollPane();
        propertiesTable = new javax.swing.JTable();
        editButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        oscillationNumberLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        oscillationNumberLabel.setText("0 oscilaciones");

        chronometerLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        chronometerLabel.setText("00:00:00");

        stateLabel.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        stateLabel.setText("encendido");

        switcherSlider.setMaximum(1);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
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
                .addContainerGap(79, Short.MAX_VALUE))
        );

        executionTabbedPane.addTab("Ejecución", jPanel1);

        propertiesTable.setModel(getTableModel());
        scrollPane.setViewportView(propertiesTable);

        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/interface_dialog.gif"))); // NOI18N
        editButton.setText("Editar");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
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
                .add(scrollPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 168, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        executionTabbedPane.addTab("Datos de la muestra", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .add(executionTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 313, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, executionTabbedPane, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 239, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }
	
	protected void editButtonActionPerformed(ActionEvent evt) {
		JFrame sampleEditorJFrame = new SampleEditorJFrame(mainController, experimentId, sampleId);
		sampleEditorJFrame.setVisible(true);
	}

	private DefaultTableModel getTableModel() {
    	Sample sample = mainController.getSample(experimentId, sampleId);
		
    	return new DefaultTableModel(
            getModel(sample),
            new String [] {
                "Propiedad", "Valor"
            }
        );
	}
    
    private Object[][] getModel(Sample sample){
    	Object [][] result = null;
		
		if(sample != null){
			result = new Object[15][2];
		
			result[0][0] = "id";
			result[0][1] = sample.getId();
			result[1][0] = "diámetro apical";
			result[1][1] = sample.getApicalDiameter();
			result[2][0] = "ángulo de curvatura";
			result[2][1] = sample.getCurvatureAngle();
			result[3][0] = "radio de curvatura";
			result[3][1] = sample.getCurvatureRadius();
			result[4][0] = "velocidad del conducto";
			result[4][1] = sample.getDuctSpeed();
			result[5][0] = "velocidad angular del motor";
			result[5][1] = sample.getEngineAngularSpeed();
			result[6][0] = "torque del motor";
			result[6][1] = sample.getEngineTorque();
			result[7][0] = "identificador del experimento";
			result[7][1] = sample.getExperimentId();
			result[8][0] = "tipo de lima";
			result[8][1] = sample.getFileType();
			result[9][0] = "identificador de la muestra";
			result[9][1] = sample.getId();
			result[10][0] = "composición del metal";
			result[10][1] = sample.getMetalCompositionId();
			result[11][0] = "fecha de modificación";
			result[11][1] = sample.getModificationDate();
			result[12][0] = "tipo de movimiento";
			result[12][1] = sample.getMovementTypeId();
			result[13][0] = "tipo de studio";
			result[13][1] = sample.getStudyTypeId();
			result[14][0] = "nº de usos";
			result[14][1] = sample.getUses();
		}
		
		return result;
    }
}
