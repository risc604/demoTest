
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DateFormatDemo
{
	public void testDateFormat()
	{
		SimpleDateFormat sdfor = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		try
		{
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
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// sdf.setLenient(false);
		// System.out.println(sdf.parse("1985/15/48"));//執行會跳exception
	}

	public String getCurrentDT()
	{
		// Calendar cal = Calendar.getInstance();
		// Date currentDate = cal.getTime();
		/// boolean is24Hours = android.text.format.DateFormat.is24HourFormat(this);

		/// Date currentDate = Calendar.getInstance().getTime();
		/// String dtString = DateFormat.getDateInstance(DateFormat.MEDIUM,
		/// Locale.getDefault()).format(currentDate);

		LocalDate ld = LocalDate.now();
		DateTimeFormatter dtfd = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		String dString = ld.format(dtfd);
		System.out.println(" getCurrentDT(), dString: " + dString);

		LocalTime lt = LocalTime.now();

		DateTimeFormatter dtft = DateTimeFormatter.ofPattern(" HH:mm:ss");
		String tString = lt.format(dtft);
		System.out.println(" getCurrentDT(), tString: " + tString);

		dtft = DateTimeFormatter.ofPattern(" hh:mm:ss a");
		tString = lt.format(dtft);
		System.out.println(" getCurrentDT(), tString: " + tString);

		System.out.println(" getCurrentDT(), " + dString + tString);

		LocalDateTime ldt = LocalDateTime.now();

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		String dtString = ldt.format(dtf);
		System.out.println(" getCurrentDT(), dtString: " + dtString);

		// if (is24Hours)
		// {
		// //dtString = Utils.localeDateString(currentDate, 1);
		// dtString += String.format(" %tT", currentDate);
		// }
		// else
		// {
		// dtString += String.format(" %tr", currentDate);
		// }
		/// dtString += Utils.Time24or12h(is24Hours, 2, currentDate);

		// Log.w(TAG, " getCurrentDT(), is24Hours: " + is24Hours + ", DT string: " +
		// dtString);
		return dtString;
	}

}// end of class
