package com.innovate.modules.common.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description
 * @date 2019/9/20
 */
@Data
public class CommonAttachments implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;

    private String name;

    private Integer percentage;

    private Integer size;

    private Long uid;

    private CommonFile response;

    private String url;

}
