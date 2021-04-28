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
 * 服务DTO对象[TestResultDTO]
 */
@Data
@ApiModel("测试结果")
public class TestResultDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

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
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

    /**
     * 属性 [LASTRUNNER]
     *
     */
    @JSONField(name = "lastrunner")
    @JsonProperty("lastrunner")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("最后执行人")
    private String lastrunner;

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
     * 属性 [STEPRESULTS]
     *
     */
    @JSONField(name = "stepresults")
    @JsonProperty("stepresults")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("步骤结果")
    private String stepresults;

    /**
     * 属性 [CASERESULT]
     *
     */
    @JSONField(name = "caseresult")
    @JsonProperty("caseresult")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("测试结果")
    private String caseresult;

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
     * 属性 [UPDATEBY]
     *
     */
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("由谁更新")
    private String updateby;

    /**
     * 属性 [XML]
     *
     */
    @JSONField(name = "xml")
    @JsonProperty("xml")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("结果文件")
    private String xml;

    /**
     * 属性 [TASK]
     *
     */
    @JSONField(name = "task")
    @JsonProperty("task")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("属性")
    private String task;

    /**
     * 属性 [DURATION]
     *
     */
    @JSONField(name = "duration")
    @JsonProperty("duration")
    @ApiModelProperty("持续时间")
    private Double duration;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    @ApiModelProperty("测试时间")
    private Timestamp date;

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
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 属性 [VERSION]
     *
     */
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("用例版本")
    private Integer version;

    /**
     * 属性 [STORY]
     *
     */
    @JSONField(name = "story")
    @JsonProperty("story")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("相关需求")
    private Long story;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("用例名称")
    private String title;

    /**
     * 属性 [MODULENAME]
     *
     */
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("所属模块")
    private String modulename;

    /**
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属模块")
    private Long module;

    /**
     * 属性 [PRECONDITION]
     *
     */
    @JSONField(name = "precondition")
    @JsonProperty("precondition")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("前置条件")
    private String precondition;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属产品")
    private Long product;

    /**
     * 属性 [JOB]
     *
     */
    @JSONField(name = "job")
    @JsonProperty("job")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("构建任务")
    private Long job;

    /**
     * 属性 [CASE]
     *
     */
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("用例")
    private Long ibizcase;

    /**
     * 属性 [RUN]
     *
     */
    @JSONField(name = "run")
    @JsonProperty("run")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("测试执行")
    private Long run;

    /**
     * 属性 [COMPILE]
     *
     */
    @JSONField(name = "compile")
    @JsonProperty("compile")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("代码编译")
    private Long compile;


    /**
     * 设置 [LASTRUNNER]
     */
    public void setLastrunner(String  lastrunner){
        this.lastrunner = lastrunner ;
        this.modify("lastrunner",lastrunner);
    }

    /**
     * 设置 [STEPRESULTS]
     */
    public void setStepresults(String  stepresults){
        this.stepresults = stepresults ;
        this.modify("stepresults",stepresults);
    }

    /**
     * 设置 [CASERESULT]
     */
    public void setCaseresult(String  caseresult){
        this.caseresult = caseresult ;
        this.modify("caseresult",caseresult);
    }

    /**
     * 设置 [XML]
     */
    public void setXml(String  xml){
        this.xml = xml ;
        this.modify("xml",xml);
    }

    /**
     * 设置 [DURATION]
     */
    public void setDuration(Double  duration){
        this.duration = duration ;
        this.modify("duration",duration);
    }

    /**
     * 设置 [DATE]
     */
    public void setDate(Timestamp  date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 设置 [VERSION]
     */
    public void setVersion(Integer  version){
        this.version = version ;
        this.modify("version",version);
    }

    /**
     * 设置 [JOB]
     */
    public void setJob(Long  job){
        this.job = job ;
        this.modify("job",job);
    }

    /**
     * 设置 [CASE]
     */
    public void setIbizcase(Long  ibizcase){
        this.ibizcase = ibizcase ;
        this.modify("case",ibizcase);
    }

    /**
     * 设置 [RUN]
     */
    public void setRun(Long  run){
        this.run = run ;
        this.modify("run",run);
    }

    /**
     * 设置 [COMPILE]
     */
    public void setCompile(Long  compile){
        this.compile = compile ;
        this.modify("compile",compile);
    }


}


