package libs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import com.qust.zq.android.Utils;

public class Main {
	public static void main(String[] args) {
		// Utils.printSubmit("63038", "添加桌面时钟小部件");
		//httpUrlConnection("http://172.16.78.131:8083/");
		//Utils.printSubmit("64527", "添加联系人界面\"确定\"按钮点不到");
	}
	private static void httpUrlConnection(String pathUrl) {
		String ENCODING_UTF_8 = "UTF-8";
		try {
			// 建立连接
			URL url = new URL(pathUrl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
			// 设置连接属性
			httpConn.setDoOutput(true);// 使用 URL 连接进行输出
			httpConn.setDoInput(true);// 使用 URL 连接进行输入
			httpConn.setUseCaches(true);// 忽略缓存
			httpConn.setRequestMethod("POST");// 设置URL请求方法
			httpConn.setRequestProperty("username", "zhangqi");
			httpConn.setRequestProperty("password", "4815912");
			// 获得响应状态
			int responseCode = httpConn.getResponseCode();
			if (HttpURLConnection.HTTP_OK == responseCode) {// 连接成功
				// 当正确响应时处理数据
				StringBuffer sb = new StringBuffer();
				String readLine;
				// 处理响应流，必须与服务器响应流输出的编码一致
				BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), ENCODING_UTF_8));
				while ((readLine = responseReader.readLine()) != null) {
					sb.append(readLine).append("\n");
				}
				responseReader.close();
				System.out.println(sb.toString());
				// tv.setText(sb.toString());
			} else {
				System.out.println("responseCode : " + responseCode);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex);
		}
	}
	private static void printLib(String lib) {
		System.out.println("################ " + lib + " ##################");
		System.out.println("include $(CLEAR_VARS)");
		System.out.println("LOCAL_MODULE := " + lib);
		System.out.println("LOCAL_SRC_FILES := lib/" + lib + ".so");
		System.out.println("LOCAL_MODULE_SUFFIX := .so");
		System.out.println("LOCAL_MODULE_CLASS := SHARED_LIBRARIES");
		System.out.println("LOCAL_MODULE_TAGS := optional");
		System.out.println("LOCAL_MODULE_PATH := $(PRODUCT_OUT)/system/lib");
		System.out.println("include $(BUILD_PREBUILT)");
		System.out.println();
	}
}
