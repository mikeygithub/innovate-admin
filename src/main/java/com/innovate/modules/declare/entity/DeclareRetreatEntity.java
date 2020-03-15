package com.innovate.modules.declare.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:大创申报流程回退
 **/
@Data
@TableName("innovate_declare_retreat")
public class DeclareRetreatEntity implements Serializable {
    @TableId
    private Long retreatId;
    private Long declareId;
    private Long userId;
    private String apply;
    private Long applyStatus;
    private String retreatOption;
    private Long hasUpdate;
    private Long isDel;
}
