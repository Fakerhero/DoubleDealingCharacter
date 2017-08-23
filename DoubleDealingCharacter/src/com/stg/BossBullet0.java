package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * boss发出的小球类0
 * @author SnowHotarubi
 *
 */
public class BossBullet0 {
	int BossBulletX;
	int BossBulletY;
	Image bossBulletImage;

	public BossBullet0(int x, int y) {
		this.BossBulletX = x;
		this.BossBulletY = y;
		bossBulletImage = new ImageIcon("images/bullet/boss/bullet0.png").getImage();
	}

	public void drawBossBullet(Graphics g) {
		g.drawImage(bossBulletImage, BossBulletX, BossBulletY, null);
	}

	/**
	 * boss发出的小球的移动
	 */
	public void shootBossBullet() {
		BossBulletX -= 6;
		BossBulletY += 5;
	}

}
