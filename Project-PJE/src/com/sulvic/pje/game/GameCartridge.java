package com.sulvic.pje.game;

import com.sulvic.lib.Version;

@SuppressWarnings({"unused"})
public class GameCartridge{
	
	private final String theName;
	private final Version theVersion;
	
	public GameCartridge(String name, Version version){
		theName = name;
		theVersion = version;
	}
	
}
