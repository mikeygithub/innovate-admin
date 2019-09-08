package com.innovate.modules.innovate.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * @author: 尧志欣
 * Email：1160086364@qq.com
 * Date: 2018/12/5
 **/
@Data
@TableName("innovate_review_group_user")
public class InnovateReviewGroupUserEntity {
    @TableId
    private Long groupUserId;
    private Long groupId;
    private Long userId;
}
