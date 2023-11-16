package br.com.icare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.icare.model.User;
import br.com.icare.repository.IUser;
import br.com.icare.service.UserService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuarios")
public class UserController{
	
	private PasswordEncoder passwordEncoder;
	
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping
	public ResponseEntity<List<User>> listaUsers () {
		return ResponseEntity.status(200).body(userService.listUser());
	}

	@PostMapping
	public ResponseEntity<User> CreateUser (@Valid @RequestBody User user) {
		return ResponseEntity.status(201).body(userService.saveUser(user));
	}
	
	@PutMapping
	public ResponseEntity<User> editUser (@Valid @RequestBody User user) {
		return ResponseEntity.status(201).body(userService.editUser(user));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser (@PathVariable Integer id) {
		userService.removeUser(id);
		return ResponseEntity.status(204).build();
	}
	
	@PostMapping("/login")
	public ResponseEntity<User> valPassword(@Valid @RequestBody User user) {
		Boolean valid = userService.valPassword(user);
		if(!valid) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		return ResponseEntity.status(200).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationException(MethodArgumentNotValidException exception) {
		Map<String, String> err = new HashMap<>();
		
		exception.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMesage = error.getDefaultMessage();
			err.put(fieldName, errorMesage);
		});
		
		return err;
	}
}
