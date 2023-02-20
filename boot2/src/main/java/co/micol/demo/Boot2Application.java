package co.micol.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Boot2Application {

	@Autowired CustomerRepository custRepo;
	
	@GetMapping("/cust")
	public List<Customer> cust(){
		return custRepo.findAll();
	}
	public static void main(String[] args) {
		SpringApplication.run(Boot2Application.class, args);
	}

}
