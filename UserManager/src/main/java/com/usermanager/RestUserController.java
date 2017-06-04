package com.usermanager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {

	@Autowired
	private UserService userService;

	@RequestMapping("/rest-all-users")
	public List<User> getAllUsers() {
		return userService.getAllUsers();
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rest-new-user")
	public String addUser(String login, String password, boolean blocked) {
		User user = new User();
		user.setLogin(login);
		user.setPassword(password);
		user.setBlocked(blocked);
		try {
			userService.saveUser(user);
			return null;
		} catch (Exception e) {
			return "Save failed.\n" + e.getMessage();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rest-update-user")
	public String updateUser(String login, String newLogin, String newPassword, boolean newBlocked) {
		User user = userService.findUser(login);
		if (user != null) {
			user.setLogin(newLogin);
			user.setPassword(newPassword);
			user.setBlocked(newBlocked);
			userService.saveUser(user);
			return null;
		} else {
			return "User " + login + " not found.";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rest-block-user")
	public String userBlock(String login) {
		User user = userService.findUser(login);
		if (user != null) {
			if (user.isBlocked() == false) {
				user.setBlocked(true);
				userService.saveUser(user);
			}
			return null;
		} else {
			return "User " + login + " not found.";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rest-unblock-user")
	public String userUnblock(String login) {
		User user = userService.findUser(login);
		if (user != null) {
			if (user.isBlocked() == true) {
				user.setBlocked(false);
				userService.saveUser(user);
			}
			return null;
		} else {
			return "User " + login + " not found.";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rest-delete-user")
	public String userDelete(String login) {
		User user = userService.findUser(login);
		if (user != null) {
			userService.delete(user.getId());
			return null;
		} else {
			return "User " + login + " not found.";
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/rest-login")
	public String login(String login, String password) {
		User user = userService.findUser(login);
		if (user != null) {
			if (user.getPassword().equals(password)) {
				if (user.isBlocked()) {
					return "User " + login + " is blocked.";
				} else {
					return "Hello, " + login + "!";
				}
			} else {
				return "The password is incorrect. Try again.";
			}
		} else {
			return "User " + login + " not found.";
		}
	}

}