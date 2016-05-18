package br.com.sam9araujo.projetojava.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormataDate {

	private static SimpleDateFormat DATEFORMART = new SimpleDateFormat("yyyyMMdd");
	private static SimpleDateFormat DATETIMEFORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
	private static SimpleDateFormat TIMEMINUTESECONDFORMAT = new SimpleDateFormat("HHmmss");
	
	public static Date getParseDate(Integer pDate, Integer pTime) throws ParseException{
			Date date = null;
			
			if((pDate == 0)){
				return null;
			}
			
			if(pTime == null && pDate != null){
				try {
					date = DATEFORMART.parse(String.valueOf(pDate));
				} catch (ParseException e) {			
					e.printStackTrace();
					throw e;
				}
			}
			
			if(pDate != null && pTime != null){
				try {
					String zero = "";
					if(String.valueOf(pTime).length() < 6){
						int dif = 6 - String.valueOf(pTime).length();
	
						int i = 0;
						while(i < dif){
							zero += "0";
							i++;
						}
					}
					
					date = DATETIMEFORMAT.parse(String.valueOf(pDate)  + zero + String.valueOf(pTime));
				} catch (ParseException e) {			
					e.printStackTrace();
					throw e;
				}
			}
			
			return date;						
	}
	
	public static Integer getParseDate(Date pDate) throws NumberFormatException{
		
		String date = null;
		Integer dateResult = null;
		if(pDate != null){			
			try{
				date = DATEFORMART.format(pDate);
				//System.out.println(date);
				dateResult = Integer.parseInt(date);
			} catch (NumberFormatException e){
				e.printStackTrace();
				throw e;
			}
		}		
		return dateResult;
	}
	
	public static Integer getParseTime(Date pDate) throws NumberFormatException{
		Integer dateResult = null;		
		try{
			if(pDate != null){		
				dateResult = Integer.parseInt(TIMEMINUTESECONDFORMAT.format(pDate));
			}
			return dateResult;
		} catch(NumberFormatException e){
			e.printStackTrace();
			throw e;
		}		
	}
	
	public static void main(String[] args){
		try {
			System.out.println(getParseDate(20150917, 171601));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
