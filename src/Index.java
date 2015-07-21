import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import Methods.HttpClientMethods;
import Methods.StringCheckMethods;
import Model.Student;
import MySqlCheck.StudentServer;

/*
 * 主程序，登录教务系统并且获取数据存入mysql
 * 后期需要修改成可视化界面
 */
public class Index {
	private static StudentServer sts=new StudentServer();

	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		Student student=new Student("12135135","setv.05174",false);
		CloseableHttpClient httpclient=HttpClients.createDefault();
		student.Login(httpclient);
		HttpResponse response=HttpClientMethods.GetResponseFromUrl(student.GetIndexUrl(), httpclient);
		String indexHtml=null;
		try {
			indexHtml=EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(indexHtml==null){
			System.out.println("错误");
			return;
		}
		student.SetName(StringCheckMethods.GetChars(indexHtml, Student.LABEL_GETNAME_START, Student.LABEL_GETNAME_END));
		response=HttpClientMethods.GetResponseFromUrl(student.GetPersonalUrl(), student.GetPersonalUrl(), httpclient);
		String personHtml=null;
		try {
			personHtml=EntityUtils.toString(response.getEntity());
		} catch (ParseException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		student.SetClassName(StringCheckMethods.GetChars(personHtml, Student.LABEL_GETCLASSNAME_START,Student.LABEL_GETCLASSNAME_END));
		System.out.println(student.GetName());
		System.out.println(student.GetClassName());
		//sts.InsertStudent(student);
	}

}
