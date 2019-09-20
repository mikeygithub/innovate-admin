package com.innovate.modules.common.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author spring
 * email: 4298293220@qq.com
 * site: https://springbless.xin
 * @description
 * @date 2019/9/20
 */
@Data
public class CommonFile implements Serializable {

    private static final long serialVersionUID = 1L;

    private String msg;

    private Integer code;

    private String data;

}
