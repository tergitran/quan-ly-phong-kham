package DTO;

public class DvtDTO {
	int MaDVT;
	String TenDVT;
	public int getMaDVT() {
		return MaDVT;
	}
	public void setMaDVT(int maDVT) {
		MaDVT = maDVT;
	}
	public String getTenDVT() {
		return TenDVT;
	}
	public void setTenDVT(String tenDVT) {
		TenDVT = tenDVT;
	}
	
	public String toString() {
		return MaDVT + " - " + TenDVT;
	}
}
