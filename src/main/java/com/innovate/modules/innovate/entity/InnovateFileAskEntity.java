package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 上传文件要求表
 * 
 * @author Mikey
 * @email 1625017540@qq.com
 * @date 2019-09-18 22:20:42
 */
@Data
@TableName("innovate_file_ask")
public class InnovateFileAskEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 上传文件要求
	 */
	@TableId
	private Long fileAskId;
	/**
	 * 类型：0 大创,1 中期检查,2 赛事,3 结题
	 */
	private Integer fileAskType;
	/**
	 * 要求
	 */
	private String fileAskContent;
	/**
	 * 年度
	 */
	private Date fileAskTime;
	/**
	 * 删除标识
	 */
	private Integer isDel;

}
