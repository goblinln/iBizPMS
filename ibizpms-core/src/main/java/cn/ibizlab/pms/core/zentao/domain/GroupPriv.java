package cn.ibizlab.pms.core.zentao.domain;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.math.BigInteger;
import java.util.HashMap;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.ObjectUtils;
import org.springframework.util.DigestUtils;
import cn.ibizlab.pms.util.domain.EntityBase;
import cn.ibizlab.pms.util.annotation.DEField;
import cn.ibizlab.pms.util.enums.DEPredefinedFieldType;
import cn.ibizlab.pms.util.enums.DEFieldDefaultValueType;
import cn.ibizlab.pms.util.helper.DataObject;
import cn.ibizlab.pms.util.enums.DupCheck;
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;
import cn.ibizlab.pms.util.annotation.Audit;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[群组权限]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_grouppriv", resultMap = "GroupPrivResultMap")
@ApiModel("群组权限")
public class GroupPriv extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * group
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`GROUP`")
    @JSONField(name = "group")
    @JsonProperty("group")
    @ApiModelProperty("group")
    private Integer group;
    /**
     * method
     */
    @TableField(value = "`METHOD`")
    @JSONField(name = "method")
    @JsonProperty("method")
    @ApiModelProperty("method")
    private String method;
    /**
     * 主键
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_UUID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("主键")
    private String id;
    /**
     * module
     */
    @TableField(value = "`MODULE`")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("module")
    private String module;



    /**
     * 设置 [group]
     */
    public void setGroup(Integer group) {
        this.group = group;
        this.modify("group", group);
    }

    /**
     * 设置 [method]
     */
    public void setMethod(String method) {
        this.method = method;
        this.modify("method", method);
    }

    /**
     * 设置 [module]
     */
    public void setModule(String module) {
        this.module = module;
        this.modify("module", module);
    }


    /**
     * 复制当前对象数据到目标对象(粘贴重置)
     * @param targetEntity 目标数据对象
     * @param bIncEmpty  是否包括空值
     * @param <T>
     * @return
     */
    @Override
    public <T> T copyTo(T targetEntity, boolean bIncEmpty) {
        this.reset("id");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


