package co.micol.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class EmpController {
	
	@Autowired EmpMapper empMapper;
	//Logger logger = LoggerFactory.getLogger(EmpController.class);
	
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
}
