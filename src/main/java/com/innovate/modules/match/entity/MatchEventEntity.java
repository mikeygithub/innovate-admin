package com.innovate.modules.match.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;

/**
 * @author:tz
 * @create:2018-12-15
 * @description:赛事信息
 **/
@Data
@TableName("innovate_match_event")
public class MatchEventEntity implements Serializable {
    @TableId
    private Long eventId;
    private String eventName;
    private Long eventPeople;
    private Long eventAwardNum;
    private Long eventProjectNum;
    @JsonSerialize(using= ToStringSerializer.class)
    private Double eventAwardMoney;
    private String attachName;
    private String attachPath;
    private String fileAskContent;
}
