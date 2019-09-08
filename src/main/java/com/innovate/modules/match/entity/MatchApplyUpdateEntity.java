package com.innovate.modules.match.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:参赛项目申请修改
 **/
@Data
@TableName("innovate_match_apply_update")
public class MatchApplyUpdateEntity implements Serializable {
    @TableId
    private Long applyId;
    private Long matchId;
    private String reason;
    private Long result;
    private String applyOption;
    private Long isDel;
}
