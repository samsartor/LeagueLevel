package eekysam.leaguelevel.room.nvr.vert;

import java.io.IOException;

import eekysam.LittleDataRead;
import eekysam.PrintOut;

public class NVRVertexList
{
	public NVRVertexList(LittleDataRead read) throws IOException
	{
		this.dataSize = read.readInt();
		PrintOut.printf("Reading %,d Bytes of Vertex Data...", this.dataSize);
		this.tempList = new byte[this.dataSize];
		read.read(this.tempList);
	}
	
	private byte[] tempList = null;
	public int dataSize;
	
	public void parseData(boolean byte12data)
	{
		if (this.tempList == null)
		{
			return;
		}
		PrintOut.println("Parsing Vertices...");
		if (byte12data)
		{
			this.parseByte12Data();
		}
		else
		{
			this.paseByte364044Data();
		}
		this.tempList = null;
	}
	
	private void parseByte12Data()
	{
		
	}
	
	private void paseByte364044Data()
	{
		
	}
}
