package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import BUS.ThuocBUS;
import DTO.ThuocDTO;
import DTO.ToaThuocDTO;

public class ToaThuocModel extends AbstractTableModel {

	private List<ToaThuocDTO> data = new ArrayList<>();
//	private String column[] = { "Mã thuốc", "Tên Thuốc", "Số lượng", "Cách dùng" };
	private String column[] = {"Tên Thuốc", "Số lượng", "Đơn Giá", "Thành tiền", "Cách dùng" };


	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public String getColumnName(int col) {
		// TODO Auto-generated method stub
		return column[col];
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ThuocBUS thuocBUS = new ThuocBUS();
		ThuocDTO t = thuocBUS.thuocByID(data.get(rowIndex).getMaThuoc());
		switch (columnIndex) {		// Mỗi lần kích chọn dòng trong table lập tức chạy 4 lần hàm  này là sao ta @@
		case 0:
			return t.getTenThuoc();
		case 1:
			return data.get(rowIndex).getSoLuong();
		case 2:
			return data.get(rowIndex).getDonGia();
		case 3:
			return data.get(rowIndex).getThanhtien();
		case 4:
			return thuocBUS.cachDungByID(t.getMaCD()).getCachDung();
		}
		return null;
	}
	
	public void addRow(ToaThuocDTO toa) {
		data.add(toa);
		fireTableDataChanged();
	}
	
	public ArrayList<ToaThuocDTO> getToaThuoc(){
		return (ArrayList<ToaThuocDTO>) data;
	}
	
	public void setToaThuoc(ArrayList<ToaThuocDTO> toathuoc) {
		this.data = toathuoc;
		fireTableDataChanged();
	}
	
	public void removeRow(int rowIndex) {
		data.remove(rowIndex);
		fireTableDataChanged();
	}
	
	public void removeAll() {
		data = new ArrayList<>();
		fireTableDataChanged();
	}
	
	public int getTongTien() {
		int total = 0;
		for(ToaThuocDTO t : data) {
			total += t.getThanhtien();
		}
		return total;
	}
}
