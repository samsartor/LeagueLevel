package eekysam.leaguemaper;

import java.io.File;

import eekysam.leaguelevel.Level;

public class Main
{
	public static void main(String[] args)
	{
		Level level = new Level(new File(args[0]));
		try
		{
			level.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
