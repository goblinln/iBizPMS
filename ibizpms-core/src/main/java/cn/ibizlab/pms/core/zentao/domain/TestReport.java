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
 * 实体[测试报告]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_testreport", resultMap = "TestReportResultMap")
@ApiModel("测试报告")
public class TestReport extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目报告产品数
     */
    @TableField(exist = false)
    @JSONField(name = "productcnt")
    @JsonProperty("productcnt")
    @ApiModelProperty("项目报告产品数")
    private Integer productcnt;
    /**
     * 概况
     */
    @TableField(exist = false)
    @JSONField(name = "overviews")
    @JsonProperty("overviews")
    @ApiModelProperty("概况")
    private String overviews;
    /**
     * 所属对象
     */
    @DEField(defaultValue = "0")
    @TableField(value = "objectid")
    @JSONField(name = "objectid")
    @JsonProperty("objectid")
    @ApiModelProperty("所属对象")
    private Integer objectid;
    /**
     * 用例
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "cases")
    @JSONField(name = "cases")
    @JsonProperty("cases")
    @ApiModelProperty("用例")
    private String cases;
    /**
     * 参与人员
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "members")
    @JSONField(name = "members")
    @JsonProperty("members")
    @ApiModelProperty("参与人员")
    private String members;
    /**
     * 测试的Bug
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "bugs")
    @JSONField(name = "bugs")
    @JsonProperty("bugs")
    @ApiModelProperty("测试的Bug")
    private String bugs;
    /**
     * 由谁创建
     */
    @DEField(preType = DEPredefinedFieldType.CREATEMANNAME)
    @TableField(value = "createdby")
    @JSONField(name = "createdby")
    @JsonProperty("createdby")
    @ApiModelProperty("由谁创建")
    private String createdby;
    /**
     * 测试的需求
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "stories")
    @JSONField(name = "stories")
    @JsonProperty("stories")
    @ApiModelProperty("测试的需求")
    private String stories;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * 测试单
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "tasks")
    @JSONField(name = "tasks")
    @JsonProperty("tasks")
    @ApiModelProperty("测试单")
    private String tasks;
    /**
     * 标题
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("标题")
    private String title;
    /**
     * 开始时间
     */
    @TableField(value = "begin")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始时间")
    private Timestamp begin;
    /**
     * 对象类型
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "objecttype")
    @JSONField(name = "objecttype")
    @JsonProperty("objecttype")
    @ApiModelProperty("对象类型")
    private String objecttype;
    /**
     * 结束时间
     */
    @TableField(value = "end")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束时间")
    private Timestamp end;
    /**
     * 版本信息
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "builds")
    @JSONField(name = "builds")
    @JsonProperty("builds")
    @ApiModelProperty("版本信息")
    private String builds;
    /**
     * 创建时间
     */
    @DEField(preType = DEPredefinedFieldType.CREATEDATE)
    @TableField(value = "createddate", fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "createddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createddate")
    @ApiModelProperty("创建时间")
    private Timestamp createddate;
    /**
     * 总结
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "report")
    @JSONField(name = "report")
    @JsonProperty("report")
    @ApiModelProperty("总结")
    private String report;
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
     * 负责人
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "owner")
    @JSONField(name = "owner")
    @JsonProperty("owner")
    @ApiModelProperty("负责人")
    private String owner;
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
     * 所属项目
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @ApiModelProperty("所属项目")
    private String projectname;
    /**
     * 所属产品
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("所属产品")
    private String productname;
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
     * 所属项目
     */
    @DEField(defaultValue = "0")
    @TableField(value = "project")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("所属项目")
    private Long project;

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
    private cn.ibizlab.pms.core.zentao.domain.Project ztproject;



    /**
     * 设置 [所属对象]
     */
    public void setObjectid(Integer objectid) {
        this.objectid = objectid;
        this.modify("objectid", objectid);
    }

    /**
     * 设置 [用例]
     */
    public void setCases(String cases) {
        this.cases = cases;
        this.modify("cases", cases);
    }

    /**
     * 设置 [参与人员]
     */
    public void setMembers(String members) {
        this.members = members;
        this.modify("members", members);
    }

    /**
     * 设置 [测试的Bug]
     */
    public void setBugs(String bugs) {
        this.bugs = bugs;
        this.modify("bugs", bugs);
    }

    /**
     * 设置 [测试的需求]
     */
    public void setStories(String stories) {
        this.stories = stories;
        this.modify("stories", stories);
    }

    /**
     * 设置 [测试单]
     */
    public void setTasks(String tasks) {
        this.tasks = tasks;
        this.modify("tasks", tasks);
    }

    /**
     * 设置 [标题]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [开始时间]
     */
    public void setBegin(Timestamp begin) {
        this.begin = begin;
        this.modify("begin", begin);
    }

    /**
     * 格式化日期 [开始时间]
     */
    public String formatBegin() {
        if (this.begin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(begin);
    }
    /**
     * 设置 [对象类型]
     */
    public void setObjecttype(String objecttype) {
        this.objecttype = objecttype;
        this.modify("objecttype", objecttype);
    }

    /**
     * 设置 [结束时间]
     */
    public void setEnd(Timestamp end) {
        this.end = end;
        this.modify("end", end);
    }

    /**
     * 格式化日期 [结束时间]
     */
    public String formatEnd() {
        if (this.end == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(end);
    }
    /**
     * 设置 [版本信息]
     */
    public void setBuilds(String builds) {
        this.builds = builds;
        this.modify("builds", builds);
    }

    /**
     * 设置 [总结]
     */
    public void setReport(String report) {
        this.report = report;
        this.modify("report", report);
    }

    /**
     * 设置 [负责人]
     */
    public void setOwner(String owner) {
        this.owner = owner;
        this.modify("owner", owner);
    }

    /**
     * 设置 [所属产品]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [所属项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
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


