package com.sulvic.pje.game.map;

import java.io.IOException;

import com.sulvic.io.EndianInputStream;

@SuppressWarnings("unused")
public class GameMap{
	
	protected final int mapHeight, mapWidth;
	private int[] mapTilesets = {0, 1};
	private short[] mapTileBorders = {0, 0, 0, 0};
	private Tile[] mapTiles;
	private String mapName, mapPath;
	
	public GameMap(){ this(1, 1); }
	
	public GameMap(int width, int height){
		mapWidth = width;
		mapHeight = height;
		mapTiles = Tile.fillDefaults(this);
	}
	
	public static GameMap parseAdvanceMap(EndianInputStream stream) throws IOException{
		GameMap map = new GameMap(stream.readInt(), stream.readInt()).setTileset(stream.readInt(), stream.readInt());
		byte[] unknown = new byte[]{stream.readByte(), stream.readByte(), stream.readByte(), stream.readByte()};
		short[] borders = new short[4];
		for(int i = 0; i < 4; i++) borders[i] = stream.readShort();
		map.setTileBorders(borders);
		Tile[] tiles = new Tile[map.mapWidth * map.mapHeight];
		for(int i = 0; i < tiles.length; i++){
			short temp = stream.readShort();
			short tileID = (short)(temp & 0x3FF);
			byte perm = (byte)((temp >> 8) - (tileID >> 8));
			int y = i / map.mapWidth;
			int x = i % map.mapWidth;
			map.setTile(x, y, new Tile(tileID).setPermission(EnumMovePerm.getPermission(perm)));
		}
		// i / mapWidth -> y
		// i % y -> x
		return map;
	}
	
	public int getHeight(){ return mapHeight; }
	
	public int getWidth(){ return mapWidth; }
	
	public int[] getTilesets(){ return mapTilesets; }
	
	public GameMap setTileset(int tileset, int tileset1){
		mapTilesets = new int[]{tileset, tileset1};
		return this;
	}
	
	public GameMap setTileBorders(short[] borders){
		mapTileBorders = borders;
		return this;
	}
	
	public GameMap setTileBorders(short topLeft, short topRight, short bottomLeft, short bottomRight){ return setTileBorders(new short[]{topLeft, topRight, bottomLeft, bottomRight}); }
	
	public EnumMovePerm[][] getPermissions(int x, int y, int w, int h){
		EnumMovePerm[][] perms = new EnumMovePerm[h][w];
		for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) perms[y_][x_] = getTile(x + x_, y + y_).getPermission();
		return perms;
	}
	
	public Tile[][] getTiles(int x, int y, int w, int h){
		Tile[][] tiles = new Tile[h][w];
		for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) tiles[y_][x_] = getTile(x + x_, y + y_);
		return tiles;
	}
	
	public EnumMovePerm[] getPermissions(){
		EnumMovePerm[] perms = new EnumMovePerm[mapTiles.length];
		for(int i = 0; i < mapTiles.length; i++) perms[i] = mapTiles[i].getPermission();
		return perms;
	}
	
	public short[] getTileBorders(){ return mapTileBorders; }
	
	public Tile[] getTiles(){ return mapTiles; }
	
	public EnumMovePerm getPermission(int x, int y){ return getTile(x, y).getPermission(); }
	
	public String getName(){ return mapName; }
	
	public String getPath(){ return mapPath; }
	
	public Tile getTile(int x, int y){ return mapTiles[x + y * mapWidth]; }
	
	public void changeTile(int x, int y, short id){ setTile(x, y, new Tile(id).setPermission(getPermission(x, y))); }
	
	public void changeTiles(int x, int y, int w, int h, short id){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) changeTile(x + x_, y + y_, id); }
	
	public void setName(String name){ mapName = name; }
	
	public void setPath(String path){ mapPath = path; }
	
	public void setPermission(int x, int y, EnumMovePerm perm){ getTile(x, y).setPermission(perm); }
	
	public void setPermissions(int x, int y, int w, int h, EnumMovePerm perm){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) setPermission(x + x_, y + y_, perm); }
	
	public void setPermissions(int x, int y, int w, int h, EnumMovePerm[][] perms){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) setPermission(x + x_, y + y_, perms[y_][x_]); }
	
	public void setTile(int x, int y, Tile tile){ mapTiles[x + y * mapWidth] = tile; }
	
	public void setTiles(int x, int y, int w, int h, Tile[][] tiles){ for(int y_ = 0; y_ < h; y_++) for(int x_ = 0; x_ < w; x_++) setTile(x + x_, y + y_, tiles[y_][x_]); }
	
}
