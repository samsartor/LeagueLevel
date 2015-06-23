package net.eekysam.leaguelevel.export.wgeo;

import java.io.IOException;

import net.eekysam.leaguelevel.export.ReadUtils;

import com.google.common.io.LittleEndianDataInputStream;

public class WGEOVert
{
	private WGEOVert()
	{
	}
	
	public float[] pos = new float[3];
	public float[] uv = new float[3];
	
	public static WGEOVert read(LittleEndianDataInputStream in) throws IOException
	{
		WGEOVert v = new WGEOVert();
		
		ReadUtils.readFloats(in, v.pos);
		ReadUtils.readFloats(in, v.uv);
		
		return v;
	}
}
