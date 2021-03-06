package com.myproject.spring5.mvc.fileupload;

import java.io.File;
import java.util.Iterator;
import java.util.UUID;
import java.util.regex.Matcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.myproject.spring5.util.ajax.AjaxUtils;

@Controller
@RequestMapping("/fileUpload")
public class FileUploadController {

	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	
    @RequestMapping(value = "/post") //ajax에서 호출하는 부분
    @PostMapping
    @ResponseBody
    public String uploadToTempDirectory(MultipartHttpServletRequest multipartRequest) throws Exception { //Multipart로 받는다.
    	
    	logger.info("Upload is starting");
    	
        Iterator<String> itr =  multipartRequest.getFileNames();
         
        //Temp 저장공간
        String filePath = "C:/transfer_test";
        String savedUid = "";
         
        while (itr.hasNext()) { //받은 파일들을 모두 돌린다.
             

            MultipartFile 	multipartFile 		= multipartRequest.getFile(itr.next());
            String 			originalFilename 	= multipartFile.getOriginalFilename();
            String 			fileExtension 		= originalFilename.substring(originalFilename.lastIndexOf(".")+1); //파일 확장자
            
            logger.debug("originalName 	>>>>>>>>>>>>>>>> {}"	,multipartFile.getOriginalFilename());
            logger.debug("fileExtension >>>>>>>>>>>>>>>> {}"	,fileExtension);
            logger.debug("size 			>>>>>>>>>>>>>>>> {}"	,multipartFile.getSize());
            logger.debug("ContentType 	>>>>>>>>>>>>>>>> {}"	,multipartFile.getContentType());
            logger.debug("originalName 	>>>>>>>>>>>>>>>> {}"	,multipartFile.getOriginalFilename());
      
            UUID uid = UUID.randomUUID();
            savedUid = uid.toString();
            
            logger.debug("savedUid	 	>>>>>>>>>>>>>>>> {}"	,savedUid);
            
            String fileFullPath = filePath + "/" + savedUid + "." + fileExtension; 	//저장될 경로
            fileFullPath.replaceAll("/", Matcher.quoteReplacement(File.separator));
      
            //경로가 없다면 생성
    		File directory = new File(fileFullPath);
    		if (directory.exists() == false) {
    			directory.mkdirs();
    		}
    		
            try {
                //파일 저장
            	multipartFile.transferTo(directory); //파일저장 실제로는 service에서 처리
            } catch (Exception e) {
                logger.error("error occured while making file to temp directory!!! caused by >>>>>>>>>>>>> {}",e.getMessage());
                throw new Exception(e);
            }
                          
       }
          
        return savedUid;
    }

}
