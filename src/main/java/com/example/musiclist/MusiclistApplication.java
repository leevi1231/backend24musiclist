package com.example.musiclist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.musiclist.domain.Genre;
import com.example.musiclist.domain.GenreRepository;
import com.example.musiclist.domain.Playlist;
import com.example.musiclist.domain.PlaylistRepository;
import com.example.musiclist.domain.Song;
import com.example.musiclist.domain.SongRepository;
import com.example.musiclist.domain.User;
import com.example.musiclist.domain.UserRepository;

@SpringBootApplication
public class MusiclistApplication {

	public static void main(String[] args) {
		SpringApplication.run(MusiclistApplication.class, args);
	}

	private static final Logger log = LoggerFactory.getLogger(MusiclistApplication.class);
	
	@Bean
	public CommandLineRunner songDemo(SongRepository sRepository, GenreRepository gRepository,
			UserRepository uRepository, PlaylistRepository pRepository) {
		return (args) -> {

			Genre electronic = gRepository.save(new Genre("Electronic"));
			Genre metal = gRepository.save(new Genre("Metal"));
			Genre pop = gRepository.save(new Genre("Pop"));
			Genre hiphop = gRepository.save(new Genre("Hip Hop"));
			Genre ambient = gRepository.save(new Genre("Ambient"));
			Genre rock = gRepository.save(new Genre("Rock"));

			sRepository.save(new Song("Xtal", "Aphex Twin", "7:44", electronic));
			sRepository.save(new Song("Poker Face", "Lady Gaga", "3:57", pop));
			sRepository.save(new Song("Children of the Grave", "Black Sabbath", "5:15", metal));
			sRepository.save(new Song("It Was A Good Day", "Ice Cube", "4:20", hiphop));
			sRepository.save(new Song("Olson", "Boards of Canada", "1:31", ambient));
			sRepository.save(new Song("Breathe (In The Air)", "Pink Floyd", "2:49", rock));
			
			uRepository.save(new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER"));
			uRepository.save(new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN"));

			pRepository.save(new Playlist("Metal list", "heavy epicness"));
			pRepository.save(new Playlist("Electronic groove", "beep boop"));
			pRepository.save(new Playlist("Summer playlist!!", "relaxing songs only"));

			log.info("Fetching all songs and users");

			for (Song song : sRepository.findAll()) {
				log.info(song.toString());
			}

			for (User user : uRepository.findAll()) {
				log.info(user.toString());
			}

		};
	}
}
