package co.edu.icesi;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import co.edu.icesi.dao.IBusesDao;
import co.edu.icesi.dao.IConductoresDao;
import co.edu.icesi.dao.IRutasDao;
import co.edu.icesi.dao.IUserRepository;
import co.edu.icesi.model.BusType;
import co.edu.icesi.model.Tmio1Bus;
import co.edu.icesi.model.Tmio1Conductore;
import co.edu.icesi.model.Tmio1Ruta;
import co.edu.icesi.model.UserApp;
import co.edu.icesi.model.UserType;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner demo(IUserRepository userRepository, IBusesDao busRepository, IConductoresDao conductorRepository,
			IRutasDao rutaRepository) {
		return (args) -> {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	        
			
			UserApp user = new UserApp();			
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("123"));
			user.setType(UserType.admin);
			userRepository.save(user);
			
			UserApp user2 = new UserApp();			
			user2.setUsername("operator");
			user2.setPassword(passwordEncoder.encode("123"));
			user2.setType(UserType.operator);
			userRepository.save(user2);
			
			/*
			Tmio1Bus bus = new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(50));
			bus.setTipo(BusType.T);
			bus.setMarca("1234");
			bus.setPlaca("ABC123");
			bus.setModelo(new BigDecimal("2"));
			busRepository.save(bus);
			
			bus = new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(40));
			bus.setTipo(BusType.A);
			bus.setMarca("5678");
			bus.setPlaca("XYZ123");
			bus.setModelo(new BigDecimal("2"));
			busRepository.save(bus);

			Tmio1Conductore conductor = new Tmio1Conductore();
			conductor.setFechaContratacion(LocalDate.of(2010, 12, 31));
			conductor.setFechaNacimiento(LocalDate.of(1990,12,31));
			conductor.setCedula("000000000");
			conductor.setNombre("Juan");
			conductor.setApellidos("IG");
			conductorRepository.save(conductor);
			
			conductor.setFechaContratacion(LocalDate.of(2010, 12, 31));
			conductor.setFechaNacimiento(LocalDate.of(1990,12,31));
			conductor.setCedula("1111111111");
			conductor.setNombre("Sara");
			conductor.setApellidos("OD");
			conductorRepository.save(conductor);
			
			conductor = new Tmio1Conductore();
			conductor.setFechaContratacion(LocalDate.of(2010, 12, 31));
			conductor.setFechaNacimiento(LocalDate.of(1990,12,31));
			conductor.setCedula("2222222222");
			conductor.setNombre("Daniela");
			conductor.setApellidos("CM");
			conductorRepository.save(conductor);
			
			Tmio1Ruta ruta = new Tmio1Ruta();
			ruta.setNumero("1");
			ruta.setActiva("si");
			ruta.setDescripcion("universitaria");
			ruta.setDiaInicio(new BigDecimal(1));
			ruta.setDiaFin(new BigDecimal(3));
			ruta.setHoraInicio(new BigDecimal(1));
			ruta.setHoraFin(new BigDecimal(1000));
			rutaRepository.save(ruta);
			*/
		};
	}
}
