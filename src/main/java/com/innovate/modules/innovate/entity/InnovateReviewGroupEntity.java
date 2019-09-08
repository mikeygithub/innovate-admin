package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.util.List;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/5
 **/
@Data
@TableName("innovate_review_group")
public class InnovateReviewGroupEntity {
    @TableId
    private Long groupId;
    private String groupName;
    private String groupContent;
    @TableField(exist = false)
    private List<InnovateReviewGroupUserEntity> innovateReviewGroupUserEntities;
}
