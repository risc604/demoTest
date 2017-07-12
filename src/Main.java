import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    static final String HEXES = "0123456789ABCDEF";

    public Main() {
    }

    public static boolean isNumeric(String str) {
	Pattern pattern = Pattern.compile("[-]?[0-9]*[\\.|,][0-9]*");
	Matcher isNum = pattern.matcher(str);
	if (!isNum.matches()) {
	    return false;
	}
	return true;
    }

    public static void randomTest() {
	RandomDemo rd = new RandomDemo();
	System.out.println("rd: " + rd.toString());
    }

    public static void testDateTime() {
	DateFormatDemo dfDemo = new DateFormatDemo();
	dfDemo.testDateFormat();
	System.out.println("dfDemo: " + dfDemo.toString());
    }

    public static byte[] hexStringToByteArray(String s) {
	int len = s.length();
	byte[] data = new byte[len / 2];

	for (int i = 0; i < len; i += 2) {
	    data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
	}

	return data;
    }

    public static String getHexToString(byte[] raw) {
	// StringBuilder sb= new StringBuilder(responInfo.length);
	// for (byte indx: responInfo)
	// {
	// sb.append(format("%02X", indx));
	// }

	if (raw == null) {
	    return null;
	}

	final StringBuilder hex = new StringBuilder(2 * raw.length);
	for (final byte b : raw) {
	    hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
	}
	return hex.toString();
    }

    public static int byteToUnsignedInt(byte b) {
	return 0x00 << 24 | b & 0xff;
    }

    public static void testHexDateTime() {
	String hexDate = "1105160F23";
	byte[] byteHexDate = hexStringToByteArray(hexDate);
	System.out.println("byteHexDate: " + Arrays.toString(byteHexDate) + ", length: " + byteHexDate.length);
	int[] intDate = new int[byteHexDate.length];
	for (int i = 0; i < byteHexDate.length; i++) {
	    intDate[i] = byteToUnsignedInt(byteHexDate[i]);
	}
	LocalDateTime dt = LocalDateTime.of(intDate[0], intDate[1], intDate[2], intDate[3], intDate[4], 0, 0);
	// System.out.println("dt: " + dt.toString() + ", " + dt.getYear() + "/" +
	// dt.getMonthValue() + "/"
	// + dt.getDayOfMonth() + " " + +dt.getHour() + ":" + dt.getMinute() + ":" +
	// dt.getSecond() );
	System.out.printf("dt: %s, %02d/%02d/%02d  %02d:%02d:%02d, %n", dt.toString(), dt.getYear(), dt.getMonthValue(),
		dt.getDayOfMonth(), dt.getHour(), dt.getMinute(), dt.getSecond());
    }

    public static void testDateToLong() {
	// 2016-01-01 15:00:00 GMT
	SimpleDateFormat sdfor = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z");
	Date date = null;
	try {
	    date = sdfor.parse("2016-01-01 15:00:00 GMT");
	} catch (ParseException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	System.out.println("date: " + date + ", long time: " + date.getTime());

    }

    public static void testStringSparet() {
	String exp = "[0-9a-zA-Z&&[00{1,1}]]";
	String src = "1E28001E01001D4B001D37001D28001D1C001D11001D09001D01001C5E001C58001C53001C4E001C48001C44001C3F001C3B001C37001C34001C30001C2D001C2A001C27001C25001C24001C23001C22001C21001C21001C20001C1F00225400";

	// List<String> tmpStrList = new ArrayList<String>();
	List strList = new ArrayList<String>();

	for (int i = 0; i < src.length(); i++) {
	    // System.out.println(strList.get(i));
	    String[] tmpStr = src.split(exp);
	    System.out.println(tmpStr[i]);
	    strList.add(tmpStr[i]);
	}

	/// for (int i = 0; i < src.length(); i++) {
	/// String[] tmpNewStr = src.split("00");
	/// System.out.println(Arrays.toString(tmpNewStr));
	/// tmpStrList.add(tmpNewStr.toString() + "00");
	/// }
	///
	for (int i = 0; i < strList.size(); i++) {
	    System.out.println(strList.get(i));
	}
	///// System.out.println(Arrays.toString(tmpStrList.toArray()));

    }

    public static void main(String[] args) throws ParseException {
	// System.out.println("isNumeric(\"35.42\"): " + isNumeric("35.42"));
	// System.out.println("isNumeric(\"35,42\"): " + isNumeric("35,42"));

	// randomTest();
	// testDateTime();
	// testHexDateTime();
	// testDateToLong();
	testStringSparet();
    }

}
