package com.example.sb.users;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userSvc; // 이코드로 userService사용 가능

	@GetMapping("/list/{page}")
	public String list(@PathVariable int page, Model model) {
		List<User> list = userSvc.getUserList(page);
		model.addAttribute("userList", list);
		return "user/list";
	}

	@GetMapping("/register")
	public String register() {
		return "user/register";
	}

	@PostMapping("/register")
	public String registerProc(String uid, String pwd, String pwd2, String uname, String email) {
		if (userSvc.getUserByUid(uid) == null && pwd.equals(pwd2)) {
			String hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
			User user = new User(uid, hashedPwd, uname, email);
			userSvc.registerUser(user);
		}
		return "redirect:/user/list/1";
	}
	@GetMapping("/update/{uid}")
	public String update(@PathVariable String uid, Model model){
		User user = userSvc.getUserByUid(uid);
		model.addAttribute("user", user);
		return "user/update";
	}

	@PostMapping("/update")
	public String updateProc(String uid, String pwd, String pwd2, String hashedPwd, String uname, String email){
		if (pwd != null && pwd.equals(pwd2)){
			hashedPwd = BCrypt.hashpw(pwd, BCrypt.gensalt());
			User user = new User(uid, hashedPwd, uname, email);
			userSvc.updateUser(user);
		}
		return "redirect:/user/list/1";
	}

	@GetMapping("/delete/{uid}")
	public String delete(@PathVariable String uid){
		userSvc.deleteUser(uid);
		return "redirect:/user/list/1";
	}
}
