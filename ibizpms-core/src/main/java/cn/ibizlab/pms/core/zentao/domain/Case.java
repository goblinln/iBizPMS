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
 * 实体[测试用例]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_case", resultMap = "CaseResultMap")
@ApiModel("测试用例")
public class Case extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 修改日期
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`LASTEDITEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastediteddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    @ApiModelProperty("修改日期")
    private Timestamp lastediteddate;
    /**
     * scriptedDate
     */
    @TableField(value = "`SCRIPTEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "scripteddate", format = "yyyy-MM-dd")
    @JsonProperty("scripteddate")
    @ApiModelProperty("scriptedDate")
    private Timestamp scripteddate;
    /**
     * 标题颜色
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`COLOR`")
    @JSONField(name = "color")
    @JsonProperty("color")
    @ApiModelProperty("标题颜色")
    private String color;
    /**
     * path
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PATH`")
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("path")
    private Integer path;
    /**
     * 创建日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`OPENEDDATE`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "openeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("创建日期")
    private Timestamp openeddate;
    /**
     * 结果
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`LASTRUNRESULT`")
    @JSONField(name = "lastrunresult")
    @JsonProperty("lastrunresult")
    @ApiModelProperty("结果")
    private String lastrunresult;
    /**
     * 模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename1")
    @JsonProperty("modulename1")
    @ApiModelProperty("模块名称")
    private String modulename1;
    /**
     * 相关用例
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`LINKCASE`")
    @JSONField(name = "linkcase")
    @JsonProperty("linkcase")
    @ApiModelProperty("相关用例")
    private String linkcase;
    /**
     * 属性
     */
    @TableField(exist = false)
    @JSONField(name = "task")
    @JsonProperty("task")
    @ApiModelProperty("属性")
    private String task;
    /**
     * 排序
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ORDER`")
    @JSONField(name = "order")
    @JsonProperty("order")
    @ApiModelProperty("排序")
    private Integer order;
    /**
     * howRun
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`HOWRUN`")
    @JSONField(name = "howrun")
    @JsonProperty("howrun")
    @ApiModelProperty("howRun")
    private String howrun;
    /**
     * 测试结果数
     */
    @TableField(exist = false)
    @JSONField(name = "resultcnt")
    @JsonProperty("resultcnt")
    @ApiModelProperty("测试结果数")
    private Integer resultcnt;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 用例版本
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`VERSION`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("用例版本")
    private Integer version;
    /**
     * scriptedBy
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SCRIPTEDBY`")
    @JSONField(name = "scriptedby")
    @JsonProperty("scriptedby")
    @ApiModelProperty("scriptedBy")
    private String scriptedby;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`OPENEDBY`")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @ApiModelProperty("由谁创建")
    private String openedby;
    /**
     * 用例类型
     */
    @DEField(defaultValue = "feature")
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("用例类型")
    private String type;
    /**
     * 测试失败数
     */
    @TableField(exist = false)
    @JSONField(name = "resultfalicnt")
    @JsonProperty("resultfalicnt")
    @ApiModelProperty("测试失败数")
    private Integer resultfalicnt;
    /**
     * 用例状态
     */
    @DEField(defaultValue = "normal")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("用例状态")
    private String status;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * auto
     */
    @DEField(defaultValue = "no")
    @TableField(value = "`AUTO`")
    @JSONField(name = "auto")
    @JsonProperty("auto")
    @ApiModelProperty("auto")
    private String auto;
    /**
     * 是否收藏
     */
    @TableField(exist = false)
    @JSONField(name = "isfavorites")
    @JsonProperty("isfavorites")
    @ApiModelProperty("是否收藏")
    private String isfavorites;
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
     * frequency
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`FREQUENCY`")
    @JSONField(name = "frequency")
    @JsonProperty("frequency")
    @ApiModelProperty("frequency")
    private String frequency;
    /**
     * 用例标题
     */
    @TableField(value = "`TITLE`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("用例标题")
    private String title;
    /**
     * 最后修改者
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`LASTEDITEDBY`")
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    @ApiModelProperty("最后修改者")
    private String lasteditedby;
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
     * 由谁评审
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`REVIEWEDBY`")
    @JSONField(name = "reviewedby")
    @JsonProperty("reviewedby")
    @ApiModelProperty("由谁评审")
    private String reviewedby;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 转bug数
     */
    @TableField(exist = false)
    @JSONField(name = "tobugcnt")
    @JsonProperty("tobugcnt")
    @ApiModelProperty("转bug数")
    private Integer tobugcnt;
    /**
     * 指派给
     */
    @TableField(exist = false)
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @ApiModelProperty("指派给")
    private String assignedto;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 评审时间
     */
    @TableField(value = "`REVIEWEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "revieweddate", format = "yyyy-MM-dd")
    @JsonProperty("revieweddate")
    @ApiModelProperty("评审时间")
    private Timestamp revieweddate;
    /**
     * 优先级
     */
    @DEField(defaultValue = "3")
    @TableField(value = "`PRI`")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;
    /**
     * 适用阶段
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`STAGE`")
    @JSONField(name = "stage")
    @JsonProperty("stage")
    @ApiModelProperty("适用阶段")
    private String stage;
    /**
     * scriptLocation
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SCRIPTLOCATION`")
    @JSONField(name = "scriptlocation")
    @JsonProperty("scriptlocation")
    @ApiModelProperty("scriptLocation")
    private String scriptlocation;
    /**
     * 用例状态
     */
    @TableField(exist = false)
    @JSONField(name = "status1")
    @JsonProperty("status1")
    @ApiModelProperty("用例状态")
    private String status1;
    /**
     * 执行时间
     */
    @TableField(value = "`LASTRUNDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastrundate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastrundate")
    @ApiModelProperty("执行时间")
    private Timestamp lastrundate;
    /**
     * 关键词
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`KEYWORDS`")
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    @ApiModelProperty("关键词")
    private String keywords;
    /**
     * scriptStatus
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SCRIPTSTATUS`")
    @JSONField(name = "scriptstatus")
    @JsonProperty("scriptstatus")
    @ApiModelProperty("scriptStatus")
    private String scriptstatus;
    /**
     * 工具/框架
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`FRAME`")
    @JSONField(name = "frame")
    @JsonProperty("frame")
    @ApiModelProperty("工具/框架")
    private String frame;
    /**
     * 测试用例结果
     */
    @TableField(exist = false)
    @JSONField(name = "lastrunresult1")
    @JsonProperty("lastrunresult1")
    @ApiModelProperty("测试用例结果")
    private String lastrunresult1;
    /**
     * 用例步骤数
     */
    @TableField(exist = false)
    @JSONField(name = "stepcnt")
    @JsonProperty("stepcnt")
    @ApiModelProperty("用例步骤数")
    private Integer stepcnt;
    /**
     * 子状态
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`SUBSTATUS`")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @ApiModelProperty("子状态")
    private String substatus;
    /**
     * 用例编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("用例编号")
    private Long id;
    /**
     * 前置条件
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`PRECONDITION`")
    @JSONField(name = "precondition")
    @JsonProperty("precondition")
    @ApiModelProperty("前置条件")
    private String precondition;
    /**
     * 执行人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`LASTRUNNER`")
    @JSONField(name = "lastrunner")
    @JsonProperty("lastrunner")
    @ApiModelProperty("执行人")
    private String lastrunner;
    /**
     * 用例库
     */
    @TableField(exist = false)
    @JSONField(name = "libname")
    @JsonProperty("libname")
    @ApiModelProperty("用例库")
    private String libname;
    /**
     * 来源用例版本
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`FROMCASEVERSION`")
    @JSONField(name = "fromcaseversion")
    @JsonProperty("fromcaseversion")
    @ApiModelProperty("来源用例版本")
    private Integer fromcaseversion;
    /**
     * 需求名称
     */
    @TableField(exist = false)
    @JSONField(name = "storyname")
    @JsonProperty("storyname")
    @ApiModelProperty("需求名称")
    private String storyname;
    /**
     * 模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @ApiModelProperty("模块名称")
    private String modulename;
    /**
     * 需求版本
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`STORYVERSION`")
    @JSONField(name = "storyversion")
    @JsonProperty("storyversion")
    @ApiModelProperty("需求版本")
    private Integer storyversion;
    /**
     * 产品名称
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("产品名称")
    private String productname;
    /**
     * 来源用例
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`FROMCASEID`")
    @JSONField(name = "fromcaseid")
    @JsonProperty("fromcaseid")
    @ApiModelProperty("来源用例")
    private Long fromcaseid;
    /**
     * 平台/分支
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`BRANCH`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @ApiModelProperty("平台/分支")
    private Long branch;
    /**
     * 来源Bug
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`FROMBUG`")
    @JSONField(name = "frombug")
    @JsonProperty("frombug")
    @ApiModelProperty("来源Bug")
    private Long frombug;
    /**
     * 相关需求
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`STORY`")
    @JSONField(name = "story")
    @JsonProperty("story")
    @ApiModelProperty("相关需求")
    private Long story;
    /**
     * 所属产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("所属产品")
    private Long product;
    /**
     * 所属库
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`LIB`")
    @JSONField(name = "lib")
    @JsonProperty("lib")
    @ApiModelProperty("所属库")
    private Long lib;
    /**
     * 所属模块
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`MODULE`")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("所属模块")
    private Long module;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Branch ztbranch;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Bug ztfrombug;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Case ztfromcase;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Module ztmodule;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product ztproduct;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Story ztstory;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.TestSuite zttestsuite;


    /**
     * 用例步骤
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.zentao.domain.CaseStep> casestep;


    /**
     * 设置 [scriptedDate]
     */
    public void setScripteddate(Timestamp scripteddate) {
        this.scripteddate = scripteddate;
        this.modify("scripteddate", scripteddate);
    }

    /**
     * 格式化日期 [scriptedDate]
     */
    public String formatScripteddate() {
        if (this.scripteddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(scripteddate);
    }
    /**
     * 设置 [标题颜色]
     */
    public void setColor(String color) {
        this.color = color;
        this.modify("color", color);
    }

    /**
     * 设置 [path]
     */
    public void setPath(Integer path) {
        this.path = path;
        this.modify("path", path);
    }

    /**
     * 设置 [结果]
     */
    public void setLastrunresult(String lastrunresult) {
        this.lastrunresult = lastrunresult;
        this.modify("lastrunresult", lastrunresult);
    }

    /**
     * 设置 [相关用例]
     */
    public void setLinkcase(String linkcase) {
        this.linkcase = linkcase;
        this.modify("linkcase", linkcase);
    }

    /**
     * 设置 [排序]
     */
    public void setOrder(Integer order) {
        this.order = order;
        this.modify("order", order);
    }

    /**
     * 设置 [howRun]
     */
    public void setHowrun(String howrun) {
        this.howrun = howrun;
        this.modify("howrun", howrun);
    }

    /**
     * 设置 [用例版本]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [scriptedBy]
     */
    public void setScriptedby(String scriptedby) {
        this.scriptedby = scriptedby;
        this.modify("scriptedby", scriptedby);
    }

    /**
     * 设置 [用例类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [用例状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [auto]
     */
    public void setAuto(String auto) {
        this.auto = auto;
        this.modify("auto", auto);
    }

    /**
     * 设置 [frequency]
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
        this.modify("frequency", frequency);
    }

    /**
     * 设置 [用例标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [由谁评审]
     */
    public void setReviewedby(String reviewedby) {
        this.reviewedby = reviewedby;
        this.modify("reviewedby", reviewedby);
    }

    /**
     * 设置 [评审时间]
     */
    public void setRevieweddate(Timestamp revieweddate) {
        this.revieweddate = revieweddate;
        this.modify("revieweddate", revieweddate);
    }

    /**
     * 格式化日期 [评审时间]
     */
    public String formatRevieweddate() {
        if (this.revieweddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(revieweddate);
    }
    /**
     * 设置 [优先级]
     */
    public void setPri(Integer pri) {
        this.pri = pri;
        this.modify("pri", pri);
    }

    /**
     * 设置 [适用阶段]
     */
    public void setStage(String stage) {
        this.stage = stage;
        this.modify("stage", stage);
    }

    /**
     * 设置 [scriptLocation]
     */
    public void setScriptlocation(String scriptlocation) {
        this.scriptlocation = scriptlocation;
        this.modify("scriptlocation", scriptlocation);
    }

    /**
     * 设置 [执行时间]
     */
    public void setLastrundate(Timestamp lastrundate) {
        this.lastrundate = lastrundate;
        this.modify("lastrundate", lastrundate);
    }

    /**
     * 格式化日期 [执行时间]
     */
    public String formatLastrundate() {
        if (this.lastrundate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastrundate);
    }
    /**
     * 设置 [关键词]
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
        this.modify("keywords", keywords);
    }

    /**
     * 设置 [scriptStatus]
     */
    public void setScriptstatus(String scriptstatus) {
        this.scriptstatus = scriptstatus;
        this.modify("scriptstatus", scriptstatus);
    }

    /**
     * 设置 [工具/框架]
     */
    public void setFrame(String frame) {
        this.frame = frame;
        this.modify("frame", frame);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus) {
        this.substatus = substatus;
        this.modify("substatus", substatus);
    }

    /**
     * 设置 [前置条件]
     */
    public void setPrecondition(String precondition) {
        this.precondition = precondition;
        this.modify("precondition", precondition);
    }

    /**
     * 设置 [执行人]
     */
    public void setLastrunner(String lastrunner) {
        this.lastrunner = lastrunner;
        this.modify("lastrunner", lastrunner);
    }

    /**
     * 设置 [来源用例版本]
     */
    public void setFromcaseversion(Integer fromcaseversion) {
        this.fromcaseversion = fromcaseversion;
        this.modify("fromcaseversion", fromcaseversion);
    }

    /**
     * 设置 [需求版本]
     */
    public void setStoryversion(Integer storyversion) {
        this.storyversion = storyversion;
        this.modify("storyversion", storyversion);
    }

    /**
     * 设置 [来源用例]
     */
    public void setFromcaseid(Long fromcaseid) {
        this.fromcaseid = fromcaseid;
        this.modify("fromcaseid", fromcaseid);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [来源Bug]
     */
    public void setFrombug(Long frombug) {
        this.frombug = frombug;
        this.modify("frombug", frombug);
    }

    /**
     * 设置 [相关需求]
     */
    public void setStory(Long story) {
        this.story = story;
        this.modify("story", story);
    }

    /**
     * 设置 [所属产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [所属库]
     */
    public void setLib(Long lib) {
        this.lib = lib;
        this.modify("lib", lib);
    }

    /**
     * 设置 [所属模块]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
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


