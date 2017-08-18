package com.stg;

import java.awt.Dimension;
import java.awt.Toolkit;

//主方法类
public class ClassMain {
	// Main方法
	public static void main(String[] args) {
		new GameJFrame("東方輝針城");
	}
}

// 屏幕size接口
interface Screen {
	// final
	Toolkit screen = Toolkit.getDefaultToolkit();
	Dimension screeDimension = screen.getScreenSize();
	public static final int screenX = (int) screeDimension.getWidth();
	public static final int screenY = (int) screeDimension.getHeight();
	public static final int catoonX = 960;
	public static final int catoonY = 720;
	// 游戏控制
	public static final int LOADING = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int END = 3;
	// 子弹模式
	public static final int Mode1 = 1;
	public static final int Mode2 = 2;
	public static final int Mode3 = 3;
	// 重来，clear集合
}
