package co.micol.demo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
//@JsonInclude(JsonInclude.Include.NON_NULL)	//JSON으로 변환할때 null인건 안나오게함
public class EmpVO {
	
	String employeeId;
	String firstName;
	String lastName;
	String salary;
	@JsonFormat(pattern = "yyyy/MM/dd")		//날자가 숫자로 나올때
	//@DateTimeFormat
	Date hireDate;
	String departmentId;
	//@JsonIgnore			//json으로 변환할때 안나오게 하고싶음
	String jobId;
	String email;
	String phoneNumber;
	
	//페이징에서 변수로 사용하기 위함(pageDTO사용하기 전에 썼던것)
//	Integer first;
//	Integer last;
}
