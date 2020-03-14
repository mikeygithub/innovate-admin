package com.innovate.modules.finish.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-19
 * @description:大创结题项目附件
 **/
@Data
@TableName("innovate_finish_attach")
public class FinishAttachEntity implements Serializable {
    @TableId
    private Long attachId;
    private Long finishId;
    private String attachName;
    private String attachPath;
    private Long isDel;
}
