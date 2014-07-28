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
}
