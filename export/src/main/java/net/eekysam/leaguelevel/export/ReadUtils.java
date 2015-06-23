package net.eekysam.leaguelevel.export;

import java.io.DataInput;
import java.io.IOException;

public class ReadUtils
{
	public static String readString(DataInput in, int length) throws IOException
	{
		byte[] string = new byte[length];
		in.readFully(string);
		String out = new String(string);
		return out.substring(0, out.indexOf(0x00));
	}
	
	public static void readFloats(DataInput in, float[] floats) throws IOException
	{
		for (int i = 0; i < floats.length; i++)
		{
			floats[i] = in.readFloat();
		}
	}
	
	public static void readInts(DataInput in, int[] ints) throws IOException
	{
		for (int i = 0; i < ints.length; i++)
		{
			ints[i] = in.readInt();
		}
	}
	
	public static void readShorts(DataInput in, short[] shorts) throws IOException
	{
		for (int i = 0; i < shorts.length; i++)
		{
			shorts[i] = in.readShort();
		}
	}
}
