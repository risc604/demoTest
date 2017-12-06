import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class LocaleDateTimeFormat
{
	public LocaleDateTimeFormat()
	{
		getLocalDTFormat();

	}

	public void getLocalDTFormat()
	{
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		System.out.println("Date Obj: " + date.toString());
		System.out.printf("Date: %tD %n", date);

		System.out.println("Dateformat");
		for (int i = 0; i < 4; i++)
		{
			DateFormat dtFormat = DateFormat.getDateTimeInstance(i, i, Locale.TAIWAN);
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

}
