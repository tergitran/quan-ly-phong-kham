package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import BUS.PhieuKhamBUS;
import DTO.PhieuKhamDTO;
import DTO.ToaThuocDTO;
import Model.ToaThuocModel;

import com.jgoodies.forms.layout.FormSpecs;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class dialog_xemPhieuKham extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	private JButton btnInPhiuKhm;
	private JButton btnXaPhiuKhm;
	private JLabel label_MaPK;
	private JLabel label_TenBN;
	private JLabel label_NgayKham;
	private JLabel label_TrieuChung;
	private JLabel label_Benh;
	private ToaThuocModel toaThuocModel;
	private JLabel label_TienThuoc;
	private JLabel label_TienKham;
	private JLabel label_TongTien;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		try {
//			dialog_xemPhieuKham dialog = new dialog_xemPhieuKham();
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	/**
	 * Create the dialog.
	 */
	
	
	
	public dialog_xemPhieuKham(PhieuKhamDTO pk) {
		setTitle("Thông tin phiếu khám");
		setModal(true);
		setBounds(100, 100, 676, 549);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblThngTinPhiu = new JLabel("THÔNG TIN PHIẾU KHÁM");
		JLabel lblNewLabel = new JLabel("Mã phiếu khám:");
		JLabel lblNewLabel_1 = new JLabel("Tên bệnh nhân:");
		JLabel lblNewLabel_2 = new JLabel("Ngày khám:");
		JLabel label_Tsdfff = new JLabel("Triệu chứng:");
		JLabel lblNewLabel_4 = new JLabel("Chuẩn đoán:");
		JLabel lblToaThuc = new JLabel("TOA THUỐC");
		JScrollPane scrollPane = new JScrollPane();
		
		label_TienThuoc = new JLabel("TienThuoc");
		
		label_TienKham = new JLabel("TienKham");
		
		label_TongTien = new JLabel("TongTien");
		
		btnInPhiuKhm = new JButton("In phiếu khám");
		
		btnXaPhiuKhm = new JButton("Xóa phiếu khám");
		
		label_MaPK = new JLabel("PK001");
		
		label_TenBN = new JLabel("New label");
		
		label_NgayKham = new JLabel("19-2-1998");
		
		label_TrieuChung = new JLabel("Ho");
		
		label_Benh = new JLabel("sốt");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(241)
							.addComponent(lblThngTinPhiu))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(124)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNewLabel)
								.addComponent(lblNewLabel_1)
								.addComponent(lblNewLabel_2)
								.addComponent(label_Tsdfff)
								.addComponent(lblNewLabel_4))
							.addGap(26)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_Benh)
								.addComponent(label_TrieuChung)
								.addComponent(label_NgayKham)
								.addComponent(label_TenBN)
								.addComponent(label_MaPK)))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(263)
							.addComponent(lblToaThuc))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(101)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 432, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(187)
							.addComponent(btnInPhiuKhm)
							.addGap(18)
							.addComponent(btnXaPhiuKhm)))
					.addContainerGap(117, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
					.addGap(435)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(label_TongTien)
						.addComponent(label_TienKham)
						.addComponent(label_TienThuoc))
					.addContainerGap(128, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(25)
					.addComponent(lblThngTinPhiu)
					.addGap(28)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(label_MaPK))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(label_TenBN))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(label_NgayKham))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_Tsdfff)
						.addComponent(label_TrieuChung))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_4)
						.addComponent(label_Benh))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblToaThuc)
					.addGap(5)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_TienThuoc)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_TienKham)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_TongTien)
					.addPreferredGap(ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnXaPhiuKhm)
						.addComponent(btnInPhiuKhm))
					.addGap(25))
		);
		
		table = new JTable();
		toaThuocModel = new ToaThuocModel();
		table.setModel(toaThuocModel);
		
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btn_thoat = new JButton("Thoát");
				btn_thoat.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				btn_thoat.setActionCommand("Cancel");
				buttonPane.add(btn_thoat);
			}
		}
		
		setForm(pk);
	}
	
	
	public void setForm(PhieuKhamDTO pk) {
		label_MaPK.setText(String.valueOf(pk.getMaPK()));
		label_Benh.setText(pk.getBenh().getTenBenh());
		label_NgayKham.setText(pk.getNgayKham());
		label_TenBN.setText(pk.getBenhNhan().getHoTen());
		label_TrieuChung.setText(pk.getTrieuChung());
		
		PhieuKhamBUS phieuKhamBUS = new PhieuKhamBUS();
		ArrayList<ToaThuocDTO> toaThuoc = phieuKhamBUS.toaThuoc(pk.getMaPK());
		toaThuocModel.setToaThuoc(toaThuoc);
		
		int tienthuoc = toaThuocModel.getTongTien();
		int tienkham = phieuKhamBUS.phiKhamHienTai();
		int tongtien = tienthuoc + tienkham;
		label_TienThuoc.setText("Tiền thuốc: " + String.valueOf(tienthuoc));
		label_TienKham.setText("Tiền khám: " + String.valueOf(tienkham));
		label_TongTien.setText("Tổng tiền: " +  tongtien);
		
		
	}
}
