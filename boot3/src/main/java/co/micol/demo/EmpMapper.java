package co.micol.demo;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
	List<Map> getJobList();

	//검색, 페이징 조회
	List<EmpVO> getEmpList(EmpSearchVO svo);
	int getcountTotal(EmpSearchVO svo);
	
	//단건조회
	EmpVO getEmp(String id);
	//등록
	int insertEmp(EmpVO emp);
	//수정
	int updateEmp(EmpVO emp);
	//삭제
	int deleteEmp(String id);
}
