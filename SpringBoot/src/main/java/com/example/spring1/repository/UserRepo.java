package com.example.spring1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.spring1.dto.User;

public interface UserRepo extends JpaRepository<User, Integer>{
	//select user where username=?
	User findByUsername(String userName);
	//where name =:s
	List<User> findByName(String s);
	/*
	 * @Query("SELECT u FROM User u WHERE u.name LIKE %:x%") List<User>
	 * searchByName(@Param("x") String s);
	 */
	List<User> findByNameContaining(String name);
	/*
	 * @Query("SELECT u FROM User u WHERE u.name LIKE :x " +"OR u.username LIKE :x")
	 * List<User> searchByNameAndUsername(@Param("x") String s);
	 */
	List<User> findByNameContainingOrUsernameContaining(String name, String username);
	@Modifying
	@Query("DELETE FROM User u WHERE u.username = :x")
	int deleteUser(@Param("x") String username);
	
	void deleteByUsername(String username);
	
}
	