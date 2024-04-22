package com.example.musiclist;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.musiclist.web.PlaylistController;
import com.example.musiclist.web.SongController;

@SpringBootTest
class MusiclistApplicationTests {

	@Autowired
    private SongController scontroller;

    @Autowired
    private PlaylistController pcontroller;

	@Test
	void contextLoads() {
		assertThat(scontroller).isNotNull();
        assertThat(pcontroller).isNotNull();
	}

}
