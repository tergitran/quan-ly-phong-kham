package Model;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import BUS.BenhNhanBUS;
import BUS.PhieuKhamBUS;
import DTO.BenhDTO;
import DTO.BenhNhanDTO;
import DTO.PhieuKhamDTO;

public class PhieuKhamModel extends AbstractTableModel{
	private List<PhieuKhamDTO> data = new ArrayList<>();
	private String[] columns = {"Mã phiếu khám", "Ngày khám", "Chuẩn đoán", "Bệnh nhân"};

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
		switch (columnIndex) {
		case 0:
			return data.get(rowIndex).getMaPK();
		case 1:
			return data.get(rowIndex).getNgayKham();
//		case 2:
//			return data.get(rowIndex).getTrieuChung();
		case 2:
			PhieuKhamBUS phieuKhamBUS = new PhieuKhamBUS();
			BenhDTO b = phieuKhamBUS.getBenhByID(data.get(rowIndex).getMaBenh());
			System.out.println("Benh: " + b.getTenBenh());
			return b.getTenBenh();
		case 3:
			BenhNhanBUS benhNhanBUS = new BenhNhanBUS();
			BenhNhanDTO bn = benhNhanBUS.BenhNhanByID(data.get(rowIndex).getMaBN());
			return bn.getHoTen();
		default:
			break;
		}
		return null;
	}
	
	public void setData(ArrayList<PhieuKhamDTO> data) {
		this.data = data;
		fireTableDataChanged();
	}
	
	public PhieuKhamDTO getRow(int rowIndex) {
		return data.get(rowIndex);
	}

}
