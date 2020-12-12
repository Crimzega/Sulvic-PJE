package com.sulvic.pje.game;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.sulvic.io.EndianInputStream;

@SuppressWarnings("unused")
public class Palette{
	
	private static final List<Palette> PALETTES = new ArrayList<Palette>();
	private static final int TRANSPARENT_INDEX = 0;
	private Color[] colors;
	
	private Palette(){ colors = new Color[16]; }
	
	public static void createPalettes(EndianInputStream stream) throws IOException{
		stream.read(new byte[4]);
		int count = stream.readInt();
		for(int i = 0; i < count; i++){
			Palette palette = new Palette();
			palette.fillColors(stream);
			PALETTES.add(palette);
		}
	}
	
	public static List<Palette> getPalettes(){ return PALETTES; }
	
	public static Palette getPalette(int index){ return PALETTES.get(index); }
	
	private void fillColors(EndianInputStream stream) throws IOException{ for(int i = 0; i < colors.length; i++) colors[i] = new Color(stream.readInt()); }
	
	public String toString(){
		StringBuilder builder = new StringBuilder("Palette: [\n\t");
		for(int i = 0; i < colors.length; i++){
			builder.append(i + 1).append(": ");
			Color color = colors[i];
			builder.append(String.format("%06x", color.getRGB() & 0xFFFFFF).toUpperCase());
			if(i != colors.length - 1) builder.append(",\n\t");
		}
		builder.append("\n]");
		return builder.toString();
	}
	
}
