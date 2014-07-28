package eekysam;

import java.io.IOException;
import java.io.InputStream;

public class LittleDataRead
{
	protected InputStream inputStream;
	
	public LittleDataRead(InputStream input)
	{
		this.inputStream = input;
	}
	
	public int readInt() throws IOException
	{
		int i = 0;
		i |= this.inputStream.read();
		i |= this.inputStream.read() << 8;
		i |= this.inputStream.read() << 16;
		i |= this.inputStream.read() << 24;
		return i;
	}
	
	public short readShort() throws IOException
	{
		short i = 0;
		i |= this.inputStream.read();
		i |= this.inputStream.read() << 8;
		return i;
	}
	
	public float readFloat() throws IOException
	{
		return Float.intBitsToFloat(this.readInt());
	}
	
	public int read(byte[] bytes) throws IOException
	{
		return this.inputStream.read(bytes);
	}
	
	public int read(char[] chars) throws IOException
	{
		int i;
		for (i = 0; i < chars.length; i++)
		{
			int r = this.inputStream.read();
			if (r == -1)
			{
				return i;
			}
			chars[i] = (char) r;
		}
		return i;
	}
	
	public int read() throws IOException
	{
		return this.inputStream.read();
	}
	
	public byte readByte() throws IOException
	{
		return (byte) this.inputStream.read();
	}
}
