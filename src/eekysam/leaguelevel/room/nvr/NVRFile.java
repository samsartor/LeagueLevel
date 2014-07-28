package eekysam.leaguelevel.room.nvr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

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
	
	public static byte[] fileMagic = new byte[] {0x4E, 0x56, 0x52, 0x00};
	
	public byte[] magic = new byte[4];
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
		if (Arrays.equals(fileMagic, this.magic))
		{
			PrintOut.printf("WARNING: Magic number is incorrect! (%s)", PrintOut.fromCharsFull(this.magic));
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
	
	public static NVRFile readNVR(File file) throws IOException
	{
		InputStream stream = new FileInputStream(file);
		LittleDataRead read = new LittleDataRead(stream);
		return new NVRFile(read);
	}
}
