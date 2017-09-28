package com.shuixiaofei.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.StringUtils;

public class Test {
			
	
	
	public static void main(String[] args) {
		String  sd = "0011111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111111";
		
		
		String snew = "";
		for(int i=0;i<sd.length();i++){
			
			if(i==7){
				snew = snew +1;
			}else if(i==8){
				snew = snew +1;

			}else{
				snew = snew +0;
			}
			
		}
		
		
		System.out.println(snew);
		

		char[] charArray = snew.toCharArray();
		List<Integer> codeList = new ArrayList<Integer>();

		for (int i = 0; i < charArray.length; i++) {
			if (String.valueOf(charArray[i]).equals("1")) {
				codeList.add(i);
		     }
	     }
		
		String ssd = StringUtils.join(codeList, ",");
		
		System.out.println("=============================================================");
		System.out.println(ssd);
		
	}
}
