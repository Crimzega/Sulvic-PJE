package com.sulvic.io;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.io.Closeable;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class SulvicIO{
	
	private static final Desktop DESKTOP = Desktop.getDesktop();
	private static final Toolkit TOOLKIT = Toolkit.getDefaultToolkit();
	
	public static boolean hasDisplay(){ return Desktop.isDesktopSupported(); }
	
	public static Clipboard getClipboard(){ return TOOLKIT.getSystemClipboard(); }
	
	public static Dimension getDisplaySize(){ return TOOLKIT.getScreenSize(); }
	
	public static Image getImage(String fileName){ return TOOLKIT.getImage(fileName); }
	
	public static Image getResourceImage(String fileName){ return TOOLKIT.getImage(SulvicIO.class.getResource(fileName)); }
	
	public static InputStream getResource(String fileName) {return SulvicIO.class.getResourceAsStream(fileName); }
	
	public static <T> String toArrayString(T[] array){ return Arrays.toString(array); }
	
	public static void browseFolser(File folder) throws IOException{ DESKTOP.browse(folder.toURI()); }
	
	public static void closeQuietly(Closeable... closeables) throws IOException{
		for(Closeable closeable: closeables){
			if(closeable instanceof Flushable) ((Flushable)closeable).flush();
			closeable.close();
		}
	}
	
	public static void openFile(File file) throws IOException{ DESKTOP.open(file); }
	
}
