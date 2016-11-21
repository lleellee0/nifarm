package com.bob.mook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bob.dao.check_date.CheckDateDao;
import com.bob.dao.check_date.CheckDateVo;
import com.bob.dao.company_check_date.CompanyCheckDateDao;
import com.bob.dao.company_check_date.CompanyCheckDateVo;
import com.bob.dao.company_info.CompanyInfoDao;
import com.bob.dao.company_info.CompanyInfoVo;
import com.bob.dao.company_submited_form.CompanySubmitedFormDao;
import com.bob.dao.company_submited_form.CompanySubmitedFormVo;
import com.bob.dao.farm_image.FarmImageDao;
import com.bob.dao.farm_image.FarmImageVo;
import com.bob.dao.farm_info.FarmInfoDao;
import com.bob.dao.farm_info.FarmInfoVo;
import com.bob.dao.member.MemberDao;
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
	
	@RequestMapping(value = "/inner/api/file/{file_hash}", method = RequestMethod.GET)
	public void fileDownload(Locale locale, Model model, HttpServletResponse response, HttpServletRequest request, @PathVariable("file_hash") String file_hash) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		SubmitedFormDao sfdao = new SubmitedFormDao();
		SubmitedFormVo sfvo = sfdao.selectByFileHash(file_hash);
		
		File downloadFile = new File(ssi.getFilePath() + sfvo.getFile_hash());

		response.setHeader("Content-Disposition", "attachment; filename=\"" + sfvo.getOriginal_file_name() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
		
        FileInputStream fis = null;
        
        try {
             
            fis = new FileInputStream(downloadFile);
             
            FileCopyUtils.copy(fis, out);
             
             
        } catch(Exception e){
             
            e.printStackTrace();
             
        }finally{
             
            if(fis != null){
                 
                try{
                    fis.close();
                }catch(Exception e){}
            }
             
        }// try end;
         
        out.flush();
        
	}
	
	@RequestMapping(value = "/inner/company/api/file/{file_hash}", method = RequestMethod.GET)
	public void companyFileDownload(Locale locale, Model model, HttpServletResponse response, HttpServletRequest request, @PathVariable("file_hash") String file_hash) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		CompanySubmitedFormDao sfdao = new CompanySubmitedFormDao();
		CompanySubmitedFormVo sfvo = sfdao.selectByFileHash(file_hash);
		
		File downloadFile = new File(ssi.getFilePath() + sfvo.getFile_hash());

		response.setHeader("Content-Disposition", "attachment; filename=\"" + sfvo.getOriginal_file_name() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
		
        FileInputStream fis = null;
        
        try {
             
            fis = new FileInputStream(downloadFile);
             
            FileCopyUtils.copy(fis, out);
             
             
        } catch(Exception e){
             
            e.printStackTrace();
             
        }finally{
             
            if(fis != null){
                 
                try{
                    fis.close();
                }catch(Exception e){}
            }
             
        }// try end;
         
        out.flush();
        
	}
	
	@RequestMapping(value = "/inner/api/image/{file_hash}", method = RequestMethod.GET)
	public void farmImage(Locale locale, Model model, HttpServletResponse response, HttpServletRequest request, @PathVariable("file_hash") String file_hash) throws IOException {
		logger.info("Welcome home! The client locale is {}.", locale);

		SingletonSetting ssi = SingletonSetting.getInstance();
		ssi.setAllParameter(model);
		
		FarmImageDao fimdao = new FarmImageDao();
		FarmImageVo fimvo = fimdao.selectByFileHash(file_hash);
		
		File downloadFile = new File(ssi.getFilePath() + fimvo.getFile_hash());

		response.setHeader("Content-Disposition", "attachment; filename=\"" + fimvo.getOriginal_file_name() + "\";");
        response.setHeader("Content-Transfer-Encoding", "binary");
        OutputStream out = response.getOutputStream();
		
        FileInputStream fis = null;
        
        try {
             
            fis = new FileInputStream(downloadFile);
             
            FileCopyUtils.copy(fis, out);
             
             
        } catch(Exception e){
             
            e.printStackTrace();
             
        }finally{
             
            if(fis != null){
                 
                try{
                    fis.close();
                }catch(Exception e){}
            }
             
        }// try end;
         
        out.flush();
        
	}
	
	
	
	@ResponseBody
	@RequestMapping(value = "/inner/submit-checklist", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public Object uploadFile(MultipartHttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 농장주 ID 검색 후 있으면 member의 index에 저장해주고 없으면 ㅌㅌ하자
		Enumeration paramNames = request.getParameterNames(); 
		String farmName = null;
		String farmId = null;
		String checkDate = null;
		String location = null;
		String scale = null;
		String opinion = null;
		
		int farm_info_index = 0;
		int form_count = 0;
		
		int current_farm_info_index = 0;
		int current_form_count = 0;
		
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if(paramName.matches("farmName")) {
            	farmName = paramValues[0];
        	} else if(paramName.matches("farmId")) {
        		farmId = paramValues[0];
        	} else if(paramName.matches("checkDate")) {
        		checkDate = paramValues[0];
        	} else if(paramName.matches("location")) {
        		location = paramValues[0];
        	} else if(paramName.matches("scale")) {
        		scale = paramValues[0];
        	} else if(paramName.matches("checkerOpinion")) {
        		opinion = paramValues[0];
        	}
        } 

        if(!isExistFarm(farmId)) {
        	System.out.println("그런 id 없어요~");
        	return false;
        }
        
       	// 이미 체크를 한번 했던 농장이니까 마지막 체크 일자 수정, count 증가
       	FarmInfoDao fidao = new FarmInfoDao();
       	MemberDao mdao = new MemberDao();
       	FarmInfoVo fivo = fidao.selectByFarmId(farmId);
      	
       	fivo.setLast_check_date(checkDate);
       	fivo.setCheck_count(fivo.getCheck_count() + 1);
       	
       	fidao.update(fivo);
        
        fidao = new FarmInfoDao();
        mdao = new MemberDao();
        current_farm_info_index = farm_info_index = fidao.selectByFarmId(farmId).getIndex();
        current_form_count = form_count = fidao.selectByFarmId(farmId).getCheck_count();
        		
		SubmitedFormDao sfdao = new SubmitedFormDao();
		
		fivo = new FarmInfoVo();
		
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
        cdvo.setOpinion(opinion.replace("\n", "<br>"));
        cddao.insert(cdvo);
        
        
        /*
         * 파일 업로드 - 앞쪽에서 submited_form에 insert는 끝났기 때문에 update만 시켜주면 됨.
         */

        Iterator<String> itr =  request.getFileNames();
        if(itr.hasNext()) {
        	while(itr.hasNext()) {
        		MultipartFile mpf = request.getFile(itr.next());
        		System.out.println(mpf.getName());	// name 이름
        		
        		if(mpf.getName().equals("farm_image"))	// 농장 사진에 대한 예외처리
        			continue;
        		
        		int current_check_form_info_index = Integer.parseInt(mpf.getName().substring(5));
        		SubmitedFormVo sfvo = sfdao.selectByFarmInfoIndexAndFormCountAndCheckFormInfoIndex(current_farm_info_index, current_form_count, current_check_form_info_index);
        		
        		sfvo.setOriginal_file_name(mpf.getOriginalFilename());
        		String filehash = getSHA256(mpf.getOriginalFilename() + "sssssssaaaaaaaaallllllllllllllttttttttt" + Math.random() * Math.random());
        		sfvo.setFile_hash(filehash);

        		sfdao.update(sfvo);

                try {
                    //just temporary save file info into ufile
                	File f = multipartToFile(mpf, filehash);
                	
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
        	}
        	
        	
        	//농장 이미지 업로드
        	MultipartFile mpf = request.getFile("farm_image");
        	
        	FarmImageDao fimdao = new FarmImageDao();
        	FarmImageVo fimvo = new FarmImageVo();
        	
        	fimvo.setFarm_info_index(farm_info_index);
        	fimvo.setForm_count(form_count);
        	fimvo.setOriginal_file_name(mpf.getOriginalFilename());
        	
        	String filehash = getSHA256(mpf.getOriginalFilename() + "sssssssaaaaaaaaallllllllllllllttttttttt" + Math.random() * Math.random());
        	
        	fimvo.setFile_hash(filehash);
        	fimdao.insert(fimvo);
        	
        	try {
                //just temporary save file info into ufile
            	File f = multipartToFile(mpf, filehash);
            	
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        	
        	return true;
        } else {
        	System.out.println("No File!");
            return true;
        }
        
        
        
    }
	
	
	
	@ResponseBody
	@RequestMapping(value = "/inner/company/submit-checklist", method = RequestMethod.POST, produces = "application/json; charset=utf8")
    public Object uploadCompanyFile(MultipartHttpServletRequest request) throws UnsupportedEncodingException {
		request.setCharacterEncoding("UTF-8");
		// 농장주 ID 검색 후 있으면 member의 index에 저장해주고 없으면 ㅌㅌ하자
		Enumeration paramNames = request.getParameterNames(); 
		String farmName = null;
		String farmId = null;
		String checkDate = null;
		String location = null;
		String scale = null;
		String opinion = null;
		
		int farm_info_index = 0;
		int form_count = 0;
		
		int current_farm_info_index = 0;
		int current_form_count = 0;
		
        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            if(paramName.matches("farmName")) {
            	farmName = paramValues[0];
        	} else if(paramName.matches("farmId")) {
        		farmId = paramValues[0];
        	} else if(paramName.matches("checkDate")) {
        		checkDate = paramValues[0];
        	} else if(paramName.matches("location")) {
        		location = paramValues[0];
        	} else if(paramName.matches("scale")) {
        		scale = paramValues[0];
        	} else if(paramName.matches("checkerOpinion")) {
        		opinion = paramValues[0];
        	}
        } 

        if(!isExistCompany(farmId)) {
        	System.out.println("그런 id 없어요~");
        	return false;
        }
        
       	// 이미 체크를 한번 했던 농장이니까 마지막 체크 일자 수정, count 증가
       	CompanyInfoDao fidao = new CompanyInfoDao();
       	MemberDao mdao = new MemberDao();
       	CompanyInfoVo fivo = fidao.selectByCompanyId(farmId);
      	
       	fivo.setLast_check_date(checkDate);
       	fivo.setCheck_count(fivo.getCheck_count() + 1);
       	
       	fidao.update(fivo);
        
        fidao = new CompanyInfoDao();
        mdao = new MemberDao();
        current_farm_info_index = farm_info_index = fidao.selectByCompanyId(farmId).getIndex();
        current_form_count = form_count = fidao.selectByCompanyId(farmId).getCheck_count();
        		
		CompanySubmitedFormDao sfdao = new CompanySubmitedFormDao();
		
		fivo = new CompanyInfoVo();
		
		paramNames = request.getParameterNames(); 
        while(paramNames.hasMoreElements()) { 
            String paramName = (String)paramNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);
            
            if(paramName.contains("file_")) {
            	// 파일은 아래서 일괄적으로 처리
            	continue;
            } else if(paramName.contains("ypn_")) {
            	CompanySubmitedFormVo sfvo = new CompanySubmitedFormVo();
            	sfvo.setCompany_info_index(farm_info_index);
            	sfvo.setForm_count(form_count);
            	sfvo.setCompany_check_form_info_index(Integer.parseInt(paramName.substring(4)));
            	sfvo.setYpn(paramValues[0]);
            	sfvo.setOriginal_file_name("");
            	sfvo.setFile_hash("");
            	sfdao.insert(sfvo);
            }
        } 
        
        // check_date에 기록
        CompanyCheckDateDao cddao = new CompanyCheckDateDao();
        CompanyCheckDateVo cdvo = new CompanyCheckDateVo();
        cdvo.setCheck_date(checkDate);
        cdvo.setCompany_info_index(current_farm_info_index);
        cdvo.setForm_count(current_form_count);        
        cdvo.setOpinion(opinion.replace("\n", "<br>"));
        cddao.insert(cdvo);
        
        
        /*
         * 파일 업로드 - 앞쪽에서 submited_form에 insert는 끝났기 때문에 update만 시켜주면 됨.
         */

        Iterator<String> itr =  request.getFileNames();
        if(itr.hasNext()) {
        	while(itr.hasNext()) {
        		MultipartFile mpf = request.getFile(itr.next());
        		System.out.println(mpf.getName());	// name 이름
        		
        		if(mpf.getName().equals("farm_image"))	// 농장 사진에 대한 예외처리
        			continue;
        		
        		int current_check_form_info_index = Integer.parseInt(mpf.getName().substring(5));
        		CompanySubmitedFormVo sfvo = sfdao.selectByCompanyInfoIndexAndFormCountAndCheckFormInfoIndex(current_farm_info_index, current_form_count, current_check_form_info_index);
        		
        		sfvo.setOriginal_file_name(mpf.getOriginalFilename());
        		String filehash = getSHA256(mpf.getOriginalFilename() + "sssssssaaaaaaaaallllllllllllllttttttttt" + Math.random() * Math.random());
        		sfvo.setFile_hash(filehash);

        		sfdao.update(sfvo);

                try {
                    //just temporary save file info into ufile
                	File f = multipartToFile(mpf, filehash);
                	
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                    e.printStackTrace();
                }
        	}
        	
        	
        	//농장 이미지 업로드
        	MultipartFile mpf = request.getFile("farm_image");
        	
        	FarmImageDao fimdao = new FarmImageDao();
        	FarmImageVo fimvo = new FarmImageVo();
        	
        	fimvo.setFarm_info_index(farm_info_index);
        	fimvo.setForm_count(form_count);
        	fimvo.setOriginal_file_name(mpf.getOriginalFilename());
        	
        	String filehash = getSHA256(mpf.getOriginalFilename() + "sssssssaaaaaaaaallllllllllllllttttttttt" + Math.random() * Math.random());
        	
        	fimvo.setFile_hash(filehash);
        	fimdao.insert(fimvo);
        	
        	try {
                //just temporary save file info into ufile
            	File f = multipartToFile(mpf, filehash);
            	
            } catch (IOException e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        	
        	return true;
        } else {
        	System.out.println("No File!");
            return true;
        }
        
        
        
    }
	
	
	
	
	
	
	
	
	
	
	private boolean isExistFarm(String farmId) {
		/*
		MemberDao mdao = new MemberDao();
		FarmInfoDao fidao = new FarmInfoDao();
		MemberVo mvo = mdao.selectById(farmerId);
		
		if(fidao.selectByMemberIndex(mvo.getIndex()).getIndex() != 0)
			return true;
		
		return false;
		*/
		FarmInfoDao fidao = new FarmInfoDao();
		FarmInfoVo fivo = fidao.selectByFarmId(farmId);
		
		if(fivo.getIndex() != 0)
			return true;
		
		return false;
	}
	
	private boolean isExistCompany(String farmId) {
		/*
		MemberDao mdao = new MemberDao();
		FarmInfoDao fidao = new FarmInfoDao();
		MemberVo mvo = mdao.selectById(farmerId);
		
		if(fidao.selectByMemberIndex(mvo.getIndex()).getIndex() != 0)
			return true;
		
		return false;
		*/
		CompanyInfoDao fidao = new CompanyInfoDao();
		CompanyInfoVo fivo = fidao.selectByCompanyId(farmId);
		
		if(fivo.getIndex() != 0)
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
