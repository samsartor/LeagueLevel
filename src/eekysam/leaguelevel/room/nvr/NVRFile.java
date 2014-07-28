package eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import eekysam.LittleDataRead;
import eekysam.PrintOut;

public class NVRFile
{
	private NVRFile()
	{
		
	}
	
	public NVRFile(LittleDataRead read) throws IOException
	{
		this();
		this.read(read);
	}
	
	public static char[] fileMagic = new char[] {'N', 'V', 'R', 0x00};
	
	public char[] magic = new char[4];
	public short majorVersion;
	public short minorVersion;
	public int materialCount;
	public int vertexListCount;
	public int indexListCount;
	public int modelCount;
	public int AABBCount;
	
	public NVRMaterial[] materials;
	
	private void read(LittleDataRead read) throws IOException
	{
		read.read(this.magic);
		if (!fileMagic.equals(this.magic))
		{
			PrintOut.printf("WARNING: Magic number is incorrect! (%s)", String.copyValueOf(this.magic));
		}
		this.majorVersion = read.readShort();
		this.minorVersion = read.readShort();
		PrintOut.printf("Version: %d.%d", this.majorVersion, this.minorVersion);
		this.materialCount = read.readInt();
		this.vertexListCount = read.readInt();
		this.indexListCount = read.readInt();
		this.modelCount = read.readInt();
		this.AABBCount = read.readInt();
		
		PrintOut.printf("Reading %d Materials...", this.materialCount);
		PrintOut.println("{");
		PrintOut.addPrefix("\t");
		this.materials = new NVRMaterial[this.modelCount];
		for (int i = 0; i < this.materialCount; i++)
		{
			this.materials[i] = new NVRMaterial(read);
		}
		PrintOut.removePrefix();
		PrintOut.println("}");
	}
}
