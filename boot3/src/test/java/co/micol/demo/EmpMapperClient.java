package co.micol.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmpMapperClient {
	//단위테스트 케이스에 쓰이는 테스트 도구는 junit이다.
	
	@Autowired EmpMapper empMapper;
	
	@Test
	public void 페이징() {	//테스트이기 때문에 메서드이름을 한글로 써도 상관없다.
		
		EmpSearchVO vo = new EmpSearchVO();
		vo.setFirst(10);
		vo.setLast(20);
		List<EmpVO> list = empMapper.getEmpList(vo);
		System.out.println(list.get(0));
		
		//제대로 테스트가 되었는지 확인하기 위한 것. assert로 시작하는 여러가지 질문들이 있다.
		assertNotNull(list); 		//null인가 아닌가 알려줌
		
		//아랫쪽 junit탭에 초록색바가 나타나면 정상실행된것. 
		//sysout했으니 콘솔창에도 한줄 출력되었는지 확인
	}
	
	@Test
	public void 부서검색() {
		EmpSearchVO vo = new EmpSearchVO();
		//mapper쿼리에 페이징을 했기 때문에 페이지 설정을 해줘야한다.
		vo.setFirst(1);
		vo.setLast(10);
		vo.setDepartmentId("");
		vo.setJobId("IT_PROG");
		List<EmpVO> list = empMapper.getEmpList(vo);
		System.out.println(list.get(0));
		assertEquals(list.get(0).getJobId(),"IT_PROG");
	}
	
	@Test
	public void 직무검색() {
		
	}
}
