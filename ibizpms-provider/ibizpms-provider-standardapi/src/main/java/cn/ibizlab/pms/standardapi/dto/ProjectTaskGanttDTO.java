package cn.ibizlab.pms.standardapi.dto;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.HashMap;
import java.io.Serializable;
import java.math.BigDecimal;
import com.fasterxml.jackson.annotation.JsonFilter;
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 服务DTO对象[ProjectTaskGanttDTO]
 */
@Data
@ApiModel("项目甘特图（待删除）")
@JsonFilter(value = "dtofieldfilter")
public class ProjectTaskGanttDTO extends DTOBase implements Serializable {

	private static final long serialVersionUID = 1L;

    /**
     * 属性 [CANCELEDBY]
     *
     */
    @JSONField(name = "canceledby")
    @JsonProperty("canceledby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁取消")
    private String canceledby;

    /**
     * 属性 [CONFIG_TYPE]
     *
     */
    @JSONField(name = "configtype")
    @JsonProperty("configtype")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("周期类型")
    private String configtype;

    /**
     * 属性 [STORYSTATUS]
     *
     */
    @JSONField(name = "storystatus")
    @JsonProperty("storystatus")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("需求状态")
    private String storystatus;

    /**
     * 属性 [LEFT]
     *
     */
    @JSONField(name = "left")
    @JsonProperty("left")
    @ApiModelProperty("预计剩余")
    private Double left;

    /**
     * 属性 [ISFAVORITES]
     *
     */
    @JSONField(name = "isfavorites")
    @JsonProperty("isfavorites")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("是否收藏")
    private String isfavorites;

    /**
     * 属性 [CONFIG_END]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "configend" , format="yyyy-MM-dd")
    @JsonProperty("configend")
    @ApiModelProperty("过期日期")
    private Timestamp configend;

    /**
     * 属性 [HASDETAIL]
     *
     */
    @JSONField(name = "hasdetail")
    @JsonProperty("hasdetail")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("是否填写描述")
    private String hasdetail;

    /**
     * 属性 [OPENEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "openeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("openeddate")
    @ApiModelProperty("创建日期")
    private Timestamp openeddate;

    /**
     * 属性 [ASSIGN]
     *
     */
    @JSONField(name = "assign")
    @JsonProperty("assign")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("是否指派")
    private String assign;

    /**
     * 属性 [COLOR]
     *
     */
    @JSONField(name = "color")
    @JsonProperty("color")
    @Size(min = 0, max = 7, message = "内容长度必须小于等于[7]")
    @ApiModelProperty("标题颜色")
    private String color;

    /**
     * 属性 [ID]
     *
     */
    @JSONField(name = "id")
    @JsonProperty("id")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long id;

    /**
     * 属性 [FINISHEDBY]
     *
     */
    @JSONField(name = "finishedby")
    @JsonProperty("finishedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁完成")
    private String finishedby;

    /**
     * 属性 [MYTOTALTIME]
     *
     */
    @JSONField(name = "mytotaltime")
    @JsonProperty("mytotaltime")
    @ApiModelProperty("我的总消耗")
    private Double mytotaltime;

    /**
     * 属性 [MAILTOPK]
     *
     */
    @JSONField(name = "mailtopk")
    @JsonProperty("mailtopk")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("抄送给")
    private String mailtopk;

    /**
     * 属性 [FINISHEDLIST]
     *
     */
    @JSONField(name = "finishedlist")
    @JsonProperty("finishedlist")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("完成者列表")
    private String finishedlist;

    /**
     * 属性 [MODULENAME1]
     *
     */
    @JSONField(name = "modulename1")
    @JsonProperty("modulename1")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("所属模块")
    private String modulename1;

    /**
     * 属性 [ISLEAF]
     *
     */
    @JSONField(name = "isleaf")
    @JsonProperty("isleaf")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("是否子任务")
    private String isleaf;

    /**
     * 属性 [REALSTARTED]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "realstarted" , format="yyyy-MM-dd")
    @JsonProperty("realstarted")
    @ApiModelProperty("实际开始")
    private Timestamp realstarted;

    /**
     * 属性 [STATUS1]
     *
     */
    @JSONField(name = "status1")
    @JsonProperty("status1")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("任务状态")
    private String status1;

    /**
     * 属性 [ORG]
     *
     */
    @JSONField(name = "org")
    @JsonProperty("org")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织")
    private String org;

    /**
     * 属性 [REPLYCOUNT]
     *
     */
    @JSONField(name = "replycount")
    @JsonProperty("replycount")
    @ApiModelProperty("回复数量")
    private Integer replycount;

    /**
     * 属性 [DEPTNAME]
     *
     */
    @JSONField(name = "deptname")
    @JsonProperty("deptname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门名")
    private String deptname;

    /**
     * 属性 [CONFIG_BEGIN]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "configbegin" , format="yyyy-MM-dd")
    @JsonProperty("configbegin")
    @ApiModelProperty("开始日期")
    private Timestamp configbegin;

    /**
     * 属性 [UPDATEDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "updatedate" , format="yyyy-MM-dd")
    @JsonProperty("updatedate")
    @ApiModelProperty("最后的更新日期")
    private Timestamp updatedate;

    /**
     * 属性 [NOTICEUSERS]
     *
     */
    @JSONField(name = "noticeusers")
    @JsonProperty("noticeusers")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("消息通知用户")
    private String noticeusers;

    /**
     * 属性 [CLOSEDBY]
     *
     */
    @JSONField(name = "closedby")
    @JsonProperty("closedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁关闭")
    private String closedby;

    /**
     * 属性 [STORYVERSIONNEW]
     *
     */
    @JSONField(name = "storyversionnew")
    @JsonProperty("storyversionnew")
    @ApiModelProperty("相关需求最新版本")
    private Integer storyversionnew;

    /**
     * 属性 [CURRENTCONSUMED]
     *
     */
    @JSONField(name = "currentconsumed")
    @JsonProperty("currentconsumed")
    @ApiModelProperty("本次消耗")
    private Double currentconsumed;

    /**
     * 属性 [FILES]
     *
     */
    @JSONField(name = "files")
    @JsonProperty("files")
    @Size(min = 0, max = 1000, message = "内容长度必须小于等于[1000]")
    @ApiModelProperty("附件")
    private String files;

    /**
     * 属性 [SUBSTATUS]
     *
     */
    @JSONField(name = "substatus")
    @JsonProperty("substatus")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("子状态")
    private String substatus;

    /**
     * 属性 [DEPT]
     *
     */
    @JSONField(name = "dept")
    @JsonProperty("dept")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属部门")
    private String dept;

    /**
     * 属性 [CLOSEDREASON]
     *
     */
    @JSONField(name = "closedreason")
    @JsonProperty("closedreason")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("关闭原因")
    private String closedreason;

    /**
     * 属性 [TASKSPECIES]
     *
     */
    @JSONField(name = "taskspecies")
    @JsonProperty("taskspecies")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("任务种别")
    private String taskspecies;

    /**
     * 属性 [LASTEDITEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "lastediteddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("lastediteddate")
    @ApiModelProperty("最后修改日期")
    private Timestamp lastediteddate;

    /**
     * 属性 [CONFIG_DAY]
     *
     */
    @JSONField(name = "configday")
    @JsonProperty("configday")
    @ApiModelProperty("间隔天数")
    private Integer configday;

    /**
     * 属性 [ASSIGNEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "assigneddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("assigneddate")
    @ApiModelProperty("指派日期")
    private Timestamp assigneddate;

    /**
     * 属性 [PRI]
     *
     */
    @JSONField(name = "pri")
    @JsonProperty("pri")
    @ApiModelProperty("优先级")
    private Integer pri;

    /**
     * 属性 [LASTEDITEDBY]
     *
     */
    @JSONField(name = "lasteditedby")
    @JsonProperty("lasteditedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("最后修改")
    private String lasteditedby;

    /**
     * 属性 [IDVALUE]
     *
     */
    @JSONField(name = "idvalue")
    @JsonProperty("idvalue")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("关联编号")
    private Long idvalue;

    /**
     * 属性 [STATUS]
     *
     */
    @JSONField(name = "status")
    @JsonProperty("status")
    @Size(min = 0, max = 6, message = "内容长度必须小于等于[6]")
    @ApiModelProperty("任务状态")
    private String status;

    /**
     * 属性 [MULTIPLE]
     *
     */
    @JSONField(name = "multiple")
    @JsonProperty("multiple")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("多人任务")
    private String multiple;

    /**
     * 属性 [NAME]
     *
     */
    @JSONField(name = "name")
    @JsonProperty("name")
    @NotBlank(message = "[任务名称]不允许为空!")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("任务名称")
    private String name;

    /**
     * 属性 [CLOSEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "closeddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("closeddate")
    @ApiModelProperty("关闭时间")
    private Timestamp closeddate;

    /**
     * 属性 [INPUTCOST]
     *
     */
    @JSONField(name = "inputcost")
    @JsonProperty("inputcost")
    @ApiModelProperty("投入成本")
    private Double inputcost;

    /**
     * 属性 [TOTALTIME]
     *
     */
    @JSONField(name = "totaltime")
    @JsonProperty("totaltime")
    @ApiModelProperty("总计耗时")
    private Double totaltime;

    /**
     * 属性 [TYPE]
     *
     */
    @JSONField(name = "type")
    @JsonProperty("type")
    @Size(min = 0, max = 20, message = "内容长度必须小于等于[20]")
    @ApiModelProperty("任务类型")
    private String type;

    /**
     * 属性 [ASSIGNEDTO]
     *
     */
    @JSONField(name = "assignedto")
    @JsonProperty("assignedto")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("指派给")
    private String assignedto;

    /**
     * 属性 [ORGNAME]
     *
     */
    @JSONField(name = "orgname")
    @JsonProperty("orgname")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("归属组织名")
    private String orgname;

    /**
     * 属性 [STORYVERSION]
     *
     */
    @JSONField(name = "storyversion")
    @JsonProperty("storyversion")
    @ApiModelProperty("需求版本")
    private Integer storyversion;

    /**
     * 属性 [DELAY]
     *
     */
    @JSONField(name = "delay")
    @JsonProperty("delay")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("延期")
    private String delay;

    /**
     * 属性 [DESC]
     *
     */
    @JSONField(name = "desc")
    @JsonProperty("desc")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("任务描述")
    private String desc;

    /**
     * 属性 [ESTSTARTED]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "eststarted" , format="yyyy-MM-dd")
    @JsonProperty("eststarted")
    @ApiModelProperty("预计开始")
    private Timestamp eststarted;

    /**
     * 属性 [DEADLINE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "deadline" , format="yyyy-MM-dd")
    @JsonProperty("deadline")
    @ApiModelProperty("截止日期")
    private Timestamp deadline;

    /**
     * 属性 [STATUSORDER]
     *
     */
    @JSONField(name = "statusorder")
    @JsonProperty("statusorder")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("排序")
    private Long statusorder;

    /**
     * 属性 [MAILTOCONACT]
     *
     */
    @JSONField(name = "mailtoconact")
    @JsonProperty("mailtoconact")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("联系人")
    private String mailtoconact;

    /**
     * 属性 [DELETED]
     *
     */
    @JSONField(name = "deleted")
    @JsonProperty("deleted")
    @Size(min = 0, max = 1, message = "内容长度必须小于等于[1]")
    @ApiModelProperty("已删除")
    private String deleted;

    /**
     * 属性 [CYCLE]
     *
     */
    @JSONField(name = "cycle")
    @JsonProperty("cycle")
    @ApiModelProperty("周期")
    private Integer cycle;

    /**
     * 属性 [MAILTO]
     *
     */
    @JSONField(name = "mailto")
    @JsonProperty("mailto")
    @Size(min = 0, max = 65535, message = "内容长度必须小于等于[65535]")
    @ApiModelProperty("抄送给")
    private String mailto;

    /**
     * 属性 [CONSUMED]
     *
     */
    @JSONField(name = "consumed")
    @JsonProperty("consumed")
    @ApiModelProperty("总计消耗")
    private Double consumed;

    /**
     * 属性 [ESTIMATE]
     *
     */
    @JSONField(name = "estimate")
    @JsonProperty("estimate")
    @ApiModelProperty("最初预计")
    private Double estimate;

    /**
     * 属性 [OPENEDBY]
     *
     */
    @JSONField(name = "openedby")
    @JsonProperty("openedby")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("由谁创建")
    private String openedby;

    /**
     * 属性 [ISFINISHED]
     *
     */
    @JSONField(name = "isfinished")
    @JsonProperty("isfinished")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("是否完成")
    private String isfinished;

    /**
     * 属性 [CANCELEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "canceleddate" , format="yyyy-MM-dd HH:mm:ss")
    @JsonProperty("canceleddate")
    @ApiModelProperty("取消时间")
    private Timestamp canceleddate;

    /**
     * 属性 [CONFIG_MONTH]
     *
     */
    @JSONField(name = "configmonth")
    @JsonProperty("configmonth")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("周期设置月")
    private String configmonth;

    /**
     * 属性 [COMMENT]
     *
     */
    @JSONField(name = "comment")
    @JsonProperty("comment")
    @Size(min = 0, max = 1048576, message = "内容长度必须小于等于[1048576]")
    @ApiModelProperty("备注")
    private String comment;

    /**
     * 属性 [DURATION]
     *
     */
    @JSONField(name = "duration")
    @JsonProperty("duration")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("持续时间")
    private String duration;

    /**
     * 属性 [ASSIGNEDTOZJ]
     *
     */
    @JSONField(name = "assignedtozj")
    @JsonProperty("assignedtozj")
    @Size(min = 0, max = 30, message = "内容长度必须小于等于[30]")
    @ApiModelProperty("转交给")
    private String assignedtozj;

    /**
     * 属性 [USERNAMES]
     *
     */
    @JSONField(name = "usernames")
    @JsonProperty("usernames")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("团队用户")
    private String usernames;

    /**
     * 属性 [MYCONSUMED]
     *
     */
    @JSONField(name = "myconsumed")
    @JsonProperty("myconsumed")
    @ApiModelProperty("之前消耗")
    private Double myconsumed;

    /**
     * 属性 [CONFIG_WEEK]
     *
     */
    @JSONField(name = "configweek")
    @JsonProperty("configweek")
    @Size(min = 0, max = 2000, message = "内容长度必须小于等于[2000]")
    @ApiModelProperty("周期设置周几")
    private String configweek;

    /**
     * 属性 [TASKTYPE]
     *
     */
    @JSONField(name = "tasktype")
    @JsonProperty("tasktype")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("任务类型")
    private String tasktype;

    /**
     * 属性 [ALLMODULES]
     *
     */
    @JSONField(name = "allmodules")
    @JsonProperty("allmodules")
    @Size(min = 0, max = 100, message = "内容长度必须小于等于[100]")
    @ApiModelProperty("所有模块")
    private String allmodules;

    /**
     * 属性 [CONFIG_BEFOREDAYS]
     *
     */
    @JSONField(name = "configbeforedays")
    @JsonProperty("configbeforedays")
    @ApiModelProperty("提前天数")
    private Integer configbeforedays;

    /**
     * 属性 [FINISHEDDATE]
     *
     */
    @JsonFormat(pattern="yyyy-MM-dd", locale = "zh" , timezone="GMT+8")
    @JSONField(name = "finisheddate" , format="yyyy-MM-dd")
    @JsonProperty("finisheddate")
    @ApiModelProperty("实际完成")
    private Timestamp finisheddate;

    /**
     * 属性 [PROGRESSRATE]
     *
     */
    @JSONField(name = "progressrate")
    @JsonProperty("progressrate")
    @Size(min = 0, max = 200, message = "内容长度必须小于等于[200]")
    @ApiModelProperty("进度")
    private String progressrate;

    /**
     * 属性 [MODULENAME]
     *
     */
    @JSONField(name = "modulename")
    @JsonProperty("modulename")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("所属模块")
    private String modulename;

    /**
     * 属性 [STORYNAME]
     *
     */
    @JSONField(name = "storyname")
    @JsonProperty("storyname")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("相关需求")
    private String storyname;

    /**
     * 属性 [PATH]
     *
     */
    @JSONField(name = "path")
    @JsonProperty("path")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("模块路径")
    private String path;

    /**
     * 属性 [PLANNAME]
     *
     */
    @JSONField(name = "planname")
    @JsonProperty("planname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("所属计划")
    private String planname;

    /**
     * 属性 [PROJECTNAME]
     *
     */
    @JSONField(name = "projectname")
    @JsonProperty("projectname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("所属项目")
    private String projectname;

    /**
     * 属性 [PRODUCT]
     *
     */
    @JSONField(name = "product")
    @JsonProperty("product")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("产品")
    private Long product;

    /**
     * 属性 [PRODUCTNAME]
     *
     */
    @JSONField(name = "productname")
    @JsonProperty("productname")
    @Size(min = 0, max = 90, message = "内容长度必须小于等于[90]")
    @ApiModelProperty("产品")
    private String productname;

    /**
     * 属性 [PARENTNAME]
     *
     */
    @JSONField(name = "parentname")
    @JsonProperty("parentname")
    @Size(min = 0, max = 255, message = "内容长度必须小于等于[255]")
    @ApiModelProperty("父任务")
    private String parentname;

    /**
     * 属性 [PROJECT]
     *
     */
    @JSONField(name = "project")
    @JsonProperty("project")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("所属项目")
    private Long project;

    /**
     * 属性 [PLAN]
     *
     */
    @JSONField(name = "plan")
    @JsonProperty("plan")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("编号")
    private Long plan;

    /**
     * 属性 [MODULE]
     *
     */
    @JSONField(name = "module")
    @JsonProperty("module")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("模块")
    private Long module;

    /**
     * 属性 [STORY]
     *
     */
    @JSONField(name = "story")
    @JsonProperty("story")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("相关需求")
    private Long story;

    /**
     * 属性 [PARENT]
     *
     */
    @JSONField(name = "parent")
    @JsonProperty("parent")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("父任务")
    private Long parent;

    /**
     * 属性 [FROMBUG]
     *
     */
    @JSONField(name = "frombug")
    @JsonProperty("frombug")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("来源Bug")
    private Long frombug;

    /**
     * 属性 [TASKSN]
     *
     */
    @JSONField(name = "tasksn")
    @JsonProperty("tasksn")
    @JsonSerialize(using = ToStringSerializer.class)
    @ApiModelProperty("任务编号")
    private Long tasksn;

    /**
     * 属性 [CREATEMAN]
     *
     */
    @JSONField(name = "createman")
    @JsonProperty("createman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("建立人")
    private String createman;

    /**
     * 属性 [UPDATEMAN]
     *
     */
    @JSONField(name = "updateman")
    @JsonProperty("updateman")
    @Size(min = 0, max = 60, message = "内容长度必须小于等于[60]")
    @ApiModelProperty("更新人")
    private String updateman;


    /**
     * 设置 [CANCELEDBY]
     */
    public void setCanceledby(String  canceledby){
        this.canceledby = canceledby ;
        this.modify("canceledby",canceledby);
    }

    /**
     * 设置 [CONFIG_TYPE]
     */
    public void setConfigtype(String  configtype){
        this.configtype = configtype ;
        this.modify("config_type",configtype);
    }

    /**
     * 设置 [LEFT]
     */
    public void setLeft(Double  left){
        this.left = left ;
        this.modify("left",left);
    }

    /**
     * 设置 [CONFIG_END]
     */
    public void setConfigend(Timestamp  configend){
        this.configend = configend ;
        this.modify("config_end",configend);
    }

    /**
     * 设置 [ASSIGN]
     */
    public void setAssign(String  assign){
        this.assign = assign ;
        this.modify("assign",assign);
    }

    /**
     * 设置 [COLOR]
     */
    public void setColor(String  color){
        this.color = color ;
        this.modify("color",color);
    }

    /**
     * 设置 [FINISHEDBY]
     */
    public void setFinishedby(String  finishedby){
        this.finishedby = finishedby ;
        this.modify("finishedby",finishedby);
    }

    /**
     * 设置 [FINISHEDLIST]
     */
    public void setFinishedlist(String  finishedlist){
        this.finishedlist = finishedlist ;
        this.modify("finishedlist",finishedlist);
    }

    /**
     * 设置 [REALSTARTED]
     */
    public void setRealstarted(Timestamp  realstarted){
        this.realstarted = realstarted ;
        this.modify("realstarted",realstarted);
    }

    /**
     * 设置 [CONFIG_BEGIN]
     */
    public void setConfigbegin(Timestamp  configbegin){
        this.configbegin = configbegin ;
        this.modify("config_begin",configbegin);
    }

    /**
     * 设置 [CLOSEDBY]
     */
    public void setClosedby(String  closedby){
        this.closedby = closedby ;
        this.modify("closedby",closedby);
    }

    /**
     * 设置 [SUBSTATUS]
     */
    public void setSubstatus(String  substatus){
        this.substatus = substatus ;
        this.modify("substatus",substatus);
    }

    /**
     * 设置 [CLOSEDREASON]
     */
    public void setClosedreason(String  closedreason){
        this.closedreason = closedreason ;
        this.modify("closedreason",closedreason);
    }

    /**
     * 设置 [TASKSPECIES]
     */
    public void setTaskspecies(String  taskspecies){
        this.taskspecies = taskspecies ;
        this.modify("taskspecies",taskspecies);
    }

    /**
     * 设置 [CONFIG_DAY]
     */
    public void setConfigday(Integer  configday){
        this.configday = configday ;
        this.modify("config_day",configday);
    }

    /**
     * 设置 [ASSIGNEDDATE]
     */
    public void setAssigneddate(Timestamp  assigneddate){
        this.assigneddate = assigneddate ;
        this.modify("assigneddate",assigneddate);
    }

    /**
     * 设置 [PRI]
     */
    public void setPri(Integer  pri){
        this.pri = pri ;
        this.modify("pri",pri);
    }

    /**
     * 设置 [IDVALUE]
     */
    public void setIdvalue(Long  idvalue){
        this.idvalue = idvalue ;
        this.modify("idvalue",idvalue);
    }

    /**
     * 设置 [STATUS]
     */
    public void setStatus(String  status){
        this.status = status ;
        this.modify("status",status);
    }

    /**
     * 设置 [NAME]
     */
    public void setName(String  name){
        this.name = name ;
        this.modify("name",name);
    }

    /**
     * 设置 [CLOSEDDATE]
     */
    public void setCloseddate(Timestamp  closeddate){
        this.closeddate = closeddate ;
        this.modify("closeddate",closeddate);
    }

    /**
     * 设置 [INPUTCOST]
     */
    public void setInputcost(Double  inputcost){
        this.inputcost = inputcost ;
        this.modify("inputcost",inputcost);
    }

    /**
     * 设置 [TYPE]
     */
    public void setType(String  type){
        this.type = type ;
        this.modify("type",type);
    }

    /**
     * 设置 [ASSIGNEDTO]
     */
    public void setAssignedto(String  assignedto){
        this.assignedto = assignedto ;
        this.modify("assignedto",assignedto);
    }

    /**
     * 设置 [STORYVERSION]
     */
    public void setStoryversion(Integer  storyversion){
        this.storyversion = storyversion ;
        this.modify("storyversion",storyversion);
    }

    /**
     * 设置 [DESC]
     */
    public void setDesc(String  desc){
        this.desc = desc ;
        this.modify("desc",desc);
    }

    /**
     * 设置 [ESTSTARTED]
     */
    public void setEststarted(Timestamp  eststarted){
        this.eststarted = eststarted ;
        this.modify("eststarted",eststarted);
    }

    /**
     * 设置 [DEADLINE]
     */
    public void setDeadline(Timestamp  deadline){
        this.deadline = deadline ;
        this.modify("deadline",deadline);
    }

    /**
     * 设置 [CYCLE]
     */
    public void setCycle(Integer  cycle){
        this.cycle = cycle ;
        this.modify("cycle",cycle);
    }

    /**
     * 设置 [MAILTO]
     */
    public void setMailto(String  mailto){
        this.mailto = mailto ;
        this.modify("mailto",mailto);
    }

    /**
     * 设置 [CONSUMED]
     */
    public void setConsumed(Double  consumed){
        this.consumed = consumed ;
        this.modify("consumed",consumed);
    }

    /**
     * 设置 [ESTIMATE]
     */
    public void setEstimate(Double  estimate){
        this.estimate = estimate ;
        this.modify("estimate",estimate);
    }

    /**
     * 设置 [CANCELEDDATE]
     */
    public void setCanceleddate(Timestamp  canceleddate){
        this.canceleddate = canceleddate ;
        this.modify("canceleddate",canceleddate);
    }

    /**
     * 设置 [CONFIG_MONTH]
     */
    public void setConfigmonth(String  configmonth){
        this.configmonth = configmonth ;
        this.modify("config_month",configmonth);
    }

    /**
     * 设置 [CONFIG_WEEK]
     */
    public void setConfigweek(String  configweek){
        this.configweek = configweek ;
        this.modify("config_week",configweek);
    }

    /**
     * 设置 [CONFIG_BEFOREDAYS]
     */
    public void setConfigbeforedays(Integer  configbeforedays){
        this.configbeforedays = configbeforedays ;
        this.modify("config_beforedays",configbeforedays);
    }

    /**
     * 设置 [FINISHEDDATE]
     */
    public void setFinisheddate(Timestamp  finisheddate){
        this.finisheddate = finisheddate ;
        this.modify("finisheddate",finisheddate);
    }

    /**
     * 设置 [PROJECT]
     */
    public void setProject(Long  project){
        this.project = project ;
        this.modify("project",project);
    }

    /**
     * 设置 [PLAN]
     */
    public void setPlan(Long  plan){
        this.plan = plan ;
        this.modify("plan",plan);
    }

    /**
     * 设置 [MODULE]
     */
    public void setModule(Long  module){
        this.module = module ;
        this.modify("module",module);
    }

    /**
     * 设置 [STORY]
     */
    public void setStory(Long  story){
        this.story = story ;
        this.modify("story",story);
    }

    /**
     * 设置 [PARENT]
     */
    public void setParent(Long  parent){
        this.parent = parent ;
        this.modify("parent",parent);
    }

    /**
     * 设置 [FROMBUG]
     */
    public void setFrombug(Long  frombug){
        this.frombug = frombug ;
        this.modify("frombug",frombug);
    }

    /**
     * 设置 [TASKSN]
     */
    public void setTasksn(Long  tasksn){
        this.tasksn = tasksn ;
        this.modify("tasksn",tasksn);
    }


}


