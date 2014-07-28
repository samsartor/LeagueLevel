package eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import eekysam.LittleDataRead;

public class NVRBoundingBox
{
	public float[] min = new float[3];
	public float[] max = new float[3];
	public int[] unk1 = new int[4];
	
	private NVRBoundingBox()
	{
		
	}
	
	public NVRBoundingBox(LittleDataRead read) throws IOException
	{
		this();
		this.read(read);
	}
	
	private void read(LittleDataRead read) throws IOException
	{
		for (int i = 0; i < 3; i++)
		{
			this.min[i] = read.readFloat();
		}
		for (int i = 0; i < 3; i++)
		{
			this.max[i] = read.readFloat();
		}
		for (int i = 0; i < 4; i++)
		{
			this.unk1[i] = read.readInt();
		}
	}
}
