package com.qust.zq.android;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @=================== Done ===================
 * @ro值获取 adb shell getprop ro.build.type
 * @解析AndroidManifest.xml文件 使用方法：Utils.parseAndroidManifest();
 * @全键盘 改善输入速度？？？
 * @T9键盘 改善输入速度？？？
 * @快速生成上传记录 如何根据bugId,自动获取bug名称？？？
 * 
 * @=================== Todos ===================
 * @将所有功能做成Button一键模式
 * @一键编译 显示编译状态
 * @签名
 * @反编译APK
 * @完美汉化？？
 * @从apk中获取libs
 * @模块编译 remount,make,mkdir,push,ps,kill,reboot
 * @启动Activity adb shell am start -n com.android.launcher3/com.android.launcher3.Launcher
 * @快速修改默认值 adb shell sqlite3 /data/data/com.android.providers.settings/databases/settings.db “update system set value='1800000' where name='screen_off_timeout';“
 * @数据库读取 adb shell sqlite3 /data/data/com.android.launcher3/databases/launcher.db
 * @抓log
 * @log分析
 * @查看布局
 * @mkdir 一键push歌曲 adb shell mkdir /storage/sdcard0/Music;adb push .. /storage/sdcard0/Music/
 * @一键快速加宏 xml文件修改（修改宏，添加宏）
 * @支持的语言查询
 * @修改编译ICU资源
 * @实时显示手机屏幕
 * @模拟鼠标？？
 * @checklist检查？？
 * @萨瑞联系人信息查询
 * @色值查询和转换
 */
public class Keyboard {
	public static void main(String[] args) {
		NewFrame frame = new NewFrame(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 一定要设置关闭
		frame.setVisible(true);
		NewFrame frame2 = new NewFrame(false);
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 一定要设置关闭
		frame2.setVisible(true);
		frame2.setLocation(800, 0);
		System.out.println(0x7f060058);
		//
		// System.out.println(Utils.parseWorkspace("/home/zq/Desktop/test.txt"));
		// Utils.getAndroidVersion();
		// Utils.updateSettingsDatabase("", "", "");
		// System.out.println((char)('A'+1)+"");
	}
}

class NewFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	public NewFrame(boolean isFullKey) {
		super();
		if (isFullKey) {
			initFullKeyboard();
		} else {
			initTwelveKeyboard();
		}
	}
	private void initFullKeyMap() {
		keyMap = new HashMap<Integer, KeyObject>();
		// 字母
		for (int i = 11; i <= 36; i++) {
			keyMap.put(i, new KeyObject((char) ('A' + i - 11) + "", KeyObject.KEYCODE_A + i - 11));
		}
		// keyMap.put(40, new KeyObject("Clear", KeyObject.KEYCODE_CLEAR));
		// 数字
		for (int i = 41; i <= 50; i++) {
			keyMap.put(i, new KeyObject(i - 41 + "", KeyObject.KEYCODE_0 + i - 41));
		}
		// 符号
		keyMap.put(51, new KeyObject("@", KeyObject.KEYCODE_AT));
		keyMap.put(52, new KeyObject(".", KeyObject.KEYCODE_PERIOD));
		keyMap.put(53, new KeyObject("-", KeyObject.KEYCODE_MINUS));
		keyMap.put(54, new KeyObject("+", KeyObject.KEYCODE_PLUS));
		keyMap.put(55, new KeyObject("=", KeyObject.KEYCODE_EQUALS));
		keyMap.put(56, new KeyObject("[", KeyObject.KEYCODE_LEFT_BRACKET));
		keyMap.put(57, new KeyObject("]", KeyObject.KEYCODE_RIGHT_BRACKET));
		keyMap.put(58, new KeyObject("/", KeyObject.KEYCODE_SLASH));
		keyMap.put(59, new KeyObject("\\", KeyObject.KEYCODE_BACKSLASH));
		keyMap.put(60, new KeyObject("DEL", KeyObject.KEYCODE_DEL));
	}
	private void initFullKeyboard() {
		initFullKeyMap();
		this.setSize(800, 300);
		this.getContentPane().setLayout(new BorderLayout());// 设置布局控制器
		this.setTitle("Android Full Keyboard");// 设置窗口标题
		//
		final TextArea textArea = new TextArea(5, 30);
		JPanel jPanel1 = new JPanel(new BorderLayout());
		jPanel1.add(textArea);
		JButton btnSend = new JButton("SEND");
		btnSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						Utils.execCommand("adb shell input text " + textArea.getText());
					}
				}).start();
			}
		});
		jPanel1.add(btnSend, BorderLayout.EAST);
		this.add(jPanel1, BorderLayout.NORTH);
		//
		JPanel jPanel = new JPanel(new GridLayout(5, 10));
		for (int i = 1; i <= 5; i++) {
			for (int k = 1; k <= 10; k++) {
				jPanel.add(getKeyButton(i * 10 + k));
			}
		}
		this.add(jPanel, BorderLayout.CENTER);
	}
	//
	private void initTwelveKeyboard() {
		initKeyMap();
		this.setSize(300, 500);
		this.getContentPane().setLayout(new BorderLayout());// 设置布局控制器
		this.setTitle("Android Keyboard");// 设置窗口标题
		//
		TextArea textArea = new TextArea(5, 30);
		this.add(textArea, BorderLayout.NORTH);
		//
		JPanel jPanel = new JPanel(new GridLayout(8, 3));
		for (int i = 1; i <= 8; i++) {
			for (int k = 1; k <= 3; k++) {
				jPanel.add(getKeyButton(i * 10 + k));
			}
		}
		this.add(jPanel, BorderLayout.CENTER);
	}
	private JButton getKeyButton(int id) {
		JButton button = new JButton();
		if (keyMap.get(id) == null) {
			return button;
		}
		String buttonText = keyMap.get(id).getKeyName();
		button.setText(buttonText);
		button.setToolTipText(buttonText);
		int keyCode = keyMap.get(id).getKeyCode();
		button.addActionListener(new ButtonListener(keyCode));
		return button;
	}
	/** 按键监听器 */
	private class ButtonListener implements ActionListener {
		private int keyCode;
		public ButtonListener(int keyCode) {
			this.keyCode = keyCode;
		}
		public void actionPerformed(ActionEvent e) {
			System.out.println("keyPressed : " + ((JButton) e.getSource()).getText());
			Utils.execCommand("adb shell input keyevent " + keyCode);
		}
	}
	/** keyMap对 */
	private HashMap<Integer, KeyObject> keyMap;
	private void initKeyMap() {
		keyMap = new HashMap<Integer, KeyObject>();
		//
		keyMap.put(11, new KeyObject("LEFT", KeyObject.KEYCODE_SOFT_LEFT));
		keyMap.put(12, new KeyObject("上", KeyObject.KEYCODE_DPAD_UP));
		keyMap.put(13, new KeyObject("RIGHT", KeyObject.KEYCODE_SOFT_RIGHT));
		//
		keyMap.put(21, new KeyObject("左", KeyObject.KEYCODE_DPAD_LEFT));
		keyMap.put(22, new KeyObject("OK", KeyObject.KEYCODE_DPAD_CENTER));
		keyMap.put(23, new KeyObject("右", KeyObject.KEYCODE_DPAD_RIGHT));
		//
		keyMap.put(31, new KeyObject("CALL", KeyObject.KEYCODE_CALL));
		keyMap.put(32, new KeyObject("下", KeyObject.KEYCODE_DPAD_DOWN));
		keyMap.put(33, new KeyObject("END", KeyObject.KEYCODE_ENDCALL));
		// 123
		keyMap.put(41, new KeyObject("1", KeyObject.KEYCODE_1));
		keyMap.put(42, new KeyObject("2", KeyObject.KEYCODE_2));
		keyMap.put(43, new KeyObject("3", KeyObject.KEYCODE_3));
		// 456
		keyMap.put(51, new KeyObject("4", KeyObject.KEYCODE_4));
		keyMap.put(52, new KeyObject("5", KeyObject.KEYCODE_5));
		keyMap.put(53, new KeyObject("6", KeyObject.KEYCODE_6));
		// 789
		keyMap.put(61, new KeyObject("7", KeyObject.KEYCODE_7));
		keyMap.put(62, new KeyObject("8", KeyObject.KEYCODE_8));
		keyMap.put(63, new KeyObject("9", KeyObject.KEYCODE_9));
		// *0#
		keyMap.put(71, new KeyObject("*", KeyObject.KEYCODE_STAR));
		keyMap.put(72, new KeyObject("0", KeyObject.KEYCODE_0));
		keyMap.put(73, new KeyObject("#", KeyObject.KEYCODE_POUND));
		// menu,home,back
		keyMap.put(81, new KeyObject("MENU", KeyObject.KEYCODE_MENU));
		keyMap.put(82, new KeyObject("HOME", KeyObject.KEYCODE_HOME));
		keyMap.put(83, new KeyObject("BACK", KeyObject.KEYCODE_BACK));
	}
}