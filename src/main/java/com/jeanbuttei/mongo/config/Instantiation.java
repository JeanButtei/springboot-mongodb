package com.jeanbuttei.mongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.jeanbuttei.mongo.domain.Post;
import com.jeanbuttei.mongo.domain.User;
import com.jeanbuttei.mongo.dto.AuthorDTO;
import com.jeanbuttei.mongo.dto.CommentDTO;
import com.jeanbuttei.mongo.repository.PostRepository;
import com.jeanbuttei.mongo.repository.UserRepository;

@Configuration
public class Instantiation implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PostRepository postRepo;

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		postRepo.deleteAll();
		userRepo.deleteAll();
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepo.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("16/03/2021"), "Partiu viagem", "Lorem Ispsum", new AuthorDTO(maria));
		Post post2 = new Post(null, sdf.parse("14/03/2021"), "Bora", "Bla bla bla bla", new AuthorDTO(maria));
		
		CommentDTO c1 = new CommentDTO("Valeu", sdf.parse("16/03/2021"), new AuthorDTO(alex));
		CommentDTO c2 = new CommentDTO("Err err err", sdf.parse("15/03/2021"), new AuthorDTO(bob));
		CommentDTO c3 = new CommentDTO("Xhxhxhxhxh", sdf.parse("16/03/2021"), new AuthorDTO(alex));
		
		post1.getComments().addAll(Arrays.asList(c1, c2));
		post2.getComments().addAll(Arrays.asList(c3));
		
		postRepo.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepo.save(maria);
	}

}
