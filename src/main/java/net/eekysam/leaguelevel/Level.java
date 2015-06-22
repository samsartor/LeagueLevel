package net.eekysam.leaguelevel;

import java.io.File;
import java.io.IOException;

import net.eekysam.leaguelevel.room.LevelRoom;

public class Level
{
	public final File levelDir;
	public LevelRoom room;

	public Level(File levelDir)
	{
		if (levelDir.isDirectory())
		{
			this.levelDir = levelDir;
		}
		else
		{
			this.levelDir = new File(levelDir.getParent());
		}
	}

	public void load() throws IOException
	{
		this.room = new LevelRoom(this.levelDir.getAbsolutePath() + "\\Scene\\room");
	}
}
