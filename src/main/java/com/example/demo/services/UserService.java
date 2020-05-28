package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;
	
	public List<User> findAll(){
		
		return repo.findAll();
	}
	
	public Optional<User> findById(String id) {
		
		Optional<User> user = repo.findById(id);
		
		if(user == null) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		
		return user;
		
	}
	
	public User insert(User obj) {
		
		return repo.insert(obj);
	}
	
	public User fromDTO(UserDTO objDto) {
		
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
	
	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

}
