package com.usermanager;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> getAllUsers() {
		List<User> users = new ArrayList<>();
		userRepository.findAll().forEach(users::add);
		return users;
	}

	public User findUser(long id) {
		return userRepository.findOne(id);
	}

	public User findUser(String login) {
		return userRepository.findByLogin(login);
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}

	public void delete(long id) {
		userRepository.delete(id);
	}

}