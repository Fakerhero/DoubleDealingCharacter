package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

//boss发出的小球类3
public class BossBullet3 {
	int BossBulletX;
	int BossBulletY;
	Image bossBulletImage;
	
	public BossBullet3(int x,int y){
		this.BossBulletX = x;
		this.BossBulletY = y;
		bossBulletImage = new ImageIcon("images/bullet/boss/bullet3.png").getImage();
	}
	public void drawBossBullet(Graphics g){
		g.drawImage(bossBulletImage, BossBulletX, BossBulletY, null);
	}
	public void shootBossBullet(){
		BossBulletX -= 0;
		BossBulletY += 5;
	}
}