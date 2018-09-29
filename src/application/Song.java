package application;

public class Song {
	//Instance Variables
	String name;
	String artist;
	String album;
	int year;
    		
	public Song(String name, String artist, String album, int year)
	{
		this.name = name;
		this.artist = artist;
		this.album = album;
		this.year = year;
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
}