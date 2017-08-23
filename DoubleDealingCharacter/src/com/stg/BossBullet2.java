package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//boss发出的小球类2
public class BossBullet2 {
	int BossBulletX;
	int BossBulletY;
	Image bossBulletImage;

	public BossBullet2(int x, int y) {
		this.BossBulletX = x;
		this.BossBulletY = y;
		bossBulletImage = new ImageIcon("images/bullet/boss/bullet2.png").getImage();
	}

	public void drawBossBullet(Graphics g) {
		g.drawImage(bossBulletImage, BossBulletX, BossBulletY, null);
	}

	public void shootBossBullet() {
		BossBulletX -= 2;
		BossBulletY += 5;
	}
}