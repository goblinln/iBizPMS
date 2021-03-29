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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[项目周报]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "T_IBZPRO_PROJECTWEEKLY", resultMap = "IbizproProjectWeeklyResultMap")
public class IbizproProjectWeekly extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 建立时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`createdate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createdate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    private Timestamp createdate;
    /**
     * 更新人
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMAN)
    @TableField(value = "`updateman`")
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    private String updateman;
    /**
     * 年
     */
    @TableField(value = "`year`")
    @JSONField(name = "year")
    @JsonProperty("year")
    private String year;
    /**
     * 结束统计
     */
    @TableField(value = "`enddatestats`")
    @JSONField(name = "enddatestats")
    @JsonProperty("enddatestats")
    private String enddatestats;
    /**
     * 建立人
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMAN)
    @TableField(value = "`createman`", fill = FieldFill.INSERT)
    @JSONField(name = "createman")
    @JsonProperty("createman")
    private String createman;
    /**
     * 任务
     */
    @TableField(value = "`tasks`")
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    private String tasks;
    /**
     * 项目周报名称
     */
    @DEField(name = "ibzpro_projectweeklyname")
    @TableField(value = "`ibzpro_projectweeklyname`")
    @JSONField(name = "projectweeklyname")
    @JsonProperty("projectweeklyname")
    private String projectweeklyname;
    /**
     * 周
     */
    @TableField(value = "`week`")
    @JSONField(name = "week")
    @JsonProperty("week")
    private String week;
    /**
     * 项目周报标识
     */
    @DEField(name = "ibzpro_projectweeklyid", isKeyField = true)
    @TableId(value = "ibzpro_projectweeklyid", type = IdType.ASSIGN_UUID)
    @JSONField(name = "projectweeklyid")
    @JsonProperty("projectweeklyid")
    private String projectweeklyid;
    /**
     * 项目负责人
     */
    @TableField(value = "`pm`")
    @JSONField(name = "pm")
    @JsonProperty("pm")
    private String pm;
    /**
     * 日期
     */
    @TableField(value = "`date`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * 总工时
     */
    @TableField(value = "`totalestimates`")
    @JSONField(name = "totalestimates")
    @JsonProperty("totalestimates")
    private Double totalestimates;
    /**
     * 开始统计
     */
    @TableField(value = "`begindatestats`")
    @JSONField(name = "begindatestats")
    @JsonProperty("begindatestats")
    private String begindatestats;
    /**
     * 更新时间
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`updatedate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    private Timestamp updatedate;
    /**
     * 月
     */
    @TableField(value = "`month`")
    @JSONField(name = "month")
    @JsonProperty("month")
    private String month;
    /**
     * 项目名称
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    private String projectname;
    /**
     * 项目编号
     */
    @TableField(value = "`project`")
    @JSONField(name = "project")
    @JsonProperty("project")
    private Long project;

    /**
     * 项目
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project ztproject;



    /**
     * 设置 [年]
     */
    public void setYear(String year) {
        this.year = year;
        this.modify("year", year);
    }

    /**
     * 设置 [结束统计]
     */
    public void setEnddatestats(String enddatestats) {
        this.enddatestats = enddatestats;
        this.modify("enddatestats", enddatestats);
    }

    /**
     * 设置 [任务]
     */
    public void setTasks(String tasks) {
        this.tasks = tasks;
        this.modify("tasks", tasks);
    }

    /**
     * 设置 [项目周报名称]
     */
    public void setProjectweeklyname(String projectweeklyname) {
        this.projectweeklyname = projectweeklyname;
        this.modify("ibzpro_projectweeklyname", projectweeklyname);
    }

    /**
     * 设置 [周]
     */
    public void setWeek(String week) {
        this.week = week;
        this.modify("week", week);
    }

    /**
     * 设置 [项目负责人]
     */
    public void setPm(String pm) {
        this.pm = pm;
        this.modify("pm", pm);
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
     * 设置 [开始统计]
     */
    public void setBegindatestats(String begindatestats) {
        this.begindatestats = begindatestats;
        this.modify("begindatestats", begindatestats);
    }

    /**
     * 设置 [月]
     */
    public void setMonth(String month) {
        this.month = month;
        this.modify("month", month);
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
        this.reset("ibzpro_projectweeklyid");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


