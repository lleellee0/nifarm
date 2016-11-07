package com.bob.mook;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

import com.bob.dao.check_date.CheckDateDao;
import com.bob.dao.check_date.CheckDateVo;
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
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	private static final SingletonSetting ssi = SingletonSetting.getInstance();
	
	/**
	 * Simply selects the home view to render by returning its name.
	 * @throws UnsupportedEncodingException 
	 */
	@ResponseBody
	@RequestMapping(value = "/inner/submit-checklist", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public Object uploadFile(MultipartHttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 농장주 ID 검색 후 있으면 member의 index에 저장해주고 없으면 ㅌㅌ하자
		Enumeration paramNames = request.getParameterNames(); 
		String farmName = null;
		String farmerId = null;
		String checkDate = null;
		String location = null;
		String scale = null;
		
		int farm_info_index = 0;
		int form_count = 0;
		
		int current_farm_info_index = 0;
		int current_form_count = 0;
		
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
        System.out.println("id :" + farmerId);
        if(!isExistFarm(farmerId)) {
        	// 농장이 없으니까 만들어줘야지 (insert into farm_info)
        	System.out.println("이름없네? 농장 ㄱ");
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
        	// 이미 체크를 한번 했던 농장이니까 마지막 체크 일자 수정, count 증가
        	FarmInfoDao fidao = new FarmInfoDao();
        	MemberDao mdao = new MemberDao();
        	FarmInfoVo fivo = fidao.selectByMemberIndex((mdao.selectById(farmerId).getIndex()));
        	
        	fivo.setLast_check_date(checkDate);
        	fivo.setCheck_count(fivo.getCheck_count() + 1);
        	System.out.println(fivo.getCheck_count());
        	
        	fidao.update(fivo);
        }
        
        FarmInfoDao fidao = new FarmInfoDao();
        MemberDao mdao = new MemberDao();
        current_farm_info_index = farm_info_index = fidao.selectByMemberIndex(mdao.selectById(farmerId).getIndex()).getIndex();
        current_form_count = form_count = fidao.selectByMemberIndex(mdao.selectById(farmerId).getIndex()).getCheck_count();
        		
		SubmitedFormDao sfdao = new SubmitedFormDao();
		
		FarmInfoVo fivo = new FarmInfoVo();
		
		paramNames = request.getParameterNames(); 
        while(paramNames.hasMoreElements()) { 
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            
            if(paramName.contains("file_")) {
            	// 파일은 아래서 일괄적으로 처리
            	continue;
            } else if(paramName.contains("ypn_")) {
            	SubmitedFormVo sfvo = new SubmitedFormVo();
            	sfvo.setFarm_info_index(farm_info_index);
            	sfvo.setForm_count(form_count);
            	sfvo.setCheck_form_info_index(Integer.parseInt(paramName.substring(4)));
            	sfvo.setYpn(paramValues[0]);
            	sfvo.setOriginal_file_name("");
            	sfvo.setFile_hash("");
            	sfdao.insert(sfvo);
            }
        } 
        
        // check_date에 기록
        CheckDateDao cddao = new CheckDateDao();
        CheckDateVo cdvo = new CheckDateVo();
        cdvo.setCheck_date(checkDate);
        cdvo.setFarm_info_index(current_farm_info_index);
        cdvo.setForm_count(current_form_count);
        cddao.insert(cdvo);
        
        
        /*
         * 파일 업로드 - 앞쪽에서 submited_form에 insert는 끝났기 때문에 update만 시켜주면 됨.
         */

        Iterator<String> itr =  request.getFileNames();
        if(itr.hasNext()) {
        	while(itr.hasNext()) {
        		MultipartFile mpf = request.getFile(itr.next());
        		System.out.println(mpf.getName());	// name 이름
        		
        		int current_check_form_info_index = Integer.parseInt(mpf.getName().substring(5));
        		SubmitedFormVo sfvo = sfdao.selectByFarmInfoIndexAndFormCountAndCheckFormInfoIndex(current_farm_info_index, current_form_count, current_check_form_info_index);
        		
        		sfvo.setOriginal_file_name(mpf.getOriginalFilename());
        		String filehash = getSHA256(mpf.getOriginalFilename() + "sssssssaaaaaaaaallllllllllllllttttttttt" + Math.random() * Math.random());
        		sfvo.setFile_hash(filehash);

        		sfdao.update(sfvo);
        		
                System.out.println(mpf.getOriginalFilename() +" uploaded!");
                try {
                    //just temporary save file info into ufile
                	File f = multipartToFile(mpf, filehash);
                	
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
        	}
        	return true;
        } else {
        	System.out.println("No File!");
            return true;
        }
    }
	
	private boolean isExistFarm(String farmerId) {
		MemberDao mdao = new MemberDao();
		FarmInfoDao fidao = new FarmInfoDao();
		MemberVo mvo = mdao.selectById(farmerId);
		
		if(fidao.selectByMemberIndex(mvo.getIndex()).getIndex() != 0)
			return true;
		
		return false;
	}
	
	public String getSHA256(String str){
		String SHA = ""; 
		try{
			MessageDigest sh = MessageDigest.getInstance("SHA-256"); 
			sh.update(str.getBytes()); 
			byte byteData[] = sh.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			SHA = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			e.printStackTrace(); 
			SHA = null; 
		}
		return SHA;
	}
	
	// 호출하면 멀티파트 파일이 파일로 저장됨.
	public File multipartToFile(MultipartFile multipart, String filehash) throws IllegalStateException, IOException 
	{
	        File convFile = new File( ssi.getFilePath() + filehash);
	        multipart.transferTo(convFile);
	        return convFile;
	}
}
