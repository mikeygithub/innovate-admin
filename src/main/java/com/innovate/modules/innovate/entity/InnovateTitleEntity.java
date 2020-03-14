package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("innovate_sys_title")
public class InnovateTitleEntity {

    @TableId
    private Long titleId;
    private String titleName;
}
