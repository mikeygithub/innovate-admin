package com.innovate.modules.common.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.innovate.entity.ProjectAttachEntity;
import com.innovate.modules.sys.controller.AbstractController;
import com.innovate.modules.util.FileUtils;
import com.innovate.modules.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description
 * @date 2019/9/20
 */
@Slf4j
@RestController
@RequestMapping("/common/file/")
public class CommonFileController extends AbstractController {

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadBat")
    public R uploadFile(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request){
        List<Map<String,Object>> resilt = new ArrayList<>();
        String projectName = request.getParameter("projectName");
        String tempPath = "/" + RandomUtils.getRandomNums() + "/";
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + projectName + tempPath;
        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }
        try {
            for(MultipartFile file : files){
                Map<String,Object> item = new HashMap<>();
                String fileName = file.getOriginalFilename();
                String result = FileUtils.upLoad(UPLOAD_FILES_PATH, fileName, file);
                if (!result.equals("true")) {
                    item.put("code", 500);
                }else{
                    item.put("code", 200);
                }
                tempPath += fileName;
                item.put("url", tempPath);
                logger.info("文件路径：{}", tempPath);
                resilt.add(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok().put("data",resilt);
    }

    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/upload")
    public Object upload(@RequestParam("file") MultipartFile file, final HttpServletRequest request) {
        try {
            String projectName = request.getParameter("projectName");
            if (projectName == null || "".equals(projectName)){
                projectName = "innovate";
            }
            String tempPath = "/" + RandomUtils.getRandomNums() + "/";
            String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + projectName + tempPath;
            if (Objects.isNull(file) || file.isEmpty()) {
                return R.error("文件为空，请重新上传");
            }
            String fileName = file.getOriginalFilename();
            String result  = FileUtils.upLoad(UPLOAD_FILES_PATH, fileName, file);
            if (!result.equals("true")) {
                return R.error(result);
            }
            logger.info("文件路径：{}", tempPath + fileName);
            logger.info(fileName);
            return R.ok("文件上传成功").put("data", UPLOAD_FILES_PATH + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.error("文件上传失败");
    }

}
