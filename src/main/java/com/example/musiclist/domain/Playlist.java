package com.example.musiclist.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import jakarta.validation.constraints.NotBlank;

@Entity
public class Playlist {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long playlistId;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @NotBlank(message = "description cannot be blank")
    private String description;

    @ManyToMany(mappedBy = "playlists", cascade = CascadeType.ALL)
    private List<Song> songs = new ArrayList<>();

    public Playlist() {
    }

    public Playlist(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(long playlistId) {
        this.playlistId = playlistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    @Override
    public String toString() {
        return "Playlist [playlistId=" + playlistId + ", name=" + name + ", description=" + description + ", songs="
                + songs + "]";
    }

    
}