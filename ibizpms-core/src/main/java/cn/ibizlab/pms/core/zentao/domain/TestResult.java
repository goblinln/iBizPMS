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
 * 实体[测试结果]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_testresult", resultMap = "TestResultResultMap")
@ApiModel("测试结果")
public class TestResult extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`CREATEBY`")
    @JSONField(name = "createby")
    @JsonProperty("createby")
    @ApiModelProperty("由谁创建")
    private String createby;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "`ORG`")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 最后执行人
     */
    @TableField(value = "`LASTRUNNER`")
    @JSONField(name = "lastrunner")
    @JsonProperty("lastrunner")
    @ApiModelProperty("最后执行人")
    private String lastrunner;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 步骤结果
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`STEPRESULTS`")
    @JSONField(name = "stepresults")
    @JsonProperty("stepresults")
    @ApiModelProperty("步骤结果")
    private String stepresults;
    /**
     * 测试结果
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`CASERESULT`")
    @JSONField(name = "caseresult")
    @JsonProperty("caseresult")
    @ApiModelProperty("测试结果")
    private String caseresult;
    /**
     * 由谁更新
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`UPDATEBY`")
    @JSONField(name = "updateby")
    @JsonProperty("updateby")
    @ApiModelProperty("由谁更新")
    private String updateby;
    /**
     * 结果文件
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`XML`")
    @JSONField(name = "xml")
    @JsonProperty("xml")
    @ApiModelProperty("结果文件")
    private String xml;
    /**
     * 属性
     */
    @TableField(exist = false)
    @JSONField(name = "task")
    @JsonProperty("task")
    @ApiModelProperty("属性")
    private String task;
    /**
     * 持续时间
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`DURATION`")
    @JSONField(name = "duration")
    @JsonProperty("duration")
    @ApiModelProperty("持续时间")
    private Double duration;
    /**
     * 测试时间
     */
    @TableField(value = "`DATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "date", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("date")
    @ApiModelProperty("测试时间")
    private Timestamp date;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 用例版本
     */
    @TableField(value = "`VERSION`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("用例版本")
    private Integer version;
    /**
     * 相关需求
     */
    @TableField(exist = false)
    @JSONField(name = "story")
    @JsonProperty("story")
    @ApiModelProperty("相关需求")
    private Long story;
    /**
     * 用例名称
     */
    @TableField(exist = false)
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("用例名称")
    private String title;
    /**
     * 所属模块
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @ApiModelProperty("所属模块")
    private String modulename;
    /**
     * 所属模块
     */
    @TableField(exist = false)
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("所属模块")
    private Long module;
    /**
     * 前置条件
     */
    @TableField(exist = false)
    @JSONField(name = "precondition")
    @JsonProperty("precondition")
    @ApiModelProperty("前置条件")
    private String precondition;
    /**
     * 所属产品
     */
    @TableField(exist = false)
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("所属产品")
    private Long product;
    /**
     * 构建任务
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`JOB`")
    @JSONField(name = "job")
    @JsonProperty("job")
    @ApiModelProperty("构建任务")
    private Long job;
    /**
     * 用例
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`CASE`")
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    @ApiModelProperty("用例")
    private Long ibizcase;
    /**
     * 测试执行
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`RUN`")
    @JSONField(name = "run")
    @JsonProperty("run")
    @ApiModelProperty("测试执行")
    private Long run;
    /**
     * 代码编译
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`COMPILE`")
    @JSONField(name = "compile")
    @JsonProperty("compile")
    @ApiModelProperty("代码编译")
    private Long compile;
    /**
     * 归属部门名
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORNAME)
    @TableField(value = "`DEPTNAME`")
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @ApiModelProperty("归属部门名")
    private String deptname;
    /**
     * 归属组织名
     */
    @DEField(preType = DEPredefinedFieldType.ORGNAME)
    @TableField(value = "`ORGNAME`")
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @ApiModelProperty("归属组织名")
    private String orgname;

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
    public void setLastrunner(String lastrunner) {
        this.lastrunner = lastrunner;
        this.modify("lastrunner", lastrunner);
    }

    /**
     * 设置 [步骤结果]
     */
    public void setStepresults(String stepresults) {
        this.stepresults = stepresults;
        this.modify("stepresults", stepresults);
    }

    /**
     * 设置 [测试结果]
     */
    public void setCaseresult(String caseresult) {
        this.caseresult = caseresult;
        this.modify("caseresult", caseresult);
    }

    /**
     * 设置 [结果文件]
     */
    public void setXml(String xml) {
        this.xml = xml;
        this.modify("xml", xml);
    }

    /**
     * 设置 [持续时间]
     */
    public void setDuration(Double duration) {
        this.duration = duration;
        this.modify("duration", duration);
    }

    /**
     * 设置 [测试时间]
     */
    public void setDate(Timestamp date) {
        this.date = date;
        this.modify("date", date);
    }

    /**
     * 格式化日期 [测试时间]
     */
    public String formatDate() {
        if (this.date == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    /**
     * 设置 [用例版本]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [构建任务]
     */
    public void setJob(Long job) {
        this.job = job;
        this.modify("job", job);
    }

    /**
     * 设置 [用例]
     */
    public void setIbizcase(Long ibizcase) {
        this.ibizcase = ibizcase;
        this.modify("case", ibizcase);
    }

    /**
     * 设置 [测试执行]
     */
    public void setRun(Long run) {
        this.run = run;
        this.modify("run", run);
    }

    /**
     * 设置 [代码编译]
     */
    public void setCompile(Long compile) {
        this.compile = compile;
        this.modify("compile", compile);
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
        this.reset("id");
        return super.copyTo(targetEntity, bIncEmpty);
    }
}


