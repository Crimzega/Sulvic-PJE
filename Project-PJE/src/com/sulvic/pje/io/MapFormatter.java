package com.sulvic.pje.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.sulvic.io.Endian;
import com.sulvic.io.EndianOutputStream;
import com.sulvic.io.SulvicIO;
import com.sulvic.pje.game.map.GameMap;
import com.sulvic.pje.game.map.Tile;

public class MapFormatter{
	
	public static void writeToFile(GameMap map) throws IOException{
		String path = map.getPath();
		ZipOutputStream zipStream = new ZipOutputStream(new FileOutputStream(path + ".pjm"));
		zipStream.setMethod(ZipEntry.DEFLATED);
		zipStream.setLevel(6);
		EndianOutputStream coreStream = new EndianOutputStream(Endian.LITTLE, zipStream);
		zipStream.putNextEntry(new ZipEntry("header"));
		coreStream.writeUTF(map.getName());
		coreStream.writeInt(map.getWidth());
		coreStream.writeInt(map.getHeight());
		for(int tileset: map.getTilesets()) coreStream.writeInt(tileset);
		for(short border: map.getTileBorders()) coreStream.writeShort(border);
		zipStream.closeEntry();
		Tile[] tiles = map.getTiles();
		zipStream.putNextEntry(new ZipEntry("tiles"));
		for(Tile tile: tiles) coreStream.writeShort(tile.getID());
		zipStream.closeEntry();
		zipStream.putNextEntry(new ZipEntry("movement"));
		for(Tile tile: tiles) coreStream.writeByte(tile.getPermission().getValue());
		zipStream.closeEntry();
		SulvicIO.closeQuietly(coreStream, zipStream);
	}
	
}
