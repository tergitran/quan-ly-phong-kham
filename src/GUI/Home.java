package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.MysqlConnection;
import DTO.BenhDTO;
import DTO.BenhNhanDTO;
import DTO.CachDungDTO;
import DTO.DvtDTO;
import DTO.PhieuKhamDTO;
import DTO.ThuocDTO;
import DTO.ToaThuocDTO;
import Model.PhieuKhamModel;
import Model.ThuocModel;
import Model.ToaThuocModel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;

import com.toedter.calendar.JDateChooser;

import BUS.BenhNhanBUS;
import BUS.PhieuKhamBUS;
import BUS.ThuocBUS;

import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.border.TitledBorder;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.JLayeredPane;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ChangeEvent;
import javax.swing.DropMode;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.border.EtchedBorder;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.ListModel;

public class Home extends JFrame {

	PhieuKhamBUS phieuKhamBUS;
	BenhNhanBUS benhNhanBUS;
	

//=============================== Lập phiếu khám ============================================================
	private JTextField textField_maPK;
	private JLabel label_NgayKham;
	
	private JLabel label_MaBN;
	private JLabel label_Hoten;
	private JLabel label_gioitinh;
	JComboBox comboBox;
	
	private ButtonGroup bg;
	private JRadioButton radio_cu;
	private JRadioButton radito_moi;
	private JTextField timThuoc_tf;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton button_lapphieukham;


	
	private JButton btnThemMoi;

	private JButton btnInHD;

	private JButton btnInPhieuKham;

	private JButton btnSuaPhieuKham;

	private JButton btnThot;

	private JLayeredPane layeredPane;

	
	JDateChooser dateChooser_ngaykham;


	private SimpleDateFormat format, format_ymd;
	
	private JList listThuoc;
	private ThuocBUS thuocBUS;
	private JTable bangToaThuoc;
	private JTable bangPhieuKham;
	private DefaultListModel<ThuocDTO> modelThuoc;
	private ToaThuocModel toaThuocModel;
//=================================================================================================================
	
	private JPanel contentPane;
	JPanel panel;
	JTabbedPane tabbedPane;
	JSplitPane splitPane;
	JPanel panel_50;
	JPanel panel_6;
	JPanel panel_4;
	private JLabel label_mapk2;
	private JLabel label_tenBN2;
	private JLabel label_ngaykham2;
	private JLabel label_tenbn2;
	private JPanel panel_5;
	private JLabel lblCcDuHiu;
	private JLabel label_chuandoan2;
	private JPanel panel_10;
	private JLabel lblPhiuKhm_1;
	private JScrollPane scrollPane_3;
	private JLabel label_tienthuoc;
	private JTable table;
	private PhieuKhamModel phieuKhamModel;
	private JTextArea textArea_trieuchung;
	private JScrollPane scrollPane_4;
	private JScrollPane scrollPane_5;
	private JPanel panel_13;
	private JButton btnThm;
	private JButton btnXa;
	private JButton btnSa;
	private JPanel panel_14;
	private JLabel lblTm;
	private JTextField textField_timThuoc;
	private JPanel panel_15;
	private JPanel panel_16;
	private JPanel panel_17;
	private JPanel panel_18;
	private JPanel panel_19;
	
	
	private JTable table_thuoc;
	private ThuocModel thuocModel;
	private TableRowSorter<TableModel> rowSorter;
	private DefaultListModel<CachDungDTO> danhSachCachDung_model;
	private JList danhSachCachDung;
	private JTextField textField_cachDung;
	private JLabel lblCchDng;
	private DefaultListModel<DvtDTO> danhSachDVT_model;
	private JList danhSachDvt;
	private JPanel panel_20;
	private JList danhSachBenh_jlist;
	private DefaultListModel<BenhDTO> danhSachBenhModel;
	private JLabel label_phikham;


	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		Home frame = new Home();
		frame.setVisible(true);
	}

	public Home() {
		setTitle("Quản lý phòng mạch tư");
		
		initComponents();
		
		createEvents();
	}
	
	private void createEvents() {
		
		// ========================== PHIEU KHAM ==============================================
		
		button_lapphieukham.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				PhieuKhamDTO pk;
				ArrayList<ToaThuocDTO> tt;
				try {
					pk = getPhieuKham();
					tt = toaThuocModel.getToaThuoc();
					phieuKhamBUS.luuPhieuKham(pk);
					phieuKhamBUS.luuToaThuoc(tt);
					capNhatBangPhieuKham();
					
					layeredPane.removeAll();
					layeredPane.add(panel_6);
					layeredPane.repaint();
					layeredPane.revalidate();
					
					xemLaiPhieuKham();
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Bạn chưa cung cấp đầy đủ thông tin, vui lòng kiểm tra lại + Lỗi: " + e1.getLocalizedMessage());
				}		
			}
		});
			
		btnThemMoi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panel_4);
				layeredPane.repaint();
				layeredPane.revalidate();
				khoiTaoPhieuKham();
			}
		});
		
		
		radito_moi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dialog_ThemMoiBenhNhan p = new dialog_ThemMoiBenhNhan(label_MaBN, label_Hoten, label_gioitinh); //label_hoten, label_gioitinh
				p.setVisible(true);
				
				System.out.print("Vẫn đang chạy");
			}
		});
		
		radio_cu.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String id = JOptionPane.showInputDialog("Nhập mã bệnh nhân: ");
				if(id != null) {
					benhNhanBUS = new BenhNhanBUS();
					BenhNhanDTO bn = benhNhanBUS.BenhNhanByID(Integer.parseInt(id.substring(2)));
					
					if(bn!= null) {
						label_Hoten.setText(bn.getHoTen());
						label_gioitinh.setText(bn.getGioiTinh());
						label_MaBN.setText(String.format("BN%03d", bn.getMaBN()));
					}
					else 
						JOptionPane.showMessageDialog(null, "Bệnh nhân không tồn tại");
					// Set thông tin hiển thị bệnh nhân

				}
			}
		});
		
		btnThem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(listThuoc.getSelectedIndex() != -1) {
					
					ThuocDTO t = (ThuocDTO)listThuoc.getSelectedValue();
					
					Boolean check = true;
					for(ToaThuocDTO i : toaThuocModel.getToaThuoc()) {
						if(i.getMaThuoc() == t.getMaThuoc()) {
							JOptionPane.showMessageDialog(null, "Thuốc này đã được thêm!");
							check = false;
							break;
						}
					}
					
					if(check) {
						String sl = JOptionPane.showInputDialog("Nhập số lượng thuốc: ");
						if(sl != null) {
							if(Integer.parseInt(sl)<t.getSoLuongTon()) {
								ToaThuocDTO tt = new ToaThuocDTO();
								tt.setMaPK(Integer.parseInt(textField_maPK.getText().substring(2)));
								tt.setDonGia(t.getDonGia());
								tt.setMaThuoc(t.getMaThuoc());
								tt.setSoLuong(Integer.parseInt(sl));
								toaThuocModel.addRow(tt);
							} else JOptionPane.showMessageDialog(null, "Không đủ số lượng thuốc", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
						}
					}
					
				}
			}
		});
		
		
		btnXoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int rowIndex = bangToaThuoc.getSelectedRow();
				if(rowIndex != -1) {
					toaThuocModel.removeRow(rowIndex);
					JOptionPane.showMessageDialog(null, "Đã loại bỏ thuốc");
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn thuốc muốn loại bỏ");
				}
			}
		});
		
		dateChooser_ngaykham.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				capNhatBangPhieuKham();
			}
		});
		
		// ========================== PHIEU KHAM ==============================================
		
		textField_timThuoc.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String text = textField_timThuoc.getText();
				if(text.trim().length()==0) {
					rowSorter.setRowFilter(null);
				} else
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
			}
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				String text = textField_timThuoc.getText();
				if(text.trim().length()==0) {
					rowSorter.setRowFilter(null);
				} else
					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
				
			}
		});
		

		
	}

	private void initComponents() {
		// TODO Auto-generated method stub
		phieuKhamBUS = new PhieuKhamBUS();
		format = new SimpleDateFormat("dd-MM-yyyy");
		format_ymd = new SimpleDateFormat("yyyy/MM/dd");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 960, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);

		panel = new JPanel();
		tabbedPane.addTab("Khám bệnh", null, panel, null);

		splitPane = new JSplitPane();
		splitPane.setAutoscrolls(true);

		panel_50 = new JPanel();
		splitPane.setRightComponent(panel_50);
		panel_50.setLayout(new BorderLayout(0, 0));

		JPanel panel_9 = new JPanel();
		panel_50.add(panel_9, BorderLayout.NORTH);

		JLabel lblDanhSchPhiu = new JLabel("DANH SÁCH PHIẾU KHÁM");

		JLabel lblNgyKhm_1 = new JLabel("Ngày khám:");

		dateChooser_ngaykham = new JDateChooser();
		
		Date currentDay = new Date();
		dateChooser_ngaykham.setDate(currentDay);
		dateChooser_ngaykham.setDateFormatString("dd-MM-yyyy");
		
		GroupLayout gl_panel_9 = new GroupLayout(panel_9);
		gl_panel_9.setHorizontalGroup(
			gl_panel_9.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(91)
					.addComponent(lblNgyKhm_1)
					.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addComponent(dateChooser_ngaykham, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(89, Short.MAX_VALUE))
				.addGroup(Alignment.TRAILING, gl_panel_9.createSequentialGroup()
					.addContainerGap(127, Short.MAX_VALUE)
					.addComponent(lblDanhSchPhiu, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.addGap(99))
		);
		gl_panel_9.setVerticalGroup(
			gl_panel_9.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_9.createSequentialGroup()
					.addGap(12)
					.addComponent(lblDanhSchPhiu, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_9.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNgyKhm_1)
						.addComponent(dateChooser_ngaykham, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(17))
		);
		panel_9.setLayout(gl_panel_9);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		panel_50.add(scrollPane_2, BorderLayout.CENTER);
		
		bangPhieuKham = new JTable();
		phieuKhamModel = new PhieuKhamModel();
		capNhatBangPhieuKham();
		bangPhieuKham.setModel(phieuKhamModel);
		
		scrollPane_2.setViewportView(bangPhieuKham);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(splitPane,
				GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(splitPane,
				GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE));

		layeredPane = new JLayeredPane();
		splitPane.setLeftComponent(layeredPane);
		layeredPane.setLayout(new CardLayout(0, 0));

		panel_4 = new JPanel();
		layeredPane.add(panel_4, "name_186627874366100");

		panel_6 = new JPanel();
		layeredPane.add(panel_6, "name_186621073148900");

		button_lapphieukham = new JButton("Lập phiếu khám");
		

		JLabel lblMPhiuKhm_1 = new JLabel("Mã phiếu khám");

		JLabel label_3 = new JLabel("Ngày khám");

		JLabel label_4 = new JLabel("Triệu chứng");

		JLabel label_5 = new JLabel("Chuẩn đoán");

		textField_maPK = new JTextField();
		textField_maPK.setColumns(10);
		
		phieuKhamBUS = new PhieuKhamBUS();
		ArrayList<BenhDTO> dsBenh = phieuKhamBUS.tatCaBenh();
		comboBox = new JComboBox(dsBenh.toArray());
		comboBox.setSelectedIndex(-1);

		JPanel panel_7 = new JPanel();
		panel_7.setBorder(new TitledBorder(null, "Toa thuốc", TitledBorder.LEFT, TitledBorder.TOP, null, null));

		JLabel label_6 = new JLabel("Tìm");

		timThuoc_tf = new JTextField();
		timThuoc_tf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				JTextField textField =(JTextField)e.getSource();
				capNhatThuoc(textField.getText());
			}
		});
		
		timThuoc_tf.setColumns(10);
		

		JScrollPane scrollPane = new JScrollPane();

		btnXoa = new JButton("Xóa");


		btnThem = new JButton("Thêm");


		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Thu\u1ED1c \u0111\u00E3 k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout gl_panel_7 = new GroupLayout(panel_7);
		gl_panel_7.setHorizontalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel_7
								.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_7.createSequentialGroup()
										.addComponent(label_6, GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
										.addPreferredGap(ComponentPlacement.RELATED)
										.addComponent(timThuoc_tf, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(29))
								.addComponent(scrollPane, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 159,
										Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
								.addComponent(btnThem, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE)
								.addComponent(btnXoa, GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scrollPane_1, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE).addContainerGap()));
		gl_panel_7.setVerticalGroup(gl_panel_7.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_7.createSequentialGroup().addGap(20)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING).addComponent(label_6).addComponent(
								timThuoc_tf, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_panel_7.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panel_7.createSequentialGroup().addComponent(btnThem)
										.addPreferredGap(ComponentPlacement.RELATED).addComponent(btnXoa).addGap(30))
								.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE).addComponent(
										scrollPane_1, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE))
						.addGap(30)));
		
		
		bangToaThuoc = new JTable();

		toaThuocModel = new ToaThuocModel();
		bangToaThuoc.setModel(toaThuocModel);
		bangToaThuoc.removeColumn(bangToaThuoc.getColumnModel().getColumn(2));
		bangToaThuoc.removeColumn(bangToaThuoc.getColumnModel().getColumn(2));
		scrollPane_1.setViewportView(bangToaThuoc);
		
		
		thuocBUS = new ThuocBUS();
		ArrayList<ThuocDTO> dsThuoc = thuocBUS.tatCaThuoc();
		modelThuoc = new DefaultListModel<ThuocDTO>();
		for(ThuocDTO i : dsThuoc) {
			modelThuoc.addElement(i);
		}
		
		listThuoc = new JList(modelThuoc);
		listThuoc.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listThuoc);
		panel_7.setLayout(gl_panel_7);

		JLabel lblBnhNhn = new JLabel("Bệnh nhân");

		radito_moi = new JRadioButton("Mới");
		radio_cu = new JRadioButton("Cũ");
		bg = new ButtonGroup();
		bg.add(radito_moi);
		bg.add(radio_cu);

		JLabel lblNewLabel = new JLabel("Họ tên");

		JLabel lblGiiTnh = new JLabel("Giới tính");

		JLabel lblMBnhNhn = new JLabel("Mã bệnh nhân");

		JLabel lblNhpThngTin = new JLabel("NHẬP THÔNG TIN KHÁM BỆNH");
		lblNhpThngTin.setHorizontalAlignment(SwingConstants.CENTER);

		label_MaBN = new JLabel("");

		label_Hoten = new JLabel("");

		label_gioitinh = new JLabel("");
		
		Calendar c = Calendar.getInstance();
		Date d = c.getTime();
		
		label_NgayKham = new JLabel(format.format(d));
		
		scrollPane_4 = new JScrollPane();
		
		
		GroupLayout gl_panel_4 = new GroupLayout(panel_4);
		gl_panel_4.setHorizontalGroup(
			gl_panel_4.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(160)
					.addComponent(button_lapphieukham, GroupLayout.DEFAULT_SIZE, 286, Short.MAX_VALUE)
					.addGap(160))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel_7, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(lblMPhiuKhm_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_maPK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGap(27)
							.addComponent(label_3)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(label_NgayKham))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addComponent(label_5)
							.addGap(18)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_4.createSequentialGroup()
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(label_4)
								.addComponent(lblBnhNhn))
							.addGap(18)
							.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
								.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, 425, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel_4.createSequentialGroup()
									.addComponent(radito_moi)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(radio_cu))
								.addGroup(gl_panel_4.createSequentialGroup()
									.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addComponent(lblMBnhNhn)
										.addComponent(lblNewLabel)
										.addComponent(lblGiiTnh))
									.addGap(18)
									.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
										.addComponent(label_gioitinh)
										.addComponent(label_Hoten)
										.addComponent(label_MaBN))))))
					.addGap(87))
				.addGroup(gl_panel_4.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNhpThngTin, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_panel_4.setVerticalGroup(
			gl_panel_4.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_4.createSequentialGroup()
					.addGap(33)
					.addComponent(lblNhpThngTin)
					.addGap(30)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblMPhiuKhm_1)
						.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
							.addComponent(textField_maPK, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(label_3)
							.addComponent(label_NgayKham)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(radito_moi)
						.addComponent(radio_cu)
						.addComponent(lblBnhNhn))
					.addGap(8)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMBnhNhn)
						.addComponent(label_MaBN))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(label_Hoten))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGiiTnh)
						.addComponent(label_gioitinh))
					.addGap(19)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.LEADING)
						.addComponent(label_4)
						.addComponent(scrollPane_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_panel_4.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(panel_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(button_lapphieukham, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		
		textArea_trieuchung = new JTextArea();
		scrollPane_4.setViewportView(textArea_trieuchung);
		textArea_trieuchung.setLineWrap(true);
		textArea_trieuchung.setRows(2);
		textArea_trieuchung.setColumns(50);
		panel_4.setLayout(gl_panel_4);

		btnThemMoi = new JButton("Thêm mới");
		

		JLabel lblPhiuKhm = new JLabel("PHIẾU KHÁM ĐÃ ĐƯỢC LƯU");

		btnInHD = new JButton("In hóa đơn");

		btnInPhieuKham = new JButton("In phiếu khám");

		btnSuaPhieuKham = new JButton("Sửa phiếu khám");
		
		panel_5 = new JPanel();
		GroupLayout gl_panel_6 = new GroupLayout(panel_6);
		gl_panel_6.setHorizontalGroup(
			gl_panel_6.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGroup(gl_panel_6.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(78)
							.addComponent(btnInHD)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnInPhieuKham)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSuaPhieuKham)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnThemMoi))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(49)
							.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 463, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel_6.createSequentialGroup()
							.addGap(211)
							.addComponent(lblPhiuKhm)))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		gl_panel_6.setVerticalGroup(
			gl_panel_6.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_6.createSequentialGroup()
					.addGap(47)
					.addComponent(lblPhiuKhm)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(panel_5, GroupLayout.PREFERRED_SIZE, 378, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
					.addGroup(gl_panel_6.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInHD)
						.addComponent(btnInPhieuKham)
						.addComponent(btnSuaPhieuKham)
						.addComponent(btnThemMoi))
					.addGap(79))
		);
		
				JLabel lblMPhiuKhm = new JLabel("Mã phiếu khám:");
				
				label_mapk2 = new JLabel("sdfdsf");
				
						JLabel lblTnBnhNhn = new JLabel("Tên bệnh nhân:");
						
								JLabel lblNgyKhm = new JLabel("Ngày khám:");
								
										JLabel lblTriuChng = new JLabel("Triệu chứng:");
										
												JLabel lblChunon = new JLabel("Chuẩn đoán:");
												
												label_tenbn2 = new JLabel("dfgfdg");
												
												label_ngaykham2 = new JLabel("sdfsdf");
												label_ngaykham2.setBackground(Color.WHITE);
																								
														JLabel lblToaThuc = new JLabel("TOA THUỐC");
												
												lblCcDuHiu = new JLabel("haha");
												lblCcDuHiu.setSize(new Dimension(50, 3));
												
												label_chuandoan2 = new JLabel("New label");
												
												panel_10 = new JPanel();
												
												lblPhiuKhm_1 = new JLabel("PHIẾU KHÁM");
												GroupLayout gl_panel_5 = new GroupLayout(panel_5);
												gl_panel_5.setHorizontalGroup(
													gl_panel_5.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_5.createSequentialGroup()
															.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING, false)
																.addGroup(Alignment.TRAILING, gl_panel_5.createSequentialGroup()
																	.addGap(79)
																	.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
																		.addComponent(lblTnBnhNhn)
																		.addComponent(lblNgyKhm)
																		.addComponent(lblTriuChng)
																		.addComponent(lblMPhiuKhm)
																		.addComponent(lblChunon))
																	.addGap(30)
																	.addGroup(gl_panel_5.createParallelGroup(Alignment.LEADING)
																		.addComponent(label_ngaykham2)
																		.addComponent(label_tenbn2)
																		.addComponent(lblPhiuKhm_1)
																		.addComponent(lblToaThuc)
																		.addComponent(label_mapk2, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
																		.addComponent(label_chuandoan2, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
																		.addComponent(lblCcDuHiu, GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)))
																.addGroup(gl_panel_5.createSequentialGroup()
																	.addGap(35)
																	.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 393, GroupLayout.PREFERRED_SIZE)))
															.addGap(35))
												);
												gl_panel_5.setVerticalGroup(
													gl_panel_5.createParallelGroup(Alignment.LEADING)
														.addGroup(gl_panel_5.createSequentialGroup()
															.addGap(35)
															.addComponent(lblPhiuKhm_1)
															.addGap(29)
															.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblMPhiuKhm)
																.addComponent(label_mapk2))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblTnBnhNhn)
																.addComponent(label_tenbn2))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblNgyKhm)
																.addComponent(label_ngaykham2))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblTriuChng)
																.addComponent(lblCcDuHiu))
															.addPreferredGap(ComponentPlacement.RELATED)
															.addGroup(gl_panel_5.createParallelGroup(Alignment.BASELINE)
																.addComponent(lblChunon)
																.addComponent(label_chuandoan2))
															.addGap(28)
															.addComponent(lblToaThuc)
															.addPreferredGap(ComponentPlacement.RELATED)
															.addComponent(panel_10, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
															.addContainerGap(23, Short.MAX_VALUE))
												);
												panel_10.setLayout(new BorderLayout(0, 0));
												
												scrollPane_3 = new JScrollPane();
												panel_10.add(scrollPane_3, BorderLayout.CENTER);
												
							
												table = new JTable();
												table.setModel(toaThuocModel);
												table.removeColumn(table.getColumnModel().getColumn(4));
												
												scrollPane_3.setViewportView(table);
												
												label_tienthuoc = new JLabel("Tiền thuốc:");
												label_tienthuoc.setHorizontalAlignment(SwingConstants.RIGHT);
												panel_10.add(label_tienthuoc, BorderLayout.SOUTH);
												panel_5.setLayout(gl_panel_5);
		panel_6.setLayout(gl_panel_6);
		panel.setLayout(gl_panel);

		JPanel panel_1 = new JPanel();
		panel_QuanLyBenhNhan p_bn = new panel_QuanLyBenhNhan();
		tabbedPane.addTab("Quản lý bệnh nhân", null, panel_1, null);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(p_bn);

		JPanel panel_8 = new JPanel();
		tabbedPane.addTab("Thống kê", null, panel_8, null);
		
				JPanel panel_2 = new JPanel();
				tabbedPane.addTab("Tùy chọn", null, panel_2, null);
				
				JSplitPane splitPane_1 = new JSplitPane();
				splitPane_1.setResizeWeight(0.5);
				GroupLayout gl_panel_2 = new GroupLayout(panel_2);
				gl_panel_2.setHorizontalGroup(
					gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(splitPane_1, GroupLayout.DEFAULT_SIZE, 929, Short.MAX_VALUE)
				);
				gl_panel_2.setVerticalGroup(
					gl_panel_2.createParallelGroup(Alignment.LEADING)
						.addComponent(splitPane_1, GroupLayout.PREFERRED_SIZE, 611, Short.MAX_VALUE)
				);
				
				JPanel panel_11 = new JPanel();
				panel_11.setBorder(null);
				splitPane_1.setLeftComponent(panel_11);
				panel_11.setLayout(new BorderLayout(0, 0));
				
				scrollPane_5 = new JScrollPane();
				scrollPane_5.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "B\u1EA3ng danh s\u00E1ch c\u00E1c thu\u1ED1c", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
				panel_11.add(scrollPane_5, BorderLayout.CENTER);
				
				
				
				table_thuoc = new JTable();
				
				thuocModel = new ThuocModel();
				thuocModel.setData(thuocBUS.tatCaThuoc());
				table_thuoc.setModel(thuocModel);
				
				rowSorter = new TableRowSorter<>(table_thuoc.getModel());
				table_thuoc.setRowSorter(rowSorter);
				
				scrollPane_5.setViewportView(table_thuoc);
				
				panel_13 = new JPanel();
				panel_11.add(panel_13, BorderLayout.SOUTH);
				
				btnThm = new JButton("Thêm");
				btnThm.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dialog_Thuoc benhNhanForm = new dialog_Thuoc();
						benhNhanForm.setVisible(true);
						capNhapBangThuoc();
					}
				});
				panel_13.add(btnThm);
				
				btnXa = new JButton("Xóa");
				panel_13.add(btnXa);
				
				btnSa = new JButton("Sửa");
				btnSa.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(table_thuoc.getSelectedRow() != -1) {
							int rowSeleted = table_thuoc.getSelectedRow(); 
							dialog_Thuoc benhNhanForm = new dialog_Thuoc(rowSeleted, thuocModel);
							benhNhanForm.setVisible(true);
//							capNhapBangThuoc();
						} else {
							JOptionPane.showMessageDialog(null, "Bạn chưa chọn thuốc muốn sửa!");
						}						
					}
				});
				panel_13.add(btnSa);
				
				panel_14 = new JPanel();
				panel_14.setBorder(new EmptyBorder(15, 0, 0, 0));
				panel_11.add(panel_14, BorderLayout.NORTH);
				
				lblTm = new JLabel("Tìm kiếm");
				panel_14.add(lblTm);
				
				textField_timThuoc = new JTextField();
				panel_14.add(textField_timThuoc);
				textField_timThuoc.setColumns(10);
				
				
				JPanel panel_12 = new JPanel();
				splitPane_1.setRightComponent(panel_12);
				panel_12.setLayout(new BorderLayout(0, 0));
				
				panel_18 = new JPanel();
				panel_12.add(panel_18, BorderLayout.CENTER);
				GridBagLayout gbl_panel_18 = new GridBagLayout();
				gbl_panel_18.columnWidths = new int[] {400};
				gbl_panel_18.rowHeights = new int[]{191, 191, 191, 0};
				gbl_panel_18.columnWeights = new double[]{0.0};
				gbl_panel_18.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
				panel_18.setLayout(gbl_panel_18);
				
				panel_15 = new JPanel();
				GridBagConstraints gbc_panel_15 = new GridBagConstraints();
				gbc_panel_15.fill = GridBagConstraints.BOTH;
				gbc_panel_15.insets = new Insets(0, 0, 5, 0);
				gbc_panel_15.gridx = 0;
				gbc_panel_15.gridy = 0;
				panel_18.add(panel_15, gbc_panel_15);
				panel_15.setBorder(new TitledBorder(null, "\u0110\u01A1n v\u1ECB t\u00EDnh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				
				JScrollPane scrollPane_6 = new JScrollPane();
				
				JButton btnThm_1 = new JButton("Thêm");
				btnThm_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String dvt = JOptionPane.showInputDialog("Nhập tên đơn vị tính mới:");
						if(dvt != null) {
							thuocBUS.themDonViTinh(dvt);
							capNhapDanhSachDvt();
						}
					}
				});
				
				JButton btnXa_1 = new JButton("Xóa");
				
				JButton btnSa_1 = new JButton("Sửa");
				btnSa_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(danhSachDvt.getSelectedIndex() != -1) {
							DvtDTO dvt = (DvtDTO) danhSachDvt.getSelectedValue();
							
							String input = (String)JOptionPane.showInputDialog(null, "Please enter new quantity",
									"Please enter new quantity", JOptionPane.QUESTION_MESSAGE,null,null, dvt.getTenDVT());
							
							if(input != null) {
								if(!input.equals("")) {
									System.out.println(input);
									
									dvt.setTenDVT(input);
									thuocBUS.suaDonViTinh(dvt);
									capNhapDanhSachDvt();
								} else {
									JOptionPane.showMessageDialog(null, "Lỗi! Tên đơn vị không được để trống");
								}
							}
					
							
						} else {
							JOptionPane.showMessageDialog(null, "Bạn chưa chọn đơn vị tính");
						}

					}
				});
				
				danhSachDVT_model = new DefaultListModel<DvtDTO>();
				ArrayList<DvtDTO> danhSachDVT = thuocBUS.tatCaDvT();
				for(DvtDTO i : danhSachDVT) {
					danhSachDVT_model.addElement(i);
				}
				
				danhSachDvt = new JList(danhSachDVT_model);

				
				scrollPane_6.setViewportView(danhSachDvt);
				GroupLayout gl_panel_15 = new GroupLayout(panel_15);
				gl_panel_15.setHorizontalGroup(
					gl_panel_15.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_15.createSequentialGroup()
							.addGap(116)
							.addComponent(btnThm_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnXa_1)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(btnSa_1)
							.addGap(151))
						.addGroup(gl_panel_15.createSequentialGroup()
							.addGap(75)
							.addComponent(scrollPane_6, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
							.addGap(70))
				);
				gl_panel_15.setVerticalGroup(
					gl_panel_15.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_15.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_6, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_panel_15.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnSa_1)
								.addComponent(btnXa_1)
								.addComponent(btnThm_1))
							.addContainerGap())
				);
				panel_15.setLayout(gl_panel_15);
				
				panel_16 = new JPanel();
				GridBagConstraints gbc_panel_16 = new GridBagConstraints();
				gbc_panel_16.fill = GridBagConstraints.BOTH;
				gbc_panel_16.insets = new Insets(0, 0, 5, 0);
				gbc_panel_16.gridx = 0;
				gbc_panel_16.gridy = 1;
				panel_18.add(panel_16, gbc_panel_16);
				panel_16.setBorder(new TitledBorder(null, "C\u00E1ch d\u00F9ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				
				JScrollPane scrollPane_7 = new JScrollPane();
				
				JButton btnThm_2 = new JButton("Thêm");
				btnThm_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
//						if(danhSachCachDung.getSelectedIndex() != -1) {
//							
//						}
						
						if(!textField_cachDung.getText().equals("")) {
							thuocBUS.themCachDung(textField_cachDung.getText());
							capNhapDanhSachThuoc();
						} else {
							JOptionPane.showMessageDialog(null, "Vui lòng nhập cách dùng");
						}
						
//						String cachdung = JOptionPane.showInputDialog("Nhập cách dùng: ");
//						if(cachdung != null) {
//							thuocBUS.themCachDung(cachdung);
//							capNhapDanhSachThuoc();
//						}
						
					}

				});
				
				JButton btnXa_2 = new JButton("Xóa");
				
				JButton btnSa_2 = new JButton("Sửa");
				btnSa_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(danhSachCachDung.getSelectedValue() != null)
						{
							CachDungDTO cd = (CachDungDTO) danhSachCachDung.getSelectedValue();
							 CachDungDTO newCD = new CachDungDTO();
							 newCD.setMaCD(cd.getMaCD());
							 newCD.setCachDung(textField_cachDung.getText());
							 
							 thuocBUS.suaCachDung(newCD);
							 capNhapDanhSachThuoc();
							 
							 JOptionPane.showMessageDialog(null, "Thay đổi thành công");
							 textField_cachDung.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "Bạn chưa chọn cách dùng muốn sửa!");
						}
						 
					}
				});
				
				textField_cachDung = new JTextField();
				textField_cachDung.setColumns(10);
				
				lblCchDng = new JLabel("Cách dùng");
				GroupLayout gl_panel_16 = new GroupLayout(panel_16);
				gl_panel_16.setHorizontalGroup(
					gl_panel_16.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_16.createSequentialGroup()
							.addGap(30)
							.addComponent(scrollPane_7, GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
							.addGap(48))
						.addGroup(gl_panel_16.createSequentialGroup()
							.addGap(51)
							.addComponent(lblCchDng, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_16.createParallelGroup(Alignment.TRAILING)
								.addComponent(textField_cachDung, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
								.addGroup(gl_panel_16.createSequentialGroup()
									.addComponent(btnXa_2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(18)
									.addComponent(btnSa_2)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(btnThm_2)))
							.addGap(133))
				);
				gl_panel_16.setVerticalGroup(
					gl_panel_16.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_16.createSequentialGroup()
							.addGap(2)
							.addComponent(scrollPane_7, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, 16, Short.MAX_VALUE)
							.addGroup(gl_panel_16.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField_cachDung, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCchDng))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_panel_16.createParallelGroup(Alignment.BASELINE)
								.addComponent(btnThm_2)
								.addComponent(btnSa_2)
								.addComponent(btnXa_2)))
				);
				
				
				ArrayList<CachDungDTO> dsCachDung = thuocBUS.tatCaCachDung();
				danhSachCachDung_model = new DefaultListModel<CachDungDTO>();
				for(CachDungDTO i : dsCachDung) {
					danhSachCachDung_model.addElement(i);
				}
				
				
				danhSachCachDung = new JList(danhSachCachDung_model);
				danhSachCachDung.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						CachDungDTO cd = (CachDungDTO) danhSachCachDung.getSelectedValue();
						textField_cachDung.setText(cd.getCachDung());
						
					}
				});
				scrollPane_7.setViewportView(danhSachCachDung);
				panel_16.setLayout(gl_panel_16);
				
				panel_17 = new JPanel();
				GridBagConstraints gbc_panel_17 = new GridBagConstraints();
				gbc_panel_17.anchor = GridBagConstraints.NORTH;
				gbc_panel_17.fill = GridBagConstraints.BOTH;
				gbc_panel_17.gridx = 0;
				gbc_panel_17.gridy = 2;
				panel_18.add(panel_17, gbc_panel_17);
				panel_17.setBorder(new TitledBorder(null, "Kh\u00E1c", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				panel_17.setLayout(new BorderLayout(0, 0));
				
				panel_20 = new JPanel();
				panel_17.add(panel_20, BorderLayout.CENTER);
				
				JPanel panel_21 = new JPanel();
				panel_21.setBorder(new TitledBorder(null, "Ph\u00ED kh\u00E1m b\u1EC7nh", TitledBorder.LEADING, TitledBorder.TOP, null, null));
				
				JPanel panel_22 = new JPanel();
				panel_22.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch c\u00E1c b\u1EC7nh", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
				GroupLayout gl_panel_20 = new GroupLayout(panel_20);
				gl_panel_20.setHorizontalGroup(
					gl_panel_20.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_panel_20.createSequentialGroup()
							.addGap(29)
							.addComponent(panel_22, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
				);
				gl_panel_20.setVerticalGroup(
					gl_panel_20.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel_20.createSequentialGroup()
							.addGroup(gl_panel_20.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_panel_20.createSequentialGroup()
									.addGap(19)
									.addComponent(panel_21, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE))
								.addComponent(panel_22, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
							.addContainerGap())
				);
				panel_22.setLayout(null);
				
				JScrollPane scrollPane_8 = new JScrollPane();
				scrollPane_8.setBounds(10, 22, 270, 91);
				panel_22.add(scrollPane_8);
				
				
				danhSachBenh_jlist = new JList();
				scrollPane_8.setViewportView(danhSachBenh_jlist);
				danhSachBenhModel = new DefaultListModel<BenhDTO>();
				danhSachBenhModel.addAll(phieuKhamBUS.tatCaBenh());
				danhSachBenh_jlist.setModel(danhSachBenhModel);
				
				JButton btnThm_3 = new JButton("Thêm");
				btnThm_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String benh = JOptionPane.showInputDialog("Nhập tên bệnh mới");
						if(benh!=null) {
							if(!benh.equals("")) {
								phieuKhamBUS.themBenhMoi(benh);
								JOptionPane.showMessageDialog(null, "Thêm bệnh mới thành công");
								capNhapDanhSachBenh();
							}
						}
						
					}
				});
				
				btnThm_3.setBounds(41, 124, 59, 23);
				panel_22.add(btnThm_3);
				
				JButton btnXa_3 = new JButton("Xóa");
				btnXa_3.setBounds(110, 124, 59, 23);
				panel_22.add(btnXa_3);
				
				JButton btnSa_4 = new JButton("Sửa");
				btnSa_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(danhSachBenh_jlist.getSelectedIndex() != -1) {
							BenhDTO b = (BenhDTO) danhSachBenh_jlist.getSelectedValue();
							
							String input = (String)JOptionPane.showInputDialog(null, "Tên bệnh muốn sửa",
									"Bệnh", JOptionPane.QUESTION_MESSAGE,null,null, b.getTenBenh());
							
							if(input != null) {
								if(!input.equals("")) {
									System.out.println(input);
									
									b.setTenBenh(input);
									phieuKhamBUS.suaBenh(b);
									capNhapDanhSachBenh();
								} else {
									JOptionPane.showMessageDialog(null, "Lỗi! Tên bệnh không được để trống");
								}
							}
						} else {
							JOptionPane.showMessageDialog(null, "Bạn chưa chọn bệnh muốn sửa đổi");
						}
					}
				});
				btnSa_4.setBounds(179, 124, 59, 23);
				panel_22.add(btnSa_4);
				panel_21.setLayout(null);
				
				JButton btnSa_3 = new JButton("Sửa");
				btnSa_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String input = (String)JOptionPane.showInputDialog(null, "Mức phí mới",
								"Phí khám", JOptionPane.QUESTION_MESSAGE,null,null, label_phikham.getText());
						if(input != null) {
							try {
								phieuKhamBUS.thayDoiPhiKham(Integer.parseInt(input));
								label_phikham.setText(input);
								JOptionPane.showMessageDialog(null, "Đã cập nhật phí khám mới");
							} catch (Exception e2) {
								JOptionPane.showMessageDialog(null, "Phí khám phải không hợp lệ");
							}
						}
					}
				});
				btnSa_3.setBounds(10, 48, 51, 23);
				panel_21.add(btnSa_3);
				
				
				label_phikham = new JLabel(String.valueOf(phieuKhamBUS.phiKhamHienTai()));
				label_phikham.setBounds(10, 23, 48, 14);
				panel_21.add(label_phikham);
				
				JLabel lblVnd = new JLabel("VND");
				lblVnd.setBounds(59, 23, 32, 14);
				panel_21.add(lblVnd);
				panel_20.setLayout(gl_panel_20);
				
				panel_19 = new JPanel();
				panel_12.add(panel_19, BorderLayout.NORTH);
				
				JLabel lblThayiThng = new JLabel("Thay đổi thông số");
				panel_19.add(lblThayiThng);
				panel_2.setLayout(gl_panel_2);

		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel_3, BorderLayout.SOUTH);

		btnThot = new JButton("Thoát");
		panel_3.add(btnThot);

		khoiTaoPhieuKham();
	}

	protected void capNhapDanhSachDvt() {
		
//		danhSachDVT_model = new DefaultListModel<DvtDTO>();
		danhSachDVT_model.removeAllElements();
		ArrayList<DvtDTO> danhSachDVT = thuocBUS.tatCaDvT();
		for(DvtDTO i : danhSachDVT) {
			danhSachDVT_model.addElement(i);
		}
	}
	
	protected void capNhapDanhSachBenh() {
		danhSachBenhModel.removeAllElements();
		ArrayList<BenhDTO> danhSachBenh = phieuKhamBUS.tatCaBenh();
		for(BenhDTO i : danhSachBenh) {
			danhSachBenhModel.addElement(i);
		}
	}

	public void khoiTaoPhieuKham() {
		String maPK = phieuKhamBUS.layMaPhieuKham();
		textField_maPK.setText(maPK);
		label_MaBN.setText(null);
		label_Hoten.setText(null);
		label_gioitinh.setText(null);
		textArea_trieuchung.setText(null);
		comboBox.setSelectedIndex(-1);
		bg.clearSelection();
		toaThuocModel.removeAll();
		
		timThuoc_tf.setText(null);	//Set null vẫn chưa cập nhật lại thuốc!
		capNhatThuoc("");
	}
	
	public void capNhatThuoc(String text) {
		DefaultListModel<ThuocDTO> model_search = new DefaultListModel<ThuocDTO>();
		ArrayList<ThuocDTO> tatCaThuoc = thuocBUS.tatCaThuoc();
		for(ThuocDTO i : tatCaThuoc) {
			String t = i.toString().toLowerCase();
			if(t.contains(text.toLowerCase())) {
				model_search.addElement(i);
			}
		}
		listThuoc.setModel(model_search);
	}
	
	public PhieuKhamDTO getPhieuKham() throws Exception{
		
		PhieuKhamDTO pk = new PhieuKhamDTO();
//		try {
			String d = label_NgayKham.getText();
			System.out.print(format_ymd.format(format.parse(d)));
			pk.setNgayKham(format_ymd.format(format.parse(d)));

			pk.setMaBN(Integer.parseInt(label_MaBN.getText().substring(2)));
			pk.setTrieuChung(textArea_trieuchung.getText());
			BenhDTO b = (BenhDTO)comboBox.getSelectedItem();
			pk.setMaBenh(b.getMaBenh());
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "Bạn chưa cung cấp đầy đủ thông tin, vui lòng kiểm tra lại + Lỗi: " + e.getLocalizedMessage());
//		}
//		throw new Ex
		return pk;
	}

	public void xemLaiPhieuKham() {
		label_mapk2.setText(textField_maPK.getText());
		label_tenbn2.setText(label_Hoten.getText());
		label_ngaykham2.setText(label_NgayKham.getText());
		lblCcDuHiu.setText(textArea_trieuchung.getText());
		label_chuandoan2.setText(comboBox.getSelectedItem().toString());
		label_tienthuoc.setText("Tiền thuốc: " + toaThuocModel.getTongTien());
	}

	public void capNhatBangPhieuKham() {
		System.out.println(format_ymd.format(dateChooser_ngaykham.getDate()));
		String d = format_ymd.format(dateChooser_ngaykham.getDate());
		ArrayList<PhieuKhamDTO> data = phieuKhamBUS.danhSachPhieuKhamTheoNgay(d);
		phieuKhamModel.setData(data);
	}
	
	public void capNhapDanhSachThuoc() {
		ArrayList<CachDungDTO> dsCachDung = thuocBUS.tatCaCachDung();
//		danhSachCachDung_model = new DefaultListModel<CachDungDTO>();
		DefaultListModel dsCachDungModel = (DefaultListModel) danhSachCachDung.getModel();
		dsCachDungModel.removeAllElements();
		for(CachDungDTO i : dsCachDung) {
			danhSachCachDung_model.addElement(i);
		}
	}
	
	public void capNhapBangThuoc(){
		thuocModel.setData(thuocBUS.tatCaThuoc());
	}
}
