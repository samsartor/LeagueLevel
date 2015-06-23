package net.eekysam.leaguelevel.export.nvr;

import java.io.InputStream;
import java.util.Arrays;

import com.google.common.io.LittleEndianDataInputStream;

public class NVRFile
{
	private NVRFile()
	{
		
	}
	
	public static byte[] fileMagic = new byte[] { 0x4E, 0x56, 0x52, 0x00 };
	
	public byte[] magic = new byte[4];
	public short majorVersion;
	public short minorVersion;
	
	public NVRMaterial[] materials;
	public NVRVert[][] verts;
	
	public static NVRFile read(InputStream read) throws NVRReadException
	{
		return read(new LittleEndianDataInputStream(read));
	}
	
	public static NVRFile read(LittleEndianDataInputStream in) throws NVRReadException
	{
		try
		{
			NVRFile f = new NVRFile();
			
			in.readFully(f.magic);
			if (!Arrays.equals(fileMagic, f.magic))
			{
				throw new NVRReadException("Wrong magic number");
			}
			f.majorVersion = in.readShort();
			f.minorVersion = in.readShort();
			int materialCount = in.readInt();
			int vertexArrayCount = in.readInt();
			int indexArrayCount = in.readInt();
			int modelCount = in.readInt();
			int AABBCount = in.readInt();
			
			f.materials = new NVRMaterial[materialCount];
			for (int i = 0; i < f.materials.length; i++)
			{
				f.materials[i] = NVRMaterial.read(in);
			}
			
			f.verts = new NVRVert[materialCount][];
			for (int i = 0; i < f.verts.length; i++)
			{
				f.verts[i] = NVRVert.readArray(in);
			}
			
			return f;
		}
		catch (Exception e)
		{
			if (e instanceof NVRReadException)
			{
				throw (NVRReadException) e;
			}
			else
			{
				throw new NVRReadException(e);
			}
		}
	}
}
