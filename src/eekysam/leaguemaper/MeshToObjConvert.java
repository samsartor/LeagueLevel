package eekysam.leaguemaper;

import java.io.PrintStream;

import eekysam.leaguelevel.room.nvr.NVRFaceArray;
import eekysam.leaguelevel.room.nvr.NVRFile;
import eekysam.leaguelevel.room.nvr.NVRSubMesh;
import eekysam.leaguelevel.room.nvr.vert.NVRVertex;
import eekysam.leaguelevel.room.nvr.vert.NVRVertexComplex;
import eekysam.leaguelevel.room.nvr.vert.NVRVertexList;
import eekysam.leaguelevel.room.nvr.vert.NVRVertexSimple;

public class MeshToObjConvert
{
	public NVRSubMesh mesh;
	public NVRFile nvr;
	public PrintStream print;
	public boolean complex;
	
	public MeshToObjConvert(NVRSubMesh mesh, NVRFile nvrFile, PrintStream print, boolean complex)
	{
		this.mesh = mesh;
		this.nvr = nvrFile;
		this.print = print;
		this.complex = complex;
	}
	
	public int convert(int vertindex, float scale)
	{
		int num = 0;
		NVRVertexList verts = this.nvr.verts[this.mesh.vertexId];
		for (int i = this.mesh.vertexOffset; i < this.mesh.vertexOffset + this.mesh.vertexCount; i++)
		{
			this.convertVertex(verts.verts[i], scale);
			num++;
		}
		NVRFaceArray array = this.nvr.indicies[this.mesh.indexId];
		for (int i = this.mesh.indexOffset; i < this.mesh.indexOffset + this.mesh.indexCount; i+=3)
		{
			this.convertFace(array, i, vertindex);
		}
		return num;
	}
	
	private void convertFace(NVRFaceArray array, int face, int vertindex)
	{
		String s = "f";
		for (int j = 0; j < 3; j++)
		{
			int k = array.indicies[face + j] & 0xFFFF;
			int f = (k - this.mesh.vertexOffset) + vertindex + 1;
			s += " " + this.getFaceIndex(f);
		}
		this.print.println(s);
	}
	
	private String getFaceIndex(int ind)
	{
		if (this.complex)
		{
			return ind + "/" + ind + "/" + ind;
		}
		else
		{
			return "" + ind;
		}
	}
	
	private void convertVertex(NVRVertex vert, float scale)
	{
		if (vert instanceof NVRVertexSimple)
		{
			this.convertVertex((NVRVertexSimple) vert, scale);
		}
		else if (vert instanceof NVRVertexComplex)
		{
			this.convertVertex((NVRVertexComplex) vert, scale);
		}
	}
	
	private void convertVertex(NVRVertexSimple vert, float scale)
	{
		String s = "v";
		for (int i = 0; i < 3; i++)
		{
			s += " " + (vert.position[i] * scale);
		}
		this.print.println(s);
	}
	
	private void convertVertex(NVRVertexComplex vert, float scale)
	{
		String s = "v";
		for (int i = 0; i < 3; i++)
		{
			s += " " + (vert.position[i] * scale);
		}
		this.print.println(s);
		s = "vn";
		for (int i = 0; i < 3; i++)
		{
			s += " " + vert.normal[i];
		}
		this.print.println(s);
		s = "vt";
		for (int i = 0; i < 2; i++)
		{
			s += " " + vert.uv[i];
		}
		this.print.println(s);
	}
}
