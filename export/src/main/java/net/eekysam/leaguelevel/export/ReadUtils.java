package net.eekysam.leaguelevel.export;

import java.io.IOException;
import com.google.common.io.LittleEndianDataInputStream;

public class ReadUtils
{
	public static String readString(LittleEndianDataInputStream in, int length) throws IOException
	{
		byte[] string = new byte[length];
		in.readFully(string);
		String out = new String(string);
		return out.substring(0, out.indexOf(0x00));
	}
}
