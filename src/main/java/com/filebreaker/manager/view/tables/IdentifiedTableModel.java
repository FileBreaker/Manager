package com.filebreaker.manager.view.tables;

import java.util.List;

import javax.swing.table.DefaultTableModel;

public class IdentifiedTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 2736567825790223553L;
	
	private List<Object> ids;
	
	private boolean editableName;
	
	public IdentifiedTableModel(Object [][] model, String [] titles, List<Object> ids){
		super(model, titles);
		this.ids = ids;

		editableName = false;
	}
	
	public Object getModelId(int index){
		return ids.get(index);
	}

	public List<Object> getIds() {
		return ids;
	}

	public void setIds(List<Object> ids) {
		this.ids = ids;
	}
	
	public void setEditableNameCell(boolean value){
		this.editableName = value;
	}

	
	@Override
	public boolean isCellEditable(int row, int column) {
		return column == 0 && editableName;
	}
}