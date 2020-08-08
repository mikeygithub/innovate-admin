package com.innovate.modules.match.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.match.entity.MatchAttachEntity;
import com.innovate.modules.match.service.MatchAttachService;
import com.innovate.modules.match.service.MatchAwardService;
import com.innovate.modules.sys.controller.AbstractController;
import com.innovate.modules.util.FileUtils;
import com.innovate.modules.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/5
 * 附件Controller
 **/
@RestController
@RequestMapping("innovate/match/attach")
@Slf4j
public class MatchAttachController extends AbstractController {

    @Autowired
    private MatchAttachService matchAttachService;
    @Autowired
    private MatchAwardService matchAwardService;

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @PostMapping(value = "/upload")
    @RequiresPermissions("innovate:match:save")
    public Object uploadFile(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) {
        String matchName = request.getParameter("matchName");
        if (matchName==null||matchName.equals(""))matchName="MatchNameISNull";
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + matchName + "/" + RandomUtils.getRandomNums() + "/";
        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }
        MatchAttachEntity matchAttachEntity = null;
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
            matchAttachEntity = new MatchAttachEntity();
            matchAttachEntity.setAttachPath(UPLOAD_FILES_PATH);
            matchAttachEntity.setAttachName(fileName);
        }
        return R.ok("文件上传成功")
                .put("matchAttachEntity", matchAttachEntity);
    }


    /**
     * 获奖证书上传
     * @param files
     * @param request
     * @return
     */
    @PostMapping(value = "/uploadfile")
    @RequiresPermissions("innovate:match:save")
    public Object uploadAwardFile(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) {
        String matchName = request.getParameter("matchName");
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + matchName + "/" + RandomUtils.getRandomNums() + "/";
        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }
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
        }
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

}
