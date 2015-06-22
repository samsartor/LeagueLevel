package net.eekysam.leaguelevel.room.nvr.vert;

import java.util.Arrays;

public class NVRVertexComplex extends NVRVertex
{
	public float[] position = new float[3];
	public float[] normal = new float[3];
	public float[] uv = new float[2];
	public byte[] extra;
	
	public NVRVertexComplex(byte[] data, int pos)
	{
		for (int i = 0; i < 3; i++)
		{
			this.position[i] = this.getFloat(data, pos);
			pos += 4;
		}
		for (int i = 0; i < 3; i++)
		{
			this.normal[i] = this.getFloat(data, pos);
			pos += 4;
		}
		for (int i = 0; i < 2; i++)
		{
			this.uv[i] = this.getFloat(data, pos);
			pos += 4;
		}
		int extrasize = 0;
		if (this.hasExtra(data, pos + 8))
		{
			extrasize = 12;
		}
		else if (this.hasExtra(data, pos + 4))
		{
			extrasize = 8;
		}
		else if (this.hasExtra(data, pos))
		{
			extrasize = 4;
		}
		this.extra = Arrays.copyOfRange(data, pos, pos + extrasize);
	}
	
	public boolean hasExtra(byte[] data, int pos)
	{
		if (pos + 3 >= data.length)
		{
			return false;
		}
		return data[pos + 3] == (byte) 0xFF;
	}

	@Override
	public int getReadSize()
	{
		return 32 + this.extra.length;
	}

	@Override
	public float[] getPosition()
	{
		return this.position;
	}
}
