package eekysam.leaguelevel.room;

import java.io.File;
import java.io.IOException;

import eekysam.leaguelevel.room.nvr.NVRFile;

public class LevelRoom
{
	public NVRFile roomModels;
	
	public LevelRoom(String roomFile) throws IOException
	{
		this.roomModels = NVRFile.readNVR(new File(roomFile + ".nvr"));
	}
}
