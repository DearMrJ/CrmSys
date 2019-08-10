package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class test {

	public static  String getOrderId() {
		SimpleDateFormat sdf=new  SimpleDateFormat("yyMMddHHmmss");
		String newDate = sdf.format(new Date());
		String result = "";
		Random random = new Random();
		for(int i=0;i<3;i++) {
			result += random.nextInt(10);
		}
		return newDate+result;
	}
	public static void main(String[] args) {
		for(int i=0;i<100;i++) {
			String id= getOrderId();
			System.out.println(id);
		}
	}
}
