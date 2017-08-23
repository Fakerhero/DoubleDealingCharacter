package com.stg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * 用于记录分录，英雄生命，boss生命，还有章节的类。
 * @author SnowHotarubi
 *
 */
public class Score {
	long score;
	String life;
	String chapter;

	public Score() {
		score = 0;
		life = "★★★★★"; // 生命值，初数值为五颗星，life = "☆☆☆☆";
	}

	public void drawScore(Graphics g, String chapter, long score, String life, int bossLife) {
		this.score = score;
		this.life = life;
		this.chapter = chapter;
		/*
		 * 描边失败，以后再说弄了。
		 * 
		 */
		// Font f = new Font("微软雅黑", Font.PLAIN, 24);
		// GlyphVector v =
		// f.createGlyphVector(getFontMetrics(f).getFontRenderContext(),
		// "Hello");
		// Shape s = v.getOutline();
		// Graphics2D g2 = (Graphics2D)g.create();
		// g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		// g2.translate(100,150);
		// g2.rotate(0.4);
		// g2.setPaint(Color.decode("#ff8213"));
		// g2.fill(s);
		// g2.setPaint(Color.black);
		// g2.setStroke(new BasicStroke(3));
		// g2.draw(s);
		g.setFont(new Font("微软雅黑", Font.BOLD, 32));
		g.setColor(Color.red);
		g.drawString("关卡:  " + chapter, Screen.screenX / 2 + 80, Screen.screenY / 2 - 170);
		g.setFont(new Font("微软雅黑", Font.BOLD, 24));
		g.setColor(Color.decode("#ff8213"));
		g.drawString("得点:  " + score, Screen.screenX / 2 + 80, Screen.screenY / 2 - 100);
		g.setColor(Color.decode("#40b5f2"));
		g.drawString("灵力:  " + life, Screen.screenX / 2 + 80, Screen.screenY / 2 - 100 + 50);
		g.setColor(Color.decode("#7fcac8"));
		g.fillRect(Screen.screenX / 2 + 80, Screen.screenY / 2 - 100 + 100, bossLife, 10);// bosslife=150
	}
}
