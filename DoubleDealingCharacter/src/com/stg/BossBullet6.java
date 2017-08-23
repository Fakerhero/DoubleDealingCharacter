package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//boss发出的小球类6
public class BossBullet6 {
	int BossBulletX;
	int BossBulletY;
	Image bossBulletImage;

	public BossBullet6(int x, int y) {
		this.BossBulletX = x;
		this.BossBulletY = y;
		bossBulletImage = new ImageIcon("images/bullet/boss/bullet6.png").getImage();
	}

	public void drawBossBullet(Graphics g) {
		g.drawImage(bossBulletImage, BossBulletX, BossBulletY, null);
	}

	public void shootBossBullet() {
		BossBulletX += 6;
		BossBulletY += 5;
	}
}