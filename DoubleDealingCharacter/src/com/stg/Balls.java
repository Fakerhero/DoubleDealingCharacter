package com.stg;
import java.awt.Color;
import java.awt.Graphics;

/**
 * –°«Ú¿‡
 * @author SnowHotarubi
 *
 */
public class Balls {
	int ballsX;
	int ballsY;
	int ballsSpeed;

	public Balls() {
		ballsX = (int) (Math.random() * (512 - 40) + 224);
		ballsY = (int) (Math.random() * (Screen.catoonY - 40));
		ballsSpeed = (int) (Math.random() * 4 + 3);
	}
	/**
	 * ª≠–°«Ú
	 */
	public void drawBalls(Graphics g, String color) {
		g.setColor(Color.decode(color));
		g.fillOval(ballsX, ballsY, 40, 40);
	}
}
