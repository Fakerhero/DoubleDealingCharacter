package com.stg;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Plain {

	int plainX;
	int plainY;
	int plainSpeed;
	Image plainImage;
	ArrayList<PlainBullet> plainBullets = new ArrayList<PlainBullet>();

	public Plain(int plainSpeed) {
		this.plainSpeed = plainSpeed;
		plainX = (int) (Math.random() * (512 - 40) + 224);
		plainY = (int) (Math.random() * (Screen.catoonY / 2 - 240));
		plainSpeed = (int) (Math.random() * 5 + 3);
		int num = (int) (Math.random() * 7);
		plainImage = new ImageIcon("images/enemy/plain/p" + num + ".png").getImage();

		plainBullets.add(new PlainBullet(plainX + 20, plainY + 40));
	}

	public void drawPlain(Graphics g) {
		g.drawImage(plainImage, plainX, plainY, null);
		for (int i = 0; i < plainBullets.size(); i++) {
			plainBullets.get(i).drawplainBullet(g);
		}
	}

	public void movePlain() {
		plainY += plainSpeed;
		for (int i = 0; i < plainBullets.size(); i++) {
			plainBullets.get(i).shootplainBullet();
		}
	}
}
