package tabling.frame;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tabling.util.MyMouseListener;

public class LoginSelectFrame extends JFrame {

	private BackgroundPanel backgroundPanel;
	private JLabel customerBtn;
	private JLabel restaurantBtn;

	public LoginSelectFrame() {
		new WaittingFrame();
//		initData();
//		setInitLayout();
//		addEventListener();
	}

	private void initData() {
		backgroundPanel = new BackgroundPanel("img/mainBg.jpg");

		setTitle("테이블링");
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);

		customerBtn = new JLabel(new ImageIcon("img/customerStartBtn.png"));
		restaurantBtn = new JLabel(new ImageIcon("img/ownerStartBtn.png"));
		setVisible(true);
	}

	private void setInitLayout() {
		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		customerBtn.setBounds(35, 115, 50, 50);
		customerBtn.setSize(320, 200);
		customerBtn.setFont(new Font("Noto Sans KR", Font.BOLD, 15));
		backgroundPanel.add(customerBtn);

		restaurantBtn.setBounds(35, 310, 50, 50);
		restaurantBtn.setSize(320, 200);
		restaurantBtn.setFont(new Font("Noto Sans KR", Font.BOLD, 15));
		backgroundPanel.add(restaurantBtn);
	}

	private void addEventListener() {

		// 손님 측 프레임 오픈
		customerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new CustomerLoginFrame();
				setVisible(false);
				dispose();
			}
		});

		// 식당 측 프레임 오픈
		restaurantBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				new RestaurantLoginFrame();
				setVisible(false);
				dispose();
			}
		});

	}
	
	private class WaittingFrame extends JFrame {
		private BackgroundPanel backgroundPanel;
		
		public WaittingFrame() {
			backgroundPanel = new BackgroundPanel("img/startBg.jpg");
			setTitle("테이블링");
			setSize(400, 600);
			setLocationRelativeTo(null);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setResizable(false);
			
			backgroundPanel.setSize(getWidth(), getHeight());
			backgroundPanel.setLayout(null);
			add(backgroundPanel);
			
			setVisible(true);
			
			backgroundPanel.addMouseListener(new MyMouseListener() {
				@Override
				public void mousePressed(MouseEvent e) {
					setVisible(false);
					initData();
					setInitLayout();
					addEventListener();
					dispose();
				}
			});
		}
	}
}
