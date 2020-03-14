package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:大创申报申请修改
 **/
@Data
@TableName("innovate_declare_apply_update")
public class DeclareApplyUpdateEntity implements Serializable {
    @TableId
    private Long applyId;
    private Long declareId;
    private String reason;
    private Long result;
    private String applyOption;
    private Long isDel;
}
