package com.stg;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

//boss������С����1
public class BossBullet1 {
	int BossBulletX;
	int BossBulletY;
	Image bossBulletImage;

	public BossBullet1(int x, int y) {
		this.BossBulletX = x;
		this.BossBulletY = y;
		bossBulletImage = new ImageIcon("images/bullet/boss/bullet1.png").getImage();
	}

	public void shootBossBullet() {
		BossBulletX -= 4;
		BossBulletY += 5;
	}

	public void drawBossBullet(Graphics g) {
		g.drawImage(bossBulletImage, BossBulletX, BossBulletY, null);
	}
}