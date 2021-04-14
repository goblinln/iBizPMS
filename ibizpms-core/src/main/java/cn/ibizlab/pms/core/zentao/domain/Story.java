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
 * 实体[需求]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_story", resultMap = "StoryResultMap")
@ApiModel("需求")
public class Story extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 指派给
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "assignedto")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @ApiModelProperty("指派给")
    private String assignedto;
    /**
     * 所属模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename1")
    @JsonProperty("modulename1")
    @ApiModelProperty("所属模块名称")
    private String modulename1;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 细分需求
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "childstories")
    @JSONField(name = "childstories")
    @JsonProperty("childstories")
    @ApiModelProperty("细分需求")
    private String childstories;
    /**
     * IBIZ标识
     */
    @DEField(name = "ibiz_id")
    @TableField(value = "ibiz_id")
    @JSONField(name = "ibiz_id")
    @JsonProperty("ibiz_id")
    @ApiModelProperty("IBIZ标识")
    private String ibizId;
    /**
     * 所属计划
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "plan")
    @JSONField(name = "plan")
    @JsonProperty("plan")
    @ApiModelProperty("所属计划")
    private String plan;
    /**
     * 版本号
     */
    @DEField(defaultValue = "1")
    @TableField(value = "version")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("版本号")
    private Integer version;
    /**
     * 指派日期
     */
    @TableField(value = "assigneddate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "assigneddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("assigneddate")
    @ApiModelProperty("指派日期")
    private Timestamp assigneddate;
    /**
     * 故事点
     */
    @TableField(value = "storypoints")
    @JSONField(name = "storypoints")
    @JsonProperty("storypoints")
    @ApiModelProperty("故事点")
    private String storypoints;
    /**
     * 来源对象名称
     */
    @TableField(value = "sourcename")
    @JSONField(name = "sourcename")
    @JsonProperty("sourcename")
    @ApiModelProperty("来源对象名称")
    private String sourcename;
    /**
     * 需求提供时间
     */
    @TableField(value = "storyprovidedate")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "storyprovidedate", format = "yyyy-MM-dd")
    @JsonProperty("storyprovidedate")
    @ApiModelProperty("需求提供时间")
    private Timestamp storyprovidedate;
    /**
     * 是否子需求
     */
    @TableField(exist = false)
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @ApiModelProperty("是否子需求")
    private String isleaf;
    /**
     * 优先级
     */
    @DEField(defaultValue = "3")
    @TableField(value = "pri")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;
    /**
     * 来源对象标识
     */
    @TableField(value = "sourceid")
    @JSONField(name = "sourceid")
    @JsonProperty("sourceid")
    @ApiModelProperty("来源对象标识")
    private String sourceid;
    /**
     * 相关需求
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "linkstories")
    @JSONField(name = "linkstories")
    @JsonProperty("linkstories")
    @ApiModelProperty("相关需求")
    private String linkstories;
    /**
     * 评审结果
     */
    @TableField(exist = false)
    @JSONField(name = "assessresult")
    @JsonProperty("assessresult")
    @ApiModelProperty("评审结果")
    private String assessresult;
    /**
     * 当前状态
     */
    @DEField(defaultValue = "draft")
    @TableField(value = "status")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("当前状态")
    private String status;
    /**
     * 抄送给
     */
    @TableField(exist = false)
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    @ApiModelProperty("抄送给")
    private String mailtopk;
    /**
     * 预计工时
     */
    @DEField(defaultValue = "0")
    @TableField(value = "estimate")
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    @ApiModelProperty("预计工时")
    private Double estimate;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 评审时间
     */
    @TableField(value = "revieweddate")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "revieweddate", format = "yyyy-MM-dd")
    @JsonProperty("revieweddate")
    @ApiModelProperty("评审时间")
    private Timestamp revieweddate;
    /**
     * 需求名称
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("需求名称")
    private String title;
    /**
     * 联系人
     */
    @TableField(exist = false)
    @JSONField(name = "mailtoconact")
    @JsonProperty("mailtoconact")
    @ApiModelProperty("联系人")
    private String mailtoconact;
    /**
     * 来源备注
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "sourcenote")
    @JSONField(name = "sourcenote")
    @JsonProperty("sourcenote")
    @ApiModelProperty("来源备注")
    private String sourcenote;
    /**
     * 版本号
     */
    @TableField(exist = false)
    @JSONField(name = "versionc")
    @JsonProperty("versionc")
    @ApiModelProperty("版本号")
    private String versionc;
    /**
     * 由谁评审
     */
    @TableField(value = "reviewedby")
    @JSONField(name = "reviewedby")
    @JsonProperty("reviewedby")
    @ApiModelProperty("由谁评审")
    private String reviewedby;
    /**
     * 子状态
     */
    @TableField(value = "substatus")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @ApiModelProperty("子状态")
    private String substatus;
    /**
     * 设置阶段者
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "stagedby")
    @JSONField(name = "stagedby")
    @JsonProperty("stagedby")
    @ApiModelProperty("设置阶段者")
    private String stagedby;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "openedby")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @ApiModelProperty("由谁创建")
    private String openedby;
    /**
     * 创建日期
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "openeddate", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "openeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("创建日期")
    private Timestamp openeddate;
    /**
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 来源对象
     */
    @DEField(name = "ibiz_sourceobject")
    @TableField(value = "ibiz_sourceobject")
    @JSONField(name = "ibiz_sourceobject")
    @JsonProperty("ibiz_sourceobject")
    @ApiModelProperty("来源对象")
    private String ibizSourceobject;
    /**
     * 需求来源
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "source")
    @JSONField(name = "source")
    @JsonProperty("source")
    @ApiModelProperty("需求来源")
    private String source;
    /**
     * 需求最晚完成时间
     */
    @TableField(value = "storylatestfinisheddate")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "storylatestfinisheddate", format = "yyyy-MM-dd")
    @JsonProperty("storylatestfinisheddate")
    @ApiModelProperty("需求最晚完成时间")
    private Timestamp storylatestfinisheddate;
    /**
     * 不需要评审
     */
    @TableField(exist = false)
    @JSONField(name = "neednotreview")
    @JsonProperty("neednotreview")
    @ApiModelProperty("不需要评审")
    private String neednotreview;
    /**
     * 是否可以细分
     */
    @TableField(exist = false)
    @JSONField(name = "ischild")
    @JsonProperty("ischild")
    @ApiModelProperty("是否可以细分")
    private String ischild;
    /**
     * 关闭原因
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "closedreason")
    @JSONField(name = "closedreason")
    @JsonProperty("closedreason")
    @ApiModelProperty("关闭原因")
    private String closedreason;
    /**
     * 标题颜色
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "color")
    @JSONField(name = "color")
    @JsonProperty("color")
    @ApiModelProperty("标题颜色")
    private String color;
    /**
     * orgid
     */
    @TableField(exist = false)
    @JSONField(name = "orgid")
    @JsonProperty("orgid")
    @ApiModelProperty("orgid")
    private String orgid;
    /**
     * 抄送给
     */
    @TableField(value = "mailto")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 是否收藏
     */
    @TableField(exist = false)
    @JSONField(name = "isfavorites")
    @JsonProperty("isfavorites")
    @ApiModelProperty("是否收藏")
    private String isfavorites;
    /**
     * 来源对象
     */
    @TableField(value = "sourceobject")
    @JSONField(name = "sourceobject")
    @JsonProperty("sourceobject")
    @ApiModelProperty("来源对象")
    private String sourceobject;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID, logicval = "0", logicdelval = "1")
    @TableLogic(value = "0", delval = "1")
    @TableField(value = "deleted")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 关键词
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "keywords")
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    @ApiModelProperty("关键词")
    private String keywords;
    /**
     * 最后修改
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "lasteditedby")
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    @ApiModelProperty("最后修改")
    private String lasteditedby;
    /**
     * 所处阶段
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "stage")
    @JSONField(name = "stage")
    @JsonProperty("stage")
    @ApiModelProperty("所处阶段")
    private String stage;
    /**
     * 项目
     */
    @TableField(exist = false)
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目")
    private Long project;
    /**
     * 关闭日期	
     */
    @TableField(value = "closeddate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "closeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭日期	")
    private Timestamp closeddate;
    /**
     * 需求描述
     */
    @TableField(exist = false)
    @JSONField(name = "spec")
    @JsonProperty("spec")
    @ApiModelProperty("需求描述")
    private String spec;
    /**
     * 来源对象名称
     */
    @DEField(name = "ibiz_sourcename")
    @TableField(value = "ibiz_sourcename")
    @JSONField(name = "ibiz_sourcename")
    @JsonProperty("ibiz_sourcename")
    @ApiModelProperty("来源对象名称")
    private String ibizSourcename;
    /**
     * 指派给（选择）
     */
    @TableField(exist = false)
    @JSONField(name = "assignedtopk")
    @JsonProperty("assignedtopk")
    @ApiModelProperty("指派给（选择）")
    private String assignedtopk;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * acllist
     */
    @TableField(exist = false)
    @JSONField(name = "acllist")
    @JsonProperty("acllist")
    @ApiModelProperty("acllist")
    private String acllist;
    /**
     * 验收标准
     */
    @TableField(exist = false)
    @JSONField(name = "verify")
    @JsonProperty("verify")
    @ApiModelProperty("验收标准")
    private String verify;
    /**
     * 由谁关闭
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "closedby")
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @ApiModelProperty("由谁关闭")
    private String closedby;
    /**
     * acl
     */
    @TableField(exist = false)
    @JSONField(name = "acl")
    @JsonProperty("acl")
    @ApiModelProperty("acl")
    private String acl;
    /**
     * 评审结果
     */
    @TableField(exist = false)
    @JSONField(name = "result")
    @JsonProperty("result")
    @ApiModelProperty("评审结果")
    private String result;
    /**
     * 需求类型
     */
    @DEField(defaultValue = "story")
    @TableField(value = "type")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("需求类型")
    private String type;
    /**
     * 最后修改日期
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "lastediteddate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastediteddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    @ApiModelProperty("最后修改日期")
    private Timestamp lastediteddate;
    /**
     * 来源对象标识
     */
    @DEField(name = "ibiz_sourceid")
    @TableField(value = "ibiz_sourceid")
    @JSONField(name = "ibiz_sourceid")
    @JsonProperty("ibiz_sourceid")
    @ApiModelProperty("来源对象标识")
    private String ibizSourceid;
    /**
     * 之前的版本
     */
    @TableField(exist = false)
    @JSONField(name = "preversion")
    @JsonProperty("preversion")
    @ApiModelProperty("之前的版本")
    private Integer preversion;
    /**
     * 需求提供人
     */
    @TableField(value = "storyprovider")
    @JSONField(name = "storyprovider")
    @JsonProperty("storyprovider")
    @ApiModelProperty("需求提供人")
    private String storyprovider;
    /**
     * MDEPTID
     */
    @TableField(exist = false)
    @JSONField(name = "mdeptid")
    @JsonProperty("mdeptid")
    @ApiModelProperty("MDEPTID")
    private String mdeptid;
    /**
     * 模块路径
     */
    @TableField(exist = false)
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("模块路径")
    private String path;
    /**
     * 父需求名称
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @ApiModelProperty("父需求名称")
    private String parentname;
    /**
     * 所属模块名称
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @ApiModelProperty("所属模块名称")
    private String modulename;
    /**
     * 产品名称
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("产品名称")
    private String productname;
    /**
     * 平台/分支
     */
    @TableField(exist = false)
    @JSONField(name = "branchname")
    @JsonProperty("branchname")
    @ApiModelProperty("平台/分支")
    private String branchname;
    /**
     * 来源Bug
     */
    @DEField(defaultValue = "0")
    @TableField(value = "frombug")
    @JSONField(name = "frombug")
    @JsonProperty("frombug")
    @ApiModelProperty("来源Bug")
    private Long frombug;
    /**
     * 父需求
     */
    @DEField(defaultValue = "0")
    @TableField(value = "parent")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("父需求")
    private Long parent;
    /**
     * 所属模块
     */
    @DEField(defaultValue = "0")
    @TableField(value = "module")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("所属模块")
    private Long module;
    /**
     * 所属产品
     */
    @DEField(defaultValue = "0")
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("所属产品")
    private Long product;
    /**
     * 重复需求ID
     */
    @DEField(defaultValue = "0")
    @TableField(value = "duplicatestory")
    @JSONField(name = "duplicatestory")
    @JsonProperty("duplicatestory")
    @ApiModelProperty("重复需求ID")
    private Long duplicatestory;
    /**
     * 平台/分支
     */
    @DEField(defaultValue = "0")
    @TableField(value = "branch")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @ApiModelProperty("平台/分支")
    private Long branch;
    /**
     * 转Bug
     */
    @DEField(defaultValue = "0")
    @TableField(value = "tobug")
    @JSONField(name = "tobug")
    @JsonProperty("tobug")
    @ApiModelProperty("转Bug")
    private Long tobug;
    /**
     * 归属组织
     */
    @DEField(preType = DEPredefinedFieldType.ORGID)
    @TableField(value = "org")
    @JSONField(name = "org")
    @JsonProperty("org")
    @ApiModelProperty("归属组织")
    private String org;
    /**
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "dept")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibiz.domain.ProductModule ibzproductmodule;

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
    private cn.ibizlab.pms.core.zentao.domain.Bug zttobug;

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
    private cn.ibizlab.pms.core.zentao.domain.Story ztstoryduplicatestory;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Story ztparent;



    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
        this.modify("assignedto", assignedto);
    }

    /**
     * 设置 [细分需求]
     */
    public void setChildstories(String childstories) {
        this.childstories = childstories;
        this.modify("childstories", childstories);
    }

    /**
     * 设置 [IBIZ标识]
     */
    public void setIbizId(String ibizId) {
        this.ibizId = ibizId;
        this.modify("ibiz_id", ibizId);
    }

    /**
     * 设置 [所属计划]
     */
    public void setPlan(String plan) {
        this.plan = plan;
        this.modify("plan", plan);
    }

    /**
     * 设置 [版本号]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
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
     * 设置 [故事点]
     */
    public void setStorypoints(String storypoints) {
        this.storypoints = storypoints;
        this.modify("storypoints", storypoints);
    }

    /**
     * 设置 [来源对象名称]
     */
    public void setSourcename(String sourcename) {
        this.sourcename = sourcename;
        this.modify("sourcename", sourcename);
    }

    /**
     * 设置 [需求提供时间]
     */
    public void setStoryprovidedate(Timestamp storyprovidedate) {
        this.storyprovidedate = storyprovidedate;
        this.modify("storyprovidedate", storyprovidedate);
    }

    /**
     * 格式化日期 [需求提供时间]
     */
    public String formatStoryprovidedate() {
        if (this.storyprovidedate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(storyprovidedate);
    }
    /**
     * 设置 [优先级]
     */
    public void setPri(Integer pri) {
        this.pri = pri;
        this.modify("pri", pri);
    }

    /**
     * 设置 [来源对象标识]
     */
    public void setSourceid(String sourceid) {
        this.sourceid = sourceid;
        this.modify("sourceid", sourceid);
    }

    /**
     * 设置 [相关需求]
     */
    public void setLinkstories(String linkstories) {
        this.linkstories = linkstories;
        this.modify("linkstories", linkstories);
    }

    /**
     * 设置 [当前状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [预计工时]
     */
    public void setEstimate(Double estimate) {
        this.estimate = estimate;
        this.modify("estimate", estimate);
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
     * 设置 [需求名称]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [来源备注]
     */
    public void setSourcenote(String sourcenote) {
        this.sourcenote = sourcenote;
        this.modify("sourcenote", sourcenote);
    }

    /**
     * 设置 [由谁评审]
     */
    public void setReviewedby(String reviewedby) {
        this.reviewedby = reviewedby;
        this.modify("reviewedby", reviewedby);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus) {
        this.substatus = substatus;
        this.modify("substatus", substatus);
    }

    /**
     * 设置 [设置阶段者]
     */
    public void setStagedby(String stagedby) {
        this.stagedby = stagedby;
        this.modify("stagedby", stagedby);
    }

    /**
     * 设置 [来源对象]
     */
    public void setIbizSourceobject(String ibizSourceobject) {
        this.ibizSourceobject = ibizSourceobject;
        this.modify("ibiz_sourceobject", ibizSourceobject);
    }

    /**
     * 设置 [需求来源]
     */
    public void setSource(String source) {
        this.source = source;
        this.modify("source", source);
    }

    /**
     * 设置 [需求最晚完成时间]
     */
    public void setStorylatestfinisheddate(Timestamp storylatestfinisheddate) {
        this.storylatestfinisheddate = storylatestfinisheddate;
        this.modify("storylatestfinisheddate", storylatestfinisheddate);
    }

    /**
     * 格式化日期 [需求最晚完成时间]
     */
    public String formatStorylatestfinisheddate() {
        if (this.storylatestfinisheddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(storylatestfinisheddate);
    }
    /**
     * 设置 [关闭原因]
     */
    public void setClosedreason(String closedreason) {
        this.closedreason = closedreason;
        this.modify("closedreason", closedreason);
    }

    /**
     * 设置 [标题颜色]
     */
    public void setColor(String color) {
        this.color = color;
        this.modify("color", color);
    }

    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [来源对象]
     */
    public void setSourceobject(String sourceobject) {
        this.sourceobject = sourceobject;
        this.modify("sourceobject", sourceobject);
    }

    /**
     * 设置 [关键词]
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
        this.modify("keywords", keywords);
    }

    /**
     * 设置 [所处阶段]
     */
    public void setStage(String stage) {
        this.stage = stage;
        this.modify("stage", stage);
    }

    /**
     * 设置 [关闭日期	]
     */
    public void setCloseddate(Timestamp closeddate) {
        this.closeddate = closeddate;
        this.modify("closeddate", closeddate);
    }

    /**
     * 格式化日期 [关闭日期	]
     */
    public String formatCloseddate() {
        if (this.closeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(closeddate);
    }
    /**
     * 设置 [来源对象名称]
     */
    public void setIbizSourcename(String ibizSourcename) {
        this.ibizSourcename = ibizSourcename;
        this.modify("ibiz_sourcename", ibizSourcename);
    }

    /**
     * 设置 [由谁关闭]
     */
    public void setClosedby(String closedby) {
        this.closedby = closedby;
        this.modify("closedby", closedby);
    }

    /**
     * 设置 [需求类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [来源对象标识]
     */
    public void setIbizSourceid(String ibizSourceid) {
        this.ibizSourceid = ibizSourceid;
        this.modify("ibiz_sourceid", ibizSourceid);
    }

    /**
     * 设置 [需求提供人]
     */
    public void setStoryprovider(String storyprovider) {
        this.storyprovider = storyprovider;
        this.modify("storyprovider", storyprovider);
    }

    /**
     * 设置 [来源Bug]
     */
    public void setFrombug(Long frombug) {
        this.frombug = frombug;
        this.modify("frombug", frombug);
    }

    /**
     * 设置 [父需求]
     */
    public void setParent(Long parent) {
        this.parent = parent;
        this.modify("parent", parent);
    }

    /**
     * 设置 [所属模块]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
    }

    /**
     * 设置 [所属产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [重复需求ID]
     */
    public void setDuplicatestory(Long duplicatestory) {
        this.duplicatestory = duplicatestory;
        this.modify("duplicatestory", duplicatestory);
    }

    /**
     * 设置 [平台/分支]
     */
    public void setBranch(Long branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [转Bug]
     */
    public void setTobug(Long tobug) {
        this.tobug = tobug;
        this.modify("tobug", tobug);
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


