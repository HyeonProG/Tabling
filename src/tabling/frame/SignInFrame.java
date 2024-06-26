package tabling.frame;

import java.awt.Choice;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import tabling.request.CustomerRequest;
import tabling.util.Define;
import tabling.util.MyMouseListener;

// 회원가입 화면
public class SignInFrame extends JFrame implements MyMouseListener {

	// 컴포넌트
	private JLabel background;
	private JTextField nameField;
	private JTextField phoneField;
	private JLabel duplicateCheck;
	private JLabel signInBtn;
	private Choice localTel;
	private JComboBox<String> myLocation;
	private String[] localTelArray;

	// 가입 버튼 활성화를 결정하는 변수
	private boolean canCheck;
	private boolean canLogin;

	// request
	private CustomerRequest request;

	public SignInFrame() {
		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		request = new CustomerRequest();
		setTitle("회원가입");
		setSize(400, 600);
		setResizable(false);

		background = new JLabel(new ImageIcon("img/signInBg.jpg"));

		setContentPane(background);

		nameField = new JTextField(20);

		localTel = new Choice();
		localTelArray = new String[] { "010", "011", "016", "017", "018", "019" };
		for (int i = 0; i < localTelArray.length; i++) {
			localTel.add(localTelArray[i]);
		}

		phoneField = new JTextField();

		myLocation = new JComboBox<String>();
		for (int i = Define.LOCATION_GANGSEOGU; i <= Define.LOCATION_GIJANGGUN; i++) {
			myLocation.addItem(Define.LOCATIONS[i]);
		}

		duplicateCheck = new JLabel(new ImageIcon("img/duplicateBtn.png"));

		signInBtn = new JLabel(new ImageIcon("img/signInBtn.png"));
	}

	private void setInitLayout() {
		setLayout(null);
		setLocationRelativeTo(null);

		add(nameField);
		nameField.setBounds(90, 165, 200, 25);

		add(localTel);
		localTel.setBounds(90, 270, 50, 25);

		add(phoneField);
		phoneField.setBounds(150, 270, 140, 25);

		add(myLocation);
		myLocation.setBounds(90, 380, 200, 25);
		myLocation.setFont(new Font("Noto Sans KR", Font.BOLD, 13));

		add(duplicateCheck);
		duplicateCheck.setBounds(200, 300, 90, 25);
		duplicateCheck.setEnabled(false);

		add(signInBtn);
		signInBtn.setBounds(35, 480, 314, 46);
		signInBtn.setEnabled(false);

		setVisible(true);
	}

	@Override
	public void mousePressed(MouseEvent e) {

		// 회원 가입 버튼 입력시
		if (e.getSource() == signInBtn) {

			if (canLogin == true) {
				if (nameField.getText().matches("^\\s.*")) {
					JOptionPane.showMessageDialog(null, "닉네임은 공백으로 시작할 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					nameField.setText("");
					return;
				} else if (nameField.getText().matches(".*\\s$")) {
					JOptionPane.showMessageDialog(null, "닉네임은 공백으로 끝날 수 없습니다.", "경고", JOptionPane.WARNING_MESSAGE);
					nameField.setText("");
					return;
				} else if (nameField.getText().length() > 50) {
					JOptionPane.showMessageDialog(null, "닉네임은 50자까지만 기입 가능합니다.", "경고", JOptionPane.WARNING_MESSAGE);
					nameField.setText("");
				} else {
					request.addCustomer(nameField.getText(), localTel.getSelectedItem() + phoneField.getText(),
							myLocation.getSelectedIndex() + 1);
					JOptionPane.showMessageDialog(null, "회원가입 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
					setVisible(false);
					dispose();
				}
			}
			// 중복 체크 입력시
		} else if (e.getSource() == duplicateCheck) {

			if (phoneField.getText().length() == 8 && canCheck == true) {

				try {
					if (request.getCustomerByPhone(localTel.getSelectedItem() + phoneField.getText()) != null) {
						JOptionPane.showMessageDialog(null, "중복되는 번호입니다.", "경고", JOptionPane.WARNING_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "가입 가능한 전화번호입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
						signInBtn.setEnabled(true);
						canLogin = true;
					}
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				}

			} else if (canCheck == true) {
				JOptionPane.showMessageDialog(null, "전화번호가 8자리가 아닙니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
		}
	}

	private void addEventListener() {

		signInBtn.addMouseListener(this);
		duplicateCheck.addMouseListener(this);

		// 전화번호 8자 이상 입력불가 & 8자 입력시 중복체크 활성화
		phoneField.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				// 중복 체크이후 입력값 변경시 다시 가입버튼 비활성화용
				canLogin = false;
				signInBtn.setEnabled(false);

				if (phoneField.getText().length() < 9) {
					duplicateCheck.setEnabled(false);
					canCheck = false;
				}
				if (6 < phoneField.getText().length()) {
					duplicateCheck.setEnabled(true);
					canCheck = true;
				}
			}

			@Override
			public void keyTyped(KeyEvent e) {
				if (phoneField.getText().length() > 7) {
					e.consume();
				} else if (!(e.getKeyChar() >= '0' && e.getKeyChar() <= '9')) {
					e.consume();
				}
			}
		});

	}

} // end of class
