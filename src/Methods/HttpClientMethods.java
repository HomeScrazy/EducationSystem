package Methods;
/*
 * 简单封装httpclient里的相关方法
 * 
 */
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
public class HttpClientMethods {
	public static HttpResponse GetResponseFromUrl(String url,HttpClient httpclient){
		/*
		 * 简单封装下httpclient里的get方法
		 */
		
		HttpGet httpget=new HttpGet(url);
		HttpResponse response=null;
		try{
			response=httpclient.execute(httpget);
		}catch(IOException e){
			e.printStackTrace();
		}
		return response;
	}
	public static HttpResponse GetResponseFromUrl(String url,String referer,HttpClient httpclient){
		/*
		 * 同上方法
		 * 加上一个防盗链参数，鉴于某些网址含有防盗链信息
		 */
		HttpGet httpget=new HttpGet(url);
		//添加防盗链的header
		httpget.setHeader("Referer", referer);
		HttpResponse response=null;
		try{
			response=httpclient.execute(httpget);
		}catch(IOException e){
			e.printStackTrace();
		}
		return response;
	}
	
	public static HttpResponse PostFormToUrl(String url,ArrayList<NameValuePair> nvps,HttpClient httpclient){
		HttpPost httppost=new HttpPost(url);
		HttpResponse response=null;
		try{
			httppost.setEntity(new UrlEncodedFormEntity(nvps));
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return null;
		}
		try {
			response=httpclient.execute(httppost);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
}
