package Demo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Set;

import com.hexudong.utils.entity.FileUtil;
import com.hexudong.utils.entity.RandomUtil;
import com.hexudong.utils.entity.StringUtil;

public class Demo {

	
	public static String randomChineseString(int num) {
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<num;i++) {
			String randomChineseString = randomChineseString();
			sb.append(randomChineseString);
		}
		return sb.toString();
	}
	
	public static String randomChineseString() {
		String str = null;
		int highPos, lowPos;
		Random random = new Random();
		highPos = (176 + Math.abs(random.nextInt(39)));// 区码，0xA0打头，从第16区开始，即0xB0=11*16=176,16~55一级汉字，56~87二级汉字
		random = new Random();
		lowPos = 161 + Math.abs(random.nextInt(94));// 位码，0xA0打头，范围第1~94列
		byte[] bArr = new byte[2];
		bArr[0] = (new Integer(highPos)).byteValue();
		bArr[1] = (new Integer(lowPos)).byteValue();
		try {
			str = new String(bArr, "GB2312"); // 区位码组合成汉字
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static Date parse(String theDateStr,String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		try {
			return simpleDateFormat.parse(theDateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public static char getRandomChar() {
		Random random = new Random();
		char c = (char)('a'+random.nextInt(26));
		return c;
	}
	public static char getRandomNumber() {
		Random random = new Random();
		char c = (char)('0'+random.nextInt(10));
		return c;
	}
	public static String getRandomCharAndNumber(int num) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for(int i=0;i<num;i++) {
			int randomNum = random.nextInt(36);
			if(randomNum>10) {
				char c = getRandomChar();
				sb.append(c);
			}else {
				char c = getRandomNumber();
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public static int random(int min,int max) {
		Random random = new Random();
		int nextInt = random.nextInt(max-min+1);
		if(nextInt<min) {
			return random(min,max);
		}
		return nextInt;
	}
	
	public static String format(Date theDate,String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(theDate);
	}
	
	public static int compare(Date date1,Date date2) {
		long time1 = date1.getTime();
		long time2 = date2.getTime();
		if(time1==time2) {
			return 0;
		}
		if(time1>time2) {
			return 1;
		}
		return -1;
	}
	
	public static Date getRandomDate(Date date1,Date date2) {
		Long randomLong = Math.abs(date1.getTime()-date2.getTime());
		long random = (long) (randomLong*Math.random());
		long newDateLong = compare(date1, date2)==1?date2.getTime()+random:date1.getTime()+random;
		return new Date(newDateLong);
	}
	
	
	public static void main(String[] args) throws IOException {
		Date date1= parse("2020-01-01 00:00:00", "yyyy-MM-dd HH:mm:ss");
		Date date2 = new Date();
		Date randomDate = getRandomDate(date1, date2);
		System.out.println(format(randomDate, "随机时间为:"+"yyyy-MM-dd HH:mm:ss"));
		
		//定义一个字符串
		String str = "aaa";
		
		
		boolean kong = StringUtil.isNull(str);
		System.out.println("字符串是否为空:"+kong);
		
		boolean email = StringUtil.isEmail(str);
		System.out.println("是否为邮箱地址:"+email);
		
		
		boolean phoneNum = StringUtil.isPhoneNum(str);
		System.out.println("是否为手机号:"+phoneNum);
		
		//
		List<String> list = new ArrayList<String>();
		list.add(str);//把数据添加到集合中
		
		int size = list.size();
		if (size!=0) {
			System.out.println("字符串不为空");
		}else {
			System.out.println("字符串为空");
		}
		
		String filename = "D:\\1.txt";
		
		List<String> rad = FileUtil.readByLines(filename);
		
		System.out.println("将文件添加到集合成功");
		
			
		for(int i=0;i<2;i++) {
			System.out.println("1-120之间随机年龄为:"+random(1,120));
		}
		
		System.out.println("随机验证码↓");
		for (int i = 0; i < 4; i++) {
			System.out.print(getRandomChar());
		}
		System.out.println();
		System.out.println("随机500字↓");
		for (int i = 0; i < 500; i++) {
			System.out.print(randomChineseString());
		}
		
	}
	
	
}
