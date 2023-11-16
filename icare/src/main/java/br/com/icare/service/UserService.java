package br.com.icare.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.icare.model.User;
import br.com.icare.repository.IUser;

@Service
public class UserService {
	
	private IUser repository;
	private PasswordEncoder passwordEnconder;
	
	public UserService(IUser repository) {
		this.repository = repository;
		this.passwordEnconder = new BCryptPasswordEncoder();
	}
	
	public List<User> listUser() {
		List<User> list = repository.findAll();
		return list;
	}
	
	public User saveUser(User user) {
		String encoder = this.passwordEnconder.encode(user.getSenha());
		user.setSenha(encoder);
		User newUser = repository.save(user);
		return newUser;
	}
	
	public User editUser(User user) {
		String encoder = this.passwordEnconder.encode(user.getSenha());
		user.setSenha(encoder);
		User newUser = repository.save(user);
		return newUser;
	}
	
	public Boolean removeUser(Integer id) {
		repository.deleteById(id);
		return true;
	}

	public Boolean valPassword(User user) {
		String password = repository.getReferenceById(user.getId()).getSenha();
		Boolean valid = passwordEnconder.matches(user.getSenha(), password);
		return valid;
	}

}
