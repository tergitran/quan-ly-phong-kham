package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MysqlConnection;
import DTO.CachDungDTO;
import DTO.DvtDTO;
import DTO.ThuocDTO;

public class ThuocBUS {
	
	MysqlConnection con;
	
	public ThuocBUS() {
		con = new MysqlConnection();
	}
	
	public String layMaThuoc() {
		String sql = "select max(MaThuoc) from thuoc";
		ResultSet rs = con.executeQuery(sql);
		
		int id = 0;
		try {
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.format("T%03d", id);
	}

	
	public ArrayList<ThuocDTO> tatCaThuoc() {
		String sql = "select * from THUOC";
		ResultSet rs = con.executeQuery(sql);
		ArrayList<ThuocDTO> dsThuoc = new ArrayList<ThuocDTO>();
		try {
			while(rs.next()) {
				ThuocDTO t = new ThuocDTO();
				t.setMaThuoc(rs.getInt(1));
				t.setTenThuoc(rs.getString(2));
				t.setMaCD(rs.getInt(3));
				t.setMaDVT(rs.getInt(4));
				t.setDonGia(rs.getInt(5));
				t.setSoLuongTon(rs.getInt(6));
				dsThuoc.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsThuoc;
	}
	
	public ThuocDTO thuocByID(int id) {
		String sql = "select * from THUOC where MaThuoc = " + id;
		System.out.println(sql);
		ResultSet rs = con.executeQuery(sql);
		ThuocDTO thuoc = new ThuocDTO();
		try {
			if(rs.next()) {
				thuoc.setMaThuoc(rs.getInt("MaThuoc"));
				thuoc.setTenThuoc(rs.getString("TenThuoc"));
				thuoc.setMaCD(rs.getInt("MaCD"));
				thuoc.setMaDVT(rs.getInt("MaDVT"));
				thuoc.setDonGia(rs.getInt("DonGia"));
				thuoc.setSoLuongTon(rs.getInt("SoLuongton"));
				
//				thuoc.setMaThuoc(rs.getInt(0));
//				thuoc.setTenThuoc(rs.getString(1));
//				thuoc.setMaCD(rs.getInt(2));
//				thuoc.setMaDVT(rs.getInt(3));
//				thuoc.setDonGia(rs.getInt(4));
//				thuoc.setSoLuongTon(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Xảy ra ngoại lệ");
		}
		return thuoc;
	}
	
	public void themThuoc(ThuocDTO thuoc) {
		String sql = "insert into thuoc values(null, '" + thuoc.getTenThuoc() + "', " + thuoc.getMaCD() + ", " + thuoc.getMaDVT() + ", " + thuoc.getDonGia() + ", " + thuoc.getSoLuongTon() + ")";
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public void suaThuoc(ThuocDTO thuoc) {
		String sql = "update thuoc set TenThuoc = '" + thuoc.getTenThuoc() + "', MaCD = " + thuoc.getMaCD() + ", MaDVT = " + thuoc.getMaDVT() + ", DonGia = " 
				+ thuoc.getDonGia() + ", SoLuongTon = " + thuoc.getSoLuongTon() + " where MaThuoc = " + thuoc.getMaThuoc();
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	


	
	
	//BẢNG CÁCH DÙNG
	
	public CachDungDTO cachDungByID(int id) {
		String sql = "select * from CACHDUNG where MaCD = " + id;
		ResultSet rs = con.executeQuery(sql);
		CachDungDTO cd = new CachDungDTO();
		try {
			if(rs.next()) {
				cd.setMaCD(id);
				cd.setCachDung(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cd;
	}
	
	public void themCachDung(String CachDung) {
		String sql = "insert into cachdung values(null,'" + CachDung +"')";
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public void suaCachDung(CachDungDTO cd) {
		String sql = "update cachdung set CachDung = '" + cd.getCachDung() + "' where MaCD = " + cd.getMaCD();
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public ArrayList<CachDungDTO> tatCaCachDung(){
		String sql = "select * from cachdung";
		ResultSet rs = con.executeQuery(sql);
		
		ArrayList<CachDungDTO>data = new ArrayList<CachDungDTO>();
		try {
			while(rs.next()) {
				CachDungDTO cd = new CachDungDTO();
				cd.setMaCD(rs.getInt(1));
				cd.setCachDung(rs.getString(2));
				data.add(cd);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	//BẢNG ĐƠN VỊ TÍNH
	
	public DvtDTO donViTinhByID(int id) {
		String sql = "select * from DVT where MaDvt = " + id;
		ResultSet rs = con.executeQuery(sql);
		DvtDTO d = new DvtDTO();
		try {
			if(rs.next()) {
				d.setMaDVT(id);
				d.setTenDVT(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}
	
	public ArrayList<DvtDTO> tatCaDvT(){
		String sql = "select * from dvt";
		ResultSet rs = con.executeQuery(sql);
		
		ArrayList<DvtDTO>data = new ArrayList<DvtDTO>();
		try {
			while(rs.next()) {
				DvtDTO d = new DvtDTO();
				d.setMaDVT(rs.getInt(1));
				d.setTenDVT(rs.getString(2));
				data.add(d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return data;
	}
	
	public void themDonViTinh(String dvt) {
		String sql = "insert into dvt values(null, '" + dvt + "')";
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public void xoaDonViTinh(int MaDVT) {
		String sql = "delete from dvt where MaDVT = " + MaDVT;
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public void suaDonViTinh(DvtDTO dvt) {
		String sql = "update dvt set TenDVT = '" + dvt.getTenDVT() + "' where MaDVT = " + dvt.getMaDVT();
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	

	
	
	
}
