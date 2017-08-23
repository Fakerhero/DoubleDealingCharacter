package com.stg;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 * boss类
 * @author SnowHotarubi
 *
 */
public class Boss {
	int bossX;
	int bossY;
	int bossNo; // boss编号， 1-5个
	Image bossImage;

	public Boss(int bossNO) {
		bossX = Screen.screenX / 2 - 200;
		bossY = Screen.screenY / 2 - 350;
		this.bossNo = bossNO;
		bossImage = new ImageIcon("images/stage/boss" + bossNo + ".png").getImage();
	}

	/**
	 * 画boss
	 * @param g
	 */
	public void drawBoss(Graphics g) {
		g.drawImage(bossImage, bossX, bossY, null);
	}
	
	/**
	 * 用于boss的移动，传入一个方向控制
	 * @param dir
	 */
	public void moveBoss(int dir) {
		int left = 0;
		int right = 1;
		if (left == dir) {
			bossX += 1;
			bossY += 0;
		}
		if (right == dir) {
			bossX -= 1;
			bossY += 0;
		}
	}
}
