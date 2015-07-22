package Methods;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import DataBaseControl.StudentServer;
import Model.*;

public class StudentOperate {
	/*
	 * 学生操作类。
	 * 完成学生进行的各类操作 如登录等；
	 */
	
	/*一些共用且不会变动的信息
	 * 关键字标签信息
	 */
	//数据库操作类
	private StudentServer sts=new StudentServer();
	
	//获取姓名的标签
	public static final String LABEL_GETNAME_START="xhxm\">";
	public static final String LABEL_GETNAME_END="同学";
	
	//获取班级的标签
	public static final String LABEL_GETCLASSNAME_START="<TD><span id=\"lbl_xzb\">";
	public static final String LABEL_GETCLASSNAME_END="</span></TD>";
	
	//获取登录状态信息的标签
	public static final String LABEL_GETSTATUS_START="alert('";
	public static final String LABEL_GETSTATUS_END="')";
	public static final String LABEL_SUCCEED_START="<script>";
	public static final String LABEL_SUCCEED_END="('";
	//获取性别的标签
	public static final String LABEL_GETSEX_START="<option selected=\"selected\" value=\"";
	public static final String LABEL_GETSEX_END="\">";
	//获取所在地标签
	public static final String LABEL_GETLOCATION_START="lydq\" type=\"text\" value=\"";
	public static final String LABEL_GETLOCATION_END="\" id=\"lydq\"";
	//登录的网址（免验证码网址）
	public static final String LOGIN_URL="default_ysdx.aspx";
	//xsgrxx.aspx?xh=12135135&xm=%D5%D4%C8%F3%B3%C9&gnmkdm=N121501
	public static final String INDEX_URL_HEAD="xsgrxx.aspx?xh=";
	public static final String INDEX_URL_BODY="&xm=";
	public static final String INDEX_URL_TAIL="&gnmkdm=N121501";
	
	
	//操作的教务系统的host由是否处于内网环境下决定
	private String host;
	
	//登录所需post表单信息
	private ArrayList<NameValuePair> nvps=new ArrayList<NameValuePair>();
	
	//操作的学生对象
	private Student student;
	
	
	public StudentOperate(Student student,boolean insidenet){
		/*
		 * 在构造方法中先确定由 student 和 insidnet 参数所能确定的一些值
		 */
		this.student=student;
		SetPostFormData(student.GetAccount(),student.GetPassword());
		host=insidenet?"http://210.33.24.53/":"http://jw.usx.edu.cn/";
		
	}
	
	public String Login(HttpClient httpclient){
		/*
		 * 学生登录，获取正确的cookies，提供获取信息的权限
		 * 如果登录成功则自动获取姓名以及班级
		 */
		HttpResponse response=null;
		response=HttpClientMethods.PostFormToUrl(host+LOGIN_URL, nvps, httpclient);
		String htmlString=null;
		try {
			htmlString=EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 捕获关键字段判断登录是否成功
		 */
		if(htmlString==null) return "网络出现故障";
		if(htmlString.contains(LABEL_SUCCEED_START)&&StringCheckMethods.GetChars(htmlString, LABEL_SUCCEED_START,LABEL_SUCCEED_END).equals("window.open")){
			/*
			 * 登录成功之后，获取学生姓名
			 */
			String index_url=host+"xs_main.aspx?xh="+student.GetAccount();
			response=HttpClientMethods.GetResponseFromUrl(index_url, httpclient);
			String indexString=null;
			try {
				indexString =EntityUtils.toString(response.getEntity());
			} catch (ParseException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(indexString==null) return "网络异常";
			String name=StringCheckMethods.GetChars(indexString, LABEL_GETNAME_START, LABEL_GETNAME_END);
			student.SetName(name);
			System.out.println(student.GetName());
			SetPersonalInformation(httpclient);
			System.out.println(student.GetClassName());
			System.out.println(student.GetLocation());
			System.out.println(student.GetSex());
			sts.InsertStudent(student);
			return "登录成功！";
		}
		String status=StringCheckMethods.GetChars(htmlString, LABEL_GETSTATUS_START, LABEL_GETSTATUS_END);
		if(status.equals("密码错误！！")) return "密码错误";
		if(status.equals("用户名不存在！！")) return "该用户不存在";
		return "登录成功";
		
	}
	
	public void SetPersonalInformation(HttpClient httpclient){
		/*
		 * 获取个人信息里的性别，班级等信息
		 */
		//构造个人信息网址
		String url=INDEX_URL_HEAD+student.GetAccount()+INDEX_URL_BODY+StringCheckMethods.ToUtf8String(student.GetName())+INDEX_URL_TAIL;
		HttpResponse response=HttpClientMethods.GetResponseFromUrl(host+url, host+url, httpclient);
		String personString=null;
		try {
			personString=EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sex=StringCheckMethods.GetChars(personString, LABEL_GETSEX_START, LABEL_GETSEX_END);
		student.SetSex(sex);
		String className=StringCheckMethods.GetChars(personString, LABEL_GETCLASSNAME_START, LABEL_GETCLASSNAME_END);
		student.SetClassName(className);
		String location=StringCheckMethods.GetChars(personString, LABEL_GETLOCATION_START, LABEL_GETLOCATION_END);
		student.SetLocation(location);
		
	}
	
	public void GetCoureList(){
		/*
		 * 获取课程信息
		 */
	}
	
	private void SetPostFormData(String account,String password){
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
}
