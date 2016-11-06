package com.bob.mook;

import java.io.IOException;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * Handles requests for the application home page.
 */
@Controller
public class FileController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@ResponseBody
	@RequestMapping(value = "/inner/submit-checklist", method = RequestMethod.POST)
    public Object uploadFile(MultipartHttpServletRequest request) {
        Iterator<String> itr =  request.getFileNames();
        if(itr.hasNext()) {
        	while(itr.hasNext()) {
        		MultipartFile mpf = request.getFile(itr.next());
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
            return false;
        }
    }
}
