package com.innovate.modules.enterprise.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.innovate.modules.enterprise.entity.EntTeacherAttachmentEntity;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.innovate.entity.UserPersonInfoEntity;
import com.innovate.modules.innovate.entity.UserTeacherInfoEntity;
import com.innovate.modules.innovate.service.UserPerInfoService;
import com.innovate.modules.util.FileDownFiles;
import com.innovate.modules.util.RandomUtils;
import com.innovate.modules.util.UpLoadFileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innovate.modules.enterprise.entity.EntStudentAttachmentEntity;
import com.innovate.modules.enterprise.service.EntStudentAttachmentService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 学生附件表
 * @author soldier
 * @email 583403411@qq.com
 * @date 2019-10-11 20:55:46
 */
@RestController
@RequestMapping("enterprise/student/attachment")
public class EntStudentAttachmentController {
    @Autowired
    private EntStudentAttachmentService entStudentAttachmentService;
    @Autowired
    private UserPerInfoService userPerInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("enterprise:student:attachment:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = entStudentAttachmentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{stuAttachmentId}")
    @RequiresPermissions("enterprise:student:attachment:list")
    public R info(@PathVariable("stuAttachmentId") Long stuAttachmentId){
		EntStudentAttachmentEntity entStudentAttachment = entStudentAttachmentService.selectById(stuAttachmentId);

        return R.ok().put("entStudentAttachment", entStudentAttachment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("enterprise:student:attachment:save")
    public R save(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) {

        String userPerId = request.getParameter("userPerId");

        UserPersonInfoEntity userPersonInfoEntity = userPerInfoService.queryByUserId(Long.parseLong(userPerId));

        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + userPersonInfoEntity.getSysUserEntity().getName() + "/"+ RandomUtils.getRandomNums()+"/";

        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }

        EntStudentAttachmentEntity entStudentAttachmentEntity = null;

        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            String result = null;
            try {
                result = UpLoadFileUtils.upLoad(UPLOAD_FILES_PATH, fileName, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result.equals("true")) {
                R.error(result);
            }
            UPLOAD_FILES_PATH += fileName;
            entStudentAttachmentEntity = new EntStudentAttachmentEntity();
            entStudentAttachmentEntity.setStuAttachmentName(fileName);
            entStudentAttachmentEntity.setStuAttachmentUrl(UPLOAD_FILES_PATH);
            entStudentAttachmentEntity.setUserPerId(userPersonInfoEntity.getUserPerId());
        }
        return R.ok("文件上传成功").put("entStudentAttachmentEntity", entStudentAttachmentEntity);

    }

    /**
     * 文件下载
     */
    @PostMapping(value = "/download")
    public void downloadFile(final HttpServletResponse response, final HttpServletRequest request) {
        String filePath = request.getParameter("filePath");
        FileDownFiles.download(response, filePath);
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("enterprise:student:attachment:update")
    public R update(@RequestBody EntStudentAttachmentEntity entStudentAttachment){
		entStudentAttachmentService.updateById(entStudentAttachment);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("enterprise:student:attachment:delete")
    public R delete(@RequestBody Long[] stuAttachmentIds){
		entStudentAttachmentService.deleteBatchIds(Arrays.asList(stuAttachmentIds));

        return R.ok();
    }

}
