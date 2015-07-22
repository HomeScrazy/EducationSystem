package DataBaseControl;

import java.sql.*;

import Model.Student;

public class StudentServer {
	public void InsertStudent(Student student){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			String url="jdbc:mysql://localhost:3306/EducationSystem?probileSQL=true&useUnicode=true&characterEncoding=GBK";
			String user="root";
			String pwd="root";
			Connection conn=DriverManager.getConnection(url, user, pwd);
			Statement stm=conn.createStatement();
			StringBuilder sb=new StringBuilder();
			sb.append("insert into Students values('");
			sb.append(student.GetAccount());
			sb.append("','");
			sb.append(student.GetName());
			sb.append("','");
			sb.append(student.GetSex());
			sb.append("','");
			sb.append(student.GetClassName());
			sb.append("','");
			sb.append(student.GetLocation());
			sb.append("')");
			System.out.println(sb.toString());
			stm.execute(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
