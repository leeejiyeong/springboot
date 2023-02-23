package co.micol.demo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
@CrossOrigin(value="*")
public class EmpController {
	
	@Autowired EmpMapper empMapper;
	//Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	
	//aaa프로젝트랑 연결함(?). 다른프로젝트랑 연결하기
	@PostMapping("/product")
	@ResponseBody
	public Map product(@RequestBody List<Map<String, Object>> list) {
		System.out.println(list);
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("result", "success");
		map.put("size", list.size());
		return map;
	}
	
	
	
	@GetMapping("/empList")		//커멘드객체 => 모델에 저장
	public String empList(Model model, @ModelAttribute("esvo") EmpSearchVO svo, Paging paging){
		//paging.setPageUnit(5);
		//paging.setPageSize(3);
		svo.setFirst(paging.getFirst());
		svo.setLast(paging.getLast());
		paging.setTotalRecord(empMapper.getcountTotal(svo));
		model.addAttribute("empList", empMapper.getEmpList(svo));
		model.addAttribute("jobList", empMapper.getJobList());
		log.debug("empList 실행~~");
		return "empList";
	}
	
	//조회
	@GetMapping("/emp/{id}")
	@ResponseBody	//java vo객체를 json구조의 string으로 변환을 해줌
	public EmpVO getEmp(@PathVariable String id) {
		return empMapper.getEmp(id);
	}
	
	//등록
	@PostMapping("/emp")
	@ResponseBody
	public EmpVO insertEmp(EmpVO vo) {
		empMapper.insertEmp(vo);
		return vo;
	}
	
	//수정
	@PutMapping("/emp")
	@ResponseBody
	public EmpVO updateEmp(@RequestBody EmpVO vo) {
		empMapper.updateEmp(vo);
		return vo;
	}
	
	//삭제
	@DeleteMapping("/emp/{id}")
	@ResponseBody
	public Map<String, Object> deleteEmp(@PathVariable String id) {
		empMapper.deleteEmp(id);
		return Collections.singletonMap("result", "success");
	}
}
