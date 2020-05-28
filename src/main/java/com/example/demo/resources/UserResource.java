package com.example.demo.resources;

import java.net.URI;
import java.security.Provider.Service;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.UserDTO;
import com.example.demo.domain.User;
import com.example.demo.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll() {

		List<User> list = userService.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}
	
	
	/*@RequestMapping(value = "/{id}",method = RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {

		User obj = userService.findById(id);
		return ResponseEntity.ok().body(obj);
	}*/
	
	
	@PostMapping
	public ResponseEntity<Void> inseft(@RequestBody UserDTO objDto) {

		User obj = userService.fromDTO(objDto);
		obj = userService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
		
	}
	
	/*@RequestMapping(value = "/{id}",method = RequestMethod.PUT)
	public ResponseEntity<Void> upadate(@RequestBody UserDTO objDto, @PathVariable String id) {
		
		User obj = userService.fromDTO(objDto);
		obj.setId(id);
		obj = userService.update(obj);
		return ResponseEntity.noContent().build();
	}*/

}
