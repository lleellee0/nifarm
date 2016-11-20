package com.my.sketchbook;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class LoginController {
	DBDAO dao = new DBDAO();
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		return "login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		MemberBean member = dao.getMember(request.getParameter("id"));
		
		if(member.getM_id() != null){	//로그인성공
			System.out.println("login success");
			session.setAttribute("member",member);
			return "game";
		}else{	//로그인 실패
			session.setAttribute("member", null);
			return "login";
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public String logout(Model model) {

		//model.addAttribute("serverTime", formattedDate );
		
		return "login";
	}
	
}
