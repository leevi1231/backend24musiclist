package com.example.musiclist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.musiclist.domain.Playlist;
import com.example.musiclist.domain.PlaylistRepository;
import com.example.musiclist.domain.Song;
import com.example.musiclist.domain.SongRepository;

import jakarta.validation.Valid;

@Controller
public class PlaylistController {

    @Autowired
    private PlaylistRepository pRepository;

    @Autowired
    private SongRepository sRepository;

    // SHOW ALL PLAYLISTS

    @RequestMapping("/playlistlist")
    public String playlists(Model model) {
        model.addAttribute("playlists", pRepository.findAll());
        return "playlistlist";
    }

    // VIEW PLAYLIST

    @GetMapping("/playlists/{id}")
    public String viewPlaylist(@PathVariable("id") Long playlistId, Model model) {
        Playlist playlist = pRepository.findById(playlistId).orElse(null);
        model.addAttribute("songs", sRepository.findAll());

        if (playlist != null) {
            model.addAttribute("playlist", playlist);
            return "playlistdetail";
        } else {
            return "redirect:/playlistlist";
        }
    }

    // ADD PLAYLIST

    @GetMapping("/playlists/add")
    public String addPlaylist(Model model) {
        model.addAttribute("playlist", new Playlist());
        model.addAttribute("songs", sRepository.findAll());
        return "addplaylist";
    }

    // SAVE PLAYLIST

    @PostMapping("/playlists/save")
    public String save(@Valid @ModelAttribute Playlist playlist, Model model) {
        pRepository.save(playlist);
        return "redirect:/playlistlist";
    }

    // DELETE PLAYLIST

    @GetMapping("/playlists/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deletePlaylist(@PathVariable("id") Long playlistId, Model model) {
        pRepository.deleteById(playlistId);
        return "redirect:/playlistlist";
    }

    // EDIT PLAYLIST

    @GetMapping("/playlists/edit/{id}")
    public String editPlaylist(@PathVariable("id") Long playlistId, Model model) {
        Playlist playlist = pRepository.findById(playlistId).orElse(null);
        if (playlist != null) {

            model.addAttribute("playlist", playlist);
            model.addAttribute("songs", sRepository.findAll());
            return "editplaylist";
        } else {
            return "redirect:/playlistlist";
        }
    }

    // ADD SONG TO PLAYLIST

    @PostMapping("/playlists/addSong")
    public String addSongToPlaylist(@Valid @RequestParam("playlistId") Long playlistId,
            @RequestParam("songId") Long songId,
            Model model) {

        Playlist playlist = pRepository.findById(playlistId).orElse(null);
        Song song = sRepository.findById(songId).orElse(null);

        if (playlist != null && song != null) {

            playlist.getSongs().add(song);
            song.getPlaylists().add(playlist);

            pRepository.save(playlist);

            model.addAttribute("playlist", playlist);
            model.addAttribute("songs", sRepository.findAll());
            return "playlistdetail";
        } else {
            return "redirect:/playlists/" + playlistId;
        }

    }

    // DELETE SONG FROM PLAYLIST

    @GetMapping("/playlists/deleteSong")
    public String deleteSongFromPlaylist(@RequestParam("playlistId") Long playlistId,
            @RequestParam("songId") Long songId, Model model) {
        Playlist playlist = pRepository.findById(playlistId).orElse(null);
        Song song = sRepository.findById(songId).orElse(null);

        if (playlist != null && song != null) {
            playlist.getSongs().remove(song);
            song.getPlaylists().remove(playlist);
            pRepository.save(playlist);

            model.addAttribute("playlist", playlist);
            model.addAttribute("songs", sRepository.findAll());
            return "playlistdetail";
        } else {
            return "redirect:/playlists/" + playlistId;
        }
    }

}
