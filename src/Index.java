import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import Methods.StudentOperate;
import Model.Student;

/*
 * 主程序，登录教务系统并且获取数据存入mysql
 * 后期需要修改成可视化界面
 */
public class Index {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Student student=new Student("12136226","a11111");
		StudentOperate stuo=new StudentOperate(student,false);
		CloseableHttpClient httpclient=HttpClients.createDefault();
		String status=stuo.Login(httpclient);
		
	} 
}
