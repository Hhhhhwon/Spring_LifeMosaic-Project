package com.itwill.project.dto.setting;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.UUID;

@Slf4j
public class FileUtil {
    
    public static String updateImg(MultipartFile file) throws IOException {
        String uploadDirectoryPath = "C:\\uploads";
        
        String originalFileName = file.getOriginalFilename();
        log.debug("@@@@@@@@@@@@@@@@@@@@originalFileName={}",originalFileName);
        String originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
        log.debug("@@@@@@@@@@@@@@@@@@@@originalFileExtension={}",originalFileExtension);
        UUID uuid = UUID.randomUUID();
        String storedFileName = uuid + originalFileExtension;
        log.debug("@@@@@@@@@@@@@@@@@@@@@storedFileName={}",storedFileName);
        File uploadDirectory = new File(uploadDirectoryPath);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        File storedFile = new File(uploadDirectoryPath + File.separator + storedFileName);
        file.transferTo(storedFile);
        
        return storedFileName;
    }
}