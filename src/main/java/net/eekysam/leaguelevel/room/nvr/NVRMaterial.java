package net.eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import net.eekysam.LittleDataRead;
import net.eekysam.PrintOut;

public class NVRMaterial
{
	private NVRMaterial()
	{
		
	}
	
	public NVRMaterial(LittleDataRead read) throws IOException
	{
		this();
		this.read(read);
	}
	
	public char[] name = new char[256] ;
    public int unk1;
    public int unk2;
    public int unk3;
    public NVRTexture[] textures = new NVRTexture[8];
    
	private void read(LittleDataRead read) throws IOException
	{
		read.read(this.name);
		this.unk1 = read.readInt();
		this.unk2 = read.readInt();
		this.unk3 = read.readInt();
		PrintOut.printf("(Name: %S, %d, %d, %d)", PrintOut.fromChars(this.name), this.unk1, this.unk2, this.unk3);
		PrintOut.addPrefix("\t");
		for (int i = 0; i < this.textures.length; i++)
		{
			this.textures[i] = new NVRTexture(read);
		}
		PrintOut.removePrefix();
	}
}
