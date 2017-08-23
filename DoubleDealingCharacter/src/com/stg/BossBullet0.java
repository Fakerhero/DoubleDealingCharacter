package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * boss������С����0
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
	 * boss������С����ƶ�
	 */
	public void shootBossBullet() {
		BossBulletX -= 6;
		BossBulletY += 5;
	}

}
