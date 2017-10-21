import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

public class ImageFileInfo
{
	public ImageFileInfo()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * Check if the image is a PNG. The first eight bytes of a PNG file always
	 * contain the following (decimal) values: 137 80 78 71 13 10 26 10 / Hex: 89 50
	 * 4e 47 0d 0a 1a 0a
	 */
	public boolean isValidPNG(InputStream is)
	{
		try
		{
			byte[] b = new byte[8];
			is.read(b, 0, 8);
			if (Arrays.equals(b, new BigInteger("89504e470d0a1a0a", 16).toByteArray()))
			{
				return true;
			}
		} catch (Exception e)
		{
			// Ignore
			return false;
		}
		return false;
	}

	/**
	 * Check if the image is a JPEG. JPEG image files begin with FF D8 and end with
	 * FF D9
	 */
	public boolean isValidJPEG(InputStream is, int size)
	{
		try
		{
			byte[] b = new byte[2];
			is.read(b, 0, 2);
			// check first 2 bytes:
			if ((b[0] & 0xff) != 0xff || (b[1] & 0xff) != 0xd8)
			{
				return false;
			}
			// check last 2 bytes:
			is.skip(size - 4);
			is.read(b, 0, 2);
			if ((b[0] & 0xff) != 0xff || (b[1] & 0xff) != 0xd9)
			{
				return false;
			}
		} catch (Exception e)
		{
			// Ignore
			return false;
		}
		return true;
	}

	/**
	 * Check if the image is a valid GIF. GIF files start with GIF and 87a or 89a.
	 * http://www.onicos.com/staff/iz/formats/gif.html
	 */
	public boolean isValidGIF(InputStream is)
	{
		try
		{
			byte[] b = new byte[6];
			is.read(b, 0, 6);
			// check 1st 3 bytes
			if (b[0] != 'G' || b[1] != 'I' || b[2] != 'F')
			{
				return false;
			}
			if (b[3] != '8' || !(b[4] == '7' || b[4] == '9') || b[5] != 'a')
			{
				return false;
			}
		} catch (Exception e)
		{
			// Ignore
			return false;
		}
		return true;
	}

}
