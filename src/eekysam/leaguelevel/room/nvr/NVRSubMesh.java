package eekysam.leaguelevel.room.nvr;

import java.io.IOException;

import eekysam.LittleDataRead;
import eekysam.PrintOut;

public class NVRSubMesh
{
	public int vertexId;
	public int vertexOffset;
	public int vertexCount;
	public int indexId;
	public int indexOffset;
	public int indexCount;
	
	private NVRSubMesh()
	{
		
	}
	
	public NVRSubMesh(LittleDataRead read) throws IOException
	{
		this();
		this.read(read);
	}
	
	private void read(LittleDataRead read) throws IOException
	{
		this.vertexId = read.readInt();
		this.vertexOffset = read.readInt();
		this.vertexCount = read.readInt();
		this.indexId = read.readInt();
		this.indexOffset = read.readInt();
		this.indexCount = read.readInt();
		PrintOut.printf("(Vert: [%d] %d + %d, Indicies: [%d] %d + %d)", this.vertexId, this.vertexOffset, this.vertexCount, this.indexId, this.indexOffset, this.indexCount);
	}
}
