package net.eekysam.leaguelevel.export.nvr;

import java.io.IOException;

import net.eekysam.leaguelevel.export.ReadUtils;

import com.google.common.io.LittleEndianDataInputStream;

public class NVRTexture
{
	private NVRTexture()
	{
		
	}
	
	public float[] color = new float[4];
	public String name;
	public byte[] extra = new byte[68];
	
	static NVRTexture read(LittleEndianDataInputStream in) throws IOException
	{
		NVRTexture t = new NVRTexture();
		for (int i = 0; i < t.color.length; i++)
		{
			t.color[i] = in.readFloat();
		}
		t.name = ReadUtils.readString(in, 256);
		in.readFully(t.extra);
		return t;
	}
}
