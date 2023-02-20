package co.micol.demo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	//@getter, setter 기타 플러스알파 포함한것
@AllArgsConstructor			//
@NoArgsConstructor			//인수가 없는 기본생성자 만들어줌(?)
@Builder		
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(length = 20, nullable = false)
	private String name;
	
	@Column(length = 20, nullable = false, unique = true)
	private String phone;
	
}
