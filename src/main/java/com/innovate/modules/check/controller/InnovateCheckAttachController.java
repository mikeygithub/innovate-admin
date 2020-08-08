package com.innovate.modules.check.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.innovate.modules.declare.entity.DeclareInfoEntity;
import com.innovate.modules.declare.service.DeclareInfoService;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.util.FileUtils;
import com.innovate.modules.util.RandomUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.innovate.modules.check.entity.InnovateCheckAttachEntity;
import com.innovate.modules.check.service.InnovateCheckAttachService;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 中期检查附件表
 *
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@RestController
@RequestMapping("innovate/check/attach")
public class InnovateCheckAttachController {
    @Autowired
    private InnovateCheckAttachService innovateCheckAttachService;

    @Autowired
    private DeclareInfoService declareInfoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("check:attach:list")
    public R list(@RequestParam Map<String, Object> params){

        PageUtils page = innovateCheckAttachService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attachId}")
    @RequiresPermissions("check:attach:info")
    public R info(@PathVariable("attachId") Long attachId){
		InnovateCheckAttachEntity innovateCheckAttach = innovateCheckAttachService.selectById(attachId);

        return R.ok().put("innovateCheckAttach", innovateCheckAttach);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("check:attach:save")
    public R save(@RequestBody InnovateCheckAttachEntity innovateCheckAttach){
		innovateCheckAttachService.insert(innovateCheckAttach);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("check:attach:update")
    public R update(@RequestBody InnovateCheckAttachEntity innovateCheckAttach){
		innovateCheckAttachService.updateById(innovateCheckAttach);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("check:attach:delete")
    public R delete(@RequestBody Long[] attachIds){
		innovateCheckAttachService.deleteBatchIds(Arrays.asList(attachIds));

        return R.ok();
    }
    /**
     * 文件上传
     * @param files
     * @param request
     * @return
     */
    @PostMapping(value = "/upload")
    @RequiresPermissions("check:attach:save")
    public Object uploadFile(@RequestParam("file") List<MultipartFile> files, HttpServletRequest request) {

        String declareId = request.getParameter("declareId");

        DeclareInfoEntity declareInfoEntity = declareInfoService.queryById(Long.parseLong(declareId));

        String UPLOAD_FILES_PATH = ConfigApi.UPLOAD_URL + declareInfoEntity.getDeclareName() + "/"+ RandomUtils.getRandomNums()+"/";

        if (Objects.isNull(files) || files.isEmpty()) {
            return R.error("文件为空，请重新上传");
        }

        InnovateCheckAttachEntity innovateCheckAttachEntity = null;

        for(MultipartFile file : files){
            String fileName = file.getOriginalFilename();
            String result = null;
            try {
                result = FileUtils.upLoad(UPLOAD_FILES_PATH, fileName, file);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!result.equals("true")) {
                R.error(result);
            }
            UPLOAD_FILES_PATH += fileName;
            innovateCheckAttachEntity = new InnovateCheckAttachEntity();
            innovateCheckAttachEntity.setAttachPath(UPLOAD_FILES_PATH);
            innovateCheckAttachEntity.setAttachName(fileName);
            innovateCheckAttachEntity.setCheckId(Long.parseLong(request.getParameter("checkId")));
            innovateCheckAttachEntity.setIsDel(0);
        }
        return R.ok("文件上传成功")
                .put("checkAttachEntity", innovateCheckAttachEntity);
    }

    /**
     * 文件下载
     */
    @PostMapping(value = "/download")
    @RequiresPermissions("innovate:check:list")
    public void downloadFile(final HttpServletResponse response, final HttpServletRequest request) {
        String filePath = request.getParameter("filePath");
        FileUtils.download(response, filePath);
    }
}
