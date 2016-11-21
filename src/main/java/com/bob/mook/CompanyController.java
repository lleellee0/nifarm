package com.bob.mook;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bob.dao.company_check_date.CompanyCheckDateDao;
import com.bob.dao.company_check_date.CompanyCheckDateVo;
import com.bob.dao.company_check_form_info.CompanyCheckFormInfoDao;
import com.bob.dao.company_check_form_info.CompanyCheckFormInfoVo;
import com.bob.dao.company_info.CompanyInfoDao;
import com.bob.dao.company_info.CompanyInfoVo;
import com.bob.dao.company_submited_form.CompanySubmitedFormDao;
import com.bob.dao.company_submited_form.CompanySubmitedFormVo;
import com.bob.dao.farm_image.FarmImageDao;
import com.bob.dao.farm_image.FarmImageVo;

/**
 * Handles requests for the application home page.
 */
@Controller
public class CompanyController {
	
	private static final Logger logger = LoggerFactory.getLogger(CompanyController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/inner/company/list", method = RequestMethod.GET)
	public String list(Locale locale, Model model, HttpServletRequest request) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		request.getSession().setAttribute("state", "company");
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);

		return "company_list";
	}
	
	@RequestMapping(value = "/inner/company/result/{company_info_index}/{form_count}", method = RequestMethod.GET)
	public String result(Locale locale, Model model, @PathVariable("company_info_index") int company_info_index, @PathVariable("form_count") int form_count) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		CompanyInfoDao fiDao = new CompanyInfoDao();
		CompanyInfoVo fiVo = fiDao.selectByIndex(company_info_index);
		
		model.addAttribute("fiVo", fiVo);
		
		CompanyCheckDateDao cdDao = new CompanyCheckDateDao();
		CompanyCheckDateVo cdVo = cdDao.selectByCompanyInfoIndexAndFormCount(company_info_index, form_count);
		
		model.addAttribute("cdVo", cdVo);
		
		
		// 농장 이미지 FileHash 가져오기
		FarmImageDao fimdao = new FarmImageDao();
		FarmImageVo fimvo = new FarmImageVo();
		
		fimvo = fimdao.selectByFarmInfoIndexAndFormCount(company_info_index, form_count);
		
		model.addAttribute("company_image_file_hash", fimvo.getFile_hash());
		
		
		return "company_result";
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/inner/company/admin/api/check-form-info/{scale}", method = RequestMethod.POST)
	public HashMap<String, Object> scaleListData(@PathVariable("scale") int scale) {
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CompanyCheckFormInfoDao dao = new CompanyCheckFormInfoDao();
		List<CompanyCheckFormInfoVo> list = dao.selectByScale(scale);
		
		for(int i = 0; i < list.size(); i++) {
			CompanyCheckFormInfoVo vo = list.get(i);
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
	@RequestMapping(value = "/inner/company/admin/api/company-id-list", method = RequestMethod.POST)
	public HashMap<String, Object> companyIdListData() {
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CompanyInfoDao dao = new CompanyInfoDao();
		List<CompanyInfoVo> list = dao.selectAll();
		
		for(int i = 0; i < list.size(); i++) {
			CompanyInfoVo vo = list.get(i);
			String arr[] = new String[2];
			arr[0] = Integer.toString(vo.getIndex());
			arr[1] = vo.getCompany_id();

			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
	@ResponseBody
	@RequestMapping(value = "/inner/company/admin/api/location", method = RequestMethod.POST)
	public HashMap<String, Object> locationListData() {
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CompanyInfoDao dao = new CompanyInfoDao();
		List<CompanyInfoVo> list = dao.selectAll();
		
		int locationCountArr[] = new int[10];
		String locationStringArr[] = {"서울","경기","강원","충남","충북",
				"경북","경남","전북","전남","제주"
		};

		
		for(int i = 0; i < list.size(); i++) {
			CompanyInfoVo vo = list.get(i);
			switch(vo.getLocation()) {
			case "서울": locationCountArr[0]++; break;
			case "경기": locationCountArr[1]++; break;
			case "강원": locationCountArr[2]++; break;
			case "충남": locationCountArr[3]++; break;
			case "충북": locationCountArr[4]++; break;
			case "경북": locationCountArr[5]++; break;
			case "경남": locationCountArr[6]++; break;
			case "전북": locationCountArr[7]++; break;
			case "전남": locationCountArr[8]++; break;
			case "제주": locationCountArr[9]++; break;
			}
		}
		
		for(int i = 0; i < locationStringArr.length; i++) {
			String arr[] = new String[2];
			arr[0] = locationStringArr[i];
			arr[1] = Integer.toString(locationCountArr[i]);

			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/inner/company/admin/api/company-info-by-company-id/{companyId}", method = RequestMethod.POST)
	public HashMap<String, Object> companyInfoByCompanyId(@PathVariable("companyId") String companyId) {
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CompanyInfoDao dao = new CompanyInfoDao();
		
		CompanyInfoVo vo = dao.selectByCompanyId(companyId);
		String arr[] = new String[3];
		arr[0] = Integer.toString(vo.getIndex());
		arr[1] = vo.getCompany_name();
		arr[2] = vo.getLocation();

		hashmap.put(Integer.toString(0), arr);
		return hashmap;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/inner/company/api/result/{company_info_index}/{form_count}", method = RequestMethod.POST)
	public HashMap<String, Object> resultListData(Model model, @PathVariable("company_info_index") int company_info_index,
			@PathVariable("form_count") int form_count) {
		SingletonSetting ssi = SingletonSetting.getInstance();
		model.addAttribute("company_info_index", company_info_index);
		model.addAttribute("form_count", form_count);
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CompanyCheckFormInfoDao dao = new CompanyCheckFormInfoDao();
		List<CompanyCheckFormInfoVo> cfilist = dao.selectByScale(9999);		// 기업은 scale이 없음.
		CompanySubmitedFormDao sfdao = new CompanySubmitedFormDao();
		

		for(int i = 0; i < cfilist.size(); i++) {
			CompanyCheckFormInfoVo cfivo = cfilist.get(i);
			CompanySubmitedFormVo sfvo = sfdao.selectByCompanyInfoIndexAndFormCountAndCheckFormInfoIndex(company_info_index, form_count, cfivo.getIndex());
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
	@RequestMapping(value = "/inner/company/api/result/{company_info_index}", method = RequestMethod.POST)
	public HashMap<String, Object> resultListsListData(Model model, @PathVariable("company_info_index") int company_info_index) {
		SingletonSetting ssi = SingletonSetting.getInstance();
		model.addAttribute("company_info_index", company_info_index);
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CompanyCheckDateDao cddao = new CompanyCheckDateDao();
		List<CompanyCheckDateVo> cdlist = cddao.selectByCompanyInfoIndex(company_info_index);
		
		for(int i = 0; i < cdlist.size(); i++) {
			CompanyCheckDateVo cdvo = cdlist.get(i);
			String arr[] = new String[5];
			arr[0] = Integer.toString(cdvo.getIndex());
			arr[1] = Integer.toString(cdvo.getCompany_info_index());
			arr[2] = Integer.toString(cdvo.getForm_count());
			arr[3] = cdvo.getCheck_date();
			arr[4] = cdvo.getOpinion();
			
			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/inner/company/admin/api/dataTable", method = RequestMethod.POST)
	public HashMap<String, Object> dataTableData(Model model) {
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		HashMap<String, Object> hashmap = new HashMap<String, Object>();
		CompanyInfoDao fidao = new CompanyInfoDao();
		List<CompanyInfoVo> list = fidao.selectAll();
		
		for(int i = 0; i < list.size(); i++) {
			CompanyInfoVo vo = list.get(i);
			String arr[] = new String[5];
			arr[0] = Integer.toString(vo.getIndex());
			arr[1] = vo.getCompany_name();
			arr[2] = vo.getLocation();

			 
			arr[3] = vo.getLast_check_date();
			arr[4] = "" + vo.getCheck_count();
			hashmap.put(Integer.toString(i), arr);
		}
		return hashmap;
	}
	
}
