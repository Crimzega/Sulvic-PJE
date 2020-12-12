package com.sulvic.pje;

import java.io.FileInputStream;

import com.sulvic.io.Endian;
import com.sulvic.io.EndianInputStream;
import com.sulvic.io.SulvicIO;
import com.sulvic.pje.game.map.GameMap;
import com.sulvic.pje.io.MapFormatter;

public class ProgramRunner{
	
	public static void main(String[] args) throws Exception{
		GameMap map = GameMap.parseAdvanceMap(new EndianInputStream(Endian.LITTLE, SulvicIO.getResource("/CERULEAN CITY (3.3).map")));
//		if(map != null){
//			map.setName("Cerulean City");
//			map.setPath("cerulean_city");
//			MapFormatter.writeToFile(map);
//		}
		map = GameMap.parseAdvanceMap(new EndianInputStream(Endian.LITTLE, new FileInputStream("D:/Pok\u00e9mon Hacking Tools/Advance/Map/Maps/Hyperia X - Main.map")));
		if(map != null){
			map.setName("Hyperia2 [MAIN]");
			map.setPath("hyperia2_main");
			MapFormatter.writeToFile(map);
		}
	}
	
}
