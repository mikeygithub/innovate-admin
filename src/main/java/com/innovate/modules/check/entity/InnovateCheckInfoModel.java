package com.innovate.modules.check.entity;

import com.innovate.modules.declare.entity.DeclareInfoEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ProjectName innovate-admin
 * @Author 麦奇
 * @Email biaogejiushibiao@outlook.com
 * @Date 9/20/19 10:40 AM
 * @Version 1.0
 * @Description:
 **/
@Data
public class InnovateCheckInfoModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private InnovateCheckInfoEntity innovateCheckInfoEntity;

    private List<InnovateCheckAttachEntity> innovateCheckAttachEntities;

    private DeclareInfoEntity declareInfoEntity;

}
