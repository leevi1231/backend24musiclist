package com.example.musiclist.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.musiclist.domain.Song;
import com.example.musiclist.domain.SongRepository;

import jakarta.validation.Valid;

@RestController
public class SongRestController {

    @Autowired
    private SongRepository sRepository;

    @RequestMapping(value = "/songs", method = RequestMethod.GET)
    public List<Song> SongListRest() {
        return (List<Song>) sRepository.findAll();
    }

    @RequestMapping(value = "/song/{id}", method = RequestMethod.GET)
    public Optional<Song> findSongRest(@PathVariable("id") Long songId) {
        return sRepository.findById(songId);
    }

    @RequestMapping(value = "/songs", method = RequestMethod.POST)
    public Song addSong(@Valid @RequestBody Song song) {
        return sRepository.save(song);
    }

    @RequestMapping(value = "/song/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deleteSong(@PathVariable("id") Long songId) {
        Optional<Song> song = sRepository.findById(songId);
        if (song.isPresent()) {
            sRepository.deleteById(songId);
            return new ResponseEntity<>("Song '" + song + "' has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Song '" + song + "' not found.", HttpStatus.NOT_FOUND);
        }
    }
}
