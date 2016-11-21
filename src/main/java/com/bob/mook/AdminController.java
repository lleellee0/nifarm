package com.bob.mook;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bob.dao.company_info.CompanyInfoDao;
import com.bob.dao.company_info.CompanyInfoVo;
import com.bob.dao.farm_info.FarmInfoDao;
import com.bob.dao.farm_info.FarmInfoVo;
import com.bob.dao.member.MemberDao;
import com.bob.dao.member.MemberVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/admin/setting", method = RequestMethod.GET)
	public String settingIndex(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		return "admin";
	}
	
	@RequestMapping(value = "/admin/setting/add-farm", method = RequestMethod.POST)
	public void addFarm(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		MemberDao mdao = new MemberDao();
		int memberIndex = mdao.selectById(request.getParameter("memberId")).getIndex();
		
		FarmInfoDao fidao = new FarmInfoDao();
		FarmInfoVo fivo = new FarmInfoVo();
		
		fivo.setMember_index(memberIndex);
		fivo.setFarm_name(request.getParameter("farmName"));
		fivo.setLast_check_date("1000-01-01");
		fivo.setLocation(request.getParameter("location"));
		
		int scale = 0;
		
		switch(request.getParameter("scale")) {
		case "대규모" : scale = 3; break;
		case "중규모" : scale = 2; break;
		case "소규모" : scale = 1; break;
		}
		fivo.setScale(scale);
		fivo.setCheck_count(0);
		fivo.setFarm_id(request.getParameter("farmId"));
		
		fidao.insert(fivo);

		response.sendRedirect(ssi.getPath() + "admin/setting");
	}
	
	@RequestMapping(value = "/admin/setting/add-company", method = RequestMethod.POST)
	public void addCompany(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		MemberDao mdao = new MemberDao();
		int memberIndex = mdao.selectById(request.getParameter("memberId")).getIndex();
		
		CompanyInfoDao cidao = new CompanyInfoDao();
		CompanyInfoVo civo = new CompanyInfoVo();
		
		civo.setMember_index(memberIndex);
		civo.setCompany_name(request.getParameter("companyName"));
		civo.setLast_check_date("1000-01-01");
		civo.setLocation(request.getParameter("location"));
		
		civo.setCheck_count(0);
		civo.setCompany_id(request.getParameter("companyId"));
		
		cidao.insert(civo);

		response.sendRedirect(ssi.getPath() + "admin/setting");
	}
	
	@RequestMapping(value = "/admin/setting/add-member", method = RequestMethod.POST)
	public void addMember(Locale locale, Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		MemberDao mdao = new MemberDao();
		MemberVo mvo = new MemberVo();
		
		mvo.setId(request.getParameter("memberId"));
		mvo.setPassword(request.getParameter("memberPassword"));
		mvo.setName(request.getParameter("memberName"));

		mdao.insert(mvo);
		
		response.sendRedirect(ssi.getPath() + "admin/setting");
	}
}
