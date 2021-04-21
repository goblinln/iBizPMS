package cn.ibizlab.pms.webapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.alibaba.fastjson.annotation.JSONField;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import cn.ibizlab.pms.util.domain.DTOBase;
import cn.ibizlab.pms.util.domain.DTOClient;
import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[IbizproProjectWeeklyDTO]
 */
@Data
@ApiModel("项目周报")
public class IbizproProjectWeeklyDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [CREATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "createdate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdate")
    @ApiModelProperty("建立时间")
    private Timestamp createdate;

    /**
     * 属性 [UPDATEMAN]
     *
     */
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人")
    private String updateman;

    /**
     * 属性 [YEAR]
     *
     */
    @JSONField(name = "year")
    @JsonProperty("year")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("年")
    private String year;

    /**
     * 属性 [ENDDATESTATS]
     *
     */
    @JSONField(name = "enddatestats")
    @JsonProperty("enddatestats")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("结束统计")
    private String enddatestats;

    /**
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 属性 [TASKS]
     *
     */
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("任务")
    private String tasks;

    /**
     * 属性 [IBZPRO_PROJECTWEEKLYNAME]
     *
     */
    @JSONField(name = "projectweeklyname")
    @JsonProperty("projectweeklyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("项目周报名称")
    private String projectweeklyname;

    /**
     * 属性 [WEEK]
     *
     */
    @JSONField(name = "week")
    @JsonProperty("week")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("周")
    private String week;

    /**
     * 属性 [IBZPRO_PROJECTWEEKLYID]
     *
     */
    @JSONField(name = "projectweeklyid")
    @JsonProperty("projectweeklyid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目周报标识")
    private String projectweeklyid;

    /**
     * 属性 [PM]
     *
     */
    @JSONField(name = "pm")
    @JsonProperty("pm")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目负责人")
    private String pm;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd")
    @JsonProperty("date")
    @ApiModelProperty("日期")
    private Timestamp date;

    /**
     * 属性 [TOTALESTIMATES]
     *
     */
    @JSONField(name = "totalestimates")
    @JsonProperty("totalestimates")
    @ApiModelProperty("总工时")
    private Double totalestimates;

    /**
     * 属性 [BEGINDATESTATS]
     *
     */
    @JSONField(name = "begindatestats")
    @JsonProperty("begindatestats")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("开始统计")
    private String begindatestats;

    /**
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("updatedate")
    @ApiModelProperty("更新时间")
    private Timestamp updatedate;

    /**
     * 属性 [MONTH]
     *
     */
    @JSONField(name = "month")
    @JsonProperty("month")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("月")
    private String month;

    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("项目名称")
    private String projectname;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目编号")
    private Long project;


    /**
     * 设置 [YEAR]
     */
    public void setYear(String  year){
        this.year = year ;
        this.modify("year",year);
    }

    /**
     * 设置 [ENDDATESTATS]
     */
    public void setEnddatestats(String  enddatestats){
        this.enddatestats = enddatestats ;
        this.modify("enddatestats",enddatestats);
    }

    /**
     * 设置 [TASKS]
     */
    public void setTasks(String  tasks){
        this.tasks = tasks ;
        this.modify("tasks",tasks);
    }

    /**
     * 设置 [IBZPRO_PROJECTWEEKLYNAME]
     */
    public void setProjectweeklyname(String  projectweeklyname){
        this.projectweeklyname = projectweeklyname ;
        this.modify("ibzpro_projectweeklyname",projectweeklyname);
    }

    /**
     * 设置 [WEEK]
     */
    public void setWeek(String  week){
        this.week = week ;
        this.modify("week",week);
    }

    /**
     * 设置 [PM]
     */
    public void setPm(String  pm){
        this.pm = pm ;
        this.modify("pm",pm);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [TOTALESTIMATES]
     */
    public void setTotalestimates(Double  totalestimates){
        this.totalestimates = totalestimates ;
        this.modify("totalestimates",totalestimates);
    }

    /**
     * 设置 [BEGINDATESTATS]
     */
    public void setBegindatestats(String  begindatestats){
        this.begindatestats = begindatestats ;
        this.modify("begindatestats",begindatestats);
    }

    /**
     * 设置 [MONTH]
     */
    public void setMonth(String  month){
        this.month = month ;
        this.modify("month",month);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }


}


