package co.micol.demo;

import org.junit.jupiter.api.Test;

public class BuildTest {
	
	@Test 
	public void build() {
		//Customer cust = new Customer(10L,"","");
		Customer cust = Customer.builder().phone("011").name("kim").build();
	}
}
