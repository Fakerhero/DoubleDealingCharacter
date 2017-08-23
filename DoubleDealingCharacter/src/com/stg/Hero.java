package com.stg;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 英雄类
 * @author SnowHotarubi
 *
 */
public class Hero {

	private int heroSizeX;
	private int heroSizeY;
	private Image heroiImage;
	public int heroX;
	public int heroY;

	public int getheroSizeX() {
		return heroSizeX;
	}

	public void setheroSizeX(int heroSizeX) {
		this.heroSizeX = heroSizeX;
	}

	public int getheroSizeY() {
		return heroSizeY;
	}

	public void setheroSizeY(int heroSizeY) {
		this.heroSizeY = heroSizeY;
	}

	public Image getheroiImage() {
		return heroiImage;
	}

	public void setheroiImage(Image heroiImage) {
		this.heroiImage = heroiImage;
	}

	/*
	 * png和gif都支持背景透明,而且在ps中必须是RGB模式。なるほど
	 */
	String heroString[] = { "images/player/center-1.png", "images/player/left-1.png", "images/player/right-1.png" };
	String heroS = heroString[0];

	public Hero() {
		heroSizeX = 75;
		heroSizeY = 108;
		heroiImage = new ImageIcon(heroS).getImage();
		heroX = (Screen.catoonX - heroSizeX) / 2;
		heroY = Screen.catoonY - heroSizeY-50;
	}

	public void drawHero(Graphics g) {
		g.drawImage(heroiImage, heroX, heroY, null);
		g.drawImage(new ImageIcon("images/player/ball.png").getImage(), heroX - 30, heroY + 70, null);
		g.drawImage(new ImageIcon("images/player/ball.png").getImage(), heroX + 65, heroY + 70, null);
	}
}
