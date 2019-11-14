package co.edu.icesi.services;

import java.util.Optional;

import co.edu.icesi.model.UserApp;
import co.edu.icesi.model.UserGender;
import co.edu.icesi.model.UserType;

public interface UserServiceInt {

	public void save(UserApp user);

	public Optional<UserApp> findById(long id);

	public Iterable<UserApp> findAll();

	public Iterable<UserApp> findAllAdministrators();

	public Iterable<UserApp> findAllOperatos();

	public void delete(UserApp user);

	public UserGender[] getGenders();

	public UserType[] getTypes();
}
