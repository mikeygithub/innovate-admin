package com.innovate.modules.match.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-11-08
 * @description:项目指导老师
 **/
@Data
@TableName("innovate_match_teacher")
public class MatchTeacherEntity implements Serializable {
    @TableId
    private Long matchTeacherId;
    private Long matchId;
    private Long userId;
    private Long isDel;
}
