package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import BUS.ThuocBUS;
import DTO.CachDungDTO;
import DTO.DvtDTO;
import DTO.ThuocDTO;
import Model.ThuocModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Font;

public class dialog_Thuoc extends JDialog {

	private ThuocBUS thuocBUS = new ThuocBUS();
	
	private final JPanel contentPanel = new JPanel();
	private JTextField textField_DonGia;
	private JTextField textField_tenThuoc;
	private JTextField textField_SoLuongTon;

	private JButton btnSua;

	private JButton btnThem;

	private JButton btnHuy;

	private JLabel label_MaThuoc;

	private JComboBox comboBox_CachDung;

	private JComboBox comboBox_DonViTinh;
	
	private ThuocModel thuocModel;
	private int indexRow;
	

	
	public static void main(String[] args) {
		try {
			dialog_Thuoc dialog = new dialog_Thuoc();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public dialog_Thuoc() {
		setModal(true);
		setTitle("Thêm thuốc mới");
		
		initComponent();
		
		createEvent();
		
		btnSua.setVisible(false);
		label_MaThuoc.setText(thuocBUS.layMaThuoc());
		
	}
	
	public dialog_Thuoc(int rowSelected, ThuocModel thuocModel) {
		setModal(true);
		setTitle("Sửa thông tin thuốc");
		
		initComponent();
		createEvent();
		
		khoiTaoForm(thuocModel.getRow(rowSelected));
		this.thuocModel = thuocModel;
		this.indexRow = rowSelected;
		
		btnThem.setVisible(false);
		
	}
	
	
	public ThuocDTO layThongTinForm() {
		ThuocDTO t = new ThuocDTO();

		try {
			t.setTenThuoc(textField_tenThuoc.getText());
			t.setMaCD(((CachDungDTO)comboBox_CachDung.getSelectedItem()).getMaCD());
			t.setMaDVT(((DvtDTO)comboBox_DonViTinh.getSelectedItem()).getMaDVT());
			t.setDonGia(Integer.parseInt(textField_DonGia.getText()));
			t.setSoLuongTon(Integer.parseInt(textField_SoLuongTon.getText()));
			return t;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Thông tin thuốc không hợp lệ, vui lòng kiểm tra lại!");
		}
		
		return null;
		
	}
	
	private void khoiTaoForm(ThuocDTO t) {
		label_MaThuoc.setText(String.format("T%03d", t.getMaThuoc()));
		textField_tenThuoc.setText(t.getTenThuoc());
		
		DvtDTO dvt = thuocBUS.donViTinhByID(t.getMaDVT());
		for (int i=0; i<comboBox_DonViTinh.getModel().getSize(); i++)
		{
		    if (comboBox_DonViTinh.getItemAt(i).toString().equals(dvt.toString()))
		    {
		    	comboBox_DonViTinh.setSelectedIndex(i);
		        break;
		    }
		}
		
		
		CachDungDTO cd = thuocBUS.cachDungByID(t.getMaCD());
		for (int i=0; i<comboBox_CachDung.getModel().getSize(); i++)
		{
		    if (comboBox_CachDung.getItemAt(i).toString().equals(cd.toString()))
		    {
		    	comboBox_CachDung.setSelectedIndex(i);
		        break;
		    }
		}
		
//		comboBox_CachDung.setSelectedItem(thuocBUS.cachDungByID(t.getMaCD()));
		textField_DonGia.setText(String.valueOf(t.getDonGia()));
		textField_SoLuongTon.setText(String.valueOf(t.getSoLuongTon()));
	}

	private void createEvent() {
		
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThuocDTO t = layThongTinForm();
				if(t != null) {
					thuocBUS.themThuoc(t);
					dispose();
					JOptionPane.showMessageDialog(null, "Đã thêm thuốc thành công");
				}
			}
		});
		
		btnHuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThuocDTO t = layThongTinForm();
				if(t != null) {
					t.setMaThuoc(thuocModel.getRow(indexRow).getMaThuoc());
					thuocModel.changeRow(indexRow, t);
					dispose();
					JOptionPane.showMessageDialog(null, "Sửa thông tin thuốc thành công");
				}
				
			}
		});
		
	}
	

	private void initComponent() {
		
		setBounds(100, 100, 423, 371);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblMThuc = new JLabel("Mã thuốc");
			lblMThuc.setBounds(39, 67, 75, 14);
			contentPanel.add(lblMThuc);
		}
		{
			JLabel lblNewLabel = new JLabel("Tên thuốc");
			lblNewLabel.setBounds(39, 104, 75, 14);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblCchDng = new JLabel("Cách dùng");
			lblCchDng.setBounds(39, 138, 75, 14);
			contentPanel.add(lblCchDng);
		}
		{
			JLabel lblnVTnh = new JLabel("Đơn vị tính");
			lblnVTnh.setBounds(39, 175, 75, 14);
			contentPanel.add(lblnVTnh);
		}
		{
			JLabel lblnGi = new JLabel("Đơn giá");
			lblnGi.setBounds(39, 213, 75, 14);
			contentPanel.add(lblnGi);
		}
		{
			JLabel lblSLngTn = new JLabel("Số lượng tồn");
			lblSLngTn.setBounds(39, 247, 75, 14);
			contentPanel.add(lblSLngTn);
		}
		{
			label_MaThuoc = new JLabel("New label");
			label_MaThuoc.setBounds(124, 67, 48, 14);
			contentPanel.add(label_MaThuoc);
		}
		
		JLabel lblThngTinThuc = new JLabel("THÔNG TIN THUỐC");
		lblThngTinThuc.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblThngTinThuc.setBounds(124, 25, 151, 31);
		contentPanel.add(lblThngTinThuc);
		
		comboBox_CachDung = new JComboBox();
		comboBox_CachDung.setBounds(124, 134, 244, 22);
		contentPanel.add(comboBox_CachDung);
		DefaultComboBoxModel<CachDungDTO> comboboxCachDungModel = new DefaultComboBoxModel<CachDungDTO>();
		comboboxCachDungModel.addAll(thuocBUS.tatCaCachDung());
		comboBox_CachDung.setModel(comboboxCachDungModel);
		
		comboBox_DonViTinh = new JComboBox();
		comboBox_DonViTinh.setBounds(124, 171, 75, 22);
		contentPanel.add(comboBox_DonViTinh);
		DefaultComboBoxModel<DvtDTO> cbxDonViTinhModel = new DefaultComboBoxModel<DvtDTO>();
		cbxDonViTinhModel.addAll(thuocBUS.tatCaDvT());
		comboBox_DonViTinh.setModel(cbxDonViTinhModel);
		
		
		textField_DonGia = new JTextField();
		textField_DonGia.setBounds(124, 210, 151, 20);
		contentPanel.add(textField_DonGia);
		textField_DonGia.setColumns(10);
		
		JLabel lblVnd = new JLabel("VND");
		lblVnd.setBounds(285, 213, 21, 14);
		contentPanel.add(lblVnd);
		
		textField_tenThuoc = new JTextField();
		textField_tenThuoc.setBounds(124, 101, 151, 20);
		contentPanel.add(textField_tenThuoc);
		textField_tenThuoc.setColumns(10);
		
		textField_SoLuongTon = new JTextField();
		textField_SoLuongTon.setBounds(124, 244, 151, 20);
		contentPanel.add(textField_SoLuongTon);
		textField_SoLuongTon.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(39, 272, 329, 28);
		contentPanel.add(panel);
		{
			btnThem = new JButton("Thêm");
			panel.add(btnThem);
		}
		{
			btnSua = new JButton("Sửa");
			panel.add(btnSua);

			btnSua.setActionCommand("OK");
			getRootPane().setDefaultButton(btnSua);
		}
		{
			btnHuy = new JButton("Hủy");

			panel.add(btnHuy);

			btnHuy.setActionCommand("Cancel");
		}
	}
}
