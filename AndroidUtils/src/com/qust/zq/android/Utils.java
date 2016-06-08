package com.qust.zq.android;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import test.AXMLPrinter;

public class Utils {
	/** 根据IMEI计算解锁码 */
	public static String calcUnlockSIMMEKey(String imeiStr) {
		String password = "";
		int imeiStrLength = 0;
		int[] number = new int[15];
		int PASSWORD_LENGTH = 8;
		// String imeiStr = "354648020000521";
		if (imeiStr != null) {
			imeiStrLength = imeiStr.length();
			for (int i = 0; i < imeiStrLength; i++) {
				number[i] = imeiStr.charAt(i) - 48;
			}
			for (int j = 0; j < PASSWORD_LENGTH; j++) {
				int temp = 0;
				for (int i = 0; i < PASSWORD_LENGTH; i++) {
					temp += number[i + j];
				}
				password += (temp % 10) + "";
			}
		}
		return password;
	}
	/** 命令行执行工具 */
	private static Runtime mRuntime;
	public static String execCommand(String cmdStr) {
		if (mRuntime == null) {
			mRuntime = Runtime.getRuntime();
		}
		String result = "";
		try {
			Process process = mRuntime.exec(cmdStr);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				// System.out.println(line);
				result += line + "\n";
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	public interface FileParseListener {
		public String onParseFile(String line);
	}
	/** 文件读取工具 */
	public static String readFile(String path, FileParseListener fileParseListener) {
		BufferedReader bufferedReader = null;
		String result = "";
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
			String line = "";
			while ((line = bufferedReader.readLine()) != null) {
				String r = fileParseListener.onParseFile(line);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
	/** 解析Workspace */
	public static String parseWorkspace(String path) {
		readFile(path, new FileParseListener() {
			int index = 0;
			@Override
			public String onParseFile(String line) {
				String result = "";
				String beginStr = "{";
				String endStr = "}";
				int start = line.indexOf(beginStr);
				int end = line.indexOf(endStr);
				if (start != -1 && end != -1) {
					String component = line.substring(start + beginStr.length(), end);
					String[] componentName = component.split("/");
					result += component + "\n";
					String packageName = componentName[0];
					String className = componentName[1];
					System.out.println("\t<app\n\t\tlauncher:topPackageName=\"" + packageName + "\"\n\t\tlauncher:topClassName=\"" + className + "\"\n\t\tlauncher:topOrder=\"" + index + "\" />");
					index++;
				}
				return result;
			}
		});
		return "";
	}
	/** 解析Favorites */
	public static String parseFavorites(String path) {
		readFile(path, new FileParseListener() {
			@Override
			public String onParseFile(String line) {
				String result = "";
				String beginStr = "component=";
				String endStr = ";end";
				int start = line.indexOf(beginStr);
				int end = line.indexOf(endStr);
				if (start != -1 && end != -1) {
					String component = line.substring(start + beginStr.length(), end);
					result += component + "\n";
					System.out.println(result);
				}
				return result;
			}
		});
		return "";
	}
	/** 获取Android版本号 */
	public static String getAndroidVersion() {
		String verno = Utils.execCommand("adb shell getprop ro.build.version.release").trim();
		if ("4.4.2".equalsIgnoreCase(verno)) {
			System.out.println("KK");
			return "KK";
		} else {
		}
		return "";
	}
	/** 更新Settings数据库 */
	public static void updateSettingsDatabase(String table, String key, String value) {
		/**
		 * 
		 * ????????????
		 * 
		 */
		String cmd = "adb shell sqlite3 /data/data/com.android.providers.settings/databases/settings.db \"update system set value='1800000' where name='screen_off_timeout';\"";
		String result = Utils.execCommand(cmd);
		System.out.println(result);
	}
	/** 解析AndroidManifest.xml文件 */
	public static void parseAndroidManifest() {
		AXMLPrinter.main(new String[] { new File("").getAbsoluteFile() + "/raw/AndroidManifest.xml" });
	}
	/**
	 * 生成提交记录
	 * 
	 * @param bugId
	 *            Redmine Bug号
	 * @param title
	 *            Redmine Bug标题
	 */
	public static void printSubmit(String bugId, String title) {
		System.out.println("Redmine" + bugId + " : " + title);
		System.out.println("Submitter:");
		System.out.println("    zhangqi");
		System.out.println("Checker:");
		System.out.println("    jiangcunbin");
		System.out.println("Files:");
		// 解析上传文件
		Utils.readFile("/home/zq/Desktop/files.txt", new Utils.FileParseListener() {
			@Override
			public String onParseFile(String line) {
				if (line.indexOf("diff") != -1) {
					// modify
					String reString = line.split(" ")[0];
					System.out.println("    M:" + reString);
				} else if (line.indexOf("new file with mode") != -1) {
					// add
					String reString = line.split(" ")[0];
					System.out.println("    A:" + reString);
				}
				return null;
			}
		});
		System.out.println("Date:");
		Date date = new Date();
		System.out.println("    " + (date.getYear() + 1900) + "/" + (date.getMonth() + 1) + "/" + date.getDate());
	}
	/** 解析libs文件 */
	public static void parseLibs() {
		Utils.readFile("/home/zq/Desktop/libs.txt", new Utils.FileParseListener() {
			@Override
			public String onParseFile(String line) {
				int indexBegin = line.indexOf("lib");
				int indexEnd = line.indexOf(".so");
				if (indexBegin != -1 && indexEnd != -1) {
					String subString = line.substring(indexBegin, indexEnd);
					// System.out.println("  PRODUCT_PACKAGES += " + subString);
					// printLib(subString);
					return subString;
				}
				return "";
			}
		});
	}
	public static void main(String[] args) {
		// Utils.updateSettingsDatabase("", "", "");
		Utils.parseWorkspace("/home/zq/Desktop/workspace.txt");
	}
}
