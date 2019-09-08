package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-19
 * @description:大创申报项目附件
 **/
@Data
@TableName("innovate_declare_attach")
public class DeclareAttachEntity implements Serializable {
    @TableId
    private Long attachId;
    private Long declareId;
    private String attachName;
    private String attachPath;
    private Long isDel;
}
