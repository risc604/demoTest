import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.util.Arrays;

//https://jaimonmathew.wordpress.com/2011/01/29/simpleimageinfo/
//http://blog.jaimon.co.uk/simpleimageinfo/SimpleImageInfo.java.html
//http://www.dongcoder.com/detail-102108.html

public class ImageFileType
{
	public ImageFileType()
	{
		// TODO Auto-generated constructor stub
	}

	public Boolean isJPEG(File filename) throws Exception
	{
		DataInputStream ins = new DataInputStream(new BufferedInputStream(new FileInputStream(filename)));
		try
		{
			System.out.println("isJPEG()...");
			if (ins.readInt() == 0xffd8ffe0)
			{
				return true;
			} else
			{
				return false;
			}
		} finally
		{
			ins.close();
		}
	}

	// private static boolean isPng(File filename) throws Exception {
	public boolean isPng(File filename) throws Exception
	{
		final int MAGIC[] = new int[]
		{ 0x89, 0x50, 0x4e, 0x47, 0x0d, 0x0a, 0x1a, 0x0a };

		FileInputStream ins = new FileInputStream(filename);
		try
		{
			for (int i = 0; i < MAGIC.length; ++i)
			{
				if (ins.read() != MAGIC[i])
				{
					return false;
				}
			}
			return true;
		} finally
		{
			ins.close();
		}
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
			System.out.println("isValidPNG()...");
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
			System.out.println("isValidJPEG()...");
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
