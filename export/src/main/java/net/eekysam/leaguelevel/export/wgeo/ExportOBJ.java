package net.eekysam.leaguelevel.export.wgeo;

import java.io.File;

public class ExportOBJ
{
	public final WGEOFile wgeo;
	public final File levelfolder;
	public final File outfolder;
	
	public ExportOBJ(WGEOFile wgeo, File levelfolder, File outfolder)
	{
		this.wgeo = wgeo;
		this.levelfolder = levelfolder;
		this.outfolder = outfolder;
	}
}
