package cn.ibizlab.pms.core.ibizpro.domain;

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
@TableName(value = "zt_story", resultMap = "IBZProStoryResultMap")
@ApiModel("需求")
public class IBZProStory extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 版本号
     */
    @DEField(defaultValue = "1")
    @TableField(value = "`VERSION`")
    @JSONField(name = "version")
    @JsonProperty("version")
    @ApiModelProperty("版本号")
    private Integer version;
    /**
     * 来源备注
     */
    @TableField(value = "`SOURCENOTE`")
    @JSONField(name = "sourcenote")
    @JsonProperty("sourcenote")
    @ApiModelProperty("来源备注")
    private String sourcenote;
    /**
     * 抄送给
     */
    @TableField(value = "`MAILTO`")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 设置阶段者
     */
    @TableField(value = "`STAGEDBY`")
    @JSONField(name = "stagedby")
    @JsonProperty("stagedby")
    @ApiModelProperty("设置阶段者")
    private String stagedby;
    /**
     * 已删除
     */
    @DEField(defaultValue = "0", preType = DEPredefinedFieldType.LOGICVALID)
    @TableField(value = "`DELETED`")
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @ApiModelProperty("已删除")
    private String deleted;
    /**
     * 来源对象名称
     */
    @DEField(name = "ibiz_sourcename")
    @TableField(value = "`IBIZ_SOURCENAME`")
    @JSONField(name = "ibiz_sourcename")
    @JsonProperty("ibiz_sourcename")
    @ApiModelProperty("来源对象名称")
    private String ibizSourcename;
    /**
     * 优先级
     */
    @TableField(value = "`PRI`")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;
    /**
     * 需求阶段
     */
    @TableField(value = "`STAGE`")
    @JSONField(name = "stage")
    @JsonProperty("stage")
    @ApiModelProperty("需求阶段")
    private String stage;
    /**
     * 由谁关闭
     */
    @TableField(value = "`CLOSEDBY`")
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @ApiModelProperty("由谁关闭")
    private String closedby;
    /**
     * 需求描述
     */
    @TableField(exist = false)
    @JSONField(name = "spec")
    @JsonProperty("spec")
    @ApiModelProperty("需求描述")
    private String spec;
    /**
     * 关闭日期	
     */
    @TableField(value = "`CLOSEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "closeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭日期	")
    private Timestamp closeddate;
    /**
     * 相关需求
     */
    @TableField(value = "`LINKSTORIES`")
    @JSONField(name = "linkstories")
    @JsonProperty("linkstories")
    @ApiModelProperty("相关需求")
    private String linkstories;
    /**
     * 需求类型
     */
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("需求类型")
    private String type;
    /**
     * 重复需求
     */
    @TableField(value = "`DUPLICATESTORY`")
    @JSONField(name = "duplicatestory")
    @JsonProperty("duplicatestory")
    @ApiModelProperty("重复需求")
    private Integer duplicatestory;
    /**
     * 平台
     */
    @TableField(value = "`BRANCH`")
    @JSONField(name = "branch")
    @JsonProperty("branch")
    @ApiModelProperty("平台")
    private Integer branch;
    /**
     * 关键词
     */
    @TableField(value = "`KEYWORDS`")
    @JSONField(name = "keywords")
    @JsonProperty("keywords")
    @ApiModelProperty("关键词")
    private String keywords;
    /**
     * 需求细分
     */
    @TableField(value = "`CHILDSTORIES`")
    @JSONField(name = "childstories")
    @JsonProperty("childstories")
    @ApiModelProperty("需求细分")
    private String childstories;
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
     * 转Bug
     */
    @TableField(value = "`TOBUG`")
    @JSONField(name = "tobug")
    @JsonProperty("tobug")
    @ApiModelProperty("转Bug")
    private Integer tobug;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
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
     * 关闭原因
     */
    @TableField(value = "`CLOSEDREASON`")
    @JSONField(name = "closedreason")
    @JsonProperty("closedreason")
    @ApiModelProperty("关闭原因")
    private String closedreason;
    /**
     * 项目
     */
    @TableField(exist = false)
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目")
    private String project;
    /**
     * 来源Bug
     */
    @TableField(value = "`FROMBUG`")
    @JSONField(name = "frombug")
    @JsonProperty("frombug")
    @ApiModelProperty("来源Bug")
    private Integer frombug;
    /**
     * 最后修改者
     */
    @TableField(value = "`LASTEDITEDBY`")
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    @ApiModelProperty("最后修改者")
    private String lasteditedby;
    /**
     * 指派日期
     */
    @TableField(value = "`ASSIGNEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "assigneddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("assigneddate")
    @ApiModelProperty("指派日期")
    private Timestamp assigneddate;
    /**
     * 子状态
     */
    @TableField(value = "`SUBSTATUS`")
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @ApiModelProperty("子状态")
    private String substatus;
    /**
     * 来源对象标识
     */
    @DEField(name = "ibiz_sourceid")
    @TableField(value = "`IBIZ_SOURCEID`")
    @JSONField(name = "ibiz_sourceid")
    @JsonProperty("ibiz_sourceid")
    @ApiModelProperty("来源对象标识")
    private String ibizSourceid;
    /**
     * 需求名称
     */
    @TableField(value = "`TITLE`")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("需求名称")
    private String title;
    /**
     * 最后修改日期
     */
    @TableField(value = "`LASTEDITEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastediteddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    @ApiModelProperty("最后修改日期")
    private Timestamp lastediteddate;
    /**
     * 来源对象
     */
    @DEField(name = "ibiz_sourceobject")
    @TableField(value = "`IBIZ_SOURCEOBJECT`")
    @JSONField(name = "ibiz_sourceobject")
    @JsonProperty("ibiz_sourceobject")
    @ApiModelProperty("来源对象")
    private String ibizSourceobject;
    /**
     * IBIZ标识
     */
    @DEField(name = "ibiz_id")
    @TableField(value = "`IBIZ_ID`")
    @JSONField(name = "ibizid")
    @JsonProperty("ibizid")
    @ApiModelProperty("IBIZ标识")
    private String ibizid;
    /**
     * 验收标准
     */
    @TableField(exist = false)
    @JSONField(name = "verify")
    @JsonProperty("verify")
    @ApiModelProperty("验收标准")
    private String verify;
    /**
     * 由谁评审
     */
    @TableField(value = "`REVIEWEDBY`")
    @JSONField(name = "reviewedby")
    @JsonProperty("reviewedby")
    @ApiModelProperty("由谁评审")
    private String reviewedby;
    /**
     * 指派给
     */
    @TableField(value = "`ASSIGNEDTO`")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @ApiModelProperty("指派给")
    private String assignedto;
    /**
     * 需求来源
     */
    @DEField(defaultValue = "iBiz")
    @TableField(value = "`SOURCE`")
    @JSONField(name = "source")
    @JsonProperty("source")
    @ApiModelProperty("需求来源")
    private String source;
    /**
     * 预计工时
     */
    @TableField(value = "`ESTIMATE`")
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    @ApiModelProperty("预计工时")
    private Double estimate;
    /**
     * 由谁创建
     */
    @TableField(value = "`OPENEDBY`")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @ApiModelProperty("由谁创建")
    private String openedby;
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
     * 颜色
     */
    @TableField(value = "`COLOR`")
    @JSONField(name = "color")
    @JsonProperty("color")
    @ApiModelProperty("颜色")
    private String color;
    /**
     * 状态
     */
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("状态")
    private String status;
    /**
     * 编号
     */
    @TableField(value = "`PRODUCT`")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("编号")
    private Long product;
    /**
     * id
     */
    @TableField(value = "`MODULE`")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("id")
    private Long module;

    /**
     * 产品
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibizpro.domain.IBZProProduct ibzproproduct;

    /**
     * 需求模块
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.ibizpro.domain.IBZProStoryModule ibzprostorymodule;



    /**
     * 设置 [版本号]
     */
    public void setVersion(Integer version) {
        this.version = version;
        this.modify("version", version);
    }

    /**
     * 设置 [来源备注]
     */
    public void setSourcenote(String sourcenote) {
        this.sourcenote = sourcenote;
        this.modify("sourcenote", sourcenote);
    }

    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [设置阶段者]
     */
    public void setStagedby(String stagedby) {
        this.stagedby = stagedby;
        this.modify("stagedby", stagedby);
    }

    /**
     * 设置 [来源对象名称]
     */
    public void setIbizSourcename(String ibizSourcename) {
        this.ibizSourcename = ibizSourcename;
        this.modify("ibiz_sourcename", ibizSourcename);
    }

    /**
     * 设置 [优先级]
     */
    public void setPri(Integer pri) {
        this.pri = pri;
        this.modify("pri", pri);
    }

    /**
     * 设置 [需求阶段]
     */
    public void setStage(String stage) {
        this.stage = stage;
        this.modify("stage", stage);
    }

    /**
     * 设置 [由谁关闭]
     */
    public void setClosedby(String closedby) {
        this.closedby = closedby;
        this.modify("closedby", closedby);
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
     * 设置 [相关需求]
     */
    public void setLinkstories(String linkstories) {
        this.linkstories = linkstories;
        this.modify("linkstories", linkstories);
    }

    /**
     * 设置 [需求类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [重复需求]
     */
    public void setDuplicatestory(Integer duplicatestory) {
        this.duplicatestory = duplicatestory;
        this.modify("duplicatestory", duplicatestory);
    }

    /**
     * 设置 [平台]
     */
    public void setBranch(Integer branch) {
        this.branch = branch;
        this.modify("branch", branch);
    }

    /**
     * 设置 [关键词]
     */
    public void setKeywords(String keywords) {
        this.keywords = keywords;
        this.modify("keywords", keywords);
    }

    /**
     * 设置 [需求细分]
     */
    public void setChildstories(String childstories) {
        this.childstories = childstories;
        this.modify("childstories", childstories);
    }

    /**
     * 设置 [转Bug]
     */
    public void setTobug(Integer tobug) {
        this.tobug = tobug;
        this.modify("tobug", tobug);
    }

    /**
     * 设置 [关闭原因]
     */
    public void setClosedreason(String closedreason) {
        this.closedreason = closedreason;
        this.modify("closedreason", closedreason);
    }

    /**
     * 设置 [来源Bug]
     */
    public void setFrombug(Integer frombug) {
        this.frombug = frombug;
        this.modify("frombug", frombug);
    }

    /**
     * 设置 [最后修改者]
     */
    public void setLasteditedby(String lasteditedby) {
        this.lasteditedby = lasteditedby;
        this.modify("lasteditedby", lasteditedby);
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
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus) {
        this.substatus = substatus;
        this.modify("substatus", substatus);
    }

    /**
     * 设置 [来源对象标识]
     */
    public void setIbizSourceid(String ibizSourceid) {
        this.ibizSourceid = ibizSourceid;
        this.modify("ibiz_sourceid", ibizSourceid);
    }

    /**
     * 设置 [需求名称]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [最后修改日期]
     */
    public void setLastediteddate(Timestamp lastediteddate) {
        this.lastediteddate = lastediteddate;
        this.modify("lastediteddate", lastediteddate);
    }

    /**
     * 格式化日期 [最后修改日期]
     */
    public String formatLastediteddate() {
        if (this.lastediteddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(lastediteddate);
    }
    /**
     * 设置 [来源对象]
     */
    public void setIbizSourceobject(String ibizSourceobject) {
        this.ibizSourceobject = ibizSourceobject;
        this.modify("ibiz_sourceobject", ibizSourceobject);
    }

    /**
     * 设置 [IBIZ标识]
     */
    public void setIbizid(String ibizid) {
        this.ibizid = ibizid;
        this.modify("ibiz_id", ibizid);
    }

    /**
     * 设置 [由谁评审]
     */
    public void setReviewedby(String reviewedby) {
        this.reviewedby = reviewedby;
        this.modify("reviewedby", reviewedby);
    }

    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
        this.modify("assignedto", assignedto);
    }

    /**
     * 设置 [需求来源]
     */
    public void setSource(String source) {
        this.source = source;
        this.modify("source", source);
    }

    /**
     * 设置 [预计工时]
     */
    public void setEstimate(Double estimate) {
        this.estimate = estimate;
        this.modify("estimate", estimate);
    }

    /**
     * 设置 [由谁创建]
     */
    public void setOpenedby(String openedby) {
        this.openedby = openedby;
        this.modify("openedby", openedby);
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
     * 设置 [颜色]
     */
    public void setColor(String color) {
        this.color = color;
        this.modify("color", color);
    }

    /**
     * 设置 [状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [编号]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [id]
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


