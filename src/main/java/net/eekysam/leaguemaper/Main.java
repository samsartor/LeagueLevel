package net.eekysam.leaguemaper;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import net.eekysam.leaguelevel.Level;
import net.eekysam.leaguelevel.room.nvr.NVRFile;
import net.eekysam.leaguelevel.room.nvr.NVRMaterial;
import net.eekysam.leaguelevel.room.nvr.NVRMesh;

public class Main
{
	public static void main(String[] args) throws IOException
	{
		Level level = new Level(new File(args[0]));
		try
		{
			level.load();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		System.out.printf("%nConverting '%s' to OBJ...%n", args[0]);
		float scale = 1.0F;
		if (args.length >= 2)
		{
			scale = Float.parseFloat(args[1]);
		}
		NVRFile nvr = level.room.roomModels;
		PrintStream[][] prints = new PrintStream[nvr.materialCount][];
		int[][] vertindex = new int[nvr.materialCount][2];
		float lastpercent = 0.0F;
		for (int i = 0; i < nvr.materialCount; i++)
		{
			NVRMaterial mat = nvr.materials[i];
			String matname = String.copyValueOf(mat.name).trim();
			File fs = new File(i + " " + matname + " simp.obj");
			fs.createNewFile();
			File fc = new File(i + " " + matname + " comp.obj");
			fc.createNewFile();
			prints[i] = new PrintStream[] { new PrintStream(fc), new PrintStream(fs) };
			lastpercent = percentUpdate(1, 2, i, nvr.materialCount, lastpercent, 10.0F);
		}
		lastpercent = 0.0F;
		for (int i = 0; i < nvr.models.length; i++)
		{
			NVRMesh mesh = nvr.models[i];
			for (int j = 0; j < 2; j++)
			{
				MeshToObjConvert conv = new MeshToObjConvert(mesh.meshes[j], nvr, prints[mesh.material][j], j == 0);
				vertindex[mesh.material][j] += conv.convert(vertindex[mesh.material][j], scale);
			}
			lastpercent = percentUpdate(2, 2, i, nvr.models.length, lastpercent, 1.0F);
		}
		for (int i = 0; i < nvr.materials.length; i++)
		{
			prints[i][0].close();
			prints[i][1].close();
		}
		System.out.printf("%nFinished.");
	}
	
	public static float percentUpdate(int step, int maxstep, int part, int total, float lastpercent, float inc)
	{
		float percent = ((float) part / total) * 100.0F;
		if (percent - lastpercent >= inc)
		{
			lastpercent = percent;
			System.out.printf("Step %d of %d: %d/%d (%.0f%%)%n", step, maxstep, part, total, percent);
		}
		return lastpercent;
	}
}
