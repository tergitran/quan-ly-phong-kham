package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MysqlConnection;
import DTO.BenhNhanDTO;
import DTO.PhieuKhamDTO;
import DTO.ThuocDTO;

public class BenhNhanBUS {
	MysqlConnection con;

	public BenhNhanBUS() {
		con = new MysqlConnection();
	}

	public String layMaBenhNhanMoi() {
		String sql = "select max(MaBN) from BENHNHAN";
		ResultSet rs = con.executeQuery(sql);
		int MaBN = 0;
		try {
			if (rs.next()) {
				MaBN = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.format("BN%03d", MaBN);
	}

	public int themBenhNhan(BenhNhanDTO bn) {
		String sql = "insert into BENHNHAN values(null,'" + bn.getHoTen() + "', '" + bn.getNgaySinh() + "', '"
				+ bn.getGioiTinh() + "', '" + bn.getDiaChi() + "')";
		System.out.print(sql);
		return con.excuteUpdate(sql);
	}

	public BenhNhanDTO BenhNhanByID(int id) {
		BenhNhanDTO bn = new BenhNhanDTO();
		String sql = "Select * from BenhNhan where mabn = " + id;
//		System.out.println(sql);
		ResultSet rs = con.executeQuery(sql);
		try {
			if (rs.next()) {
				bn.setMaBN(rs.getInt("MaBN"));
				bn.setHoTen(rs.getString("HoTen"));
				bn.setGioiTinh(rs.getString("GioiTinh"));
				bn.setDiaChi(rs.getString("DiaChi"));
				bn.setNgaySinh(rs.getString("NgaySinh"));
			} else bn = null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bn;
	}
	
	public ArrayList<BenhNhanDTO> tatCaBenhNhan(){
		String sql = "select * from benhnhan";
		ResultSet rs = con.executeQuery(sql);
		ArrayList<BenhNhanDTO> dsBenhNhan = new ArrayList<BenhNhanDTO>();
		try {
			while(rs.next()) {
				BenhNhanDTO b = new BenhNhanDTO();
				b.setMaBN(rs.getInt(1));
				b.setHoTen(rs.getString(2));
				b.setNgaySinh(rs.getString(3));
				b.setGioiTinh(rs.getString(4));
				b.setDiaChi(rs.getString(5));
				dsBenhNhan.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dsBenhNhan;
	}
	
	public ArrayList<PhieuKhamDTO> phieuKhamTheoBenhNhan(int MaBN){
		String sql = "select * from PHIEUKHAM where MaBN = " + MaBN;
		ResultSet rs = con.executeQuery(sql);
		ArrayList<PhieuKhamDTO> ds = new ArrayList<PhieuKhamDTO>();
		try {
			while (rs.next()) {
				PhieuKhamDTO pk = new PhieuKhamDTO();
				pk.setMaPK(rs.getInt("MaPK"));
				pk.setNgayKham(rs.getString("NgayKham"));
				pk.setMaBenh(rs.getInt("MaBenh"));
				pk.setMaBN(rs.getInt("MaBN"));
				pk.setTrieuChung(rs.getString("TrieuChung"));
				ds.add(pk);
			}
			return ds;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public void suaBenhNhan(BenhNhanDTO bn) {
		String sql = "update benhnhan set HoTen = '" + bn.getHoTen()+ "', NgaySinh = '" + bn.getNgaySinh()+"', GioiTinh = '" 
				+ bn.getGioiTinh()+ "', DiaChi = '"+ bn.getDiaChi() +"' where MaBN = " + bn.getMaBN();
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
}
