package Model;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import BUS.ThuocBUS;
import DTO.ThuocDTO;

public class ThuocModel extends AbstractTableModel{
	
	private ArrayList<ThuocDTO> data = new ArrayList<ThuocDTO>();
	private String[] columns = {"Mã thuốc", "Tên thuốc", "Cách Dùng", "Đơn vị tính", "Đơn giá", "Số lượng tồn"};
	private ThuocBUS thuocBUS = new ThuocBUS();

	
	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public String getColumnName(int column) {
		return columns[column];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
//		System.out.println("Access Database");

		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).getMaThuoc();
		case 1:
			return data.get(rowIndex).getTenThuoc();
		case 2:			
			return thuocBUS.cachDungByID(data.get(rowIndex).getMaCD()).getCachDung();
		case 3:
			return thuocBUS.donViTinhByID(data.get(rowIndex).getMaDVT()).getTenDVT();
		case 4:
			return data.get(rowIndex).getDonGia();
		case 5:
			return data.get(rowIndex).getSoLuongTon();
		default:
			break;
		}
		return null;
	}
	
	public void setData(ArrayList<ThuocDTO> data) {
		this.data = data;
		fireTableDataChanged();
	}
	
	public ThuocDTO getRow(int index) {
		return data.get(index);
	}
	
	public void changeRow(int indexRow, ThuocDTO t) {
		data.set(indexRow, t);
		thuocBUS.suaThuoc(t);
		fireTableDataChanged();
	}
	
}
