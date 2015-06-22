package net.eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import net.eekysam.LittleDataRead;
import net.eekysam.PrintOut;

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
	public char[] name = new char[256];
	public byte[] additional = new byte[68];

	private void read(LittleDataRead read) throws IOException
	{
		this.redColor = read.readFloat();
		this.greenColor = read.readFloat();
		this.blueColor = read.readFloat();
		this.alphaColor = read.readFloat();
		read.read(this.name);
		read.read(this.additional);
		String n = this.getTexture();
		if (n != null)
		{
			PrintOut.printf("(%s, %.3f, %.3f, %.3f, %.3f)", n, this.redColor, this.greenColor, this.blueColor, this.alphaColor);
		}
	}

	public String getTexture()
	{
		String s = "";
		boolean flag = false;
		for (int i = 0; i < this.name.length; i++)
		{
			char c = this.name[i];
			if (Character.isAlphabetic(c))
			{
				flag = true;
			}
			if (c != 0x00)
			{
				s += c;
			}
		}
		if (!flag)
		{
			return null;
		}
		return s.trim();
	}
}
