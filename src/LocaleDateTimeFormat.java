import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class LocaleDateTimeFormat
{
	public LocaleDateTimeFormat()
	{
		getLocalDTFormat();

	}

	public void getLocalDTFormat()
	{
		Calendar cal = Calendar.getInstance();
		// Date date = cal.getTime();
		System.out.println();

		DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate ld = LocalDate.parse("2017-03-13", DATEFORMATTER);
		LocalDateTime ldt = LocalDateTime.of(ld, LocalDateTime.now().toLocalTime());
		System.out.println(ldt);

		System.out.println(DATEFORMATTER.ofLocalizedDateTime(null));

		// Locale locale = new Locale("en", "US"); // 區域為美國
		// DateFormat LocalFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);
		// System.out.println("US DT: " + Local);
	}

}
