package com.stg;

import java.awt.Container;
import java.awt.TextArea;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.*;
import java.awt.*;

//游戏说明类
public class DirectionJDialog extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int width = 600;
	int height = 500;

	public DirectionJDialog(GameJFrame frame) {
		super(frame, "弹幕游戏", true);
		setBounds((Screen.screenX - width) / 2, (Screen.screenY - height) / 2, width, height);

		Container container = getContentPane();
		TextArea aboutArea = new TextArea();
		aboutArea.setFont(new Font("微软雅黑", 0, 14));
		container.add(aboutArea);

		try {
			File aboutFile = new File("./readme.txt");
			FileInputStream aboutFilein = new FileInputStream(aboutFile);
			byte byt[] = new byte[1024];
			int len = aboutFilein.read(byt);
			aboutArea.setText(new String(byt, 0, len));
			aboutFilein.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
