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
 * 实体[计划模板详情]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZ_PLANTEMPLETDETAIL", resultMap = "IbzPlanTempletDetailResultMap")
@ApiModel("计划模板详情")
public class IbzPlanTempletDetail extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 类型
     */
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("类型")
    private String type;
    /**
     * 计划编号
     */
    @TableField(value = "`plancode`")
    @JSONField(name = "plancode")
    @JsonProperty("plancode")
    @ApiModelProperty("计划编号")
    private String plancode;
    /**
     * 计划名称
     */
    @TableField(value = "`desc`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("计划名称")
    private String desc;
    /**
     * 排序
     */
    @DEField(defaultValue = "10")
    @TableField(value = "`order`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * 计划模板详情标识
     */
    @DEField(name = "ibz_plantempletdetailid", isKeyField = true)
    @TableId(value = "ibz_plantempletdetailid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "ibzplantempletdetailid")
    @JsonProperty("ibzplantempletdetailid")
    @ApiModelProperty("计划模板详情标识")
    private String ibzplantempletdetailid;
    /**
     * 计划模板详情名称
     */
    @DEField(name = "ibz_plantempletdetailname")
    @TableField(value = "`ibz_plantempletdetailname`")
    @JSONField(name = "ibzplantempletdetailname")
    @JsonProperty("ibzplantempletdetailname")
    @ApiModelProperty("计划模板详情名称")
    private String ibzplantempletdetailname;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 描述
     */
    @TableField(value = "`expect`")
    @JSONField(name = "expect")
    @JsonProperty("expect")
    @ApiModelProperty("描述")
    private String expect;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 产品计划模板标识
     */
    @TableField(value = "`plantempletid`")
    @JSONField(name = "plantempletid")
    @JsonProperty("plantempletid")
    @ApiModelProperty("产品计划模板标识")
    private String plantempletid;

    /**
     * 计划模板标识
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibizpro.domain.IbzPlanTemplet ibzplantemplet;



    /**
     * 设置 [类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [计划编号]
     */
    public void setPlancode(String plancode) {
        this.plancode = plancode;
        this.modify("plancode", plancode);
    }

    /**
     * 设置 [计划名称]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [计划模板详情名称]
     */
    public void setIbzplantempletdetailname(String ibzplantempletdetailname) {
        this.ibzplantempletdetailname = ibzplantempletdetailname;
        this.modify("ibz_plantempletdetailname", ibzplantempletdetailname);
    }

    /**
     * 设置 [描述]
     */
    public void setExpect(String expect) {
        this.expect = expect;
        this.modify("expect", expect);
    }

    /**
     * 设置 [产品计划模板标识]
     */
    public void setPlantempletid(String plantempletid) {
        this.plantempletid = plantempletid;
        this.modify("plantempletid", plantempletid);
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
        this.reset("ibz_plantempletdetailid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


