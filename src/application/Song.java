package application;

public class Song{
	//Instance Variables
	String name;
	String artist;
	String album;
	String year;
    		
	public Song(String name, String artist, String album, String year)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
	}
	public Song() {
	}
	public static boolean IsSame(Song s, Song s2)
	{
		if(s.name == s2.name)
		{
			if(s.artist == s2.artist)
				{
				return true;
				}
		}
		return false;
	}
	@Override
	public String toString(){
		return this.name + "_" +  this.artist + "_" + this.album + "_" + this.year;		
	}
	
}