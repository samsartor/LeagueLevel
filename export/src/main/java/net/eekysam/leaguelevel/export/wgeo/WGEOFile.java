package net.eekysam.leaguelevel.export.wgeo;

import java.io.InputStream;
import java.util.Arrays;

import com.google.common.io.LittleEndianDataInputStream;

public class WGEOFile
{
	private WGEOFile()
	{
		
	}
	
	public static byte[] fileMagic = new byte[] { 'W', 'G', 'E', 'O' };
	
	public byte[] magic = new byte[4];
	public short majorVersion;
	public short minorVersion;
	
	public int triCount;
	
	public WGEOMesh[] mesh;
	
	public static WGEOFile read(InputStream read) throws WGEOReadException
	{
		return read(new LittleEndianDataInputStream(read));
	}
	
	public static WGEOFile read(LittleEndianDataInputStream in) throws WGEOReadException
	{
		try
		{
			WGEOFile f = new WGEOFile();
			
			in.readFully(f.magic);
			if (!Arrays.equals(fileMagic, f.magic))
			{
				throw new WGEOReadException("Wrong magic number");
			}
			f.majorVersion = in.readShort();
			f.minorVersion = in.readShort();
			f.mesh = new WGEOMesh[in.readInt()];
			f.triCount = in.readInt();
			
			for (int i = 0; i < f.mesh.length; i++)
			{
				f.mesh[i] = WGEOMesh.read(in);
			}
			
			return f;
		}
		catch (Exception e)
		{
			if (e instanceof WGEOReadException)
			{
				throw (WGEOReadException) e;
			}
			else
			{
				throw new WGEOReadException(e);
			}
		}
	}
}
