package com.stg;
import javax.swing.*;
import java.awt.*;

/**
 * 菜单中的关于内容
 * @author SnowHotarubi
 *
 */
public class AboutJDialog extends JDialog {
	
	private static final long serialVersionUID = 1L;
	int width = 400;
	int height = 300;

	public AboutJDialog(GameJFrame frame) {
		super(frame, "東方輝針城", true);
		Container container = getContentPane();

		String strMsg1 = "東方輝針城";
		String strMsg2 = "Copyright©2017 SnowHotarubi";
		String strMsg3 = "version2.0.0";
		/*
		 * 哇，原来html的语法是这样用在java中的啊。
		 */
		String about = "<html><body>" + strMsg1 + "<br>" + strMsg2 + "<br>" + strMsg3 + "<body></html>";
		JLabel aboutjLabel = new JLabel(about, SwingConstants.CENTER); // 设置居中
		aboutjLabel.setFont(new Font("微软雅黑", 0, 13));
		container.add(aboutjLabel);
		setBounds((Screen.screenX - width) / 2, (Screen.screenY - height) / 2, width, height);
	}
}