package tabling;

import java.awt.Font;

import javax.swing.UIManager;

import tabling.frame.LoginSelectFrame;
import tabling.request.Request;

public class Tabling {

	public static void main(String[] args) {
		// 서버 주소 변경 가능
		Request.setIp("localhost");
		UIManager.put("OptionPane.messageFont", new Font("Noto Sans KR", Font.PLAIN, 12));
		new LoginSelectFrame();
	}

}