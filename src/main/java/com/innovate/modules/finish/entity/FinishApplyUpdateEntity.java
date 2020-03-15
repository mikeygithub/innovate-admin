package com.innovate.modules.finish.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:大创结题申请修改
 **/
@Data
@TableName("innovate_finish_apply_update")
public class FinishApplyUpdateEntity implements Serializable {
    @TableId
    private Long applyId;
    private Long finishId;
    private String reason;
    private Long result;
    private String applyOption;
    private Long isDel;
}
