package net.eekysam.leaguelevel.export.wgeo;

import java.io.IOException;

import net.eekysam.leaguelevel.export.ReadUtils;

import com.google.common.io.LittleEndianDataInputStream;

public class WGEOMesh
{
	private WGEOMesh()
	{
	}
	
	public String texture;
	public int unk1;
	public String name;
	public float[] sphere = new float[4];
	public float[] min = new float[3];
	public float[] max = new float[3];
	public WGEOVert[] verts;
	public short[] inds;
	
	public static WGEOMesh read(LittleEndianDataInputStream in) throws IOException
	{
		WGEOMesh m = new WGEOMesh();
		
		m.texture = ReadUtils.readString(in, 256);
		m.unk1 = in.readInt();
		m.name = ReadUtils.readString(in, 64);
		ReadUtils.readFloats(in, m.sphere);
		ReadUtils.readFloats(in, m.min);
		ReadUtils.readFloats(in, m.max);
		m.verts = new WGEOVert[in.readInt()];
		m.inds = new short[in.readInt()];
		for (int i = 0; i < m.verts.length; i++)
		{
			m.verts[i] = WGEOVert.read(in);
		}
		ReadUtils.readShorts(in, m.inds);
		
		return m;
	}
}
