package DataBaseControl;

import java.sql.*;

import Model.Student;

public class StudentServer {
	
	private static final String SQL_URL="jdbc:mysql://localhost:3306/EducationSystem?probileSQL=true&useUnicode=true&characterEncoding=GBK";
	private static final String USER="root";
	private static final String PASSWORD="root";
	
	public void InsertStudent(Student student){
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn=DriverManager.getConnection(SQL_URL, USER, PASSWORD);
			Statement stm=conn.createStatement();
			String sql="select count(*) from Students where Id='"+student.GetAccount()+"'";
			ResultSet rs=stm.executeQuery(sql);
			int count=0;
			while(rs.next()){
				count=rs.getInt(1);
			}
			if(count==0){
				StringBuilder sb=new StringBuilder();
				sb.append("insert into Students values('");
				sb.append(student.GetAccount());
				sb.append("','");
				sb.append(student.GetPassword());
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
			}
			else return;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
