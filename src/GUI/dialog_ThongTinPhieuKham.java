package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.FormSpecs;

public class dialog_ThongTinPhieuKham extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;


	/**
	 * Create the dialog.
	 */
	public dialog_ThongTinPhieuKham() {
		
		setModal(true);
		
		setBounds(100, 100, 687, 580);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblThngTinPhiu = new JLabel("THÔNG TIN PHIẾU KHÁM");
		
		JLabel lblMPhiuKhm = new JLabel("Mã phiếu khám:");
		
		JLabel lblTnBnhNhn = new JLabel("Tên bệnh nhân:");
		
		JLabel lblNgyKhm = new JLabel("Ngày khám:");
		
		JLabel lblTriuChng = new JLabel("Triệu chứng:");
		
		JLabel lblChunon = new JLabel("Chuẩn đoán:");
		
		JLabel lblNewLabel = new JLabel("New label");
		
		JLabel lblToaThuc = new JLabel("TOA THUỐC");
		
		JScrollPane scrollPane = new JScrollPane();
		
		JPanel panel = new JPanel();
		
		JButton btnInPhiuKhm = new JButton("In phiếu khám");
		
		JButton btnXaPhiuKhm = new JButton("Xóa phiếu khám");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(228)
							.addComponent(lblThngTinPhiu))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(82)
							.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblNgyKhm)
								.addComponent(lblTriuChng)
								.addComponent(lblChunon)
								.addGroup(gl_contentPanel.createSequentialGroup()
									.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
										.addComponent(lblMPhiuKhm)
										.addComponent(lblTnBnhNhn))
									.addGap(45)
									.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(153)
							.addComponent(btnInPhiuKhm)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnXaPhiuKhm))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(245)
							.addComponent(lblToaThuc))
						.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
							.addGap(66)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 457, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(134, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(26)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addComponent(lblThngTinPhiu)
							.addGap(52))
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblMPhiuKhm)
							.addComponent(lblNewLabel)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTnBnhNhn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblNgyKhm)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblTriuChng)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblChunon)
					.addGap(21)
					.addComponent(lblToaThuc)
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 111, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(42)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnInPhiuKhm)
						.addComponent(btnXaPhiuKhm))
					.addContainerGap())
		);
		panel.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		JLabel lblTinThuc = new JLabel("Tiền thuốc:");
		panel.add(lblTinThuc, "2, 2");
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		panel.add(lblNewLabel_1, "4, 2");
		
		JLabel lblTinKhm = new JLabel("Tiền khám:");
		panel.add(lblTinKhm, "2, 4");
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		panel.add(lblNewLabel_2, "4, 4");
		
		JLabel lblTngTin = new JLabel("Tổng tiền:");
		panel.add(lblTngTin, "2, 6");
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		panel.add(lblNewLabel_3, "4, 6");
		
		table = new JTable();
		scrollPane.setViewportView(table);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btn_thoat = new JButton("thoát");
				btn_thoat.setActionCommand("Cancel");
				buttonPane.add(btn_thoat);
			}
		}
	}
}
