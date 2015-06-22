package net.eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import net.eekysam.LittleDataRead;
import net.eekysam.PrintOut;

public class NVRMesh
{
	public int flag0;
	public int zero;
	public float[] sphere = new float[4];
	public float[] min = new float[3];
	public float[] max = new float[3];
	public int material;
	public NVRSubMesh[] meshes = new NVRSubMesh[2];
	
	private NVRMesh()
	{
		
	}
	
	public NVRMesh(LittleDataRead read) throws IOException
	{
		this();
		this.read(read);
	}
	
	private void read(LittleDataRead read) throws IOException
	{
		this.flag0 = read.readInt();
		this.zero = read.readInt();
		read.readFloats(this.sphere);
		read.readFloats(this.min);
		read.readFloats(this.max);
		this.material = read.readInt();
		PrintOut.printf("(Material: %d)", this.material);
		PrintOut.addPrefix("\t");
		this.meshes[0] = new NVRSubMesh(read);
		this.meshes[1] = new NVRSubMesh(read);
		PrintOut.removePrefix();
	}
}
