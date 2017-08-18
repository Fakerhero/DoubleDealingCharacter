package com.stg;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

//����
public class Enemy {
	private int enemyX;
	private int enemyY;
	private int enemySpeed;
	private int enemySpeedX; 
	private Image enemyImg;
	private int dir;

	public int getEnemyX() {
		return enemyX;
	}

	public void setEnemyX(int enemyX) {
		this.enemyX = enemyX;
	}

	public int getEnemyY() {
		return enemyY;
	}

	public void setEnemyY(int enemyY) {
		this.enemyY = enemyY;
	}

	public int getEnemySpeed() {
		return enemySpeed;
	}

	public void setEnemySpeed(int enemySpeed) {
		this.enemySpeed = enemySpeed;
	}

	public Image getEnemyImg() {
		return enemyImg;
	}

	public void setEnemyImg(Image enemyImg) {
		this.enemyImg = enemyImg;
	}

	public Enemy(int enemySpeed, int dir) {
		this.dir = dir;
		this.enemySpeed = enemySpeed;
		enemySpeedX = 0;
		enemyX = (int) (Math.random() * (512 - 40) + 224);
		enemyY = (int) (Math.random() * (Screen.catoonY / 2 - 240));
		enemySpeed = (int) (Math.random() * 6 + 5);
		int num = (int) (Math.random() * 8);
		enemyImg = new ImageIcon("images/enemy/bullet" + num + ".png").getImage();
	}

	public void drawEnemy(Graphics g) {
		g.drawImage(enemyImg, enemyX, enemyY, null);
	}

	public void moveEnemy() {
		enemyY += enemySpeed;
		//�����ж�
		if(dir == 0){
			enemyX -= enemySpeedX;
		}else{
			enemyX += enemySpeedX;
		}
		//ײǽ�ͷ�����ע�����ﶼ��"-"��ʾ������
		if(enemyX <= new GameJPanel().bgX || 
			enemyX >= new GameJPanel().bgX + new GameJPanel().bgSizeX - enemyImg.getWidth(null)){
			
			enemySpeedX = -enemySpeedX;
		}
	}
}
