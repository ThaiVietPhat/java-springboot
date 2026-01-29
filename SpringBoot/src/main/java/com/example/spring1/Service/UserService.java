package com.example.spring1.Service;

import com.example.spring1.dto.User;
import com.example.spring1.repository.UserRepo;

import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service //tao Bean
public class UserService {
	@Autowired
	UserRepo userRepo;
    @Transactional
    public List<User> getUsers(String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return userRepo.findByNameContaining(keyword);
        }
        return userRepo.findAll();
    }
    @Transactional
    public void create(User user){
    	userRepo.save(user);
    }
    @Transactional
    public void delete(int id) {
    	userRepo.deleteById(id);
    }
    
    public User getById(int id) {
    	//Optional
    	return userRepo.findById(id).orElse(null); 
    }
    @Transactional
    public void update(User user) {
    	User currentUser=userRepo.findById(user.getId()).orElse(null);
    	if(currentUser !=null) {
    		currentUser.setName(user.getName());
    		currentUser.setOld(user.getOld());
    		currentUser.setHomeAddress(user.getHomeAddress());
    		userRepo.save(user);
    	}
    }
    @Transactional
    public void updatePassWord(User user) {
    	User currentUser=userRepo.findById(user.getId()).orElse(null);
    	if(currentUser !=null) {
    		currentUser.setPassword(user.getPassword());
    		userRepo.save(user);
    	}
    }
}

