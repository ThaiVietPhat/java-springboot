package com.example.spring1.Service;

import com.example.spring1.dto.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<User> list= new ArrayList<>();
    public List<User> getAll(){
        return list;
    }
    public void create(User user){
        list.add(user);
    }
}

