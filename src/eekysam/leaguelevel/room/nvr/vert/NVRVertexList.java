package eekysam.leaguelevel.room.nvr.vert;

import java.io.IOException;
import java.util.ArrayList;

import eekysam.LittleDataRead;
import eekysam.PrintOut;

public class NVRVertexList
{
	public NVRVertexList(LittleDataRead read) throws IOException
	{
		this.dataSize = read.readInt();
		PrintOut.printf("Reading %,d Bytes of Vertex Data...", this.dataSize);
		this.tempList = new byte[this.dataSize];
		read.read(this.tempList);
	}

	private byte[] tempList = null;
	public int dataSize;
	public NVRVertex[] verts;

	public void parseData(boolean byte12data)
	{
		if (this.tempList == null)
		{
			return;
		}
		if (byte12data)
		{
			this.parseByte12Data();
		}
		else
		{
			this.parseByte364044Data();
		}
		this.tempList = null;
	}

	private void parseByte12Data()
	{
		this.verts = new NVRVertexSimple[this.dataSize / 12];
		int pos = 0;
		for (int i = 0; i < this.verts.length; i++)
		{
			this.verts[i] = new NVRVertexSimple(this.tempList, pos);
			pos += this.verts[i].getReadSize();
		}
		PrintOut.printf("(Count: %d, VertexType: 12 byte)", this.verts.length);
	}

	private void parseByte364044Data()
	{
		ArrayList<NVRVertexComplex> list = new ArrayList<NVRVertexComplex>();
		int type = 0;
		boolean diff = false;
		int pos = 0;
		while (pos < this.tempList.length)
		{
			NVRVertexComplex v = new NVRVertexComplex(this.tempList, pos);
			list.add(v);
			int off = v.getReadSize();
			pos += off;
			if (type != 0 && type != off)
			{
				diff = true;
			}
			type = off;
		}
		this.verts = new NVRVertexComplex[list.size()];
		list.toArray(this.verts);
		PrintOut.printf("(Count: %d, VertexType: %d byte)", this.verts.length, type);
		if (diff)
		{
			PrintOut.println("\tWARNING: Vertex Type was not Constant!");
		}
	}
}
