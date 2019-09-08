package com.innovate.modules.match.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:参赛项目流程回退
 **/
@Data
@TableName("innovate_match_retreat")
public class MatchRetreatEntity implements Serializable {
    @TableId
    private Long retreatId;
    private Long matchId;
    private Long userId;
    private String apply;
    private Long applyStatus;
    private String retreatOption;
    private Long hasUpdate;
    private Long isDel;
}
