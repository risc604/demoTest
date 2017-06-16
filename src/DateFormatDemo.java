
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatDemo {
    public void testDateFormat() {
	SimpleDateFormat sdfor = new SimpleDateFormat("yyyy/MM/dd");
	Date date;
	try {
	    date = sdfor.parse("2015/07/03");
	    System.out.println(date);// Fri Jul 03 00:00:00 CST 2015
	    System.out.println(sdfor.format(date));// 2015/07/03

	    sdfor = new SimpleDateFormat("yyyy/mm/dd");
	    date = sdfor.parse("2015/07/03");
	    System.out.println(date);// Sat Jan 03 00:07:00 CST 2015

	    sdfor = new SimpleDateFormat("yyyy");
	    date = sdfor.parse("2015");
	    System.out.println(date);// Thu Jan 01 00:00:00 CST 2015
	    System.out.println(sdfor.format(date));// 2015

	    sdfor = new SimpleDateFormat("MM月");
	    date = sdfor.parse("12月");
	    System.out.println(date);// Tue Dec 01 00:00:00 CST 1970
	    System.out.println(sdfor.format(date));// 12月

	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
	    System.out.println(sdf.parse("1985/15/48"));
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	// sdf.setLenient(false);
	// System.out.println(sdf.parse("1985/15/48"));//執行會跳exception
    }

}// end of class
