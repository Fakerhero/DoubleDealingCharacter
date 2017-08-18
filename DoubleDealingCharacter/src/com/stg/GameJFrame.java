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

//����
public class GameJFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	GameJPanel gameJPanel = new GameJPanel();

	// ���캯��
	public GameJFrame(String title) {

		// ����ͳ�ʼ����Ҫ�����
		JMenuBar jMenuBar = new JMenuBar();
		JMenu starJMenu = new JMenu("ѡ��(F)");
		JMenu resultJMenu = new JMenu("���а�(V)");
		JMenu helpJMenu = new JMenu("����(H)");
		JMenuItem startItem = new JMenuItem("��ʼ                        Enter");
		JMenuItem stopItem = new JMenuItem("��ͣ                        SPACE");
		JMenuItem reGameItem = new JMenuItem("���¿�ʼ                       ");
		JMenuItem exitItem = new JMenuItem("�˳�                        X");
		JMenuItem directionItem = new JMenuItem("��Ϸ˵��(D)");
		JMenuItem aboutItem = new JMenuItem("����(A)");

		starJMenu.setFont(new Font("΢���ź�", 0, 13));
		resultJMenu.setFont(new Font("΢���ź�", 0, 13));
		helpJMenu.setFont(new Font("΢���ź�", 0, 13));
		startItem.setFont(new Font("΢���ź�", 0, 13));
		stopItem.setFont(new Font("΢���ź�", 0, 13));
		reGameItem.setFont(new Font("΢���ź�", 0, 13));
		exitItem.setFont(new Font("΢���ź�", 0, 13));
		directionItem.setFont(new Font("΢���ź�", 0, 13));
		aboutItem.setFont(new Font("΢���ź�", 0, 13));

		// ���ע�ᵥ
		this.addMouseListener(gameJPanel);
		this.addMouseMotionListener(gameJPanel);
		this.addKeyListener(gameJPanel);

		this.setJMenuBar(jMenuBar);
		starJMenu.add(startItem);
		starJMenu.add(stopItem);
		starJMenu.add(reGameItem);
		// starJMenu.addSeparator();//���÷ָ���
		starJMenu.add(exitItem);

		helpJMenu.add(directionItem);
		helpJMenu.add(aboutItem);

		jMenuBar.add(starJMenu);
		jMenuBar.add(resultJMenu);
		jMenuBar.add(helpJMenu);

		// ��������
		this.setTitle(title);
		this.setSize(Screen.catoonX, Screen.catoonY);
		this.setResizable(false);
		this.setLocationRelativeTo(null);//
		// this.setUndecorated(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Image icon = new ImageIcon("icon/cut_mistia.png").getImage();
		this.setIconImage(icon);
		this.setVisible(true);

		// ��ӵ�JFrame
		this.add(gameJPanel);

		// ����¼�����
		directionItem.addActionListener(derectionItemListener);
		startItem.addActionListener(startActionListener);
		stopItem.addActionListener(pauseActionListener);
		reGameItem.addActionListener(regameActionListener);
		aboutItem.addActionListener(aboutItemActionListener);
		exitItem.addActionListener(exitItemActionListener);

		// ���������߳�
		new bgm().start();
		// �߳�
		gameJPanel.heroMoveThread();
		gameJPanel.mapMoveThread();
		gameJPanel.moveOrv();
	}

	// �¼�����
	// ѡ��-��ʼ
	ActionListener startActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameJPanel.start();
		}
	};
	// ѡ��-��ͣ
	ActionListener pauseActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameJPanel.pause();
		}
	};
	// ѡ��-���¿�ʼ
	ActionListener regameActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			gameJPanel.regame();
		}
	};
	// ѡ��-�˳�
	ActionListener exitItemActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	// ���а�
	// ����-��Ϸ˵��
	ActionListener derectionItemListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			new DirectionJDialog(GameJFrame.this).setVisible(true);
			;
		}
	};

	// ����-����
	ActionListener aboutItemActionListener = new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent e) {
			new AboutJDialog(GameJFrame.this).setVisible(true);
		}
	};

	// ���������߳�
	class bgm extends Thread {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			try {
				/*
				 * javaֻ�ܲ����������֣���wav��ʽ �ÿӰ�������������ô�ã�ԭ��û��������Ȼ�����ԭ��
				 */
				File f = new File("bgm/History of the Moon.wav");
				URI uri = f.toURI();
				URL url = uri.toURL(); // ������ַ
				AudioClip soundAudioClip = Applet.newAudioClip(url);// AduoClip��
				soundAudioClip.play(); // ѭ���������֣�stop����ֹͣ���ţ�paly()���β���������
				try {
					Thread.sleep(5 * 60 * 1000 + 17 * 1000);
				} catch (Exception e) {
				}
				
				File f2 = new File("bgm/�ҥȥꥴ.wav");
				URI uri2 = f2.toURI();
				URL url2 = uri2.toURL(); // ������ַ
				AudioClip soundAudioClip2 = Applet.newAudioClip(url2);// AduoClip��
				soundAudioClip2.play(); // ѭ���������֣�stop����ֹͣ���ţ�paly()���β���������
				try {
					Thread.sleep(1 * 60 * 1000 + 35 * 1000);
				} catch (Exception e) {
				}
				
				File f3 = new File("bgm/adrenaline!!!.wav");
				URI uri3 = f3.toURI();
				URL url3 = uri3.toURL(); // ������ַ
				AudioClip soundAudioClip3 = Applet.newAudioClip(url3);// AduoClip��
				soundAudioClip3.play(); // ѭ���������֣�stop����ֹͣ���ţ�paly()���β���������

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
