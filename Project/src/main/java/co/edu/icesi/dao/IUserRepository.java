package co.edu.icesi.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.model.UserApp;
import co.edu.icesi.model.UserType;

public interface IUserRepository extends CrudRepository<UserApp, Long>{
	
	public List<UserApp> findByName(String name);
	
	public List<UserApp> findByUsername(String username);
	
	public List<UserApp> findByType(UserType type);

}
