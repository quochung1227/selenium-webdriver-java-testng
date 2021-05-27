package javaTester;

public class Topic_10_Javasplit {
	public static void main(String[] args) {
		String url = "http://the-internet.herokuapp.com";
		String[] newUrl= url.split("//");
		url= newUrl[0]+ "//"+"admin"+":"+"admin"+"@"+newUrl[1];
		System.out.println(url);
	}
}
