package eekysam.leaguelevel.room.nvr.vert;

public abstract class NVRVertex
{
	protected int getInt(byte[] data, int pos)
	{
		int i = 0;
		i |= (data[pos + 0] & 0xFF);
		i |= (data[pos + 1] & 0xFF) << 8;
		i |= (data[pos + 2] & 0xFF) << 16;
		i |= (data[pos + 3] & 0xFF) << 24;
		return i;
	}
	
	protected float getFloat(byte[] data, int pos)
	{
		return Float.intBitsToFloat(this.getInt(data, pos));
	}
	
	public abstract int getReadSize();
	
	public abstract float[] getPosition();
}
