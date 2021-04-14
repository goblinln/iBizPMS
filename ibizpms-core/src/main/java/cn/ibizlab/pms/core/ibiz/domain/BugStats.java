package cn.ibizlab.pms.core.ibiz.domain;

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
 * 实体[Bug统计]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_bug", resultMap = "BugStatsResultMap")
@ApiModel("Bug统计")
public class BugStats extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 激活Bug
     */
    @TableField(exist = false)
    @JSONField(name = "bugactive")
    @JsonProperty("bugactive")
    @ApiModelProperty("激活Bug")
    private Integer bugactive;
    /**
     * 不予解决
     */
    @TableField(exist = false)
    @JSONField(name = "bugwillnotfix")
    @JsonProperty("bugwillnotfix")
    @ApiModelProperty("不予解决")
    private Integer bugwillnotfix;
    /**
     * 由谁解决
     */
    @TableField(value = "resolvedby")
    @JSONField(name = "resolvedby")
    @JsonProperty("resolvedby")
    @ApiModelProperty("由谁解决")
    private String resolvedby;
    /**
     * Bug解决方案
     */
    @TableField(exist = false)
    @JSONField(name = "bugresolution")
    @JsonProperty("bugresolution")
    @ApiModelProperty("Bug解决方案")
    private String bugresolution;
    /**
     * 部门
     */
    @TableField(exist = false)
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("部门")
    private String dept;
    /**
     * 设计如此
     */
    @TableField(exist = false)
    @JSONField(name = "bugbydesign")
    @JsonProperty("bugbydesign")
    @ApiModelProperty("设计如此")
    private Integer bugbydesign;
    /**
     * bug创建人
     */
    @TableField(exist = false)
    @JSONField(name = "bugopenedby")
    @JsonProperty("bugopenedby")
    @ApiModelProperty("bug创建人")
    private String bugopenedby;
    /**
     * Bug状态
     */
    @TableField(exist = false)
    @JSONField(name = "bugstatus")
    @JsonProperty("bugstatus")
    @ApiModelProperty("Bug状态")
    private String bugstatus;
    /**
     * 已关闭Bug
     */
    @TableField(exist = false)
    @JSONField(name = "bugclosed")
    @JsonProperty("bugclosed")
    @ApiModelProperty("已关闭Bug")
    private Integer bugclosed;
    /**
     * 指派给
     */
    @TableField(value = "assignedto")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @ApiModelProperty("指派给")
    private String assignedto;
    /**
     * 外部原因
     */
    @TableField(exist = false)
    @JSONField(name = "bugexternal")
    @JsonProperty("bugexternal")
    @ApiModelProperty("外部原因")
    private Integer bugexternal;
    /**
     * 开始
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "begin", format = "yyyy-MM-dd")
    @JsonProperty("begin")
    @ApiModelProperty("开始")
    private Timestamp begin;
    /**
     * bug解决日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "bugresolveddate", format = "yyyy-MM-dd")
    @JsonProperty("bugresolveddate")
    @ApiModelProperty("bug解决日期")
    private Timestamp bugresolveddate;
    /**
     * 结束
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "end", format = "yyyy-MM-dd")
    @JsonProperty("end")
    @ApiModelProperty("结束")
    private Timestamp end;
    /**
     * Bug编号
     */
    @TableField(exist = false)
    @JSONField(name = "bugid")
    @JsonProperty("bugid")
    @ApiModelProperty("Bug编号")
    private Integer bugid;
    /**
     * 项目名称
     */
    @TableField(value = "projectname")
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @ApiModelProperty("项目名称")
    private String projectname;
    /**
     * 已解决
     */
    @TableField(exist = false)
    @JSONField(name = "bugfixed")
    @JsonProperty("bugfixed")
    @ApiModelProperty("已解决")
    private Integer bugfixed;
    /**
     * 标识
     */
    @DEField(isKeyField = true)
    @TableId(value = "id", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("标识")
    private Long id;
    /**
     * 延期处理
     */
    @TableField(exist = false)
    @JSONField(name = "bugpostponed")
    @JsonProperty("bugpostponed")
    @ApiModelProperty("延期处理")
    private Integer bugpostponed;
    /**
     * Bug标题
     */
    @TableField(exist = false)
    @JSONField(name = "bugtitle")
    @JsonProperty("bugtitle")
    @ApiModelProperty("Bug标题")
    private String bugtitle;
    /**
     * Bug
     */
    @TableField(exist = false)
    @JSONField(name = "bugcnt")
    @JsonProperty("bugcnt")
    @ApiModelProperty("Bug")
    private Integer bugcnt;
    /**
     * Bug创建日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "bugopeneddate", format = "yyyy-MM-dd")
    @JsonProperty("bugopeneddate")
    @ApiModelProperty("Bug创建日期")
    private Timestamp bugopeneddate;
    /**
     * 未解决
     */
    @TableField(exist = false)
    @JSONField(name = "bugwjj")
    @JsonProperty("bugwjj")
    @ApiModelProperty("未解决")
    private Integer bugwjj;
    /**
     * 名称
     */
    @TableField(value = "title")
    @JSONField(name = "title")
    @JsonProperty("title")
    @ApiModelProperty("名称")
    private String title;
    /**
     * Bug优先级
     */
    @TableField(exist = false)
    @JSONField(name = "bugpri")
    @JsonProperty("bugpri")
    @ApiModelProperty("Bug优先级")
    private Integer bugpri;
    /**
     * 无法重现
     */
    @TableField(exist = false)
    @JSONField(name = "bugnotrepro")
    @JsonProperty("bugnotrepro")
    @ApiModelProperty("无法重现")
    private Integer bugnotrepro;
    /**
     * 项目名称
     */
    @TableField(exist = false)
    @JSONField(name = "projectname1")
    @JsonProperty("projectname1")
    @ApiModelProperty("项目名称")
    private String projectname1;
    /**
     * 已解决Bug
     */
    @TableField(exist = false)
    @JSONField(name = "bugresolved")
    @JsonProperty("bugresolved")
    @ApiModelProperty("已解决Bug")
    private Integer bugresolved;
    /**
     * 重复Bug
     */
    @TableField(exist = false)
    @JSONField(name = "bugduplicate")
    @JsonProperty("bugduplicate")
    @ApiModelProperty("重复Bug")
    private Integer bugduplicate;
    /**
     * 由谁创建
     */
    @TableField(value = "openedby")
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @ApiModelProperty("由谁创建")
    private String openedby;
    /**
     * 有效率
     */
    @TableField(exist = false)
    @JSONField(name = "bugefficient")
    @JsonProperty("bugefficient")
    @ApiModelProperty("有效率")
    private String bugefficient;
    /**
     * 转为需求
     */
    @TableField(exist = false)
    @JSONField(name = "bugtostory")
    @JsonProperty("bugtostory")
    @ApiModelProperty("转为需求")
    private Integer bugtostory;
    /**
     * Bug严重程度
     */
    @TableField(exist = false)
    @JSONField(name = "bugseverity")
    @JsonProperty("bugseverity")
    @ApiModelProperty("Bug严重程度")
    private Integer bugseverity;
    /**
     * 总计
     */
    @TableField(exist = false)
    @JSONField(name = "bugtotal")
    @JsonProperty("bugtotal")
    @ApiModelProperty("总计")
    private Integer bugtotal;
    /**
     * 产品名称
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("产品名称")
    private String productname;
    /**
     * 编号
     */
    @TableField(value = "product")
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("编号")
    private Long product;
    /**
     * 项目编号
     */
    @TableField(value = "project")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("项目编号")
    private Long project;

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
     * 设置 [由谁解决]
     */
    public void setResolvedby(String resolvedby) {
        this.resolvedby = resolvedby;
        this.modify("resolvedby", resolvedby);
    }

    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
        this.modify("assignedto", assignedto);
    }

    /**
     * 设置 [项目名称]
     */
    public void setProjectname(String projectname) {
        this.projectname = projectname;
        this.modify("projectname", projectname);
    }

    /**
     * 设置 [名称]
     */
    public void setTitle(String title) {
        this.title = title;
        this.modify("title", title);
    }

    /**
     * 设置 [由谁创建]
     */
    public void setOpenedby(String openedby) {
        this.openedby = openedby;
        this.modify("openedby", openedby);
    }

    /**
     * 设置 [编号]
     */
    public void setProduct(Long product) {
        this.product = product;
        this.modify("product", product);
    }

    /**
     * 设置 [项目编号]
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


