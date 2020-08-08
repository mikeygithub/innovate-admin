package com.innovate.modules.finish.controller;

import com.innovate.common.utils.R;
import com.innovate.modules.finish.entity.FinishAttachEntity;
import com.innovate.modules.finish.service.FinishAttachService;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.sys.controller.AbstractController;
import com.innovate.modules.util.FileUtils;
import com.innovate.modules.util.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/5
 * 附件Controller
 **/
@RestController
@RequestMapping("innovate/finish/attach")
@Slf4j
public class FinishAttachController extends AbstractController {

    @Autowired
    private FinishAttachService finishAttachService;

    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @PostMapping(value = "/upload")
    @RequiresPermissions("innovate:finish:save")
    public Object uploadFile(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) {
        String finishName = request.getParameter("finishName");
        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + finishName + "/"+ RandomUtils.getRandomNums()+"/";
        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }
        FinishAttachEntity finishAttachEntity = null;
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

            finishAttachEntity = new FinishAttachEntity();
            finishAttachEntity.setAttachPath(UPLOAD_FILES_PATH);
            finishAttachEntity.setAttachName(fileName);
        }
        return R.ok("文件上传成功")
                .put("finishAttachEntity", finishAttachEntity);
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
