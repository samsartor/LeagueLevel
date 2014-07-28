package eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import eekysam.LittleDataRead;
import eekysam.PrintOut;

public class NVRFaceArray
{
	public int faceCount;
	public int d3dType;
	public short[] indicies;
	
	private NVRFaceArray()
	{
		
	}
	
	public NVRFaceArray(LittleDataRead read) throws IOException
	{
		this();
		this.read(read);
	}
	
	private void read(LittleDataRead read) throws IOException
	{
		int dataSize = read.readInt();
		this.faceCount = dataSize / 6;
		this.d3dType = read.readInt();
		PrintOut.printf("(Faces: %d, Type: %d)", this.faceCount, this.d3dType);
		if (this.d3dType != 101)
		{
			PrintOut.printf("\tWARNING: Unexpected Index Type!");
		}
		if (dataSize != this.faceCount * 6)
		{
			PrintOut.printf("\tWARNING: Index Data has Invalid Size!");
		}
		this.indicies = new short[this.faceCount * 3];
		for (int i = 0; i < this.faceCount; i++)
		{
			this.indicies[i * 3 + 0] = read.readShort();
			this.indicies[i * 3 + 1] = read.readShort();
			this.indicies[i * 3 + 2] = read.readShort();
		}
	}
}
