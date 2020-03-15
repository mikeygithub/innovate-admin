package com.innovate.modules.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传util
 */
public class UpLoadFileUtils {
    public static String upLoad (String UPLOAD_FILES_PATH, String fileName, MultipartFile file) throws IOException {
        if(file.isEmpty()){
            return "文件为空";
        }else{
            File dest = new File(UPLOAD_FILES_PATH  + "/" +  fileName );
            if(!dest.getParentFile().exists()){ //判断文件父目录是否存在
                dest.getParentFile().mkdirs();
            }
            file.transferTo(dest);
        }
        return "true";
    }
}
