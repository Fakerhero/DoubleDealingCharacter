package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 
 * @author SnowHotarubi
 *
 */
public class PlainBullet {
	int plainBulletX;
	int plainBulletY;
	Image plainBulletImage;
	int plainSpeed;

	public PlainBullet(int X, int Y) {
		this.plainBulletX = X;
		this.plainBulletY = Y;
		plainSpeed = 16;
		plainBulletImage = new ImageIcon("images/bullet/plainBullet.gif").getImage();
	}

	public void drawplainBullet(Graphics g) {

		g.drawImage(plainBulletImage, plainBulletX, plainBulletY, null);
	}

	public void shootplainBullet() {

		plainBulletY += plainSpeed;
	}
}
