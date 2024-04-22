package com.example.musiclist.domain;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import jakarta.validation.constraints.NotBlank;


@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long songId;

    @NotBlank(message = "title cannot be blank")
    private String title;

    @NotBlank(message = "artist cannot be blank")
    private String artist;

    @NotBlank(message = "duration cannot be blank")
    private String duration;

    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Playlist> playlists = new ArrayList<>();;

    @ManyToOne
    @JoinColumn(name = "genreid")
    private Genre genre;

    public Song() {
    }

    public Song(String title, String artist, String duration, Genre genre) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.genre = genre;
    }

    public long getSongId() {
        return songId;
    }

    public void setSongId(long songId) {
        this.songId = songId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Song [songId=" + songId + ", title=" + title + ", artist=" + artist + ", duration=" + duration
                + ", genres=" + genre + "]";
    }

}
