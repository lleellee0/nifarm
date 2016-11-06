package com.bob.mook;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bob.dao.farm_info.FarmInfoDao;
import com.bob.dao.farm_info.FarmInfoVo;
import com.bob.dao.member.MemberDao;
import com.bob.dao.member.MemberVo;
import com.bob.dao.submited_form.SubmitedFormDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/inner/submit-checklist", method = RequestMethod.POST, produces = "application/text; charset=utf8")
    public Object uploadFile(MultipartHttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 농장주 ID 검색 후 있으면 member의 index에 저장해주고 없으면 ㅌㅌ하자
		Enumeration paramNames = request.getParameterNames(); 
		String farmName = null;
		String farmerId = null;
		String checkDate = null;
		String location = null;
		String scale = null;
		
		int target_farm_info_index = 0;
		
        while(paramNames.hasMoreElements()) { 
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if(paramName.matches("farmName")) {
            	farmName = paramValues[0];
        	} else if(paramName.matches("farmerId")) {
        		farmerId = paramValues[0];
        	} else if(paramName.matches("checkDate")) {
        		checkDate = paramValues[0];
        	} else if(paramName.matches("location")) {
        		location = paramValues[0];
        	} else if(paramName.matches("scale")) {
        		scale = paramValues[0];
        	}
        } 
        if(!isExistFarm(farmerId)) {
        	// 농장이 없으니까 만들어줘야지 (insert into farm_info)
        	FarmInfoDao fidao = new FarmInfoDao();
        	FarmInfoVo fivo = new FarmInfoVo();
        	
        	MemberDao mdao = new MemberDao();
        	
        	fivo.setMember_index(mdao.selectById(farmerId).getIndex());
        	fivo.setFarm_name(farmName);
        	fivo.setLast_check_date(checkDate);
        	fivo.setLocation(location);
        	int iScale = 0;
        	if("대규모".equals(scale))
        		iScale = 3;
        	else if("중규모".equals(scale))
        		iScale = 2;
        	else if("소규모".equals(scale))
        		iScale = 1;
        	fivo.setScale(iScale);
        	fivo.setCheck_count(1);
        	
        	fidao.insert(fivo);
        } else {
        	FarmInfoDao fidao = new FarmInfoDao();
        	MemberDao mdao = new MemberDao();
        	FarmInfoVo fivo = fidao.selectByMemberIndex((mdao.selectById(farmerId).getIndex()));
        	
        	fivo.setLast_check_date(checkDate);
        	fivo.setCheck_count(fivo.getCheck_count() + 1);
        	System.out.println(fivo.getCheck_count());
        	
        	fidao.update(fivo);
        }
/*		
		SubmitedFormDao sfdao = new SubmitedFormDao();
		
		FarmInfoVo fivo = new FarmInfoVo();
		
		paramNames = request.getParameterNames(); 
        while(paramNames.hasMoreElements()) { 
        	
        	
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            System.out.println("Header : " + paramName); 
            
            if(paramName.contains("file_")) {
            	// 파일은 아래서 일괄적으로 처리
            	continue;
            } else if(paramName.contains("ypn_")) {
            	sfdao.s
            	paramValues[0];
            } else {
            	if(paramName.matches("farmName")) {
            		
            	} else if(paramName.matches("farmerId")) {
            		
            	} else if(paramName.matches("checkDate")) {
            		
            	} else if(paramName.matches("location")) {
            		
            	} else if(paramName.matches("scale")) {
            		
            	}
            }
        
                if (paramValues.length == 1) { 
                    String paramValue = paramValues[0]; 
                    if (paramValue.length() == 0) 
                        System.out.println("Value : No Value"); 
                    else 
                       System.out.println(paramValue); 
                } else { 
                    for(int i=0; i<paramValues.length; i++) { 
                        System.out.println("Value : " + paramValues[i]); 
                    } 
                } 
            } 
		
        Iterator<String> itr =  request.getFileNames();
        if(itr.hasNext()) {
        	while(itr.hasNext()) {
        		MultipartFile mpf = request.getFile(itr.next());
        		System.out.println(mpf.getName());	// name 이름
                System.out.println(mpf.getOriginalFilename() +" uploaded!");
                try {
                    //just temporary save file info into ufile
                    System.out.println("file length : " + mpf.getBytes().length);
                    System.out.println("file name : " + mpf.getOriginalFilename());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
        	}
        	return true;
        } else {
        	System.out.println("No File!");
            return false;
        }
        */
        return true;
    }
	
	private boolean isExistFarm(String farmerId) {
		MemberDao mdao = new MemberDao();
		FarmInfoDao fidao = new FarmInfoDao();
		MemberVo mvo = mdao.selectById(farmerId);
		
		if(fidao.selectByMemberIndex(mvo.getIndex()).getIndex() != 0)
			return true;
		
		return false;
	}
}
