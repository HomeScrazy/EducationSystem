/*
 * 用于字符串处理的方法类
 */
package Methods;

public abstract class StringCheckMethods {
	public static String ToUtf8String(String s){
		/*
		 * 将一个中文字符串转换成utf8格式用于在url中加入中文
		 */
		StringBuilder sb=new StringBuilder();
		for(int i=0;i<s.length();i++){
			char c=s.charAt(i);
			if(c>=0&&c<=255){
				/*
				 * 如果是普通字符，直接添加进去
				 */
				sb.append(c);
			}else{
				/*
				 * 如果是类似于中文这种特殊字符，则转换后添加
				 */
				byte[] b;
				try{
					b=String.valueOf(c).getBytes("utf-8");
				}catch(Exception ex){
					System.out.println(ex);
					b=new byte[0];
				}
				for(int j=0;j<b.length;j++){
					int k=b[j];
					if(k<0) k+=256;
					sb.append("%"+Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
	
	public static String GetChars(String source,String start,String end){
		/*
		 * 指定字符串A和字符串B
		 * 获取在A和B之间的字符串
		 */
		int pointStart=source.indexOf(start);
		String str=source.substring(pointStart);
		pointStart=str.indexOf(start);
		int pointEnd=str.indexOf(end);
		return str.substring(pointStart+start.length(), pointEnd);
	}
}
