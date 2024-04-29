package css217_8.ex2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class Song {
    private String title;
    private String artist;

    public Song(String title, String artist) {
        this.title = title;
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }
}

interface SongIterator {
    boolean hasNextSong();
    Song nextSong();
}

interface Playlist {
    void addSong(Song song);
    SongIterator getIterator();
}

class PlaylistIterator implements SongIterator {
    private List<Song> songs;
    private int currentPosition;

    public PlaylistIterator(List<Song> songs) {
        this.songs = songs;
        this.currentPosition = 0;
    }

    @Override
    public boolean hasNextSong() {
        return currentPosition < songs.size();
    }

    @Override
    public Song nextSong() {
        if (hasNextSong()) {
            Song song = songs.get(currentPosition);
            currentPosition++;
            return song;
        }
        return null;
    }
}

class MyPlaylist implements Playlist {
    private List<Song> songs;

    public MyPlaylist() {
        this.songs = new ArrayList<>();
    }

    @Override
    public void addSong(Song song) {
        songs.add(song);
    }

    @Override
    public SongIterator getIterator() {
        return new PlaylistIterator(songs);
    }
}

public class MusicPlayerApp {
    public static void main(String[] args) {
        Playlist myPlaylist = new MyPlaylist();

        myPlaylist.addSong(new Song("Song 1", "Artist 1"));
        myPlaylist.addSong(new Song("Song 2", "Artist 2"));
        myPlaylist.addSong(new Song("Song 3", "Artist 3"));

        SongIterator iterator = myPlaylist.getIterator();

        while (iterator.hasNextSong()) {
            Song song = iterator.nextSong();
            System.out.println("Playing: " + song.getTitle() + " by " + song.getArtist());
        }
    }
}
