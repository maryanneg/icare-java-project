package br.com.icare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.repository.CrudRepository;
import br.com.icare.model.User;

public interface IUser extends JpaRepository<User, Integer>{

}
