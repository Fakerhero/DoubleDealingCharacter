package com.stg;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * ֮ǰд��ʱ����������е���ˣ������cleanEnemys�ǽ�����һ���࣬�Ե�������ӵ��һ�������Ļ���
 * @author SnowHotarubi
 *
 */
public class CleanEnemys {

	int x;
	int y;
	int speed;
	Image img;

	public CleanEnemys() {
		x = (int) (Math.random() * (512 - 40) + 224);
		y = (int) (Math.random() * (Screen.catoonY / 2 - 240));
		speed = (int) (Math.random() * 10 + 5);
		img = new ImageIcon("images/bullet/clean.png").getImage();
	}

	public void draw(Graphics g) {
		g.drawImage(img, x, y, null);
	}

	public void move() {
		y += speed;
	}
}
