package com.example.sb.exercise;


import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;



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
	@GetMapping("/login")
	public String login() {
		return "exercise/login";
	}
	
	@PostMapping("/login")
	public String loginProc(String uid, String pwd, HttpSession session, Model model){
		String hashedPwd = "$2a$10$gBKG8IZVNCHGVVWmDQLsXOd/KId9/5YboVtmfzAZo1j9bLpRpjw46";
		if (uid.equals("james")&& BCrypt.checkpw(pwd, hashedPwd)) {
			model.addAttribute("msg", uid + "님이 로그인 했습니다.");
			session.setAttribute("sessUid", uid);
			session.setAttribute("sessUname", "제임스");
			return "exercise/loginResult";
		}else {
			model.addAttribute("msg", "uid, 비밀번호를 확인하세요.");
			return "exercise/loginResult";
		}
	}
	
	@GetMapping({"/path/{uid}/{bid}", "/path/{uid}"})
	@ResponseBody
	public String path(@PathVariable String uid, @PathVariable (required=false) Integer bid) {
		bid = (bid == null) ? 0 : bid;
		return "<h1>uid=" + uid + ", bid=" + bid +  "</h1>"; 
	}
	// 여기까지가 데이터 주고 받기 파라메터,get,RespoonseBody,post
	
	
	
}
