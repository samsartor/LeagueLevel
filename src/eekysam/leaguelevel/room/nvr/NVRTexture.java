package eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import eekysam.LittleDataRead;

public class NVRTexture
{
	private NVRTexture()
	{
		
	}
	
	public NVRTexture(LittleDataRead read) throws IOException
	{
		this();
		this.read(read);
	}
	
	public float redColor;
	public float greenColor;
	public float blueColor;
	public float alphaColor;
	public char[] name = new char[324];
	
	private void read(LittleDataRead read) throws IOException
	{
		this.redColor = read.readFloat();
		this.greenColor = read.readFloat();
		this.blueColor = read.readFloat();
		this.alphaColor = read.readFloat();
		read.read(this.name);
	}
}
