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

//画板
@SuppressWarnings("serial")
public class GameJPanel extends JPanel implements MouseListener, MouseMotionListener, KeyListener {

	// 游戏背景960*720
	public int gameX = 960;
	public int gameY = 720;
	
	// 地图图片大小：512*2816
	public int bgSizeX = 512;
	public int bgSizeY = 12288;
	public int bgX = (Screen.catoonX - bgSizeX) / 2;
	public int bgY = -bgSizeY + Screen.screenY;

	// 控制游戏
	public long scoreTotal = 0; // 分数
	public String assessGame = "萌新"; // 称号
	public String assesString = "恭喜你送了全队人头";// 评价
	int BossNO = 1;// boss 模式
	int gameMode = 0; // 游戏模式
	boolean shoot = false; // 英雄是否开枪
	int egg = 0; // 彩蛋
	int award = 0;// 奖励
	boolean awardTOF = true;// 奖励开关
	int state = Screen.LOADING; // 初始登录，控制游戏, 0 进入游戏
	int life = 5; // 血条,满血
	int bossLife = 150;// boss初始生命值
	int enemySpeed = (int) (Math.random() * 6 + 10); // 关卡子弹敌人的移动速度
	int plainSpeed = (int) (Math.random() * 6 + 4);
	String img[] = { "images/bullet/herobullet0.png ", "images/bullet/herobullet1.png" };
	String loading[] = { "一场灾难即将来临，这片土地将会面临一场杀戮", "而你作为这片土地的守护者，将在此守护", "黑夜覆盖了这片土地，而你无法回头", "等待你的将会是无情的战场，你将如何面对？",
			"按Enter开始游戏" };
	String chapter[] = { "第一章", "第二章", "终章" };
	int chapterNo = 0;
	
	//创建对象
	Hero hero = new Hero();
	Boss boss = new Boss(BossNO);
	Score score2 = new Score();

	static final int enemyAmout = 10; // 敌人数目
	int heroBulletMode = 1;// 英雄子弹模式
	
	// 集合
	ArrayList<String> lifeList = new ArrayList<String>();// 得分
	Vector<HeroBullet> heroBullets = new Vector<HeroBullet>();// 英雄子弹集合
	Vector<Enemy> enemys = new Vector<Enemy>();// 敌军集合
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
	 * 装饰小球
	 */
	int x = 0;
	int y = 100;
	int dir = 1;

	// 覆盖paint
	@Override
	public void paint(Graphics g) {
		super.paint(g);

		// 游戏背景
		Image gameBg = Toolkit.getDefaultToolkit().getImage("images/title/title_bk01.png");
		g.drawImage(gameBg, 0, 0, gameX, gameY, this);

		new paintMap(g);// 画地图
		hero.drawHero(g);// 画英雄
		this.drawHeroBullet(g);// 英雄子弹
		this.drawAward(g);// 画奖励
		if (gameMode > 0) {
			this.drawLife(); // 画英雄生命
			score2.drawScore(g, chapter[chapterNo], scoreTotal, lifeList.get(life), bossLife);// 分数
		}

		// 游戏关卡
		//登陆界面
		if (0 == gameMode) {
			this.drawLoading(g);
		}
		//第一关跳动的球
		if (1 == gameMode) {
			this.drawEnemy(g);
			chapterNo = 0;
		}
		//第二关敌机
		if (2 == gameMode) {
			this.drawPlain(g);
			chapterNo = 1;
		}
		//第三关boss
		if (3 == gameMode) {
			boss.drawBoss(g);// 画boss
			this.drawBossBullet(g);
			this.drawEnemy(g);
			chapterNo = 2;
		}
		//游戏结束
		if (4 == gameMode) {
			this.drawOver(g);
			hero.heroX = (Screen.catoonX - hero.getheroSizeX()) / 2;
			hero.heroY = Screen.catoonY - hero.getheroSizeY()-50;
			egg = 1;
		}
		//游戏通关
		if (5 == gameMode) {
			this.drawEnd(g);
		}

		// 装饰小球
		g.setColor(Color.red);
		g.fillOval(x, y, 50, 50);
	}

	// 画boss子弹
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

	// 开始事件触发
	public void start() {
		state = Screen.RUNNING;
		gameMode = 1;
	}

	// 暂停事件触发
	public void pause() {
		state = Screen.PAUSE;
	}

	// 重新开始事件触发
	public void regame() {
		state = Screen.PAUSE;
		gameMode = 0;
		scoreTotal = 0;
	}

	// 奖励award
	public void drawAward(Graphics g) {
		for (int i = 0; i < cleanEnemys.size(); i++) {
			cleanEnemys.get(i).draw(g);
		}
	}

	// 游戏加载
	public void drawLoading(Graphics g) {
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.BOLD, 16));
		g.drawString(loading[0], Screen.screenX / 2 - 400, Screen.catoonY / 2 - 100);
		g.drawString(loading[1], Screen.screenX / 2 - 400, Screen.catoonY / 2 - 50);
		g.drawString(loading[2], Screen.screenX / 2 - 400, Screen.catoonY / 2);
		g.drawString(loading[3], Screen.screenX / 2 - 400, Screen.catoonY / 2 + 50);
		g.drawString(loading[4], Screen.screenX / 2 - 400, Screen.catoonY / 2 + 100);
	}

	// 游戏成功，结束
	public void drawEnd(Graphics g) {
		g.setFont(new Font("微软雅黑", 0, 20));
		g.setColor(Color.white);
		g.drawString("紧握手中的利器，不灭的是心中的光明", Screen.screenX / 2 - 400, Screen.catoonY / 2 - 100);
		g.drawString("黑夜终将消散于你的领土，战争过后你倒在了地上", Screen.screenX / 2 - 400, Screen.catoonY / 2 - 50);
		g.drawString("躺在地上望着这片尘土已无力站起来", Screen.screenX / 2 - 400, Screen.catoonY / 2);
		g.drawString("这片土地终于在你的守护下得到了安宁", Screen.screenX / 2 - 400, Screen.catoonY / 2 + 50);
		g.drawString("是男人，就要为了谁而变得更强", Screen.screenX / 2 - 400, Screen.catoonY / 2 + 100);
		g.drawString("恭喜你通过了游戏全部关卡！感谢你玩本游戏", Screen.screenX / 2 - 400, Screen.catoonY / 2 + 200);

	}

	// 游戏失败，结束
	public void drawOver(Graphics g) {
		g.setColor(Color.decode("#a9a9c7"));
		g.setFont(new Font("微软雅黑", Font.BOLD, 40));
		g.drawString("游戏结束 ", Screen.screenX / 2 - 400, Screen.catoonY / 2 - 100);
		g.setFont(new Font("微软雅黑", Font.BOLD, 20));
		g.setColor(Color.decode("#d53e5a"));
		g.drawString("最终得分： " + scoreTotal, Screen.screenX / 2 - 400, Screen.catoonY / 2 - 50);
		g.setColor(Color.decode("#62628c"));
		g.drawString("称号： " + assessGame, Screen.screenX / 2 - 400, Screen.catoonY / 2);
		g.drawString("评价： " + assesString, Screen.screenX / 2 - 400, Screen.catoonY / 2 + 50);
	}

	// 生命
	public void drawLife() {
		lifeList.add(new String("☆☆☆☆☆"));
		lifeList.add(new String("★☆☆☆☆"));
		lifeList.add(new String("★★☆☆☆"));
		lifeList.add(new String("★★★☆☆"));
		lifeList.add(new String("★★★★☆"));
		lifeList.add(new String("★★★★★"));
	}

	// 内部类，游戏地图
	class paintMap {
		public paintMap(Graphics g) {
			Image bg = Toolkit.getDefaultToolkit().getImage("images/background/map.png");
			g.drawImage(bg, bgX, bgY, null);
		}
	}

	// 英雄子弹
	public void drawHeroBullet(Graphics g) {
		for (int i = 0; i < heroBullets.size(); i++) {
			heroBullets.get(i).drawHeroBullet(g);
		}
	}

	// 敌人
	// 画敌军
	public void drawEnemy(Graphics g) {
		for (int i = 0; i < enemys.size(); i++) {
			enemys.get(i).drawEnemy(g);
		}
	}
	
	// 画飞机
	public void drawPlain(Graphics g) {
		for (int i = 0; i < plains.size(); i++) {
			plains.get(i).drawPlain(g);
		}
	}

	// KeyListener
	@Override
	public void keyTyped(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	//键盘按压
	@Override
	public void keyPressed(KeyEvent e) {
		// 左移动
		if (KeyEvent.VK_A == e.getKeyCode() || KeyEvent.VK_LEFT == e.getKeyCode()) {
			hero.heroX -= 20;

			if (hero.heroX < bgX + 30) {
				hero.heroX = bgX + 30;
			}
		}
		// 右移动
		if (KeyEvent.VK_D == e.getKeyCode() || KeyEvent.VK_RIGHT == e.getKeyCode()) {
			hero.heroX += 20;
			if (hero.heroX > bgX + bgSizeX - hero.getheroSizeX() - 30) {
				hero.heroX = bgX + bgSizeX - hero.getheroSizeX() - 30;
			}
		}
		// 上移动
		if (KeyEvent.VK_W == e.getKeyCode() || KeyEvent.VK_UP == e.getKeyCode()) {
			hero.heroY -= 20;
			if (hero.heroY < 0) {
				hero.heroY = 0;
			}
		}
		// 下移动
		if (KeyEvent.VK_S == e.getKeyCode() || KeyEvent.VK_DOWN == e.getKeyCode()) {
			// 18*20=360
			// 24*20=480
			hero.heroY += 20;

			if (hero.heroY > 450 + hero.getheroSizeY()) {
				hero.heroY = 450 + hero.getheroSizeY();
			}
		}

		// 英雄子弹
		if (KeyEvent.VK_Z == e.getKeyCode()) {
			shoot = true;
			if (heroBullets.size() < 10) {
				if (heroBulletMode == Screen.Mode1) {
					heroBullets.add(new HeroBullet(hero.heroX + 30, hero.heroY, img[1]));
				}
				if (heroBulletMode == Screen.Mode2) {
					heroBullets.add(new HeroBullet(hero.heroX - 15, hero.heroY + 70, img[0]));
					heroBullets.add(new HeroBullet(hero.heroX + 30, hero.heroY, img[0]));// 中
					heroBullets.add(new HeroBullet(hero.heroX + 75, hero.heroY + 70, img[0]));
				}
			}
//			while(shoot) {
//				 try {
//				 File f = new File("bgm/shoot.wav");
//				 URI uri = f.toURI();
//				 URL url = uri.toURL();//解析地址
//				 AudioClip soundAudioClip = Applet.newAudioClip(url);//AduoClip类
//				 soundAudioClip.play();//循环播放音乐，stop（）停止播放，paly()依次播放声音。
//				 shoot = false;
//				 } catch (Exception e1) {
//				 e1.printStackTrace();
//				 }
//			 }
			/*
			 * 开枪声音 bug：连续开枪会出现卡顿。暂未修复2017年5月5日（留个标签先，到时通过搜索好找来修复）
			 * 
			 * 更新：
			 * 时间：2017-8-18
			 * 内容：尝试新建立一个线程用来解决枪声问题，后面想了一下线程很快就结束了。除非结束之后又继续调用。
			 * 如果在这里写的话，每生成一个子弹就会响一声，但是如果连续按着发射的话就会造成卡死状态。目前来说
			 * 我也没有想到好的方法，这一个设计
			 */
		}
		
		repaint();

		// enter 进入游戏
		if (KeyEvent.VK_ENTER == e.getKeyCode()) {
			state = Screen.RUNNING;
			gameMode = 1;
		}
		// 重新开始
		if (KeyEvent.VK_ENTER == e.getKeyCode() && life <= 0) {
			state = Screen.RUNNING;
			gameMode = 1;
			life = 5;
		}
		// 重新开始
		if (KeyEvent.VK_SPACE == e.getKeyCode() && life <= 0) {
			state = Screen.PAUSE;
			gameMode = 0;
			scoreTotal = 0;
		}
		// 暂停
		if (KeyEvent.VK_SPACE == e.getKeyCode()) {
			state = Screen.PAUSE;
		}
		// 复活彩蛋
		if (KeyEvent.VK_ENTER == e.getKeyCode() && egg == 1) {
			life = 5;
			gameMode = 1;
		}
		// 奖励清屏
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
			heroBullets.add(new HeroBullet(hero.heroX - 95, hero.heroY + 70, img[0]));// 左1
			heroBullets.add(new HeroBullet(hero.heroX - 70, hero.heroY + 70, img[0]));// 左1
			heroBullets.add(new HeroBullet(hero.heroX - 45, hero.heroY + 70, img[0]));// 左1
			heroBullets.add(new HeroBullet(hero.heroX - 20, hero.heroY + 70, img[0]));// 左2
			heroBullets.add(new HeroBullet(hero.heroX + 5, hero.heroY + 70, img[0]));// 左3
			heroBullets.add(new HeroBullet(hero.heroX + 30, hero.heroY + 70, img[0]));// 中
			heroBullets.add(new HeroBullet(hero.heroX + 55, hero.heroY + 70, img[0]));// 右1
			heroBullets.add(new HeroBullet(hero.heroX + 80, hero.heroY + 70, img[0]));// 右2
			heroBullets.add(new HeroBullet(hero.heroX + 105, hero.heroY + 70, img[0]));// 右3
			heroBullets.add(new HeroBullet(hero.heroX + 130, hero.heroY + 70, img[0]));// 右3
			heroBullets.add(new HeroBullet(hero.heroX + 155, hero.heroY + 70, img[0]));// 右3
		}

	}

	// MouseListener, MouseMotionListener
	@Override
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}// 鼠标进入
	public void mouseEntered(MouseEvent e) {}// 鼠标退出
	public void mouseExited(MouseEvent e) {}
	// 鼠标控制
	@Override
	public void mouseMoved(MouseEvent e) {
		if(state == Screen.RUNNING && 4 != gameMode){
			hero.heroX = e.getX() - 35;// 获取鼠标坐标e.getX();
			hero.heroY = e.getY() - 70;
		}
		// 英雄变化
		if (hero.heroX < getX()) {
			hero.heroS = hero.heroString[2];
		}
		if (hero.heroX > getX()) {
			hero.heroS = hero.heroString[1];
		}
		if (hero.heroX == getX()) {
			hero.heroS = hero.heroString[0];
		}
		// 英雄移动限制
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

	// 移动线程
	// 英雄移动线程
	public void heroMoveThread() {

		new Thread() {
			public boolean stopped = false;

			public void run() {
				// 1.改坐标
				// 2.repaint()
				// 3.休眠
				@SuppressWarnings("unused")
				int score = 0;
				int dirBoss = 0;
				@SuppressWarnings("unused")
				int dirBalls = (int) (Math.random() * 3 + 1);
				int count = 0;// 定时器
				while (!stopped) {
					System.out.println();//测试
					Random rand = new Random();
					int enemyDir = rand.nextInt(2);
					if (state == Screen.RUNNING) {
						count++;
						// 关卡1
						if (1 == gameMode) {
							// 奖励
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
							// 游戏分数判断，控制游戏模式
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
							// 下一关
							if (scoreTotal >= 50000) {
								enemys.clear();
								gameMode = 2;
								heroBulletMode = Screen.Mode2;
							}
							// 嗯哼
						}
						// 关卡2
						if (2 == gameMode) {
							// 奖励
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
							// 下一关
							if (scoreTotal > 200000) {
								enemys.clear();
								plains.clear();
								gameMode = 3;
								heroBulletMode = Screen.Mode2;
							}
						}

						// 关卡3，boss模式，不同分数对应不同外表
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
							// 奖励
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

							// boss发射子弹
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

							// 第一关的小球
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

						// 移动
						// 第一关敌人的移动，消失一个球分数加100
						for (int i = 0; i < enemys.size(); i++) {
							enemys.get(i).moveEnemy();
							if (enemys.get(i).getEnemyY() > 768) {
								enemys.remove(i);
								scoreTotal += 500;
							}
						}
						// 第二关飞机的移动
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

						// 第三关boss子弹的移动
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

						// 判断
						// 英雄吃到奖励，然后或者清屏机会,并恢复一滴生命
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
						// 判断敌人撞击到英雄
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
						// 判断飞机子弹撞击到英雄
						for (int i = 0; i < plains.size(); i++) {
							for (int j = 0; j < plains.get(i).plainBullets.size(); j++) {
								// 判断每一架飞机的子弹是否打到英雄
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

						// 判断消失，英雄打到敌人
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

						// 判断消失，英雄打到飞机
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

						// 判断英雄子弹打到boss
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

						// 判断boss子弹打到英雄
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
						// 英雄子弹出界
						for (int i = 0; i < heroBullets.size(); i++) {
							heroBullets.get(i).shootHeroBullet(10);
							if (heroBullets.get(i).getHeroBulletY() < 0) {
								heroBullets.remove(i);
							}
						}

						// 游戏结束
						// 英雄死亡，游戏结束,清空不需要的集合
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
						// 打败boss，胜利游戏结束
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
						// 分数评价，10种不同的结局和评价
						if (scoreTotal <= 20000) {
							assessGame = "萌新";
							assesString = "你已经是群成员了，快来和大家打个招呼。";
						} else if (scoreTotal > 20000 && scoreTotal <= 50000) {
							assessGame = "马老师新学员";
							assesString = "国服第一小学生，怎么说？欸，你皮任你皮";
						} else if (scoreTotal > 50000 && scoreTotal <= 70000) {
							assessGame = "马老师科代表";
							assesString = "小伙子，看你很有灵性啊，以后你就是我的课代表了";
						} else if (scoreTotal > 70000 && scoreTotal <= 90000) {
							assessGame = "电竞加藤鹰";
							assesString = "哪里需要我呢？我用双手成就你的未来。";
						} else if (scoreTotal > 90000 && scoreTotal <= 120000) {
							assessGame = "假装是大佬";
							assesString = "大佬你再不女装我就要开枪了（开枪手势）";
						} else if (scoreTotal > 120000 && scoreTotal <= 150000) {
							assessGame = "节奏大师";
							assesString = "动词大慈，动词大慈，如果我是dj你会打我吗（打死）";
						} else if (scoreTotal > 150000 && scoreTotal <= 180000) {
							assessGame = "秋明山老司机";
							assesString = "唉唉，如今车道依旧在，不见当年老司机";
						} else if (scoreTotal > 180000 && scoreTotal <= 210000) {
							assessGame = "葬爱家族";
							assesString = "舞蹈是我的生活，音乐就是我的灵魂，都给我嗨起来";
						} else if (scoreTotal > 210000 && scoreTotal <= 300000) {
							assessGame = "CrazyRockBOY";
							assesString = "只要心依旧跳动，我的双手就不会停止";
						} else {
							assessGame = "遁入空门";
							assesString = "不乱于心，不困于情，不畏将来。恭喜道友渡劫成功";
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

	// 地图移动线程
	public void mapMoveThread() {
		new Thread() {
			public void run() {
				while (true) {
					System.out.println();
					if (state == Screen.RUNNING) {
						// 地图移动
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

	// 装饰小球移动线程
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
