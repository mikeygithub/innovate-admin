package com.innovate.modules.match.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-19
 * @description:参赛项目附件
 **/
@Data
@TableName("innovate_match_attach")
public class MatchAttachEntity implements Serializable {
    @TableId
    private Long attachId;
    private Long matchId;
    private String attachName;
    private String attachPath;
    private Long isDel;
}
