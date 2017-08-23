package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//boss发出的小球类4
public class BossBullet4 {
	int BossBulletX;
	int BossBulletY;
	Image bossBulletImage;
	
	public BossBullet4(int x,int y){
		this.BossBulletX = x;
		this.BossBulletY = y;
		bossBulletImage = new ImageIcon("images/bullet/boss/bullet4.png").getImage();
	}
	public void drawBossBullet(Graphics g){
		g.drawImage(bossBulletImage, BossBulletX, BossBulletY, null);
	}
	public void shootBossBullet(){
		BossBulletX += 2;
		BossBulletY += 5;
	}
}