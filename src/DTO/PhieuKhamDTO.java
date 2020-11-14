package DTO;

import java.util.ArrayList;

import BUS.BenhNhanBUS;
import BUS.PhieuKhamBUS;

public class PhieuKhamDTO {
	
	private int MaPK;
	private String NgayKham;
	private String TrieuChung;
	private int MaBenh;
	private int MaBN;
	
	public int getMaPK() {
		return MaPK;
	}
	public void setMaPK(int maPK) {
		MaPK = maPK;
	}
	public String getNgayKham() {
		return NgayKham;
	}
	public void setNgayKham(String ngayKham) {
		NgayKham = ngayKham;
	}
	public String getTrieuChung() {
		return TrieuChung;
	}
	public void setTrieuChung(String trieuChung) {
		TrieuChung = trieuChung;
	}
	public int getMaBenh() {
		return MaBenh;
	}
	public void setMaBenh(int maBenh) {
		MaBenh = maBenh;
	}
	public int getMaBN() {
		return MaBN;
	}
	public void setMaBN(int maBN) {
		MaBN = maBN;
	}
	
	public BenhDTO getBenh() {
		PhieuKhamBUS phieuKhamBUS = new PhieuKhamBUS();
		return phieuKhamBUS.getBenhByID(MaBenh);
	}
	
	public BenhNhanDTO getBenhNhan() {
		BenhNhanBUS benhNhanBUS = new BenhNhanBUS();
		return benhNhanBUS.BenhNhanByID(MaBN);
	}
	}
