package com.sulvic.lib;

public class Version{
	
	private final int majorVersion, minorVersion;
	private boolean useBuild, useRevision;
	private int buildVersion, revisionVersion;
	
	public Version(int major, int minor){
		majorVersion = major;
		minorVersion = minor;
	}
	
	public Version(int major, int minor, int build){
		this(major, minor);
		useBuild = true;
		buildVersion = build;
	}
	
	public Version(int major, int minor, int build, int revision){
		this(major, minor, build);
		useRevision = true;
		revisionVersion = revision;
	}
	
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append(majorVersion).append('.');
		builder.append(minorVersion);
		if(useBuild) builder.append('.').append(buildVersion);
		if(useRevision) builder.append('.').append(revisionVersion);
		return builder.toString();
	}
	
}
