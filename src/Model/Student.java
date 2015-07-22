package Model;
/*
 * 学生类
 */
public class Student {
	/*
	 * 修改后的学生类，进行了优化，简化了学生类的功能
	 */
	
	//学号
	private String account;
	//教务系统密码
	private String password;
	//姓名
	private String name;
	//性别 
	private String sex;
	//班级
	private String className;
	//所在地区
	private String location;
	
	public Student(String account,String password){
		/*
		 * 构造方法，所需参数
		 * 1 学号
		 * 2 教务系统密码
		 */
		this.account=account;
		this.password=password;
	}
	
	public void SetName(String name){
		/*
		 * 设置学生姓名
		 */
		this.name=name;
	}
	
	public void SetSex(String sex){
		/*
		 * 设置性别
		 */
		this.sex=sex;
	}
	
	public void SetLocation(String location){
		/*
		 * 设置所在地
		 */
		this.location=location;
	}
	
	public void SetClassName(String className){
		/*
		 * 设置学生的所在班级
		 */
		this.className=className;
	}
	
	public String GetAccount(){
		/*
		 * 获取学生学号
		 */
		return account;
	}
	
	public String GetPassword(){
		/*
		 * 获取管理系统的密码
		 */
		return password;
	}
	
	public String GetName(){
		/*
		 * 获取姓名
		 */
		return name;
	}
	
	public String GetSex(){
		/*
		 * 获取性别
		 */
		return sex;
	}
	
	public String GetLocation(){
		/*
		 * 获取所在地
		 */
		return location;
	}
	
	public String GetClassName(){
		/*
		 * 获取班级名称
		 */
		return className;
	}
	
}
