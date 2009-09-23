package utils.string;

import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class StringFormatter {
	
	
	public static String formatDate(String string) {
		long l = 0l;

		try {
			l = Long.parseLong(string);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		// change to GMT time:
		formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

		String newDateString = formatter.format(new Date(l));
		// System.out.println("Date changed format :" + newDateString);
		return newDateString;
	}

	public static String formatSize(String string) {
		double l = 0l;
		DecimalFormat df = new DecimalFormat("#.##");
		
		try {
			l = Double.parseDouble(string);
		} catch (NumberFormatException e) {
			// e.printStackTrace();
		}
		
		String size = "" + l;

		if (l <= 1024) {
			size = df.format(l) + " B";
			return size;
		}

		l /= 1024;

		if (l <= 1024) {
			size = df.format(l) + " KB";
			return size;
		}

		l /= 1024;

		if (l <= 1024){
			size = df.format(l) + " MB";
			return size;
		}

		
		l /= 1024;

		if (l <= 1024){ 
			 size = df.format(l) + " GB"; 
			 return size; 
		}
		
		l /= 1024;
		size = df.format(l) + " TB";
		return size;
	}
}
