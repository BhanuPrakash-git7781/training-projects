package com.neosoft.test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.neosoft.test.bean.User;

@Service
public class UserService {

	private List<User> users = new ArrayList<User> (
			Arrays.asList(
				new User(101,"Akshay","Ak123"),
				new User(102,"Amit","Am123"),
				new User(103,"Anurag","An123"),
				new User(104,"Rohit","Rh123"),
				new User(105,"Vijay","Vj123")
			));
	
	public List<User> getAllUsers(){
		return users;
	}
	
	public User getUserById(int id) {
		return users.stream().filter(u -> u.getUid() == id).findFirst().get();
	}
	
	public void addUser(User user) {
		users.add(user);
	}
	
	public void deleteUser(int id) {
		users.removeIf(u -> u.getUid() == id);
	}
	
	public void updateUser(User user, int id) {
		users.replaceAll( u-> {
			if(u.getUid() == id)
				u = user;
			return u;	
		});
	}
	
	public List<User> sortByUname(){
		Comparator<User> byNameComparator = Comparator.comparing(User :: getUname);		
		return users.stream().sorted(byNameComparator).collect(Collectors.toList());
	}
}
