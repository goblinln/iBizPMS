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
 * 实体[项目日报]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBIZPRO_PROJECTDAILY", resultMap = "IbizproProjectDailyResultMap")
@ApiModel("项目日报")
public class IbizproProjectDaily extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目日报名称
     */
    @DEField(name = "ibizpro_projectdailyname")
    @TableField(value = "`IBIZPRO_PROJECTDAILYNAME`")
    @JSONField(name = "ibizproprojectdailyname")
    @JsonProperty("ibizproprojectdailyname")
    @ApiModelProperty("项目日报名称")
    private String ibizproprojectdailyname;
    /**
     * 任务
     */
    @TableField(value = "`TASKS`")
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    @ApiModelProperty("任务")
    private String tasks;
    /**
     * 开始日期
     */
    @TableField(value = "`BEGIN`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始日期")
    private Timestamp begin;
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
     * 结束日期
     */
    @TableField(value = "`END`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束日期")
    private Timestamp end;
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
     * 日期
     */
    @TableField(value = "`DATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;
    /**
     * 总工时
     */
    @TableField(value = "`TOTALESTIMATES`")
    @JSONField(name = "totalestimates")
    @JsonProperty("totalestimates")
    @ApiModelProperty("总工时")
    private Double totalestimates;
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
     * 项目负责人
     */
    @TableField(value = "`PM`")
    @JSONField(name = "pm")
    @JsonProperty("pm")
    @ApiModelProperty("项目负责人")
    private String pm;
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
     * 项目日报标识
     */
    @DEField(name = "ibizpro_projectdailyid", isKeyField = true)
    @TableId(value = "`IBIZPRO_PROJECTDAILYID`", type = IdType.ASSIGN_UUID)
    @JSONField(name = "ibizproprojectdailyid")
    @JsonProperty("ibizproprojectdailyid")
    @ApiModelProperty("项目日报标识")
    private String ibizproprojectdailyid;
    /**
     * 项目名称
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @ApiModelProperty("项目名称")
    private String projectname;
    /**
     * 项目编号
     */
    @TableField(value = "`PROJECT`")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目编号")
    private Long project;

    /**
     * 项目
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project ztproject;



    /**
     * 设置 [项目日报名称]
     */
    public void setIbizproprojectdailyname(String ibizproprojectdailyname) {
        this.ibizproprojectdailyname = ibizproprojectdailyname;
        this.modify("ibizpro_projectdailyname", ibizproprojectdailyname);
    }

    /**
     * 设置 [任务]
     */
    public void setTasks(String tasks) {
        this.tasks = tasks;
        this.modify("tasks", tasks);
    }

    /**
     * 设置 [开始日期]
     */
    public void setBegin(Timestamp begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 格式化日期 [开始日期]
     */
    public String formatBegin() {
        if (this.begin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(begin);
    }
    /**
     * 设置 [结束日期]
     */
    public void setEnd(Timestamp end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 格式化日期 [结束日期]
     */
    public String formatEnd() {
        if (this.end == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(end);
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
     * 设置 [总工时]
     */
    public void setTotalestimates(Double totalestimates) {
        this.totalestimates = totalestimates;
        this.modify("totalestimates", totalestimates);
    }

    /**
     * 设置 [项目负责人]
     */
    public void setPm(String pm) {
        this.pm = pm;
        this.modify("pm", pm);
    }

    /**
     * 设置 [项目编号]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
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
        this.reset("ibizpro_projectdailyid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


