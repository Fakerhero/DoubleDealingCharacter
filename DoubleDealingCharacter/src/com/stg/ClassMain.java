package com.stg;

import java.awt.Dimension;
import java.awt.Toolkit;

//��������
public class ClassMain {
	// Main����
	public static void main(String[] args) {
		new GameJFrame("�|���xᘳ�");
	}
}

// ��Ļsize�ӿ�
interface Screen {
	// final
	Toolkit screen = Toolkit.getDefaultToolkit();
	Dimension screeDimension = screen.getScreenSize();
	public static final int screenX = (int) screeDimension.getWidth();
	public static final int screenY = (int) screeDimension.getHeight();
	public static final int catoonX = 960;
	public static final int catoonY = 720;
	// ��Ϸ����
	public static final int LOADING = 0;
	public static final int RUNNING = 1;
	public static final int PAUSE = 2;
	public static final int END = 3;
	// �ӵ�ģʽ
	public static final int Mode1 = 1;
	public static final int Mode2 = 2;
	public static final int Mode3 = 3;
	// ������clear����
}
