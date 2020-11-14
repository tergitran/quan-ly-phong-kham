package DTO;

public class ThuocDTO {
	int MaThuoc;
	String TenThuoc;
	int MaDVT;
	int DonGia;
	int SoLuongTon;
	int MaCD;
	
	public int getMaCD() {
		return MaCD;
	}
	public void setMaCD(int maCD) {
		MaCD = maCD;
	}
	
	public int getMaDVT() {
		return MaDVT;
	}
	public void setMaDVT(int maDVT) {
		MaDVT = maDVT;
	}

	
	public int getSoLuongTon() {
		return SoLuongTon;
	}
	public void setSoLuongTon(int soLuongTon) {
		SoLuongTon = soLuongTon;
	}
	public int getMaThuoc() {
		return MaThuoc;
	}
	public void setMaThuoc(int maThuoc) {
		MaThuoc = maThuoc;
	}
	public String getTenThuoc() {
		return TenThuoc;
	}
	public void setTenThuoc(String tenThuoc) {
		TenThuoc = tenThuoc;
	}

	
	public int getDonGia() {
		return DonGia;
	}
	public void setDonGia(int donGia) {
		DonGia = donGia;
	}
	
	public String toString() {
		return TenThuoc + " - SL: " + SoLuongTon + " - Đơn Giá: " + DonGia;
	}
}
