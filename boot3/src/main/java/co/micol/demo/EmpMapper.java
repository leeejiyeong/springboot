package co.micol.demo;

import java.util.List;
import java.util.Map;

public interface EmpMapper {
	List<EmpVO> getEmpList(EmpSearchVO svo);
	int getcountTotal(EmpSearchVO svo);
	List<Map> getJobList();
}
