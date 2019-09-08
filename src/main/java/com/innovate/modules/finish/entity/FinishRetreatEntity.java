package com.innovate.modules.finish.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:大创结题流程回退
 **/
@Data
@TableName("innovate_finish_retreat")
public class FinishRetreatEntity implements Serializable {
    @TableId
    private Long retreatId;
    private Long finishId;
    private Long userId;
    private String apply;
    private Long applyStatus;
    private String retreatOption;
    private Long hasUpdate;
    private Long isDel;
}
