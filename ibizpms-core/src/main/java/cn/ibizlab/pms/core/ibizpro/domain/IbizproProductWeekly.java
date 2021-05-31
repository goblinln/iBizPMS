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
 * 实体[产品周报]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBIZPRO_PRODUCTWEEKLY", resultMap = "IbizproProductWeeklyResultMap")
@ApiModel("产品周报")
public class IbizproProductWeekly extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 产品负责人
     */
    @TableField(value = "`PO`")
    @JSONField(name = "po")
    @JsonProperty("po")
    @ApiModelProperty("产品负责人")
    private String po;
    /**
     * 产品周报标识
     */
    @DEField(name = "ibizpro_productweeklyid", isKeyField = true)
    @TableId(value = "`IBIZPRO_PRODUCTWEEKLYID`", type = IdType.ASSIGN_ID)
    @JSONField(name = "ibizpro_productweeklyid")
    @JsonProperty("ibizpro_productweeklyid")
    @ApiModelProperty("产品周报标识")
    private Long ibizproProductweeklyid;
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
     * 任务
     */
    @TableField(value = "`TASKS`")
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    @ApiModelProperty("任务")
    private String tasks;
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
     * 日期
     */
    @TableField(value = "`DATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
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
     * 产品周报名称
     */
    @DEField(name = "ibizpro_productdailyname")
    @TableField(value = "`IBIZPRO_PRODUCTDAILYNAME`")
    @JSONField(name = "ibizproproductweeklyname")
    @JsonProperty("ibizproproductweeklyname")
    @ApiModelProperty("产品周报名称")
    private String ibizproproductweeklyname;
    /**
     * 开始统计
     */
    @TableField(value = "`BEGINDATESTATS`")
    @JSONField(name = "begindatestats")
    @JsonProperty("begindatestats")
    @ApiModelProperty("开始统计")
    private String begindatestats;
    /**
     * 总工时
     */
    @TableField(value = "`TOTALESTIMATES`")
    @JSONField(name = "totalestimates")
    @JsonProperty("totalestimates")
    @ApiModelProperty("总工时")
    private Double totalestimates;
    /**
     * 结束统计
     */
    @TableField(value = "`ENDDATESTATS`")
    @JSONField(name = "enddatestats")
    @JsonProperty("enddatestats")
    @ApiModelProperty("结束统计")
    private String enddatestats;
    /**
     * 产品名称
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("产品名称")
    private String productname;
    /**
     * 编号
     */
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("编号")
    private Long product;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product productweekly;



    /**
     * 设置 [产品负责人]
     */
    public void setPo(String po) {
        this.po = po;
        this.modify("po", po);
    }

    /**
     * 设置 [任务]
     */
    public void setTasks(String tasks) {
        this.tasks = tasks;
        this.modify("tasks", tasks);
    }

    /**
     * 设置 [日期]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [日期]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
    /**
     * 设置 [产品周报名称]
     */
    public void setIbizproproductweeklyname(String ibizproproductweeklyname) {
        this.ibizproproductweeklyname = ibizproproductweeklyname;
        this.modify("ibizpro_productdailyname", ibizproproductweeklyname);
    }

    /**
     * 设置 [开始统计]
     */
    public void setBegindatestats(String begindatestats) {
        this.begindatestats = begindatestats;
        this.modify("begindatestats", begindatestats);
    }

    /**
     * 设置 [总工时]
     */
    public void setTotalestimates(Double totalestimates) {
        this.totalestimates = totalestimates;
        this.modify("totalestimates", totalestimates);
    }

    /**
     * 设置 [结束统计]
     */
    public void setEnddatestats(String enddatestats) {
        this.enddatestats = enddatestats;
        this.modify("enddatestats", enddatestats);
    }

    /**
     * 设置 [编号]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
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
        this.reset("ibizpro_productweeklyid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


