package co.micol.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//vo와 같음
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Greeting {
	private String content;
}
