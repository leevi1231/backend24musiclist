package com.example.musiclist;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.security.test.context.support.WithMockUser;


@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = "ADMIN")
public class SongControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testSongsPage() throws Exception {
        mockMvc.perform(get("/songlist"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("songs"))
                .andExpect(view().name("songlist"));
    }

    @Test
    public void testAddSongPage() throws Exception {
        mockMvc.perform(get("/songs/add"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("song"))
                .andExpect(model().attributeExists("genres"))
                .andExpect(view().name("addsong"));
    }

}

