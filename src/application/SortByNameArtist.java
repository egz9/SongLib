//Eric Zimmerman & Allen Guo
package application;

import java.util.Comparator;

public class SortByNameArtist implements Comparator<Song> {
	//returns negative number if this song comes before song s
	//returns 0 if song s and this song are equal
	//returns positive number if this song comes after song s
	public int compare(Song s1, Song s2){
		if (s1.name.equals(s2.name)){
			return s1.artist.compareToIgnoreCase(s2.album);
		}
		return s1.name.compareToIgnoreCase(s2.name);
	}
}
