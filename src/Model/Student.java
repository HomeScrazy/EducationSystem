package Model;


import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;

import Methods.HttpClientMethods;
import Methods.StringCheckMethods;


/*
 * 学生类，包含学生的相关信息，学号，登录密码，课表，个人信息等；
 */
public class Student {
	//是否是内网
	private boolean insideNet;
	//记录ID
	public static int ID;
	private HttpClientMethods hcm=new HttpClientMethods();
	//关键字标签信息
	//获取姓名的标签
	public static final String LABEL_GETNAME_START="xhxm\">";
	public static final String LABEL_GETNAME_END="同学";
	//获取班级的标签
	public static final String LABEL_GETCLASSNAME_START="<TD><span id=\"lbl_xzb\">";
	public static final String LABEL_GETCLASSNAME_END="</span></TD>";
	//登录页面网址
	private String url_login;
	//登录后个人主页
	private String url_index;
	//个人信息网址
	private String url_personal_information;
	//课表网址
	private String url_courseList;
	
	//帐号
	private String account;
	//登录密码
	private String password;
	//个人姓名
	private String name;
	//所在班级
	private String className;
	//登录所需post表单信息
	private ArrayList<NameValuePair> nvps=new ArrayList<NameValuePair>();
	
	public Student(String account,String password,boolean in){
		/*
		 * 构造学生基本信息
		 * 学生帐号，密码，登录教务系统的地址，个人主页,以及登录表单信息
		 * 以上都是在获得帐号和密码后就能自动填充的数据
		 */
		ID++;
		this.account=account;
		this.password=password;
		insideNet=in?true:false;
		String host=in?"http://210.33.24.53/":"http://jw.usx.edu.cn/";
		url_login=host+"default_ysdx.aspx";
		url_index=host+"xs_main.aspx?xh="+this.account;
		SetPostFormData(account,password);
	}
	
	
	public void SetPostFormData(String account,String password){
		/*
		 * 填充登录是需要提交的表单数据
		 */
		nvps.add(new BasicNameValuePair("__VIEWSTATE","dDwxOTA5NTY0MDU2Ozs+2DycebZXlj2577fF4f3jIepqjdY="));
		nvps.add(new BasicNameValuePair("__VIEWSTATEGENERATOR","701681CC"));
		nvps.add(new BasicNameValuePair("TextBox1",account));
		nvps.add(new BasicNameValuePair("TextBox2",password));
		nvps.add(new BasicNameValuePair("RadioButtonList1","%D1%A7%C9%"));
		nvps.add(new BasicNameValuePair("Button1","+%B5%C7%C2%BC++"));
	}
	
	
	//信息设置
	public void SetClassName(String name){
		/*
		 * 设置班级名称
		 */
		this.className=name;
	}
	public void SetName(String name){
		/*
		 * 设置学生姓名
		 * 在姓名获取之后既可以自动填充一些子界面的地址
		 * 在将中文加入url之前先进行格式转换
		 */
		this.name=name;
		String _name=StringCheckMethods.ToUtf8String(name);
		String host=insideNet?"http://210.33.24.53/":"http://jw.usx.edu.cn/";
		url_personal_information=host+"xsgrxx.aspx?xh="+account+"&xm="+_name+"&gnmkdm=N121501";
	}

	
	public ArrayList<NameValuePair> GetPostFormData(){
		return nvps;
	}
	
	
	public String GetIndexUrl(){
		/*
		 * 获取登录后首页地址
		 * 如果是内网host为:http://210.33.24.53/;
		 * 如果是内网host为:http://jw.usx.edu.cn/
		 */
		return url_index;
	}
	
	public String GetPersonalUrl(){
		/*
		 * 获取个人信息地址
		 * 如果是内网host为:http://210.33.24.53/;
		 * 如果是内网host为:http://jw.usx.edu.cn/
		 */
		return url_personal_information;
	}
	
	//获取网址信息
/*
	public String GetLoginUrl(boolean in){
		
		 * 获取登录地址
		 * 如果是内网host为:http://210.33.24.53/;
		 * 如果是内网host为:http://jw.usx.edu.cn/
		 
		String host=in?"http://210.33.24.53/":"http://jw.usx.edu.cn/";
		return host+url_login;
	}
	
	public String GetIndexUrl(boolean in){
		
		 * 获取登录后首页地址
		 * 如果是内网host为:http://210.33.24.53/;
		 * 如果是内网host为:http://jw.usx.edu.cn/
		 
		String host=in?"http://210.33.24.53/":"http://jw.usx.edu.cn/";
		return host+url_index;
	}
	
	public String GetPersonalUrl(boolean in){
		
		 * 获取个人信息地址
		 * 如果是内网host为:http://210.33.24.53/;
		 * 如果是内网host为:http://jw.usx.edu.cn/
		 
		String host=in?"http://210.33.24.53/":"http://jw.usx.edu.cn/";
		return host+url_personal_information;
	}
*/
	public String GetName(){
		return name;
	}
	public String GetClassName(){
		return className;
	}

	
	public HttpResponse Login(HttpClient httpclient){
		/*
		 * 向登录界面post表单信息，返回服务器返回的响应内容
		 */
		return HttpClientMethods.PostFormToUrl(url_login, nvps, httpclient);
		
	}
	
}
