package co.micol.demo;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectMapperClient {
	
	@Test
	public void write() throws JsonProcessingException {
		ObjectMapper ob = new ObjectMapper();
		
		EmpVO vo = new EmpVO();
		
		vo.setEmployeeId("100");
		vo.setFirstName("홍길동");
		vo.setHireDate(new Date());
		String str = ob.writeValueAsString(vo);
		
		System.out.println(str);
	}
	
	@Test
	public void read() throws JsonMappingException, JsonProcessingException {
		String json = "{\"employeeId\":\"100\",\"firstName\":\"홍길동\",\"lastName\":null,\"salary\":null,\"hireDate\":\"2023/02/22\",\"departmentId\":null}";
		ObjectMapper ob = new ObjectMapper();
		EmpVO vo = ob.readValue(json, EmpVO.class);
		System.out.println(vo.getFirstName());
		
	}
}
