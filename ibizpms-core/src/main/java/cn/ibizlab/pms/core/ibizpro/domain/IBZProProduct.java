package cn.ibizlab.pms.core.ibizpro.domain;

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
 * 实体[平台产品]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_product", resultMap = "IBZProProductResultMap")
@ApiModel("平台产品")
public class IBZProProduct extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * IBIZ标识
     */
    @DEField(name = "ibiz_id")
    @TableField(value = "`IBIZ_ID`")
    @JSONField(name = "ibizid")
    @JsonProperty("ibizid")
    @ApiModelProperty("IBIZ标识")
    private String ibizid;
    /**
     * 产品代号
     */
    @TableField(value = "`CODE`")
    @JSONField(name = "code")
    @JsonProperty("code")
    @ApiModelProperty("产品代号")
    private String code;
    /**
     * 产品名称
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("产品名称")
    private String name;
    /**
     * 编号
     */
    @DEField(defaultValue = "0", isKeyField = true)
    @TableId(value = "`ID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;



    /**
     * 设置 [IBIZ标识]
     */
    public void setIbizid(String ibizid) {
        this.ibizid = ibizid;
        this.modify("ibiz_id", ibizid);
    }

    /**
     * 设置 [产品代号]
     */
    public void setCode(String code) {
        this.code = code;
        this.modify("code", code);
    }

    /**
     * 设置 [产品名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }


    @Override
    public Serializable getDefaultKey(boolean gen) {
        return IdWorker.getId();
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


