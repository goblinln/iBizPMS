package cn.ibizlab.pms.webapi.dto;

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
 * 服务DTO对象[TaskStatsDTO]
 */
@Data
@ApiModel("任务统计")
//@JsonFilter(value = "dtofieldfilter")
public class TaskStatsDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [TASKDEADLINE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "taskdeadline" , format="yyyy-MM-dd")
    @JsonProperty("taskdeadline")
    @ApiModelProperty("任务截至日期")
    private Timestamp taskdeadline;

    /**
     * 属性 [TASKID]
     *
     */
    @JSONField(name = "taskid")
    @JsonProperty("taskid")
    @ApiModelProperty("任务编号")
    private Integer taskid;

    /**
     * 属性 [TASKREALSTART]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "taskrealstart" , format="yyyy-MM-dd")
    @JsonProperty("taskrealstart")
    @ApiModelProperty("任务实际开始时间")
    private Timestamp taskrealstart;

    /**
     * 属性 [TASKEFFICIENT]
     *
     */
    @JSONField(name = "taskefficient")
    @JsonProperty("taskefficient")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("效率")
    private String taskefficient;

    /**
     * 属性 [TASKESTIMATE]
     *
     */
    @JSONField(name = "taskestimate")
    @JsonProperty("taskestimate")
    @ApiModelProperty("任务预计消耗")
    private Integer taskestimate;

    /**
     * 属性 [END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "end" , format="yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束")
    private Timestamp end;

    /**
     * 属性 [USERCONSUMED]
     *
     */
    @JSONField(name = "userconsumed")
    @JsonProperty("userconsumed")
    @ApiModelProperty("用户总消耗")
    private Integer userconsumed;

    /**
     * 属性 [TOTALLEFT]
     *
     */
    @JSONField(name = "totalleft")
    @JsonProperty("totalleft")
    @ApiModelProperty("剩余总工时")
    private Integer totalleft;

    /**
     * 属性 [PROJECTCONSUMED]
     *
     */
    @JSONField(name = "projectconsumed")
    @JsonProperty("projectconsumed")
    @ApiModelProperty("项目总消耗")
    private Integer projectconsumed;

    /**
     * 属性 [BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "begin" , format="yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("属性")
    private Timestamp begin;

    /**
     * 属性 [TOTALCONSUMED]
     *
     */
    @JSONField(name = "totalconsumed")
    @JsonProperty("totalconsumed")
    @ApiModelProperty("消耗总工时")
    private Integer totalconsumed;

    /**
     * 属性 [TOTALESTIMATE]
     *
     */
    @JSONField(name = "totalestimate")
    @JsonProperty("totalestimate")
    @ApiModelProperty("预计总工时")
    private Integer totalestimate;

    /**
     * 属性 [TASKESTSTARTED]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "taskeststarted" , format="yyyy-MM-dd")
    @JsonProperty("taskeststarted")
    @ApiModelProperty("任务预计开始日期")
    private Timestamp taskeststarted;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("部门")
    private String dept;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("名称")
    private String name;

    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目名称")
    private String projectname;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("项目")
    private String project;

    /**
     * 属性 [TASKFINISHEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "taskfinisheddate" , format="yyyy-MM-dd")
    @JsonProperty("taskfinisheddate")
    @ApiModelProperty("任务实际完成日期")
    private Timestamp taskfinisheddate;

    /**
     * 属性 [TASKNAME]
     *
     */
    @JSONField(name = "taskname")
    @JsonProperty("taskname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("任务名称")
    private String taskname;

    /**
     * 属性 [TASKCNT]
     *
     */
    @JSONField(name = "taskcnt")
    @JsonProperty("taskcnt")
    @ApiModelProperty("总任务数")
    private Integer taskcnt;

    /**
     * 属性 [TASKPRI]
     *
     */
    @JSONField(name = "taskpri")
    @JsonProperty("taskpri")
    @ApiModelProperty("任务优先级")
    private Integer taskpri;

    /**
     * 属性 [TASKDELAY]
     *
     */
    @JSONField(name = "taskdelay")
    @JsonProperty("taskdelay")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("任务延期")
    private String taskdelay;

    /**
     * 属性 [FINISHEDBY]
     *
     */
    @JSONField(name = "finishedby")
    @JsonProperty("finishedby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("完成者")
    private String finishedby;


    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }


}


