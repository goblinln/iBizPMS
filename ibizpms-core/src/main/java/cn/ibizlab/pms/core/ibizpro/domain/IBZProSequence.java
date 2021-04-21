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
 * 实体[业务序列表]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZPRO_SEQUENCE", resultMap = "IBZProSequenceResultMap")
@ApiModel("业务序列表")
public class IBZProSequence extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`CREATEDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;
    /**
     * 是否启用
     */
    @TableField(value = "`ISENABLE`")
    @JSONField(name = "isenable")
    @JsonProperty("isenable")
    @ApiModelProperty("是否启用")
    private Integer isenable;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`UPDATEDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;
    /**
     * 组织部门标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPTID`")
    @JSONField(name = "deptid")
    @JsonProperty("deptid")
    @ApiModelProperty("组织部门标识")
    private String deptid;
    /**
     * 自增数值
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`INCREMENT`")
    @JSONField(name = "increment")
    @JsonProperty("increment")
    @ApiModelProperty("自增数值")
    private Integer increment;
    /**
     * 业务序列表标识
     */
    @DEField(name = "ibzpro_sequenceid", isKeyField = true)
    @TableId(value = "`IBZPRO_SEQUENCEID`", type = IdType.ASSIGN_UUID)
    @JSONField(name = "ibzprosequenceid")
    @JsonProperty("ibzprosequenceid")
    @ApiModelProperty("业务序列表标识")
    private String ibzprosequenceid;
    /**
     * 开始序列号
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`BEGINSEQ`")
    @JSONField(name = "beginseq")
    @JsonProperty("beginseq")
    @ApiModelProperty("开始序列号")
    private Long beginseq;
    /**
     * 年份
     */
    @TableField(value = "`YEAR`")
    @JSONField(name = "year")
    @JsonProperty("year")
    @ApiModelProperty("年份")
    private String year;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`UPDATEMAN`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @ApiModelProperty("更新人")
    private String updateman;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`CREATEMAN`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @ApiModelProperty("建立人")
    private String createman;
    /**
     * 当前序列号
     */
    @TableField(value = "`CURSEQ`")
    @JSONField(name = "curseq")
    @JsonProperty("curseq")
    @ApiModelProperty("当前序列号")
    private Long curseq;
    /**
     * 业务序列表名称
     */
    @DEField(name = "ibzpro_sequencename")
    @TableField(value = "`IBZPRO_SEQUENCENAME`")
    @JSONField(name = "ibzprosequencename")
    @JsonProperty("ibzprosequencename")
    @ApiModelProperty("业务序列表名称")
    private String ibzprosequencename;
    /**
     * 组织机构标识
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORGID`")
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("组织机构标识")
    private String orgid;
    /**
     * 业务类别
     */
    @TableField(value = "`CATEGORY`")
    @JSONField(name = "category")
    @JsonProperty("category")
    @ApiModelProperty("业务类别")
    private String category;



    /**
     * 设置 [是否启用]
     */
    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
        this.modify("isenable", isenable);
    }

    /**
     * 设置 [自增数值]
     */
    public void setIncrement(Integer increment) {
        this.increment = increment;
        this.modify("increment", increment);
    }

    /**
     * 设置 [开始序列号]
     */
    public void setBeginseq(Long beginseq) {
        this.beginseq = beginseq;
        this.modify("beginseq", beginseq);
    }

    /**
     * 设置 [年份]
     */
    public void setYear(String year) {
        this.year = year;
        this.modify("year", year);
    }

    /**
     * 设置 [当前序列号]
     */
    public void setCurseq(Long curseq) {
        this.curseq = curseq;
        this.modify("curseq", curseq);
    }

    /**
     * 设置 [业务序列表名称]
     */
    public void setIbzprosequencename(String ibzprosequencename) {
        this.ibzprosequencename = ibzprosequencename;
        this.modify("ibzpro_sequencename", ibzprosequencename);
    }

    /**
     * 设置 [业务类别]
     */
    public void setCategory(String category) {
        this.category = category;
        this.modify("category", category);
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
        this.reset("ibzpro_sequenceid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


