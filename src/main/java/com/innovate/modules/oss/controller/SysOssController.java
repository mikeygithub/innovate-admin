package com.innovate.modules.oss.controller;

import com.google.gson.Gson;
import com.innovate.common.exception.RRException;
import com.innovate.common.utils.ConfigConstant;
import com.innovate.common.utils.Constant;
import com.innovate.common.utils.PageUtils;
import com.innovate.common.utils.R;
import com.innovate.common.validator.ValidatorUtils;
import com.innovate.common.validator.group.AliyunGroup;
import com.innovate.common.validator.group.QcloudGroup;
import com.innovate.common.validator.group.QiniuGroup;
import com.innovate.modules.innovate.config.ConfigApi;
import com.innovate.modules.oss.cloud.CloudStorageConfig;
import com.innovate.modules.oss.cloud.OSSFactory;
import com.innovate.modules.oss.entity.SysOssEntity;
import com.innovate.modules.oss.service.SysOssService;
import com.innovate.modules.sys.service.SysConfigService;
import com.innovate.modules.util.FileUtils;
import com.innovate.modules.util.ZipUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * 文件上传
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-25 12:13:26
 */
@RestController
@RequestMapping("sys/oss")
public class SysOssController {
	@Autowired
	private SysOssService sysOssService;
    @Autowired
    private SysConfigService sysConfigService;

    private final static String KEY = ConfigConstant.CLOUD_STORAGE_CONFIG_KEY;


	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:oss:all")
	public R list(@RequestParam Map<String, Object> params){
		PageUtils page = sysOssService.queryPage(params);

		return R.ok().put("page", page);
	}


    /**
     * 云存储配置信息
     */
    @GetMapping("/config")
    @RequiresPermissions("sys:oss:all")
    public R config(){
        CloudStorageConfig config = sysConfigService.getConfigObject(KEY, CloudStorageConfig.class);

        return R.ok().put("config", config);
    }


	/**
	 * 保存云存储配置信息
	 */
	@PostMapping("/saveConfig")
	@RequiresPermissions("sys:oss:all")
	public R saveConfig(@RequestBody CloudStorageConfig config){
		//校验类型
		ValidatorUtils.validateEntity(config);

		if(config.getType() == Constant.CloudService.QINIU.getValue()){
			//校验七牛数据
			ValidatorUtils.validateEntity(config, QiniuGroup.class);
		}else if(config.getType() == Constant.CloudService.ALIYUN.getValue()){
			//校验阿里云数据
			ValidatorUtils.validateEntity(config, AliyunGroup.class);
		}else if(config.getType() == Constant.CloudService.QCLOUD.getValue()){
			//校验腾讯云数据
			ValidatorUtils.validateEntity(config, QcloudGroup.class);
		}

        sysConfigService.updateValueByKey(KEY, new Gson().toJson(config));

		return R.ok();
	}
	

	/**
	 * 上传文件
	 */
	@PostMapping("/upload")
	@RequiresPermissions("sys:oss:all")
	public R upload(@RequestParam("file") MultipartFile file) throws Exception {
		if (file.isEmpty()) {
			throw new RRException("上传文件不能为空");
		}

		//上传文件
		String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		String url = OSSFactory.build().uploadSuffix(file.getBytes(), suffix);

		//保存文件信息
		SysOssEntity ossEntity = new SysOssEntity();
		ossEntity.setUrl(url);
		ossEntity.setCreateDate(new Date());
		sysOssService.insert(ossEntity);

		return R.ok().put("url", url);
	}


	/**
	 * 删除
	 */
	@PostMapping("/delete")
	@RequiresPermissions("sys:oss:all")
	public R delete(@RequestBody Long[] ids){
		sysOssService.deleteBatchIds(Arrays.asList(ids));

		return R.ok();
	}

	/**
	 * 下载服务器所有附件
	 * @param response
	 * @param request
	 */
	@PostMapping(value = "/download")
	@RequiresPermissions("sys:oss:all")
	public void downloadFile(final HttpServletResponse response, final HttpServletRequest request) {
		try {
			ZipUtils.toZip(ConfigApi.UPLOAD_URL,response.getOutputStream(),true);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 删除所有附件
	 */
	@PostMapping(value = "/del")
	@RequiresPermissions("sys:oss:all")
	public R deleteServerFile() {


		logger.warn("执行删除服务器附件");
		//谨慎操作
		FileUtils.deleteTotal(ConfigApi.UPLOAD_URL);

		return R.ok();
	}

}
