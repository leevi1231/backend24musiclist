package com.example.musiclist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.musiclist.domain.GenreRepository;
import com.example.musiclist.domain.Song;
import com.example.musiclist.domain.SongRepository;

import jakarta.validation.Valid;

@Controller
public class SongController {

    @Autowired
    private SongRepository sRepository;

    @Autowired
    private GenreRepository gRepository;

    // SHOW ALL SONGS

    @GetMapping("/songlist")
    public String songs(Model model) {
        model.addAttribute("songs", sRepository.findAll());
        return "songlist";
    }

    // ADD SONG

    @GetMapping("/songs/add")
    public String addSong(Model model) {
        model.addAttribute("song", new Song());
        model.addAttribute("genres", gRepository.findAll());
        return "addsong";
    }

    // SAVE SONG

    @PostMapping("/songs/save")
    public String save(@Valid @ModelAttribute Song song, Model model) {
        sRepository.save(song);
        return "redirect:/songlist";
    }

    // DELETE SONG

    @GetMapping("/songs/delete/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteSong(@PathVariable("id") Long songId, Model model) {
        sRepository.deleteById(songId);
        return "redirect:/songlist";
    }

    // EDIT SONG

    @GetMapping("/songs/edit/{id}")
    public String editSong(@PathVariable("id") Long songId, Model model) {
        Song song = sRepository.findById(songId).orElse(null);
        if (song != null) {
            model.addAttribute("song", song);
            model.addAttribute("genres", gRepository.findAll());
            return "editsong";
        } else {
            return "redirect:/songlist";
        }
    }

    // UPDATE SONG

    @PostMapping("/songs/update/{id}")
    public String updateSong(@Valid @PathVariable("id") Long songId, @ModelAttribute Song updatedSong) {
        Song existingSong = sRepository.findById(songId).orElse(null);
        if (existingSong != null) {
            existingSong.setTitle(updatedSong.getTitle());
            existingSong.setArtist(updatedSong.getArtist());
            existingSong.setDuration(updatedSong.getDuration());
            existingSong.setGenre(updatedSong.getGenre());
            sRepository.save(existingSong);
        }
        return "redirect:/songlist";
    }
}
