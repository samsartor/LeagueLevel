package eekysam.leaguelevel.room.nvr.vert;

public class NVRVertexSimple extends NVRVertex
{
	public float[] position = new float[3];
	
	public NVRVertexSimple(byte[] data, int pos)
	{
		for (int i = 0; i < 3; i++)
		{
			this.position[i] = this.getFloat(data, pos);
			pos += 4;
		}
	}

	@Override
	public int getReadSize()
	{
		return 12;
	}

	@Override
	public float[] getPosition()
	{
		return this.position;
	}
}
