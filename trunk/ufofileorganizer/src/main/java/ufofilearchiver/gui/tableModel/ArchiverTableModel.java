package ufofilearchiver.gui.tableModel;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import ufofileorganizer.core.node.TreeNodeVector;
import utils.string.StringFormatter;

public class ArchiverTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String[] columnNames = {"Name","Size","Type","Modified"};
	private Vector<String[]> cells = new Vector<String[]>();
	private TreeNodeVector _TreeNodeVector;

	public ArchiverTableModel(TreeNodeVector aTreeNodeVector) {
		_TreeNodeVector = aTreeNodeVector;
		update();
	}

	@Override
	public int getColumnCount() {
		if (columnNames != null) {
			return columnNames.length;
		}
		return 0;
	}

	@Override
	public String getColumnName(int c) {
		return columnNames[c];
	}

	@Override
	public int getRowCount() {
		if (cells != null) {
			return cells.size();
		}
		return 0;
	}

	@Override
	public Object getValueAt(int riga, int colonna) {
		return cells.get(riga)[colonna];
	}

	private void update() {
		if (_TreeNodeVector == null) {
			return;
		}
		/*
		TreeNodeVector tempChildrenVector = _TreeNodeVector.getAllChildren();
		
		for (int i = 0; i<tempChildrenVector.size();i++){
                 */
                for (int i = 0; i<_TreeNodeVector.size();i++){
			String[] row_temp = new String[columnNames.length];
			row_temp[0] = ((TreeNodeVector) _TreeNodeVector.get(i) ).getName();
			row_temp[1] =  StringFormatter.formatSize( ((TreeNodeVector) _TreeNodeVector.get(i) ).getSize().toString());
			row_temp[2] = ((TreeNodeVector) _TreeNodeVector.get(i) ).getType();
			row_temp[3] = StringFormatter.formatDate( ((TreeNodeVector) _TreeNodeVector.get(i) ).getModified().toString());
			cells.add(row_temp);
		}

		this.fireTableDataChanged();
		this.fireTableStructureChanged();

	}

}
