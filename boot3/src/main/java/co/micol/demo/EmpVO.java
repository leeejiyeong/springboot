package co.micol.demo;

import java.util.Date;

import lombok.Data;

@Data
public class EmpVO {
	String employeeId;
	String firstName;
	String lastName;
	String salary;
	Date hireDate;
	String departmentId;
	String jobId;
	
	//페이징에서 변수로 사용하기 위함(pageDTO사용하기 전에 썼던것)
//	Integer first;
//	Integer last;
}
