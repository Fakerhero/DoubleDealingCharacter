package com.stg;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javax.swing.*;

//窗口
public class GameJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameJPanel gameJPanel = new GameJPanel();

	// 构造函数
	public GameJFrame(String title) {

		// 定义和初始化需要的组件
		JMenuBar jMenuBar = new JMenuBar();
		JMenu starJMenu = new JMenu("选项(F)");
		JMenu resultJMenu = new JMenu("排行榜(V)");
		JMenu helpJMenu = new JMenu("帮助(H)");
		JMenuItem startItem = new JMenuItem("开始                        Enter");
		JMenuItem stopItem = new JMenuItem("暂停                        SPACE");
		JMenuItem reGameItem = new JMenuItem("重新开始                       ");
		JMenuItem exitItem = new JMenuItem("退出                        X");
		JMenuItem directionItem = new JMenuItem("游戏说明(D)");
		JMenuItem aboutItem = new JMenuItem("关于(A)");

		starJMenu.setFont(new Font("微软雅黑", 0, 13));
		resultJMenu.setFont(new Font("微软雅黑", 0, 13));
		helpJMenu.setFont(new Font("微软雅黑", 0, 13));
		startItem.setFont(new Font("微软雅黑", 0, 13));
		stopItem.setFont(new Font("微软雅黑", 0, 13));
		reGameItem.setFont(new Font("微软雅黑", 0, 13));
		exitItem.setFont(new Font("微软雅黑", 0, 13));
		directionItem.setFont(new Font("微软雅黑", 0, 13));
		aboutItem.setFont(new Font("微软雅黑", 0, 13));

		// 添加注册单
		this.addMouseListener(gameJPanel);
		this.addMouseMotionListener(gameJPanel);
		this.addKeyListener(gameJPanel);

		this.setJMenuBar(jMenuBar);
		starJMenu.add(startItem);
		starJMenu.add(stopItem);
		starJMenu.add(reGameItem);
		// starJMenu.addSeparator();//设置分割线
		starJMenu.add(exitItem);

		helpJMenu.add(directionItem);
		helpJMenu.add(aboutItem);

		jMenuBar.add(starJMenu);
		jMenuBar.add(resultJMenu);
		jMenuBar.add(helpJMenu);

		// 设置属性
		this.setTitle(title);
		this.setSize(Screen.catoonX, Screen.catoonY);
		this.setResizable(false);
		this.setLocationRelativeTo(null);//
		// this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = new ImageIcon("icon/cut_mistia.png").getImage();
		this.setIconImage(icon);
		this.setVisible(true);

		// 添加到JFrame
		this.add(gameJPanel);

		// 添加事件监听
		directionItem.addActionListener(derectionItemListener);
		startItem.addActionListener(startActionListener);
		stopItem.addActionListener(pauseActionListener);
		reGameItem.addActionListener(regameActionListener);
		aboutItem.addActionListener(aboutItemActionListener);
		exitItem.addActionListener(exitItemActionListener);

		// 背景音乐线程
		new bgm().start();
		// 线程
		gameJPanel.heroMoveThread();
		gameJPanel.mapMoveThread();
		gameJPanel.moveOrv();
	}

	// 事件监听
	// 选择-开始
	ActionListener startActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameJPanel.start();
		}
	};
	// 选项-暂停
	ActionListener pauseActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameJPanel.pause();
		}
	};
	// 选项-重新开始
	ActionListener regameActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameJPanel.regame();
		}
	};
	// 选项-退出
	ActionListener exitItemActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	// 排行榜
	// 帮助-游戏说明
	ActionListener derectionItemListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			new DirectionJDialog(GameJFrame.this).setVisible(true);
			;
		}
	};

	// 帮助-关于
	ActionListener aboutItemActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			new AboutJDialog(GameJFrame.this).setVisible(true);
		}
	};

	// 背景音乐线程
	class bgm extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				/*
				 * java只能播放无损音乐，如wav格式 好坑啊，害我试了那么久，原来没有声音竟然是这个原因。
				 */
				File f = new File("bgm/History of the Moon.wav");
				URI uri = f.toURI();
				URL url = uri.toURL(); // 解析地址
				AudioClip soundAudioClip = Applet.newAudioClip(url);// AduoClip类
				soundAudioClip.play(); // 循环播放音乐，stop（）停止播放，paly()依次播放声音。
				try {
					Thread.sleep(5 * 60 * 1000 + 17 * 1000);
				} catch (Exception e) {
				}
				
				File f2 = new File("bgm/ヒトリゴ.wav");
				URI uri2 = f2.toURI();
				URL url2 = uri2.toURL(); // 解析地址
				AudioClip soundAudioClip2 = Applet.newAudioClip(url2);// AduoClip类
				soundAudioClip2.play(); // 循环播放音乐，stop（）停止播放，paly()依次播放声音。
				try {
					Thread.sleep(1 * 60 * 1000 + 35 * 1000);
				} catch (Exception e) {
				}
				
				File f3 = new File("bgm/adrenaline!!!.wav");
				URI uri3 = f3.toURI();
				URL url3 = uri3.toURL(); // 解析地址
				AudioClip soundAudioClip3 = Applet.newAudioClip(url3);// AduoClip类
				soundAudioClip3.play(); // 循环播放音乐，stop（）停止播放，paly()依次播放声音。

				try {
					sleep(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
