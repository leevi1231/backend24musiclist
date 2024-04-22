package com.example.musiclist.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import jakarta.validation.constraints.NotBlank;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long genreId;

    @NotBlank(message = "name cannot be blank")
    private String name;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private List<Song> songs;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public long getGenreId() {
        return genreId;
    }

    public void setGenreId(long genreId) {
        this.genreId = genreId;
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
        return "Genre [genreId=" + genreId + ", name=" + name + "]";
    }

}
