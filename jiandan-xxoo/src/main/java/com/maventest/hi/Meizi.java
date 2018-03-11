package com.maventest.hi;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
public class Meizi {
	/*
	 * 根据传入的煎蛋网网址，提取出妹子的图片
	 * 定义对象之后，可调用三个run方法，
	 * ·若不指定抓取数目，则默认抓取10页
	 * ·若指定抓取数目，则抓取指定页数
	 * ·若指定起始页和终止页，则按此抓取页抓取
	 * 抓取后的图片放在"E:/meizi"文件夹下，其中每一页在一个文件夹下，文件夹用其在煎蛋网的名字命名
	 *	 例如第111页为E:/meizi/111,文件名用其在本页的编号命名如2.jpg
	 *	
	 */
	public static String url = "http://jandan.net/ooxx/page-1";
	public void run() {
		/*
		 * 使用默认URL，抓取从当前URL开始的10页
		 */
		int now = Integer.parseInt(url.substring(url.indexOf('-')+1));
		run(now,now+10);
	}
	public void run( int nums) {
		/*
		 * 指定抓取页数，从当前URL开始抓取nums页
		 */
		int now = Integer.parseInt(url.substring(url.indexOf('-')+1));
		run(now,now+nums);
	}
	public void run(int m , int n) {
		int index = url.indexOf('-');
		m = m < 1 ? 1:m;
		n = n > 1500 ? 1500:n;
		for(int i = m; i < n; i++) {		
			url = url.substring(0, index)+"-"+i;
			File file = new File("D:\\meizi\\"+i);
			if(!file.exists()) {
				file.mkdirs();
			}else{
				continue;
			}
			HttpEntity entity = getEntity(url);
			ArrayList<String> list = getURLList(entity);
			for(int j = 0; j < list.size(); j++) {
				
				HttpEntity entity1 = getEntity(list.get(j));
				writeToFile(file.getPath()+"\\"+j+".jpg",entity1);
			}
			System.out.println("success"+i);
		}
	}
	private void writeToFile(String dir, HttpEntity entity) {
		/*
		 * 把得到的图片的entity写入文件
		 */
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buf = new byte[1024];
		int len = 0;
		
		try {
			InputStream inputStream = entity.getContent();
			while((len = inputStream.read(buf))!=-1) {
				out.write(buf,0,len);
			}
			inputStream.close();
			File file = new File(dir);
			FileOutputStream fo = new FileOutputStream(file);
			fo.write(out.toByteArray());
			fo.close();			
			
		} catch (IOException e3) {
			e3.printStackTrace();
		}
	}
	
	private ArrayList<String> getURLList(HttpEntity entity) {
		/*
		 * 从entity 提取图片的URL;
		 */
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader(entity.getContent()));
			String tmp = "";
			ArrayList<String> result = new ArrayList<String>();
			String line = "";
			while((line = br.readLine())!=null) {
				tmp += line;
			}
			br.close();
			entity.getContent().close();
			Pattern p = Pattern.compile("src=\"//w(.+?)\"");
			//Pattern p = Pattern.compile("src=\"(.+?)\"");
			Matcher m = p.matcher(tmp);
			boolean isFind = m.find();
			while(isFind) {
				result.add("http://w"+m.group(1));
				isFind = m.find();
			}
			return result;
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
		
 	private HttpEntity getEntity(String url) {
 		/*
 		 * 从URL得到一个HttpEntity
 		 */
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("User-Agent","Mozilla/5.0 "+
		"(Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleW"+
				"ebKit/537.36 (KHTML, like Gecko) Chrome/59."+
		"0.3071.115 Mobile Safari/537.36");
		try {
			CloseableHttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			return entity;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	 
		return null;
	}
}