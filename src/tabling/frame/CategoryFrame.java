package tabling.frame;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import tabling.dao.RestaurantDAO;
import tabling.dto.RestaurantDTO;
import tabling.util.Define;

public class CategoryFrame extends JFrame implements MouseListener {

	private JLabel[] categoryImgs;
	private JLabel[] categoryTexts;

	private JLabel homeLable;
	
	private BackgroundPanel backgroundPanel;
	private MainPanel mainPanel;
	private JScrollPane scrollPane;
	private CustomerLoginFrame customerLoginFrame;

//	private List<JLabel> cateLabels= new ArrayList<>(13);
	public CategoryFrame() {
		initData();
		setInitLayout();
		initListener();
	}

	private void initData() {
		backgroundPanel = new BackgroundPanel();
		//그림라벨
		categoryImgs = new JLabel[14];
		// allLabel= new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryImgs[Define.CATEGORY_ALL] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryImgs[Define.CATEGORY_GYOUNGYANG] = new JLabel(new ImageIcon("img/gyungyang.png"));
		categoryImgs[Define.CATEGORY_CAFE] = new JLabel(new ImageIcon("img/cafe.png"));
		categoryImgs[Define.CATEGORY_BOONSIK] = new JLabel(new ImageIcon("img/boonsik.png"));
		categoryImgs[Define.CATEGORY_GUI] = new JLabel(new ImageIcon("img/gui.png"));
		categoryImgs[Define.CATEGORY_JOONGSIK] = new JLabel(new ImageIcon("img/joongsik.png"));
		categoryImgs[Define.CATEGORY_FASTFOOD] = new JLabel(new ImageIcon("img/fastfood.png"));
		categoryImgs[Define.CATEGORY_HANSIK] = new JLabel(new ImageIcon("img/hansik.png"));
		categoryImgs[Define.CATEGORY_CHICKEN] = new JLabel(new ImageIcon("img/chicken.png"));
		categoryImgs[Define.CATEGORY_HOE] = new JLabel(new ImageIcon("img/hoe.png"));
		categoryImgs[Define.CATEGORY_FAMILIY] = new JLabel(new ImageIcon("img/family.png"));
		categoryImgs[Define.CATEGORY_ILSIK] = new JLabel(new ImageIcon("img/ilsik.png"));
		categoryImgs[Define.CATEGORY_NAENGMYUN] = new JLabel(new ImageIcon("img/naengmyun.png"));
		categoryImgs[Define.CATEGORY_HOF] = new JLabel(new ImageIcon("img/hof.png"));
		//테스트라벨
		categoryTexts = new JLabel[14];
		categoryTexts[Define.CATEGORY_ALL] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_GYOUNGYANG] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_CAFE] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_BOONSIK] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_GUI] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_JOONGSIK] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_FASTFOOD] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_HANSIK] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_CHICKEN] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_HOE] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_FAMILIY] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_ILSIK] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_NAENGMYUN] = new JLabel(new ImageIcon("img/korean_btn.png"));
		categoryTexts[Define.CATEGORY_HOF] = new JLabel(new ImageIcon("img/korean_btn.png"));
		mainPanel = new MainPanel();
		scrollPane = new JScrollPane(mainPanel);
		
		homeLable= new JLabel(new ImageIcon("img/home.png"));
		// 리스트 생각중
		for(int i=0;i<14;i++) {
			mainPanel.add(categoryTexts[i]);
			mainPanel.add(categoryImgs[i]);
		}

	}

	private void setInitLayout() {
		setSize(400, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		setResizable(false);
		
		homeLable.setBounds(40, 5, 70, 70);
		add(homeLable);

		backgroundPanel.setSize(getWidth(), getHeight());
		backgroundPanel.setLayout(null);
		add(backgroundPanel);

		scrollPane.setBounds(10, 80, 360, 400);
		backgroundPanel.add(scrollPane);

		mainPanel.setLayout(new GridLayout(0, 2));
	}

	private void initListener() {
		for (JLabel categoryImgs : categoryImgs) {
			categoryImgs.addMouseListener(this);
		}
		for (JLabel categoryTexts : categoryTexts) {
			categoryTexts.addMouseListener(this);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			
	for(int i=0;i<14;i++) {
		 if (i==0) {			 
			 if(e.getSource() == categoryImgs[i]  || e.getSource() == categoryTexts[i]) {
				 
					RestaurantDAO dao = new RestaurantDAO();
					List<RestaurantDTO> list = dao.getAllRestaurants();
					new RestaurantListFrame(list);	
			 }
		} else {
	 
		  if (e.getSource() == categoryImgs[i]  || e.getSource() == categoryTexts[i] ) {
			RestaurantDAO dao = new RestaurantDAO();
				List<RestaurantDTO> list = dao.getRestaurantsByCategory(i);
				new RestaurantListFrame(list);
		  			}
		 	}  
		 
		 
//		 if(e.getSource() == homeLable) {
//			 customerLoginFrame();
//
//			 
//		 }
		}
	
		} catch (SQLException e1) {
					e1.printStackTrace();
				}
}

	private class BackgroundPanel extends JPanel {
		private Image backgroundImage;

		public BackgroundPanel() {
			backgroundImage = new ImageIcon("img/backgroundimage.jpg").getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}
	}

	private class MainPanel extends JPanel {
		private Image backgroundImage;

		public MainPanel() {
			backgroundImage = new ImageIcon("img/backgroundimage.jpg").getImage();
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String[] args) {
		new CategoryFrame();
	}
}
