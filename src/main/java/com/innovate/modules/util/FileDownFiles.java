package com.innovate.modules.util;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;


/**
 * @ClassName: FileUpload
 * @Description: 关于文件的操作
 * @author tz
 * @date 2017年8月23日
 */
@Slf4j
public class FileDownFiles {

	/**
	 * 文件下载核心
	 *
	 * @param response
	 * @param realPath
	 */
	public static void download(final HttpServletResponse response, String realPath) {
		File file = new File(realPath);
		if (file.exists()) {
			response.setHeader("content-type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			// 下载文件能正常显示中文
//			try {
//				response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
//			} catch (UnsupportedEncodingException e) {
//				e.printStackTrace();
//			}

			// 实现文件下载
			byte[] buffer = new byte[1024];
			FileInputStream fis = null;
			BufferedInputStream bis = null;
			try {
				fis = new FileInputStream(file);
				bis = new BufferedInputStream(fis);
				OutputStream os = response.getOutputStream();
				int i = bis.read(buffer);
				while (i != -1) {
					os.write(buffer, 0, i);
					i = bis.read(buffer);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
