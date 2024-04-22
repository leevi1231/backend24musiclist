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

import com.example.musiclist.domain.Playlist;
import com.example.musiclist.domain.PlaylistRepository;

@RestController
public class PlaylistRestController {

    @Autowired
    private PlaylistRepository pRepository;

    @RequestMapping(value = "/playlists", method = RequestMethod.GET)
    public List<Playlist> PlaylistListRest() {
        return (List<Playlist>) pRepository.findAll();
    }

    @RequestMapping(value = "/playlist/{id}", method = RequestMethod.GET)
    public Optional<Playlist> findPlaylistRest(@PathVariable("id") Long playlistId) {
        return pRepository.findById(playlistId);
    }

    @RequestMapping(value = "/playlists", method = RequestMethod.POST)
    public Playlist addPlaylist(@RequestBody Playlist playlist) {
        return pRepository.save(playlist);
    }

    @RequestMapping(value = "/playlist/{id}", method = RequestMethod.DELETE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> deletePlaylist(@PathVariable("id") Long playlistId) {
        Optional<Playlist> playlist = pRepository.findById(playlistId);
        if (playlist.isPresent()) {
            pRepository.deleteById(playlistId);
            return new ResponseEntity<>("Playlist '" + playlist + "' has been deleted.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Playlist '" + playlist + "' not found.", HttpStatus.NOT_FOUND);
        }
    }
}
