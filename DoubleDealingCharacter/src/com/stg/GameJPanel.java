package com.stg;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

import javax.swing.JPanel;

//����
@SuppressWarnings("serial")
public class GameJPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	// ��Ϸ����960*720
	public int gameX = 960;
	public int gameY = 720;
	
	// ��ͼͼƬ��С��512*2816
	public int bgSizeX = 512;
	public int bgSizeY = 12288;
	public int bgX = (Screen.catoonX - bgSizeX) / 2;
	public int bgY = -bgSizeY + Screen.screenY;

	// ������Ϸ
	public long scoreTotal = 0; // ����
	public String assessGame = "����"; // �ƺ�
	public String assesString = "��ϲ������ȫ����ͷ";// ����
	int BossNO = 1;// boss ģʽ
	int gameMode = 0; // ��Ϸģʽ
	boolean shoot = false; // Ӣ���Ƿ�ǹ
	int egg = 0; // �ʵ�
	int award = 0;// ����
	boolean awardTOF = true;// ��������
	int state = Screen.LOADING; // ��ʼ��¼��������Ϸ, 0 ������Ϸ
	int life = 5; // Ѫ��,��Ѫ
	int bossLife = 150;// boss��ʼ����ֵ
	int enemySpeed = (int) (Math.random() * 6 + 10); // �ؿ��ӵ����˵��ƶ��ٶ�
	int plainSpeed = (int) (Math.random() * 6 + 4);
	String img[] = { "images/bullet/herobullet0.png ", "images/bullet/herobullet1.png" };
	String loading[] = { "һ�����Ѽ������٣���Ƭ���ؽ�������һ��ɱ¾", "������Ϊ��Ƭ���ص��ػ��ߣ����ڴ��ػ�", "��ҹ��������Ƭ���أ������޷���ͷ", "�ȴ���Ľ����������ս�����㽫�����ԣ�",
			"��Enter��ʼ��Ϸ" };
	String chapter[] = { "��һ��", "�ڶ���", "����" };
	int chapterNo = 0;
	
	//��������
	Hero hero = new Hero();
	Boss boss = new Boss(BossNO);
	Score score2 = new Score();

	static final int enemyAmout = 10; // ������Ŀ
	int heroBulletMode = 1;// Ӣ���ӵ�ģʽ
	
	// ����
	ArrayList<String> lifeList = new ArrayList<String>();// �÷�
	Vector<HeroBullet> heroBullets = new Vector<HeroBullet>();// Ӣ���ӵ�����
	Vector<Enemy> enemys = new Vector<Enemy>();// �о�����
	Vector<Plain> plains = new Vector<Plain>();
	Vector<CleanEnemys> cleanEnemys = new Vector<CleanEnemys>();

	Vector<BossBullet0> b0 = new Vector<BossBullet0>();
	Vector<BossBullet1> b1 = new Vector<BossBullet1>();
	Vector<BossBullet2> b2 = new Vector<BossBullet2>();
	Vector<BossBullet3> b3 = new Vector<BossBullet3>();
	Vector<BossBullet4> b4 = new Vector<BossBullet4>();
	Vector<BossBullet5> b5 = new Vector<BossBullet5>();
	Vector<BossBullet6> b6 = new Vector<BossBullet6>();

	/*
	 * װ��С��
	 */
	int x = 0;
	int y = 100;
	int dir = 1;

	// ����paint
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// ��Ϸ����
		Image gameBg = Toolkit.getDefaultToolkit().getImage("images/title/title_bk01.png");
		g.drawImage(gameBg, 0, 0, gameX, gameY, this);

		new paintMap(g);// ����ͼ
		hero.drawHero(g);// ��Ӣ��
		this.drawHeroBullet(g);// Ӣ���ӵ�
		this.drawAward(g);// ������
		if (gameMode > 0) {
			this.drawLife(); // ��Ӣ������
			score2.drawScore(g, chapter[chapterNo], scoreTotal, lifeList.get(life), bossLife);// ����
		}

		// ��Ϸ�ؿ�
		//��½����
		if (0 == gameMode) {
			this.drawLoading(g);
		}
		//��һ����������
		if (1 == gameMode) {
			this.drawEnemy(g);
			chapterNo = 0;
		}
		//�ڶ��صл�
		if (2 == gameMode) {
			this.drawPlain(g);
			chapterNo = 1;
		}
		//������boss
		if (3 == gameMode) {
			boss.drawBoss(g);// ��boss
			this.drawBossBullet(g);
			this.drawEnemy(g);
			chapterNo = 2;
		}
		//��Ϸ����
		if (4 == gameMode) {
			this.drawOver(g);
			hero.heroX = (Screen.catoonX - hero.getheroSizeX()) / 2;
			hero.heroY = Screen.catoonY - hero.getheroSizeY()-50;
			egg = 1;
		}
		//��Ϸͨ��
		if (5 == gameMode) {
			this.drawEnd(g);
		}

		// װ��С��
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
	}

	// ��boss�ӵ�
	public void drawBossBullet(Graphics g) {
		for (int i = 0; i < b0.size(); i++) {
			b0.get(i).drawBossBullet(g);
		}
		for (int i = 0; i < b1.size(); i++) {
			b1.get(i).drawBossBullet(g);
		}
		for (int i = 0; i < b2.size(); i++) {
			b2.get(i).drawBossBullet(g);
		}
		for (int i = 0; i < b3.size(); i++) {
			b3.get(i).drawBossBullet(g);
		}
		for (int i = 0; i < b4.size(); i++) {
			b4.get(i).drawBossBullet(g);
		}
		for (int i = 0; i < b5.size(); i++) {
			b5.get(i).drawBossBullet(g);
		}
		for (int i = 0; i < b6.size(); i++) {
			b6.get(i).drawBossBullet(g);
		}
	}

	// ��ʼ�¼�����
	public void start() {
		state = Screen.RUNNING;
		gameMode = 1;
	}

	// ��ͣ�¼�����
	public void pause() {
		state = Screen.PAUSE;
	}

	// ���¿�ʼ�¼�����
	public void regame() {
		state = Screen.PAUSE;
		gameMode = 0;
		scoreTotal = 0;
	}

	// ����award
	public void drawAward(Graphics g) {
		for (int i = 0; i < cleanEnemys.size(); i++) {
			cleanEnemys.get(i).draw(g);
		}
	}

	// ��Ϸ����
	public void drawLoading(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("΢���ź�", Font.BOLD, 16));
		g.drawString(loading[0], Screen.screenX / 2 - 400, Screen.catoonY / 2 - 100);
		g.drawString(loading[1], Screen.screenX / 2 - 400, Screen.catoonY / 2 - 50);
		g.drawString(loading[2], Screen.screenX / 2 - 400, Screen.catoonY / 2);
		g.drawString(loading[3], Screen.screenX / 2 - 400, Screen.catoonY / 2 + 50);
		g.drawString(loading[4], Screen.screenX / 2 - 400, Screen.catoonY / 2 + 100);
	}

	// ��Ϸ�ɹ�������
	public void drawEnd(Graphics g) {
		g.setFont(new Font("΢���ź�", 0, 20));
		g.setColor(Color.white);
		g.drawString("�������е�����������������еĹ���", Screen.screenX / 2 - 400, Screen.catoonY / 2 - 100);
		g.drawString("��ҹ�ս���ɢ�����������ս�������㵹���˵���", Screen.screenX / 2 - 400, Screen.catoonY / 2 - 50);
		g.drawString("���ڵ���������Ƭ����������վ����", Screen.screenX / 2 - 400, Screen.catoonY / 2);
		g.drawString("��Ƭ��������������ػ��µõ��˰���", Screen.screenX / 2 - 400, Screen.catoonY / 2 + 50);
		g.drawString("�����ˣ���ҪΪ��˭����ø�ǿ", Screen.screenX / 2 - 400, Screen.catoonY / 2 + 100);
		g.drawString("��ϲ��ͨ������Ϸȫ���ؿ�����л���汾��Ϸ", Screen.screenX / 2 - 400, Screen.catoonY / 2 + 200);

	}

	// ��Ϸʧ�ܣ�����
	public void drawOver(Graphics g) {
		g.setColor(Color.decode("#a9a9c7"));
		g.setFont(new Font("΢���ź�", Font.BOLD, 40));
		g.drawString("��Ϸ���� ", Screen.screenX / 2 - 400, Screen.catoonY / 2 - 100);
		g.setFont(new Font("΢���ź�", Font.BOLD, 20));
		g.setColor(Color.decode("#d53e5a"));
		g.drawString("���յ÷֣� " + scoreTotal, Screen.screenX / 2 - 400, Screen.catoonY / 2 - 50);
		g.setColor(Color.decode("#62628c"));
		g.drawString("�ƺţ� " + assessGame, Screen.screenX / 2 - 400, Screen.catoonY / 2);
		g.drawString("���ۣ� " + assesString, Screen.screenX / 2 - 400, Screen.catoonY / 2 + 50);
	}

	// ����
	public void drawLife() {
		lifeList.add(new String("������"));
		lifeList.add(new String("������"));
		lifeList.add(new String("������"));
		lifeList.add(new String("������"));
		lifeList.add(new String("������"));
		lifeList.add(new String("������"));
	}

	// �ڲ��࣬��Ϸ��ͼ
	class paintMap {
		public paintMap(Graphics g) {
			Image bg = Toolkit.getDefaultToolkit().getImage("images/background/map.png");
			g.drawImage(bg, bgX, bgY, null);
		}
	}

	// Ӣ���ӵ�
	public void drawHeroBullet(Graphics g) {
		for (int i = 0; i < heroBullets.size(); i++) {
			heroBullets.get(i).drawHeroBullet(g);
		}
	}

	// ����
	// ���о�
	public void drawEnemy(Graphics g) {
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).drawEnemy(g);
		}
	}
	
	// ���ɻ�
	public void drawPlain(Graphics g) {
		for (int i = 0; i < plains.size(); i++) {
			plains.get(i).drawPlain(g);
		}
	}

	// KeyListener
	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	//���̰�ѹ
	@Override
	public void keyPressed(KeyEvent e) {
		// ���ƶ�
		if (KeyEvent.VK_A == e.getKeyCode() || KeyEvent.VK_LEFT == e.getKeyCode()) {
			hero.heroX -= 20;

			if (hero.heroX < bgX + 30) {
				hero.heroX = bgX + 30;
			}
		}
		// ���ƶ�
		if (KeyEvent.VK_D == e.getKeyCode() || KeyEvent.VK_RIGHT == e.getKeyCode()) {
			hero.heroX += 20;
			if (hero.heroX > bgX + bgSizeX - hero.getheroSizeX() - 30) {
				hero.heroX = bgX + bgSizeX - hero.getheroSizeX() - 30;
			}
		}
		// ���ƶ�
		if (KeyEvent.VK_W == e.getKeyCode() || KeyEvent.VK_UP == e.getKeyCode()) {
			hero.heroY -= 20;
			if (hero.heroY < 0) {
				hero.heroY = 0;
			}
		}
		// ���ƶ�
		if (KeyEvent.VK_S == e.getKeyCode() || KeyEvent.VK_DOWN == e.getKeyCode()) {
			// 18*20=360
			// 24*20=480
			hero.heroY += 20;

			if (hero.heroY > 450 + hero.getheroSizeY()) {
				hero.heroY = 450 + hero.getheroSizeY();
			}
		}

		// Ӣ���ӵ�
		if (KeyEvent.VK_Z == e.getKeyCode()) {
			shoot = true;
			if (heroBullets.size() < 10) {
				if (heroBulletMode == Screen.Mode1) {
					heroBullets.add(new HeroBullet(hero.heroX + 30, hero.heroY, img[1]));
				}
				if (heroBulletMode == Screen.Mode2) {
					heroBullets.add(new HeroBullet(hero.heroX - 15, hero.heroY + 70, img[0]));
					heroBullets.add(new HeroBullet(hero.heroX + 30, hero.heroY, img[0]));// ��
					heroBullets.add(new HeroBullet(hero.heroX + 75, hero.heroY + 70, img[0]));
				}
			}
//			while(shoot) {
//				 try {
//				 File f = new File("bgm/shoot.wav");
//				 URI uri = f.toURI();
//				 URL url = uri.toURL();//������ַ
//				 AudioClip soundAudioClip = Applet.newAudioClip(url);//AduoClip��
//				 soundAudioClip.play();//ѭ���������֣�stop����ֹͣ���ţ�paly()���β���������
//				 shoot = false;
//				 } catch (Exception e1) {
//				 e1.printStackTrace();
//				 }
//			 }
			/*
			 * ��ǹ���� bug��������ǹ����ֿ��١���δ�޸�2017��5��5�գ�������ǩ�ȣ���ʱͨ�������������޸���
			 * 
			 * ���£�
			 * ʱ�䣺2017-8-18
			 * ���ݣ������½���һ���߳��������ǹ�����⣬��������һ���̺߳ܿ�ͽ����ˡ����ǽ���֮���ּ������á�
			 * ���������д�Ļ���ÿ����һ���ӵ��ͻ���һ������������������ŷ���Ļ��ͻ���ɿ���״̬��Ŀǰ��˵
			 * ��Ҳû���뵽�õķ�������һ�����
			 */
		}
		
		repaint();

		// enter ������Ϸ
		if (KeyEvent.VK_ENTER == e.getKeyCode()) {
			state = Screen.RUNNING;
			gameMode = 1;
		}
		// ���¿�ʼ
		if (KeyEvent.VK_ENTER == e.getKeyCode() && life <= 0) {
			state = Screen.RUNNING;
			gameMode = 1;
			life = 5;
		}
		// ���¿�ʼ
		if (KeyEvent.VK_SPACE == e.getKeyCode() && life <= 0) {
			state = Screen.PAUSE;
			gameMode = 0;
			scoreTotal = 0;
		}
		// ��ͣ
		if (KeyEvent.VK_SPACE == e.getKeyCode()) {
			state = Screen.PAUSE;
		}
		// ����ʵ�
		if (KeyEvent.VK_ENTER == e.getKeyCode() && egg == 1) {
			life = 5;
			gameMode = 1;
		}
		// ��������
		if (KeyEvent.VK_X == e.getKeyCode() && award != 0) {
			award -= 1;
			enemys.clear();
			plains.clear();
			b0.clear();
			b1.clear();
			b2.clear();
			b3.clear();
			b4.clear();
			b5.clear();
			b6.clear();
			// scoreTotal += 10000;
			heroBullets.add(new HeroBullet(hero.heroX - 95, hero.heroY + 70, img[0]));// ��1
			heroBullets.add(new HeroBullet(hero.heroX - 70, hero.heroY + 70, img[0]));// ��1
			heroBullets.add(new HeroBullet(hero.heroX - 45, hero.heroY + 70, img[0]));// ��1
			heroBullets.add(new HeroBullet(hero.heroX - 20, hero.heroY + 70, img[0]));// ��2
			heroBullets.add(new HeroBullet(hero.heroX + 5, hero.heroY + 70, img[0]));// ��3
			heroBullets.add(new HeroBullet(hero.heroX + 30, hero.heroY + 70, img[0]));// ��
			heroBullets.add(new HeroBullet(hero.heroX + 55, hero.heroY + 70, img[0]));// ��1
			heroBullets.add(new HeroBullet(hero.heroX + 80, hero.heroY + 70, img[0]));// ��2
			heroBullets.add(new HeroBullet(hero.heroX + 105, hero.heroY + 70, img[0]));// ��3
			heroBullets.add(new HeroBullet(hero.heroX + 130, hero.heroY + 70, img[0]));// ��3
			heroBullets.add(new HeroBullet(hero.heroX + 155, hero.heroY + 70, img[0]));// ��3
		}

	}

	// MouseListener, MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}// ������
	public void mouseEntered(MouseEvent e) {}// ����˳�
	public void mouseExited(MouseEvent e) {}
	// ������
	@Override
	public void mouseMoved(MouseEvent e) {
		if(state == Screen.RUNNING && 4 != gameMode){
			hero.heroX = e.getX() - 35;// ��ȡ�������e.getX();
			hero.heroY = e.getY() - 70;
		}
		// Ӣ�۱仯
		if (hero.heroX < getX()) {
			hero.heroS = hero.heroString[2];
		}
		if (hero.heroX > getX()) {
			hero.heroS = hero.heroString[1];
		}
		if (hero.heroX == getX()) {
			hero.heroS = hero.heroString[0];
		}
		// Ӣ���ƶ�����
		if (hero.heroX < bgX + 30) {
			hero.heroX = bgX + 20;
		}
		if (hero.heroX > bgX + bgSizeX - hero.getheroSizeX() - 30) {
			hero.heroX = bgX + bgSizeX - hero.getheroSizeX() - 30;
		}
		if (hero.heroY < 0) {
			hero.heroY = 0;
		}
		if (hero.heroY > 450 + hero.getheroSizeY()) {
			hero.heroY = 450 + hero.getheroSizeY();
		}
		repaint();
	}

	// �ƶ��߳�
	// Ӣ���ƶ��߳�
	public void heroMoveThread() {

		new Thread() {
			public boolean stopped = false;

			public void run() {
				// 1.������
				// 2.repaint()
				// 3.����
				@SuppressWarnings("unused")
				int score = 0;
				int dirBoss = 0;
				@SuppressWarnings("unused")
				int dirBalls = (int) (Math.random() * 3 + 1);
				int count = 0;// ��ʱ��
				while (!stopped) {
					System.out.println();//����
					Random rand = new Random();
					int enemyDir = rand.nextInt(2);
					if (state == Screen.RUNNING) {
						count++;
						// �ؿ�1
						if (1 == gameMode) {
							// ����
							if (scoreTotal == 1000 && awardTOF == true) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = false;
							}
							if (scoreTotal == 10000 && awardTOF == false) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = true;
							}
							if (scoreTotal == 25000 && awardTOF == true) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = false;
							}
							if (scoreTotal == 40000 && awardTOF == false) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = true;
							}
							// ��Ϸ�����жϣ�������Ϸģʽ
							if (scoreTotal < 20000) {
								if (count % 7 == 0) {
									enemys.add(new Enemy(enemySpeed, enemyDir));
								}
							} else {
								if (count % 6 == 0) {
									enemys.add(new Enemy(enemySpeed,enemyDir));
								}
							}
							if (scoreTotal >= 20000) {
								enemySpeed = (int) (Math.random() * 8 + 5);
							}
							// ��һ��
							if (scoreTotal >= 50000) {
								enemys.clear();
								gameMode = 2;
								heroBulletMode = Screen.Mode2;
							}
							// �ź�
						}
						// �ؿ�2
						if (2 == gameMode) {
							// ����
							if (scoreTotal == 55000 && awardTOF == true) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = false;
							}
							if (scoreTotal == 70000 && awardTOF == false) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = true;
							}
							if (scoreTotal == 90000 && awardTOF == true) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = false;
							}
							if (scoreTotal == 10000 && awardTOF == false) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = true;
							}

							if (scoreTotal >= 50000 && scoreTotal <= 90000) {
								if (count % 20 == 0) {
									plains.add(new Plain(plainSpeed));
								}
							} else {
								if (count % 10 == 0) {
									plains.add(new Plain(plainSpeed));
								}
							}

							if (scoreTotal == 70000) {
								plainSpeed = (int) (Math.random() * 7 + 5);
							}
							if (scoreTotal == 80000) {
								plainSpeed = (int) (Math.random() * 9 + 6);
							}
							// ��һ��
							if (scoreTotal > 200000) {
								enemys.clear();
								plains.clear();
								gameMode = 3;
								heroBulletMode = Screen.Mode2;
							}
						}

						// �ؿ�3��bossģʽ����ͬ������Ӧ��ͬ���
						if (3 == gameMode) {
							if (scoreTotal > 120000 && scoreTotal <= 130000) {
								BossNO = 2;
							} else if (scoreTotal > 130000 && scoreTotal <= 150000) {
								BossNO = 3;
							} else if (scoreTotal > 150000 && scoreTotal <= 170000) {
								BossNO = 4;
							} else {
								BossNO = 5;
							}
							// ����
							if (scoreTotal == 120000 && awardTOF == true) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = false;
							}
							if (scoreTotal == 130000 && awardTOF == false) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = true;
							}
							if (scoreTotal == 150000 && awardTOF == true) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = false;
							}
							if (scoreTotal == 200000 && awardTOF == false) {
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								cleanEnemys.add(new CleanEnemys());
								awardTOF = true;
							}

							// boss�����ӵ�
							if (scoreTotal >= 100000 && scoreTotal < 150000) {
								if (count % 40 == 0) {
									int bossImgX = boss.bossImage.getWidth(null);
									int bossImgY = boss.bossImage.getWidth(null);
									b0.add(new BossBullet0(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b1.add(new BossBullet1(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b2.add(new BossBullet2(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b3.add(new BossBullet3(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b4.add(new BossBullet4(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b5.add(new BossBullet5(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b6.add(new BossBullet6(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
								}
							} else {
								if (count % 25 == 0) {
									int bossImgX = boss.bossImage.getWidth(null);
									int bossImgY = boss.bossImage.getWidth(null);
									b0.add(new BossBullet0(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b1.add(new BossBullet1(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b2.add(new BossBullet2(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b3.add(new BossBullet3(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b4.add(new BossBullet4(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b5.add(new BossBullet5(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
									b6.add(new BossBullet6(boss.bossX + bossImgX / 2, boss.bossY + bossImgY));
								}
							}

							// ��һ�ص�С��
							if (scoreTotal >= 100000 && scoreTotal < 150000) {
								if (count % 20 == 0) {
									enemySpeed = (int) (Math.random() * 8 + 5);
									enemys.add(new Enemy(enemySpeed,enemyDir));
								}
							} else {
								if (count % 30 == 0) {
									enemySpeed = (int) (Math.random() * 3 + 5);
									enemys.add(new Enemy(enemySpeed,enemyDir));
								}
							}

							boss.moveBoss(dirBoss);
							if (boss.bossX < 244) {
								dirBoss = 0;
							}
							if (boss.bossX > 696 - boss.bossImage.getWidth(null)) {
								dirBoss = 1;
							}
						}

						// �ƶ�
						// ��һ�ص��˵��ƶ�����ʧһ���������100
						for (int i = 0; i < enemys.size(); i++) {
							enemys.get(i).moveEnemy();
							if (enemys.get(i).getEnemyY() > 768) {
								enemys.remove(i);
								scoreTotal += 500;
							}
						}
						// �ڶ��طɻ����ƶ�
						for (int i = 0; i < plains.size(); i++) {
							plains.get(i).movePlain();
							if (plains.get(i).plainY > 768) {
								plains.remove(i);
							}
						}
						for (int i = 0; i < cleanEnemys.size(); i++) {
							cleanEnemys.get(i).move();
							if (cleanEnemys.get(i).x > 768) {
								cleanEnemys.remove(i);
							}
						}

						// ������boss�ӵ����ƶ�
						for (int i = 0; i < b0.size(); i++) {
							b0.get(i).shootBossBullet();
							if (b0.get(i).BossBulletY > 768 || b0.get(i).BossBulletX < 224
									|| b0.get(i).BossBulletX > 696) {
								b0.remove(i);
								scoreTotal += 5;
							}
						}
						for (int i = 0; i < b1.size(); i++) {
							b1.get(i).shootBossBullet();
							if (b1.get(i).BossBulletY > 768 || b1.get(i).BossBulletX < 224
									|| b1.get(i).BossBulletX > 696) {
								b1.remove(i);
								scoreTotal += 5;
							}
						}
						for (int i = 0; i < b2.size(); i++) {
							b2.get(i).shootBossBullet();
							if (b2.get(i).BossBulletY > 768 || b2.get(i).BossBulletX < 224
									|| b2.get(i).BossBulletX > 696) {
								b2.remove(i);
								scoreTotal += 5;
							}
						}
						for (int i = 0; i < b3.size(); i++) {
							b3.get(i).shootBossBullet();
							if (b3.get(i).BossBulletY > 768 || b3.get(i).BossBulletX < 224
									|| b3.get(i).BossBulletX > 696) {
								b3.remove(i);
								scoreTotal += 10;
							}
						}
						for (int i = 0; i < b4.size(); i++) {
							b4.get(i).shootBossBullet();
							if (b4.get(i).BossBulletY > 768 || b4.get(i).BossBulletX < 224
									|| b4.get(i).BossBulletX > 696) {
								b4.remove(i);
								scoreTotal += 5;
							}
						}
						for (int i = 0; i < b5.size(); i++) {
							b5.get(i).shootBossBullet();
							if (b5.get(i).BossBulletY > 768 || b5.get(i).BossBulletX < 224
									|| b5.get(i).BossBulletX > 696) {
								b5.remove(i);
								scoreTotal += 5;
							}
						}
						for (int i = 0; i < b6.size(); i++) {
							b6.get(i).shootBossBullet();
							if (b6.get(i).BossBulletY > 768 || b6.get(i).BossBulletX < 224
									|| b6.get(i).BossBulletX > 696) {
								b6.remove(i);
								scoreTotal += 5;
							}
						}

						// �ж�
						// Ӣ�۳Ե�������Ȼ�������������,���ָ�һ������
						for (int i = 0; i < cleanEnemys.size(); i++) {
							int cX = cleanEnemys.get(i).img.getWidth(null);
							int cY = cleanEnemys.get(i).img.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = cleanEnemys.get(i).x;
							int y1 = cleanEnemys.get(i).y;
							int x2 = hero.heroX;
							int y2 = hero.heroY;

							if (x1 > x2 && x1 + cX < x2 + heroImgX && y1 > y2 && y1 + cY < y2 + heroImgY) {
								award += 1;
								cleanEnemys.remove(i);
								if (life < 5) {
									life += 1;
								}
								break;
							}
						}
						// �жϵ���ײ����Ӣ��
						for (int i = 0; i < enemys.size(); i++) {
							int enemysImgX = enemys.get(i).getEnemyImg().getWidth(null);
							int enemysImgY = enemys.get(i).getEnemyImg().getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = enemys.get(i).getEnemyX();
							int y1 = enemys.get(i).getEnemyY();
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								enemys.remove(i);
								break;
							}
						}
						// �жϷɻ��ӵ�ײ����Ӣ��
						for (int i = 0; i < plains.size(); i++) {
							for (int j = 0; j < plains.get(i).plainBullets.size(); j++) {
								// �ж�ÿһ�ܷɻ����ӵ��Ƿ��Ӣ��
								int pBImgX = plains.get(i).plainBullets.get(j).plainBulletImage.getWidth(null);
								int pBImgY = plains.get(i).plainBullets.get(j).plainBulletImage.getHeight(null);
								int heroImgX = hero.getheroiImage().getWidth(null);
								int heroImgY = hero.getheroiImage().getHeight(null);

								int x1 = plains.get(i).plainBullets.get(j).plainBulletX;
								int y1 = plains.get(i).plainBullets.get(j).plainBulletY;
								int x2 = hero.heroX;
								int y2 = hero.heroY;
								if (x1 > x2 && x1 + pBImgX < x2 + heroImgX && y1 > y2 && y1 + pBImgY < y2 + heroImgY) {
									score -= 500;
									life -= 1;
									plains.get(i).plainBullets.remove(j);
									break;
								}
							}
						}

						// �ж���ʧ��Ӣ�۴򵽵���
						for (int i = 0; i < heroBullets.size(); i++) {
							for (int j = 0; j < enemys.size(); j++) {

								int x1 = heroBullets.get(i).getHeroBulletImage().getWidth(null);
								int y1 = heroBullets.get(i).getHeroBulletImage().getHeight(null);
								int x2 = enemys.get(j).getEnemyImg().getWidth(null);
								int y2 = enemys.get(j).getEnemyImg().getHeight(null);

								int bulletX = heroBullets.get(i).HeroBulletX;
								int bulletY = heroBullets.get(i).HeroBulletY;
								int enemysX = enemys.get(j).getEnemyX();
								int enemysY = enemys.get(j).getEnemyY();

								if (enemysX < bulletX && bulletX + x1 < enemysX + x2 && enemysY < bulletY
										&& bulletY + y1 < enemysY + y2) {
									heroBullets.remove(i);
									enemys.remove(j);
									scoreTotal += 1000;
									break;
								}
							}
						}

						// �ж���ʧ��Ӣ�۴򵽷ɻ�
						for (int i = 0; i < heroBullets.size(); i++) {
							for (int j = 0; j < plains.size(); j++) {
								int HBimgX = heroBullets.get(i).getHeroBulletImage().getWidth(null);
								int HBimgY = heroBullets.get(i).getHeroBulletImage().getHeight(null);
								int PimgX = plains.get(j).plainImage.getWidth(null);
								int PimgY = plains.get(j).plainImage.getHeight(null);

								int x1 = heroBullets.get(i).HeroBulletX;
								int y1 = heroBullets.get(i).HeroBulletY;
								int x2 = plains.get(j).plainX;
								int y2 = plains.get(j).plainY;
								if (x1 > x2 && x1 + HBimgX < x2 + PimgX && y1 > y2 && y1 + HBimgY < y2 + PimgY) {
									heroBullets.remove(i);
									plains.remove(j);
									scoreTotal += 1000;
									break;
								}
							}
						}

						// �ж�Ӣ���ӵ���boss
						if(3 == gameMode){
							for (int i = 0; i < heroBullets.size(); i++) {
								int HBimgX = heroBullets.get(i).getHeroBulletImage().getWidth(null);
								int HBimgY = heroBullets.get(i).getHeroBulletImage().getHeight(null);
								int bossImgX = boss.bossImage.getWidth(null);
								int bossImgY = boss.bossImage.getWidth(null);
								
								int x1 = heroBullets.get(i).HeroBulletX;
								int y1 = heroBullets.get(i).HeroBulletY;
								int x2 = boss.bossX;
								int y2 = boss.bossY;
								if (x1 > x2 && x1 + HBimgX < x2 + bossImgX && y1 > y2 && y1 + HBimgY < y2 + bossImgY) {
									heroBullets.remove(i);
									scoreTotal += 3000;
									bossLife -= 0.0000000000001;
									break;
								}
							}
						}

						// �ж�boss�ӵ���Ӣ��
						for (int i = 0; i < b0.size(); i++) {
							int enemysImgX = b0.get(i).bossBulletImage.getWidth(null);
							int enemysImgY = b0.get(i).bossBulletImage.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = b0.get(i).BossBulletX;
							int y1 = b0.get(i).BossBulletY;
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								b0.remove(i);
								break;
							}
						}
						for (int i = 0; i < b1.size(); i++) {
							int enemysImgX = b1.get(i).bossBulletImage.getWidth(null);
							int enemysImgY = b1.get(i).bossBulletImage.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = b1.get(i).BossBulletX;
							int y1 = b1.get(i).BossBulletY;
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								b1.remove(i);
								break;
							}
						}
						for (int i = 0; i < b2.size(); i++) {
							int enemysImgX = b2.get(i).bossBulletImage.getWidth(null);
							int enemysImgY = b2.get(i).bossBulletImage.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = b2.get(i).BossBulletX;
							int y1 = b2.get(i).BossBulletY;
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								b2.remove(i);
								break;
							}
						}
						for (int i = 0; i < b3.size(); i++) {
							int enemysImgX = b3.get(i).bossBulletImage.getWidth(null);
							int enemysImgY = b3.get(i).bossBulletImage.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = b3.get(i).BossBulletX;
							int y1 = b3.get(i).BossBulletY;
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								b3.remove(i);
								break;
							}
						}
						for (int i = 0; i < b4.size(); i++) {
							int enemysImgX = b4.get(i).bossBulletImage.getWidth(null);
							int enemysImgY = b4.get(i).bossBulletImage.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = b4.get(i).BossBulletX;
							int y1 = b4.get(i).BossBulletY;
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								b4.remove(i);
								break;
							}
						}
						for (int i = 0; i < b5.size(); i++) {
							int enemysImgX = b5.get(i).bossBulletImage.getWidth(null);
							int enemysImgY = b5.get(i).bossBulletImage.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = b5.get(i).BossBulletX;
							int y1 = b5.get(i).BossBulletY;
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								b5.remove(i);
								break;
							}
						}
						for (int i = 0; i < b6.size(); i++) {
							int enemysImgX = b6.get(i).bossBulletImage.getWidth(null);
							int enemysImgY = b6.get(i).bossBulletImage.getHeight(null);
							int heroImgX = hero.getheroiImage().getWidth(null);
							int heroImgY = hero.getheroiImage().getHeight(null);

							int x1 = b6.get(i).BossBulletX;
							int y1 = b6.get(i).BossBulletY;
							int x2 = hero.heroX;
							int y2 = hero.heroY;
							if (x1 > x2 && x1 + enemysImgX < x2 + heroImgX && y1 > y2
									&& y1 + enemysImgY < y2 + heroImgY) {
								score -= 500;
								life -= 1;
								b6.remove(i);
								break;
							}
						}
						// Ӣ���ӵ�����
						for (int i = 0; i < heroBullets.size(); i++) {
							heroBullets.get(i).shootHeroBullet(10);
							if (heroBullets.get(i).getHeroBulletY() < 0) {
								heroBullets.remove(i);
							}
						}

						// ��Ϸ����
						// Ӣ����������Ϸ����,��ղ���Ҫ�ļ���
						if (life <= 0) {
							enemys.clear();
							plains.clear();
							b0.clear();
							b1.clear();
							b2.clear();
							b3.clear();
							b4.clear();
							b5.clear();
							b6.clear();
							gameMode = 4;
						}
						// ���boss��ʤ����Ϸ����
						if (bossLife <= 0) {
							enemys.clear();
							plains.clear();
							b0.clear();
							b1.clear();
							b2.clear();
							b3.clear();
							b4.clear();
							b5.clear();
							b6.clear();
							gameMode = 5;
						}
						// �������ۣ�10�ֲ�ͬ�Ľ�ֺ�����
						if (scoreTotal <= 20000) {
							assessGame = "����";
							assesString = "���Ѿ���Ⱥ��Ա�ˣ������ʹ�Ҵ���к���";
						} else if (scoreTotal > 20000 && scoreTotal <= 50000) {
							assessGame = "����ʦ��ѧԱ";
							assesString = "������һСѧ������ô˵���G����Ƥ����Ƥ";
						} else if (scoreTotal > 50000 && scoreTotal <= 70000) {
							assessGame = "����ʦ�ƴ���";
							assesString = "С���ӣ�����������԰����Ժ�������ҵĿδ�����";
						} else if (scoreTotal > 70000 && scoreTotal <= 90000) {
							assessGame = "�羺����ӥ";
							assesString = "������Ҫ���أ�����˫�ֳɾ����δ����";
						} else if (scoreTotal > 90000 && scoreTotal <= 120000) {
							assessGame = "��װ�Ǵ���";
							assesString = "�������ٲ�Ůװ�Ҿ�Ҫ��ǹ�ˣ���ǹ���ƣ�";
						} else if (scoreTotal > 120000 && scoreTotal <= 150000) {
							assessGame = "�����ʦ";
							assesString = "���ʴ�ȣ����ʴ�ȣ��������dj�������𣨴�����";
						} else if (scoreTotal > 150000 && scoreTotal <= 180000) {
							assessGame = "����ɽ��˾��";
							assesString = "��������񳵵������ڣ�����������˾��";
						} else if (scoreTotal > 180000 && scoreTotal <= 210000) {
							assessGame = "�ᰮ����";
							assesString = "�赸���ҵ�������־����ҵ���꣬������������";
						} else if (scoreTotal > 210000 && scoreTotal <= 300000) {
							assessGame = "CrazyRockBOY";
							assesString = "ֻҪ�������������ҵ�˫�־Ͳ���ֹͣ";
						} else {
							assessGame = "�������";
							assesString = "�������ģ��������飬��η��������ϲ���Ѷɽٳɹ�";
						}
						try {
							sleep(20);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};
		}.start();
	}

	// ��ͼ�ƶ��߳�
	public void mapMoveThread() {
		new Thread() {
			public void run() {
				while (true) {
					System.out.println();
					if (state == Screen.RUNNING) {
						// ��ͼ�ƶ�
						bgX += 0;
						bgY += 0.0000000001;
						if (bgY == 0) {
							bgY = -bgSizeY + Screen.screenY;
						}
						repaint();
						try {
							sleep(10);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			};
		}.start();
	}

	// װ��С���ƶ��߳�
	public void moveOrv() {
		new Thread() {
			public void run() {
				int dir = 1;
				while (true) {
					System.out.println();
					if (state == Screen.RUNNING) {
						try {
							sleep(50);
						} catch (Exception e) {
							e.printStackTrace();
						}
						if (y < 0) {
							dir = 1;
						}
						if (x < 224) {
							dir = 4;
						}
						if (x > 696) {
							if (dir == 1) {
								dir = 2;
							} else {

								dir = 3;
							}
						}
						if (y > 640) {
							if (dir == 2) {

								dir = 3;
							} else {
								dir = 4;
							}
						}
						if (dir == 1) {
							x += 5;
							y += 5;
						}
						if (2 == dir) {
							x -= 5;
							y += 5;
						}
						if (3 == dir) {
							x -= 5;
							y -= 5;
						}
						if (4 == dir) {
							x += 5;
							y -= 5;
						}
						repaint();
					}
				}
			};
		}.start();
	}
}
