package com.jeanbuttei.mongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jeanbuttei.mongo.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {	

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		User mock = new User("1", "Mockito Mocker", "mocked@mocking.com");
		User dummy = new User("2", "Dummy Bear", "dummy@dummer.com");
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(mock, dummy));
		
		return ResponseEntity.ok().body(list);
	}
}
