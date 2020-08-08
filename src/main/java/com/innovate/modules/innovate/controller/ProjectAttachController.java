package com.innovate.modules.innovate.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.innovate.entity.ProjectAttachEntity;
import com.innovate.modules.sys.controller.AbstractController;
import com.innovate.modules.util.FileUtils;
import com.innovate.modules.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/5
 * 附件Controller
 **/
@RestController
@RequestMapping("innovate/project/attach")
@Slf4j
public class ProjectAttachController extends AbstractController {

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @PostMapping(value = "/upload")
    @RequiresPermissions("innovate:project:save")
    public Object uploadFile(@RequestParam("file") List<MultipartFile> files, final HttpServletRequest request) {
        String projectName = request.getParameter("projectName");
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + projectName + "/" + RandomUtils.getRandomNums() + "/";
        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }
        ProjectAttachEntity projectAttachEntity = null;
        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            String result = null;
            try {
                result = FileUtils.upLoad(UPLOAD_FILES_PATH, fileName, file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (!result.equals("true")) {
                R.error(result);
            }
            UPLOAD_FILES_PATH += fileName;
            projectAttachEntity = new ProjectAttachEntity();
            projectAttachEntity.setAttachPath(UPLOAD_FILES_PATH);
            projectAttachEntity.setAttachName(fileName);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            projectAttachEntity.setAttachTime(df.format(new Date()));// new Date()为获取当前系统时间
        }
        return R.ok("文件上传成功")
                .put("projectAttachEntity", projectAttachEntity);
    }
    /**
     * 文件上传
     * @param file
     * @param request
     * @return
     */
    @PostMapping(value = "/upfile")
    @RequiresPermissions("innovate:project:save")
    public Object upload(@RequestParam("file") MultipartFile file, final HttpServletRequest request) {
        String projectName = request.getParameter("projectName");
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + projectName + "/" + RandomUtils.getRandomNums() + "/";
        if (Objects.isNull(file) || file.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }
        String fileName = file.getOriginalFilename();
        String result = null;
        try {
            result = FileUtils.upLoad(UPLOAD_FILES_PATH, fileName, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (!result.equals("true")) {
            R.error(result);
        }
        UPLOAD_FILES_PATH += fileName;
        return R.ok("文件上传成功")
                .put("filePath", UPLOAD_FILES_PATH);
    }

    /**
     * 文件下载
     */
    @PostMapping(value = "/download")
    @RequiresPermissions("innovate:project:list")
    public void downloadFile(final HttpServletResponse response, final HttpServletRequest request) {
        String filePath = request.getParameter("filePath");
        FileUtils.download(response, filePath);
    }

    /**
     * 模板下载
     */
    @PostMapping(value = "/template")
    @RequiresPermissions("innovate:project:list")
    public void downloadTemplate(final HttpServletResponse response, final HttpServletRequest request) {
        String fileName = request.getParameter("fileName");
        String realPath = ConfigApi.TEMPLATE_URL + fileName;
        FileUtils.download(response, realPath);
    }
}
