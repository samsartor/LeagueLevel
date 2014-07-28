package eekysam.leaguelevel.room.nvr;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import eekysam.LittleDataRead;
import eekysam.PrintOut;
import eekysam.leaguelevel.room.nvr.vert.NVRVertexList;

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
	public NVRVertexList[] verts;
	public NVRFaceArray[] indicies;
	public NVRMesh[] models;
	public NVRBoundingBox[] boxes;
	
	private boolean[] vertTypeFlags;
	
	private void read(LittleDataRead read) throws IOException
	{
		long startTime = System.currentTimeMillis();
		read.read(this.magic);
		if (!Arrays.equals(fileMagic, this.magic))
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
		PrintOut.tabIn();
		this.materials = new NVRMaterial[this.materialCount];
		for (int i = 0; i < this.materialCount; i++)
		{
			this.materials[i] = new NVRMaterial(read);
		}
		PrintOut.tabOut();
		
		PrintOut.printf("Reading %d Vertex Lists...", this.vertexListCount);
		PrintOut.tabIn();
		this.verts = new NVRVertexList[this.vertexListCount];
		for (int i = 0; i < this.vertexListCount; i++)
		{
			this.verts[i] = new NVRVertexList(read);
		}
		PrintOut.tabOut();
		
		PrintOut.printf("Reading %d Face Arrays...", this.indexListCount);
		PrintOut.tabIn();
		this.indicies = new NVRFaceArray[this.indexListCount];
		for (int i = 0; i < this.indexListCount; i++)
		{
			this.indicies[i] = new NVRFaceArray(read);
		}
		PrintOut.tabOut();
		
		this.vertTypeFlags = new boolean[this.vertexListCount];
		
		PrintOut.printf("Reading %d Models...", this.modelCount);
		PrintOut.tabIn();
		this.models = new NVRMesh[this.modelCount];
		for (int i = 0; i < this.modelCount; i++)
		{
			NVRMesh mesh = new NVRMesh(read);
			this.models[i] = mesh;
			this.vertTypeFlags[mesh.meshes[0].indexId] = true;
			this.vertTypeFlags[mesh.meshes[1].indexId] = false;
		}
		PrintOut.tabOut();
		
		PrintOut.printf("Parsing %d Vertex Lists...", this.vertexListCount);
		PrintOut.tabIn();
		for (int i = 0; i < this.vertexListCount; i++)
		{
			this.verts[i].parseData(!this.vertTypeFlags[i]);
		}
		PrintOut.tabOut();
		
		PrintOut.printf("Reading %d Bounding Boxes...", this.AABBCount);
		//PrintOut.tabIn();
		this.boxes = new NVRBoundingBox[this.AABBCount];
		for (int i = 0; i < this.AABBCount; i++)
		{
			this.boxes[i] = new NVRBoundingBox(read);
		}
		//PrintOut.tabOut();
		
		long endTime = System.currentTimeMillis();
		PrintOut.printf("\nDone Reading NVR File After %dms!", endTime - startTime);
	}
	
	public static NVRFile readNVR(File file) throws IOException
	{
		InputStream stream = new FileInputStream(file);
		LittleDataRead read = new LittleDataRead(stream);
		return new NVRFile(read);
	}
}
