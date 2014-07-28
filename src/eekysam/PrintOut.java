package eekysam;

import java.util.ArrayList;

public class PrintOut
{
	private static ArrayList<String> prefixs = new ArrayList<String>();
	private static String prefix = "";

	private static void remakePrefix()
	{
		prefix = "";
		for (int i = 0; i < prefixs.size(); i++)
		{
			prefix += prefixs.get(i);
		}
	}

	public static void addPrefix(String pre)
	{
		prefixs.add(pre);
		remakePrefix();
	}

	public static void removePrefix()
	{
		if (prefixs.isEmpty())
		{
			return;
		}
		prefixs.remove(prefixs.size() - 1);
		remakePrefix();
	}

	public static void printf(String format, Object... args)
	{
		System.out.println(prefix + String.format(format, args));
	}

	public static void println(String string)
	{
		System.out.println(prefix + string);
	}

	public static String fromChars(char[] chars)
	{
		String s = new String();
		for (int i = 0; i < chars.length; i++)
		{
			char b = chars[i];
			if (b != 0x00)
			{
				s += b;
			}
		}
		return s.trim();
	}

	public static String fromChars(byte[] bytes)
	{
		String s = new String();
		for (int i = 0; i < bytes.length; i++)
		{
			byte b = bytes[i];
			if (b != 0x00)
			{
				s += (char) b;
			}
		}
		return s.trim();
	}

	public static String fromCharsFull(byte[] bytes)
	{
		String s = new String();
		for (int i = 0; i < bytes.length; i++)
		{
			byte b = bytes[i];
			s += (char) b;
		}
		return s;
	}
	
	public static String fromCharsFull(char[] chars)
	{
		String s = new String();
		for (int i = 0; i < chars.length; i++)
		{
			char b = chars[i];
			s += (char) b;
		}
		return s;
	}
	
	public static void tabIn()
	{
		PrintOut.println("{");
		PrintOut.addPrefix("\t");
	}
	
	public static void tabOut()
	{
		PrintOut.removePrefix();
		PrintOut.println("}");
	}
}
