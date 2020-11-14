package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import BUS.BenhNhanBUS;
import DTO.BenhNhanDTO;
import Model.BenhNhanTabelModel;

import javax.swing.JComboBox;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

public class dialog_ThemMoiBenhNhan extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	BenhNhanBUS benhNhanBUS;
	
	private JTextField HoTen;
	private JLabel MaBenhNhan;
	private JDateChooser NgaySinh;
	private JRadioButton rdbtnNam;
	private JRadioButton rdbtnN;
	private JButton btnThem;
	private JButton btnHuy;
	private JTextPane DiaChi;
	private BenhNhanDTO bn;

	private JButton btnSua;
	
	/**
	 * @wbp.parser.constructor
	 */
	
	public dialog_ThemMoiBenhNhan(int indexRow, BenhNhanTabelModel benhNhanTableModel) {
		initComponents();
		setTitle("Sửa thông tin bệnh nhân");
		btnThem.setVisible(false);
		
		BenhNhanDTO bn = benhNhanTableModel.getRow(indexRow);
		
		MaBenhNhan.setText(String.valueOf(bn.getMaBN()));
		HoTen.setText(bn.getHoTen());
		DiaChi.setText(bn.getDiaChi());
		
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd").parse(bn.getNgaySinh());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		NgaySinh.setDate(d);
		if(bn.getGioiTinh().equals("Nam")) {
			rdbtnNam.setSelected(true);
		} else {
			rdbtnN.setSelected(true);
		}
		
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		
				BenhNhanDTO bnNew = layThongTinForm();
				benhNhanBUS.suaBenhNhan(bnNew);
				benhNhanTableModel.suaBenhNhan(indexRow, bnNew);
				dispose();
				JOptionPane.showMessageDialog(null, "Đã sửa thành công");
			}
		});
		
	}

	public dialog_ThemMoiBenhNhan(JLabel label_MaBN,JLabel label_hoten, JLabel label_gioitinh) { // JLabel label_hoten, JLabel label_gioitinh
		
		initComponents();
		setTitle("Thêm mới bệnh nhân");
		btnSua.setVisible(false);
		
		MaBenhNhan.setText(benhNhanBUS.layMaBenhNhanMoi());
		
		Date d = null;
		try {
			d = new SimpleDateFormat("dd-MM-yyyy").parse("1-1-2000");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		NgaySinh.setDate(d);
		
		btnThem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
//				bn = new BenhNhanDTO();
//				bn.setMaBN(Integer.parseInt(MaBenhNhan.getText()));
//				bn.setHoTen(HoTen.getText());
//				bn.setGioiTinh(rdbtnN.isSelected() ? "Nữ" : "Nam");
//				SimpleDateFormat f = new SimpleDateFormat("yyyy/MM/dd");
//				bn.setNgaySinh(f.format(NgaySinh.getDate()));
//				bn.setDiaChi(DiaChi.getText());
				benhNhanBUS.themBenhNhan(layThongTinForm());
				
//				if(label_hoten == null) System.out.print("NULL RỒI ÔNG GIÁO ƠI");
				label_hoten.setText(bn.getHoTen());
				label_gioitinh.setText(bn.getGioiTinh());
				label_MaBN.setText(String.valueOf(bn.getMaBN()));
				JOptionPane.showMessageDialog(null, "Thêm mới bệnh nhân thành công!");
				dispose();
			}
		});
	}
	

	private void initComponents() {
		benhNhanBUS = new BenhNhanBUS();
		setModal(true);
		
		setBounds(100, 100, 444, 347);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.WEST);
		JLabel lblMBnhNhn = new JLabel("Mã bệnh nhân");
		JLabel lblHTn = new JLabel("Họ tên");
		JLabel lblNgySinh = new JLabel("Ngày Sinh");
		JLabel lblGiiTnh = new JLabel("Giới tính");
		JLabel lblaCh = new JLabel("Địa chỉ");
		MaBenhNhan = new JLabel();
		HoTen = new JTextField();
		HoTen.setColumns(10);
		
		NgaySinh = new JDateChooser();
		Locale vn = new Locale("Vi");
		NgaySinh.setLocale(vn);
		NgaySinh.setDateFormatString("dd-MM-yyyy");
		
		rdbtnNam = new JRadioButton("Nam");
		rdbtnN = new JRadioButton("Nữ");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnN);
		bg.add(rdbtnNam);
		
		DiaChi = new JTextPane();
		
		JLabel lblNhpThngTin = new JLabel("NHẬP THÔNG TIN BỆNH NHÂN MỚI");
		lblNhpThngTin.setHorizontalAlignment(SwingConstants.CENTER);
		
		JPanel panel = new JPanel();
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(36)
					.addComponent(lblNhpThngTin, GroupLayout.DEFAULT_SIZE, 347, Short.MAX_VALUE)
					.addGap(41))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(73)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblHTn)
										.addComponent(lblNgySinh)
										.addComponent(lblaCh)
										.addComponent(lblGiiTnh))
									.addGap(38))
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(lblMBnhNhn)
									.addGap(18)))
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addComponent(rdbtnNam)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(rdbtnN))
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
									.addGroup(gl_contentPanel.createSequentialGroup()
										.addComponent(NgaySinh, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
									.addComponent(HoTen)
									.addComponent(MaBenhNhan))
								.addComponent(DiaChi, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(67, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(20)
					.addComponent(lblNhpThngTin)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addComponent(MaBenhNhan)
						.addComponent(lblMBnhNhn))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHTn)
						.addComponent(HoTen, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNgySinh)
						.addComponent(NgaySinh, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(15)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGiiTnh)
						.addComponent(rdbtnNam)
						.addComponent(rdbtnN))
					.addGap(15)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblaCh)
						.addComponent(DiaChi, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE))
					.addGap(19)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(40))
		);
		
		btnThem = new JButton("Thêm");
		panel.add(btnThem);
		
		btnSua = new JButton("Sửa");

		panel.add(btnSua);
		
		btnHuy = new JButton("Hủy");
		btnHuy.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		panel.add(btnHuy);
		contentPanel.setLayout(gl_contentPanel);
	}
	
	private BenhNhanDTO layThongTinForm() {
		bn = new BenhNhanDTO();
		bn.setMaBN(Integer.parseInt(MaBenhNhan.getText()));
		bn.setHoTen(HoTen.getText());
		bn.setGioiTinh(rdbtnN.isSelected() ? "Nữ" : "Nam");
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
		bn.setNgaySinh(f.format(NgaySinh.getDate()));
		bn.setDiaChi(DiaChi.getText());
		
		return bn;
	}
}
