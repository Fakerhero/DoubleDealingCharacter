package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * Ó¢ÐÛ×Óµ¯
 * @author SnowHotarubi
 *
 */
public class HeroBullet {

	private int heroBulletSizeX;
	private int heroBulletSizeY;
	public int HeroBulletX;
	public int HeroBulletY;
	private int HeroBulletSpeed = 10;
	private Image heroBulletImage;
	String img;

	public int getHeroBulletX() {
		return HeroBulletX;
	}

	public void setHeroBulletX(int heroBulletX) {
		HeroBulletX = heroBulletX;
	}

	public int getHeroBulletY() {
		return HeroBulletY;
	}

	public void setHeroBulletY(int heroBulletY) {
		HeroBulletY = heroBulletY;
	}

	public int getHeroBulletSpeed() {
		return HeroBulletSpeed;
	}

	public void setHeroBulletSpeed(int heroBulletSpeed) {
		HeroBulletSpeed = heroBulletSpeed;
	}

	public Image getHeroBulletImage() {
		return heroBulletImage;
	}

	public void setHeroBulletImage(Image heroBulletImage) {
		this.heroBulletImage = heroBulletImage;
	}

	public int getHeroBulletSizeX() {
		return heroBulletSizeX;
	}

	public void setHeroBulletSizeX(int heroBulletSizeX) {
		this.heroBulletSizeX = heroBulletSizeX;
	}

	public int getHeroBulletSizeY() {
		return heroBulletSizeY;
	}

	public void setHeroBulletSizeY(int heroBulletSizeY) {
		this.heroBulletSizeY = heroBulletSizeY;
	}

	public HeroBullet(int X, int Y, String img) {
		HeroBulletX = X;
		HeroBulletY = Y;
		heroBulletSizeX = 13;
		heroBulletSizeY = 15;
		this.img = img;
		heroBulletImage = new ImageIcon(img).getImage();
	}

	public void drawHeroBullet(Graphics g) {

		g.drawImage(heroBulletImage, HeroBulletX, HeroBulletY, null);
	}

	public void shootHeroBullet(int speed) {
		HeroBulletY -= 25;
	}
}
