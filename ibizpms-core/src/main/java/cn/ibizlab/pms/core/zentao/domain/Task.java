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
 * 实体[任务]
 */
@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(value = "handler")
@TableName(value = "zt_task", resultMap = "TaskResultMap")
@ApiModel("任务")
public class Task extends EntityMP implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 由谁取消
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`CANCELEDBY`")
    @JSONField(name = "canceledby")
    @JsonProperty("canceledby")
    @ApiModelProperty("由谁取消")
    private String canceledby;
    /**
     * 周期类型
     */
    @DEField(name = "config_type")
    @TableField(value = "`CONFIG_TYPE`")
    @JSONField(name = "configtype")
    @JsonProperty("configtype")
    @ApiModelProperty("周期类型")
    private String configtype;
    /**
     * 预计剩余
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`LEFT`")
    @JSONField(name = "left")
    @JsonProperty("left")
    @ApiModelProperty("预计剩余")
    private Double left;
    /**
     * 是否收藏
     */
    @TableField(exist = false)
    @JSONField(name = "isfavorites")
    @JsonProperty("isfavorites")
    @ApiModelProperty("是否收藏")
    private String isfavorites;
    /**
     * 过期日期
     */
    @DEField(name = "config_end")
    @TableField(value = "`CONFIG_END`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "configend", format = "yyyy-MM-dd")
    @JsonProperty("configend")
    @ApiModelProperty("过期日期")
    private Timestamp configend;
    /**
     * 是否填写描述
     */
    @TableField(exist = false)
    @JSONField(name = "hasdetail")
    @JsonProperty("hasdetail")
    @ApiModelProperty("是否填写描述")
    private String hasdetail;
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
     * 是否指派
     */
    @TableField(value = "`ASSIGN`")
    @JSONField(name = "assign")
    @JsonProperty("assign")
    @ApiModelProperty("是否指派")
    private String assign;
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
     * 编号
     */
    @DEField(isKeyField = true)
    @TableId(value = "`ID`", type = IdType.AUTO)
    @JSONField(name = "id")
    @JsonProperty("id")
    @ApiModelProperty("编号")
    private Long id;
    /**
     * 由谁完成
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`FINISHEDBY`")
    @JSONField(name = "finishedby")
    @JsonProperty("finishedby")
    @ApiModelProperty("由谁完成")
    private String finishedby;
    /**
     * 我的总消耗
     */
    @TableField(exist = false)
    @JSONField(name = "mytotaltime")
    @JsonProperty("mytotaltime")
    @ApiModelProperty("我的总消耗")
    private Double mytotaltime;
    /**
     * 抄送给
     */
    @TableField(exist = false)
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    @ApiModelProperty("抄送给")
    private String mailtopk;
    /**
     * 完成者列表
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`FINISHEDLIST`")
    @JSONField(name = "finishedlist")
    @JsonProperty("finishedlist")
    @ApiModelProperty("完成者列表")
    private String finishedlist;
    /**
     * 所属模块
     */
    @TableField(exist = false)
    @JSONField(name = "modulename1")
    @JsonProperty("modulename1")
    @ApiModelProperty("所属模块")
    private String modulename1;
    /**
     * 是否子任务
     */
    @TableField(exist = false)
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @ApiModelProperty("是否子任务")
    private String isleaf;
    /**
     * 实际开始
     */
    @TableField(value = "`REALSTARTED`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "realstarted", format = "yyyy-MM-dd")
    @JsonProperty("realstarted")
    @ApiModelProperty("实际开始")
    private Timestamp realstarted;
    /**
     * 任务状态
     */
    @TableField(exist = false)
    @JSONField(name = "status1")
    @JsonProperty("status1")
    @ApiModelProperty("任务状态")
    private String status1;
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
     * 回复数量
     */
    @TableField(exist = false)
    @JSONField(name = "replycount")
    @JsonProperty("replycount")
    @ApiModelProperty("回复数量")
    private Integer replycount;
    /**
     * 开始日期
     */
    @DEField(name = "config_begin")
    @TableField(value = "`CONFIG_BEGIN`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "configbegin", format = "yyyy-MM-dd")
    @JsonProperty("configbegin")
    @ApiModelProperty("开始日期")
    private Timestamp configbegin;
    /**
     * 最后的更新日期
     */
    @TableField(exist = false)
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "updatedate", format = "yyyy-MM-dd")
    @JsonProperty("updatedate")
    @ApiModelProperty("最后的更新日期")
    private Timestamp updatedate;
    /**
     * 消息通知用户
     */
    @TableField(exist = false)
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;
    /**
     * 由谁关闭
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`CLOSEDBY`")
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @ApiModelProperty("由谁关闭")
    private String closedby;
    /**
     * 本次消耗
     */
    @TableField(exist = false)
    @JSONField(name = "currentconsumed")
    @JsonProperty("currentconsumed")
    @ApiModelProperty("本次消耗")
    private Double currentconsumed;
    /**
     * 附件
     */
    @TableField(exist = false)
    @JSONField(name = "files")
    @JsonProperty("files")
    @ApiModelProperty("附件")
    private String files;
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
     * 归属部门
     */
    @DEField(preType = DEPredefinedFieldType.ORGSECTORID)
    @TableField(value = "`DEPT`")
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @ApiModelProperty("归属部门")
    private String dept;
    /**
     * 关闭原因
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`CLOSEDREASON`")
    @JSONField(name = "closedreason")
    @JsonProperty("closedreason")
    @ApiModelProperty("关闭原因")
    private String closedreason;
    /**
     * 任务种别
     */
    @TableField(value = "`TASKSPECIES`")
    @JSONField(name = "taskspecies")
    @JsonProperty("taskspecies")
    @ApiModelProperty("任务种别")
    private String taskspecies;
    /**
     * 最后修改日期
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEDATE)
    @TableField(value = "`LASTEDITEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "lastediteddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    @ApiModelProperty("最后修改日期")
    private Timestamp lastediteddate;
    /**
     * 间隔天数
     */
    @DEField(name = "config_day")
    @TableField(value = "`CONFIG_DAY`")
    @JSONField(name = "configday")
    @JsonProperty("configday")
    @ApiModelProperty("间隔天数")
    private Integer configday;
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
     * 优先级
     */
    @DEField(defaultValue = "3")
    @TableField(value = "`PRI`")
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;
    /**
     * 最后修改
     */
    @DEField(preType = DEPredefinedFieldType.UPDATEMANNAME)
    @TableField(value = "`LASTEDITEDBY`")
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    @ApiModelProperty("最后修改")
    private String lasteditedby;
    /**
     * 关联编号
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`IDVALUE`")
    @JSONField(name = "idvalue")
    @JsonProperty("idvalue")
    @ApiModelProperty("关联编号")
    private Long idvalue;
    /**
     * 任务状态
     */
    @DEField(defaultValue = "wait")
    @TableField(value = "`STATUS`")
    @JSONField(name = "status")
    @JsonProperty("status")
    @ApiModelProperty("任务状态")
    private String status;
    /**
     * 多人任务
     */
    @TableField(exist = false)
    @JSONField(name = "multiple")
    @JsonProperty("multiple")
    @ApiModelProperty("多人任务")
    private String multiple;
    /**
     * 任务名称
     */
    @TableField(value = "`NAME`")
    @JSONField(name = "name")
    @JsonProperty("name")
    @ApiModelProperty("任务名称")
    private String name;
    /**
     * 关闭时间
     */
    @TableField(value = "`CLOSEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "closeddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭时间")
    private Timestamp closeddate;
    /**
     * 投入成本
     */
    @TableField(value = "`INPUTCOST`")
    @JSONField(name = "inputcost")
    @JsonProperty("inputcost")
    @ApiModelProperty("投入成本")
    private Double inputcost;
    /**
     * 总计耗时
     */
    @TableField(exist = false)
    @JSONField(name = "totaltime")
    @JsonProperty("totaltime")
    @ApiModelProperty("总计耗时")
    private Double totaltime;
    /**
     * 任务类型
     */
    @DEField(defaultValue = "devel")
    @TableField(value = "`TYPE`")
    @JSONField(name = "type")
    @JsonProperty("type")
    @ApiModelProperty("任务类型")
    private String type;
    /**
     * 指派给
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`ASSIGNEDTO`")
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @ApiModelProperty("指派给")
    private String assignedto;
    /**
     * 延期
     */
    @TableField(exist = false)
    @JSONField(name = "delay")
    @JsonProperty("delay")
    @ApiModelProperty("延期")
    private String delay;
    /**
     * 任务描述
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`DESC`")
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @ApiModelProperty("任务描述")
    private String desc;
    /**
     * 预计开始
     */
    @TableField(value = "`ESTSTARTED`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "eststarted", format = "yyyy-MM-dd")
    @JsonProperty("eststarted")
    @ApiModelProperty("预计开始")
    private Timestamp eststarted;
    /**
     * 截止日期
     */
    @TableField(value = "`DEADLINE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "deadline", format = "yyyy-MM-dd")
    @JsonProperty("deadline")
    @ApiModelProperty("截止日期")
    private Timestamp deadline;
    /**
     * 排序
     */
    @TableField(exist = false)
    @JSONField(name = "statusorder")
    @JsonProperty("statusorder")
    @ApiModelProperty("排序")
    private Integer statusorder;
    /**
     * 联系人
     */
    @TableField(exist = false)
    @JSONField(name = "mailtoconact")
    @JsonProperty("mailtoconact")
    @ApiModelProperty("联系人")
    private String mailtoconact;
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
     * 周期
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`CYCLE`")
    @JSONField(name = "cycle")
    @JsonProperty("cycle")
    @ApiModelProperty("周期")
    private Integer cycle;
    /**
     * 抄送给
     */
    @DEField(defaultValue = "#EMPTY")
    @TableField(value = "`MAILTO`")
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @ApiModelProperty("抄送给")
    private String mailto;
    /**
     * 总计消耗
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`CONSUMED`")
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("总计消耗")
    private Double consumed;
    /**
     * 最初预计
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`ESTIMATE`")
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    @ApiModelProperty("最初预计")
    private Double estimate;
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
     * 是否完成
     */
    @TableField(exist = false)
    @JSONField(name = "isfinished")
    @JsonProperty("isfinished")
    @ApiModelProperty("是否完成")
    private String isfinished;
    /**
     * 取消时间
     */
    @TableField(value = "`CANCELEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "canceleddate", format = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("canceleddate")
    @ApiModelProperty("取消时间")
    private Timestamp canceleddate;
    /**
     * 周期设置月
     */
    @DEField(name = "config_month")
    @TableField(value = "`CONFIG_MONTH`")
    @JSONField(name = "configmonth")
    @JsonProperty("configmonth")
    @ApiModelProperty("周期设置月")
    private String configmonth;
    /**
     * 备注
     */
    @TableField(exist = false)
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @ApiModelProperty("备注")
    private String comment;
    /**
     * 持续时间
     */
    @TableField(exist = false)
    @JSONField(name = "duration")
    @JsonProperty("duration")
    @ApiModelProperty("持续时间")
    private String duration;
    /**
     * 转交给
     */
    @TableField(exist = false)
    @JSONField(name = "assignedtozj")
    @JsonProperty("assignedtozj")
    @ApiModelProperty("转交给")
    private String assignedtozj;
    /**
     * 团队用户
     */
    @TableField(exist = false)
    @JSONField(name = "usernames")
    @JsonProperty("usernames")
    @ApiModelProperty("团队用户")
    private String usernames;
    /**
     * 之前消耗
     */
    @TableField(exist = false)
    @JSONField(name = "myconsumed")
    @JsonProperty("myconsumed")
    @ApiModelProperty("之前消耗")
    private Double myconsumed;
    /**
     * 周期设置周几
     */
    @DEField(name = "config_week")
    @TableField(value = "`CONFIG_WEEK`")
    @JSONField(name = "configweek")
    @JsonProperty("configweek")
    @ApiModelProperty("周期设置周几")
    private String configweek;
    /**
     * 任务类型
     */
    @TableField(exist = false)
    @JSONField(name = "tasktype")
    @JsonProperty("tasktype")
    @ApiModelProperty("任务类型")
    private String tasktype;
    /**
     * 所有模块
     */
    @TableField(exist = false)
    @JSONField(name = "allmodules")
    @JsonProperty("allmodules")
    @ApiModelProperty("所有模块")
    private String allmodules;
    /**
     * 提前天数
     */
    @DEField(name = "config_beforedays")
    @TableField(value = "`CONFIG_BEFOREDAYS`")
    @JSONField(name = "configbeforedays")
    @JsonProperty("configbeforedays")
    @ApiModelProperty("提前天数")
    private Integer configbeforedays;
    /**
     * 实际完成
     */
    @TableField(value = "`FINISHEDDATE`")
    @JsonFormat(pattern = "yyyy-MM-dd", locale = "zh", timezone = "GMT+8")
    @JSONField(name = "finisheddate", format = "yyyy-MM-dd")
    @JsonProperty("finisheddate")
    @ApiModelProperty("实际完成")
    private Timestamp finisheddate;
    /**
     * 进度
     */
    @TableField(exist = false)
    @JSONField(name = "progressrate")
    @JsonProperty("progressrate")
    @ApiModelProperty("进度")
    private String progressrate;
    /**
     * 所属模块
     */
    @TableField(exist = false)
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @ApiModelProperty("所属模块")
    private String modulename;
    /**
     * 相关需求
     */
    @TableField(exist = false)
    @JSONField(name = "storyname")
    @JsonProperty("storyname")
    @ApiModelProperty("相关需求")
    private String storyname;
    /**
     * 模块路径
     */
    @TableField(exist = false)
    @JSONField(name = "path")
    @JsonProperty("path")
    @ApiModelProperty("模块路径")
    private String path;
    /**
     * 所属计划
     */
    @TableField(exist = false)
    @JSONField(name = "planname")
    @JsonProperty("planname")
    @ApiModelProperty("所属计划")
    private String planname;
    /**
     * 所属项目
     */
    @TableField(exist = false)
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @ApiModelProperty("所属项目")
    private String projectname;
    /**
     * 产品
     */
    @TableField(exist = false)
    @JSONField(name = "product")
    @JsonProperty("product")
    @ApiModelProperty("产品")
    private Long product;
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
     * 产品
     */
    @TableField(exist = false)
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @ApiModelProperty("产品")
    private String productname;
    /**
     * 父任务
     */
    @TableField(exist = false)
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @ApiModelProperty("父任务")
    private String parentname;
    /**
     * 所属项目
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PROJECT`")
    @JSONField(name = "project")
    @JsonProperty("project")
    @ApiModelProperty("所属项目")
    private Long project;
    /**
     * 编号
     */
    @TableField(value = "`PLAN`")
    @JSONField(name = "plan")
    @JsonProperty("plan")
    @ApiModelProperty("编号")
    private Long plan;
    /**
     * 模块
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`MODULE`")
    @JSONField(name = "module")
    @JsonProperty("module")
    @ApiModelProperty("模块")
    private Long module;
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
     * 父任务
     */
    @DEField(defaultValue = "0")
    @TableField(value = "`PARENT`")
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @ApiModelProperty("父任务")
    private Long parent;
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
     * 相关需求最新版本
     */
    @TableField(exist = false)
    @JSONField(name = "storyversionnew")
    @JsonProperty("storyversionnew")
    @ApiModelProperty("相关需求最新版本")
    private Integer storyversionnew;
    /**
     * 需求状态
     */
    @TableField(exist = false)
    @JSONField(name = "storystatus")
    @JsonProperty("storystatus")
    @ApiModelProperty("需求状态")
    private String storystatus;
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
    private cn.ibizlab.pms.core.ibiz.domain.ProjectModule projectmodule;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Bug ztfrombug;

    /**
     * 相关计划
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.ProductPlan productplan;

    /**
     * 
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
    private cn.ibizlab.pms.core.zentao.domain.Story ztstory;

    /**
     * 
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private cn.ibizlab.pms.core.zentao.domain.Task ztparent;


    /**
     * 任务团队
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.ibiz.domain.TaskTeam> taskteam;

    /**
     * 任务预计
     */
    @JsonIgnore
    @JSONField(serialize = false)
    @TableField(exist = false)
    private List<cn.ibizlab.pms.core.zentao.domain.TaskEstimate> taskestimates;


    /**
     * 设置 [由谁取消]
     */
    public void setCanceledby(String canceledby) {
        this.canceledby = canceledby;
        this.modify("canceledby", canceledby);
    }

    /**
     * 设置 [周期类型]
     */
    public void setConfigtype(String configtype) {
        this.configtype = configtype;
        this.modify("config_type", configtype);
    }

    /**
     * 设置 [预计剩余]
     */
    public void setLeft(Double left) {
        this.left = left;
        this.modify("left", left);
    }

    /**
     * 设置 [过期日期]
     */
    public void setConfigend(Timestamp configend) {
        this.configend = configend;
        this.modify("config_end", configend);
    }

    /**
     * 格式化日期 [过期日期]
     */
    public String formatConfigend() {
        if (this.configend == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(configend);
    }
    /**
     * 设置 [是否指派]
     */
    public void setAssign(String assign) {
        this.assign = assign;
        this.modify("assign", assign);
    }

    /**
     * 设置 [标题颜色]
     */
    public void setColor(String color) {
        this.color = color;
        this.modify("color", color);
    }

    /**
     * 设置 [由谁完成]
     */
    public void setFinishedby(String finishedby) {
        this.finishedby = finishedby;
        this.modify("finishedby", finishedby);
    }

    /**
     * 设置 [完成者列表]
     */
    public void setFinishedlist(String finishedlist) {
        this.finishedlist = finishedlist;
        this.modify("finishedlist", finishedlist);
    }

    /**
     * 设置 [实际开始]
     */
    public void setRealstarted(Timestamp realstarted) {
        this.realstarted = realstarted;
        this.modify("realstarted", realstarted);
    }

    /**
     * 格式化日期 [实际开始]
     */
    public String formatRealstarted() {
        if (this.realstarted == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(realstarted);
    }
    /**
     * 设置 [开始日期]
     */
    public void setConfigbegin(Timestamp configbegin) {
        this.configbegin = configbegin;
        this.modify("config_begin", configbegin);
    }

    /**
     * 格式化日期 [开始日期]
     */
    public String formatConfigbegin() {
        if (this.configbegin == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(configbegin);
    }
    /**
     * 设置 [由谁关闭]
     */
    public void setClosedby(String closedby) {
        this.closedby = closedby;
        this.modify("closedby", closedby);
    }

    /**
     * 设置 [子状态]
     */
    public void setSubstatus(String substatus) {
        this.substatus = substatus;
        this.modify("substatus", substatus);
    }

    /**
     * 设置 [关闭原因]
     */
    public void setClosedreason(String closedreason) {
        this.closedreason = closedreason;
        this.modify("closedreason", closedreason);
    }

    /**
     * 设置 [任务种别]
     */
    public void setTaskspecies(String taskspecies) {
        this.taskspecies = taskspecies;
        this.modify("taskspecies", taskspecies);
    }

    /**
     * 设置 [间隔天数]
     */
    public void setConfigday(Integer configday) {
        this.configday = configday;
        this.modify("config_day", configday);
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
     * 设置 [优先级]
     */
    public void setPri(Integer pri) {
        this.pri = pri;
        this.modify("pri", pri);
    }

    /**
     * 设置 [关联编号]
     */
    public void setIdvalue(Long idvalue) {
        this.idvalue = idvalue;
        this.modify("idvalue", idvalue);
    }

    /**
     * 设置 [任务状态]
     */
    public void setStatus(String status) {
        this.status = status;
        this.modify("status", status);
    }

    /**
     * 设置 [任务名称]
     */
    public void setName(String name) {
        this.name = name;
        this.modify("name", name);
    }

    /**
     * 设置 [关闭时间]
     */
    public void setCloseddate(Timestamp closeddate) {
        this.closeddate = closeddate;
        this.modify("closeddate", closeddate);
    }

    /**
     * 格式化日期 [关闭时间]
     */
    public String formatCloseddate() {
        if (this.closeddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(closeddate);
    }
    /**
     * 设置 [投入成本]
     */
    public void setInputcost(Double inputcost) {
        this.inputcost = inputcost;
        this.modify("inputcost", inputcost);
    }

    /**
     * 设置 [任务类型]
     */
    public void setType(String type) {
        this.type = type;
        this.modify("type", type);
    }

    /**
     * 设置 [指派给]
     */
    public void setAssignedto(String assignedto) {
        this.assignedto = assignedto;
        this.modify("assignedto", assignedto);
    }

    /**
     * 设置 [任务描述]
     */
    public void setDesc(String desc) {
        this.desc = desc;
        this.modify("desc", desc);
    }

    /**
     * 设置 [预计开始]
     */
    public void setEststarted(Timestamp eststarted) {
        this.eststarted = eststarted;
        this.modify("eststarted", eststarted);
    }

    /**
     * 格式化日期 [预计开始]
     */
    public String formatEststarted() {
        if (this.eststarted == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(eststarted);
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
     * 设置 [周期]
     */
    public void setCycle(Integer cycle) {
        this.cycle = cycle;
        this.modify("cycle", cycle);
    }

    /**
     * 设置 [抄送给]
     */
    public void setMailto(String mailto) {
        this.mailto = mailto;
        this.modify("mailto", mailto);
    }

    /**
     * 设置 [总计消耗]
     */
    public void setConsumed(Double consumed) {
        this.consumed = consumed;
        this.modify("consumed", consumed);
    }

    /**
     * 设置 [最初预计]
     */
    public void setEstimate(Double estimate) {
        this.estimate = estimate;
        this.modify("estimate", estimate);
    }

    /**
     * 设置 [取消时间]
     */
    public void setCanceleddate(Timestamp canceleddate) {
        this.canceleddate = canceleddate;
        this.modify("canceleddate", canceleddate);
    }

    /**
     * 格式化日期 [取消时间]
     */
    public String formatCanceleddate() {
        if (this.canceleddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(canceleddate);
    }
    /**
     * 设置 [周期设置月]
     */
    public void setConfigmonth(String configmonth) {
        this.configmonth = configmonth;
        this.modify("config_month", configmonth);
    }

    /**
     * 设置 [周期设置周几]
     */
    public void setConfigweek(String configweek) {
        this.configweek = configweek;
        this.modify("config_week", configweek);
    }

    /**
     * 设置 [提前天数]
     */
    public void setConfigbeforedays(Integer configbeforedays) {
        this.configbeforedays = configbeforedays;
        this.modify("config_beforedays", configbeforedays);
    }

    /**
     * 设置 [实际完成]
     */
    public void setFinisheddate(Timestamp finisheddate) {
        this.finisheddate = finisheddate;
        this.modify("finisheddate", finisheddate);
    }

    /**
     * 格式化日期 [实际完成]
     */
    public String formatFinisheddate() {
        if (this.finisheddate == null) {
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(finisheddate);
    }
    /**
     * 设置 [需求版本]
     */
    public void setStoryversion(Integer storyversion) {
        this.storyversion = storyversion;
        this.modify("storyversion", storyversion);
    }

    /**
     * 设置 [所属项目]
     */
    public void setProject(Long project) {
        this.project = project;
        this.modify("project", project);
    }

    /**
     * 设置 [编号]
     */
    public void setPlan(Long plan) {
        this.plan = plan;
        this.modify("plan", plan);
    }

    /**
     * 设置 [模块]
     */
    public void setModule(Long module) {
        this.module = module;
        this.modify("module", module);
    }

    /**
     * 设置 [相关需求]
     */
    public void setStory(Long story) {
        this.story = story;
        this.modify("story", story);
    }

    /**
     * 设置 [父任务]
     */
    public void setParent(Long parent) {
        this.parent = parent;
        this.modify("parent", parent);
    }

    /**
     * 设置 [来源Bug]
     */
    public void setFrombug(Long frombug) {
        this.frombug = frombug;
        this.modify("frombug", frombug);
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


