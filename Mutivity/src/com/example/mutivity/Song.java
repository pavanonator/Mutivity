package com.example.mutivity;

public class Song {
	private long id;
	private String title;
	private String artist;
	//private double bpm;
	
	public Song(long songID, String songTitle, String songArtist/*, double songBpm*/) {
		  id=songID;
		  title=songTitle;
		  artist=songArtist;
		//  bpm=songBpm;
		}
	
	public long getID(){return id;}
	public String getTitle(){return title;}
	public String getArtist(){return artist;}
	//public double getBpm(){return bpm;}
}
