package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import DAO.MysqlConnection;
import DTO.BenhDTO;
import DTO.PhieuKhamDTO;
import DTO.ToaThuocDTO;

public class PhieuKhamBUS {
	MysqlConnection con;

	public PhieuKhamBUS() {
		con = new MysqlConnection();
	}

	public String layMaPhieuKham() {
		String sql = "select max(mapk) from PHIEUKHAM";
		ResultSet rs = con.executeQuery(sql);

		int id = 0;
		try {
			if (rs.next()) {
				id = rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return String.format("PK%05d", id);
	}


	public void luuPhieuKham(PhieuKhamDTO pk) {
		String sql = "insert into PHIEUKHAM values(null,'" + pk.getNgayKham() + "', '" + "" + pk.getTrieuChung() + "', "
				+ pk.getMaBenh() + ", " + pk.getMaBN() + ")";
		System.out.print(sql);
		con.excuteUpdate(sql);
	}

	public void luuToaThuoc(ArrayList<ToaThuocDTO> tt) {
		for (ToaThuocDTO t : tt) {
			String sql = "insert into TOATHUOC values(" + t.getMaPK() + ", " + t.getMaThuoc() + ", " + t.getSoLuong()
					+ ", " + t.getDonGia() + ")";
			System.out.print(sql);
			con.excuteUpdate(sql);
		}
	}

	public ArrayList<PhieuKhamDTO> danhSachPhieuKhamTheoNgay(String ngay) {
		String sql = "select * from PHIEUKHAM where ngaykham = '" + ngay + "'";
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	
	// TOA THUỐC
	
	public ArrayList<ToaThuocDTO> toaThuoc(int mapk){
		String sql = "select * from toathuoc where mapk = " + mapk + "";
		System.out.println(sql);
		ResultSet rs = con.executeQuery(sql);
		ArrayList<ToaThuocDTO> ds = new ArrayList<ToaThuocDTO>();
		try {
			while (rs.next()) {
				ToaThuocDTO t = new ToaThuocDTO();
				t.setMaPK(mapk);
				t.setMaThuoc(rs.getInt(2));
				t.setSoLuong(rs.getInt(3));
				t.setDonGia(rs.getInt(4));
		
				ds.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ds;
	}
	
	// Bảng THAMSO
	public void thayDoiPhiKham(int phimoi) {
		String sql = "update thuoctinh set GiaTri = " + phimoi + " where TenThuocTinh = 'PhiKham'";
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public int phiKhamHienTai() {
		String sql = "select * from thuoctinh where TenThuocTinh = 'PhiKham'";
		ResultSet rs = con.executeQuery(sql);

		int phi = -1;
		try {
			if (rs.next()) {
				phi = rs.getInt(2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return phi;
	}
	// Bảng BỆNH
	
	public ArrayList<BenhDTO> tatCaBenh() {

		String sql = "select * from benh";
		ResultSet rs = con.executeQuery(sql);

		ArrayList<BenhDTO> dsBenh = new ArrayList<BenhDTO>();
		try {
			while (rs.next()) {
				BenhDTO b = new BenhDTO();
				b.setMaBenh(rs.getInt(1));
				b.setTenBenh(rs.getString(2));
				dsBenh.add(b);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Lỗi ở lấy danh sách các bệnh");
		}

		return dsBenh;
	}

	public BenhDTO getBenhByID(int id) {
		String sql = "select * from benh where MaBenh = " + id;
		System.out.println(sql);
		ResultSet rs = con.executeQuery(sql);
		BenhDTO b = new BenhDTO();
		try {
			if (rs.next()) {
				b.setMaBenh(rs.getInt(1));
				b.setTenBenh(rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("Lỗi ở lấy thông tin bệnh bằng id");
		}
		return b;
	}
	
	public void themBenhMoi(String b) {
		String sql = "insert into benh values(null, '" + b +"')";
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public void xoaBenh(int mb) {
		String sql = "delete from benh where MaBenh = " + mb;
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
	
	public void suaBenh(BenhDTO b) {
		String sql = "update benh set TenBenh = '" + b.getTenBenh() + "' where MaBenh = " + b.getMaBenh();
		System.out.println(sql);
		con.excuteUpdate(sql);
	}
}
