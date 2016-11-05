package com.bob.mook;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bob.dao.check_form_info.CheckFormInfoDao;
import com.bob.dao.check_form_info.CheckFormInfoVo;
import com.bob.dao.member.MemberDao;
import com.bob.dao.member.MemberVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		return "index";
	}
	
	@RequestMapping(value = "/inner/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);

		return "list";
	}
	
	@RequestMapping(value = "/inner/result/{index}", method = RequestMethod.GET)
	public String result(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);

		return "list";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void login(Locale locale, Model model, HttpServletResponse response, HttpServletRequest request) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);

		MemberDao dao = new MemberDao();
		MemberVo vo = dao.selectByIdAndPassword(request.getParameter("id"), request.getParameter("password"));

		if (vo.getId() != null) { // 로그인 성공!
			request.getSession().setAttribute("memberVo", vo);
			response.sendRedirect(ssi.getPath() + "inner/list");
			
		} else { // 로그인 실패!
			response.sendRedirect(ssi.getPath());
		}
	}
	
	@ResponseBody
	@RequestMapping(value = "/inner/admin/api/check-form-info/{scale}", method = RequestMethod.POST)
	public HashMap<String, Object> scaleListData(@PathVariable("scale") int scale) {
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CheckFormInfoDao dao = new CheckFormInfoDao();
		List<CheckFormInfoVo> list = dao.selectByScale(scale);
		
		for(int i = 0; i < list.size(); i++) {
			CheckFormInfoVo vo = list.get(i);
			String arr[] = new String[5];
			arr[0] = Integer.toString(vo.getIndex());
			arr[1] = Integer.toString(vo.getCategory());
			arr[2] = vo.getContent();
			arr[3] = Integer.toString(vo.getScale());
			arr[4] = vo.getCriteria();
			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
}
