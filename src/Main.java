import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
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

    public static void main(String[] args) throws ParseException {
	// System.out.println("isNumeric(\"35.42\"): " + isNumeric("35.42"));
	// System.out.println("isNumeric(\"35,42\"): " + isNumeric("35,42"));

	// randomTest();
	testDateTime();
    }

}
