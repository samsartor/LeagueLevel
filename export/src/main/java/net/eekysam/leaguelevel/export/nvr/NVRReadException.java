package net.eekysam.leaguelevel.export.nvr;

import java.io.IOException;

public class NVRReadException extends IOException
{
	private static final long serialVersionUID = 4102175737859725838L;
	
	public NVRReadException()
	{
		super();
	}
	
	public NVRReadException(String message)
	{
		super(message);
	}
	
	public NVRReadException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public NVRReadException(Throwable cause)
	{
		super(cause);
	}
}
