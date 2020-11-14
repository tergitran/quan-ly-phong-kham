package GUI;

import javax.swing.JPanel;

import BUS.BenhNhanBUS;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;
import javax.swing.JButton;

public class panel_themBenhNhan extends JPanel {
	BenhNhanBUS benhNhanBUS;
	private JTextField hoten;
	public panel_themBenhNhan() {
		
		System.out.print("Mở panel");
		
		JLabel lblMBnhNhn = new JLabel("Mã bệnh nhân:");
		
		JLabel lblHTn = new JLabel("Họ tên:");
		
		JLabel lblNgySinh = new JLabel("Ngày sinh:");
		
		JLabel lblGiiTnh = new JLabel("Giới tính:");
		
		JLabel lblaCh = new JLabel("Địa chỉ:");
		
		JLabel lblNhpThongTin = new JLabel("NHẬP THÔNG TIN BỆNH NHÂN MỚI");
		
		hoten = new JTextField();
		hoten.setColumns(10);
		
		JLabel maBN = new JLabel("BN003");
		
		JDateChooser dateChooser = new JDateChooser();
		
		JRadioButton rdbtnNam = new JRadioButton("Nam");
		
		JRadioButton rdbtnN = new JRadioButton("Nữ");
		
		JTextPane textPane = new JTextPane();
		
		JButton btnThm = new JButton("Thêm");
		
		JButton btnHy = new JButton("Hủy");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap(143, Short.MAX_VALUE)
					.addComponent(lblNhpThongTin)
					.addGap(139))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(89)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblaCh)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(lblHTn)
								.addComponent(lblMBnhNhn)
								.addComponent(lblGiiTnh)
								.addComponent(lblNgySinh))
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(18)
									.addComponent(maBN))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(11)
									.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
										.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
											.addGroup(groupLayout.createSequentialGroup()
												.addComponent(rdbtnNam)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addComponent(rdbtnN))
											.addComponent(hoten)
											.addComponent(dateChooser, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
										.addGroup(groupLayout.createSequentialGroup()
											.addComponent(btnThm)
											.addPreferredGap(ComponentPlacement.RELATED)
											.addComponent(btnHy)))))
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addContainerGap(87, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(50)
					.addComponent(lblNhpThongTin)
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMBnhNhn)
						.addComponent(maBN))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblHTn)
						.addComponent(hoten, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNgySinh)
						.addComponent(dateChooser, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblGiiTnh)
						.addComponent(rdbtnNam)
						.addComponent(rdbtnN))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblaCh)
						.addComponent(textPane, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnThm)
						.addComponent(btnHy))
					.addContainerGap(27, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
		benhNhanBUS = new BenhNhanBUS();
		maBN.setText(benhNhanBUS.layMaBenhNhanMoi());
	}
}
