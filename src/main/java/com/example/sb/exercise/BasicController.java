package com.example.sb.exercise;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/ex")
public class BasicController {

	@GetMapping("/hello")	// localhost:8090/sb/ex/hello
	public String hello() {
		return "exercise/hello"; 		// hello.html를 의미	// 파일이름
	}
	
	@ResponseBody			// html 파일을 찾지 말고, 데이터를 직접 전송하라는 코드
	@GetMapping("/noHTML")
	public String noHTML() {
		return "<h1>Hello Spring Boot!!!</h1>";
	}
	
	@GetMapping("/redirect")
	public String redirect() {
		return "redirect:/ex/hello";		// Redirection 가능 hello로 보내줌
	}
	
	@GetMapping("/params")
    public String params(Model model){
        model.addAttribute("name","제임스");
        return "exercise/params";
    }
}
