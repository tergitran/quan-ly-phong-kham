package GUI;

import Model.BenhNhanTabelModel;
import Model.PhieuKhamModel;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;

import com.toedter.calendar.JDateChooser;

import BUS.BenhNhanBUS;
import DTO.BenhNhanDTO;
import DTO.PhieuKhamDTO;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class panel_QuanLyBenhNhan extends JPanel {
	private JTable table_BenhNhan;
	private JTextField textField_TimKiemBenhNhan;
	
	private BenhNhanBUS benhNhanBUS = new BenhNhanBUS();
	private TableRowSorter rowSorter_BenhNhanTable;
	private JTable table_PhieuKham;

	public panel_QuanLyBenhNhan() {
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Tra c\u1EE9u b\u1EC7nh nh\u00E2n", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "L\u1ECBch s\u1EED kh\u00E1m", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addGap(20)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 916, Short.MAX_VALUE)
						.addComponent(panel_2, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(20))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 327, GroupLayout.PREFERRED_SIZE)
					.addGap(27)
					.addComponent(panel_2, GroupLayout.PREFERRED_SIZE, 218, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(32, Short.MAX_VALUE))
		);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new TitledBorder(null, "Danh s\u00E1ch phi\u1EBFu kh\u00E1m c\u1EE7a b\u1EC7nh nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_2.add(scrollPane_1, BorderLayout.CENTER);
		
		
		
		
		table_PhieuKham = new JTable();
		PhieuKhamModel phieuKhamModel = new PhieuKhamModel();
		table_PhieuKham.setModel(phieuKhamModel);
		TableColumnModel tcm = table_PhieuKham.getColumnModel();
		tcm.removeColumn( tcm.getColumn(3) );
		
		table_PhieuKham.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				   if (e.getClickCount() == 2) {
				      JTable target = (JTable)e.getSource();
				      int row = target.getSelectedRow();
				      System.out.println("Dòng " + row);
				      new dialog_xemPhieuKham(phieuKhamModel.getRow(row)).setVisible(true);;
				   }
				}
		});
		
		
		scrollPane_1.setViewportView(table_PhieuKham);
		panel.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new TitledBorder(null, "Danh s\u00E1ch b\u1EC7nh nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.add(scrollPane, BorderLayout.CENTER);
		
		table_BenhNhan = new JTable();
		scrollPane.setViewportView(table_BenhNhan);
		BenhNhanTabelModel benhNhanTabelModel = new BenhNhanTabelModel(benhNhanBUS.tatCaBenhNhan());
		table_BenhNhan.setModel(benhNhanTabelModel);
		
		rowSorter_BenhNhanTable = new TableRowSorter<>(table_BenhNhan.getModel());
		table_BenhNhan.setRowSorter(rowSorter_BenhNhanTable);
		
		table_BenhNhan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.print("Hahaha Đã nhấn");
				int rowIndex = table_BenhNhan.getSelectedRow();
				BenhNhanDTO bn = benhNhanTabelModel.getRow(rowIndex);
				
				phieuKhamModel.setData(benhNhanBUS.phieuKhamTheoBenhNhan(bn.getMaBN()));
			}
		});
		
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.SOUTH);
		
		JButton btnXoa = new JButton("Xóa");
		panel_1.add(btnXoa);
		
		JButton btnSua = new JButton("Sửa");
		btnSua.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table_BenhNhan.getSelectedRow() != -1) {
					int rowIndex = table_BenhNhan.getSelectedRow();
					new dialog_ThemMoiBenhNhan(rowIndex, benhNhanTabelModel).setVisible(true);;
				} else {
					JOptionPane.showMessageDialog(null, "Vui lòng chọn bệnh nhân muốn sửa");
				}
			}
		});
		panel_1.add(btnSua);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, BorderLayout.NORTH);
		
		JLabel lblThngTinBnh = new JLabel("Nhập thông tin bệnh nhân");
		
		textField_TimKiemBenhNhan = new JTextField();
		textField_TimKiemBenhNhan.setColumns(10);
		
		textField_TimKiemBenhNhan.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				String text = textField_TimKiemBenhNhan.getText();
				if(text.trim().length()==0) {
					rowSorter_BenhNhanTable.setRowFilter(null);
				} else
					rowSorter_BenhNhanTable.setRowFilter(RowFilter.regexFilter("(?i)" + text));
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				String text = textField_TimKiemBenhNhan.getText();
				if(text.trim().length()==0) {
					rowSorter_BenhNhanTable.setRowFilter(null);
				} else
					rowSorter_BenhNhanTable.setRowFilter(RowFilter.regexFilter("(?i)" + text));
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
			}
		});
		

		
//		
//			textField_timThuoc.getDocument().addDocumentListener(new DocumentListener() {
//			
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				String text = textField_timThuoc.getText();
//				if(text.trim().length()==0) {
//					rowSorter.setRowFilter(null);
//				} else
//					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//			}
//			
//			@Override
//			public void removeUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				String text = textField_timThuoc.getText();
//				if(text.trim().length()==0) {
//					rowSorter.setRowFilter(null);
//				} else
//					rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
//			}
//			
//			@Override
//			public void changedUpdate(DocumentEvent e) {
//				// TODO Auto-generated method stub
//				throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//				
//			}
//		});
//		rowSorter = new TableRowSorter<>(table_thuoc.getModel());
//		table_thuoc.setRowSorter(rowSorter);
		
		
		
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3.setHorizontalGroup(
			gl_panel_3.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addGap(289)
					.addComponent(lblThngTinBnh)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(textField_TimKiemBenhNhan, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(419, Short.MAX_VALUE))
		);
		gl_panel_3.setVerticalGroup(
			gl_panel_3.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel_3.createSequentialGroup()
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGroup(gl_panel_3.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblThngTinBnh)
						.addComponent(textField_TimKiemBenhNhan, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		panel_3.setLayout(gl_panel_3);
		setLayout(groupLayout);
		

	}
}
