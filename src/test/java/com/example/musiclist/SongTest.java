package com.example.musiclist;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.musiclist.domain.Genre;
import com.example.musiclist.domain.GenreRepository;
import com.example.musiclist.domain.Song;
import com.example.musiclist.domain.SongRepository;

@DataJpaTest
public class SongTest {

    @Autowired
    private SongRepository songRepository;
    
    @Autowired
    private GenreRepository genreRepository;

    @Test
    public void findByTitleShouldReturnSong() {
        Genre genre = new Genre("Pop");
        genreRepository.save(genre);
        Song song = new Song("Title", "Artist", "03:30", genre);
        songRepository.save(song);

        List<Song> foundSongs = songRepository.findByTitle("Title");

        assertThat(foundSongs).hasSize(1);
        assertThat(foundSongs.get(0).getTitle()).isEqualTo("Title");
        assertThat(foundSongs.get(0).getArtist()).isEqualTo("Artist");
        assertThat(foundSongs.get(0).getDuration()).isEqualTo("03:30");
        assertThat(foundSongs.get(0).getGenre()).isEqualTo(genre);
    }

    @Test
    public void createNewSong() {
        Genre genre = new Genre("Pop");
        Song song = new Song("Title", "Artist", "03:30", genre);

        songRepository.save(song);

        assertThat(song.getSongId()).isNotNull();
    }

    @Test
    public void deleteSong() {
        Genre genre = new Genre("Pop");
        Song song = new Song("Title", "Artist", "03:30", genre);
        songRepository.save(song);

        songRepository.delete(song);

        assertThat(songRepository.findByTitle("Title")).isEmpty();
    }
}
