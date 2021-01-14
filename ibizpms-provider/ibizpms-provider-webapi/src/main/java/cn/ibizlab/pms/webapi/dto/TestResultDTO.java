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

/**
 * 服务DTO对象[TestResultDTO]
 */
@Data
public class TestResultDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [LASTRUNNER]
     *
     */
    @JSONField(name = "lastrunner")
    @JsonProperty("lastrunner")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String lastrunner;

    /**
     * 属性 [STEPRESULTS]
     *
     */
    @JSONField(name = "stepresults")
    @JsonProperty("stepresults")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    private String stepresults;

    /**
     * 属性 [CASERESULT]
     *
     */
    @JSONField(name = "caseresult")
    @JsonProperty("caseresult")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    private String caseresult;

    /**
     * 属性 [XML]
     *
     */
    @JSONField(name = "xml")
    @JsonProperty("xml")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    private String xml;

    /**
     * 属性 [DURATION]
     *
     */
    @JSONField(name = "duration")
    @JsonProperty("duration")
    private Double duration;

    /**
     * 属性 [DATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    private Timestamp date;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 属性 [VERSION]
     *
     */
    @JSONField(name = "version")
    @JsonProperty("version")
    private Integer version;

    /**
     * 属性 [JOB]
     *
     */
    @JSONField(name = "job")
    @JsonProperty("job")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long job;

    /**
     * 属性 [CASE]
     *
     */
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long ibizcase;

    /**
     * 属性 [RUN]
     *
     */
    @JSONField(name = "run")
    @JsonProperty("run")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long run;

    /**
     * 属性 [COMPILE]
     *
     */
    @JSONField(name = "compile")
    @JsonProperty("compile")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long compile;

    /**
     * 属性 [TASK]
     *
     */
    @JSONField(name = "task")
    @JsonProperty("task")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    private String task;

    /**
     * 属性 [TITLE]
     *
     */
    @JSONField(name = "title")
    @JsonProperty("title")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    private String title;

    /**
     * 属性 [STORY]
     *
     */
    @JSONField(name = "story")
    @JsonProperty("story")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long story;

    /**
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long module;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long product;

    /**
     * 属性 [PRECONDITION]
     *
     */
    @JSONField(name = "precondition")
    @JsonProperty("precondition")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    private String precondition;

    /**
     * 属性 [MODULENAME]
     *
     */
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    private String modulename;


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


