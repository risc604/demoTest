import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocaleDateTimeFormat
{
	public LocaleDateTimeFormat()
	{
		getLocalDTFormat();
		checkDTString();
		// compareString();
	}

	public void getLocalDTFormat()
	{
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		System.out.println("Date Obj: " + date.toString());
		System.out.printf("Date: %tD %n", date);
		System.out.println("Dateformat: ");
		for (int i = 0; i < 4; i++)
		{
			// DateFormat dtFormat = DateFormat.getDateTimeInstance(i, i, Locale.TAIWAN);
			DateFormat dtFormat = DateFormat.getDateTimeInstance(i, i, Locale.GERMAN);
			// DateFormat dtFormat = DateFormat.getTimeInstance(i, i, Locale.GERMAN);
			String showDT = dtFormat.format(date);
			System.out.println("[" + i + "]: " + showDT);
		}

		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2017-03-13", DATEFORMATTER);
		LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
		System.out.println(ldt);

		// System.out.println(DATEFORMATTER.ofLocalizedDateTime(null));

		// Locale locale = new Locale("en", "US"); // �ϰ쬰����
		// DateFormat LocalFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
		// System.out.println("US DT: " + Local);
	}

	// @SuppressWarnings("null")
	public void checkDTString()
	{
		Calendar cc = Calendar.getInstance(Locale.TAIWAN);
		Date[] dateArray = new Date[2];
		dateArray[0] = Calendar.getInstance(Locale.GERMANY).getTime();
		cc.setTime(dateArray[0]);
		int mode = 1;
		if (mode == 1)
		{
			cc.add(Calendar.MINUTE, 30);
		} else
		{
			cc.add(Calendar.HOUR, 30);
		}
		dateArray[1] = cc.getTime();

		System.out.println(Arrays.toString(dateArray));
		System.out.println("dateArray[0] compare dateArray[1]: " + dateArray[1].compareTo(dateArray[0]));

		DateFormat[] dfArray = new DateFormat[2];
		dfArray[0] = DateFormat.getDateTimeInstance(3, 3, Locale.GERMANY);
		dfArray[1] = DateFormat.getDateTimeInstance(3, 3, Locale.GERMANY);

		String[] dtString = new String[2];
		dtString[0] = dfArray[0].format(dateArray[0]);
		dtString[1] = dfArray[1].format(dateArray[1]);
		System.out.println(Arrays.toString(dtString));
		System.out.println("dtString[0] compareTo dtString[1]: " + dtString[0].compareTo(dtString[1]));
		System.out.println("dtString[0] indexOf dtString[1]: " + dtString[0].indexOf(dtString[1]));
		System.out.println("dtString[0] regionMatch dtString[1]: " + dtString[0].regionMatches(0, dtString[1], 0, 9));

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		String[] sdfString = new String[2];
		sdfString[0] = sdf.format(dateArray[0]);
		sdfString[1] = sdf.format(dateArray[1]);
		System.out.println("sdfString: " + Arrays.toString(sdfString));

		if (sdfString[0].regionMatches(0, sdfString[1], 0, 10))
		{
			dfArray[1] = DateFormat.getTimeInstance(3, Locale.GERMANY);
			dtString[1] = dfArray[1].format(dateArray[1]);
		}
		System.out.println("dtString[0] date length:" + dfArray[0]);
		System.out.println("@@ dtString[1]: " + dtString[1]);
		System.out.println(dtString[0] + " ~ " + dtString[1]);
	}

	public void compareString()
	{
		// String searchMe = "Green Eggs and Ham";
		// String findMe = "Eggs";

		String searchMe = "12.01.18, 10:02";
		String findMe = "12.01.18, 11:02";
		int searchMeLength = searchMe.length();
		int findMeLength = findMe.length();
		boolean foundIt = false;
		for (int i = 0; i <= (searchMeLength - findMeLength); i++)
		{
			if (searchMe.regionMatches(i, findMe, 0, findMeLength))
			{
				foundIt = true;
				System.out.println(searchMe.substring(i, i + findMeLength));
				break;
			}
		}
		if (!foundIt)
			System.out.println("No match found.");
	}

}
