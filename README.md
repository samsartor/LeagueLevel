#LeagueLevel

Export League of Legends NVR Map Files

##Installing
1. Clone the Repository
2. Compile "src" to an executable Jar file in an empty folder. (This folder will also be your export destination)

##Getting the Level Files
To export a LOL map, you have to first extract the map data from the game assets.
###Installing RAFManager
Follow the instructions from the [League of Legends forums](http://forums.na.leagueoflegends.com/board/showthread.php?p=8597866) to download and install RAFManager by ItzWarty 
###Extracting the Files
1. Create an easy-to-access dump folder (on the desktop is recommended, you can move it later)
2. Open RAFManager
3. For each directory in the tree-view on the right, starting with the lowest version number and working your way up...
  1. Expand the directory  (Some directories do not have any contents, skip them)
  2. Right click the subdirectory called "LEVELS" (Many directories do not have a "LEVELS" subdirectory, skip them as well)
  3. Click "Dump" and select the folder you created in step 1 (This may take a few seconds, be patient)
4. Move your dump folder (Now containing "/LEVELS/Map.../...") to a better location if you want

##Running and Exporting
Your dump folder contains all of the LOL maps. Each map has its own folder in "LEVELS". If you wanted to export summoner's rift for example, you would use '[Your Dump Folder]/LEVELS/Map1/' as your map folder.
1. Open a command line in your export folder (containing the executable Jar)
2. Run the following command (Make sure you have Java on your environment PATH):
'''dos
java -jar "[Jar File].jar" "[Map Folder]" [Export Scale]
'''

##About the Exports
-The map is split into files by material
-The program will export 2 versions of each file. Both versions come directly from the game files
  -The complex version ('comp.obj') includes vertex normals and texture coordinates
  -The simplified version of each file ('simp.obj') contains the geometry only
-Each export is named using the material id and material name. (ie. '14 green_grass comp.obj')
  -Information about each material can be found in '[Map Folder]/Scene/room.mat'
-During export, the vertex coordinates are multiplied by the "Export Scale" parameter
  -At a scale of 1.0 the maps can be thousands of units wide
-The exports are Wavefront .obj files
