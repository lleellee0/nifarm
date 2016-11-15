package com.bob.mook;

import java.io.IOException;
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

import com.bob.dao.check_date.CheckDateDao;
import com.bob.dao.check_date.CheckDateVo;
import com.bob.dao.check_form_info.CheckFormInfoDao;
import com.bob.dao.check_form_info.CheckFormInfoVo;
import com.bob.dao.farm_info.FarmInfoDao;
import com.bob.dao.farm_info.FarmInfoVo;
import com.bob.dao.member.MemberDao;
import com.bob.dao.member.MemberVo;
import com.bob.dao.submited_form.SubmitedFormDao;
import com.bob.dao.submited_form.SubmitedFormVo;

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
	
	@RequestMapping(value = "/inner/result/{farm_info_index}/{form_count}", method = RequestMethod.GET)
	public String result(Locale locale, Model model, @PathVariable("farm_info_index") int farm_info_index, @PathVariable("form_count") int form_count) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		FarmInfoDao fiDao = new FarmInfoDao();
		FarmInfoVo fiVo = fiDao.selectByIndex(farm_info_index);
		
		switch(fiVo.getScale()) {
			case 1: model.addAttribute("scale", "소규모");break;
			case 2: model.addAttribute("scale", "중규모");break;
			case 3: model.addAttribute("scale", "대규모");break;
		}
		
		model.addAttribute("fiVo", fiVo);
		
		CheckDateDao cdDao = new CheckDateDao();
		CheckDateVo cdVo = cdDao.selectByFarmInfoIndexAndFormCount(farm_info_index, form_count);
		
		System.out.println(cdVo.getCheck_date() + " " + farm_info_index + " " + form_count);
		
		model.addAttribute("cdVo", cdVo);

		return "result";
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
			String arr[] = new String[6];
			arr[0] = Integer.toString(vo.getIndex());
			arr[1] = Integer.toString(vo.getCategory());
			arr[2] = vo.getContent();
			arr[3] = Integer.toString(vo.getScale());
			arr[4] = vo.getCriteria();
			arr[5] = vo.getExample();
			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/inner/api/result/{scale}/{farm_info_index}/{form_count}", method = RequestMethod.POST)
	public HashMap<String, Object> resultListData(Model model, @PathVariable("scale") int scale, @PathVariable("farm_info_index") int farm_info_index,
			@PathVariable("form_count") int form_count) {
		SingletonSetting ssi = SingletonSetting.getInstance();
		model.addAttribute("farm_info_index", farm_info_index);
		model.addAttribute("form_count", form_count);
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CheckFormInfoDao dao = new CheckFormInfoDao();
		List<CheckFormInfoVo> cfilist = dao.selectByScale(scale);
		SubmitedFormDao sfdao = new SubmitedFormDao();
		
		System.out.println(farm_info_index + " " + form_count);
		

		for(int i = 0; i < cfilist.size(); i++) {
			CheckFormInfoVo cfivo = cfilist.get(i);
			SubmitedFormVo sfvo = sfdao.selectByFarmInfoIndexAndFormCountAndCheckFormInfoIndex(farm_info_index, form_count, i + 1);
			String arr[] = new String[9];
			arr[0] = Integer.toString(cfivo.getIndex());
			arr[1] = Integer.toString(cfivo.getCategory());
			arr[2] = cfivo.getContent();
			arr[3] = Integer.toString(cfivo.getScale());
			arr[4] = cfivo.getCriteria();
			arr[5] = cfivo.getExample();
			
			
			
			arr[6] = sfvo.getYpn();
			arr[7] = sfvo.getOriginal_file_name();
			arr[8] = sfvo.getFile_hash();
			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/inner/api/result/{farm_info_index}", method = RequestMethod.POST)
	public HashMap<String, Object> resultListsListData(Model model, @PathVariable("farm_info_index") int farm_info_index) {
		SingletonSetting ssi = SingletonSetting.getInstance();
		model.addAttribute("farm_info_index", farm_info_index);
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CheckDateDao cddao = new CheckDateDao();
		List<CheckDateVo> cdlist = cddao.selectByFarmInfoIndex(farm_info_index);
		
		for(int i = 0; i < cdlist.size(); i++) {
			CheckDateVo cdvo = cdlist.get(i);
			String arr[] = new String[4];
			arr[0] = Integer.toString(cdvo.getIndex());
			arr[1] = Integer.toString(cdvo.getFarm_info_index());
			arr[2] = Integer.toString(cdvo.getForm_count());
			arr[3] = cdvo.getCheck_date();
			
			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/inner/admin/api/dataTable", method = RequestMethod.POST)
	public HashMap<String, Object> dataTableData(Model model) {
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		FarmInfoDao fidao = new FarmInfoDao();
		List<FarmInfoVo> list = fidao.selectAll();
		
		for(int i = 0; i < list.size(); i++) {
			FarmInfoVo vo = list.get(i);
			String arr[] = new String[6];
			arr[0] = Integer.toString(vo.getIndex());
			arr[1] = vo.getFarm_name();
			arr[2] = vo.getLocation();
			switch(vo.getScale()) {
			case 1: arr[3] = "소";break;
			case 2: arr[3] = "중";break;
			case 3: arr[3] = "대";break;
			}
			 
			arr[4] = vo.getLast_check_date();
			arr[5] = "" + vo.getCheck_count();
			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
}
