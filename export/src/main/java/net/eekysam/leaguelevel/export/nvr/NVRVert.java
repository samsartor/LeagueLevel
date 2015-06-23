package net.eekysam.leaguelevel.export.nvr;

import java.io.IOException;
import java.util.Arrays;

import net.eekysam.leaguelevel.export.ReadUtils;

import com.google.common.io.LittleEndianDataInputStream;

public class NVRVert
{
	private NVRVert()
	{
		
	}
	
	public float[] pos = new float[3];
	public float[] norm = new float[3];
	public float[] uv = new float[2];
	public int[] extra = new int[0];
	
	static NVRVert read(LittleEndianDataInputStream in) throws IOException
	{
		NVRVert v = new NVRVert();
		ReadUtils.readFloats(in, v.pos);
		ReadUtils.readFloats(in, v.norm);
		ReadUtils.readFloats(in, v.uv);
		while (true)
		{
			in.mark(5);
			int val = in.readInt();
			if (val >>> 24 == 0xFF)
			{
				v.extra = Arrays.copyOf(v.extra, v.extra.length + 1);
				v.extra[v.extra.length - 1] = val & 0x00FFFFFF;
			}
			else
			{
				in.reset();
				break;
			}
		}
		return v;
	}
	
	static NVRVert[] readArray(LittleEndianDataInputStream in) throws IOException
	{
		NVRVert[] array = new NVRVert[in.readInt()];
		for (int i = 0; i < array.length; i++)
		{
			array[i] = NVRVert.read(in);
		}
		return array;
	}
}
