package com.usermanager;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByLogin(String login);

}
