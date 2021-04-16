package com.github.holalee.network.http;

import com.github.holalee.common.Constants;
import com.github.holalee.common.IoUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Title: HttpUrlUtil
 * @Description: HttpURLConnection网络工具类
 * @version V1.0
 */
public class HttpUrlUtil {
	
	/**
     * 解析url
     * @param url
     */
    public static void parseUrl(String url) {
        BufferedReader reader = null;
        try {
            URL u = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) u.openConnection();
            // 设置连接超时时间
            connection.setConnectTimeout(1000 * 60);
            // 设置读超时时间
            connection.setReadTimeout(1000 * 60);
            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）
            connection.connect();

            // 获得响应状态信息
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("request fail...");
                return;
            }

            // 获得头信息
            Map<String, List<String>> headers = connection.getHeaderFields();
            Iterator<Map.Entry<String, List<String>>> iterator = headers.entrySet()
                    .iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, List<String>> entry = iterator.next();
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }

            System.out.println("response body：");
            // 内容是文本，直接以缓冲字符流读取
            reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), Constants.UTF8));
            String data;
            while ((data = reader.readLine()) != null) {
                System.out.println(data);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IoUtil.close(reader);
        }
    }
}
