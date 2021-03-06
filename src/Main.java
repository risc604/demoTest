import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class Main
{
	static final String HEXES = "0123456789ABCDEF";

	public static int byteToUnsignedInt(byte b)
	{
		return 0x00 << 24 | b & 0xff;
	}

	public static String calculateEndDT(int records, String startDT)
	{
		// --- Hex string to convert dec number
		int[] dtStart = covertDTFormat(startDT);
		System.out.println("dtStart: " + Arrays.toString(dtStart));

		SimpleDateFormat sdf = new SimpleDateFormat("[yy, MM, dd, HH, mm]");
		Date tmpDT = null;
		try
		{
			tmpDT = sdf.parse(Arrays.toString(dtStart));
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("tmpDT: " + tmpDT);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tmpDT);
		System.out.println("calendar: " + calendar.getTime());
		calendar.add(Calendar.MINUTE, records);
		System.out.println("add " + records + " records, " + "calendar: " + calendar.getTime());
		int[] intDT = new int[6];
		intDT[0] = calendar.get(Calendar.YEAR) - 2000;
		intDT[1] = calendar.get(Calendar.MONTH) + 1;
		intDT[2] = calendar.get(Calendar.DAY_OF_MONTH);
		intDT[3] = calendar.get(Calendar.HOUR_OF_DAY);
		intDT[4] = calendar.get(Calendar.MINUTE);
		intDT[5] = calendar.get(Calendar.SECOND);

		String endDTString = String.format("%02X%02X%02X%02X%02X", intDT[0], intDT[1], intDT[2], intDT[3], intDT[4],
		        intDT[5]);
		System.out.println("endDTString: " + endDTString);
		return endDTString;
	}

	public static String calculateEndTime2(int records, String startTime)
	{
		int[] dtTime = covertDTFormat(startTime);
		System.out.println("calculateEndTime2(), dtTime: " + Arrays.toString(dtTime));
		SimpleDateFormat sdf = new SimpleDateFormat("[yy, MM, dd, hh, mm, ss]");
		// Date startDT = new Date(dtTime[0] + 100, dtTime[1] - 1, dtTime[2], dtTime[3],
		// dtTime[4], 0);
		String dtString = sdf.format(Arrays.toString(dtTime));
		System.out.println("dtString: " + dtString);
		// Date startDT = new Date(dtString);
		@SuppressWarnings("deprecation")
		Date startDT = new Date(dtString);
		// Log.i(TAG, "calculateEndTime2(), dtTime: " + Arrays.toString(dtTime) + ",
		// startDT: " + startDT.toString());
		System.out.println(
		        "calculateEndTime2(), dtTime: " + Arrays.toString(dtTime) + ", startDT: " + startDT.toString());

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDT);
		System.out.println("before add calendar: " + calendar.getTime().toString());
		calendar.add(Calendar.MINUTE, records); // minute + 156
		// Log.i(TAG, "calendar: " + calendar.getTime().toString() + ", add records: " +
		// records + ", "
		// + calendar.getTime().toString());
		System.out.println("calendar: " + calendar.getTime().toString() + ", add records: " + records + ", "
		        + calendar.getTime().toString());

		return (covertDateToString(calendar));
	}

	public static String covertDateToString(Calendar calDate)
	{
		int[] newDT = new int[6];
		newDT[0] = calDate.get(Calendar.YEAR) - 2000;
		newDT[1] = calDate.get(Calendar.MONTH) + 1;
		newDT[2] = calDate.get(Calendar.DAY_OF_MONTH);
		newDT[3] = calDate.get(Calendar.HOUR_OF_DAY);
		newDT[4] = calDate.get(Calendar.MINUTE);
		newDT[5] = calDate.get(Calendar.SECOND);

		// Log.i(TAG, "covertDateToString(), newDT: " + Arrays.toString(newDT));
		System.out.println("covertDateToString(), newDT: " + Arrays.toString(newDT));
		// Log.d(TAG, "Year: " + newDT[0] + ", Month: " + newDT[1] + ", Date: " +
		// newDT[2] +
		// ", Hour: " + newDT[3] + ", Minute: " + newDT[4]);

		String lastTime = String.format("%02X%02X%02X%02X%02X", newDT[0], newDT[1], newDT[2], newDT[3], newDT[4]);
		System.out.println("covertDateToString(), lastTime: " + lastTime);
		// Log.i(TAG, "lastTime : " + lastTime);
		// Log.d(TAG, "lastTime : " + lastTime);

		return lastTime;
	}

	public static int[] covertDTFormat(String srcTime)
	{
		byte[] startByte = hexStringToByteArray(srcTime);
		int[] dtNumber = new int[startByte.length];
		for (int i = 0; i < startByte.length; i++)
		{
			dtNumber[i] = byteToUnsignedInt(startByte[i]);
		}

		// Log.d(TAG, "Date Time Number: " + Arrays.toString(dtNumber));
		System.out.println("covertDTFormat(), Date Time Number: " + Arrays.toString(dtNumber));
		return dtNumber;
	}

	public static String getHexToString(byte[] raw)
	{
		// StringBuilder sb= new StringBuilder(responInfo.length);
		// for (byte indx: responInfo)
		// {
		// sb.append(format("%02X", indx));
		// }

		if (raw == null)
		{
			return null;
		}

		final StringBuilder hex = new StringBuilder(2 * raw.length);
		for (final byte b : raw)
		{
			hex.append(HEXES.charAt((b & 0xF0) >> 4)).append(HEXES.charAt((b & 0x0F)));
		}
		return hex.toString();
	}

	public static byte[] hexStringToByteArray(String s)
	{
		int len = s.length();
		byte[] data = new byte[len / 2];

		for (int i = 0; i < len; i += 2)
		{
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}

		return data;
	}

	public static boolean isNumeric(String str)
	{
		Pattern pattern = Pattern.compile("[-]?[0-9]*[\\.|,][0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches())
		{
			return false;
		}
		return true;
	}

	public static void testForRandom()
	{
		for (int number = 0; number != 5; number = (int) (Math.random() * 100))
		{
			System.out.println(number);
		}
	}

	// public static void main(String[] args) throws Throwable
	// {
	// // System.out.println("isNumeric(\"35.42\"): " + isNumeric("35.42"));
	// // System.out.println("isNumeric(\"35,42\"): " + isNumeric("35,42"));
	//
	// // randomTest();
	// // testDateTime();
	// // testHexDateTime();
	// // testDateToLong();
	// // testStringSparet();
	// // testStringToken();
	// // testStringArray();
	// // testStringArray2();
	//
	// // testCalculateEndTime2();
	// // testDateBefore();
	//
	// // testImageFileCheck();
	// // testImgFileType();
	// // testSimpleImageInfo();
	//
	// // testLocaleDTFormat();
	//
	// testForRandom();
	//
	// }

	public static void randomTest()
	{
		RandomDemo rd = new RandomDemo();
		System.out.println("rd: " + rd.toString());
	}

	public static void testCalculateEndTime2()
	{
		// String startTime = "11081F0B0C";
		// String startTime = "77D091E1205";
		String startTime = "11081F111A";
		int record = 40;
		// calculateEndTime2(record - 1, startTime);
		calculateEndDT(record - 1, startTime);
	}

	public static void testDateBefore()
	{
		final String PATTERN = "yyyy/MM/dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		Date date1 = null, date2 = null, date3 = null;
		try
		{
			date1 = sdf.parse("2017/10/12 15:40:00");
			date2 = sdf.parse("2017/10/12 16:00:00");
			date3 = sdf.parse("2017/10/12 16:05:10");
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("date2.before(date1): " + date2.before(date1));
		System.out.printf("date2.before(date3): " + date2.before(date3));
	}

	public static void testDateTime()
	{
		DateFormatDemo dfDemo = new DateFormatDemo();
		dfDemo.testDateFormat();
		System.out.println("dfDemo: " + dfDemo.toString());
	}

	public static void testDateToLong()
	{
		// 2016-01-01 15:00:00 GMT
		SimpleDateFormat sdfor = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z");
		Date date = null;

		try
		{
			date = sdfor.parse("2016-01-01 15:00:00 GMT");
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("date: " + date + ", long time: " + date.getTime());
	}

	public static void testDateFormat2000()
	{
		// 2000-06-01 01:01:00 CST
		final String date2000 = "[0, 6, 1, 1, 1]";
		SimpleDateFormat sdfor = new SimpleDateFormat("[yy, MM, dd, hh, mm]");
		Date date = null;

		try
		{
			date = sdfor.parse(date2000);
			// System.out.println("date2000.substring(1, 2): " + date2000.substring(1, 2));
			if (date2000.substring(1, 2).equalsIgnoreCase("0"))
			{
				// date.setYear(00);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, 2000);
				date = cal.getTime();
				System.out.println("2 date2000.substring(1, 2): " + date2000.substring(1, 2));
			}
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("date: " + sdfor.format(date) + ", long time: " + date.getTime());
	}

	public static void checkDate(String date) throws ParseException
	{
		Calendar calendar = Calendar.getInstance();
		int year = Integer.parseInt(date.substring(0, 2));
		int month = Integer.parseInt(date.substring(2, 4));
		calendar.setLenient(false);
		int yearOfCentury = calendar.get(Calendar.YEAR);
		int century = yearOfCentury - yearOfCentury % 100;
		year = year + century;
		calendar.set(Calendar.YEAR, year);
		calendar.set(Calendar.MONTH, month - 1);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		System.out.println("Date +" + calendar.getTime());
	}

	public static void testDateTimeTosecand()
	{
		// 2016-01-01 15:00:00 GMT
		SimpleDateFormat sdfor = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss z");
		Date date = null, date0 = null;

		try
		{
			date = sdfor.parse("2016-01-01 15:00:00 GMT");
			date0 = sdfor.parse("2018-03-14 10:00:00 CST");
		} catch (ParseException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("date: " + date + ", long time: " + date.getTime());

		Date currentDT = Calendar.getInstance().getTime();
		System.out.println("currentDT: " + sdfor.format(currentDT) + ", " + currentDT.getTime() + " ms" + ", data0: "
		        + sdfor.format(date0) + ", " + date0.getTime() + " ms");
		int diffSecand = (int) (Math.abs(date0.getTime() - currentDT.getTime()) / (1000));
		System.out.println("diffSecand: " + diffSecand);

	}

	public static void testHexDateTime()
	{
		String hexDate = "1105160F23";
		byte[] byteHexDate = hexStringToByteArray(hexDate);
		System.out.println("byteHexDate: " + Arrays.toString(byteHexDate) + ", length: " + byteHexDate.length);
		int[] intDate = new int[byteHexDate.length];
		for (int i = 0; i < byteHexDate.length; i++)
		{
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

	public static void testImageFileCheck() throws Throwable
	{
		String fileName = "Your image file to be read";
		ImageInputStream iis = ImageIO.createImageInputStream(new File(fileName));
		Iterator<ImageReader> readers = ImageIO.getImageReadersByFormatName("jpg");
		@SuppressWarnings("unused")
		boolean canRead = false;
		while (readers.hasNext())
		{
			try
			{
				ImageReader reader = readers.next();
				reader.setInput(iis);
				reader.read(0);
				canRead = true;
				break;
			} catch (IOException exp)
			{
			}
		}
	}

	public static void testImgFileType()
	{
		ImageFileType imgfileType = new ImageFileType();

		String[] fPathArray = new String[]
		{ "D:\\Camera\\20170215_190504.jpg", "D:\\Camera\\20170402_181409.jpg", "", "D:\\mt24hr\\20171019094813.png",
		        "D:\\mt24hr\\20171019100051.png" };

		// String[] fPathArray = new String[]
		// { "/Volumes/SD256GB02/shoe/ladypeep_1494324742.jpg",
		// "/Volumes/SD256GB02/shoe/pigalle_1477584732.jpg", "",
		// "/Volumes/SD256GB02/shoe/20171023113656.png",
		// "/Volumes/SD256GB02/shoe/image024.png" };

		boolean[] fileState = new boolean[]
		{ false, false, false, false };

		for (int i = 0; i < fPathArray.length; i++)
		{
			if (fPathArray[i].equalsIgnoreCase(""))
				continue;

			try
			{
				InputStream is = new FileInputStream(fPathArray[i]);
				// InputStream is =
				// getClass().getClassLoader().getResourceAsStream(fPathArray[i]);
				// Path file = Paths.get(fPathArray[i]);
				// InputStream is = Files.newInputStream(file);
				byte[] fileSize = is.readAllBytes();
				System.out.printf("file size: %04d bytes %n", fileSize.length);
				fileState[0] = imgfileType.isValidJPEG(is, fileSize.length);
				fileState[1] = imgfileType.isValidPNG(is);
				fileState[2] = imgfileType.isJPEG(new File(fPathArray[i]));
				fileState[3] = imgfileType.isPng(new File(fPathArray[i]));
				System.out.println("[" + i + "] file State: " + Arrays.toString(fileState));
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void testLocaleDTFormat()
	{
		LocaleDateTimeFormat ldtFormat = new LocaleDateTimeFormat();
		System.out.println("ldtFormat: " + String.valueOf(ldtFormat));
	}

	// @SuppressWarnings("unused")
	public static void testSimpleImageInfo()
	{
		// SimpleImageInfo sif;
		String[] fPathArray = new String[]
		{ "D:\\Camera\\20170215_190504.jpg", "D:\\Camera\\20170402_181409.jpg", "", "D:\\mt24hr\\20171019094813.png",
		        "D:\\mt24hr\\20171019100051.png" };

		// String[] fPathArray = new String[]
		// { "/Volumes/SD256GB02/shoe/ladypeep_1494324742.jpg",
		// "/Volumes/SD256GB02/shoe/pigalle_1477584732.jpg", "",
		// "/Volumes/SD256GB02/shoe/20171023113656.png",
		// "/Volumes/SD256GB02/shoe/image024.png" };

		// boolean[] fileState = new boolean[]
		// { false, false, false, false };

		for (int i = 0; i < fPathArray.length; i++)
		{
			if (fPathArray[i].equalsIgnoreCase(""))
				continue;

			try
			{
				// InputStream is = new FileInputStream(fPathArray[i]);
				// InputStream is =
				// getClass().getClassLoader().getResourceAsStream(fPathArray[i]);
				// Path file = Paths.get(fPathArray[i]);
				// InputStream is = Files.newInputStream(file);
				// byte[] fileSize = is.readAllBytes();
				@SuppressWarnings("resource")
				byte[] fileSize = new FileInputStream(fPathArray[i]).readAllBytes();
				SimpleImageInfo sif = new SimpleImageInfo(fileSize);
				System.out.printf("file size: %04d bytes, %s %n", fileSize.length, sif.getMimeType());

				// SimpleImageInfo sif2 = new SimpleImageInfo(new File(fPathArray[i]));
				// SimpleImageInfo sif3 = new SimpleImageInfo(is);

				System.out.println("[" + i + "]: " + sif.getMimeType());

				// fileState[0] = sif.(is);
				// fileState[1] = sif(is);
				// fileState[2] = imgfileType.isJPEG(new File(fPathArray[i]));
				// fileState[3] = imgfileType.isPng(new File(fPathArray[i]));
				// System.out.println("[" + i + "] file State: " + Arrays.toString(fileState));
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void testStringArray()
	{
		String src = "1E28001E01001D4B001D37001D28001D1C001D11001D09001D01001C5E001C58001C53001C4E001C48001C44001C3F001C3B001C37001C34001C30001C2D001C2A001C27001C25001C24001C23001C22001C21001C21001C20001C1F00225400";
		char[] chArrays = src.toCharArray();
		System.out.printf("%s:%02d, char size: %02d %n", "src", src.length(), chArrays.length);

		for (int j = 0; j < chArrays.length / 6; j++)
		{
			char[] tmpStr = new char[7];
			for (int k = 0; k < 6; k++)
			{
				tmpStr[k] = chArrays[j * 6 + k];
			}
			System.out.printf("%s %n", String.valueOf(tmpStr));
		}
	}

	public static void testStringArray2()
	{
		String src = "1E28001E01001D4B001D37001D28001D1C001D11001D09001D01001C5E001C58001C53001C4E001C48001C44001C3F001C3B001C37001C34001C30001C2D001C2A001C27001C25001C24001C23001C22001C21001C21001C20001C1F00225400";
		System.out.printf("%s:%02d %n", "src", src.length());

		for (int j = 0; j < src.length() / 6; j++)
		{
			String tmpStr = String.copyValueOf(src.toCharArray(), j * 6, 6);
			System.out.printf("[%02d]:%s %n", j, tmpStr);
		}
	}

	public static void testStringSparet()
	{
		String exp = "(?:0*00)";
		String src = "1E28001E01001D4B001D37001D28001D1C001D11001D09001D01001C5E001C58001C53001C4E001C48001C44001C3F001C3B001C37001C34001C30001C2D001C2A001C27001C25001C24001C23001C22001C21001C21001C20001C1F00225400";

		// List<String> tmpStrList = new ArrayList<String>();
		// List strList = new ArrayList<String>();
		System.out.println("src length: " + src.length());

		for (int i = 0; i < src.length() / 6; i++)
		{
			String[] Strtmp = src.split(exp);
			System.out.printf("Strtmp[%02d]: %s %n", i, Strtmp[i]);
		}
		///// System.out.println(Arrays.toString(tmpStrList.toArray()));
	}

	public static void testStringToken()
	{
		String src = "1E28001E01001D4B001D37001D28001D1C001D11001D09001D01001C5E001C58001C53001C4E001C48001C44001C3F001C3B001C37001C34001C30001C2D001C2A001C27001C25001C24001C23001C22001C21001C21001C20001C1F00225400";
		StringTokenizer tokens = new StringTokenizer(src, "0?00");

		while (tokens.hasMoreTokens())
		{
			System.out.printf("%s:%02d %n", tokens.nextToken(), tokens.countTokens());
		}
	}

	public static void testCurrentDateTime()
	{
		DateFormatDemo dfd = new DateFormatDemo();

		dfd.getCurrentDT();
	}

	public Main()
	{
	}

	public static void main(String[] args) throws Throwable
	{
		// System.out.println("isNumeric(\"35.42\"): " + isNumeric("35.42"));
		// System.out.println("isNumeric(\"35,42\"): " + isNumeric("35,42"));

		// randomTest();
		// testDateTime();
		// testHexDateTime();
		// testDateToLong();
		// testStringSparet();
		// testStringToken();
		// testStringArray();
		// testStringArray2();

		// testCalculateEndTime2();
		// testDateBefore();

		// testImageFileCheck();
		// testImgFileType();
		// testSimpleImageInfo();

		// testLocaleDTFormat();
		testCurrentDateTime();

		// testDateTimeTosecand();
		// testDateFormat2000();
		// checkDate("00060101");

	}

}
