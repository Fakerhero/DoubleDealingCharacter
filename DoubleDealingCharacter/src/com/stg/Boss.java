package com.stg;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Boss {
	int bossX;
	int bossY;
	int bossNo; // boss±àºÅ£¬ 1-5¸ö
	Image bossImage;

	public Boss(int bossNO) {
		bossX = Screen.screenX / 2 - 200;
		bossY = Screen.screenY / 2 - 350;
		this.bossNo = bossNO;
		bossImage = new ImageIcon("images/stage/boss" + bossNo + ".png").getImage();
	}

	public void drawBoss(Graphics g) {
		g.drawImage(bossImage, bossX, bossY, null);
	}

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
