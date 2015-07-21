package MySqlCheck;

import Model.Student;
import java.sql.*;

public class StudentServer {
	public void InsertStudent(Student student){
		try{
			String url="jdbc:mysql://localhost:3306/StudentInformation?probileSQL=true&useUnicode=true&characterEncoding=GBK";
			String user="root";
			String pwd="root";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn=DriverManager.getConnection(url, user, pwd);
			Statement stm=conn.createStatement();
			StringBuilder sb=new StringBuilder();
			sb.append("insert into Student values(");
			sb.append(student.ID);
			sb.append(",'");
			sb.append(student.GetName());
			sb.append("','");
			sb.append(student.GetClassName());
			sb.append("')");
			System.out.println(sb.toString());
			stm.execute(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
