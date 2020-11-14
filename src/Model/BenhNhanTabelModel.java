package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import DTO.BenhNhanDTO;

public class BenhNhanTabelModel extends AbstractTableModel{
	
	private List<BenhNhanDTO> data = new ArrayList<BenhNhanDTO>();
	private String[] columns = {"Mã bệnh nhân", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ"};
	
	public BenhNhanTabelModel(ArrayList<BenhNhanDTO> data) {
		this.data = data;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).getMaBN();
		case 1:
			return data.get(rowIndex).getHoTen();
		case 2:
			return data.get(rowIndex).getNgaySinh();
		case 3:
			return data.get(rowIndex).getGioiTinh();
		case 4:
			return data.get(rowIndex).getDiaChi();
		}
		return null;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}
	
	public BenhNhanDTO getRow(int rowIndex) {
		return data.get(rowIndex);
	}
	
	public void suaBenhNhan(int rowIndex, BenhNhanDTO bn) {
		System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		data.set(rowIndex, bn);
		fireTableDataChanged();
	}
}
