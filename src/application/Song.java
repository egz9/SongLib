//Eric Zimmerman & Allen Guo
package application;

import java.util.Comparator;

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
	
	//returns negative number if this song comes before song s
	//returns 0 if song s and this song are equal
	//returns positive number if this song comes after song s
	public int compareTo(Song s){
		if (this.name.equals(s.name)){
			return this.artist.compareToIgnoreCase(s.album);
		}
		return this.name.compareToIgnoreCase(s.name);
	}
	
	@Override
	public String toString(){
		return this.name + "_" +  this.artist + "_" + this.album + "_" + this.year;		
	}
	
}