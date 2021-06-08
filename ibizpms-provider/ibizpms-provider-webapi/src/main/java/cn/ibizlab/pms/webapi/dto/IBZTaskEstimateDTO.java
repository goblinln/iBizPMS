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
 * 服务DTO对象[IBZTaskEstimateDTO]
 */
@Data
@ApiModel("任务预计")
@JsonFilter(value = "dtofieldfilter")
public class IBZTaskEstimateDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 属性 [MONTHNAME]
     *
     */
    @JSONField(name = "monthname")
    @JsonProperty("monthname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("月（显示）")
    private String monthname;
    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;
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
     * 属性 [ACCOUNT]
     *
     */
    @JSONField(name = "account")
    @JsonProperty("account")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("用户")
    private String account;
    /**
     * 属性 [LEFT]
     *
     */
    @JSONField(name = "left")
    @JsonProperty("left")
    @ApiModelProperty("预计剩余")
    private Double left;
    /**
     * 属性 [CONSUMED]
     *
     */
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("总计消耗")
    private Double consumed;
    /**
     * 属性 [CREATEBY]
     *
     */
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 属性 [EVALUATIONCOST]
     *
     */
    @JSONField(name = "evaluationcost")
    @JsonProperty("evaluationcost")
    @ApiModelProperty("评估成本")
    private Double evaluationcost;
    /**
     * 属性 [MONTHORDER]
     *
     */
    @JSONField(name = "monthorder")
    @JsonProperty("monthorder")
    @ApiModelProperty("月（排序）")
    private Integer monthorder;
    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("附件")
    private String files;
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
     * 属性 [EVALUATIONSTATUS]
     *
     */
    @JSONField(name = "evaluationstatus")
    @JsonProperty("evaluationstatus")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("评估状态")
    private String evaluationstatus;
    /**
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;
    /**
     * 属性 [YEARNAME]
     *
     */
    @JSONField(name = "yearname")
    @JsonProperty("yearname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("年（显示）")
    private String yearname;
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
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 属性 [EVALUATIONTIME]
     *
     */
    @JSONField(name = "evaluationtime")
    @JsonProperty("evaluationtime")
    @ApiModelProperty("评估工时")
    private Double evaluationtime;
    /**
     * 属性 [INPUTCOST]
     *
     */
    @JSONField(name = "inputcost")
    @JsonProperty("inputcost")
    @ApiModelProperty("投入成本")
    private Double inputcost;
    /**
     * 属性 [DATES]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "dates" , format="yyyy-MM-dd")
    @JsonProperty("dates")
    @ApiModelProperty("日期")
    private Timestamp dates;
    /**
     * 属性 [DEPTNAME]
     *
     */
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * 属性 [TASKESTIMATESN]
     *
     */
    @JSONField(name = "taskestimatesn")
    @JsonProperty("taskestimatesn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("任务预计编号")
    private Long taskestimatesn;
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
     * 属性 [WORK]
     *
     */
    @JSONField(name = "work")
    @JsonProperty("work")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("work")
    private String work;
    /**
     * 属性 [EVALUATIONDESC]
     *
     */
    @JSONField(name = "evaluationdesc")
    @JsonProperty("evaluationdesc")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("评估说明")
    private String evaluationdesc;
    /**
     * 属性 [TASKSPECIES]
     *
     */
    @JSONField(name = "taskspecies")
    @JsonProperty("taskspecies")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("任务种别")
    private String taskspecies;
    /**
     * 属性 [TASKNAME]
     *
     */
    @JSONField(name = "taskname")
    @JsonProperty("taskname")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("任务名称")
    private String taskname;
    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("所属项目")
    private String projectname;
    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("任务类型")
    private String type;
    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("任务删除标识")
    private String deleted;
    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("项目")
    private Long project;
    /**
     * 属性 [TASK]
     *
     */
    @JSONField(name = "task")
    @JsonProperty("task")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("任务")
    private Long task;

    /**
     * 设置 [MONTHNAME]
     */
    public void setMonthname(String  monthname){
        this.monthname = monthname ;
        this.modify("monthname",monthname);
    }

    /**
     * 设置 [ACCOUNT]
     */
    public void setAccount(String  account){
        this.account = account ;
        this.modify("account",account);
    }

    /**
     * 设置 [LEFT]
     */
    public void setLeft(Double  left){
        this.left = left ;
        this.modify("left",left);
    }

    /**
     * 设置 [CONSUMED]
     */
    public void setConsumed(Double  consumed){
        this.consumed = consumed ;
        this.modify("consumed",consumed);
    }

    /**
     * 设置 [EVALUATIONCOST]
     */
    public void setEvaluationcost(Double  evaluationcost){
        this.evaluationcost = evaluationcost ;
        this.modify("evaluationcost",evaluationcost);
    }

    /**
     * 设置 [FILES]
     */
    public void setFiles(String  files){
        this.files = files ;
        this.modify("files",files);
    }

    /**
     * 设置 [EVALUATIONSTATUS]
     */
    public void setEvaluationstatus(String  evaluationstatus){
        this.evaluationstatus = evaluationstatus ;
        this.modify("evaluationstatus",evaluationstatus);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [EVALUATIONTIME]
     */
    public void setEvaluationtime(Double  evaluationtime){
        this.evaluationtime = evaluationtime ;
        this.modify("evaluationtime",evaluationtime);
    }

    /**
     * 设置 [INPUTCOST]
     */
    public void setInputcost(Double  inputcost){
        this.inputcost = inputcost ;
        this.modify("inputcost",inputcost);
    }

    /**
     * 设置 [TASKESTIMATESN]
     */
    public void setTaskestimatesn(Long  taskestimatesn){
        this.taskestimatesn = taskestimatesn ;
        this.modify("taskestimatesn",taskestimatesn);
    }

    /**
     * 设置 [WORK]
     */
    public void setWork(String  work){
        this.work = work ;
        this.modify("work",work);
    }

    /**
     * 设置 [EVALUATIONDESC]
     */
    public void setEvaluationdesc(String  evaluationdesc){
        this.evaluationdesc = evaluationdesc ;
        this.modify("evaluationdesc",evaluationdesc);
    }

    /**
     * 设置 [TASK]
     */
    public void setTask(Long  task){
        this.task = task ;
        this.modify("task",task);
    }


}


