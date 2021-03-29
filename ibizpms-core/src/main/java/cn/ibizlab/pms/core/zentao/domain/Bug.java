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


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.baomidou.mybatisplus.annotation.*;
import cn.ibizlab.pms.util.domain.EntityMP;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

/**
 * 实体[Bug]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_bug", resultMap = "BugResultMap")
public class Bug extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 严重程度
     */
    @DEField(defaultValue = "3")
    @TableField(value = "`severity`")
    @JSONField(name = "severity")
    @JsonProperty("severity")
    private Integer severity;
    /**
     * 需求版本
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`storyversion`")
    @JSONField(name = "storyversion")
    @JsonProperty("storyversion")
    private Integer storyversion;
    /**
     * 版本名称
     */
    @TableField(exist = false)
    @JSONField(name = "buildname")
    @JsonProperty("buildname")
    private String buildname;
    /**
     * 相关Bug
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`linkbug`")
    @JSONField(name = "linkbug")
    @JsonProperty("linkbug")
    private String linkbug;
    /**
     * 激活日期
     */
    @TableField(value = "`activateddate`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "activateddate", format = "yyyy-MM-dd")
    @JsonProperty("activateddate")
    private Timestamp activateddate;
    /**
     * 过期天数
     */
    @TableField(exist = false)
    @JSONField(name = "overduebugs")
    @JsonProperty("overduebugs")
    private Integer overduebugs;
    /**
     * 创建版本
     */
    @TableField(exist = false)
    @JSONField(name = "createbuild")
    @JsonProperty("createbuild")
    private Integer createbuild;
    /**
     * 指派给
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`assignedto`")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    private String assignedto;
    /**
     * 解决方案
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`resolution`")
    @JSONField(name = "resolution")
    @JsonProperty("resolution")
    private String resolution;
    /**
     * 修改日期
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`lastediteddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastediteddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    private Timestamp lastediteddate;
    /**
     * 移动端图片
     */
    @TableField(exist = false)
    @JSONField(name = "mobimage")
    @JsonProperty("mobimage")
    private String mobimage;
    /**
     * result
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`result`")
    @JSONField(name = "result")
    @JsonProperty("result")
    private Integer result;
    /**
     * 关键词
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`keywords`")
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    private String keywords;
    /**
     * 是否收藏
     */
    @TableField(exist = false)
    @JSONField(name = "isfavorites")
    @JsonProperty("isfavorites")
    private String isfavorites;
    /**
     * 模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename1")
    @JsonProperty("modulename1")
    private String modulename1;
    /**
     * 由谁关闭
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`closedby`")
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    private String closedby;
    /**
     * 浏览器
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`browser`")
    @JSONField(name = "browser")
    @JsonProperty("browser")
    private String browser;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    private String noticeusers;
    /**
     * 重现步骤
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`steps`")
    @JSONField(name = "steps")
    @JsonProperty("steps")
    private String steps;
    /**
     * v2
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`v2`")
    @JSONField(name = "v2")
    @JsonProperty("v2")
    private String v2;
    /**
     * 是否确认
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`confirmed`")
    @JSONField(name = "confirmed")
    @JsonProperty("confirmed")
    private Integer confirmed;
    /**
     * 联系人
     */
    @TableField(exist = false)
    @JSONField(name = "mailtoconact")
    @JsonProperty("mailtoconact")
    private String mailtoconact;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "`openedby`")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    private String openedby;
    /**
     * 激活次数
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`activatedcount`")
    @JSONField(name = "activatedcount")
    @JsonProperty("activatedcount")
    private Integer activatedcount;
    /**
     * 创建日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "`openeddate`", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "openeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    private Timestamp openeddate;
    /**
     * 关闭日期
     */
    @TableField(value = "`closeddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "closeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    private Timestamp closeddate;
    /**
     * 抄送给
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`mailto`")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    private String mailto;
    /**
     * 指派日期
     */
    @TableField(value = "`assigneddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "assigneddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("assigneddate")
    private Timestamp assigneddate;
    /**
     * 截止日期
     */
    @TableField(value = "`deadline`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "deadline", format = "yyyy-MM-dd")
    @JsonProperty("deadline")
    private Timestamp deadline;
    /**
     * 标题颜色
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`color`")
    @JSONField(name = "color")
    @JsonProperty("color")
    private String color;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    private String comment;
    /**
     * 解决日期
     */
    @TableField(value = "`resolveddate`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "resolveddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("resolveddate")
    private Timestamp resolveddate;
    /**
     * Bug类型
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`type`")
    @JSONField(name = "type")
    @JsonProperty("type")
    private String type;
    /**
     * Bug状态
     */
    @DEField(defaultValue = "active")
    @TableField(value = "`status`")
    @JSONField(name = "status")
    @JsonProperty("status")
    private String status;
    /**
     * 影响版本
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`openedbuild`")
    @JSONField(name = "openedbuild")
    @JsonProperty("openedbuild")
    private String openedbuild;
    /**
     * 延期解决
     */
    @TableField(exist = false)
    @JSONField(name = "delayresolve")
    @JsonProperty("delayresolve")
    private String delayresolve;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    private String files;
    /**
     * 抄送给
     */
    @TableField(exist = false)
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    private String mailtopk;
    /**
     * v1
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`v1`")
    @JSONField(name = "v1")
    @JsonProperty("v1")
    private String v1;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "`deleted`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    private String deleted;
    /**
     * lines
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`lines`")
    @JSONField(name = "lines")
    @JsonProperty("lines")
    private String lines;
    /**
     * 子状态
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`substatus`")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    private String substatus;
    /**
     * 版本项目
     */
    @TableField(exist = false)
    @JSONField(name = "buildproject")
    @JsonProperty("buildproject")
    private String buildproject;
    /**
     * Bug编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    private Long id;
    /**
     * 延期
     */
    @TableField(exist = false)
    @JSONField(name = "delay")
    @JsonProperty("delay")
    private String delay;
    /**
     * found
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`found`")
    @JSONField(name = "found")
    @JsonProperty("found")
    private String found;
    /**
     * 解决者
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`resolvedby`")
    @JSONField(name = "resolvedby")
    @JsonProperty("resolvedby")
    private String resolvedby;
    /**
     * 解决版本
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`resolvedbuild`")
    @JSONField(name = "resolvedbuild")
    @JsonProperty("resolvedbuild")
    private String resolvedbuild;
    /**
     * 优先级
     */
    @DEField(defaultValue = "3")
    @TableField(value = "`pri`")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    private Integer pri;
    /**
     * 操作系统
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`os`")
    @JSONField(name = "os")
    @JsonProperty("os")
    private String os;
    /**
     * hardware
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`hardware`")
    @JSONField(name = "hardware")
    @JsonProperty("hardware")
    private String hardware;
    /**
     * 最后修改者
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`lasteditedby`")
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    private String lasteditedby;
    /**
     * Bug标题
     */
    @TableField(value = "`title`")
    @JSONField(name = "title")
    @JsonProperty("title")
    private String title;
    /**
     * 产品
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    private String productname;
    /**
     * 平台/分支
     */
    @TableField(exist = false)
    @JSONField(name = "branchname")
    @JsonProperty("branchname")
    private String branchname;
    /**
     * 相关任务
     */
    @TableField(exist = false)
    @JSONField(name = "taskname")
    @JsonProperty("taskname")
    private String taskname;
    /**
     * 相关用例
     */
    @TableField(exist = false)
    @JSONField(name = "casename")
    @JsonProperty("casename")
    private String casename;
    /**
     * 项目
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    private String projectname;
    /**
     * 相关需求
     */
    @TableField(exist = false)
    @JSONField(name = "storyname")
    @JsonProperty("storyname")
    private String storyname;
    /**
     * 用例版本
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`caseversion`")
    @JSONField(name = "caseversion")
    @JsonProperty("caseversion")
    private Integer caseversion;
    /**
     * 代码类型
     */
    @TableField(value = "`repotype`")
    @JSONField(name = "repotype")
    @JsonProperty("repotype")
    private String repotype;
    /**
     * 模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    private String modulename;
    /**
     * 转需求
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`tostory`")
    @JSONField(name = "tostory")
    @JsonProperty("tostory")
    private Long tostory;
    /**
     * 应用
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`entry`")
    @JSONField(name = "entry")
    @JsonProperty("entry")
    private Long entry;
    /**
     * 所属产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`product`")
    @JSONField(name = "product")
    @JsonProperty("product")
    private Long product;
    /**
     * 转任务
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`totask`")
    @JSONField(name = "totask")
    @JsonProperty("totask")
    private Long totask;
    /**
     * 所属计划
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`plan`")
    @JSONField(name = "plan")
    @JsonProperty("plan")
    private Long plan;
    /**
     * 所属模块
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`module`")
    @JSONField(name = "module")
    @JsonProperty("module")
    private Long module;
    /**
     * 平台/分支
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`branch`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    private Long branch;
    /**
     * 重复ID
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`duplicatebug`")
    @JSONField(name = "duplicatebug")
    @JsonProperty("duplicatebug")
    private Long duplicatebug;
    /**
     * 代码
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`repo`")
    @JSONField(name = "repo")
    @JsonProperty("repo")
    private Long repo;
    /**
     * 相关需求
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`story`")
    @JSONField(name = "story")
    @JsonProperty("story")
    private Long story;
    /**
     * 相关用例
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`case`")
    @JSONField(name = "ibizcase")
    @JsonProperty("ibizcase")
    private Long ibizcase;
    /**
     * 所属项目
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`project`")
    @JSONField(name = "project")
    @JsonProperty("project")
    private Long project;
    /**
     * 相关任务
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`task`")
    @JSONField(name = "task")
    @JsonProperty("task")
    private Long task;
    /**
     * 测试单
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`testtask`")
    @JSONField(name = "testtask")
    @JsonProperty("testtask")
    private Long testtask;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Branch ztrranch;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Bug ztduplicatebug;

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
    private cn.ibizlab.pms.core.zentao.domain.Entry ztentry;

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
    private cn.ibizlab.pms.core.zentao.domain.ProductPlan ztproductplan;

    /**
     * 产品
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Product ztproduct;

    /**
     * 项目
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Project ztproject;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Repo ztrepo;

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
    private cn.ibizlab.pms.core.zentao.domain.Story zttostory;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Task zttask;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Task zttotask;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.TestTask zttesttask;



    /**
     * 设置 [严重程度]
     */
    public void setSeverity(Integer severity) {
        this.severity = severity;
        this.modify("severity", severity);
    }

    /**
     * 设置 [需求版本]
     */
    public void setStoryversion(Integer storyversion) {
        this.storyversion = storyversion;
        this.modify("storyversion", storyversion);
    }

    /**
     * 设置 [相关Bug]
     */
    public void setLinkbug(String linkbug) {
        this.linkbug = linkbug;
        this.modify("linkbug", linkbug);
    }

    /**
     * 设置 [激活日期]
     */
    public void setActivateddate(Timestamp activateddate) {
        this.activateddate = activateddate;
        this.modify("activateddate", activateddate);
    }

    /**
     * 格式化日期 [激活日期]
     */
    public String formatActivateddate() {
        if (this.activateddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(activateddate);
    }
    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
        this.modify("assignedto", assignedto);
    }

    /**
     * 设置 [解决方案]
     */
    public void setResolution(String resolution) {
        this.resolution = resolution;
        this.modify("resolution", resolution);
    }

    /**
     * 设置 [result]
     */
    public void setResult(Integer result) {
        this.result = result;
        this.modify("result", result);
    }

    /**
     * 设置 [关键词]
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
        this.modify("keywords", keywords);
    }

    /**
     * 设置 [由谁关闭]
     */
    public void setClosedby(String closedby) {
        this.closedby = closedby;
        this.modify("closedby", closedby);
    }

    /**
     * 设置 [浏览器]
     */
    public void setBrowser(String browser) {
        this.browser = browser;
        this.modify("browser", browser);
    }

    /**
     * 设置 [重现步骤]
     */
    public void setSteps(String steps) {
        this.steps = steps;
        this.modify("steps", steps);
    }

    /**
     * 设置 [v2]
     */
    public void setV2(String v2) {
        this.v2 = v2;
        this.modify("v2", v2);
    }

    /**
     * 设置 [是否确认]
     */
    public void setConfirmed(Integer confirmed) {
        this.confirmed = confirmed;
        this.modify("confirmed", confirmed);
    }

    /**
     * 设置 [激活次数]
     */
    public void setActivatedcount(Integer activatedcount) {
        this.activatedcount = activatedcount;
        this.modify("activatedcount", activatedcount);
    }

    /**
     * 设置 [关闭日期]
     */
    public void setCloseddate(Timestamp closeddate) {
        this.closeddate = closeddate;
        this.modify("closeddate", closeddate);
    }

    /**
     * 格式化日期 [关闭日期]
     */
    public String formatCloseddate() {
        if (this.closeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(closeddate);
    }
    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [指派日期]
     */
    public void setAssigneddate(Timestamp assigneddate) {
        this.assigneddate = assigneddate;
        this.modify("assigneddate", assigneddate);
    }

    /**
     * 格式化日期 [指派日期]
     */
    public String formatAssigneddate() {
        if (this.assigneddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(assigneddate);
    }
    /**
     * 设置 [截止日期]
     */
    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
        this.modify("deadline", deadline);
    }

    /**
     * 格式化日期 [截止日期]
     */
    public String formatDeadline() {
        if (this.deadline == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(deadline);
    }
    /**
     * 设置 [标题颜色]
     */
    public void setColor(String color) {
        this.color = color;
        this.modify("color", color);
    }

    /**
     * 设置 [解决日期]
     */
    public void setResolveddate(Timestamp resolveddate) {
        this.resolveddate = resolveddate;
        this.modify("resolveddate", resolveddate);
    }

    /**
     * 格式化日期 [解决日期]
     */
    public String formatResolveddate() {
        if (this.resolveddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(resolveddate);
    }
    /**
     * 设置 [Bug类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [Bug状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [影响版本]
     */
    public void setOpenedbuild(String openedbuild) {
        this.openedbuild = openedbuild;
        this.modify("openedbuild", openedbuild);
    }

    /**
     * 设置 [v1]
     */
    public void setV1(String v1) {
        this.v1 = v1;
        this.modify("v1", v1);
    }

    /**
     * 设置 [lines]
     */
    public void setLines(String lines) {
        this.lines = lines;
        this.modify("lines", lines);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus) {
        this.substatus = substatus;
        this.modify("substatus", substatus);
    }

    /**
     * 设置 [found]
     */
    public void setFound(String found) {
        this.found = found;
        this.modify("found", found);
    }

    /**
     * 设置 [解决者]
     */
    public void setResolvedby(String resolvedby) {
        this.resolvedby = resolvedby;
        this.modify("resolvedby", resolvedby);
    }

    /**
     * 设置 [解决版本]
     */
    public void setResolvedbuild(String resolvedbuild) {
        this.resolvedbuild = resolvedbuild;
        this.modify("resolvedbuild", resolvedbuild);
    }

    /**
     * 设置 [优先级]
     */
    public void setPri(Integer pri) {
        this.pri = pri;
        this.modify("pri", pri);
    }

    /**
     * 设置 [操作系统]
     */
    public void setOs(String os) {
        this.os = os;
        this.modify("os", os);
    }

    /**
     * 设置 [hardware]
     */
    public void setHardware(String hardware) {
        this.hardware = hardware;
        this.modify("hardware", hardware);
    }

    /**
     * 设置 [Bug标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [用例版本]
     */
    public void setCaseversion(Integer caseversion) {
        this.caseversion = caseversion;
        this.modify("caseversion", caseversion);
    }

    /**
     * 设置 [代码类型]
     */
    public void setRepotype(String repotype) {
        this.repotype = repotype;
        this.modify("repotype", repotype);
    }

    /**
     * 设置 [转需求]
     */
    public void setTostory(Long tostory) {
        this.tostory = tostory;
        this.modify("tostory", tostory);
    }

    /**
     * 设置 [应用]
     */
    public void setEntry(Long entry) {
        this.entry = entry;
        this.modify("entry", entry);
    }

    /**
     * 设置 [所属产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [转任务]
     */
    public void setTotask(Long totask) {
        this.totask = totask;
        this.modify("totask", totask);
    }

    /**
     * 设置 [所属计划]
     */
    public void setPlan(Long plan) {
        this.plan = plan;
        this.modify("plan", plan);
    }

    /**
     * 设置 [所属模块]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [重复ID]
     */
    public void setDuplicatebug(Long duplicatebug) {
        this.duplicatebug = duplicatebug;
        this.modify("duplicatebug", duplicatebug);
    }

    /**
     * 设置 [代码]
     */
    public void setRepo(Long repo) {
        this.repo = repo;
        this.modify("repo", repo);
    }

    /**
     * 设置 [相关需求]
     */
    public void setStory(Long story) {
        this.story = story;
        this.modify("story", story);
    }

    /**
     * 设置 [相关用例]
     */
    public void setIbizcase(Long ibizcase) {
        this.ibizcase = ibizcase;
        this.modify("case", ibizcase);
    }

    /**
     * 设置 [所属项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
    }

    /**
     * 设置 [相关任务]
     */
    public void setTask(Long task) {
        this.task = task;
        this.modify("task", task);
    }

    /**
     * 设置 [测试单]
     */
    public void setTesttask(Long testtask) {
        this.testtask = testtask;
        this.modify("testtask", testtask);
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


