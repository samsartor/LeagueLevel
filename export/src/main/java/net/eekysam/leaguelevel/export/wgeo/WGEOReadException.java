package net.eekysam.leaguelevel.export.wgeo;

import java.io.IOException;

public class WGEOReadException extends IOException
{
	private static final long serialVersionUID = -4985196039536351705L;
	
	public WGEOReadException()
	{
		super();
	}
	
	public WGEOReadException(String message)
	{
		super(message);
	}
	
	public WGEOReadException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
	public WGEOReadException(Throwable cause)
	{
		super(cause);
	}
}
