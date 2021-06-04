package cn.ibizlab.pms.standardapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
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
 * 服务DTO对象[ProjectMonthlyDTO]
 */
@Data
@ApiModel("项目月报")
@JsonFilter(value = "dtofieldfilter")
public class ProjectMonthlyDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [IBIZPRO_PROJECTMONTHLYNAME]
     *
     */
    @JSONField(name = "ibizproprojectmonthlyname")
    @JsonProperty("ibizproprojectmonthlyname")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("项目月报名称")
    private String ibizproprojectmonthlyname;

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
     * 属性 [YEAR_MONTH]
     *
     */
    @JSONField(name = "yearmonth")
    @JsonProperty("yearmonth")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("年月")
    private String yearmonth;

    /**
     * 属性 [TOTALESTIMATES]
     *
     */
    @JSONField(name = "totalestimates")
    @JsonProperty("totalestimates")
    @ApiModelProperty("总工时")
    private Double totalestimates;

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
     * 属性 [TASKS]
     *
     */
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("任务")
    private String tasks;

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
     * 属性 [IBIZPRO_PROJECTMONTHLYID]
     *
     */
    @JSONField(name = "ibizproprojectmonthlyid")
    @JsonProperty("ibizproprojectmonthlyid")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目月报标识")
    private String ibizproprojectmonthlyid;

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
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 属性 [PM]
     *
     */
    @JSONField(name = "pm")
    @JsonProperty("pm")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("项目负责人")
    private String pm;

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
     * 设置 [IBIZPRO_PROJECTMONTHLYNAME]
     */
    public void setIbizproprojectmonthlyname(String  ibizproprojectmonthlyname){
        this.ibizproprojectmonthlyname = ibizproprojectmonthlyname ;
        this.modify("ibizpro_projectmonthlyname",ibizproprojectmonthlyname);
    }

    /**
     * 设置 [YEAR_MONTH]
     */
    public void setYearmonth(String  yearmonth){
        this.yearmonth = yearmonth ;
        this.modify("year_month",yearmonth);
    }

    /**
     * 设置 [TOTALESTIMATES]
     */
    public void setTotalestimates(Double  totalestimates){
        this.totalestimates = totalestimates ;
        this.modify("totalestimates",totalestimates);
    }

    /**
     * 设置 [TASKS]
     */
    public void setTasks(String  tasks){
        this.tasks = tasks ;
        this.modify("tasks",tasks);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }


}


