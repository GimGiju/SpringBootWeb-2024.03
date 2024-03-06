package com.example.sb.exercise;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/ex")
public class BasicController {
	private final Logger log = LoggerFactory.getLogger(getClass());
	
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
	
	@GetMapping("/params2")
    public String params2(Model model, HttpServletRequest req){				// jakarta.servlet.http.HttpServletRequest
		String name = req.getParameter("name"); 
        model.addAttribute("name", name);
        return "exercise/params";
    }
	
	@GetMapping("/params3")				// 바로 위 코드와 동일하게 작동가능
    public String params3(Model model, String name, int count){				
        model.addAttribute("name", name + count);
        return "exercise/params";
    }
	
	@GetMapping("/memberForm")
	public String memberForm() {
		return "exercise/memberForm";
	}
	
	@PostMapping("/memberProc")
	public String memberProc(Member member, Model model) {
		log.info(member.toString());
		model.addAttribute("name", member.getName());
		return  "exercise/params";
	}
}
