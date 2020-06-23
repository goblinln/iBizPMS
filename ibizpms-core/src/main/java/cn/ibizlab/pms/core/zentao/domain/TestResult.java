package cn.ibizlab.pms.core.zentao.domain;

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
import java.io.Serializable;
import lombok.*;
import org.springframework.data.annotation.Transient;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;

/**
 * 实体[测试结果]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_testresult",resultMap = "TestResultResultMap")
public class TestResult extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 最后执行人
     */
    @TableField(value = "lastrunner")
    @JSONField(name = "lastrunner")
    @JsonProperty("lastrunner")
    private String lastrunner;
    /**
     * 步骤结果
     */
    @TableField(value = "stepresults")
    @JSONField(name = "stepresults")
    @JsonProperty("stepresults")
    private String stepresults;
    /**
     * 测试结果
     */
    @TableField(value = "caseresult")
    @JSONField(name = "caseresult")
    @JsonProperty("caseresult")
    private String caseresult;
    /**
     * 结果文件
     */
    @TableField(value = "xml")
    @JSONField(name = "xml")
    @JsonProperty("xml")
    private String xml;
    /**
     * 持续时间
     */
    @TableField(value = "duration")
    @JSONField(name = "duration")
    @JsonProperty("duration")
    private Double duration;
    /**
     * 测试时间
     */
    @TableField(value = "date")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "date" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    private Timestamp date;
    /**
     * 编号
     */
    @DEField(isKeyField=true)
    @TableId(value= "id",type=IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private BigInteger id;
    /**
     * 用例版本
     */
    @TableField(value = "version")
    @JSONField(name = "version")
    @JsonProperty("version")
    private Integer version;
    /**
     * 构建任务
     */
    @TableField(value = "job")
    @JSONField(name = "job")
    @JsonProperty("job")
    private BigInteger job;
    /**
     * 用例
     */
    @DEField(name = "case")
    @TableField(value = "case")
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    private BigInteger ibizcase;
    /**
     * 测试执行
     */
    @TableField(value = "run")
    @JSONField(name = "run")
    @JsonProperty("run")
    private BigInteger run;
    /**
     * 代码编译
     */
    @TableField(value = "compile")
    @JSONField(name = "compile")
    @JsonProperty("compile")
    private BigInteger compile;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Case ztcase;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Compile ztcompile;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Job ztjob;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.TestRun zttestrun;



    /**
     * 设置 [最后执行人]
     */
    public void setLastrunner(String lastrunner){
        this.lastrunner = lastrunner ;
        this.modify("lastrunner",lastrunner);
    }

    /**
     * 设置 [步骤结果]
     */
    public void setStepresults(String stepresults){
        this.stepresults = stepresults ;
        this.modify("stepresults",stepresults);
    }

    /**
     * 设置 [测试结果]
     */
    public void setCaseresult(String caseresult){
        this.caseresult = caseresult ;
        this.modify("caseresult",caseresult);
    }

    /**
     * 设置 [结果文件]
     */
    public void setXml(String xml){
        this.xml = xml ;
        this.modify("xml",xml);
    }

    /**
     * 设置 [持续时间]
     */
    public void setDuration(Double duration){
        this.duration = duration ;
        this.modify("duration",duration);
    }

    /**
     * 设置 [测试时间]
     */
    public void setDate(Timestamp date){
        this.date = date ;
        this.modify("date",date);
    }

    /**
     * 格式化日期 [测试时间]
     */
    public String formatDate(){
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    /**
     * 设置 [用例版本]
     */
    public void setVersion(Integer version){
        this.version = version ;
        this.modify("version",version);
    }

    /**
     * 设置 [构建任务]
     */
    public void setJob(BigInteger job){
        this.job = job ;
        this.modify("job",job);
    }

    /**
     * 设置 [用例]
     */
    public void setIbizcase(BigInteger ibizcase){
        this.ibizcase = ibizcase ;
        this.modify("case",ibizcase);
    }

    /**
     * 设置 [测试执行]
     */
    public void setRun(BigInteger run){
        this.run = run ;
        this.modify("run",run);
    }

    /**
     * 设置 [代码编译]
     */
    public void setCompile(BigInteger compile){
        this.compile = compile ;
        this.modify("compile",compile);
    }


}


