package net.eekysam.leaguelevel.export.nvr;

import java.io.IOException;

import net.eekysam.leaguelevel.export.ReadUtils;

import com.google.common.io.LittleEndianDataInputStream;

public class NVRMaterial
{
	private NVRMaterial()
	{
		
	}
	
	public String name;
	public int unk1;
	public int unk2;
	public int unk3;
	public NVRTexture[] textures = new NVRTexture[8];
	
	static NVRMaterial read(LittleEndianDataInputStream in) throws IOException
	{
		NVRMaterial m = new NVRMaterial();
		m.name = ReadUtils.readString(in, 256);
		m.unk1 = in.readInt();
		m.unk2 = in.readInt();
		m.unk3 = in.readInt();
		for (int i = 0; i < m.textures.length; i++)
		{
			m.textures[i] = NVRTexture.read(in);
		}
		return m;
	}
}
