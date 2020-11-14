package DTO;

public class ToaThuocDTO {
	int MaPK;
	int MaThuoc;
	int SoLuong;
	int DonGia;
	int thanhtien;
	
	public int getMaPK() {
		return MaPK;
	}
	public void setMaPK(int maPK) {
		MaPK = maPK;
	}

	public int getSoLuong() {
		return SoLuong;
	}
	public void setSoLuong(int soLuong) {
		SoLuong = soLuong;
	}
	
	public int getDonGia() {
		return DonGia;
	}
	public void setDonGia(int donGia) {
		DonGia = donGia;
	}
	
	public int getMaThuoc() {
		return MaThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		MaThuoc = maThuoc;
	}
	public int getThanhtien() {
		this.thanhtien = SoLuong*DonGia;
		return thanhtien;
	}
//	public void setThanhtien() {
//		this.thanhtien = SoLuong*DonGia;
//	}
	
}
